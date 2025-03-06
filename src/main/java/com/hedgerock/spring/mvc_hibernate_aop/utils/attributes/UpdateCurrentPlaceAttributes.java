package com.hedgerock.spring.mvc_hibernate_aop.utils.attributes;

public class UpdateCurrentPlaceAttributes<T> {
    private T currentPlace;
    private String updateAction;
    private String mainAction;
    private String attrName;
    private String attrPath;

    public UpdateCurrentPlaceAttributes() {
    }

    public T getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(T currentPlace) {
        this.currentPlace = currentPlace;
    }

    public String getUpdateAction() {
        return updateAction;
    }

    public String getAttrPath() {
        return attrPath;
    }

    public void setAttrPath(String attrPath) {
        this.attrPath = attrPath;
    }

    public void setUpdateAction(String updateAction) {
        this.updateAction = updateAction;
    }

    public String getMainAction() {
        return mainAction;
    }

    public void setMainAction(String mainAction) {
        this.mainAction = mainAction;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
}
