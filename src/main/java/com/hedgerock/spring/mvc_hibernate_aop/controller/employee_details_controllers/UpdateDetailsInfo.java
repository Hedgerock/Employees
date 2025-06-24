package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initIdFinder;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.validateDetails;

@Controller
public class UpdateDetailsInfo extends MyController {
    public UpdateDetailsInfo(
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

    @ModelAttribute
    private void initGlobalAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "updateDetails");
    }

    @GetMapping("/updateDetailsInfo")
    public String updateDetailsInfo(
            @RequestParam("empDetId") Long empDetId,
            @RequestParam("empId") Long empId,
            @RequestParam(value = "depId", required = false) Long depId,
            @RequestParam(value = "natId", required = false) Long natId,
            @RequestParam(value = "cityId", required = false) Long cityId,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        Employee employee = getCurrentValue(empId, "Employee", Employee.class, this.generalInfoService);

        String validation = validateDetails(employee, redirectAttributes, empDetId, depId, cityId, natId);
        if (!validation.equals(OK)) return validation;

        EmployeeDetails details = employee.getEmployeeDetails();

        initIdFinder(employee.getEmployeeDetailsId(), depId, cityId, natId);

        model.addAttribute("currentEmployeeDetails", details);
        model.addAttribute("emails", details.getEmails());
        model.addAttribute("phoneNumber", details.getPhoneNumbers());
        model.addAttribute("title", String.format("Edit details for employee %s %s page",
                details.getEmployee().getFirstName(), details.getEmployee().getLastName())
        );

        return MAIN_VIEW;
    }

}
