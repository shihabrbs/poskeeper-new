package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Setup extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    @SerializedName("setupId")
    @Expose
    private int setupId;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("uniqueCode")
    @Expose
    private String uniqueCode;
    @SerializedName("apiSecret")
    @Expose
    private String apiSecret;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("locationId")
    @Expose
    private int locationId;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("main_app")
    @Expose
    private int mainApp;
    @SerializedName("main_app_name")
    @Expose
    private String mainAppName;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("otp")
    @Expose
    private String otp;


    @SerializedName("appsManual")
    @Expose
    private String appsManual;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("vatRegNo")
    @Expose
    private String vatRegNo;
    @SerializedName("vatPercentage")
    @Expose
    private String vatPercentage;
    @SerializedName("vatEnable")
    @Expose
    private boolean vatEnable;

    public int getSetupId() {
        return setupId;
    }

    public void setSetupId(int setupId) {
        this.setupId = setupId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getMainApp() {
        return mainApp;
    }

    public void setMainApp(int mainApp) {
        this.mainApp = mainApp;
    }

    public String getMainAppName() {
        return mainAppName;
    }

    public void setMainAppName(String mainAppName) {
        this.mainAppName = mainAppName;
    }

    public String getAppsManual() {
        return appsManual;
    }

    public void setAppsManual(String appsManual) {
        this.appsManual = appsManual;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVatRegNo() {
        return vatRegNo;
    }

    public void setVatRegNo(String vatRegNo) {
        this.vatRegNo = vatRegNo;
    }

    public String getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(String vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public boolean isVatEnable() {
        return vatEnable;
    }

    public void setVatEnable(boolean vatEnable) {
        this.vatEnable = vatEnable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
}
