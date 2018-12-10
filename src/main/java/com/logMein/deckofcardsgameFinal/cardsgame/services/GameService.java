package com.jivecommunications.deckofcardsgame.services;

import com.jivecommunications.deckofcardsgame.entities.Card;
import com.jivecommunications.deckofcardsgame.entities.Deck;
import com.jivecommunications.deckofcardsgame.entities.Game;
import com.jivecommunications.deckofcardsgame.entities.Player;
import com.jivecommunications.deckofcardsgame.enums.CardSuit;
import com.jivecommunications.deckofcardsgame.enums.CardType;

import java.util.*;

public class GameService {

    private final Map<Long,Game>  games;

    private SessionService sessionService;

    public GameService() {
        this.sessionService = SessionService.getInstance();
        this.games = new HashMap<>();
    }

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

    public boolean addDeck(long idGame, Deck deck) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return false;
        }

        game.getShoe().addAll(deck.getCards());
        this.save(game);

        return true;
    }

    public boolean addPlayer(long idGame, Player player) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return false;
        }

        game.getPlayers().add(player);
        this.save(game);

        return true;
    }

    public boolean removePlayer(long idGame, long idPlayer) {
        Game game = this.getGameById(idGame);

        if(game == null) {
            return false;
        }

        List<Player> players = game.getPlayers();
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if(idPlayer == player.getPlayer_id()) {
                iterator.remove();
                this.save(game);
                return true;
            }
        }

        return false;
    }

    public List<Card> getCardsOf(Long idGame, Long idPlayer){
        Game game = this.getGameById(idGame);

        if(game == null) {
            return null;
        }

        List<Player> players = game.getPlayers();
        for(Player player :  players) {
            if(idPlayer.equals(player.getPlayer_id())) {
                return player.getCards();
            }
        }

        return null;
    }

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

    public Game save(Game game) {

        return game;
    }


}
