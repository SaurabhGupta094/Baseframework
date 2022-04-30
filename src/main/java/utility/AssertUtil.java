package utility;

import org.testng.Assert;

public class AssertUtil {

	public void sample(String sMessage) {

	}

	/**
	 * Assert to fail with message 
	 * 
	 * @param sMessage
	 */
	public void toBeFail(String sMessage) {
		Assert.fail(sMessage);
	}

	/**
	 * 
	 * Assert to be equal with message
	 * 
	 * @param actual
	 * @param expected
	 * @param sMessage
	 */
	public void expectToBeEqual(String actual, String expected, String sMessage) {
		Assert.assertEquals(actual, expected, sMessage);
	}

}
