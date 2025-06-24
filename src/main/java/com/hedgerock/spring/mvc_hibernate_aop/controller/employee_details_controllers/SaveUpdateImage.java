package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersAddDetails.initPicture;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersAddDetails.saveFile;

@Controller
public class SaveUpdateImage extends MyController {
    public SaveUpdateImage(
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

    @PostMapping("/updateImage")
    public String updateImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("empDetId") Long empDetId,
            @RequestParam("empId") Long empId,
            RedirectAttributes redirectAttributes
    ) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message","File cannot be empty");
            return "redirect:/update?empId=" + empId;
        }

        if(!allowedTypes.contains(file.getContentType())) {
            redirectAttributes.addFlashAttribute("message",
                    "Wrong type should be a picture with type png, jpg, jpeg or webp");
            return "redirect:/update?empId=" + empId;
        }

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Employee employee = getCurrentValue(empId, "Employee", Employee.class, this.generalInfoService);

            if (employee == null) {
                SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
                return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
            }

            EmployeeDetails employeeDetails = employee.getEmployeeDetails();

            Picture picture = initPicture(employeeDetails);

            picture.setPictureSrc(fileName);
            employeeDetails.setPicture(picture);

            this.employeeDetailsService.saveEmployeeDetails(employeeDetails);

            saveFile(file, fileName, this.servletContext);
            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, "File", "uploaded");
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, "file", "upload", e);
        }

        return "redirect:/showCurrentEmployeeDetails?empId=" + empId;
    }
}
