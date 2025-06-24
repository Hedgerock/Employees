package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

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
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo.initDelete;

@Controller
@RequestMapping("/")
public class DeleteCurrentPlaceController extends MyController {
    public DeleteCurrentPlaceController(
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

    @RequestMapping("deleteCity")
    public String deleteCity(
            RedirectAttributes redirectAttributes,
            @RequestParam("cityId") Long cityId
    ) {
        String status = initDelete(
                cityId, City.class, redirectAttributes, "City",
                "cityId", this.generalInfoService
        );

        return getStatus(status, "cities");
    }

    @RequestMapping("deleteCurrentDepartment")
    public String deleteCurrentDepartment(
            RedirectAttributes redirectAttributes,
            @RequestParam("depId") Long depId
    ) {
        String status = initDelete(
                depId, Department.class, redirectAttributes, "Department",
                "depId", this.generalInfoService
        );

        return getStatus(status, "departments");
    }

    @RequestMapping("deleteNationality")
    public String deleteNationality(
            @RequestParam("natId") Long natId,
            RedirectAttributes redirectAttributes
    ) {
        String status = initDelete(
                natId, Nationality.class, redirectAttributes, "Nationality",
                "nationalityId", this.generalInfoService
        );

        return getStatus(status, "nationalities");
    }

    private String getStatus(String status, String currentPlace) {
        return status.equals("ok") ? String.format("redirect:/%s", currentPlace) : status;
    }
}
