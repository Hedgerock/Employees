package com.hedgerock.spring.mvc_hibernate_aop.controller.history_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
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
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.ServletContext;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersHistory.initCurrentRevision;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersHistory.initShowPlaces;

@Controller
public class CurrentPlaceHistoryController extends MyController {
    public CurrentPlaceHistoryController(
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
        model.addAttribute("pagePath", "historyCurrentPlace");
    }

    @GetMapping("/showDepartmentHistory")
    public String showDepartmentHistory(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("depId") Long depId
    ) {
        Pageable pageable = (Pageable) model.getAttribute(PAGEABLE_TITLE);
        String entityName = "Department";

        Department currentDepartment = getCurrentValue(depId, entityName,
                Department.class, this.generalInfoService);

        if (currentDepartment == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, entityName);
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }


        initShowPlaces(model, "depId", depId, entityName);

        return initCurrentRevision(model, currentDepartment, "departments", "depId",
                pageable, redirectAttributes, "/showDepartmentHistory", this.historyService);
    }

    @GetMapping("/showCityHistory")
    public String showCityHistory(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("cityId") Long cityId
    ) {
        Pageable pageable = initPageable(model);
        String entityName = "City";

        City currentCity = getCurrentValue(cityId, entityName,
                City.class, this.generalInfoService);

        if (currentCity == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, entityName);
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        initShowPlaces(model, "cityId", cityId, entityName);

        return initCurrentRevision(model, currentCity, "cities", "cityId",
                pageable, redirectAttributes, "/showCityHistory", this.historyService);
    }

    @GetMapping("/showNationalityHistory")
    public String showNationalityHistory(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("natId") Long natId
    ) {
        Pageable pageable = initPageable(model);
        String entityName = "Nationality";

        Nationality currentNationality = getCurrentValue(natId, entityName,
                Nationality.class, this.generalInfoService);

        if (currentNationality == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, entityName);
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        initShowPlaces(model, "natId", natId, entityName);

        return initCurrentRevision(model, currentNationality, "nationalities", "natId", pageable,
                redirectAttributes, "/showNationalityHistory", this.historyService);
    }

 }
