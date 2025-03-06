package com.hedgerock.spring.mvc_hibernate_aop.utils.attributes;

import java.util.List;

public class ShowPlacesAttributes <T> {
    private List<T> currentPlaces;
    private String pageName = "citiesPage";
    private String addHref = "addNewCity";
    private String buttonContent = "Add new City";
    private String placeTitle = "cityId";
    private String title = "Cities page";
    private final boolean hasSingleValue = false;


    public ShowPlacesAttributes() {
    }

    public List<T> getCurrentPlaces() {
        return currentPlaces;
    }

    public void setCurrentPlaces(List<T> currentPlaces) {
        this.currentPlaces = currentPlaces;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getAddHref() {
        return addHref;
    }

    public void setAddHref(String addHref) {
        this.addHref = addHref;
    }

    public String getButtonContent() {
        return buttonContent;
    }

    public void setButtonContent(String buttonContent) {
        this.buttonContent = buttonContent;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public boolean isHasSingleValue() {
        return hasSingleValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
