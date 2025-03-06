package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.AddUserDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.enums.Roles;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initBindingErrors;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersProfile.initUser;

@Controller
public class AddUserController extends MyController {
    private static final String ATTR_TITLE = "contentPage";
    private static final String VIEW_PAGE = "profiles/my-profile-view";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/addUser")
    public String addUser(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Map<String, String> roles = new LinkedHashMap<>();

        for(Object el: Roles.values()) {
            roles.put(el.toString(), el.toString());
        }

        model.addAttribute("roles", roles);
        model.addAttribute(ATTR_TITLE, "add-user");
        model.addAttribute("newUser", new AddUserDTO());
        return VIEW_PAGE;
    }

    @PostMapping("/saveUser")
    public String saveUser(
            @Valid @ModelAttribute("newUser") AddUserDTO user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        final String redirect = "redirect:/addUser";
        final String entityName = "User";
        final String operation = "create";
        final String operationExt = operation + "d";

        if (bindingResult.hasErrors()) {
            return initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        String username = user.getUsername();

        final Optional<User> searchUser = this.generalInfoService
                .findCurrentEntity(username, entityName, "username", User.class);

        if (searchUser.isPresent()) {
            String errorMessage = entityName + " " + username + " already exists";
            SetDefaultParameters
                    .initFailedFlashAttr(redirectAttributes,
                            entityName, "add", new RuntimeException(errorMessage));
            return redirect;
        }

        final User curUser = initUser(user, this.passwordEncoder);

        try {
            this.generalInfoService.saveCurrentEntity(curUser);
            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, entityName, operationExt);

            return redirect;
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entityName, operation, e);
            return redirect;
        }

    }

}
