package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.service.city_service.CityService;
import com.hedgerock.spring.mvc_hibernate_aop.service.department_service.DepartmentService;
import com.hedgerock.spring.mvc_hibernate_aop.service.email_service.EmailService;
import com.hedgerock.spring.mvc_hibernate_aop.service.employee_details_service.EmployeeDetailsService;
import com.hedgerock.spring.mvc_hibernate_aop.service.employee_service.EmployeeService;
import com.hedgerock.spring.mvc_hibernate_aop.service.general_info_service.GeneralInfoService;
import com.hedgerock.spring.mvc_hibernate_aop.service.histrory_service.HistoryService;
import com.hedgerock.spring.mvc_hibernate_aop.service.nationality_service.NationalityService;
import com.hedgerock.spring.mvc_hibernate_aop.service.phoneService.PhoneService;
import com.hedgerock.spring.mvc_hibernate_aop.service.picture_service.PictureService;
import com.hedgerock.spring.mvc_hibernate_aop.service.user_service.UserService;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public HeadShowCurrentPlaceController(
            PasswordEncoder passwordEncoder,
            EmployeeService employeeService,
            DepartmentService departmentService,
            EmployeeDetailsService employeeDetailsService,
            HistoryService historyService,
            CityService cityService,
            GeneralInfoService generalInfoService,
            NationalityService nationalityService,
            UserService userService,
            PictureService pictureService,
            EmailService emailDetailsService,
            PhoneService phoneNumberDetailsService,
            ServletContext servletContext
    ) {
        super(passwordEncoder, employeeService, departmentService, employeeDetailsService, historyService, cityService,
                generalInfoService, nationalityService, userService, pictureService, emailDetailsService,
                phoneNumberDetailsService, servletContext
        );
    }

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
