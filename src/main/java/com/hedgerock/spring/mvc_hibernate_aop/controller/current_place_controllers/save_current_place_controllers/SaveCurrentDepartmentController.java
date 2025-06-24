package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers.save_current_place_controllers;


import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.service.city_service.CityService;
import com.hedgerock.spring.mvc_hibernate_aop.service.department_service.DepartmentService;
import com.hedgerock.spring.mvc_hibernate_aop.service.email_service.EmailService;
import com.hedgerock.spring.mvc_hibernate_aop.service.employee_details_service.EmployeeDetailsService;
import com.hedgerock.spring.mvc_hibernate_aop.service.employee_service.EmployeeService;
import com.hedgerock.spring.mvc_hibernate_aop.service.general_info_service.GeneralInfoService;
import com.hedgerock.spring.mvc_hibernate_aop.service.histrory_service.HistoryService;
import com.hedgerock.spring.mvc_hibernate_aop.service.nationality_service.NationalityService;
import com.hedgerock.spring.mvc_hibernate_aop.service.phoneService.PhoneService;
import com.hedgerock.spring.mvc_hibernate_aop.service.picture_service.PictureService;
import com.hedgerock.spring.mvc_hibernate_aop.service.user_service.UserService;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public SaveCurrentDepartmentController(
            PasswordEncoder passwordEncoder,
            EmployeeService employeeService,
            DepartmentService departmentService,
            EmployeeDetailsService employeeDetailsService,
            HistoryService historyService,
            CityService cityService,
            GeneralInfoService generalInfoService,
            NationalityService nationalityService,
            UserService userService,
            PictureService pictureService,
            EmailService emailDetailsService,
            PhoneService phoneNumberDetailsService,
            ServletContext servletContext
    ) {
        super(passwordEncoder, employeeService, departmentService, employeeDetailsService, historyService, cityService,
                generalInfoService, nationalityService, userService, pictureService, emailDetailsService,
                phoneNumberDetailsService, servletContext
        );
    }

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
