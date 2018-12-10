package com.jivecommunications.deckofcardsgame.services;

import com.jivecommunications.deckofcardsgame.entities.Card;
import com.jivecommunications.deckofcardsgame.entities.Game;
import com.jivecommunications.deckofcardsgame.entities.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerService {

    private SessionService sessionService;

    public PlayerService() {
        this.sessionService = SessionService.getInstance();
    }

    public Player createPlayer(String name) {
        Long id = this.sessionService.incrementPlayerId();
        Player player = new Player(id, name);

        return player;
    }

    public static Player addCard(Player player, Card card) {
        player.getCards().add(card);
        int handValue  = player.getHandValue() ;
        handValue +=card.getCardType().getValue();
        player.setHandValue(handValue);
        return player;
    }

    public static Player removeCard(Player player, Card card) {
        player.getCards().remove(card);
        int handValue  = player.getHandValue() ;
        handValue -=card.getCardType().getValue();
        player.setHandValue(handValue);
        return player;
    }
}
