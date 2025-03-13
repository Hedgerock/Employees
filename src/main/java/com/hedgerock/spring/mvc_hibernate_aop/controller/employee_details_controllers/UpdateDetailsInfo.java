package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Email;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.PhoneNumber;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.SEARCH_EMPLOYEES_NAME;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initIdFinder;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.validateDetails;

@Controller
public class UpdateDetailsInfo extends MyController {

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
