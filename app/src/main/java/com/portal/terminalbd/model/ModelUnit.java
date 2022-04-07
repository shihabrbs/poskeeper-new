package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelUnit {
    @SerializedName("unit_id")
    @Expose
    private Integer unitId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
