package com.jivecommunications.deckofcardsgame.entities;

import com.jivecommunications.deckofcardsgame.enums.CardSuit;
import com.jivecommunications.deckofcardsgame.enums.CardType;

public class Card {

    private CardSuit cardSuit;
    private CardType cardType;

    public Card(CardSuit cardSuit, CardType cardType) {
        this.cardSuit = cardSuit;
        this.cardType = cardType;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
