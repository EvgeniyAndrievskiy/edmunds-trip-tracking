package com.edmunds.track;

import com.edmunds.track.criteria.Location;
import com.edmunds.track.criteria.Road;

import java.util.List;

/**
 * Created by Aliaksandr_Halauneu on 9/29/2015.
 */
public class DrivingExperience {

    private List<Road> roads;
    private List<Location> locations;

/*    public static void create() throws Exception {
        JAXBContext jc = JAXBContext.newInstance("com.topografix.gpx.schema");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<GpxType> root = (JAXBElement<GpxType>) unmarshaller.unmarshal(
                new ClassPathResource("/tracks/Kalifornien02-01-2014.gpx").getInputStream());
        List<TrkType> tracks = root.getValue().getTrk();

        String sURL = "https://roads.googleapis.com/v1/speedLimits?";
        String path = "path";

        SpeedLimitsService speedLimitsService = new SpeedLimitsService();
        speedLimitsService.setApiKey("AIzaSyA0RHXYGEXZFQHFmLZA8ROnWAJtk1vi6jw");

        List<LatLng> points = new ArrayList<LatLng>();
        for (TrkType trkType : tracks) {
            System.out.println("Track Name:" + trkType.getName());
            for (TrksegType trksegType : trkType.getTrkseg()) {
                for (WptType wptType : trksegType.getTrkpt()) {
                    points.add(new LatLng(wptType.getLat().doubleValue(), wptType.getLon().doubleValue()));
                }
            }
        }
    }*/
}
