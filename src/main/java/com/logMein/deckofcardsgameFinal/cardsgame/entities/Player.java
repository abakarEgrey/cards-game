package com.jivecommunications.deckofcardsgame.entities;

import java.util.List;

public class Player {

    private String name;
    private List<Card> cards;
    private Long player_id;
    private int handValue;

    public Player(Long player_id, String name) {

        this.handValue = 0;
        this.player_id = player_id;
        this.name = name;
    }


    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlayer_id() {
        return player_id;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public int getHandValue() {
        return handValue;
    }
}
