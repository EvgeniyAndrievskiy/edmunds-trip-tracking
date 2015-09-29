package com.edmunds.track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahaurylau on 29.09.2015.
 */
public class VehicleStatistic {
    private static final int INIT_COUNT = 1;
    private Map<VehicleParam, Map<String, Integer>> statistics = new HashMap<>();
    private Map<VehicleParam, List<Number>> numericStats = new HashMap<>();

    public <T> void addParam(VehicleParam param, T paramValue) {
        if (param.isNumeric()) {
            addNumericParam(param, Number.class.cast(paramValue));
        } else {
            addStringParam(param, paramValue.toString());
        }
    }

    public void addStringParam(VehicleParam paramName, String paramValue) {
        Map<String, Integer> parameterCount = statistics.get(paramName);
        if (parameterCount == null) {
            parameterCount = new HashMap<>();
            statistics.put(paramName, parameterCount);
        }
        Integer count = parameterCount.get(paramValue);
        if (count == null) {
            parameterCount.put(paramValue, INIT_COUNT);
        } else {
            parameterCount.put(paramValue, ++count);
        }
    }

    public void addNumericParam(VehicleParam paramName, Number paramValue) {
        List<Number> values = numericStats.get(paramName);
        if (values == null) {
            values = new ArrayList<>();
            numericStats.put(paramName, values);
        }
        values.add(paramValue);
    }

    public Map<String, Integer> getValueStatistics(VehicleParam stringParamName) {
        Map<String, Integer> stat = statistics.get(stringParamName);
        return (stat == null) ? Collections.<String, Integer>emptyMap() : new HashMap<>(stat);
    }

    public List<Number> getNumericStatistics(VehicleParam numericParamName) {
        List<Number> stat = numericStats.get(numericParamName);
        return (stat == null) ? Collections.<Number>emptyList() : new ArrayList<>(stat);
    }
}
