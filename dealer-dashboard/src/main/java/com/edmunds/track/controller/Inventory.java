package com.edmunds.track.controller;

/**
 * Created by Administrator on 9/29/2015.
 */
public class Inventory {

    private String previewUrl;

    private String vin;

    private double rating;

    public Inventory(String previewUrl, String vin, double rating) {
        this.previewUrl = previewUrl;
        this.vin = vin;
        this.rating = rating;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getVin() {
        return vin;
    }

    public double getRating() {
        return rating;
    }
}
