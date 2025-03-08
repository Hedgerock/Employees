package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;


import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LightDarkModeController extends HeadProfileController {

    @RequestMapping("/darkMode")
    public String initDarkMode(
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {

        return initMode(model, redirectAttributes, true);
    }

    @RequestMapping("/lightMode")
    public String initLightMode(
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {
        return initMode(model, redirectAttributes, false);
    }


    private String initMode(
            Model model,
            RedirectAttributes redirectAttributes,
            Boolean trueDarkFalseLight
    ) {
        String mode = trueDarkFalseLight ? "Dark mode": "Light mode";

        try {
            final User user = (User) model.getAttribute("authorizedUser");

            if (user != null) {
                user.setThemeMode(trueDarkFalseLight);
                this.generalInfoService.saveCurrentEntity(user);
            }

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, mode, "changed");
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, mode, "change", e);
        }

        return "redirect:/profile";
    }
}
