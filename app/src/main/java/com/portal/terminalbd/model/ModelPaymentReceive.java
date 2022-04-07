package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPaymentReceive {
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("customerId")
    @Expose
    private String customerId;

    @SerializedName("method")
    @Expose
    private String method;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("remark")
    @Expose
    private String remark;

    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;

    @SerializedName("mobileAccount")
    @Expose
    private String mobileAccount;

    @SerializedName("transactionId")
    @Expose
    private String transactionId;

    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;

    @SerializedName("status")
    @Expose
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getMobileAccount() {
        return mobileAccount;
    }

    public void setMobileAccount(String mobileAccount) {
        this.mobileAccount = mobileAccount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
