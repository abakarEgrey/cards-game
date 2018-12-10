package com.logMein.deckofcardsgameFinal.cardsgame.services;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Card;
import com.logMein.deckofcardsgameFinal.cardsgame.entities.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerService {

    @Autowired
    private SessionService sessionService;

    /**
     *
     * @param name
     * @return
     */
    public Player createPlayer(String name) {
        Long id = this.sessionService.incrementPlayerId();
        Player player = new Player(id, name);

        return player;
    }

    /**
     *
     * @param player
     * @param card
     * @return
     */
    public Player addCard(Player player, Card card) {
        player.getCards().add(card);
        int handValue  = player.getHandValue() ;
        handValue +=card.getCardType().getValue();
        player.setHandValue(handValue);
        return player;
    }

    /**
     *
     * @param player
     * @param card
     * @return
     */
    public Player removeCard(Player player, Card card) {
        player.getCards().remove(card);
        int handValue  = player.getHandValue() ;
        handValue -=card.getCardType().getValue();
        player.setHandValue(handValue);
        return player;
    }
}
