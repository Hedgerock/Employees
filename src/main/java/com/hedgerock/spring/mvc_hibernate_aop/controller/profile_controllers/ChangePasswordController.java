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
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.ChangePasswordDTO;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initBindingErrors;

@Controller
public class ChangePasswordController extends HeadProfileController {

    public ChangePasswordController(
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

    @RequestMapping("/changePassword")
    public String changePassword(
            @Valid  @ModelAttribute("passwords")ChangePasswordDTO changePasswordDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        String redirect = "redirect:/settings";

        if (bindingResult.hasErrors()) {
            return initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        User user = (User) model.getAttribute("authorizedUser");

        if (user == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "User");
            return redirect;
        }

        String newPassword = changePasswordDTO.getPassword();
        String username = user.getUsername();

        try {
            String encodedPassword = user.getPassword();

            if (this.passwordEncoder.matches(newPassword, encodedPassword)) {
                SetDefaultParameters.initFailedFlashAttr(redirectAttributes, "User", "change password",
                        new RuntimeException("Password can't be previous"));

                return redirect;
            }

            user.setPassword(this.passwordEncoder.encode(newPassword));
            this.generalInfoService.saveCurrentEntity(user);

            SetDefaultParameters
                    .initSuccessFlashAttr(redirectAttributes, "Password " + username, "changed");

        } catch (Exception e) {
            SetDefaultParameters
                    .initFailedFlashAttr(redirectAttributes, "Password " + username, "change", e);
        }

        return redirect;
    }
}
