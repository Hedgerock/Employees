package com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.service.employee_service.EmployeeService;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.ShowCurrentPlaceAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.ShowPlacesAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.UpdateCurrentPlaceAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.ALL_EMPLOYEES_ATTRIBUTE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;

public class SetDefaultParameterCurrentPlace extends SetDefaultParameters {

    public static Page<Employee> getSpecificEmployees(
            String searchParams, Long id, EmployeeService employeeService, String tableFieldName, Pageable pageable) {
        Page<Employee> employees = searchParams != null && !searchParams.isEmpty()
                ? employeeService.findEmployeesInCurrentPlace(id, searchParams, tableFieldName, pageable)
                : employeeService.findEmployeesInCurrentPlace(id, tableFieldName, pageable);

        validateEmployeeDetails(employees);

        return employees;
    }

    public static <T> UpdateCurrentPlaceAttributes<T> initUpdateAttributes(
            T entity, String action, Long entityId, String entityName
    ) {
        UpdateCurrentPlaceAttributes<T> cityUpdateCurrentPlaceAttributes = new UpdateCurrentPlaceAttributes<>();

        String attrPath = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);

        cityUpdateCurrentPlaceAttributes.setCurrentPlace(entity);

        if (entityId != null) {
            cityUpdateCurrentPlaceAttributes.setUpdateAction(action + "/" + entityId);
        }

        cityUpdateCurrentPlaceAttributes.setMainAction(action);
        cityUpdateCurrentPlaceAttributes.setAttrName(entityName);
        cityUpdateCurrentPlaceAttributes.setAttrPath(attrPath);

        return cityUpdateCurrentPlaceAttributes;
    }

    public static <T> ShowPlacesAttributes<T> initShowPlacesAttributes(
            List<T> entities, String pageName, String addHref, String buttonContent, String placeTitle, String title
    ) {
        ShowPlacesAttributes<T> showPlacesAttributes = new ShowPlacesAttributes<>();
        showPlacesAttributes.setCurrentPlaces(entities);
        showPlacesAttributes.setPageName(pageName);
        showPlacesAttributes.setAddHref(addHref);
        showPlacesAttributes.setButtonContent(buttonContent);
        showPlacesAttributes.setPlaceTitle(placeTitle);
        showPlacesAttributes.setTitle(title);

        return showPlacesAttributes;
    }

    public static <T> ShowCurrentPlaceAttributes<T> initShowAttributes(
            List<T>entities, String pageTitle, Long idValue, String placeTitle, String title, String page) {
        ShowCurrentPlaceAttributes<T> showCurrentPlaceAttributes = new ShowCurrentPlaceAttributes<>();

        showCurrentPlaceAttributes.setCurrentPlaces(entities);
        showCurrentPlaceAttributes.setPageTitle(pageTitle);
        showCurrentPlaceAttributes.setIdValue(idValue);
        showCurrentPlaceAttributes.setPlaceTitle(placeTitle);
        showCurrentPlaceAttributes.setTitle(title);
        showCurrentPlaceAttributes.setPage(page);

        return showCurrentPlaceAttributes;
    }


    public static <T> String initCurrentPlaceAttributes(
            Model model,
            T entity,
            Long entityIdValue,
            RedirectAttributes redirectAttributes,
            String entityTitle,
            String idTitle,
            EmployeeService employeeService
    ) {
        Pageable pageable = initPageable(model);
        String search = initSearch(model);
        String name = SetDefaultParameters.invokeMethod(entity, "getName", String.class);

        String capitalTitle = entityTitle.substring(0, 1).toUpperCase()
                + entityTitle.substring(1).toLowerCase();

        String tableFieldName = entityTitle + ".id";
        String pageTitle = "current" + capitalTitle;
        String currentPage = "showCurrent" + capitalTitle;
        String path = "/showCurrent" + capitalTitle;


        List<T> nationalities = List.of(entity);

        Page<Employee> employees = SetDefaultParameterCurrentPlace.getSpecificEmployees(
                search, entityIdValue, employeeService, tableFieldName, pageable);

        String status = initPagination(pageable, redirectAttributes, employees, model, path);
        if (!status.equals(OK)) return status;

        model.addAttribute(ALL_EMPLOYEES_ATTRIBUTE, employees.getContent());
        String currentTitle = capitalTitle + " " +
                name + (search.isEmpty() ? ": All employees" : ": Searched employees");

        ShowCurrentPlaceAttributes<T> nationalityShowCurrentPlaceAttributes = initShowAttributes(
                nationalities, pageTitle, entityIdValue, idTitle, currentTitle,
                currentPage
        );

        model.addAttribute("showAttributes", nationalityShowCurrentPlaceAttributes);

        return OK;
    }

}
