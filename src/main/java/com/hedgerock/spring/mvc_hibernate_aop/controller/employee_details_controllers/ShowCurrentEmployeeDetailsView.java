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
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.TimeCarrier;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo.getActualEntitiesList;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.*;

@Controller
public class ShowCurrentEmployeeDetailsView extends MyController {
    private static final String ENTITY_NAME = "Employee";

    public ShowCurrentEmployeeDetailsView(
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
    private void initGlobalAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "showCurrentEmployeeDetails");
    }

    @GetMapping("/showCurrentEmployeeDetails")
    public String showCurrentDetails(
            @RequestParam(value = "searchParams", required = false) String search,
            @RequestParam("empId") Long id,
            @RequestParam(value = "depId", required = false) Long depId,
            @RequestParam(value = "cityId", required = false) Long cityId,
            @RequestParam(value = "natId", required = false) Long natId,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        List<Employee> employees = SetDefaultParametersGeneralInfo
                .getActualEntitiesList(depId, cityId, natId, id, this.generalInfoService);

       Employee currentEmployee = initSiblings(employees, id, Employee.class, model, "employee");

       if (currentEmployee == null) {
           SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
           return REDIRECT_TO_NOT_FOUND_PAGE;
       }


       EmployeeDetails employeeDetails = currentEmployee.getEmployeeDetails();

        String status = validateEntities(currentEmployee, redirectAttributes, depId, cityId, natId);

        if (!status.equals(OK)) {
            return status;
        }

        initSlider(employeeDetails, model);

        addAttributes(model, currentEmployee,  depId, cityId, natId, this.servletContext);

        model.addAttribute("phoneNumbers", employeeDetails.getPhoneNumbers());
        model.addAttribute("emails", employeeDetails.getEmails());
        model.addAttribute("pageTitle", currentEmployee.getFirstName() + ": " + "details page");

        return MAIN_VIEW;
    }

    @GetMapping("/showFiredEmployeeDetails")
    public String showCurrentFiredEmployeeDetails(
            @RequestParam("empId") Long empId,
            @RequestParam(value = "searchParams", required = false) String search,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        List<Employee> employees = getActualEntitiesList(empId,
                "Employee", Employee.class, this.generalInfoService, true);

        Employee currentEmployee = initSiblings(employees, empId, Employee.class, model, "employee");

        if (currentEmployee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Fired employee");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        EmployeeDetails employeeDetails = currentEmployee.getEmployeeDetails();
        TimeCarrier timeCarrier = initTimeCarrier(currentEmployee.getFireDate(), "setFireDate");

        checkPicture(employeeDetails, this.servletContext, model);
        initSlider(employeeDetails, model);

        model.addAttribute("timeCarrier", timeCarrier);
        model.addAttribute("isFired", true);
        model.addAttribute("phoneNumbers", employeeDetails.getPhoneNumbers());
        model.addAttribute("emails", employeeDetails.getEmails());
        model.addAttribute("pageTitle", "Fired employee - " + currentEmployee.getFirstName() + ": " + "details page");

        return MAIN_VIEW;
    }
}
