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
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.ShowPlacesAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.ServletContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameterCurrentPlace.initShowPlacesAttributes;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initPageable;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initPagination;

@Controller
public class HeadShowPlacesController extends MyController {
    public HeadShowPlacesController(
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
        );;
    }

    protected String getView(String result) {
        return result.equals(OK) ? MAIN_VIEW : result;
    }

    protected  <T> String initShowCurrentPlace(
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

        List<T>content = currentPage.getContent();

        final ShowPlacesAttributes<T> showPlacesAttributes = initShowPlacesAttributes(
                content,
                pageName, addHref, buttonContent, idTitle, title
        );

        model.addAttribute("showPlacesAttributes", showPlacesAttributes);

        if (content.isEmpty()) {
            model.addAttribute("imgValue", entityLocation);
        }

        return OK;
    }

    @ModelAttribute
    private void initAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "currentPlaces");
    }

}
