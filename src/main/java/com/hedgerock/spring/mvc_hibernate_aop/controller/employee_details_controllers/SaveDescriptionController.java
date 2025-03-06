package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media.EmployeeDescription;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;

@Controller
public class SaveDescriptionController extends MyController {

    @RequestMapping("/saveDescription")
    public String saveDescription(
            @RequestParam("empId") Long id,
            @ModelAttribute("employeeDescriptionAttribute") String textContent,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Employee employee = SetDefaultParameters
                    .getCurrentValue(id, "Employee", Employee.class, this.generalInfoService);

            if (employee == null) {
                SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
                return REDIRECT_TO_NOT_FOUND_PAGE;
            }

            EmployeeDetails employeeDetails = employee.getEmployeeDetails();
            EmployeeDescription employeeDescription = employeeDetails.getEmployeeDescription();

            System.out.println(textContent);

            employeeDescription.setValue(textContent);

            this.generalInfoService.saveCurrentEntity(employeeDescription);

            SetDefaultParameters
                    .initSuccessFlashAttr(redirectAttributes, "Employee details description", "updated");
        } catch (Exception e) {
            SetDefaultParameters
                    .initFailedFlashAttr(redirectAttributes, "Employee details description", "update", e);
        }

        return "redirect:/showCurrentEmployeeDetails?empId=" + id;
    }
}
