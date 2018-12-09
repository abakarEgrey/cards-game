package com.logMein.deckofcardsgameFinal.cardsgame.repository;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The CRUD methods are already implemented
 */
@Repository
@Transactional
public interface ICardRepository extends CrudRepository<Card, Long> {

}
