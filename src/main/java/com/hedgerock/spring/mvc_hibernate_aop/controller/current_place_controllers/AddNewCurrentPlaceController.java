package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers.HeadUpdateCurrentPlaceController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.UpdateCurrentPlaceAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameterCurrentPlace.initUpdateAttributes;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.capitalizeIt;

@Controller
@RequestMapping("/")
public class AddNewCurrentPlaceController extends HeadUpdateCurrentPlaceController {

    @GetMapping("addNewCity")
    public String addCity(
            Model model
    ) {
        initAddControllerAttributes(model, City.class, "city", "cityId");
        return MAIN_VIEW;
    }

    @GetMapping("addNewDepartment")
    public String addDepartment(
            Model model
    ) {
        initAddControllerAttributes(model, Department.class, "department", "depId");
        return MAIN_VIEW;
    }

    @GetMapping("addNewNationality")
    public String addNationality(
            Model model
    ) {
        initAddControllerAttributes(model, Nationality.class, "nationality", "nationalityId");
        return MAIN_VIEW;
    }

}
