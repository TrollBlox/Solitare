package card.enums;

public enum Suit {
    SPADES(Color.BLACK, "Spades"),
    DIAMONDS(Color.RED, "Diamonds"),
    CLUBS(Color.BLACK, "Clubs"),
    HEARTS(Color.RED, "Hearts");

    private final Color color;
    private final String name;

    Suit(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return String.valueOf(name.charAt(0));
    }

}
