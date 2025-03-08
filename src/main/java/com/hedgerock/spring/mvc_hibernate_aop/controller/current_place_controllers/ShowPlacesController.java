package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers.HeadShowPlacesController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ShowPlacesController extends HeadShowPlacesController {

    @GetMapping("cities")
    public String showCities(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        String result = initShowCurrentPlace(
                redirectAttributes, model, "city", "cities", City.class, "cityId");

        return getView(result);
    }

    @GetMapping("departments")
    public String showDepartments(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        String result = initShowCurrentPlace(
                redirectAttributes, model, "department", "departments", Department.class, "depId");

        return getView(result);
    }

    @GetMapping("nationalities")
    public String showNationalities(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        String result = initShowCurrentPlace(
                redirectAttributes, model, "nationality", "nationalities", Nationality.class, "natId");

        return getView(result);
    }
}
