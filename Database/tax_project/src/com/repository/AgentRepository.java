package com.repository;

import com.entities.Agent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sergey on 25.12.2016.
 */
public interface AgentRepository extends CrudRepository<Agent, Integer> {
}
