package com.testing.coordinates.Controller;

import com.testing.coordinates.Service.CoordinatesService;
import com.testing.coordinates.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
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
    public double calculateDistance(@RequestBody Coordinates coordinates){
        return coordinatesService.calculateDistance(coordinates);
    }
}

