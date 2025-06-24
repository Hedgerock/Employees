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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.SEARCH_EMPLOYEES_NAME;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.getCurrentValue;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initIdFinder;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersShowEmployeeDetails.validateDetails;

@Controller
public class UpdateSocialMediaController extends MyController {
    public UpdateSocialMediaController(
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

    @ModelAttribute
    private void addGlobalAttr(
            Model model
    ) {
        model.addAttribute("pagePath", "updateSocialMedia");
    }

    @GetMapping("/updateSocialMedia")
    public String updateSocialMedia(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "searchParams", required = false) String search,
            @RequestParam("empId") Long empId,
            @RequestParam("empDetId") Long empDetId,
            @RequestParam(value = "depId", required = false) Long depId,
            @RequestParam(value = "natId", required = false) Long natId,
            @RequestParam(value = "cityId", required = false) Long cityId
    ) {
        SetDefaultParameters.setSearch(SEARCH_EMPLOYEES_NAME, model, search);

        Employee employee = getCurrentValue(empId, "Employee", Employee.class, this.generalInfoService);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        String validation = validateDetails(employee, redirectAttributes, empDetId, depId, cityId, natId);
        if (!validation.equals(OK)) return validation;

        SocialMedia curSocialMedia = employee.getEmployeeDetails().getSocialMedia();

        SocialMedia socialMedia = curSocialMedia == null ? new SocialMedia() : curSocialMedia;

        initIdFinder(socialMedia, depId, cityId, natId);

        model.addAttribute("employee", employee);
        model.addAttribute("socialMedia", socialMedia);
        model.addAttribute("pageTitle", "Update social media page");

        return MAIN_VIEW;
    }

}
