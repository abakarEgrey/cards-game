package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardSuit;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardType;
import com.logMein.deckofcardsgameFinal.cardsgame.repository.IPlayerRepository;
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
 * Test Player entity
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IPlayerRepository playerRepository;

    private List<Player> players;


    @Before
    public void setUp() throws Exception {
        // given
        Game game = new Game();
        Player player = new Player(10L, "Messi");
        player.setGame(game);
        List<Card> cards = new ArrayList<>();
        Card cardQueen = new Card(CardSuit.HEARTS, CardType.QUEEN);
        Card cardKing = new Card(CardSuit.HEARTS, CardType.KING);
        Card cardJack = new Card(CardSuit.HEARTS, CardType.JACK);
        Card cardAce = new Card(CardSuit.HEARTS, CardType.ACE);
        cards.add(cardQueen);
        cards.add(cardKing);
        cards.add(cardJack);
        cards.add(cardAce);

        player.setCards(cards);

        entityManager.persist(game);
        entityManager.persist(player);
        entityManager.flush();

        // when
        players = (List<Player>) playerRepository.findAll();
    }

    @Test
    public void getCards() {

        // then
        assertTrue(players.get(0).getCards().size() == 4);
    }

    @Test
    public void getName() {

        // Then
        assertEquals(players.get(0).getName(), "Messi");
    }

    @Test
    public void getPlayer_identifier() {

        // Then
        assertEquals(players.get(0).getPlayer_identifier(), new Long(10L));
    }

    @Test
    public void getHandValue() {

        // Then
        assertEquals(players.get(0).getHandValue(), 4);
    }

    @After
    public void tearDown() throws Exception {
    }
}