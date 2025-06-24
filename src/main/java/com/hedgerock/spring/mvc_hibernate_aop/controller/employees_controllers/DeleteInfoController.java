package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
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
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
public class DeleteInfoController extends MyController {
    public DeleteInfoController(
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

    @RequestMapping("/deleteInfo")
    public String deleteEmployee(
            @RequestParam("empForDelId") Long id,
            @RequestParam(value = "pageVal", required = false) String pageName,
            @RequestParam(value = "curPageNum", required = false, defaultValue = "0") Integer curPageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = Attributes.DEFAULT_PAGE_SIZE) Integer pageCapacity,
            @RequestParam(value = "depId", required = false) Long depId,
            @RequestParam(value = "cityId", required = false) Long cityId,
            @RequestParam(value = "natId", required = false) Long natId,
            RedirectAttributes redirectAttributes
    ) {
        Integer page = getCurrentNum(curPageNum, pageCapacity);

        Employee employee = getCurrentValue(id, "Employee", Employee.class, this.generalInfoService);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        String entity = employee.getFirstName();

        initDelete(employee, redirectAttributes, entity);

        return getCurrentRedirect(pageName, depId, cityId, natId, page);
    }


    private void initDelete(Employee employee, RedirectAttributes redirectAttributes, String entity) {
        try {
            employee.setFireDate(LocalDate.now());
            Department department = employee.getDepartment();
            City city = employee.getCity();
            Nationality nationality = employee.getNationality();

            Long dId = Optional.ofNullable(department)
                    .map(Department::getId)
                    .orElse(null);
            Long cId = Optional.ofNullable(city)
                    .map(City::getId)
                    .orElse(null);
            Long nId = Optional.ofNullable(nationality)
                    .map(Nationality::getId)
                    .orElse(null);

            this.employeeService.saveCurrentEmployee(employee, dId, cId, nId);

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, entity, "deleted");
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entity, "delete", e);
        }
    }
}
