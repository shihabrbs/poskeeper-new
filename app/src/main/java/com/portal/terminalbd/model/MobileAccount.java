package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class MobileAccount extends RealmObject {

    @SerializedName("global_id")
    @Expose
    private int globalId;
    @SerializedName("item_id")
    @Expose
    private int itemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("service_charge")
    @Expose
    private String service_charge;


    public int getGlobalId() {
        return globalId;
    }

    public void setGlobalId(int globalId) {
        this.globalId = globalId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService_charge() {
        return service_charge;
    }

    public void setService_charge(String service_charge) {
        this.service_charge = service_charge;
    }
}
