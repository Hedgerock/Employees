package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
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
