package com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.service.general_info_service.GeneralInfoService;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SetDefaultParametersGeneralInfo extends SetDefaultParameters {

    public static List<Employee> getActualEntitiesList(
            Long depId, Long cityId, Long natId, Long id, GeneralInfoService generalInfoService) {
        List<Employee> employees;
        Class<Employee>employeeClass = Employee.class;
        String entityName = "Employee";

        if (depId != null) {
            employees = generalInfoService
                    .findEntityWithSiblingsAndCurrentPlace(id, depId, "depId", entityName, employeeClass);
        } else if (cityId != null) {
            employees = generalInfoService
                    .findEntityWithSiblingsAndCurrentPlace(id, cityId, "cityId", entityName, employeeClass);
        } else if (natId != null) {
            employees = generalInfoService
                    .findEntityWithSiblingsAndCurrentPlace(id, natId, "nationalityId", entityName, employeeClass);
        } else {
            employees = generalInfoService
                    .findEntityWithSiblings(id, "Employee", employeeClass);
        }

        return employees;
    }

    public static <T> List<T> getActualEntitiesList(
            Long id, String entityName, Class<T>tClass, GeneralInfoService generalInfoService) {
        return generalInfoService.findEntityWithSiblings(id, entityName, tClass);
    }

    public static <T> List<T> getActualEntitiesList(
            Long id, String entityName, Class<T>tClass, GeneralInfoService generalInfoService, boolean isFiredEmployee) {
        return isFiredEmployee
                ? generalInfoService.findEntityWithSiblings(id, tClass)
                : getActualEntitiesList(id, entityName, tClass, generalInfoService);
    }

    public static <T> String initDelete(
            Long id, Class<T>tClass, RedirectAttributes redirectAttributes, String entityName,
            String tableFieldName, GeneralInfoService generalInfoService
    ) {
        try {
            Optional<T> entity = generalInfoService.findCurrentEntity(id, entityName, tClass);
            if (entity.isEmpty()) {
                SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, entityName);
                return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
            }
            T currentEntity = entity.get();
            Method method = tClass.getDeclaredMethod("getEmployee");
            List<?> employees = (List<?>) method.invoke(currentEntity);

            List<Long>ids = employees != null
                    ? employees
                    .stream()
                    .map(el -> SetDefaultParameters.invokeMethod(el, "getId", Long.class))
                    .collect(Collectors.toList())
                    : Collections.emptyList();

            generalInfoService.deleteCurrentEntity(currentEntity, tableFieldName, ids, tClass);

            SetDefaultParameters
                    .initSuccessFlashAttr(redirectAttributes, entityName, "deleted");
        } catch (Exception e) {
            SetDefaultParameters
                    .initFailedFlashAttr(redirectAttributes, entityName.toLowerCase(), "delete", e);
        }

        return Attributes.OK;
    }

    private SetDefaultParametersGeneralInfo() {}
}
