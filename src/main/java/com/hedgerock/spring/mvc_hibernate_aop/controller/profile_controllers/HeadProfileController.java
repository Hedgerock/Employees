package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HeadProfileController extends MyController {


    @ModelAttribute
    protected void addProfilePageContext(
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("pagePath", "profiles");
    }

    protected String validateUser(
            String username, RedirectAttributes redirectAttributes, String errorRedirect) {
        final String entityName = "User";
        final Optional<User> searchUser = this.generalInfoService
                .findCurrentEntity(username, entityName, "username", User.class);

        if (searchUser.isPresent()) {
            String errorMessage = entityName + " " + username + " already exists";
            SetDefaultParameters
                    .initFailedFlashAttr(redirectAttributes,
                            entityName, "add", new RuntimeException(errorMessage));
            return errorRedirect;
        }

        return Attributes.OK;
    }
}
