package com.logMein.deckofcardsgameFinal.cardsgame.services;

import com.logMein.deckofcardsgameFinal.cardsgame.entities.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.mvc.Controller;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Test Session entity
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SessionServiceTest {

    @Autowired
    private ISessionService sessionService;

    private List<Session> sessions;

    @Before
    public void setUp() throws Exception {
        // given
        long lastGameId = 22;
        long lastPlayerId = 67;
        Session session = new Session();
        session.setLastGameId(lastGameId);
        session.setLastPlayerId(lastPlayerId);

        sessionService.save(session);

        // when
        sessions = (List<Session>) sessionService.findAll();
    }

    @Test
    public void incrementGameId() {
        // then
        assertTrue(sessionService.incrementGameId() == 1);
    }

    @Test
    public void incrementPlayerId() {
        // then
        assertTrue(sessionService.incrementPlayerId() == 1);
    }

    @After
    public void tearDown() throws Exception {
    }
}