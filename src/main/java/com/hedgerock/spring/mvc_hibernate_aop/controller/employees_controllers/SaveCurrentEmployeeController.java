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
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.CURRENT_EMPLOYEE_ATTRIBUTE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initBindingErrors;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initChanges;

@Controller
@RequestMapping("/saveCurrentEmployee")
public class SaveCurrentEmployeeController extends MyController {
    public static final String EMPLOYEE_ENTITY_TITLE = "Employee";

    public SaveCurrentEmployeeController(
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

    @PostMapping
    public String addCurrentEmployee (
            RedirectAttributes redirectAttributes,
            Model model,
            @Valid @ModelAttribute(CURRENT_EMPLOYEE_ATTRIBUTE) Employee employee,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            String redirect = "redirect:/addNewEmployee";
            return initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        initOperator(employee, model);

        return initChanges(employee,
                redirectAttributes,
                "redirect:/addNewEmployee",
                "added",
                this.generalInfoService,
                this.employeeService
        );
    }

    @PostMapping("/{id}")
    public String saveCurrentEmployee(
            RedirectAttributes redirectAttributes,
            Model model,
            @Valid @ModelAttribute(CURRENT_EMPLOYEE_ATTRIBUTE) Employee employee,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            String redirect = "redirect:/updateInfo?empId=" + employee.getId();
            return initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        initOperator(employee, model);

        return initChanges(employee,
                redirectAttributes,
                "redirect:/updateInfo?empId=" + employee.getId(),
                "updated",
                this.generalInfoService,
                this.employeeService
        );
    }
}
