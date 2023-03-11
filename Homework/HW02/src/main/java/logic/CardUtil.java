package logic;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CardUtil {

  public static boolean isExistsInList(UnitCard card, ArrayList<UnitCard> list) {
    return list.stream().filter((c) -> c.equals(card)).findFirst().isPresent();
  }

  public static boolean isExistsInList(UnitDeck deck, ArrayList<UnitDeck> list) {
    return list.stream().filter((d) -> d.equals(deck)).findFirst().isPresent();
  }

  public static boolean cardExistsInDeckList(ArrayList<UnitDeck> deckList, UnitCard cardToTest) {
    boolean exists = false;
    for (UnitDeck deck : deckList) {
      if (deck.existsInDeck(cardToTest)) {
        exists = true;
      }
    }
    return exists;
  }

  public static ArrayList<UnitCard> getCardsFromFile(String filename) {

    File fileToRead = new File(filename);
    ArrayList<UnitCard> cardsFromFile = new ArrayList<>();

    try {
      Scanner reader = new Scanner(fileToRead);

      while (reader.hasNextLine()) {
        String data = reader.nextLine();
        String[] cardData = data.split(",");
        UnitCard newCard = new UnitCard(cardData[0], Integer.parseInt(cardData[1]), Integer.parseInt(cardData[2]),
            Integer.parseInt(cardData[3]), cardData[4]);

        cardsFromFile.add(newCard);
      }

      reader.close();

      return cardsFromFile;
    } catch (FileNotFoundException e) {
    System.out.println("Cannot find file!");
    return null;
  } catch (NumberFormatException e) {
    System.out.println("File contains string with incorrect format!");
    return null;
  }
  }

  public static void printCardList(ArrayList<UnitCard> cardList, boolean verbose) {

    for (int i = 0; i < cardList.size(); i++) {
      System.out.println(i + ") " + cardList.get(i));
      if (verbose) {
        System.out.println("Blood Cost: " + cardList.get(i).getBloodCost());
        System.out.println(cardList.get(i).getFlavorText());
        if (i < cardList.size() - 1)
          System.out.println("-----");
      }
    }
  }

  public static void printDeck(UnitDeck ud) {

    if (ud.getCardsInDeck().size() == 0) {
      System.out.println("EMPTY DECK");
    } else {
      for (CardCounter cc : ud.getCardsInDeck()) {
        System.out.println(cc);
      }
    }

    System.out.println("Total Cards: " + ud.cardCount());
  }

  public static void printDeckList(ArrayList<UnitDeck> deckList) {
    for (int i = 0; i < deckList.size(); i++) {
      System.out.println(i + ") " + deckList.get(i).getDeckName());
      printDeck(deckList.get(i));
      if (i < deckList.size() - 1)
        System.out.println("-----");
    }
  }
}