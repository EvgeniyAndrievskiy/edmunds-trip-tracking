package com.edmunds.track.inventory;

import com.edmunds.rest.client.RequestView;
import com.edmunds.rest.dto.inventory.InventoriesDto;
import com.edmunds.rest.dto.inventory.InventoryDto;
import com.edmunds.rest.dto.vehicle.StyleDto;
import com.edmunds.rest.service.client.inventory.InventoryV2RestClient;
import com.edmunds.rest.service.client.vehicle.impl.StyleRestClientImpl;
import com.edmunds.track.criteria.RoadType;
import com.edmunds.track.service.PathService;
import com.edmunds.track.speed.SpeedLimitsAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Administrator on 9/29/2015.
 */
//@Service
public class InventoryService {

    public static final int MAX_HORSE_POWER = 600;
    public static final int MIN_HORSE_POWER = 60;

    public static final int MAX_RATING = 5;

    @Autowired
    private InventoryV2RestClient inventoryRestClient;
    @Autowired
    private PathService pathService;
    @Autowired
    private SpeedLimitsAnalyzer speedLimitsAnalyzer;
    @Autowired
    private StyleRestClientImpl styleRestClient;

    private Map<Long, Integer> engineHorsePowerMap = new HashMap<>();

    public InventoriesDto getInventoriesByLocation(String dealerId) {
        return inventoryRestClient.getInventoriesByDealerId(dealerId);
    }

    /**
     * Sorts inventories by rating and returns these ratings.
     *
     * @param inventories inventories.
     * @return ratings.
     */
    public Map<String, Double> sortInventoriesByRelativity(int pathId, List<InventoryDto> inventories) {
        Map<RoadType, Double> pathMap = pathService.getRoadTypesWithPcts(pathId);
        final double averageSpeedCoeff = speedLimitsAnalyzer.getSpeedLimitsAverageCoeff(pathMap.get(RoadType.HIGHWAY_RURAL),
                pathMap.get(RoadType.HIGHWAY_URBAN), pathMap.get(RoadType.RESIDENTIAL));
        fillEngineHorsePowerMap(inventories);
        Map<String, Double> ratingMap = new HashMap<>();
        for(InventoryDto inventoryDto : inventories) {
            double delta = Math.abs(getInventoryHorsePowerCoeff(inventoryDto) - averageSpeedCoeff);
            ratingMap.put(inventoryDto.getId(), (1 - delta) * MAX_RATING);
        }
        Collections.sort(inventories, new Comparator<InventoryDto>() {
            @Override
            public int compare(InventoryDto o1, InventoryDto o2) {
                Double rating1 = ratingMap.get(o1.getId());
                Double rating2 = ratingMap.get(o2.getId());
                return rating2.compareTo(rating1);
            }
        });
        return ratingMap;
    }

    private void fillEngineHorsePowerMap(List<InventoryDto> inventories) {
        for(InventoryDto inventoryDto : inventories) {
            if(!engineHorsePowerMap.containsKey(inventoryDto.getStyle().getId())) {
                StyleDto styleDto = styleRestClient.findStyleById(inventoryDto.getStyle().getId(), RequestView.custom()
                        .add("engine").add("horsepower").buildIncludingFields());
                engineHorsePowerMap.put(inventoryDto.getStyle().getId(), styleDto.getEngine().getHorsepower());
            }
        }

    }

    private double getInventoryHorsePowerCoeff(InventoryDto inventoryDto) {
        double horsePower = engineHorsePowerMap.get(inventoryDto.getStyle().getId());
        return (horsePower - MIN_HORSE_POWER) / (MAX_HORSE_POWER - MIN_HORSE_POWER);
    }

}
