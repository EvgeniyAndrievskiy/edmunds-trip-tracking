package com.edmunds.track.controller;

import com.edmunds.rest.dto.common.LinkDto;
import com.edmunds.rest.dto.inventory.InventoriesDto;
import com.edmunds.rest.dto.inventory.InventoryDto;
import com.edmunds.track.inventory.InventoryService;
import com.edmunds.track.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 9/29/2015.
 */
@Controller
@RequestMapping("/inventories")
public class InventoriesController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PathService pathService;

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public InventoriesResponse getInventories(String dealerId, long pathId) {
        InventoriesDto inventoriesDto = inventoryService.getInventoriesByLocation(dealerId);
        List<Inventory> inventories = new ArrayList<Inventory>();
        for(int i = 0; i < inventoriesDto.getInventories().size(); i++) {
            InventoryDto inventoryDto = inventoriesDto.getInventories().get(i);

            String previewHref = "";
            if(inventoryDto.getMedia() != null) {
                previewHref = inventoryDto.getMedia().getPhotos().getThumbnails().getLinks().iterator().next().getHref();
            }
            Inventory inventory = new Inventory(previewHref, inventoryDto.getVin());
            inventories.add(inventory);
        }

        return new InventoriesResponse(inventoriesDto.getInventories().get(0).getDealer().getName(), inventories);
    }



}
