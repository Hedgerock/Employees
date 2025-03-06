package com.hedgerock.spring.mvc_hibernate_aop.controller;

import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.SEARCH_EMPLOYEES_NAME;

@Controller
public class NotFoundPageController extends MyController {

    @GetMapping("/notFoundPage")
    public String handleNotFound(
            @RequestParam(value = "searchParams", required = false) String search,
            Model model
    ) {
        SetDefaultParameters.setSearch(SEARCH_EMPLOYEES_NAME, model, search);
        return "util_views/not-found-view";
    }

    @RequestMapping("/errorHandler")
    public String showErrorHandler(
            HttpServletRequest httpServletRequest,
            @ModelAttribute("statusCode") int statusCode,
            @ModelAttribute("errorMessage") String errorMessage,
            @RequestParam(value = "searchParams", required = false) String search,
            Model model
    ) {
        SetDefaultParameters.setSearch(SEARCH_EMPLOYEES_NAME, model, search);
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);

        return "util_views/error500-view";
    }

}