package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardSuit;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardSuit cardSuit;
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name="deck_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Deck deck;

    @ManyToOne
    @JoinColumn(name="player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;

    @ManyToOne
    @JoinColumn(name="game_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;

    /**
     * Constructor without args
     */
    public Card() {
    }

    /**
     * Constructor
     * @param cardSuit
     * @param cardType
     */
    public Card(CardSuit cardSuit, CardType cardType) {
        this.cardSuit = cardSuit;
        this.cardType = cardType;
    }

    /**
     * Constructor
     * @param cardSuit
     * @param cardType
     * @param deck
     */
    public Card(CardSuit cardSuit, CardType cardType, Deck deck) {
        this.cardSuit = cardSuit;
        this.cardType = cardType;
        this.deck = deck;
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

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
