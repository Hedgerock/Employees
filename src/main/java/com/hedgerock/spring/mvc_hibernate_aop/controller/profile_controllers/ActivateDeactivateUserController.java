package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersProfile.initUserHandler;

@Controller
public class ActivateDeactivateUserController extends MyController {

    @RequestMapping("/deactivateUser")
    public String deactivateUser(
            @RequestParam("username") String username,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
       return initUserHandler(redirectAttributes, username, false, this.generalInfoService);
    }

    @RequestMapping("/activateUser")
    public String activateUser(
            @RequestParam("username") String username,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        return initUserHandler(redirectAttributes, username, true, this.generalInfoService);
    }

}
