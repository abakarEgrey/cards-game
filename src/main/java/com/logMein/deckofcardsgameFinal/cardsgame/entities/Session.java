package com.jivecommunications.deckofcardsgame.entities;

import java.util.Random;

public class Session {

    private long lastGameId;
    private long lastPlayerId;

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

    public Session() {
        this.lastGameId = new Random().nextInt();
        this.lastGameId = new Random().nextInt();
    }
}
