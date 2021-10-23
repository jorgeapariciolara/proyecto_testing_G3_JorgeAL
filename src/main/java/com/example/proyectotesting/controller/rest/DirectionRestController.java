package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.service.DirectionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectionRestController {

    private DirectionService directionService;

    public DirectionRestController (DirectionService directionService) {
        this.directionService = directionService;
    }
}
