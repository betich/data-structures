package game;

import java.util.ArrayList;
import java.util.Scanner;

import logic.*;

public class DeckManager {

  public static void main(String args[]) {

    // Change initial state of cards here
    UnitCard squirrel = new UnitCard("Squirrel", 0, 0, 1, "Sacrifices must be made.");
    UnitCard stoat = new UnitCard("Stoat", 1, 1, 3, "Bad play.");
    UnitCard wolf = new UnitCard("Wolf", 2, 3, 2, "The proud Wolf. A vicious contender.");
    UnitCard grizz = new UnitCard("Grizzly", 3, 4, 6, "The monstrous Grizzly. Its form speaks enough of its efficacy.");
    UnitCard urayuli = new UnitCard("Urayuli", 4, 7, 7, "This level of brutish strength needs no explanation...");

    ArrayList<UnitCard> cards = new ArrayList<>();

    cards.add(squirrel);
    cards.add(stoat);
    cards.add(wolf);
    cards.add(grizz);
    cards.add(urayuli);
    // Card initial state setup complete.

    // Change initial state of decks here
    ArrayList<UnitDeck> decks = new ArrayList<>();

    UnitDeck stoatDeck = new UnitDeck("Stoat Deck");
    stoatDeck.addCard(stoat, 1);
    stoatDeck.addCard(wolf, 2);
    stoatDeck.addCard(squirrel, 5);

    UnitDeck grizzDeck = new UnitDeck("BEAR DECK");
    grizzDeck.addCard(grizz, 3);
    grizzDeck.addCard(squirrel, 6);

    decks.add(stoatDeck);
    decks.add(grizzDeck);
    // Deck initial state setup complete.

    // DO NOT CHANGE ANYTHING BELOW THIS LINE
    boolean programRunning = true;
    Scanner sc = new Scanner(System.in);
    System.out.println("Kaycee's Deck Manager");
    System.out.println("==========");
    while (programRunning) {
      System.out.println("Choose Option:");
      System.out.println("1. View cards");
      System.out.println("2. Create new card");
      System.out.println("3. Import cards from text file");
      System.out.println("4. Delete card");
      System.out.println("5. View starter decks");
      System.out.println("6. Create new starter deck");
      System.out.println("7. Add to starter deck");
      System.out.println("8. Remove from starter deck");
      System.out.println("9. Delete starter deck");
      System.out.println("10. Quit");
      try {
        int choose = Integer.parseInt(sc.nextLine());

        switch (choose) {
          case 1:
            CardUtil.printCardList(cards, true);
            System.out.println("==========");
            break;
          case 2:
            System.out.println("Input card name.");
            String name = sc.nextLine();
            System.out.println("Input card cost.");
            int cost = Integer.parseInt(sc.nextLine());
            System.out.println("Input card power.");
            int pow = Integer.parseInt(sc.nextLine());
            System.out.println("Input card health.");
            int health = Integer.parseInt(sc.nextLine());
            System.out.println("Input flavor text.");
            String flavor = sc.nextLine();

            UnitCard newCard = new UnitCard(name, cost, pow, health, flavor);

            if (CardUtil.isExistsInList(newCard, cards)) {
              System.out.println("A card with this name already exists! No new card added.");
            } else {
              System.out.println("Added new card: " + newCard);

              cards.add(newCard);
            }
            System.out.println("==========");
            break;
          case 3:
            System.out.println("Name your input file (with extension):");
            String filename = sc.nextLine();

            ArrayList<UnitCard> cardsFromFile = CardUtil.getCardsFromFile(filename);

            if (cardsFromFile == null) {
              System.out.println("File error! No new card added.");
            } else {
              for (UnitCard fileCard : cardsFromFile) {
                if (CardUtil.isExistsInList(fileCard, cards)) {
                  System.out.println(fileCard + " already exists! (Not added.)");
                } else {
                  cards.add(fileCard);
                  System.out.println(fileCard + " found in " + filename + ". Added to the collection.");
                }
              }
            }

            break;
          case 4:
            System.out.println("Choose a card to delete.");
            CardUtil.printCardList(cards, false);

            int del = Integer.parseInt(sc.nextLine());

            UnitCard removed = cards.get(del);

            if (CardUtil.cardExistsInDeckList(decks, removed)) {
              System.out.println(removed + " still exists in some decks! It cannot be deleted.");
            } else {
              cards.remove(del);
              System.out.println(removed + " has been deleted.");
            }

            System.out.println("==========");
            break;
          case 5:
            CardUtil.printDeckList(decks);
            System.out.println("==========");
            break;
          case 6:
            System.out.println("Input deck name.");
            String deckName = sc.nextLine();

            UnitDeck newDeck = new UnitDeck(deckName);

            if (CardUtil.isExistsInList(newDeck, decks)) {
              System.out.println("A deck with this name already exists! No new deck added.");
            } else {
              System.out.println("Added new deck: " + deckName);
              decks.add(newDeck);
            }
            System.out.println("==========");
            break;
          case 7:
            System.out.println("Choose deck to add to.");
            CardUtil.printDeckList(decks);
            int deckAdd = Integer.parseInt(sc.nextLine());

            System.out.println("Choose card to add to deck.");
            CardUtil.printCardList(cards, false);
            int cardAdd = Integer.parseInt(sc.nextLine());

            System.out.println("Choose how many to add.");
            int quantAdd = Integer.parseInt(sc.nextLine());

            decks.get(deckAdd).addCard(cards.get(cardAdd), quantAdd);

            String plural = quantAdd == 1 ? "copy" : "copies";

            System.out.println("Added " + quantAdd + " " + plural + " of " + cards.get(cardAdd) + " to "
                + decks.get(deckAdd).getDeckName() + ".");

            System.out.println("==========");
            break;
          case 8:
            System.out.println("Choose a deck to remove from.");
            CardUtil.printDeckList(decks);
            int deckRem = Integer.parseInt(sc.nextLine());

            System.out.println("Choose card to remove from deck.");
            CardUtil.printCardList(cards, false);
            int cardRem = Integer.parseInt(sc.nextLine());

            System.out.println("Choose how many to remove.");
            int quantRem = Integer.parseInt(sc.nextLine());

            if (decks.get(deckRem).existsInDeck(cards.get(cardRem))) {

              int realCount = 0;

              for (CardCounter getRealCount : decks.get(deckRem).getCardsInDeck()) {
                if (getRealCount.getCard().equals(cards.get(cardRem))) {
                  realCount = getRealCount.getCount();
                }
              }

              if (quantRem > realCount)
                quantRem = realCount;

              decks.get(deckRem).removeCard(cards.get(cardRem), quantRem);

              String plural2 = quantRem == 1 ? "copy" : "copies";

              System.out.println("Removed " + quantRem + " " + plural2 + " of " + cards.get(cardRem) + " from "
                  + decks.get(deckRem).getDeckName() + ".");
              System.out.println(cards.get(cardRem) + " is no longer in " + decks.get(deckRem).getDeckName() + ".");
            } else {
              System.out.println("This card doesn't exist in this deck!");
            }

            System.out.println("==========");
            break;
          case 9:
            System.out.println("Choose a deck to delete.");
            CardUtil.printDeckList(decks);
            int deckDel = Integer.parseInt(sc.nextLine());

            UnitDeck removedDeck = decks.remove(deckDel);

            System.out.println(removedDeck.getDeckName() + " has been deleted.");
            break;
          case 10:
            programRunning = false;
            sc.close();
            System.out.println("==========");
            break;
          default:
            System.out.println("Not a valid option! Input again.");
            System.out.println("==========");
        }
      } catch (Exception e) {
        System.out.println("Error input! Back to main menu.");
        System.out.println("==========");
      }
    }
  }
}