package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Direction;

import java.util.List;
import java.util.Optional;

public interface DirectionService {

    List<Direction> findAll();

    Optional<Direction> findOne(Long id);

    // TODO - agregar método para filtrar por ciudad y país a la vez
    
    Direction save(Direction direction);

    Long count();

    boolean deleteById(Long id);


    boolean deleteAll();
}
