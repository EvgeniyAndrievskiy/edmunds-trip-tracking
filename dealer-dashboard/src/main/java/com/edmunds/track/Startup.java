package com.edmunds.track;

import com.edmunds.track.dao.TracksHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aliaksandr_Halauneu on 9/30/2015.
 */
public class Startup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            List<Track> tracks = new ArrayList<>();
            tracks.add(Track.create(1, 1, "/tracks/Kalifornien02-01-2014.gpx"));
            TracksHolder.setTracks(tracks);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
