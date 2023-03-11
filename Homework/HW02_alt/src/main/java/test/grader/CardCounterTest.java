package test.grader;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.CardCounter;
import logic.UnitCard;

public class CardCounterTest {
	
	UnitCard uc1;
	UnitCard uc2;
	
	@BeforeEach
	void setup(){
		uc1 = new UnitCard("Flying Bear", 3, 4, 6, "TOO FAST. TOO SOON.");
		uc2 = new UnitCard("Strange Frog", 1, 1, 2, "Froggy :)");
	}
	
	@Test
	void testConstructor() {
		CardCounter cc = new CardCounter(uc1, 5);
		
		assertEquals(uc1, cc.getCard());
		assertEquals(5, cc.getCount());
	}
	
	@Test
	void testBadConstructor() {
		CardCounter cc = new CardCounter(uc2, -3);
		
		assertEquals(uc2, cc.getCard());
		assertEquals(0, cc.getCount());
	}
	
	@Test
	void testSetCount() {
		CardCounter cc = new CardCounter(uc1, 3);
		
		assertEquals(3, cc.getCount());
		
		cc.setCount(-900);
		
		assertEquals(0, cc.getCount());
		
		cc.setCount(12);
		
		assertEquals(12, cc.getCount());
		
		cc.setCount(0);
		
		assertEquals(0, cc.getCount());
	}
}
