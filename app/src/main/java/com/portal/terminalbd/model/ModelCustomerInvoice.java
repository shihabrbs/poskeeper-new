package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelCustomerInvoice {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("invoice")
    @Expose
    private String invoice;
    @SerializedName("customer")
    @Expose
    private String customer;
    @SerializedName("customerMobile")
    @Expose
    private String customerMobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("salesBy")
    @Expose
    private String salesBy;
    @SerializedName("subTotal")
    @Expose
    private Integer subTotal;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("payment")
    @Expose
    private Integer payment;
    @SerializedName("vat")
    @Expose
    private Integer vat;
    @SerializedName("sd")
    @Expose
    private Integer sd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSalesBy() {
        return salesBy;
    }

    public void setSalesBy(String salesBy) {
        this.salesBy = salesBy;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }

    public Integer getSd() {
        return sd;
    }

    public void setSd(Integer sd) {
        this.sd = sd;
    }
}
