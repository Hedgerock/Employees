package com.hedgerock.spring.mvc_hibernate_aop.utils.attributes;

import java.util.List;

public class ShowCurrentPlaceAttributes<T> {
    private List<T> currentPlaces;
    private String pageTitle;
    private Long idValue;
    private String placeTitle;
    private String title;
    private String page;
    private final boolean hasSingleValue = true;

    public ShowCurrentPlaceAttributes() {
    }

    public List<T> getCurrentPlaces() {
        return currentPlaces;
    }

    public void setCurrentPlaces(List<T> currentPlaces) {
        this.currentPlaces = currentPlaces;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Long getIdValue() {
        return idValue;
    }

    public void setIdValue(Long idValue) {
        this.idValue = idValue;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public boolean isHasSingleValue() {
        return hasSingleValue;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
