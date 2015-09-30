package com.edmunds.track.controller;

/**
 * Created by Administrator on 9/29/2015.
 */
public class Inventory {

    private String previewUrl;

    private String vin;

    private Double rating;

    public Inventory(String previewUrl, String vin, Double rating) {
        this.previewUrl = previewUrl;
        this.vin = vin;
        this.rating = rating;
    }

    public Inventory(String previewUrl, String vin) {
        this.previewUrl = previewUrl;
        this.vin = vin;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getVin() {
        return vin;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
