package com.hedgerock.spring.mvc_hibernate_aop.controller.history_controllers;

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
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.CurrentPlaceDTO;
import jakarta.servlet.ServletContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initPageable;

@Controller
public class CurrentPlacesHistoryLists extends MyController {
    private static final String TEMPLATE_OF_CURRENT_PLACE_HISTORY_REF = "show%sHistory";

    public CurrentPlacesHistoryLists(
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

    @ModelAttribute
    private void globalAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "currentPlaceList");
    }

    @GetMapping("/departmentHistory")
    public String getDepartmentsList(
            Model model
    ) {
        final Pageable pageable = initPageable(model);
        model.addAttribute("showHistory", String.format(TEMPLATE_OF_CURRENT_PLACE_HISTORY_REF, "Department"));
        return initHistory("departments", model,"depId", pageable);
    }

    @GetMapping("/cityHistory")
    public String getCitiesList(
            Model model
    ) {
        final Pageable pageable = initPageable(model);
        model.addAttribute("showHistory", String.format(TEMPLATE_OF_CURRENT_PLACE_HISTORY_REF, "City"));
        return initHistory("cities", model, "cityId", pageable);
    }

    @GetMapping("/nationalityHistory")
    public String getNationalitiesList(
            Model model
    ) {
        final Pageable pageable = initPageable(model);
        model.addAttribute("showHistory", String.format(TEMPLATE_OF_CURRENT_PLACE_HISTORY_REF, "Nationality"));
        return initHistory("nationalities", model, "natId", pageable);
    }

    private String initHistory(String entityName, Model model, String idTitle, Pageable pageable) {
        Page<CurrentPlaceDTO> currentPlaceDTOS = this.historyService.getCurrentPlaceHistory(entityName, pageable);
        model.addAttribute("currentPlaces", currentPlaceDTOS.getContent());
        model.addAttribute("pageTitle", "History of " + entityName + " page");
        model.addAttribute("idTitle", idTitle);

        return MAIN_VIEW;
    }
}
