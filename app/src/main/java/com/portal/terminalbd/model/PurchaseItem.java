package com.portal.terminalbd.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PurchaseItem extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String purchaseStockId;
    private String purchaseItemName;
    private String purchasePpPrice;
    private String purchaseMrpPrice;
    private String purchaseQuantity;
    private String purchaseSubTotal;


    public String getPurchaseStockId() {
        return purchaseStockId;
    }

    public void setPurchaseStockId(String purchaseStockId) {
        this.purchaseStockId = purchaseStockId;
    }

    public String getPurchaseItemName() {
        return purchaseItemName;
    }

    public void setPurchaseItemName(String purchaseItemName) {
        this.purchaseItemName = purchaseItemName;
    }

    public String getPurchasePpPrice() {
        return purchasePpPrice;
    }

    public void setPurchasePpPrice(String purchasePpPrice) {
        this.purchasePpPrice = purchasePpPrice;
    }

    public String getPurchaseMrpPrice() {
        return purchaseMrpPrice;
    }

    public void setPurchaseMrpPrice(String purchaseMrpPrice) {
        this.purchaseMrpPrice = purchaseMrpPrice;
    }

    public String getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getPurchaseSubTotal() {
        return purchaseSubTotal;
    }

    public void setPurchaseSubTotal(String purchaseSubTotal) {
        this.purchaseSubTotal = purchaseSubTotal;
    }


}
