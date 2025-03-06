package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameterCurrentPlace.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo.getActualEntitiesList;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.initSiblings;

@Controller
@RequestMapping("/")
public class ShowCurrentPlaceController extends MyController {

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

    private String getReturn(String result) {
        return result.equals(Attributes.OK) ? "current_place/show-current-place-view" : result;
    }

    private<T> String initCurrentPlace(
            String entityName,
            String idTitle,
            RedirectAttributes redirectAttributes,
            Model model,
            Long entityId,
            Class<T> tClass
    ) {
        String capitalEntityName = capitalizeIt(entityName);

        List<T> currentEntity = getActualEntitiesList(
                entityId, capitalEntityName, tClass, this.generalInfoService);

        T nationality = initSiblings(
                currentEntity, entityId, tClass, model, entityName);

        if (nationality == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, capitalEntityName);
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        return initCurrentPlaceAttributes(
                model, nationality, entityId, redirectAttributes, entityName, idTitle, this.employeeService);

    }
}
