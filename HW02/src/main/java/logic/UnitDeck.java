package logic;

import java.util.ArrayList;
import java.util.Optional;

public class UnitDeck {
  private ArrayList<CardCounter> cardsInDeck;
  private String deckName;

  public UnitDeck(String deckName) {
    this.deckName = deckName.isBlank() ? "Untitled Deck" : deckName;
    this.cardsInDeck = new ArrayList<CardCounter>(0);
  }

  public void addCard(UnitCard unitCard, int count) {
    if (count <= 0)
      return;
    // Check if card exists
    Optional<CardCounter> card = this.cardsInDeck.stream().filter((c) -> c.getCard().equals(unitCard)).findFirst();
    if (card.isPresent()) {
      CardCounter foundCard = card.get();
      foundCard.setCount(foundCard.getCount() + count);
    } else {
      this.cardsInDeck.add(new CardCounter(unitCard, count));
    }
  }

  public void removeCard(UnitCard toRemove, int count) {
    if (count <= 0)
      return;
    // Check if card exists
    Optional<CardCounter> card = this.cardsInDeck.stream().filter((c) -> c.getCard().equals(toRemove)).findFirst();

    if (card.isPresent()) {
      // Check card count
      CardCounter foundCard = card.get();

      if (count >= foundCard.getCount()) {
        this.cardsInDeck.removeIf((c) -> c.getCard().equals(toRemove));
      } else {
        foundCard.setCount(foundCard.getCount() - count);
      }
    }
  }

  public int cardCount() {
    int count = 0;
    for (CardCounter counter : cardsInDeck) {
      count += counter.getCount();
    }
    return count;
  }

  public boolean existsInDeck(UnitCard card) {
    Optional<CardCounter> foundCard = this.cardsInDeck.stream().filter((c) -> c.getCard().equals(card)).findFirst();

    return foundCard.isPresent();
  }

  public boolean equals(UnitDeck other) {
    return deckName.equals(other.getDeckName());
  }

  public ArrayList<CardCounter> getCardsInDeck() {
    return cardsInDeck;
  }

  public void setCardsInDeck(ArrayList<CardCounter> cardsInDeck) {
    this.cardsInDeck = cardsInDeck;
  }

  public void setDeckName(String deckName) {
    this.deckName = deckName.isBlank() ? "Untitled Deck" : deckName;
  }

  public String getDeckName() {
    return deckName;
  }
}