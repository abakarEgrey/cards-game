package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardSuit;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardType;
import com.logMein.deckofcardsgameFinal.cardsgame.repository.ICardRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test Card entity
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CardTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICardRepository cardRepository;

    private Card foundCard;

    @Before
    public void setUp() throws Exception {
        // given
        Game game = new Game();
        Player player = new Player();
        player.setGame(game);
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        Card card = new Card(CardSuit.HEARTS, CardType.QUEEN, deck);
        card.setPlayer(player);
        card.setGame(game);
        cards.add(card);

        entityManager.persist(game);
        entityManager.persist(deck);
        entityManager.persist(player);
        entityManager.persist(card);
        entityManager.flush();

        // when
        List<Card> foundCards = (List<Card>) cardRepository.findAll();
        foundCard = foundCards.get(0);

    }

    @Test
    public void getCardSuit() {

        //Then
        assertEquals(foundCard.getCardSuit().toString(), CardSuit.HEARTS.toString());


    }

    @Test
    public void getCardType() {

        //Then
        assertEquals(foundCard.getCardType().toString(), CardType.QUEEN.toString());
    }


    @After
    public void tearDown() throws Exception {
    }
}