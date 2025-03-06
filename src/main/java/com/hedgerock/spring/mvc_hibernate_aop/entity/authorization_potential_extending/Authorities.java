package com.hedgerock.spring.mvc_hibernate_aop.entity.authorization_potential_extending;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//potential extending project
@Entity
@Table(name="authorities")
public class Authorities {

    @Column(name="username")
    private String username;

    @Column(name="authority")
    private String authority;

    public Authorities() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
