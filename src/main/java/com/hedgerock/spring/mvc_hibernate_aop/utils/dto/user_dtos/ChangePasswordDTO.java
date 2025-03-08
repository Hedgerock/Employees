package com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos;

import com.hedgerock.spring.mvc_hibernate_aop.utils.validation.FieldMatch;
import jakarta.validation.constraints.NotEmpty;

@FieldMatch(first = "password", second = "repeatPassword")
public class ChangePasswordDTO {

    @NotEmpty
    private String password;

    @NotEmpty
    private String repeatPassword;

    public ChangePasswordDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
