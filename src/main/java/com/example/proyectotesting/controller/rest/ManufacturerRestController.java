package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.service.ManufacturerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManufacturerRestController {

    private ManufacturerService manufacturerService;

    public ManufacturerRestController (ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

}