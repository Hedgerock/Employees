package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.SEARCH_EMPLOYEES_NAME;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.checkPicture;

@Controller
public class UpdateImageController extends MyController {

    @GetMapping("/update")
    public String updateImage(
            @RequestParam(value = "searchParams", required = false) String search,
            @RequestParam("empId") Long empId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Employee employee = getCurrentValue(empId, "Employee", Employee.class, this.generalInfoService);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        checkPicture(employee.getEmployeeDetails(), this.servletContext, model);

        model.addAttribute("employee", employee);
        SetDefaultParameters.setSearch(SEARCH_EMPLOYEES_NAME, model, search);

        return "employee_details/update-image-view";
    }

}
