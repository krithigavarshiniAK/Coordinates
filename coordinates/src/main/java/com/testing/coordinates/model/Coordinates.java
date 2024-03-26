package com.testing.coordinates.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Coordinates {

    @Getter
    private double latitude1;
    @Getter
    private double longitude1;
    @Getter
    private double latitude2;
    @Getter
    private double longitude2;
    @Getter
    private double distance;

    public Coordinates(double latitude_1, double longitude_1, double latitude_2, double longitude_2, double distance) {
            this.latitude1 = latitude_1;
            this.longitude1 = longitude_1;
            this.latitude2 = latitude_2;
            this.longitude2 = longitude_2;
            this.distance = distance;
        }
}

