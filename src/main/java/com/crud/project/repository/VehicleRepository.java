package com.crud.project.repository;

import com.crud.project.domain.Client;
import com.crud.project.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    @Override
    List<Vehicle> findAll();

    @Override
    Vehicle save(Vehicle vehicle);

    @Override
    Optional<Vehicle> findById(Long id);

    @Override
    void deleteById(Long id);
}