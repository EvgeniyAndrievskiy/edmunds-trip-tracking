package com.edmunds.track.inventory;

import com.edmunds.rest.dto.inventory.InventoriesDto;
import com.edmunds.rest.service.client.inventory.InventoryV2RestClient;
import com.edmunds.rest.service.client.inventory.param.LocationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 9/29/2015.
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryV2RestClient inventoryRestClient;

    public InventoriesDto getInventoriesByLocation(String dealerId) {
        return inventoryRestClient.getInventoriesByDealerId(dealerId);
    }

}
