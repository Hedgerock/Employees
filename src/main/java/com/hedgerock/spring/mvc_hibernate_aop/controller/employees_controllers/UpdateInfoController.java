package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
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
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;

@Controller
public class UpdateInfoController extends HeadUpdateInfoController {
    public UpdateInfoController(
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

    @GetMapping("/updateInfo")
    public String updateEmployee(
            @RequestParam("empId") Long id,
            @RequestParam(value = "depId", required = false) Long depId,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        final String TEMPLATE_OF_PAGE_TITLE = "%s: Edit page for employee %s";
        final Employee employee = getCurrentValue(id, "Employee", Employee.class, this.generalInfoService);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        final String departmentName = getDepartmentName(employee);

        final List<Department> departmentList = this.generalInfoService
                .getCurrentEntitiesList(Department.class, "Department");
        final String title = String.format(
                TEMPLATE_OF_PAGE_TITLE,
                departmentName,
                employee.getFirstName() + " " + employee.getLastName()
        );

        final List<City>cities =this.generalInfoService
                .getCurrentEntitiesList(City.class, "City");

        final List<Nationality>nationalities = this.generalInfoService
                .getCurrentEntitiesList(Nationality.class, "Nationality");

        SetDefaultParameters.initModel(
                model, employee, departmentList, cities, nationalities, title
        );
        employee.setCurrentDepartmentId(depId);

        Optional<EmployeeDetails> employeeDetails = this.generalInfoService
                .findCurrentEntity(employee.getEmployeeDetailsId(), "EmployeeDetails", EmployeeDetails.class);
        employeeDetails.ifPresent(det -> model.addAttribute("employeeDetails", det));
        model.addAttribute("action", "saveCurrentEmployee/" + employee.getId());

        return MAIN_VIEW;
    }

    private String getDepartmentName(Employee employee) {
        return employee.getDepartment() == null
                ? "Without department"
                : employee.getDepartment().getName();
    }
}
