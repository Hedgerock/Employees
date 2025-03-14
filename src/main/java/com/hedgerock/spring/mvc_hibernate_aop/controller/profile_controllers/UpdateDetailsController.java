package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.entity.UserDetails;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.UserDetailsDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters.initBindingErrors;

@Controller
public class UpdateDetailsController extends HeadProfileController {

    @RequestMapping("/saveUserDetails")
    public String initUpdateDetails(
            RedirectAttributes redirectAttributes,
            Model model,
            @Valid  @ModelAttribute("userDetails")UserDetailsDTO userDetailsDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String redirect = "redirect:/addNewEmployee";
            return initBindingErrors(bindingResult, redirectAttributes, redirect);
        }

        User user = (User) model.getAttribute("authorizedUser");

        if (user == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "User");
            return "redirect:/settings";
        }

        UserDetails userDetails = user.getUserDetails();

        if (userDetails == null) {
            userDetails = new UserDetails();
        }

        userDetails.initDtoContent(userDetailsDTO);

        user.setUserDetails(userDetails);

        this.generalInfoService.saveCurrentEntity(user);
        SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, "User details", "updated");
        return "redirect:/settings";
    }

}
