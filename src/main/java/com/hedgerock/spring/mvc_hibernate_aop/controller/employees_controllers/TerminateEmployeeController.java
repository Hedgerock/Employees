package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;
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
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.RELATIVE_PATH;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;

@Controller
public class TerminateEmployeeController extends MyController {
    public TerminateEmployeeController(
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

    @RequestMapping("/terminateEmployeeData")
    public String terminateEmployee(
            RedirectAttributes redirectAttributes,
            @RequestParam("empId") Long id
    ) {
        try {
            Employee employee = getCurrentValue(id, "Employee", Employee.class, this.generalInfoService,
                    true);

            if (employee == null) {
                SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Fired employee");
                return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
            }

            List<String>images = new ArrayList<>();

            Picture picture = employee.getEmployeeDetails().getPicture();

            if (picture != null) {
                images.add(picture.getPictureSrc());
            }

            if (picture != null && picture.getPictures() != null) {
                picture.getPictures().forEach(el -> images.add(el.getPictureSrc()));
            }

            images.forEach(image -> {
                try {
                    String relativePath = RELATIVE_PATH + image;
                    String absolutePath = this.servletContext.getRealPath(relativePath);
                    Path path = Paths.get(absolutePath);
                    Files.deleteIfExists(path);
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
            });

            this.employeeService.deleteEmployee(employee);

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, "Fired employee", "terminated");
            return Redirects.REDIRECT_TO_THE_MAIN_PAGE;
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, "Fired employee", "terminate", e);
            return "redirect:/showFiredEmployeeDetails?empId=" + id;
        }
    }
}
