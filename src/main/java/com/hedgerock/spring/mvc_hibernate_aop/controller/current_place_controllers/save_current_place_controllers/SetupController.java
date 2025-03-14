package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.save_current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initOperation;

public class SetupController extends MyController {

    protected <T, V> String initCurrentOperation(
            String entityTitle, String idTitle, T entity, V service, String redirectValue, IdCollector idCollector,
            RedirectAttributes redirectAttributes, Class<T> tClass, boolean isNew, String lastOperator) {
        String redirectTemplate = "redirect:/showCurrent" + entityTitle + "?" + idTitle + "=";
        String methodName = "saveCurrent" + entityTitle;
        String setter = "set" + entityTitle;

        return initOperation(
                entityTitle,
                this.employeeService,
                entity,
                service,
                idCollector,
                redirectAttributes,
                redirectValue,
                redirectTemplate,
                methodName,
                setter,
                tClass,
                isNew,
                lastOperator
        );
    }

}
