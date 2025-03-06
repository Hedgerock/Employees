package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;

@Controller
public class ReturnEmployeeController extends MyController {

    @RequestMapping("/returnEmployee")
    public String returnEmployee(
        @RequestParam("empId") Long id,
        RedirectAttributes redirectAttributes
    ) {
        try {
            Employee employee = getCurrentValue(id, "Employee", Employee.class, this.generalInfoService,
                    true);

            if (employee == null) {
                SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Fired Employee");
                return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
            }

            employee.setFireDate(null);
            employee.setHireDate(LocalDate.now());

            Employee savedEmployee = this.employeeService.saveCurrentEmployee(
                    employee,employee.getDepId(), employee.getCityId(), employee.getNationalityId());

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, "Employee " + savedEmployee.getFirstName(),
                    "returned");

            return "redirect:/showCurrentEmployeeDetails?empId=" + savedEmployee.getId();
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, "Fired Employee", "return", e);
            return "redirect:/showFiredEmployeeDetails?empId" + id;
        }
    }

}
