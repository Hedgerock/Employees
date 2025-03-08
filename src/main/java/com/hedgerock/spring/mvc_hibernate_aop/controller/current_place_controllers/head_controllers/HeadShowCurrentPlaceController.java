package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameterCurrentPlace.initCurrentPlaceAttributes;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.capitalizeIt;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo.getActualEntitiesList;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.initSiblings;

@Controller
public class HeadShowCurrentPlaceController extends MyController {

    protected <T> String initCurrentPlace(
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

    protected String getReturn(String result) {
        return result.equals(Attributes.OK) ? MAIN_VIEW : result;
    }

    @ModelAttribute
    private void initAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "showCurrentPlace");
    }


}
