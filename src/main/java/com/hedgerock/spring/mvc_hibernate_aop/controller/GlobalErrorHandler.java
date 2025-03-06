package com.hedgerock.spring.mvc_hibernate_aop.controller;

import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class GlobalErrorHandler {

    @GetMapping("/error404")
    public String handle404Error(
            RedirectAttributes redirectAttributes
    ) {
        SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Error 404 page");
        return "redirect:/notFoundPage";
    }

    @GetMapping("/error400")
    public String handle400(
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("message","Error 400 bad request");
        redirectAttributes.addFlashAttribute("status", "error");

        return "redirect:/notFoundPage";
    }
}
