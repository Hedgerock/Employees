package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers.HeadShowCurrentPlaceController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ShowCurrentPlaceController extends HeadShowCurrentPlaceController {

    @GetMapping("showCurrentNationality")
    public String showCurrentNationality(
            Model model,
            @RequestParam("natId") Long natId,
            RedirectAttributes redirectAttributes
    ) {
        String result = initCurrentPlace(
                "nationality", "natId", redirectAttributes, model, natId, Nationality.class);
        return getReturn(result);
    }

    @GetMapping("showCurrentCity")
    public String showCurrentCity(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("cityId") Long cityId
    ) {
        String result = initCurrentPlace(
                "city", "cityId", redirectAttributes, model, cityId, City.class);
        return getReturn(result);
    }

    @GetMapping("showCurrentDepartment")
    public String showCurrentDepartment(
            @RequestParam("depId") Long depId,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        String result = initCurrentPlace(
                "department", "depId", redirectAttributes, model, depId, Department.class);

        return getReturn(result);
    }

}
