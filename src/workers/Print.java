package workers;

import card.Card;
import card.Pile;

import java.util.*;
import java.util.stream.Collectors;

public class Print {
    public static final int CARD_WIDTH = 20;
    public static final int CARD_HEIGHT = 11;
    public static final int CARD_PREVIEW_HEIGHT = 3;
    public static final String CARD_TOP = "_";
    public static final String CARD_SIDE = "|";
    public static final String CARD_BOTTOM = "‾";
    public static final int CARDS_PER_SCREEN = 7;
    public static final int SPACES_BETWEEN_CARDS = 5;

    public static void printDeck(Pile deck) {
        List<Pile> groupedDeck = Utils.groupList(deck, CARDS_PER_SCREEN).stream().map(Pile::new).toList();
        int remainder = groupedDeck.getLast().size();
        for (int i = 0; i < groupedDeck.size(); i++) {
            List<Card> group = groupedDeck.get(i);
            final boolean lastGroup = i >= groupedDeck.size() - 1;
            int height = CARD_PREVIEW_HEIGHT;
            if (lastGroup) height = CARD_HEIGHT;
            for (int j = 0; j < height; j++) {
                List<Integer> sections = new ArrayList<>(CARDS_PER_SCREEN);
                for (int k = 0; k < (groupedDeck.size() > 1 ? CARDS_PER_SCREEN : remainder); k++) {
                    sections.add(j);
                    if (lastGroup && k >= remainder) {
                        sections.set(k, j + CARD_PREVIEW_HEIGHT);
                    }
                }
                while (group.size() < CARDS_PER_SCREEN && groupedDeck.size() > 1) {
                    group.add(groupedDeck.get(i - 1).get(group.size()));
                }
                printCardsSections(group, sections);
            }
        }
    }

    private static void printSpaces(int count) {
        String str = "";
        for (int i = 0; i < count; i++) {
            str = str.concat("\s");
        }
        System.out.print(str);
    }

    private static String spaces(int count) {
        String str = "";
        for (int i = 0; i < count; i++) {
            str = str.concat("\s");
        }
        return str;
    }

    private static void printCardsSections(List<Card> cards, List<Integer> sections) {
        if (cards.size() > CARDS_PER_SCREEN || sections.size() > CARDS_PER_SCREEN || sections.size() != cards.size()) {
            System.out.println(cards.size());
            System.out.println(sections.size());
            throw new IllegalStateException("Too many cards!");
        }
        for (int i = 0; i < cards.size(); i++) {
            try {
                printCard(cards.get(i)).get(sections.get(i)).run();
            } catch (Exception e) {
                printSpaces(CARD_WIDTH);
            }
            printSpaces(SPACES_BETWEEN_CARDS);
        }
        System.out.println();
    }

    private static List<Runnable> printCard(Card card) {
        List<Runnable> displayOrder = new ArrayList<>();
        displayOrder.add(Print::printCardTop);
        displayOrder.add(() -> printCardSectionWithLeftValue(card.getValue().getShortName()));
        displayOrder.add(() -> printCardSectionWithLeftValue(card.getSuit().getShortName()));
        displayOrder.add(Print::printCardSection);
        displayOrder.add(() -> printCardSectionWithCenterValue(card.getValue().getName()));
        displayOrder.add(() -> printCardSectionWithCenterValue(card.getSuit().getName()));
        displayOrder.add(() -> printCardSectionWithCenterValue(card.getValue().getName()));
        displayOrder.add(Print::printCardSection);
        displayOrder.add(() -> printCardSectionWithRightValue(card.getSuit().getShortName()));
        displayOrder.add(() -> printCardSectionWithRightValue(card.getValue().getShortName()));
        displayOrder.add(Print::printCardBottom);
        return displayOrder;
    }

    private static void printCardTop() {
        for (int i = 0; i < CARD_WIDTH; i++) {
            System.out.print(CARD_TOP);
        }
    }

    private static void printCardBottom() {
        for (int i = 0; i < CARD_WIDTH; i++) {
            System.out.print(CARD_BOTTOM);
        }
    }

    private static void printCardSectionWithLeftValue(String value) {
        int spaces = (CARD_WIDTH - 2) - value.length();
        System.out.print(CARD_SIDE + value + spaces(spaces) + CARD_SIDE);
    }

    private static void printCardSectionWithCenterValue(String value) {
        int spaces = (CARD_WIDTH - 2) - value.length();
        System.out.print(CARD_SIDE + spaces(spaces / 2) + value + spaces((spaces / 2) + (spaces % 2)) + CARD_SIDE);
    }

    private static void printCardSectionWithRightValue(String value) {
        int spaces = (CARD_WIDTH - 2) - value.length();
        System.out.print(CARD_SIDE + spaces(spaces) + value + CARD_SIDE);
    }

    private static void printCardSection() {
        System.out.print(CARD_SIDE + spaces(CARD_WIDTH - 2) + CARD_SIDE);
    }

}
