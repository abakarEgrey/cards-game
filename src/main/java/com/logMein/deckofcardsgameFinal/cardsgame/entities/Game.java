package com.jivecommunications.deckofcardsgame.entities;

import java.util.List;

public class Game {

    private Long gameIdentifier;
    private List<Player> players;
    private List<Card> shoe;

    public Game(Long gameIdentifier, List<Player> players, List<Deck> decks) {
        this.gameIdentifier = gameIdentifier;
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getShoe() {
        return shoe;
    }

    public Long getGameIdentifier() {
        return gameIdentifier;
    }
}
