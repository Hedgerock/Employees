package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.CURRENT_EMPLOYEE_ATTRIBUTE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
@RequestMapping("/saveCurrentEmployee")
public class SaveCurrentEmployeeController extends MyController {
    public static final String EMPLOYEE_ENTITY_TITLE = "Employee";

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
