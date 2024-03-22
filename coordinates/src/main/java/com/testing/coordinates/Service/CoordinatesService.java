package com.testing.coordinates.Service;

import com.testing.coordinates.model.Coordinates;

public interface CoordinatesService {
    public double calculateDistance(Coordinates coordinates);

    public double calculateDistance2(Coordinates coordinates, double lat2, double lon2);
}
