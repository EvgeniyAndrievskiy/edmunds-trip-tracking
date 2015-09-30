package com.edmunds.track.service;

import com.edmunds.track.criteria.RoadType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 9/30/2015.
 */
@Service
public class PathService {

    private static Map<Integer, Map<RoadType, Double>> pathsMap = generatePathsMap();

    private static Map<Integer, Map<RoadType, Double>> generatePathsMap()  {
        Map<Integer, Map<RoadType, Double>> pathsMap = new HashMap<>();
        pathsMap.put(1, getPathMap(100, 0, 0));
        pathsMap.put(2, getPathMap(0, 0, 100));
        pathsMap.put(3, getPathMap(50, 10, 40));
        return pathsMap;
    }

    private static Map<RoadType, Double> getPathMap(double frPct, double fuPct, double rPct) {
        Map<RoadType, Double> pathMap = new HashMap<>();
        pathMap.put(RoadType.HIGHWAY_RURAL, frPct);
        pathMap.put(RoadType.HIGHWAY_URBAN, fuPct);
        pathMap.put(RoadType.RESIDENTIAL, rPct);
        return pathMap;
    }

    public Map<RoadType, Double> getRoadTypesWithPcts(int pathId) {
        return pathsMap.get(pathId);
    }
}
