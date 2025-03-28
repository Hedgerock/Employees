package com.hedgerock.spring.mvc_hibernate_aop.entity;

import jakarta.persistence.*;

@Entity
@Table(name="authorities")
public class Authority {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", insertable = false, updatable = false)
    private String username;

    @Column(name="authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name="username")
    private User user;

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
