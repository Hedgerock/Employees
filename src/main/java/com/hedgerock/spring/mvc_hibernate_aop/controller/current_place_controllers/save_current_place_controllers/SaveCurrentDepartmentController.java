package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.save_current_place_controllers;


import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/saveCurrentDepartment")
public class SaveCurrentDepartmentController extends SetupController {
    private static final String ENTITY_TITLE = "Department";
    private static final String ENTITY_ID_TITLE = "depId";
    
    @PostMapping
    public String addCurrentDepartment(
            @ModelAttribute("currentDepartment") Department department,
            @ModelAttribute("idCollector")IdCollector idCollector,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        String operator = initOperator(department, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, department, this.departmentService,
                "redirect:/addNewDepartment", idCollector, redirectAttributes, Department.class, true, operator);
    }

    @PostMapping("/{depId}")
    public String saveCurrentDepartment(
            @ModelAttribute("currentDepartment") Department department,
            Model model,
            @ModelAttribute("idCollector")IdCollector idCollector,
            RedirectAttributes redirectAttributes) {
        String redirect = "redirect:/updateCurrentDepartment?depId=" + department.getId();
        String operator = initOperator(department, model);
        return initCurrentOperation(ENTITY_TITLE, ENTITY_ID_TITLE, department, this.departmentService,
                redirect, idCollector, redirectAttributes, Department.class, false, operator);
    }

}
