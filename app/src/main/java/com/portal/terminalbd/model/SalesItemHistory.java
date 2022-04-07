package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SalesItemHistory extends RealmObject {


    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @SerializedName("salesId")
    @Expose
    private int salesId;

    @SerializedName("stockId")
    @Expose
    private int stockId;

    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("subTotal")
    @Expose
    private int subTotal;



    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
