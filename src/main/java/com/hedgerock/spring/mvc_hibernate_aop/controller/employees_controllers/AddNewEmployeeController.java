package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.SelectionCollector;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.DEFAULT_MIN_SALARY;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
public class AddNewEmployeeController extends MyController {

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

        return CURRENT_EMPLOYEE_DETAILS_VIEW;
    }
}
