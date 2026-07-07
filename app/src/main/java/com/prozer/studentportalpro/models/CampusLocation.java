package com.prozer.studentportalpro.models;

public class CampusLocation {

    private int id;
    private String buildingName;
    private String latitude;
    private String longitude;
    private String dateCaptured;

    public CampusLocation(int id,
                          String buildingName,
                          String latitude,
                          String longitude,
                          String dateCaptured) {

        this.id = id;
        this.buildingName = buildingName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateCaptured = dateCaptured;
    }

    public int getId() {
        return id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDateCaptured() {
        return dateCaptured;
    }

}