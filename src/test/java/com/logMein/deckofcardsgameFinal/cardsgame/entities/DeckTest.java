package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardSuit;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardType;
import com.logMein.deckofcardsgameFinal.cardsgame.repository.IDeckRepository;
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
 * Test Deck entity
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DeckTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IDeckRepository deckRepository;

    List<Deck> decks;

    @Before
    public void setUp() throws Exception {
        // given
        Deck deck = new Deck();

        entityManager.persist(deck);
        entityManager.flush();

        // when
        decks = (List<Deck>) deckRepository.findAll();
    }

    @Test
    public void getCards() {

        // then
        assertTrue(decks.get(0).getCards().size() == 52);
    }

    @After
    public void tearDown() throws Exception {
    }
}