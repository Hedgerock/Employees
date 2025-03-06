package com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors;

import java.util.ArrayList;
import java.util.List;

public class IdCollector {

    private List<Long> idCollection;

    public IdCollector() {
        this.idCollection = new ArrayList<>();
    }

    public List<Long> getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(List<Long> idCollection) {
        this.idCollection = idCollection;
    }
}
