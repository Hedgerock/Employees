package com.hedgerock.spring.mvc_hibernate_aop.controller.history_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.ALL_EMPLOYEES_ATTRIBUTE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initPageable;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initPagination;

@Controller
public class HistoryListController extends MyController {

    @ModelAttribute
    private void globalAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "historyList");
    }

    @GetMapping("/historyList")
    public String getHistoryList(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        final Pageable pageable = initPageable(model);
        final Page<EmployeeDTO> employeeList = this.historyService.getEmployeesWithHistory(pageable);

        final String status = initPagination(
                pageable, redirectAttributes, employeeList, model, "/historyList");
        if (!status.equals(OK)) return status;

        model.addAttribute(ALL_EMPLOYEES_ATTRIBUTE, employeeList.getContent());

        return MAIN_VIEW;
    }

}
