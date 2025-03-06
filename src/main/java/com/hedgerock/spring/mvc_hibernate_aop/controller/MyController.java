package com.hedgerock.spring.mvc_hibernate_aop.controller;

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
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Views;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.DEFAULT_PAGE_SIZE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.SEARCH_EMPLOYEES_NAME;

@Controller
public abstract class MyController {

    @Autowired
    protected EmployeeService employeeService;

    @Autowired
    protected DepartmentService departmentService;

    @Autowired
    protected EmployeeDetailsService employeeDetailsService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected CityService cityService;

    @Autowired
    protected GeneralInfoService generalInfoService;

    @Autowired
    protected NationalityService nationalityService;

    @Autowired
    protected PictureService pictureService;

    @Autowired
    protected EmailService emailDetailsService;

    @Autowired
    protected PhoneService phoneNumberDetailsService;

    @Autowired
    protected ServletContext servletContext;

    protected final List<String> allowedTypes = Arrays.asList(
            "image/png", "image/jpeg", "image/jpg", "image/webp");


    @ModelAttribute
    protected void addGlobalAttributes(
            Model model,
            @RequestParam(value = "searchParams", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        SetDefaultParameters.setSearch(SEARCH_EMPLOYEES_NAME, model, search);
        model.addAttribute("pageable", pageable);
        model.addAttribute("searchParams", search);
    }

}
