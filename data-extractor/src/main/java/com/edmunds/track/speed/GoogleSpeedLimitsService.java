package com.edmunds.track.speed;

import com.google.maps.GeoApiContext;
import com.google.maps.RoadsApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.SpeedLimit;

import java.util.Collection;

/**
 * Created by Administrator on 9/29/2015.
 */
public class GoogleSpeedLimitsService {

    private GeoApiContext geoApiContext;

    public GoogleSpeedLimitsService() {
        geoApiContext = new GeoApiContext();
    }

    public SpeedLimit[] getSpeedLimits(Collection<LatLng> pathPoints) throws Exception {
        return RoadsApi.speedLimits(geoApiContext, pathPoints.toArray(new LatLng[pathPoints.size()])).await();
    }

    public void setApiKey(String apiKey) {
        geoApiContext.setApiKey(apiKey);
    }
}
