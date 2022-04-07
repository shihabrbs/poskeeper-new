package com.portal.terminalbd.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SaleItem extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String saleStockId;
    private String saleItemName;
    private String salePpPrice;
    private String saleMrpPrice;
    private String saleQuantity;
    private String saleSubTotal;


    public String getSaleStockId() {
        return saleStockId;
    }

    public void setSaleStockId(String saleStockId) {
        this.saleStockId = saleStockId;
    }

    public String getSaleItemName() {
        return saleItemName;
    }

    public void setSaleItemName(String saleItemName) {
        this.saleItemName = saleItemName;
    }

    public String getSalePpPrice() {
        return salePpPrice;
    }

    public void setSalePpPrice(String salePpPrice) {
        this.salePpPrice = salePpPrice;
    }

    public String getSaleMrpPrice() {
        return saleMrpPrice;
    }

    public void setSaleMrpPrice(String saleMrpPrice) {
        this.saleMrpPrice = saleMrpPrice;
    }

    public String getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(String saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public String getSaleSubTotal() {
        return saleSubTotal;
    }

    public void setSaleSubTotal(String saleSubTotal) {
        this.saleSubTotal = saleSubTotal;
    }
}
