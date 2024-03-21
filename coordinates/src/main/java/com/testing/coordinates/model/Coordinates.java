package com.testing.coordinates.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Coordinates {

    private double latitude1;
    private double longitude1;
    private double latitude2;
    private double longitude2;

    public Coordinates() {

    }

    public double getLatitude2() {
        return latitude2;
    }

    public double getLongitude2() {
        return longitude2;
    }

    public double getLongitude1() {
        return longitude1;
    }
    public double getLatitude1() {
        return latitude1;
    }


    public Coordinates(double latitude1, double longitude1, double latitude2, double longitude2) {
        this.latitude1 = latitude1;
        this.longitude1 = longitude1;
        this.latitude2 = latitude2;
        this.longitude2 = longitude2;
    }
}

