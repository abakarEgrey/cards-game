package com.jivecommunications.deckofcardsgame.services;

import com.jivecommunications.deckofcardsgame.entities.Session;

public class SessionService {

    private static SessionService instance = new SessionService();

    public static SessionService getInstance() {
        return instance;
    }

    private SessionService() {
    }

    public Session getSession() {
        return new Session();
    }

    public Session save(Session session) {
        return session;

    }

    public long incrementGameId() {
        Session session = this.getSession();
        long l = session.getLastGameId() + 1;
        session.setLastGameId(l);
        this.save(session);
        return l;
    }


    public long incrementPlayerId() {
        Session session = this.getSession();
        long l = session.getLastPlayerId() + 1;
        session.setLastPlayerId(l);
        this.save(session);
        return l;
    }

}
