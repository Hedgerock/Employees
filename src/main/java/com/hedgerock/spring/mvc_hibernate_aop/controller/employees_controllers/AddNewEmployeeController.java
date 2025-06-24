package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
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
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.SelectionCollector;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.DEFAULT_MIN_SALARY;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentList;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initIdFinder;

@Controller
public class AddNewEmployeeController extends HeadUpdateInfoController {
    public AddNewEmployeeController(
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

    @GetMapping("/addNewEmployee")
    public String addNewEmployee(
            @RequestParam(value = "depId", required = false) Long depId,
            @RequestParam(value = "natId", required = false) Long natId,
            @RequestParam(value = "cityId", required = false) Long cityId,
            final Model model
    ) {

        final Employee employee = new Employee();
        employee.setCurrentDepartmentId(depId);

        List<Department> departmentList = getCurrentList(
                depId,
                this.departmentService,
                "getAllDepartments",
                "findCurrentDepartmentById",
                Department.class
        );

        List<City> cities = getCurrentList(
                cityId,
                this.cityService,
                "getCities",
                "findCity",
                City.class
        );

        List<Nationality> nationalities = getCurrentList(
                natId,
                this.nationalityService,
                "getNationalities",
                "findNationality",
                Nationality.class
        );

        employee.setSalary(DEFAULT_MIN_SALARY);
        employee.setHireDate(LocalDate.now());

        model.addAttribute("selectionCollector", new SelectionCollector());
        initIdFinder(employee, depId, cityId, natId);

        final String title = "Adding new employee page";

        SetDefaultParameters.initModel(
                model,
                employee,
                departmentList,
                cities,
                nationalities,
                title
        );

        return MAIN_VIEW;
    }
}
