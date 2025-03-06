package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdFinder;
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
        employeeDetails.setDateOfBirth(attr.getDateOfBirth());

        if (bindingResult.hasErrors()) {
            String redirect = String.format("redirect:/updateDetailsInfo?empId=%d&empDetId=%d",
                    employee.getId(), employeeDetails.getId());

            initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        try {

            SetDefaultParameters.saveEmailsAndPhones(emails, phones, employee, employeeDetails);

            employeeDetails.setSocialMedia(employee.getEmployeeDetails().getSocialMedia());

            this.employeeDetailsService.saveEmployeeDetails(employeeDetails);
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
