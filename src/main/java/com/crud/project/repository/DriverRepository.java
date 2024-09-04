package com.crud.project.repository;

import com.crud.project.domain.Client;
import com.crud.project.domain.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
    @Override
    List<Driver> findAll();

    @Override
    Driver save(Driver driver);

    @Override
    Optional<Driver> findById(Long id);

    @Override
    void deleteById(Long id);
}
