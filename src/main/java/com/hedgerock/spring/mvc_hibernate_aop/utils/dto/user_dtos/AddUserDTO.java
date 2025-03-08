package com.hedgerock.spring.mvc_hibernate_aop.utils.dto.user_dtos;

import com.hedgerock.spring.mvc_hibernate_aop.utils.validation.FieldMatch;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@FieldMatch(first = "password", second = "repeatPassword")
public class AddUserDTO {

    @NotEmpty(message = "Username can't be empty")
    private String username;

    @NotEmpty(message = "Password can't be empty")
    private String password;

    @NotEmpty(message = "Password can't be empty")
    private String repeatPassword;

    private String authority;
    private LocalDate creationDate;
    private final Boolean enabled;
    private final Boolean themeMode;


    public AddUserDTO() {
        this.enabled = true;
        this.themeMode = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getThemeMode() {
        return themeMode;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
