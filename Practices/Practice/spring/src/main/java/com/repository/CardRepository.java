package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.entity.Card;

public interface CardRepository extends CrudRepository<Card, Integer>{
    @Query("select c from Card c where c.number = :number")
    public Card findByNumber(@Param("number") String number);
    
    @Query("select c from Card c where c.idClient = :idClient")
    public Iterable<Card> findByIdClient(@Param("idClient") Integer idClient);
    
    @Query("delete c from Card c where c.number = :number")
    public void deleteByNumber(@Param("number") String number);
    
    @Query("select c from Card c where c.idClient = :idClient")
    public void deleteByIdClient(@Param("idClient") Integer idClient);
}