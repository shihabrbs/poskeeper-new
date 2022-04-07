package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PurchaseHistory extends RealmObject {

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

    @SerializedName("discountCulculation")
    @Expose
    private int discountCulculation;

    @SerializedName("vat")
    @Expose
    private int vat;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("payment")
    @Expose
    private int payment;

    @SerializedName("due")
    @Expose
    private int due;

    @SerializedName("transactionMethod")
    @Expose
    private String transactionMethod;

    @SerializedName("bankAccount")
    @Expose
    private int bankAccount;

    @SerializedName("mobileBankAccount")
    @Expose
    private int mobileBankAccount;

    @SerializedName("receivedBy")
    @Expose
    private int receivedBy;

    @SerializedName("vendorId")
    @Expose
    private int vendorId;

    @SerializedName("created")
    @Expose
    private String created;

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

    public int getDiscountCulculation() {
        return discountCulculation;
    }

    public void setDiscountCulculation(int discountCulculation) {
        this.discountCulculation = discountCulculation;
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

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
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

    public int getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(int receivedBy) {
        this.receivedBy = receivedBy;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
