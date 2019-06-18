package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    @Query("select c from Client c where c.name = :name")
    public Iterable<Client> findByName(@Param("name") String name);
}
