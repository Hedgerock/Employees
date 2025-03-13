package com.hedgerock.spring.mvc_hibernate_aop.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private Boolean enabled;

    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Authority> authorities;

    @Column(name="users_details_id", insertable = false, updatable = false)
    private Long userDetailsId;

    @OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JoinColumn(name = "users_details_id")
    private UserDetails userDetails;

    @Column(name="creation_date")
    private LocalDate creationDate;

    @Column(name="theme_mode")
    private Boolean themeMode;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAuthority(
            Authority authority
    ) {
        if (this.authorities == null) {
            this.authorities = new ArrayList<>();
        }

        this.authorities.add(authority);
        authority.setUser(this);
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Boolean getThemeMode() {
        return themeMode;
    }

    public void setThemeMode(Boolean themeMode) {
        this.themeMode = themeMode;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(Long userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
