package com.testing.coordinates.Service.Imple;

import com.testing.coordinates.Service.CoordinatesService;
import com.testing.coordinates.model.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesServiceImple implements CoordinatesService {
    private static final double EARTH_RADIUS = 6371;
    @Override
    public double calculateDistance(Coordinates coordinates) {
        System.out.println("Inside Calculate Distance method");
            //converting degree to radians.
            double lat1 = Math.toRadians(coordinates.getLatitude1());
            double lon1 = Math.toRadians(coordinates.getLongitude1());
            double lat2 = Math.toRadians(coordinates.getLatitude2());
            double lon2 = Math.toRadians(coordinates.getLongitude2());

        //finding the distance between two latitude and longitude.
        double a = Math.pow(Math.sin((lat2 - lat1) / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin((lon2 - lon1) / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance;
    }
}