package com.edmunds.track.speed;

import com.google.maps.model.LatLng;
import com.google.maps.model.SpeedLimit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 9/30/2015.
 */
public class GoogleSpeedLimitsServiceTest {

    private static final String GOOGLE_ROADS_API_KEY = "AIzaSyA0RHXYGEXZFQHFmLZA8ROnWAJtk1vi6jw";

    private GoogleSpeedLimitsService googleSpeedLimitsService;

    @BeforeClass
    public void beforeClass() {
        googleSpeedLimitsService = new GoogleSpeedLimitsService();
        googleSpeedLimitsService.setApiKey(GOOGLE_ROADS_API_KEY);
    }

    @Test(enabled = false)
    public void testGetSpeedLimits() throws Exception {
        Collection<LatLng> points = new ArrayList<LatLng>();
        points.add(new LatLng(34.04675680, -118.245313));
        points.add(new LatLng(34.04676000, -118.245310));
        points.add(new LatLng(34.04643000, -118.244710));
        points.add(new LatLng(34.04589000, -118.243190));
        points.add(new LatLng(34.04537000, -118.242250));
        points.add(new LatLng(34.04488000, -118.241520));
        points.add(new LatLng(34.04419000, -118.240380));
        points.add(new LatLng(34.04368000, -118.239540));
        points.add(new LatLng(34.04362000, -118.239380));
        points.add(new LatLng(34.04332000, -118.238300));
        points.add(new LatLng(34.04329000, -118.238130));
        points.add(new LatLng(34.04345000, -118.236540));
        points.add(new LatLng(34.04345000, -118.236550));
        points.add(new LatLng(34.04356000, -118.235620));
        points.add(new LatLng(34.04360000, -118.235500));
        points.add(new LatLng(34.04363000, -118.235470));
        points.add(new LatLng(34.04368000, -118.235460));
        points.add(new LatLng(34.04375000, -118.235480));
        SpeedLimit[] speedLimits = googleSpeedLimitsService.getSpeedLimits(points);
    }
}
