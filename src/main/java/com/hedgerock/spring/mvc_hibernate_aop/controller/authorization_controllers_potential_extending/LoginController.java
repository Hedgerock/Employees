package com.hedgerock.spring.mvc_hibernate_aop.controller.authorization_controllers_potential_extending;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login-page")
    public String login() {
        return "authorization/login-page-view";
    }

}
