package com.edmunds.track.controller;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 9/29/2015.
 */
public class Inventory {

    private String previewUrl;

    private String vin;

    private String rating;

    private static DecimalFormat decimalFormat = new DecimalFormat("#.#");

    public Inventory(String previewUrl, String vin, Double rating) {
        this.previewUrl = previewUrl;
        this.vin = vin;
        this.rating = decimalFormat.format(rating);;
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

    public String getRating() {
        return rating;
    }

}
