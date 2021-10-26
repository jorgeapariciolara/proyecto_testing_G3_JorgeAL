package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.repository.DirectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DirectionServiceImpl implements DirectionService{

    private DirectionRepository repository;

    @Override
    public List<Direction> findAll() {
        return null;
    }

    @Override
    public Direction save(Direction direction) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<Direction> findByCityAndCountry(String city, String country) {
        List<Direction> result = new ArrayList<>();
        if (city == null || country == null)
            return result;
        return repository.findByCityAndCountry(city,country);
    }
}
