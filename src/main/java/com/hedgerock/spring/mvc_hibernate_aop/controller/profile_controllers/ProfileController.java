package com.hedgerock.spring.mvc_hibernate_aop.controller.profile_controllers;

import com.hedgerock.spring.mvc_hibernate_aop.controller.MyController;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters.SetDefaultParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.OK;

@Controller
public class ProfileController extends MyController {
    private static final String VIEW_PAGE = "profiles/my-profile-view";
    private static final String ATTR_TITLE = "contentPage";


    @GetMapping("/profile")
    public String mainProfile(
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        return VIEW_PAGE;
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

        return result.equals(OK) ? VIEW_PAGE : result;
    }

    @GetMapping("/settings")
    public String settings(
            Model model
    ) {
        model.addAttribute(ATTR_TITLE, "settings");
        return VIEW_PAGE;
    }

}
