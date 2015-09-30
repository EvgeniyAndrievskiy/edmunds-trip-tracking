package com.edmunds.track.controller;

import com.edmunds.rest.dto.inventory.InventoriesDto;
import com.edmunds.rest.dto.inventory.InventoryDto;
import com.edmunds.track.criteria.RoadType;
import com.edmunds.track.inventory.InventoryService;
import com.edmunds.track.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 9/29/2015.
 */
@Controller
@RequestMapping("/inventories")
public class InventoriesController {

    @Autowired
    private InventoryService inventoryService;

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public InventoriesResponse getInventories(String dealerId, int pathId) {
        InventoriesDto inventoriesDto = inventoryService.getInventoriesByLocation(dealerId);
        Map<String, Double> ratings = inventoryService.sortInventoriesByRelativity(pathId, inventoriesDto.getInventories());
        List<Inventory> inventories = new ArrayList<Inventory>();
        for(InventoryDto inventoryDto : inventoriesDto.getInventories()) {
            String previewHref = "";
            if(inventoryDto.getMedia() != null) {
                previewHref = inventoryDto.getMedia().getPhotos().getThumbnails().getLinks().iterator().next().getHref();
            }
            Inventory inventory = new Inventory(previewHref, inventoryDto.getVin(), ratings.get(inventoryDto.getId()));
            inventories.add(inventory);
        }

        return new InventoriesResponse(inventoriesDto.getInventories().get(0).getDealer().getName(), inventories,
                inventoryService.getAverageSpeed(pathId));
    }



}
