package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.GeneralInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.ALL_EMPLOYEES_VIEW;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
@RequestMapping("/")
public class ShowAllEmployeesController extends MyController {

    @GetMapping
    public String showAllEmployees(
            RedirectAttributes redirectAttributes,
            final Model model) {
        final String result = initAllEmployees(model, "/", redirectAttributes, false);

        if (!result.equals(OK)) return result;

        final GeneralInfo generalInfo = this.generalInfoService.getGeneralInfo();
        model.addAttribute("generalInfo", generalInfo);

        return ALL_EMPLOYEES_VIEW;
    }

    @GetMapping("firedEmployees")
    public String showFiredEmployees(
            RedirectAttributes redirectAttributes,
            final Model model
    ) {
        final String result = initAllEmployees(model, "/firedEmployees", redirectAttributes, true);

        model.addAttribute("altAction", "/firedEmployees");
        return result.equals(OK) ? ALL_EMPLOYEES_VIEW : result;
    }

    private String initAllEmployees(
            Model model,
            String path,
            RedirectAttributes redirectAttributes,
            boolean isLookingForFired
    ) {
        final Pageable pageable = initPageable(model);
        final String search = initSearch(model);

        final Page<Employee> employees = SetDefaultParameters.getEmployees(this.employeeService, search, pageable,
                isLookingForFired);

        String status = initPagination(pageable, redirectAttributes, employees, model, path);
        if (!status.equals(OK)) return status;

        model.addAttribute(ALL_EMPLOYEES_ATTRIBUTE, employees.getContent());

        return OK;
    }
}
