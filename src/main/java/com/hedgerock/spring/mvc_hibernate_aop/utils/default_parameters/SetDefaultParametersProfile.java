package com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Authority;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.service.general_info_service.GeneralInfoService;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos.AddUserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

public class SetDefaultParametersProfile extends SetDefaultParameters {

    public static User initUser(AddUserDTO userDTO, PasswordEncoder passwordEncoder) {
        User user = new User();
        String userName = userDTO.getUsername();
        String password = passwordEncoder.encode(userDTO.getPassword());
        String authority = userDTO.getAuthority();
        Boolean enabled = userDTO.getEnabled();
        Boolean themeMode = userDTO.getThemeMode();

        user.setUsername(userName);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setThemeMode(themeMode);
        user.setCreationDate(LocalDate.now());

        Authority curAuthority = new Authority();
        curAuthority.setUsername(userName);
        curAuthority.setAuthority(authority);

        user.addAuthority(curAuthority);

        return user;
    }

    public static String initUserHandler(
            Model model,
            RedirectAttributes redirectAttributes,
            String username,
            boolean activation,
            GeneralInfoService generalInfoService
    ) {
        final User authorizedUser = (User) model.getAttribute("authorizedUser");
        String redirect = "redirect:/admins";

        if (authorizedUser == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "User details");
            return redirect;
        }

        String operation = activation ? "activate" : "deactivate";
        String operationExt = operation + "d";
        String entityName = "User";

        if (authorizedUser.getUsername().equals(username)) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entityName, operation,
                    new RuntimeException("You can't deactivate yourself"));

            return redirect;
        }

        try {
            Optional<User> user = generalInfoService.findCurrentEntity(username,
                    entityName, "username", User.class);

            if (user.isEmpty()) {
                initNotFoundFlashAttr(redirectAttributes, entityName);
                return redirect;
            }

            User currentUser = user.get();
            currentUser.setEnabled(activation);
            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, entityName, operationExt);
            generalInfoService.saveCurrentEntity(currentUser);
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entityName, operation, e);
        }

        return redirect;
    }

    private SetDefaultParametersProfile() {}
}
