package com.logMein.deckofcardsgameFinal.cardsgame.repository;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IGameRepository extends CrudRepository<Game, Long> {
}
