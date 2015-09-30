package com.edmunds.track;

import com.google.maps.GeoApiContext;
import com.google.maps.RoadsApi;
import com.google.maps.errors.ApiError;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.LatLng;
import com.google.maps.model.SpeedLimit;

import java.util.Collection;

public class SpeedLimitsService {

    /*static final ApiConfig SPEEDS_API_CONFIG = new ApiConfig("/v1/speedLimits")
            .hostName("https://roads.googleapis.com")
            .supportsClientId(false)
            .fieldNamingPolicy(FieldNamingPolicy.IDENTITY);*/

    private GeoApiContext geoApiContext;

    public SpeedLimitsService() {
        geoApiContext = new GeoApiContext();
    }

    public SpeedLimit[] getSpeedLimits(Collection<LatLng> pathPoints) throws Exception {
        /*String sURL = "https://roads.googleapis.com/v1/speedLimits?";
        sURL += StringJoin.join('|', pathPoints.toArray(new LatLng[pathPoints.size()]));
        sURL += "&key=AIzaSyA0RHXYGEXZFQHFmLZA8ROnWAJtk1vi6jw";

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        rootobj.get*/

        //return geoApiContext.get(SPEEDS_API_CONFIG, SpeedsResponse.class,
        //        "path", StringJoin.join('|', pathPoints.toArray(new LatLng[pathPoints.size()])),
        //        "units", "MPH");
        return RoadsApi.speedLimits(geoApiContext, pathPoints.toArray(new LatLng[pathPoints.size()])).await();
    }

    public void setApiKey(String apiKey) {
        geoApiContext.setApiKey(apiKey);
    }

    private static class SpeedsResponse implements ApiResponse<SpeedLimit[]> {
        private SpeedLimit[] speedLimits;
        private ApiError error;

        public boolean successful() {
            return error == null;
        }

        public SpeedLimit[] getResult() {
            return speedLimits;
        }

        public ApiException getError() {
            return ApiException.from(error.status, error.message);
        }
    }
}
