
package com.testing.coordinates.Controller;

import com.testing.coordinates.Service.CoordinatesService;
import com.testing.coordinates.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/Coordinates")
public class CoordinatesController {
    @Autowired
    CoordinatesService coordinatesService;
    @GetMapping("/coord-test")
    public String coord(){
        return "Coordinates";
    }
    @PostMapping("/distance")
    public ResponseEntity<Double> getDistanceWithFixedSourceAndDestination(@RequestBody Coordinates coordinates){
        double distance = coordinatesService.getDistanceWithFixedSourceAndDestination(coordinates);
        return new ResponseEntity<Double>(distance, HttpStatus.OK);
    }

    @PostMapping("/distance2")
    public ResponseEntity<Double> getDistanceWithFixedSource(@RequestBody Coordinates coordinates,
                                                     @RequestParam(required = true) double latitude2,
                                                     @RequestParam(required = true) double longitude2){
        double distance = coordinatesService.getDistanceWithFixedSource(coordinates, latitude2, longitude2);
        return new ResponseEntity<Double>(distance, HttpStatus.OK);
    }

    @PostMapping("/distance3")
    public ResponseEntity<Double> getDistanceWithDynamicSourceAndDestination(@RequestParam(required = true) double latitude1,
                                                     @RequestParam(required = true) double longitude1,
                                                     @RequestParam(required = true) double latitude2,
                                                     @RequestParam(required = true) double longitude2){
        double distance = coordinatesService.getDistanceWithDynamicSourceAndDestination(latitude1, longitude1, latitude2, longitude2);
        return new ResponseEntity<Double>(distance, HttpStatus.OK);
    }
}