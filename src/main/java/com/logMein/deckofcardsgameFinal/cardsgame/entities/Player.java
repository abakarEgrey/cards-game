package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The deck game player
 */
@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;
    @OneToMany(mappedBy="player")
    private List<Card> cards;
    private Long player_identifier;
    private int handValue;

    @ManyToOne
    @JoinColumn(name="game_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;

    /**
     * Constructor
     */
    public Player() {
    }

    /**
     * Constructor
     * @param player_identifier
     * @param name
     */
    public Player(Long player_identifier, String name) {

        this.handValue = 0;
        this.player_identifier = player_identifier;
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
        this.handValue = this.cards.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlayer_identifier() {
        return player_identifier;
    }

    public void setPlayer_identifier(Long player_identifier) {
        this.player_identifier = player_identifier;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public int getHandValue() {
        return handValue;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
