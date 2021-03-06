package com.logMein.deckofcardsgameFinal.cardsgame.repository;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IPlayerRepository extends CrudRepository<Player, Long> {
}
