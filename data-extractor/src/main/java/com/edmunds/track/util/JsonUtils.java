package com.edmunds.track.util;

import com.edmunds.track.Vehicle;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * Created by ahaurylau on 29.09.2015.
 */
public class JsonUtils {

    public static Vehicle toVehicle(String json) {
        Vehicle vehicle = new Vehicle();

        JsonObject jobject = new JsonParser().parse(json).getAsJsonObject();

        String makeName = jobject.getAsJsonObject("make").get("name").getAsString();
        vehicle.setMakeName(makeName);

        String body = jobject.getAsJsonObject("submodel").get("body").getAsString();
        vehicle.setBody(body);

        String transmissionType = jobject.getAsJsonObject("transmission").get("transmissionType").getAsString();
        vehicle.setTransmissionType(transmissionType);

        JsonObject engine = jobject.getAsJsonObject("engine");
        JsonPrimitive jHorsepower = engine.getAsJsonPrimitive("horsepower");
        Integer horsepower = (jHorsepower != null) ? jHorsepower.getAsInt() : null;
        vehicle.setHorsepower(horsepower);
        String engineType = engine.getAsJsonPrimitive("type").getAsString();
        vehicle.setEngineType(engineType);

        JsonObject mpg = jobject.getAsJsonObject("MPG");
        Integer mpgHighway = mpg.get("highway").getAsInt();
        vehicle.setMpgHighway(mpgHighway);
        Integer mpgCity = mpg.get("city").getAsInt();
        vehicle.setMpgCity(mpgCity);

        return vehicle;
    }
}
