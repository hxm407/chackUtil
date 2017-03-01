package gov.servyou.commons.junit;

import gov.servyou.commons.util.CheckIdCard;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class IDCardTest extends TestCase {

	private String idcard1 = "11010519491231002X";
	private String idcard2 = "440524188001010010";

	public void testVerify() {
		CheckIdCard idcard = new CheckIdCard();
		this.assertTrue(idcard.Verify(idcard1));
		this.assertTrue(idcard.Verify(idcard2));
	}

	public static Test suite() {
		return new TestSuite(IDCardTest.class);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
