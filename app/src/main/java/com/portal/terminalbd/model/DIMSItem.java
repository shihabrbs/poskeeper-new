package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class DIMSItem extends RealmObject {

    @SerializedName("global_id")
    @Expose
    private int globalId;
    @SerializedName("medicineId")
    @Expose
    private int medicineId;
    @SerializedName("medicineName")
    @Expose
    private String medicineName;
    @SerializedName("salesPrice")
    @Expose
    private String salesPrice;
    @SerializedName("medicineForm")
    @Expose
    private String medicineForm;
    @SerializedName("strength")
    @Expose
    private String strength;
    @SerializedName("genericName")
    @Expose
    private String genericName;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("useFor")
    @Expose
    private String useFor;

    public int getGlobalId() {
        return globalId;
    }

    public void setGlobalId(int globalId) {
        this.globalId = globalId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getMedicineForm() {
        return medicineForm;
    }

    public void setMedicineForm(String medicineForm) {
        this.medicineForm = medicineForm;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }
}
