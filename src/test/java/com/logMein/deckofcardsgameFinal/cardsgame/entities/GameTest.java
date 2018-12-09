package com.logMein.deckofcardsgameFinal.cardsgame.entities;

import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardSuit;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardType;
import com.logMein.deckofcardsgameFinal.cardsgame.repository.IGameRepository;
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
 * Test Deck Game entity
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class GameTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IGameRepository gameRepository;

    private List<Game> games;

    @Before
    public void setUp() throws Exception {
        // given
        Long gameIdentifier = 101L;
        List<Player> players = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        Player player = new Player();
        Player player1 = new Player();
        players.add(player);
        players.add(player1);
        Card cardQueen = new Card(CardSuit.HEARTS, CardType.QUEEN);
        Card cardKing = new Card(CardSuit.HEARTS, CardType.KING);
        Card cardJack = new Card(CardSuit.HEARTS, CardType.JACK);
        Card cardAce = new Card(CardSuit.HEARTS, CardType.ACE);
        cards.add(cardQueen);
        cards.add(cardKing);
        cards.add(cardJack);
        cards.add(cardAce);
        Game game = new Game(gameIdentifier, players);
        game.setShoe(cards);

        entityManager.persist(game);
        entityManager.flush();

        // when
        games = (List<Game>) gameRepository.findAll();
    }

    @Test
    public void getPlayers() {

        //then
        assertTrue(games.get(0).getPlayers().size() == 2);
    }

    @Test
    public void getShoe() {

        //then
        assertTrue(games.get(0).getShoe().size() == 4);
    }

    @After
    public void tearDown() throws Exception {
    }

}