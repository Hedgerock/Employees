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
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdFinder;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initBindingErrors;

@Controller
public class SaveDetailsInfo extends MyController {
    public SaveDetailsInfo(
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

    @RequestMapping("/saveEmployeeDetails")
    public String saveEmployeesDetails(
            @RequestParam("potentialParentId") Long id,
            @RequestParam("emailsValue") List<String> emails,
            @RequestParam("phonesValue") List<String> phones,
            RedirectAttributes redirectAttributes,
            @ModelAttribute("currentEmployeeDetails") EmployeeDetails attr,
            BindingResult bindingResult
    ) {

        Employee employee = getCurrentValue(id, "Employee", Employee.class, this.generalInfoService);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        EmployeeDetails employeeDetails = employee.getEmployeeDetails();

        if (bindingResult.hasErrors()) {
            String redirect = String.format("redirect:/updateDetailsInfo?empId=%d&empDetId=%d",
                    employee.getId(), employeeDetails.getId());

            initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        try {
            employeeDetails.setSocialMedia(employee.getEmployeeDetails().getSocialMedia());
            SetDefaultParameters.saveEmailsAndPhones(emails, phones, employee, employeeDetails, this.generalInfoService);

            IdFinder idFinder = employeeDetails.getIdFinder();

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, "Employee details", "updated");

            return SetDefaultParameters.getActualRedirect(
                    idFinder,
                    employee.getId()
            );
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, "Employee details", "update", e);
            return String.format("redirect:/updateDetailsInfo?empId=%d&empDetId=%d",
                    employee.getId(), employeeDetails.getId());
        }
    }

}
