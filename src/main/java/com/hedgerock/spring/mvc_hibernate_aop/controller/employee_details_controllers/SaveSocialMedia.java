package com.hedgerock.spring.mvc_hibernate_aop.controller.employee_details_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.social_media.SocialMedia;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
public class SaveSocialMedia extends MyController {
    public SaveSocialMedia(
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

    @PostMapping("/saveSocialMedia")
    public String updateSocialMedia(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("socialMedia") SocialMedia socialMedia,
            BindingResult bindingResult,
            @RequestParam("empId") Long empId,
            @RequestParam("empDetId")Long empDetId
    ) {
        if (bindingResult.hasErrors()) {
            String redirect = String.format("redirect:/updateSocialMedia?empId=%d&empDetId=%d", empId, empDetId);
            return initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        try {
            Employee employee = getCurrentValue(empId, "Employee", Employee.class, this.generalInfoService);

            if (employee == null) {
                SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
                return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
            }

            this.generalInfoService.saveCurrentEntity(socialMedia);

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, "Social media", "updated");

            return getActualRedirect(
                    socialMedia.getIdFinder(), empId
            );
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, "Social media", "update", e);
            return String.format("redirect:/updateSocialMedia?empId=%d&empDetId=%d", empId, empDetId);
        }
    }

}