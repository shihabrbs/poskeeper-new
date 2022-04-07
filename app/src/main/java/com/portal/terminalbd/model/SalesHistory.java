package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SalesHistory extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @SerializedName("invoiceId")
    @Expose
    private String invoiceId;

    @SerializedName("subTotal")
    @Expose
    private int subTotal;

    @SerializedName("discount")
    @Expose
    private int discount;

    @SerializedName("discountType")
    @Expose
    private String discountType;

    @SerializedName("discountCalculation")
    @Expose
    private int discountCalculation;

    @SerializedName("vat")
    @Expose
    private int vat;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("receive")
    @Expose
    private int receive;

    @SerializedName("due")
    @Expose
    private int due;

    @SerializedName("customerId")
    @Expose
    private int customerId;

    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerMobile")
    @Expose
    private String customerMobile;

    @SerializedName("transactionMethod")
    @Expose
    private String transactionMethod;
    @SerializedName("bankAccount")
    @Expose
    private int bankAccount;

    @SerializedName("mobileBankAccount")
    @Expose
    private int mobileBankAccount;

    @SerializedName("paymentMobile")
    @Expose
    private String paymentMobile;
    @SerializedName("paymentCard")
    @Expose
    private int paymentCard;

    @SerializedName("paymentCardNo")
    @Expose
    private String paymentCardNo;

    @SerializedName("transactionId")
    @Expose
    private String transactionId;

    @SerializedName("salesBy")
    @Expose
    private int salesBy;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("createdBy")
    @Expose
    private int createdBy;

    @SerializedName("slipNo")
    @Expose
    private String slipNo;

    @SerializedName("tokenNo")
    @Expose
    private String tokenNo;

    @SerializedName("discountCoupon")
    @Expose
    private int discountCoupon;

    @SerializedName("remark")
    @Expose
    private String remark;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getDiscountCalculation() {
        return discountCalculation;
    }

    public void setDiscountCalculation(int discountCalculation) {
        this.discountCalculation = discountCalculation;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(int bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getMobileBankAccount() {
        return mobileBankAccount;
    }

    public void setMobileBankAccount(int mobileBankAccount) {
        this.mobileBankAccount = mobileBankAccount;
    }

    public String getPaymentMobile() {
        return paymentMobile;
    }

    public void setPaymentMobile(String paymentMobile) {
        this.paymentMobile = paymentMobile;
    }

    public int getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(int paymentCard) {
        this.paymentCard = paymentCard;
    }

    public String getPaymentCardNo() {
        return paymentCardNo;
    }

    public void setPaymentCardNo(String paymentCardNo) {
        this.paymentCardNo = paymentCardNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getSalesBy() {
        return salesBy;
    }

    public void setSalesBy(int salesBy) {
        this.salesBy = salesBy;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public int getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(int discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
