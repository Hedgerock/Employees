package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.save_current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
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
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/saveCurrentCity")
public class SaveCityController extends SetupController {
    public SaveCityController(
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

    private static final String ENTITY_TITLE = "City";
    private static final String ENTITY_ID_TITLE = "cityId";

    @PostMapping
    public String addCity(
            @ModelAttribute("city") City city,
            Model model,
            @ModelAttribute("idCollector") IdCollector idCollector,
            RedirectAttributes redirectAttributes
    ) {
        String operator = initOperator(city, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, city, this.cityService,
                "redirect:/addNewCity", idCollector, redirectAttributes, City.class, true, operator);
    }

    @PostMapping("/{cityId}")
    public String saveCity(
            @ModelAttribute("city")City city,
            Model model,
            @ModelAttribute("idCollector")IdCollector idCollector,
            RedirectAttributes redirectAttributes
            ) {
        String redirectValue = "redirect:/updateCity?cityId=" + city.getId();
        String operator = initOperator(city, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, city, this.cityService,
                redirectValue, idCollector, redirectAttributes, City.class,false, operator);
    }
}
