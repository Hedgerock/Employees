package com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Objects;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.*;

@Controller
public class DeleteInfoController extends MyController {

    @RequestMapping("/deleteInfo")
    public String deleteEmployee(
            @RequestParam("empForDelId") Long id,
            @RequestParam(value = "pageVal", required = false) String pageName,
            @RequestParam(value = "curPageNum", required = false, defaultValue = "0") Integer curPageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = Attributes.DEFAULT_PAGE_SIZE) Integer pageCapacity,
            @RequestParam(value = "depId", required = false) Long depId,
            @RequestParam(value = "cityId", required = false) Long cityId,
            @RequestParam(value = "natId", required = false) Long natId,
            RedirectAttributes redirectAttributes
    ) {
        Integer page = getCurrentNum(curPageNum, pageCapacity);

        Employee employee = getCurrentValue(id, "Employee", Employee.class, this.generalInfoService);

        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
        }

        String entity = employee.getFirstName();

        try {
            employee.setFireDate(LocalDate.now());
            Department department = employee.getDepartment();
            City city = employee.getCity();
            Nationality nationality = employee.getNationality();

            Long dId = Objects.requireNonNullElse(department.getId(), null);
            Long cId = Objects.requireNonNullElse(city.getId(), null);
            Long nId = Objects.requireNonNullElse(nationality.getId(), null);

            this.employeeService.saveCurrentEmployee(employee, dId, cId, nId);

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, entity, "deleted");
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entity, "delete", e);
        }

        return getCurrentRedirect(pageName, depId, cityId, natId, page);
    }

}
