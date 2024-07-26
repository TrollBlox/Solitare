package card;

import card.enums.Suit;

import java.util.*;

public class Pile extends ArrayList<Card> {

    private final List<Suit> allowedSuits;

    public static final IllegalStateException SUIT_MISMATCH = new IllegalStateException("That suit isn't allowed in that pile!");

    public Pile() {
        this(Suit.values());
    }

    public Pile(Suit... allowedSuits) {
        this(new ArrayList<>(), allowedSuits);
    }

    public Pile(List<Card> pileData, Suit... allowedSuits) {
        super(pileData);
        this.allowedSuits = new ArrayList<>(Arrays.stream(allowedSuits).toList());
    }

    public Card getTopCard() {
        return getLast();
    }

    public Card getBottomCard() {
        return getFirst();
    }

    @Override
    public boolean add(Card card) {
        if (!allowedSuits.contains(card.getSuit())) throw SUIT_MISMATCH;
        return super.add(card);
    }

    @Override
    public Card set(int index, Card card) {
        if (!allowedSuits.contains(card.getSuit())) throw SUIT_MISMATCH;
        return super.set(index, card);
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public Card removeTopCard() {
        Card card = getTopCard();
        remove(getTopCard());
        return card;
    }

}
