package com.logMein.deckofcardsgameFinal.cardsgame.enums;

/**
 * The enum of the four suits of cards : hearts, spades, clubs and diamonds
 */
public enum CardSuit {
    HEARTS (1),
    SPADES(2),
    CLUBS(3),
    DIAMONDS(4);

    private int value;

    CardSuit(int value) {

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Get a suit of card from its value
     * @param value suit value
     * @return the suit of card
     */
    public static CardSuit fromValue(int value){
        for(CardSuit cardSuit : values()){
            if( cardSuit.value == value){
                return cardSuit;
            }
        }
        return null;
    }
}
