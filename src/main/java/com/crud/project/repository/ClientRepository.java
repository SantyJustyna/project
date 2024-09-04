package com.crud.project.repository;

import com.crud.project.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Override
    List<Client> findAll();

    @Override
    Client save(Client client);

    @Override
    Optional<Client> findById(Long id);

    @Override
    void deleteById(Long id);
}
