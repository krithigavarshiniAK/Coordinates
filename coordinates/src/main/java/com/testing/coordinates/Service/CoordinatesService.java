package com.testing.coordinates.Service;

import com.testing.coordinates.model.Coordinates;

public interface CoordinatesService {
    public double calculateDistance(Coordinates coordinates);

    public double calculateDistance1(Coordinates coordinates, double lat2, double lon2);

    public double calculateDistance2(double lat1, double lon1, double lat2, double lon2);

}
