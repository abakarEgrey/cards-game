package com.logMein.deckofcardsgameFinal.cardsgame.services;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Session;
import com.logMein.deckofcardsgameFinal.cardsgame.repository.ISessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Session service
 */
@Service
public class SessionService implements ISessionService {

    @Autowired
    private ISessionRepository sessionRepository;

    /**
     *
     * @return
     */
    @Override
    public Session getSession() {
        return new Session();
    }

    /**
     * Save Session .
     * @param session
     * @return
     */
    @Override
    public Session save(Session session) {
        Session lSession = this.sessionRepository.save(session);
        return lSession;
    }

    /**
     * Get all Sessions stored
     * @return
     */
    @Override
    public List<Session> findAll() {
        List<Session> documentIdentifiers = (List<Session>) this.sessionRepository.findAll();
        return documentIdentifiers;
    }

    /**
     * Delete a Session
     */
    @Override
    public void delete(Session session) {
        this.sessionRepository.delete(session);
    }

    /**
     * Increment the game id .
     * @return the incremented id
     */
    @Override
    public long incrementGameId() {
        Session session = this.getSession();
        long l = session.getLastGameId() + 1;
        session.setLastGameId(l);
        this.save(session);
        return l;
    }

    /**
     * Increment the player Id
     * @return the incremented id
     */
    @Override
    public long incrementPlayerId() {
        Session session = this.getSession();
        long l = session.getLastPlayerId() + 1;
        session.setLastPlayerId(l);
        this.save(session);
        return l;
    }

}
