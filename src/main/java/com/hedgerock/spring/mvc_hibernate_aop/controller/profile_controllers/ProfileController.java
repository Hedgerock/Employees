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
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.UserDetailsDTO;
import jakarta.servlet.ServletContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;

@Controller
public class ProfileController extends HeadProfileController {
    private static final String ATTR_TITLE = "contentPage";

    public ProfileController(
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

    @GetMapping("/profile")
    public String mainProfile(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return MAIN_VIEW;
    }

    @GetMapping("/admins")
    public String admins(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Pageable pageable = SetDefaultParameters.initPageable(model);
        Page<User> users = this.generalInfoService.getCurrentEntitiesPage(User.class, "User", pageable);

        model.addAttribute(ATTR_TITLE, "admins");

        String result = SetDefaultParameters
                .initPagination(pageable, redirectAttributes, users, model, "/admins");


        model.addAttribute("users", users.getContent());

        return result.equals(OK) ? MAIN_VIEW : result;
    }

    @GetMapping("/settings")
    public String settings(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("passwords", new ChangePasswordDTO());
        model.addAttribute("userDetails", new UserDetailsDTO());
        model.addAttribute(ATTR_TITLE, "settings");
        return MAIN_VIEW;
    }

}
