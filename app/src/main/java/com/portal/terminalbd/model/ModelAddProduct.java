package com.portal.terminalbd.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ModelAddProduct implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("p_name")
    private String name;
    @SerializedName("p_price")
    private String price;

    @SerializedName("d_price")
    private String dprice;

    @SerializedName("tabel_no")
    private String tabel_no;


    @SerializedName("seat_no")
    private String seat_no;


    @SerializedName("p_description")
    private String description;

    @SerializedName("shop_name")
    private String shopname;

    @SerializedName("shop_type")
    private String shop_type;

    @SerializedName("category")
    private String categorys;

    @SerializedName("quantity")
    private String quantitys;

    @SerializedName("offers")
    private String offer;

    @SerializedName("image_url")
    private String url;

    @SerializedName("url")
    private String imgurl;

    @SerializedName("shopId")
    private String shopId;

    @SerializedName("message")
    private String message;

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getDprice() {
        return dprice;
    }

    public void setDprice(String dprice) {
        this.dprice = dprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQuantitys() {
        return quantitys;
    }

    public void setQuantitys(String quantitys) {
        this.quantitys = quantitys;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getCategorys() {
        return categorys;
    }

    public void setCategorys(String categorys) {
        this.categorys = categorys;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public ModelAddProduct() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getTabel_no() {
        return tabel_no;
    }

    public void setTabel_no(String tabel_no) {
        this.tabel_no = tabel_no;
    }

    public String getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public ModelAddProduct(String name, String price, String dprice, String categorys, String shopId) {
        this.name = name;
        this.price = price;
        this.categorys = categorys;
        this.dprice = dprice;
        this.shopId = shopId;

    }

    public ModelAddProduct(String categorys, String shopId) {
        this.categorys = categorys;
        this.shopId = shopId;
    }

    public ModelAddProduct(String tabel_no,String seat_no,String shopId){
        this.tabel_no = tabel_no;
        this.seat_no = seat_no;
        this.shopId = shopId;
    }

    public final static Creator<ModelAddProduct> CREATOR = new Creator<ModelAddProduct>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ModelAddProduct createFromParcel(Parcel in) {
            ModelAddProduct instance = new ModelAddProduct();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((String) in.readValue((int.class.getClassLoader())));
            instance.categorys = ((String) in.readValue((String.class.getClassLoader())));
            instance.dprice = ((String) in.readValue((String.class.getClassLoader())));

            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ModelAddProduct[] newArray(int size) {
            return (new ModelAddProduct[size]);
        }

    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(price);
        dest.writeValue(categorys);
        dest.writeValue(dprice);

        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }
}
