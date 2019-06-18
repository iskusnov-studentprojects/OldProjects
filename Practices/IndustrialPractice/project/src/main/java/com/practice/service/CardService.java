package com.practice.service;

import com.practice.entity.Card;

import java.util.List;

public interface CardService {
    void add(Card card);

    List<Card> getAll();

    Card getOne(Integer id);

    Card getOne(String number);

    List<Card> getRange(Iterable<Integer> ids);

    List<Card> getRange(Integer idClient);

    void delete(Integer id);

    void delete(String number);

    void deleteRange(Integer idClient);

    void deleteRange(Iterable<Card> cards);

    void clear();

    void edit(Card card);
}

