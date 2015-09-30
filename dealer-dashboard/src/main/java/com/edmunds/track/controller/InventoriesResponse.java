package com.edmunds.track.controller;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 9/29/2015.
 */
public class InventoriesResponse {

    private String dealerName;

    private List<Inventory> inventories;

    private String averageSpeed;

    public InventoriesResponse() {}

    public InventoriesResponse(String dealerName, List<Inventory> inventories, Double averageSpeed) {
        this.dealerName = dealerName;
        this.inventories = inventories;
        this.averageSpeed = Inventory.decimalFormat.format(averageSpeed);
    }

    public String getDealerName() {
        return dealerName;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public String getAverageSpeed() {
        return averageSpeed;
    }
}
