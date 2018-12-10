package com.logMein.deckofcardsgameFinal.cardsgame.services;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Session;

import java.util.List;

public interface ISessionService {

    public Session getSession();
    public Session save(Session session);
    public List<Session> findAll();
    public void delete(Session session);
    public long incrementGameId();
    public long incrementPlayerId();

}
