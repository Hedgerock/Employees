package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.ShowPlacesAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameterCurrentPlace.initShowPlacesAttributes;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initPageable;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initPagination;

@Controller
@RequestMapping("/")
public class ShowPlacesController extends MyController {

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

    private String getView(String result) {
        return result.equals(OK) ? "current_place/current-places-view" : result;
    }

    private <T> String initShowCurrentPlace(
            RedirectAttributes redirectAttributes,
            Model model,
            String entityName,
            String entityLocation,
            Class<T> tClass,
            String idTitle
    ) {
        final String capitalizeEntityName = SetDefaultParameters.capitalizeIt(entityName);
        final String capitalizeEntityLocation = SetDefaultParameters.capitalizeIt(entityLocation);

        final String path = "/" + entityLocation;
        final String pageName = entityLocation + "Page";
        final String addHref = "addNew" + capitalizeEntityName;
        final String buttonContent = "Add new " + capitalizeEntityName;
        final String title = capitalizeEntityLocation + " page";

        final Pageable pageable = initPageable(model);
        final Page<T> currentPage = this.generalInfoService.getCurrentEntitiesList(tClass, capitalizeEntityName, pageable);

        final String status = initPagination(pageable, redirectAttributes, currentPage, model, path);
        if (!status.equals(OK)) return status;

        final ShowPlacesAttributes<T> showPlacesAttributes = initShowPlacesAttributes(
                currentPage.getContent(),
                pageName, addHref, buttonContent, idTitle, title
        );

        model.addAttribute("showPlacesAttributes", showPlacesAttributes);

        return OK;
    }
}
