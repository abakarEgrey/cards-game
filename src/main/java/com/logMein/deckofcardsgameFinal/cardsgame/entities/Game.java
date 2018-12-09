package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import javax.persistence.*;
import java.util.List;

/**
 * The deck game contains players and decks
 */
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private Long gameIdentifier;
    @OneToMany(mappedBy="game")
    private List<Player> players;
    @OneToMany(mappedBy="game")
    private List<Card> shoe;

    public Game() {
    }

    public Game(Long gameIdentifier, List<Player> players) {
        this.gameIdentifier = gameIdentifier;
        this.players = players;
    }

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

    public void setShoe(List<Card> shoe) {
        this.shoe = shoe;
    }
}
