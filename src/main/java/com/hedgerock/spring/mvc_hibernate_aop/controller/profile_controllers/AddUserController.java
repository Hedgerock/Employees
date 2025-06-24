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
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.AddUserDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.enums.Roles;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initBindingErrors;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersProfile.initUser;

@Controller
public class AddUserController extends HeadProfileController {
    private static final String ATTR_TITLE = "contentPage";

    public AddUserController(
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

    @GetMapping("/addUser")
    public String addUser(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Map<String, String> roles = new LinkedHashMap<>();

        for(Object el: Roles.values()) {
            roles.put(el.toString(), el.toString());
        }

        model.addAttribute("roles", roles);
        model.addAttribute(ATTR_TITLE, "add-user");
        model.addAttribute("newUser", new AddUserDTO());
        return MAIN_VIEW;
    }

    @PostMapping("/saveUser")
    public String saveUser(
            @Valid @ModelAttribute("newUser") AddUserDTO user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        final String errorRedirect = "redirect:/addUser";
        final String successRedirect = "redirect:/admins";
        final String entityName = "User";
        final String operation = "create";
        final String operationExt = operation + "d";

        if (bindingResult.hasErrors()) {
            return initBindingErrors(bindingResult, redirectAttributes, errorRedirect);
        }

        String result = validateUser(user.getUsername(), redirectAttributes, errorRedirect);
        if (!result.equals(Attributes.OK)) return result;

        final User curUser = initUser(user, this.passwordEncoder);

        try {
            this.generalInfoService.saveCurrentEntity(curUser);
            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, entityName, operationExt);

            return successRedirect;
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entityName, operation, e);
            return errorRedirect;
        }

    }

}
