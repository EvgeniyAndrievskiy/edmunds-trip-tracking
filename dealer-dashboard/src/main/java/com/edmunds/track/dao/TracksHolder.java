package com.edmunds.track.dao;

import com.edmunds.track.Track;

import java.util.List;

/**
 * Created by Aliaksandr_Halauneu on 9/30/2015.
 */
public class TracksHolder {

    private static List<Track> tracks;

    public synchronized static List<Track> getTracks() {
        return tracks;
    }

    public synchronized static void setTracks(List<Track> tracks) {
        TracksHolder.tracks = tracks;
    }

    public synchronized static Track getTrack(int userId, int tripId) {
        for (Track track : tracks) {
            if (track.getUserId() == userId && track.getTripId() == tripId) {
                return track;
            }
        }
        return null;
    }
}
