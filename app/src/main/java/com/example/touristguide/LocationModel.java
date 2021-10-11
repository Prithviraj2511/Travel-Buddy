package com.example.touristguide;

public class LocationModel {
    private String location_id;
    private String location_name;
    private float location_rating;
    private String location_desc;

    // Constructor
    public LocationModel(String location_id,String location_name, float location_rating, String location_desc) {
        this.location_id=location_id;
        this.location_name = location_name;
        this.location_rating = location_rating;
        this.location_desc = location_desc;
    }

    // Getter and Setter

    public String getLocation_id() {
        return location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String course_name) {
        this.location_name = course_name;
    }

    public float getLocation_rating() {
        return location_rating;
    }

    public void setLocation_rating(int course_rating) {
        this.location_rating = location_rating;
    }

    public String getLocation_desc() {
        return location_desc;
    }

    public void setLocation_desc(String location_desc) {
        this.location_desc = location_desc;
    }
}