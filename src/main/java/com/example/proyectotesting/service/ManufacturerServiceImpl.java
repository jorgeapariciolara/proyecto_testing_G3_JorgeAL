package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

    private ManufacturerRepository repository;

    public ManufacturerServiceImpl(ManufacturerRepository repository){
        this.repository = repository;
    }

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

    @Override
    public List<Manufacturer> findManufacturerByCountry(String country) {
        List<Manufacturer> result = new ArrayList<>();
        if(country == null || country.isEmpty())
            return result;
         return repository.findManufacturerByCountry(country);
    }
}
