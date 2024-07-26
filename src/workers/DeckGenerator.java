package workers;

import card.Card;
import card.Pile;
import card.enums.Suit;
import card.enums.Value;

public class DeckGenerator {
    public static Pile generateDeck() {
        Pile deck = new Pile();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                if (value == Value.ACE_HIGH) continue;
                deck.add(new Card(suit, value));
            }
        }
        return deck;
    }

    public static Pile generateAces() {
        Pile deck = new Pile();
        for (Suit suit : Suit.values()) {
            deck.add(new Card(suit, Value.ACE_LOW));
        }
        return deck;
    }
}
