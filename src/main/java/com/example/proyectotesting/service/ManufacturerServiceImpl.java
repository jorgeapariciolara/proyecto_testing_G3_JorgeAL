package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

    @Override
    public List<Manufacturer> findAll() {
        return null;
    }

    @Override
    public Optional<Manufacturer> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Manufacturer> findByYear(Integer year) {
        return null;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
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
