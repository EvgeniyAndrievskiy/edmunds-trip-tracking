package com.edmunds.track.controller;

import java.util.List;

/**
 * Created by Administrator on 9/29/2015.
 */
public class InventoriesResponse {

    private String dealerName;

    private List<Inventory> inventories;

    public InventoriesResponse() {}

    public InventoriesResponse(String dealerName, List<Inventory> inventories) {
        this.dealerName = dealerName;
        this.inventories = inventories;
    }

    public String getDealerName() {
        return dealerName;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }
}
