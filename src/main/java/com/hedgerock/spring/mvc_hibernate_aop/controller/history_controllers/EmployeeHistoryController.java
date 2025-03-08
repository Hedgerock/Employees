package com.hedgerock.spring.mvc_hibernate_aop.controller.history_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.ShowCurrentPlaceAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.EmployeeRevisionDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
public class EmployeeHistoryController extends MyController {

    @ModelAttribute
    private void globalAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "history");
    }

    @GetMapping("/history")
    public String getHistory(
            @RequestParam("empId") Long empId,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        Pageable pageable = initPageable(model);

        Employee employee = getCurrentValue(empId, "Employee", Employee.class, this.generalInfoService);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        Page<EmployeeRevisionDTO> revisions = this.historyService
                .getEmployeeRevisions(empId, pageable,false);

        String status = initPagination(
                pageable, redirectAttributes, revisions, model, "/history");
        if (!status.equals(OK)) return status;

        ShowCurrentPlaceAttributes<Nationality> showPlacesAttributes = new ShowCurrentPlaceAttributes<>();
        showPlacesAttributes.setPlaceTitle("empId");
        showPlacesAttributes.setIdValue(empId);

        model.addAttribute("showAttributes", showPlacesAttributes);

        initAttributes(model, revisions.getContent(), empId, employee, false);

        return MAIN_VIEW;
    }

    @GetMapping("/showFiredEmployeeHistory")
    public String showFiredEmployeeHistory(
            @RequestParam("empId") Long empId,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        Pageable pageable = initPageable(model);

        Employee employee = getCurrentValue(empId, "Fired employee", Employee.class,
                this.generalInfoService, true);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Fired employee");
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        Page<EmployeeRevisionDTO> revisions = this.historyService.getEmployeeRevisions(empId, pageable,
                true);

        String status = initPagination(
                pageable, redirectAttributes, revisions, model, "/showFiredEmployeeHistory");
        if (!status.equals(OK)) return status;

        model.addAttribute("isFired", true);

        initAttributes(model, revisions.getContent(), empId, employee, true);

        return MAIN_VIEW;
    }

    private void initAttributes(
            Model model, List<?>content, Long id, Employee employee, boolean isFired
    ) {
        final String prefix = isFired ? "Fired" : "";

        model.addAttribute("revisions", content);
        model.addAttribute("id", id);
        model.addAttribute("title", String.format(
                prefix + " %s %s %s: history page",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getMiddleName() != null ? employee.getMiddleName() : "#")
        );
    }
}
