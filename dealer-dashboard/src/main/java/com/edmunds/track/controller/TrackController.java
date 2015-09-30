package com.edmunds.track.controller;

import com.edmunds.track.Track;
import com.edmunds.track.dao.TracksHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Aliaksandr_Halauneu on 9/30/2015.
 */
@Controller
@RequestMapping("/track")
public class TrackController {

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public Track getTrack(int userId, int tripId) {
        return TracksHolder.getTrack(userId, tripId);
    }
}
