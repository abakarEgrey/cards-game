package com.jivecommunications.deckofcardsgame.enums;

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


    public static CardSuit fromValue(int value){
        for(CardSuit cardSuit : values()){
            if( cardSuit.value == value){
                return cardSuit;
            }
        }
        return null;
    }
}
