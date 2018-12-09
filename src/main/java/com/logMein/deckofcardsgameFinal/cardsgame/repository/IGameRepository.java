package com.logMein.deckofcardsgameFinal.cardsgame.repository;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Game;
import org.springframework.data.repository.CrudRepository;

public interface IGameRepository extends CrudRepository<Game, Long> {
}
