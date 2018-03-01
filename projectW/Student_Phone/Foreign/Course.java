package com.android.beacondetector;

import android.os.Parcel;
import android.os.Parcelable;

import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;

/**
 * Created by Sooram Kang on 2016-11-25.
 */
public class Course implements Parcelable{
    public String courseName;
    public String beaconId;
    public Region region;
    public String start = "00:00";
    public String end = "23:59";
    private String enter = "00:00";
    private String exit = "00:00";

    // Constructors
    public Course() {

    }

    public Course(String name, String id, String startTime, String endTime) {
        courseName = name;
        beaconId = id;
        String[] ids = id.split("/");
        region = new Region(courseName, Identifier.parse(ids[0]), Identifier.parse(ids[1]), Identifier.parse(ids[2]));
        start = startTime;
        end = endTime;
    }

    public Course(Parcel in) {
        courseName = in.readString();
        beaconId = in.readString();
        region = in.readParcelable(Region.class.getClassLoader());
        start = in.readString();
        end = in.readString();
        enter = in.readString();
        exit = in.readString();
    }
    // Constructors end

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeString(beaconId);
        dest.writeParcelable(region, flags);
        dest.writeString(start);
        dest.writeString(end);
        dest.writeString(enter);
        dest.writeString(exit);
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>()
    {
        public Course createFromParcel(Parcel in)
        {
            return new Course(in);
        }
        public Course[] newArray(int size)
        {
            return new Course[size];
        }
    };

}
