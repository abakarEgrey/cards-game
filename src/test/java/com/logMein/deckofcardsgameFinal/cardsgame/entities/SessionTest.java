package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import com.logMein.deckofcardsgameFinal.cardsgame.repository.ISessionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test Session entity
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SessionTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ISessionRepository sessionRepository;

    private List<Session> sessions;

    @Before
    public void setUp() throws Exception {
        // given
        long lastGameId = 22;
        long lastPlayerId = 67;
        Session session = new Session();
        session.setLastGameId(lastGameId);
        session.setLastPlayerId(lastPlayerId);

        entityManager.persist(session);
        entityManager.flush();

        // when
        sessions = (List<Session>) sessionRepository.findAll();
    }

    @Test
    public void getLastGameId() {

        //then
        assertTrue(sessions.get(0).getLastGameId() == 22);
    }

    @Test
    public void getLastPlayerId() {

        //then
        assertTrue(sessions.get(0).getLastPlayerId()== 67);
    }

    @After
    public void tearDown() throws Exception {
    }

}