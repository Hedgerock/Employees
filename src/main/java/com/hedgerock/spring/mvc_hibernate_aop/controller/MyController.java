package com.hedgerock.spring.mvc_hibernate_aop.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.DEFAULT_PAGE_SIZE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.SEARCH_EMPLOYEES_NAME;

@Controller
public abstract class MyController {
    protected final PasswordEncoder passwordEncoder;
    protected final EmployeeService employeeService;
    protected final DepartmentService departmentService;
    protected final EmployeeDetailsService employeeDetailsService;
    protected final HistoryService historyService;
    protected final CityService cityService;
    protected final GeneralInfoService generalInfoService;
    protected final NationalityService nationalityService;
    protected final UserService userService;
    protected final PictureService pictureService;
    protected final EmailService emailDetailsService;
    protected final PhoneService phoneNumberDetailsService;
    protected final ServletContext servletContext;

    public MyController(
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
        this.passwordEncoder = passwordEncoder;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.employeeDetailsService = employeeDetailsService;
        this.historyService = historyService;
        this.cityService = cityService;
        this.generalInfoService = generalInfoService;
        this.nationalityService = nationalityService;
        this.userService = userService;
        this.pictureService = pictureService;
        this.emailDetailsService = emailDetailsService;
        this.phoneNumberDetailsService = phoneNumberDetailsService;
        this.servletContext = servletContext;
    }

    protected final List<String> allowedTypes = Arrays.asList(
            "image/png", "image/jpeg", "image/jpg", "image/webp");

    protected static final String MAIN_VIEW = "skeleton-view";

    @ModelAttribute
    protected void addGlobalAttributes(
            Model model,
            @RequestParam(value = "searchParams", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        String username = (String) request.getAttribute("user");

        Optional<User> user = this.userService.getUser(username);

        if (user.isPresent()) {
            User currentUser = user.get();
            model.addAttribute("mode", currentUser.getThemeMode());
            model.addAttribute("authorizedUser", currentUser);
        }

        SetDefaultParameters.setSearch(SEARCH_EMPLOYEES_NAME, model, search);
        model.addAttribute("pageable", pageable);
        model.addAttribute("searchParams", search);
    }

    protected<T> String  initOperator(T entity, Model model) {
        User user = (User) model.getAttribute("authorizedUser");

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        try {
            Method method = entity.getClass().getDeclaredMethod("setLastOperator", String.class);
            method.invoke(entity, user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Failed to set operator: " + e.getMessage());
        }

        return user.getUsername();
    }
}
