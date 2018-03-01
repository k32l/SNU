package com.example.android.beacondetector;

import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;

/**
 * Created by Sooram Kang on 2016-11-25.
 */
public class Course {
    public String courseName;
    public String beaconId;
    public Region region;
    public String start = "00:00:00";
    public String end = "23:59:59";
    private String enter = "00:00:00";
    private String exit = "00:00:00";
    private boolean alreadyEntered = false;

    // Constructors
    Course() {

    }

    Course(String name, String id, String startTime, String endTime) {
        courseName = name;
        beaconId = id;
        String[] ids = id.split("/");
        region = new Region(courseName, Identifier.parse(ids[0]), Identifier.parse(ids[1]), Identifier.parse(ids[2]));
        start = startTime;
        end = endTime;
    }
    // Counstructors end

    public void setEnterTime(String time) {
        enter = time;
    }

    public String getEnterTime() {
        return enter;
    }

    public void setExitTime(String time) {
        exit = time;
    }

    public String getExitTime() {
        return exit;
    }

    public void setAlreadyEntered(boolean bool) {
        alreadyEntered = bool;
    }

    public boolean isAlreadyEntered() {
        return alreadyEntered;
    }

}
