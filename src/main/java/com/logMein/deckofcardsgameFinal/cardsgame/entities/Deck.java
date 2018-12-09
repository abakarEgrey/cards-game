package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardSuit;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Deck contains exactly 52 playing cards
 */
@Entity
@Table(name = "deck")
public class Deck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToMany(mappedBy="deck")
    private final List<Card> cards;


    public Deck() {
        this.cards = new ArrayList<>();
        for(int i = 1; i <= 4; i++) {
            CardSuit cardSuit = CardSuit.fromValue(i);
            for(int j = 1; j <= 13; j++) {
                CardType cardType = CardType.fromValue(j);
                cards.add(new Card(cardSuit, cardType));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
