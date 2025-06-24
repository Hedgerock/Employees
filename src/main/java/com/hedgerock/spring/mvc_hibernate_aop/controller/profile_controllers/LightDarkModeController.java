package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
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
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LightDarkModeController extends HeadProfileController {
    public LightDarkModeController(
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

    @RequestMapping("/darkMode")
    public String initDarkMode(
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {

        return initMode(model, redirectAttributes, true);
    }

    @RequestMapping("/lightMode")
    public String initLightMode(
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {
        return initMode(model, redirectAttributes, false);
    }


    private String initMode(
            Model model,
            RedirectAttributes redirectAttributes,
            Boolean trueDarkFalseLight
    ) {
        String mode = trueDarkFalseLight ? "Dark mode": "Light mode";

        try {
            final User user = (User) model.getAttribute("authorizedUser");

            if (user != null) {
                user.setThemeMode(trueDarkFalseLight);
                this.generalInfoService.saveCurrentEntity(user);
            }

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, mode, "changed");
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, mode, "change", e);
        }

        return "redirect:/profile";
    }
}
