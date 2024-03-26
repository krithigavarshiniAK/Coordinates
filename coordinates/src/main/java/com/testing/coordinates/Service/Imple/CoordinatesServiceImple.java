package com.testing.coordinates.Service.Imple;

import com.testing.coordinates.Service.CoordinatesService;
import com.testing.coordinates.model.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesServiceImple implements CoordinatesService {
    private static final double EARTH_RADIUS = 6371;
    @Override
    public double getDistanceWithFixedSourceAndDestination(Coordinates coordinates) {
        System.out.println("Inside Calculate Distance method");
            //converting degree to radians.
            double latitude1 = Math.toRadians(coordinates.getLatitude1());
            double longitude1 = Math.toRadians(coordinates.getLongitude1());
            double latitude2 = Math.toRadians(coordinates.getLatitude2());
            double longitude2 = Math.toRadians(coordinates.getLongitude2());

        double meters = radiansIntoMeters(latitude1, longitude1, latitude2, longitude2);
        return meters;
    }
    @Override
    public double getDistanceWithFixedSource(Coordinates coordinates, double latitude2, double longitude2) {
        System.out.println("Inside Calculate Distance method 1");

        if (!isValidLatitude(latitude2) || !isValidLongitude(longitude2)) {
            throw new IllegalArgumentException("Invalid latitude or longitude values");
        }
        //converting degree to radians.
        double latitude1 = Math.toRadians(coordinates.getLatitude1());
        double longitude1 = Math.toRadians(coordinates.getLongitude1());
        latitude2 = Math.toRadians(latitude2);
        longitude2 = Math.toRadians(longitude2);

        //finding the distance between two latitude and longitude.
        double meters = radiansIntoMeters(latitude1, longitude1, latitude2, longitude2);
        return meters;
    }
    @Override
    public double getDistanceWithDynamicSourceAndDestination(double latitude1, double longitude1, double latitude2, double longitude2) {
        System.out.println("Inside Calculate Distance method 2");

        if (!isValidLatitude(latitude1) || !isValidLongitude(longitude1) || !isValidLatitude(latitude2) || !isValidLongitude(longitude2)) {
            throw new IllegalArgumentException("Invalid latitude or longitude values");
        }

        latitude1 = Math.toRadians(latitude1);
        longitude1 = Math.toRadians(longitude1);
        latitude2 = Math.toRadians(latitude2);
        longitude2 = Math.toRadians(longitude2);

        double meters = radiansIntoMeters(latitude1, longitude1, latitude2, longitude2);
        return meters;
    }

    private boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    private boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    //finding the distance between two latitude and longitude.
    private double radiansIntoMeters(double latitude1, double longitude1, double latitude2, double longitude2){
        double a = Math.pow(Math.sin((latitude2 - latitude1) / 2), 2) +
                Math.cos(latitude1) * Math.cos(latitude2) *
                        Math.pow(Math.sin((longitude2 - longitude1) / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c * 1000;

        return distance;
    }
}