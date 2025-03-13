package com.hedgerock.spring.mvc_hibernate_aop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotFoundPageController extends MyController {

    @GetMapping("/notFoundPage")
    public String handleNotFound(
            @RequestParam(value = "searchParams", required = false) String search,
            Model model
    ) {
        model.addAttribute("pagePath", "notFoundPage");
        model.addAttribute("pageTitle", "Not found");
        return MAIN_VIEW;
    }

    @RequestMapping("/errorHandler")
    public String showErrorHandler(
            HttpServletRequest httpServletRequest,
            @ModelAttribute("statusCode") int statusCode,
            @ModelAttribute("errorMessage") String errorMessage,
            @RequestParam(value = "searchParams", required = false) String search,
            Model model
    ) {
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);

        return "util_views/error500-view";
    }

}