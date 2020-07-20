package com.skyhosting.softxpertandroidtask.Model;

import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("id")
    private Integer id;
    @SerializedName("brand")
    private String brand;
    @SerializedName("constructionYear")
    private String constructionYear;
    @SerializedName("isUsed")
    private Boolean isUsed;
    @SerializedName("imageUrl")
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
