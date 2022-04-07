package com.portal.terminalbd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PurchaseItemHistory extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();


    @SerializedName("purchaseId")
    @Expose
    private int purchaseId;

    @SerializedName("stockId")
    @Expose
    private int stockId;

    @SerializedName("purchasePrice")
    @Expose
    private double purchasePrice;

    @SerializedName("salesPrice")
    @Expose
    private double salesPrice;

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("subTotal")
    @Expose
    private int subTotal;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
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
