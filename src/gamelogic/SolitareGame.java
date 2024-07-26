package gamelogic;

import card.Pile;
import card.enums.Suit;
import workers.DeckGenerator;
import workers.Print;
import java.util.HashMap;
import java.util.Map;

public class SolitareGame {
    private static final int NUMBER_OF_PILES_IN_PLAY = 7;

    private HashMap<Suit, Pile> acePiles = new HashMap<>();
    private HashMap<Integer, Pile> cardPiles = new HashMap<>();
    private Pile hand = new Pile();
    private Pile discard = new Pile();

    public SolitareGame() {
         for (Suit suit : Suit.values()) {
             acePiles.put(suit, new Pile(suit));
         }
         for (int i = 0; i < NUMBER_OF_PILES_IN_PLAY; i++) {
             cardPiles.put(i, new Pile());
         }
         deal();
    }

    private void deal() {
        Pile deck = DeckGenerator.generateDeck();
        deck.shuffle();
        for (int i = 0; i < NUMBER_OF_PILES_IN_PLAY; i++) {
            for (int j = i; j >= 0; j--) {
                cardPiles.get(j).add(deck.removeTopCard());
            }
        }
        hand = deck;
    }

    // ChatGPT
    public static Pile weaveLists(Map<Integer, Pile> map) {
        Pile result = new Pile();

        if (map.isEmpty()) {
            return result;
        }

        // Determine the maximum size of the lists in the map
        int maxSize = map.values().stream()
                .mapToInt(Pile::size)
                .max()
                .orElse(0);

        // Iterate over each index up to the maximum size
        for (int i = 0; i < maxSize; i++) {
            for (Pile pile : map.values()) {
                for (Pile pile1 : map.values()) Print.printDeck(pile1);

                // If the current list has an element at index i, add it to the result
                if (i >= pile.size()) {
//                    throw new IndexOutOfBoundsException("The piles weren't dealt properly!");
                    continue;
                }

                result.add(pile.get(i));
            }
        }

        return result;
    }

    private void render() {
        Print.printDeck(weaveLists(cardPiles));
    }

    public void start() {
        render();
    }

}
