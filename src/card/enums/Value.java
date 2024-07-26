package card.enums;

public enum Value {
    ACE_LOW(1, "Ace"),
    TWO(2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(11, "Jack"),
    QUEEN(12, "Queen"),
    KING(13, "King"),
    ACE_HIGH(14, "Ace");

    private final int value;
    private final String name;

    Value(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Value getFromInt(int i) {
        for (Value value : Value.values()) {
            if (value.getValue() == i) return value;
        }
        throw new IndexOutOfBoundsException("i must be between 1 and 14!");
    }

    public String getShortName() {
        if (getValue() < 2 || getValue() > 10) {
            return String.valueOf(getName().charAt(0));
        }
        return String.valueOf(getValue());
    }

}
