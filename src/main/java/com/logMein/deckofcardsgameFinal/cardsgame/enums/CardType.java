package com.logMein.deckofcardsgameFinal.cardsgame.enums;

/**
 * CardType contains different type of cards
 */
public enum CardType {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private int value;
    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Get a card type from a its value
     *
     * @param value the integer value of a card
     * @return the card type
     */
    public static CardType fromValue(int value){
        for(CardType cardType : values()){
            if( cardType.value == value){
                return cardType;
            }
        }
        return null;
    }
}
