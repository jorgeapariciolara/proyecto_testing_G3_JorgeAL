package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{

    List<Manufacturer> findAllByManufacturerId(Long id);

    List<Manufacturer> findAllByManufacturerIdOrManufacturerIdIsNull(Long id);

    List<Manufacturer> findAllByManufacturerIdIsNull();

    List<Manufacturer> findByManufacturerName(String name);

    List<Manufacturer> findByPriceBetween(Double min, Double max);

    Optional<Manufacturer> findAllByManufacturerId();

    List<Manufacturer> findByYear(Integer year);
}
