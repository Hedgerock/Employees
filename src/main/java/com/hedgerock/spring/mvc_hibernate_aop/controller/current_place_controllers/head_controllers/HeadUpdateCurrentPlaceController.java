package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
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
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.UpdateCurrentPlaceAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameterCurrentPlace.initUpdateAttributes;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.capitalizeIt;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;

@Controller
public class HeadUpdateCurrentPlaceController extends MyController {
    public HeadUpdateCurrentPlaceController(
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

    protected <T> void initAddControllerAttributes(
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
            model.addAttribute("pageTitle", title);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected <T> String initUpdateCurrentPlaceAttributes(
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
            model.addAttribute("pageTitle", name + ": update page");
            model.addAttribute("allEmployees", employees);
            model.addAttribute("idCollector", new IdCollector());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return OK;
    }

    @ModelAttribute
    private void initAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "currentPlace");
    }

    protected String getView(String value) {
        return value.equals(OK) ? MAIN_VIEW : value;
    }
}
