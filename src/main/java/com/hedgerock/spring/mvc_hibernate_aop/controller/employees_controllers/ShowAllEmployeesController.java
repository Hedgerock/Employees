package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

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
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.GeneralInfo;
import jakarta.servlet.ServletContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
@RequestMapping("/")
public class ShowAllEmployeesController extends MyController {
    private static final String PAGE_TITLE = "Main page: %s";

    public ShowAllEmployeesController(
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
    private void initAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "employees");
    }

    @GetMapping
    public String showAllEmployees(
            RedirectAttributes redirectAttributes,
            final Model model) {
        final String result = initAllEmployees(model, "/", redirectAttributes, false);

        if (!result.equals(OK)) return result;

        final GeneralInfo generalInfo = this.generalInfoService.getGeneralInfo();
        model.addAttribute("generalInfo", generalInfo);
        model.addAttribute("pageTitle", String.format(PAGE_TITLE, "All employees"));

        return MAIN_VIEW;
    }

    @GetMapping("firedEmployees")
    public String showFiredEmployees(
            RedirectAttributes redirectAttributes,
            final Model model
    ) {
        final String result = initAllEmployees(model, "/firedEmployees", redirectAttributes, true);

        model.addAttribute("altAction", "/firedEmployees");
        model.addAttribute("pageTitle", String.format(PAGE_TITLE, "All fired employees"));
        return result.equals(OK) ? MAIN_VIEW : result;
    }

    private String initAllEmployees(
            Model model,
            String path,
            RedirectAttributes redirectAttributes,
            boolean isLookingForFired
    ) {
        final Pageable pageable = initPageable(model);
        final String search = initSearch(model);

        final Page<Employee> employees = SetDefaultParameters.getEmployees(this.employeeService, search, pageable,
                isLookingForFired);

        String status = initPagination(pageable, redirectAttributes, employees, model, path);
        if (!status.equals(OK)) return status;

        model.addAttribute(ALL_EMPLOYEES_ATTRIBUTE, employees.getContent());

        return OK;
    }
}
