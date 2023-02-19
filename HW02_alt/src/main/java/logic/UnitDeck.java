package logic;

import java.util.ArrayList;

public class UnitDeck {
    private ArrayList<CardCounter> cardsInDeck;
    private String deckName;

    public UnitDeck(String deckName) {
        this.cardsInDeck = new ArrayList<CardCounter>();
        setDeckName(deckName);
    }

    public ArrayList<CardCounter> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<CardCounter> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        if (deckName.isBlank()) {
            this.deckName = "Untitled Deck";
        } else {
            this.deckName = deckName;
        }
    }

    public void addCard(UnitCard newCard, int count) {
        if (count <= 0) {
            return;
        }
        boolean check = false;
        for (CardCounter c : cardsInDeck) {
            if (c.getCard().equals(newCard)) {
                check = true;
                c.setCount(c.getCount() + count);
            }
        }
        if (check == false) {
            CardCounter n = new CardCounter(newCard, count);
            this.cardsInDeck.add(n);
        }
    }

    public void removeCard(UnitCard toRemove, int count) {
        if (count <= 0) {
            return;
        }

        boolean remove = false;

        for (CardCounter c : cardsInDeck) {
            if (c.getCard().equals(toRemove)) {
                if (c.getCount() <= count) {
                    remove = true;
                } else {
                    c.setCount(c.getCount() - count);
                }
            }
        }

        if (remove) {
            this.cardsInDeck.removeIf((m) -> m.getCard().equals(toRemove));
        }
    }

    public int cardCount() {
        int count = 0;
        for (CardCounter counter : cardsInDeck) {
            count += counter.getCount();
        }
        return count;
    }

    public boolean equals(UnitDeck other) {
        return deckName.equals(other.getDeckName());
    }

    public boolean existsInDeck(UnitCard card) {
        boolean check = false;
        for (CardCounter c : cardsInDeck) {
            if (c.getCard().equals(card)) {
                check = true;
            }
        }
        return check;
    }
}
