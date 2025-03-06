package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
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
public class AddNewCurrentPlaceController extends MyController {

    @GetMapping("addNewCity")
    public String addCity(
            Model model
    ) {
        initAddControllerAttributes(model, City.class, "city", "cityId");
        return "current_place/current-place-info";
    }

    @GetMapping("addNewDepartment")
    public String addDepartment(
            Model model
    ) {
        initAddControllerAttributes(model, Department.class, "department", "depId");
        return "current_place/current-place-info";
    }

    @GetMapping("addNewNationality")
    public String addNationality(
            Model model
    ) {
        initAddControllerAttributes(model, Nationality.class, "nationality", "nationalityId");
        return "current_place/current-place-info";
    }

    private <T> void initAddControllerAttributes(
            Model model,
            Class<T> tClass,
            String entityName,
            String idName
    ) {
        List<Employee> employees = this.employeeService.getSpecificEmployees(idName);
        String capitalEntityName = capitalizeIt(entityName);

        String action = "saveCurrent" + capitalEntityName;
        String title = "New " + entityName + ": creation page";

        model.addAttribute("allEmployees", employees);
        model.addAttribute("idCollector", new IdCollector());

        try {
            T currentPlace = tClass.getDeclaredConstructor().newInstance();

            Long currentPlaceId = SetDefaultParameters.invokeMethod(currentPlace, "getId", Long.class);

            UpdateCurrentPlaceAttributes<T> updateAttributes = initUpdateAttributes(
                    currentPlace, action, currentPlaceId, entityName
            );

            model.addAttribute("updateAttributes", updateAttributes);
            model.addAttribute(entityName, currentPlace);
            model.addAttribute("title", title);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
