package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.save_current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/saveCurrentCity")
public class SaveCityController extends SetupController {
    private static final String ENTITY_TITLE = "City";
    private static final String ENTITY_ID_TITLE = "cityId";

    @PostMapping
    public String addCity(
            @ModelAttribute("city") City city,
            Model model,
            @ModelAttribute("idCollector") IdCollector idCollector,
            RedirectAttributes redirectAttributes
    ) {
        String operator = initOperator(city, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, city, this.cityService,
                "redirect:/addNewCity", idCollector, redirectAttributes, City.class, true, operator);
    }

    @PostMapping("/{cityId}")
    public String saveCity(
            @ModelAttribute("city")City city,
            Model model,
            @ModelAttribute("idCollector")IdCollector idCollector,
            RedirectAttributes redirectAttributes
            ) {
        String redirectValue = "redirect:/updateCity?cityId=" + city.getId();
        String operator = initOperator(city, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, city, this.cityService,
                redirectValue, idCollector, redirectAttributes, City.class,false, operator);
    }
}
