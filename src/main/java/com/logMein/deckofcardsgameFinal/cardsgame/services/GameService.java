package com.logMein.deckofcardsgameFinal.cardsgame.services;


import com.logMein.deckofcardsgameFinal.cardsgame.entities.Card;
import com.logMein.deckofcardsgameFinal.cardsgame.entities.Deck;
import com.logMein.deckofcardsgameFinal.cardsgame.entities.Game;
import com.logMein.deckofcardsgameFinal.cardsgame.entities.Player;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardSuit;
import com.logMein.deckofcardsgameFinal.cardsgame.enums.CardType;
import com.logMein.deckofcardsgameFinal.cardsgame.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Deck Game service
 */
@Service
public class GameService {

    private final Map<Long, Game>  games;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private IGameRepository gameRepository;

    public GameService() {
        this.games = new HashMap<>();
    }

    /**
     * Get game by id .
     * @param idGame the game id
     * @return the game
     */
    private Game getGameById(long idGame) {
        Game game;
        synchronized (this.games) {
            if (!this.games.containsKey(idGame)) {
                return null;
            }
            game = this.games.get(idGame);
        }
        return game;
    }

    /**
     * Create a new Game
     * @return the new game identifiant
     */
    public Long createGame() {
        Game game;
        synchronized (this.games) {
            Long id = this.sessionService.incrementGameId();

            game = new Game(id,
                    new ArrayList<>(), new ArrayList<>());

            this.games.put(id, game);
        }
        this.save(game);

        return game.getGameIdentifier();
    }

    /**
     * Delete a game
     * @param id the game id
     * @return true if the game is successfully deleted
     */
    public boolean deleteGame(Long id) {
        Game game;
        synchronized (this.games) {
            if (!this.games.containsKey(id)) {
                return false;
            }
            game = this.games.remove(id);
        }

        //System.out.println(game);
        return true;
    }

    /**
     * Add a new Deck to the game .
     * @param idGame the game id
     * @param deck the deck to add to the game
     * @return true if the deck is successfully added
     */
    public boolean addDeck(long idGame, Deck deck) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return false;
        }

        game.getShoe().addAll(deck.getCards());
        this.save(game);

        return true;
    }

    /**
     * Add a new player to the game
     * @param idGame the game id
     * @param player the player to add to the game
     * @return true if the player is successfully added
     */
    public boolean addPlayer(long idGame, Player player) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return false;
        }

        game.getPlayers().add(player);
        this.save(game);

        return true;
    }

    /**
     * Remove player from the game.
     * @param idGame     the game id
     * @param idPlayer   the player id
     * @return  true the player is successfully removed from the game
     */
    public boolean removePlayer(long idGame, long idPlayer) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return false;
        }

        List<Player> players = game.getPlayers();
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if(idPlayer == player.getPlayer_identifier()) {
                iterator.remove();
                this.save(game);
                return true;
            }
        }

        return false;
    }

    /**
     * Get the list of player cards
     * @param idGame    The game id
     * @param idPlayer   The player id
     * @return        The player cards
     */
    public List<Card> getCardsOf(Long idGame, Long idPlayer){
        Game game = this.getGameById(idGame);

        if(game == null) {
            return null;
        }

        List<Player> players = game.getPlayers();
        for(Player player :  players) {
            if(idPlayer.equals(player.getPlayer_identifier())) {
                return player.getCards();
            }
        }

        return null;
    }

    /**
     * Get the list of players in the game along with the total added value of all the cards each player holds
     * @param idGame  The game id
     * @return        The sorted list of players
     */
    public List<Player> getSortedListOfPlayersDesc(Long idGame) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return null;
        }

        List<Player> players= new ArrayList<>(game.getPlayers());
        game.getPlayers().sort((o1, o2) -> {
            if(o1.getHandValue() < o2.getHandValue())
                return 1;
            if(o1.getHandValue() > o2.getHandValue())
                return -1;
            return 0 ;
        });

        return players;
    }

    /**
     * Get cards count by suit
     * @param idGame   The game id
     * @return         the count of cards by suit
     */
    public Map<CardSuit, Integer> getCardCountBySuit(Long idGame) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return null;
        }

        Map<CardSuit, Integer> map = new HashMap<>();

        List<Card> cards = game.getShoe();

        for(int i = 1, count = 0; i <= 4; i++, count = 0) {
            CardSuit cardSuit = CardSuit.fromValue(i);
            for(Card card : cards) {
                if(cardSuit.getValue() ==
                        card.getCardSuit().getValue()){
                    count++;
                }
            }
            map.put(cardSuit, count);
        }

        return map;
    }

    /**
     * Get cards count by suit and value
     * @param idGame  The game id
     * @return         The cards count sorted by suit and value
     */
    public Map<Card, Integer> getCardCountBySuitAndValue(Long idGame) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return null;
        }

        Map<Card, Integer> map = new HashMap<>();
        List<Card> cards = game.getShoe();

        int[][] matrix = new int[4][13];

        for (Card card : cards) {

            int cardSuitValue = card.getCardSuit().getValue();
            int cardTypeValue = card.getCardType().getValue();

            matrix[cardSuitValue - 1][cardTypeValue - 1]++;
        }

        for(int i = 1; i <= 4; i++) {
            CardSuit cardSuit = CardSuit.fromValue(i);
            int cardSuitValue = cardSuit.getValue();

            for(int j = 13; j > 0; j--) {
                CardType cardType = CardType.fromValue(j);
                int cardTypeValue = cardType.getValue();

                map.put(new Card(cardSuit, cardType),
                        matrix[cardSuitValue - 1][cardTypeValue - 1]);
            }

        }

        return map;
    }
//
//    public Map<Card, Integer> getCardCountBySuitAndValue(Long idGame) {
//        Game game = this.getGameById(idGame);
//
//        if(game == null) {
//            return null;
//        }
//
//        Map<Card, Integer> map = new HashMap<>();
//        List<Card> cards = game.getShoe();
//
//        for(int i = 1; i <= 4; i++) {
//            CardSuit cardSuit = CardSuit.fromValue(i);
//            for(int j = 13, count = 0; j > 0; j--, count = 0) {
//                CardType cardType = CardType.fromValue(j);
//                for (Card card : cards) {
//                    if (cardSuit.getValue() ==
//                            card.getCardSuit().getValue() &&
//                            cardType.getValue() ==
//                                    card.getCardType().getValue()) {
//                        count++;
//                    }
//                }
//                map.put(new Card(cardSuit, cardType), count);
//            }
//
//        }
//
//        return map;
//    }

    /**
     * Permute randomly the cards .
     * @param idGame   The game id
     * @return         permuted cards
     */
    public boolean shuffle(int idGame) {

        Game game = this.getGameById(idGame);

        if(game == null) {
            return false;
        }

        List<Card> cards = game.getShoe();
        Random random = new Random();
        for(int i = 1, size = cards.size(); i < size; i++) {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            if(index1 == index2) {
                i--;
                continue;
            }
            cards.set(index1,
                    cards.set(index2, cards.get(index1)));
        }
        return true;
    }

    /**
     *
     * @param game
     * @return
     */
    public Game save(Game game) {

        Game game1 = gameRepository.save(game);
        return game1;
    }


}
