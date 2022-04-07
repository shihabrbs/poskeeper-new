package com.portal.terminalbd.model;

import com.google.gson.annotations.SerializedName;

public class ModelUser {
    @SerializedName("id")
    private int id;

    @SerializedName("shop_name")
    private String shop_name;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("shop_type")
    private String shop_type;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("message")
    private String message;

    @SerializedName("res")
    private String res;

    @SerializedName("userlimit")
    private String userlimit;

    @SerializedName("setupid")
    private String setupid;

    @SerializedName("multi_user")
    private String multi_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserlimit() {
        return userlimit;
    }

    public void setUserlimit(String userlimit) {
        this.userlimit = userlimit;
    }

    public String getSetupid() {
        return setupid;
    }

    public void setSetupid(String setupid) {
        this.setupid = setupid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMulti_user() {
        return multi_user;
    }

    public void setMulti_user(String multi_user) {
        this.multi_user = multi_user;
    }
}
