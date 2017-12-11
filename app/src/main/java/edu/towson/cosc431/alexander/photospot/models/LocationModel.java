package edu.towson.cosc431.alexander.photospot.models;

/**
 * Created by tylerhoward on 12/11/17.
 */

public class LocationModel {
    private double latitude;
    private double longitude;
    private static LocationModel instance;


    public LocationModel getInstance(){
        if (instance == null) instance = new LocationModel();
        return instance;
    }

    public LocationModel() {
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
