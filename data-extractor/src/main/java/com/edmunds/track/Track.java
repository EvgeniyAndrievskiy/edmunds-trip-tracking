package com.edmunds.track;

import com.google.maps.model.LatLng;
import com.topografix.gpx.schema.GpxType;
import com.topografix.gpx.schema.TrkType;
import com.topografix.gpx.schema.TrksegType;
import com.topografix.gpx.schema.WptType;
import org.springframework.core.io.ClassPathResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aliaksandr_Halauneu on 9/30/2015.
 */
public class Track {

    private int userId;
    private int tripId;
    private String name;
    private Date date;
    private List<LatLng> points;

    public Track(int userId, int tripId, String name, Date date, List<LatLng> points) {
        this.userId = userId;
        this.tripId = tripId;
        this.name = name;
        this.date = date;
        this.points = points;
    }

    public static Track create(int userId, int tripId, String resourceFilePath) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance("com.topografix.gpx.schema");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<GpxType> root = (JAXBElement<GpxType>) unmarshaller.unmarshal(
                new ClassPathResource(resourceFilePath).getInputStream());

        Track track;
        List<LatLng> points = new ArrayList<LatLng>();
        GpxType gpxType = root.getValue();
        if (gpxType.getTrk().size() > 1) {
            System.out.println("WARNING: number of tracks are greater then 1");
        }
        for (TrkType trkType : gpxType.getTrk()) {
            System.out.println("Loading of track:" + trkType.getName());
            track = new Track(
                    userId, tripId,
                    trkType.getName(),
                    gpxType.getMetadata().getTime().toGregorianCalendar().getTime(), points);
            for (TrksegType trksegType : trkType.getTrkseg()) {
                for (WptType wptType : trksegType.getTrkpt()) {
                    points.add(new LatLng(wptType.getLat().doubleValue(), wptType.getLon().doubleValue()));
                }
            }
            return track;
        }
        return null;
    }

    public int getUserId() {
        return userId;
    }

    public int getTripId() {
        return tripId;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<LatLng> getPoints() {
        return points;
    }
}
