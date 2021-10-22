package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionServiceImpl implements DirectionService{
    @Override
    public List<Direction> findAll() {
        return null;
    }

    @Override
    public Optional<Direction> findOne(Long id) {
        return Optional.empty();
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
}
