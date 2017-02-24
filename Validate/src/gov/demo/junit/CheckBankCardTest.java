package gov.demo.junit;

import gov.demo.util.CheckBankCard;
import junit.framework.TestCase;

import org.junit.Test;

public class CheckBankCardTest extends TestCase{

//	String ID = "6226095711989751";
	String ID = "6226095711989752";
	@Test
	public void testCheckBankCard() {
		CheckBankCard card = new CheckBankCard();
		this.assertTrue(card.checkBankCard(ID));
	}
	
	public void testGetBankCardCheckCode() {
//		fail("Not yet implemented");
	}

}
