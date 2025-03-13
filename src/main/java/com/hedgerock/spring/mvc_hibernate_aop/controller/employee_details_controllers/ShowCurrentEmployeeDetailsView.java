package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Email;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.PhoneNumber;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media.EmployeeDescription;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media.SocialMedia;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.TimeCarrier;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo.getActualEntitiesList;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.SEARCH_EMPLOYEES_NAME;

@Controller
public class ShowCurrentEmployeeDetailsView extends MyController {
    private static final String ENTITY_NAME = "Employee";

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
