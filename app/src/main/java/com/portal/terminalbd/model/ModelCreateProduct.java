package com.portal.terminalbd.model;

import com.google.gson.annotations.SerializedName;

public class ModelCreateProduct {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("brandName")
    private String brandName;

    @SerializedName("price")
    private String price;

    @SerializedName("unit")
    private String unit;

    @SerializedName("openingQuantity")
    private String openingQuantity;

    @SerializedName("minQuantity")
    private String minQuantity;

    @SerializedName("message")
    private String message;


  /*  public ModelCreateProduct(){

    }

    public ModelCreateProduct(String name, String category, String brandName, String price, String unit, String openingQuantity, String minQuantity) {
        this.name = name;
        this.category = category;
        this.brandName = brandName;
        this.price = price;
        this.unit = unit;
        this.openingQuantity = openingQuantity;
        this.minQuantity = minQuantity;
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOpeningQuantity() {
        return openingQuantity;
    }

    public void setOpeningQuantity(String openingQuantity) {
        this.openingQuantity = openingQuantity;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

  /*  public final static Parcelable.Creator<ModelCreateProduct> CREATOR = new Parcelable.Creator<ModelCreateProduct>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ModelCreateProduct createFromParcel(Parcel in) {
            ModelCreateProduct instance = new ModelCreateProduct();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((String) in.readValue((int.class.getClassLoader())));
            instance.category = ((String) in.readValue((String.class.getClassLoader())));
            instance.brandName = ((String) in.readValue((String.class.getClassLoader())));
            instance.unit = ((String) in.readValue((String.class.getClassLoader())));
            instance.minQuantity = ((String) in.readValue((String.class.getClassLoader())));
            instance.openingQuantity = ((String) in.readValue((String.class.getClassLoader())));

            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ModelCreateProduct[] newArray(int size) {
            return (new ModelCreateProduct[size]);
        }

    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(price);
        dest.writeValue(category);
        dest.writeValue(brandName);
        dest.writeValue(unit);
        dest.writeValue(minQuantity);
        dest.writeValue(openingQuantity);

        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }*/
}
