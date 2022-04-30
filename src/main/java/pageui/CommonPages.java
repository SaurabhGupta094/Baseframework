package pageui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.TestUtility;

public class CommonPages extends TestUtility {

	private WebDriver driver;

	public CommonPages(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// All Common WebElements from here

	// WebAction methods from here

	/**
	 * 
	 * Reusable method for thread.sleep
	 * 
	 * @param iSeconds
	 */
	public void sleep(int iSeconds) {
		try {
			Thread.sleep(iSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reusable method to get driver, and can be accessed with all the page objects.
	 * 
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}

}
