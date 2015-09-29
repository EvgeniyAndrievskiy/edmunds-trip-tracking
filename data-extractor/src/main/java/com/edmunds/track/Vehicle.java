package com.edmunds.track;

import java.util.HashMap;
import java.util.Map;

import static com.edmunds.track.VehicleParam.BODY;
import static com.edmunds.track.VehicleParam.ENGINE_TYPE;
import static com.edmunds.track.VehicleParam.HORSEPOWER;
import static com.edmunds.track.VehicleParam.MAKE_NAME;
import static com.edmunds.track.VehicleParam.MPG_CITY;
import static com.edmunds.track.VehicleParam.MPG_HIGHWAY;
import static com.edmunds.track.VehicleParam.TRANSMISSION_TYPE;

/**
 * Created by ahaurylau on 29.09.2015.
 */
public class Vehicle {

    private Map<VehicleParam, String> params = new HashMap<>();
    private Map<VehicleParam, Number> numericParams = new HashMap<>();

    public void setMakeName(String makeName) {
        params.put(MAKE_NAME, makeName);
    }

    public String getMakeName() {
        return params.get(MAKE_NAME);
    }

    public void setBody(String body) {
        params.put(BODY, body);
    }

    public String getBody() {
        return params.get(BODY);
    }

    public void setTransmissionType(String transmissionType) {
        params.put(TRANSMISSION_TYPE, transmissionType);
    }

    public String getTransmissionType() {
        return params.get(TRANSMISSION_TYPE);
    }

    public void setHorsepower(Integer horsepower) {
        numericParams.put(HORSEPOWER, horsepower);
    }

    public Integer getHorsepower() {
        return Integer.class.cast(numericParams.get(HORSEPOWER));
    }

    public void setEngineType(String engineType) {
        params.put(ENGINE_TYPE, engineType);
    }

    public String getEngineType() {
        return params.get(ENGINE_TYPE);
    }

    public void setMpgHighway(Integer mpgHighway) {
        numericParams.put(MPG_HIGHWAY, mpgHighway);
    }

    public Integer getMpgHighway() {
        return Integer.class.cast(numericParams.get(MPG_HIGHWAY));
    }

    public void setMpgCity(Integer mpgCity) {
        numericParams.put(MPG_CITY, mpgCity);
    }

    public Integer getMpgCity() {
        return Integer.class.cast(numericParams.get(MPG_CITY));
    }

    public String getStringParamValue(VehicleParam vehicleParam) {
        return  params.get(vehicleParam);
    }

    public Number getNumericParamValue(VehicleParam vehicleParam) {
        return  numericParams.get(vehicleParam);
    }
}
