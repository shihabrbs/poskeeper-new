package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerLedeger {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sales")
    @Expose
    private Integer sales;
    @SerializedName("receive")
    @Expose
    private Integer receive;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("sourceInvoice")
    @Expose
    private String sourceInvoice;
    @SerializedName("updated")
    @Expose
    private String updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getSourceInvoice() {
        return sourceInvoice;
    }

    public void setSourceInvoice(String sourceInvoice) {
        this.sourceInvoice = sourceInvoice;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
