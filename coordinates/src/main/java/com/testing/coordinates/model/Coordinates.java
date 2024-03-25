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

    public Coordinates() {

    }
    public Coordinates(double lat1, double lon1, double lat2, double lon2, double distance) {
            this.latitude1 = lat1;
            this.longitude1 = lon1;
            this.latitude2 = lat2;
            this.longitude2 = lon2;
            this.distance = distance;
        }

}

