package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.ChangePasswordDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.UserDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;

@Controller
public class ProfileController extends HeadProfileController {
    private static final String ATTR_TITLE = "contentPage";


    @GetMapping("/profile")
    public String mainProfile(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return MAIN_VIEW;
    }

    @GetMapping("/admins")
    public String admins(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Pageable pageable = SetDefaultParameters.initPageable(model);
        Page<User> users = this.generalInfoService.getCurrentEntitiesPage(User.class, "User", pageable);

        model.addAttribute(ATTR_TITLE, "admins");

        String result = SetDefaultParameters
                .initPagination(pageable, redirectAttributes, users, model, "/admins");


        model.addAttribute("users", users.getContent());

        return result.equals(OK) ? MAIN_VIEW : result;
    }

    @GetMapping("/settings")
    public String settings(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("passwords", new ChangePasswordDTO());
        model.addAttribute("userDetails", new UserDetailsDTO());
        model.addAttribute(ATTR_TITLE, "settings");
        return MAIN_VIEW;
    }

}
