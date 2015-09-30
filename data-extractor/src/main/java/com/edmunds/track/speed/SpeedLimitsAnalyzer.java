package com.edmunds.track.speed;

import com.google.maps.model.LatLng;
import com.google.maps.model.SpeedLimit;

import java.util.Collection;

/**
 * Created by Administrator on 9/30/2015.
 */
public class SpeedLimitsAnalyzer {

    private static final int FREEWAY_RURAL_MAX = 70;
    private static final int FREEWAY_URBAN_MAX = 65;
    private static final int RESIDENTIAL_MAX = 30;

    public double getSpeedLimitsAverageCoeff(double frPct, double fuPct, double rPct) {
        double averageSpeedLimit = (FREEWAY_RURAL_MAX * frPct / 100 + FREEWAY_URBAN_MAX * fuPct / 100
                + RESIDENTIAL_MAX * rPct / 100) / 3;
        return (averageSpeedLimit - RESIDENTIAL_MAX) / (FREEWAY_RURAL_MAX - RESIDENTIAL_MAX);
    }
}
