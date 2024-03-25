
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
    public ResponseEntity<Double> calculateDistance(@RequestBody Coordinates coordinates){
        double distance = coordinatesService.calculateDistance(coordinates);
        return new ResponseEntity<Double>(distance, HttpStatus.OK);
    }

    @PostMapping("/distance2")
    public ResponseEntity<Double> calculateDistance1(@RequestBody Coordinates coordinates,
                                                     @RequestParam(required = true) double lat2,
                                                     @RequestParam(required = true) double lon2){
        double distance = coordinatesService.calculateDistance1(coordinates, lat2, lon2);
        return new ResponseEntity<Double>(distance, HttpStatus.OK);
    }

    @PostMapping("/distance3")
    public ResponseEntity<Double> calculateDistance2(@RequestParam(required = true) double lat1,
                                                     @RequestParam(required = true) double lon1,
                                                     @RequestParam(required = true) double lat2,
                                                     @RequestParam(required = true) double lon2){
        double distance = coordinatesService.calculateDistance2(lat1, lon1, lat2, lon2);
        return new ResponseEntity<Double>(distance, HttpStatus.OK);
    }
}