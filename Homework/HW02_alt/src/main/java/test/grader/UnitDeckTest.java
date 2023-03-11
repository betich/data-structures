package test.grader;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.UnitDeck;
import logic.CardCounter;
import logic.UnitCard;

public class UnitDeckTest {
	
	UnitCard uc1;
	UnitCard uc2;
	UnitCard uc3;
	UnitCard uc4;
	UnitCard uc5;
	UnitCard uc6;
	
	UnitDeck ud;
	
	@BeforeEach
	void setup(){
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
	void testConstructor() {
		UnitDeck ud2 = new UnitDeck("Funny Deck");
		
		assertEquals("Funny Deck", ud2.getDeckName());
	}
	
	@Test
	void testBadConstructor() {
		UnitDeck ud2 = new UnitDeck("          ");
		
		assertEquals("Untitled Deck", ud2.getDeckName());
		
		ud2 = new UnitDeck("    ");  //new case
		
		assertEquals("Untitled Deck", ud2.getDeckName());
		
        ud2 = new UnitDeck(""); //new case
		
		assertEquals("Untitled Deck", ud2.getDeckName());
	}
	
	@Test
	void testSetName() {
		UnitDeck ud2 = new UnitDeck("Funny Deck");
		
		assertEquals("Funny Deck", ud2.getDeckName());
		
		ud2.setDeckName("Kingfisher Deck");
		
		assertEquals("Kingfisher Deck", ud2.getDeckName());
		
		ud2.setDeckName("     ");
		
		assertEquals("Untitled Deck", ud2.getDeckName());
		
		ud2.setDeckName("Funny Deck");
		
		assertEquals("Funny Deck", ud2.getDeckName());
		
		ud2.setDeckName("");  // new case
		
		assertEquals("Untitled Deck", ud2.getDeckName());
		
		
	}
	
	@Test
	void testAddCardNonExist() {  //this is a new test
		ud.addCard(uc6, -5);
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc6)) {
				fail("Geck is found in the deck after calling ud.addCard(uc6, -5).");
				//assertFalse(true);
			}
		}
		
		ud.addCard(uc6, 0);
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc6)) {
				fail("Geck is found in the deck after calling ud.addCard(uc6, 0).");
				//assertFalse(true);
			}
		}
		
		ud.addCard(uc6, 10);
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc6)) {
				assertEquals(10,cc2.getCount());
			}
		}
		
	}
	
	
	@Test
	void testAddCardExist() {
		
		CardCounter cc = null;
		
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc5)) cc = cc2;
		}
		
		assertEquals(5, cc.getCount());
		
		ud.addCard(uc5, 4);
		
		assertEquals(9, cc.getCount());
		
		ud.addCard(uc5, 0);
		
		assertEquals(9, cc.getCount());
		
		ud.addCard(uc5, 3);
		
		assertEquals(12, cc.getCount());
		
		ud.addCard(uc5, -7);
		
		assertEquals(12, cc.getCount());
	}
	
	@Test
	void testRemoveNonExistingCard() { //this is a new test 
		ud.removeCard(uc6, -5);
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc6)) {
				fail("Geck is found in the deck after calling ud.removeCard(uc6, -5).");
			}
		}
		
		ud.removeCard(uc6, 0);
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc6)) {
				fail("Geck is found in the deck after calling ud.removeCard(uc6, 0).");
			}
		}
		
		ud.removeCard(uc6, 5);
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc6)) {
				fail("Geck is found in the deck after calling ud.removeCard(uc6, 5).");
			}
		}
		
	}
	
	@Test
	void testRemoveExistingCard() { // add more detail 
		
		CardCounter cc = null;
		
		for(CardCounter cc2 : ud.getCardsInDeck()) {
			if(cc2.getCard().equals(uc5)) cc = cc2;
		}
		
		assertEquals(5, cc.getCount());
		
		ud.removeCard(uc5, 1);
		assertTrue(ud.getCardsInDeck().indexOf(cc) >=0);  //testing if it's still in the deck is added at every possible  place
		assertEquals(4, cc.getCount());
		
		ud.removeCard(uc5, 0);
		assertTrue(ud.getCardsInDeck().indexOf(cc) >=0);
		assertEquals(4, cc.getCount());
		
		ud.removeCard(uc5, 2);
		assertTrue(ud.getCardsInDeck().indexOf(cc) >=0);
		assertEquals(2, cc.getCount());
		
		ud.removeCard(uc5, -3);
		assertTrue(ud.getCardsInDeck().indexOf(cc) >=0);
		assertEquals(2, cc.getCount());
		
		ud.removeCard(uc5, 1000);
		assertEquals(-1, ud.getCardsInDeck().indexOf(cc));
		//assertEquals(0, cc.getCount());
		
		
	}
	
	@Test
	void testCardCount() {
		
		assertEquals(14, ud.cardCount());
		
		ud.addCard(uc3, 5);
		
		assertEquals(19, ud.cardCount());
		
		ud.removeCard(uc1, 3);
		
		assertEquals(16, ud.cardCount());
		
		ud.removeCard(uc5, 9999999);
		
		assertEquals(11, ud.cardCount());
	}
	
	@Test
	void testExistsInDeck() {
		assertTrue(ud.existsInDeck(uc1));
		assertTrue(ud.existsInDeck(uc2));
		assertTrue(ud.existsInDeck(uc3));
		assertTrue(ud.existsInDeck(uc4));
		assertTrue(ud.existsInDeck(uc5));
		assertFalse(ud.existsInDeck(uc6));
		
		ud.removeCard(uc4, 300);
		
		assertFalse(ud.existsInDeck(uc4));
	}
	
	@Test
	void testEquals() {
		UnitDeck ud2 = new UnitDeck("Stoat Deck");
		UnitDeck ud3 = new UnitDeck("Stoat Deck");
		UnitDeck ud4 = new UnitDeck("Stinkbug Deck");
		
		assertTrue(ud2.equals(ud3));
		assertTrue(ud3.equals(ud2));
		assertFalse(ud2.equals(ud4));
		assertFalse(ud3.equals(ud4));
		assertFalse(ud4.equals(ud2));
		assertFalse(ud4.equals(ud3));
			
		assertFalse(ud4.equals(new Object())); // new case
	}

}
