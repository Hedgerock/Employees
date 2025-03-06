package com.hedgerock.spring.mvc_hibernate_aop.controller.current_place_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParametersGeneralInfo.initDelete;

@Controller
@RequestMapping("/")
public class DeleteCurrentPlaceController extends MyController {

    @RequestMapping("deleteCity")
    public String deleteCity(
            RedirectAttributes redirectAttributes,
            @RequestParam("cityId") Long cityId
    ) {
        String status = initDelete(
                cityId, City.class, redirectAttributes, "City",
                "cityId", this.generalInfoService
        );

        return getStatus(status, "cities");
    }

    @RequestMapping("deleteCurrentDepartment")
    public String deleteCurrentDepartment(
            RedirectAttributes redirectAttributes,
            @RequestParam("depId") Long depId
    ) {
        String status = initDelete(
                depId, Department.class, redirectAttributes, "Department",
                "depId", this.generalInfoService
        );

        return getStatus(status, "departments");
    }

    @RequestMapping("deleteNationality")
    public String deleteNationality(
            @RequestParam("natId") Long natId,
            RedirectAttributes redirectAttributes
    ) {
        String status = initDelete(
                natId, Nationality.class, redirectAttributes, "Nationality",
                "nationalityId", this.generalInfoService
        );

        return getStatus(status, "nationalities");
    }

    private String getStatus(String status, String currentPlace) {
        return status.equals("ok") ? String.format("redirect:/%s", currentPlace) : status;
    }
}
