package com.edmunds.track;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahaurylau on 29.09.2015.
 */
public class VehicleAnalyzer {

    private VehicleStatistic vehicleStatistic = new VehicleStatistic();

    private final Map<VehicleParam, Float> parameterWF;

    public VehicleAnalyzer(Map<VehicleParam, Float> parameterWF) {
        this.parameterWF = parameterWF;
    }

    public VehicleAnalyzer() {
        this(new EnumMap<VehicleParam, Float>(VehicleParam.class));
        for (VehicleParam param: VehicleParam.values()) {
            parameterWF.put(param, 1.0F);
        }
    }

    public void addStatData(Vehicle vehicle) {
        for (VehicleParam vehicleParam : VehicleParam.values()) {
            if (vehicleParam.isNumeric()) {
                vehicleStatistic.addParam(vehicleParam, vehicle.getNumericParamValue(vehicleParam));
            } else {
                vehicleStatistic.addParam(vehicleParam, vehicle.getStringParamValue(vehicleParam));
            }
        }

    }

    public void addStatData(Iterable<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            addStatData(vehicle);
        }
    }

    /**
     * Returns the weight factor (WF) for specified vehicle.
     *
     * @param vehicle vehicle
     * @return the weight factor
     */
    public float getWf(Vehicle vehicle) {
        Map<VehicleParam, Float> wfMap = getWfMap(vehicle);
        float wf = 0;
        for (Float paramWf : wfMap.values()) {
            wf += paramWf;
        }
        return wf;
    }

    /**
     * Returns map of weight factors (WF) for specified vehicle.
     *
     * @param vehicle vehicle
     * @return map of weight factors
     */
    public Map<VehicleParam, Float> getWfMap(Vehicle vehicle) {
        Map<VehicleParam, Float> result = new EnumMap<>(VehicleParam.class);
        float wf;
        for (VehicleParam param : VehicleParam.values()) {
            if (param.isNumeric()) {
                wf = numStaticWF(param, vehicle.getNumericParamValue(param).doubleValue());
            } else {
                wf = staticWF(param, vehicle.getStringParamValue(param));
            }
            result.put(param, wf*parameterWF.get(param));
        }
        return result;
    }

    private float numStaticWF(VehicleParam param, double value) {
        SummaryStatistics statistics = new SummaryStatistics();
        List<Number> statList = vehicleStatistic.getNumericStatistics(param);
        for (Number number : statList) {
            if (number != null) {
                statistics.addValue(number.doubleValue());
            }
        }
        double mean = statistics.getMean();
        double dev = statistics.getStandardDeviation();

        if (value >= mean - dev && value <= mean + dev) {
            return 1.0F;
        }

        float wf = 0;
        if (value >= statistics.getMin() && value <= statistics.getMax()) {
            wf += 0.25F;
        }
        if (value >= mean - 2*dev && value <= mean + 2*dev) {
            wf += 0.25F;
        }
        return wf;
    }


    private float staticWF(VehicleParam param, String stringParamValue) {
        Map<String, Integer> stat = vehicleStatistic.getValueStatistics(param);
        //TODO expand "stringParamValue" by correlation map
        Integer count = stat.get(stringParamValue);
        if (count == null || count < 1) {
            return 0;
        }
        int total = 0;
        for (Integer itemCount : stat.values()) {
            total += itemCount;
        }

        return (float)count/total;
    }
}
