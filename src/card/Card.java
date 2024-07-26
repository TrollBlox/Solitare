package card;

import card.enums.Suit;
import card.enums.Value;

public class Card {
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public String toString() {
        return "Card: Suit: " + suit.getName() + " Value: " + value.getName();
    }

}
