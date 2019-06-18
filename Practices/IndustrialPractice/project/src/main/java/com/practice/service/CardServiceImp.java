package com.practice.service;

import com.practice.entity.Card;
import com.practice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    private CardRepository cr;

    public void add(Card card) {
        cr.save(card);
    }

    public List<Card> getAll() {
        return (List<Card>) cr.findAll();
    }

    public List<Card> getRange(Integer idClient){
        return (List<Card>) cr.findByIdClient(Integer.SIZE);
    }

    public List<Card> getRange(Iterable<Integer> idCards){
        return (List<Card>) cr.findAll(idCards);
    }

    public Card getOne(String number){
        return cr.findByNumber(number);
    }

    public Card getOne(Integer id) {
        return cr.findOne(id);
    }

    public void delete(Integer id) {
        cr.delete(id);
    }

    public void delete(String number) {
        cr.deleteByNumber(number);
    }

    public void deleteRange(Integer idClient){
        cr.deleteByIdClient(idClient);
    }

    public void deleteRange(Iterable<Card> cards){
        cr.delete(cards);
    }

    public void clear() {
        cr.deleteAll();
    }

    public void edit(Card card) {
        cr.save(card);
    }
}
