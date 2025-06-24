package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.head_controllers.HeadShowPlacesController;
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
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ShowPlacesController extends HeadShowPlacesController {
    public ShowPlacesController(
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
}
