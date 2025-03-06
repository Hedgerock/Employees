package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.UpdateCurrentPlaceAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameterCurrentPlace.initUpdateAttributes;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;

@Controller
@RequestMapping("/")
public class UpdateCurrentPlaceController extends MyController {

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

    private String getView(String value) {
        return value.equals(OK) ? "current_place/current-place-info" : value;
    }

    private<T> String initUpdateCurrentPlaceAttributes(
            Model model,
            RedirectAttributes redirectAttributes,
            Class<T> tClass,
            Long entityId,
            String entityName,
            String idTitle
    ) {
        String capitalizeEntityName = SetDefaultParameters.capitalizeIt(entityName);
        String action = "saveCurrent" + capitalizeEntityName;

        try {
            T currentPlace = getCurrentValue(entityId, capitalizeEntityName, tClass, this.generalInfoService);
            String name = SetDefaultParameters.invokeMethod(currentPlace, "getName", String.class);

            if (currentPlace == null) {
                SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, capitalizeEntityName);
                return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
            }

            List<Employee> employees = this.employeeService.getSpecificEmployees(idTitle);

            UpdateCurrentPlaceAttributes<T> cityUpdateCurrentPlaceAttributes = initUpdateAttributes(
                    currentPlace, action, entityId, entityName
            );

            model.addAttribute("updateAttributes", cityUpdateCurrentPlaceAttributes);
            model.addAttribute(entityName, currentPlace);
            model.addAttribute("title", name + ": update page");
            model.addAttribute("allEmployees", employees);
            model.addAttribute("idCollector", new IdCollector());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return OK;
    }
}
