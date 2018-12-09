package com.logMein.deckofcardsgameFinal.cardsgame.repository;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Deck;
import org.springframework.data.repository.CrudRepository;

public interface IDeckRepository extends CrudRepository<Deck, Long> {
}
