package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers.HeadUpdateCurrentPlaceController;
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
public class UpdateCurrentPlaceController extends HeadUpdateCurrentPlaceController {

    @GetMapping("/updateCity")
    public String updateCity(
            @RequestParam("cityId") Long cityId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        String result = initUpdateCurrentPlaceAttributes(
                model, redirectAttributes, City.class, cityId, "city", "cityId");

        return getView(result);
    }

    @GetMapping("/updateCurrentDepartment")
    public String updateDepartment(
            @RequestParam("depId") Long depId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        String result = initUpdateCurrentPlaceAttributes(
                model, redirectAttributes, Department.class, depId, "department", "depId");

        return getView(result);
    }

    @GetMapping("/updateNationality")
    public String updateNationality(
            @RequestParam("natId") Long natId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        String result = initUpdateCurrentPlaceAttributes(
                model, redirectAttributes, Nationality.class, natId, "nationality", "nationalityId");

        return getView(result);
    }

}
