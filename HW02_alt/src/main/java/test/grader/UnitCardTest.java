package test.grader;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logic.UnitCard;

public class UnitCardTest {

	@Test
	void testConstructor() {
		UnitCard uc = new UnitCard("River Snapper", 2, 1, 6, "The stalwart Snapper. A near impenetrable defense.");
		assertEquals("River Snapper", uc.getName());
		assertEquals(2, uc.getBloodCost());
		assertEquals(1, uc.getPower());
		assertEquals(6, uc.getHealth());
		assertEquals("The stalwart Snapper. A near impenetrable defense.", uc.getFlavorText());
	}
	
	@Test
	void testBadConstructor() {
		UnitCard uc = new UnitCard("", -100, -100, -100, "You aren't supposed to be here, you know.");
		assertEquals("Creature", uc.getName());
		assertEquals(0, uc.getBloodCost());
		assertEquals(0, uc.getPower());
		assertEquals(1, uc.getHealth());
		assertEquals("You aren't supposed to be here, you know.", uc.getFlavorText());
	}
	
	@Test
	void testSetName() {
		UnitCard uc = new UnitCard("River Snapper", 2, 1, 6, "The stalwart Snapper. A near impenetrable defense.");
		
		uc.setName("Caged Wolf");
		
		assertEquals("Caged Wolf", uc.getName());
		
		uc.setName("     ");
		
		assertEquals("Creature", uc.getName());
		
		uc.setName("River Snapper");
		
		assertEquals("River Snapper", uc.getName());
		
		uc.setName("");
		
		assertEquals("Creature", uc.getName());
	}
	
	@Test
	void testSetBloodCost() {
		UnitCard uc = new UnitCard("River Snapper", 2, 1, 6, "The stalwart Snapper. A near impenetrable defense.");
		
		uc.setBloodCost(0);
		
		assertEquals(0, uc.getBloodCost());
		
		uc.setBloodCost(-7);
		
		assertEquals(0, uc.getBloodCost());
		
		uc.setBloodCost(3);
		
		assertEquals(3, uc.getBloodCost());
	}
	
	@Test
	void testSetPower() {
		UnitCard uc = new UnitCard("River Snapper", 2, 1, 6, "The stalwart Snapper. A near impenetrable defense.");
		
		uc.setPower(3);
		
		assertEquals(3, uc.getPower());
		
		uc.setPower(-80);
		
		assertEquals(0, uc.getPower());
		
		uc.setPower(2);
		
		assertEquals(2, uc.getPower());
		
		uc.setPower(0);

        assertEquals(0, uc.getPower());
	}
	
	@Test
	void testSetHealth() {
		UnitCard uc = new UnitCard("River Snapper", 2, 1, 6, "The stalwart Snapper. A near impenetrable defense.");
		
		uc.setHealth(7);
		
		assertEquals(7, uc.getHealth());
		
		uc.setHealth(-666);
		
		assertEquals(1, uc.getHealth());
		
		uc.setHealth(5);
		
		assertEquals(5, uc.getHealth());
		
		uc.setPower(1);

        assertEquals(1, uc.getPower());
	}
	
	@Test
	void testSetFlavorText() {
		UnitCard uc = new UnitCard("River Snapper", 2, 1, 6, "The stalwart Snapper. A near impenetrable defense.");
		
		uc.setFlavorText("Nice topdeck.");
		
		assertEquals("Nice topdeck.", uc.getFlavorText());
		
		uc.setFlavorText("This spot? You sure?");
		
		assertEquals("This spot? You sure?", uc.getFlavorText());
		
		uc.setFlavorText("Total misplay.");
		
		assertEquals("Total misplay.", uc.getFlavorText());

	}
	
	@Test
	void testEquals() {
		UnitCard uc1 = new UnitCard("Stoat", 1, 1, 3, "Bad play.");
		UnitCard uc2 = new UnitCard("Stoat", 1, 1, 2, "Bad play.");
		UnitCard uc3 = new UnitCard("Weak Stoat", 1, 1, 2, "Bad play.");
		
		assertTrue(uc1.equals(uc2));
		assertTrue(uc2.equals(uc1));
		assertFalse(uc1.equals(uc3));
		assertFalse(uc3.equals(uc1));
		assertFalse(uc2.equals(uc3));
		assertFalse(uc3.equals(uc2));
		
		Object a = new Object();
		
		assertFalse(uc1.equals(a));
	}



}
