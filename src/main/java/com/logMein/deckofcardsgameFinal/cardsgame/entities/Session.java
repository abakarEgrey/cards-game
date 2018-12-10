package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;

@Entity
@Table(name = "session")
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private long lastGameId;
    private long lastPlayerId;

    public Session() {
        //this.lastGameId = new Random().nextInt();
        //this.lastGameId = new Random().nextInt();
    }

    public long getLastGameId() {
        return lastGameId;
    }

    public void setLastGameId(long lastGameId) {
        this.lastGameId = lastGameId;
    }

    public long getLastPlayerId() {
        return lastPlayerId;
    }

    public void setLastPlayerId(long lastPlayerId) {
        this.lastPlayerId = lastPlayerId;
    }

}
