package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.save_current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/saveCurrentNationality")
public class SaveNationalityController extends SetupController {
    private static final String ENTITY_TITLE = "Nationality";
    private static final String ENTITY_ID_TITLE = "natId";


    @PostMapping
    public String addNationality(
            @ModelAttribute("nationality") Nationality nationality,
            @ModelAttribute("idCollector")IdCollector idCollector,
            Model model,
            RedirectAttributes redirectAttributes
            )
    {
        String operator = initOperator(nationality, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, nationality, this.nationalityService,
                "redirect:/addNewNationality", idCollector, redirectAttributes, Nationality.class, true, operator);
    }

    @PostMapping("/{natId}")
    public String updateNationality(
            @ModelAttribute("nationality") Nationality nationality,
            @ModelAttribute("idCollector")IdCollector idCollector,
            Model model,
            RedirectAttributes redirectAttributes
            )
    {
        String redirect = "redirect:/updateNationality?natId=";
        String operator = initOperator(nationality, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, nationality, this.nationalityService,
                redirect, idCollector, redirectAttributes, Nationality.class, false, operator);
    }

}
