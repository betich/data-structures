package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.UnitDeck;
import logic.CardUtil;
import logic.UnitCard;

public class CardUtilTest {
	
	UnitCard uc1;
	UnitCard uc2;
	UnitCard uc3;
	UnitCard uc4;
	UnitCard uc5;
	UnitCard uc6;
	
	UnitDeck ud;
	
	@BeforeEach
	void setup() {
		uc1 = new UnitCard("Amalgam", 2, 3, 3, "Canine. Hooved. Reptilian. Feathered. Insectoid. The Amalgam is all.");
		uc2 = new UnitCard("Mole Man", 1, 0, 6, "The stalwart Mole Man. The ultimate defense.");
		uc3 = new UnitCard("Stoat", 1, 1, 3, "Bad play.");
		uc4 = new UnitCard("Stunted Wolf", 1, 2, 2, "Take the film roll! Now!");
		uc5 = new UnitCard("Squirrel", 0, 0, 1, "Sacrifices must be made.");
		uc6 = new UnitCard("Geck", 0, 1, 1, "The uninspiring Geck. Perhaps you can find a use for it?");
		
		ud = new UnitDeck("TestDeck");
		
		ud.addCard(uc1, 4);
		ud.addCard(uc2, 3);
		ud.addCard(uc3, 1);
		ud.addCard(uc4, 1);
		ud.addCard(uc5, 5);
	}
	
	@Test
	void testIsExistsInListCard() {
		ArrayList<UnitCard> cardList = new ArrayList<UnitCard>();
		
		cardList.add(uc2);
		cardList.add(uc3);
		cardList.add(uc4);
		cardList.add(uc6);
		
		assertTrue(CardUtil.isExistsInList(uc2, cardList));
		assertTrue(CardUtil.isExistsInList(uc3, cardList));
		assertTrue(CardUtil.isExistsInList(uc4, cardList));
		assertTrue(CardUtil.isExistsInList(uc6, cardList));
		assertFalse(CardUtil.isExistsInList(uc1, cardList));
		assertFalse(CardUtil.isExistsInList(uc5, cardList));
		
		cardList.remove(uc5);  //->>>>> remove
		cardList.add(uc2); //->>>>> add
		
		assertFalse(CardUtil.isExistsInList(uc5, cardList));
		assertTrue(CardUtil.isExistsInList(uc2, cardList));
	}
	
	@Test
	void testIsExistsInListDeck() {
		UnitDeck ud2 = new UnitDeck("Hungry Child Deck");
		UnitDeck ud3 = new UnitDeck("Ijiraq Deck");
		UnitDeck ud4 = new UnitDeck("Fecundity Deck");
		UnitDeck ud5 = new UnitDeck("Bones Deck");
		UnitDeck ud6 = new UnitDeck("Mantis God Deck");
		
		ArrayList<UnitDeck> deckList = new ArrayList<UnitDeck>();
		
		deckList.add(ud2);
		deckList.add(ud3);
		deckList.add(ud4);
		deckList.add(ud5);
		
		assertTrue(CardUtil.isExistsInList(ud2, deckList));
		assertTrue(CardUtil.isExistsInList(ud3, deckList));
		assertTrue(CardUtil.isExistsInList(ud4, deckList));
		assertTrue(CardUtil.isExistsInList(ud5, deckList));
		assertFalse(CardUtil.isExistsInList(ud6, deckList));
		
		deckList.remove(ud3);
		deckList.add(ud6);
		
		assertTrue(CardUtil.isExistsInList(ud6, deckList));
		assertFalse(CardUtil.isExistsInList(ud3, deckList));
	}
	
	@Test
	void testCardExistsInDeckList() {
		UnitDeck ud2 = new UnitDeck("Hungry Child Deck");
		UnitDeck ud3 = new UnitDeck("Ijiraq Deck");
		UnitDeck ud4 = new UnitDeck("Fecundity Deck");
		UnitDeck ud5 = new UnitDeck("Bones Deck");
		
		ArrayList<UnitDeck> deckList = new ArrayList<UnitDeck>();
		
		deckList.add(ud2);
		deckList.add(ud3);
		deckList.add(ud4);
		deckList.add(ud5);
		
		ud2.addCard(uc5, 10);
		ud4.addCard(uc6, 3);
		
		assertTrue(CardUtil.cardExistsInDeckList(deckList, uc5));
		assertTrue(CardUtil.cardExistsInDeckList(deckList, uc6));
		assertFalse(CardUtil.cardExistsInDeckList(deckList, uc1));
		assertFalse(CardUtil.cardExistsInDeckList(deckList, uc2));
		assertFalse(CardUtil.cardExistsInDeckList(deckList, uc3));
		assertFalse(CardUtil.cardExistsInDeckList(deckList, uc4));
		
		//new from here on 
		ud2.removeCard(uc5, 10);
		assertFalse(CardUtil.cardExistsInDeckList(deckList, uc5));
		
		deckList.remove(ud4);
		assertFalse(CardUtil.cardExistsInDeckList(deckList, uc6));
		
		
	}

}
