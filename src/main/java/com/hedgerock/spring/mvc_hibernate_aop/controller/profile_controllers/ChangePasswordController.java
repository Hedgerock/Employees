package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.ChangePasswordDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initBindingErrors;

@Controller
public class ChangePasswordController extends HeadProfileController {

    @RequestMapping("/changePassword")
    public String changePassword(
            @Valid  @ModelAttribute("passwords")ChangePasswordDTO changePasswordDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        String redirect = "redirect:/settings";

        if (bindingResult.hasErrors()) {
            return initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        User user = (User) model.getAttribute("authorizedUser");

        if (user == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "User");
            return redirect;
        }

        String newPassword = changePasswordDTO.getPassword();
        String username = user.getUsername();

        try {
            String encodedPassword = user.getPassword();

            if (this.passwordEncoder.matches(newPassword, encodedPassword)) {
                SetDefaultParameters.initFailedFlashAttr(redirectAttributes, "User", "change password",
                        new RuntimeException("Password can't be previous"));

                return redirect;
            }

            user.setPassword(this.passwordEncoder.encode(newPassword));
            this.generalInfoService.saveCurrentEntity(user);

            SetDefaultParameters
                    .initSuccessFlashAttr(redirectAttributes, "Password " + username, "changed");

        } catch (Exception e) {
            SetDefaultParameters
                    .initFailedFlashAttr(redirectAttributes, "Password " + username, "change", e);
        }

        return redirect;
    }
}
