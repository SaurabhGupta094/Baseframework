package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtility extends DateUtil {

	private WebDriver driver;
	FileInputStream fis;
	Properties configProp;

	public int SHORTWAIT;
	public int MEDIUMWAIT;
	public int LONGWAIT;
	public int VERYLONGWAIT;

	final String configPropertiesFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "resources" + File.separator + "config" + File.separator
			+ "Configuration.properties";

	public TestUtility(WebDriver driver) {
		this.driver = driver;
		try {
			fis = new FileInputStream(configPropertiesFilePath);
			configProp = new Properties();
			configProp.load(fis);
			SHORTWAIT = Integer.parseInt(configProp.getProperty("SHORTWAIT"));
			MEDIUMWAIT = Integer.parseInt(configProp.getProperty("MEDIUMWAIT"));
			LONGWAIT = Integer.parseInt(configProp.getProperty("LONGWAIT"));
			VERYLONGWAIT = Integer.parseInt(configProp.getProperty("VERYLONGWAIT"));

		} catch (IOException e) {
			toBeFail("Excel occured while reading configuration properties in TestUtility");
		}

	}

	// All BrowserActions ReUsable Methods from here

	/**
	 * 
	 * 
	 * Reusable Test utility method for waiting for element to be clickable using
	 * WebElement
	 * 
	 * @param element
	 * @param sElementName
	 * @param iTime
	 */
	public void waitForElementToBeClickable(WebElement element, String sElementName, int iTime) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, iTime);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (StaleElementReferenceException e) {
			System.out.println(sElementName + " is not attached to the page document");
			e.printStackTrace();
			toBeFail(sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println(" Element " + sElementName + " was not clickable in time-" + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not clickable in time-" + iTime);
		}

	}

	/**
	 * 
	 * Reusable Test utility method for waiting for element to be clickable using by
	 * locator
	 * 
	 * @param byLocator
	 * @param sElementName
	 * @param iTime
	 */
	public void waitForElementToBeClickable(By byLocator, String sElementName, int iTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, iTime);
			wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		} catch (StaleElementReferenceException e) {
			System.out.println(sElementName + " is not attached to the page document");
			e.printStackTrace();
			toBeFail(sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println(" Element " + sElementName + " was not clickable in time-" + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not clickable in time-" + iTime);
		}
	}

	/**
	 * 
	 * Reusable Test utility method for waiting for element to be visile using
	 * WebElement
	 * 
	 * @param element
	 * @param sElementName
	 * @param iTime
	 */
	public void waitForElementToBeVisible(WebElement element, String sElementName, int iTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, iTime);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			System.out.println("Element " + sElementName + " was not visible in time - " + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not visible in time - " + iTime);
		} catch (NoSuchElementException e) {
			System.out.println(" Element " + element + "is not attached to the page document");
			e.printStackTrace();
			toBeFail("Element " + element + "is not attached to the page document");
		} catch (Exception e) {
			System.out.println("Unable to find the element " + sElementName);
			e.printStackTrace();
			toBeFail("Unable to find the element " + sElementName);
		}
	}

	/**
	 * 
	 * 
	 * Reusable Test utility method for waiting for element to be Visile using By
	 * Locator
	 * 
	 * @param byLocator
	 * @param sElementName
	 * @param iTime
	 */
	public void waitForElementToBeVisible(By byLocator, String sElementName, int iTime) {
		for (int i = 0; i < iTime; i++) {
			try {
				driver.findElement(byLocator);
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

		}

	}

	/**
	 * Reusable Test utility method for clicking on element
	 * 
	 * 
	 * @param element
	 * @param sElementName
	 * @param iTime
	 */
	public void click(WebElement element, String sElementName, int iTime) {
		try {
			waitForElementToBeClickable(element, sElementName, iTime);
			setHighlight(element);
			element.click();
			setCompleteHighlight(element);
		} catch (StaleElementReferenceException e) {
			System.out.println(sElementName + " is not attached to the page document");
			e.printStackTrace();
			toBeFail(sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println(" Element " + sElementName + " was not clickable in time-" + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not clickable in time-" + iTime);
		}
	}

	/**
	 * Reusable Test utility method for clearing and writing on element
	 * 
	 * @param element
	 * @param sText
	 * @param sElementName
	 * @param iTime
	 */
	public void clearAndType(WebElement element, String sText, String sElementName, int iTime) {
		try {
			waitForElementToBeVisible(element, sElementName, iTime);
			setHighlight(element);
			element.clear();
			setCompleteHighlight(element);
			setHighlight(element);
			element.sendKeys(sText);
			setCompleteHighlight(element);
		} catch (StaleElementReferenceException e) {
			System.out.println(sElementName + " is not attached to the page document");
			e.printStackTrace();
			toBeFail(sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println(" Element " + sElementName + "was not found in time" + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in time-" + iTime);
		}
	}

	/**
	 * 
	 * Reusable Test utility method for selecting dropdown by visible text on
	 * element
	 * 
	 * @param element
	 * @param sVisibleText
	 * @param sElementName
	 * @param iTime
	 */
	public void selectByVisibleText(WebElement element, String sVisibleText, String sElementName, int iTime) {
		try {
			waitForElementToBeVisible(element, sElementName, iTime);
			setHighlight(element);
			Select select = new Select(element);
			select.selectByVisibleText(sVisibleText);
			setCompleteHighlight(element);

		} catch (StaleElementReferenceException e) {
			System.out.println(sElementName + " is not attached to the page document");
			e.printStackTrace();
			toBeFail(sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println(" Element " + sElementName + "was not found in time" + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in time-" + iTime);
		}
	}

	/**
	 * Reusable Test utility method for checking checkbox using element
	 * 
	 * @param element
	 * @param sElementName
	 * @param iTime
	 */
	public void checkChkBx(WebElement element, String sElementName, int iTime) {
		try {
			waitForElementToBeVisible(element, sElementName, iTime);
			if (element.isSelected()) {
				setHighlight(element);
				click(element, sElementName, iTime);
				setCompleteHighlight(element);
			}

		} catch (StaleElementReferenceException e) {
			System.out.println(sElementName + " is not attached to the page document");
			e.printStackTrace();
			toBeFail(sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println(" Element " + sElementName + "was not found in time" + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in time-" + iTime);
		}

	}

	/**
	 * 
	 * Reusable Test utility method for checking uncheckbox using element
	 * 
	 * @param element
	 * @param sElementName
	 * @param iTime
	 */
	public void uncheckChkBx(WebElement element, String sElementName, int iTime) {
		try {
			waitForElementToBeVisible(element, sElementName, iTime);
			if (!element.isSelected()) {
				setHighlight(element);
				click(element, sElementName, iTime);
				setCompleteHighlight(element);
			}
		} catch (StaleElementReferenceException e) {
			System.out.println(sElementName + " is not attached to the page document");
			e.printStackTrace();
			toBeFail(sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println(" Element " + sElementName + "was not found in time" + iTime);
			e.printStackTrace();
			toBeFail("Element " + sElementName + " was not found in time-" + iTime);
		}

	}

	/**
	 * Reusable Test utility method for switching to frame using WebElement
	 * 
	 * @param element
	 * @param iTime
	 */
	public void switchToFrame(WebElement element, int iTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, iTime);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		} catch (TimeoutException e) {
			System.out.println("Frame element was not visible in time - " + iTime);
			e.printStackTrace();
			toBeFail("Frame element was not visible in time - " + iTime);
		} catch (NoSuchElementException e) {
			System.out.println(" Frame element is not attached to the page document");
			e.printStackTrace();
			toBeFail("Frame element is not attached to the page document");
		} catch (Exception e) {
			System.out.println("Unable to find the frame element ");
			e.printStackTrace();
			toBeFail("Unable to find the frame element ");
		}
	}

	/**
	 * Reusable Test utility method for switching to frame using Index
	 * 
	 * @param iIndex
	 * @param iTime
	 */
	public void switchToFrame(int iIndex, int iTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, iTime);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iIndex));
		} catch (TimeoutException e) {
			System.out.println("Frame element was not visible in time - " + iTime);
			e.printStackTrace();
			toBeFail("Frame element was not visible in time - " + iTime);
		} catch (NoSuchElementException e) {
			System.out.println(" Frame element is not attached to the page document");
			e.printStackTrace();
			toBeFail("Frame element is not attached to the page document");
		} catch (Exception e) {
			System.out.println("Unable to find the frame element ");
			e.printStackTrace();
			toBeFail("Unable to find the frame element ");
		}
	}

	/**
	 * Reusable Test utility method for getting webElement using By locator
	 * 
	 * @param by
	 * @param iTime
	 * @return
	 */
	public WebElement getWebElement(By by, int iTime) {
		waitForElementToBeVisible(by, "", iTime);
		return driver.findElement(by);
	}

	/**
	 * 
	 * Reusable Test utility method for getting List of WebElement using By locator
	 * 
	 * @param by
	 * @param iTime
	 * @return
	 */
	public List<WebElement> getWebElements(By by, int iTime) {
		waitForElementToBeVisible(by, "", iTime);
		return driver.findElements(by);
	}

	/**
	 * Reusable Test utility method for Highlighting element
	 * 
	 * 
	 * @param element
	 */
	public void setHighlight(WebElement element) {
		try {
			String attributevalue = "border:2px solid Crimson;";
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
		} catch (Exception e) {

		}
	}

	/**
	 * Reusable Test utility method for Completed Highlighting element
	 * 
	 * @param element
	 */
	public void setCompleteHighlight(WebElement element) {
		try {
			String attributevalue = "border:2px solid limegreen;";
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * Reusable Test utility method for getting text of an element
	 * 
	 * @param element
	 * @param sElementName
	 * @param iTime
	 * @return
	 */
	public String getText(WebElement element, String sElementName, int iTime) {
		String sValue = null;
		try {
			waitForElementToBeVisible(element, sElementName, iTime);
			setCompleteHighlight(element);
			sValue = element.getText();
		} catch (StaleElementReferenceException e) {
			System.out.println("Element for " + sElementName + "is not attached to the page document");
			toBeFail("Element for " + sElementName + "is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element for " + sElementName + " was not found in DOM");
			toBeFail("Element for " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("Unable to get the text from the element " + sElementName);
			toBeFail("Unable to get the text from the element " + sElementName);
		}
		return sValue;
	}

	/**
	 * 
	 * Reusable Test utility method for moving cursor to element
	 * 
	 * @param element
	 * @param sElementName
	 * @param iTime
	 */
	public void moveToElement(WebElement element, String sElementName, int iTime) {
		try {
			waitForElementToBeVisible(element, sElementName, iTime);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (StaleElementReferenceException e) {
			System.out.println("Element for " + sElementName + "is not attached to the page document");
			toBeFail("Element for " + sElementName + "is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("Element for " + sElementName + " was not found in DOM");
			toBeFail("Element for " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("Unable to move cursor to element in element " + sElementName);
			toBeFail("Unable to move cursor to element  in element " + sElementName);
		}

	}

	/**
	 * Reusable Test utility method for switching to newly opened window
	 * 
	 * @param sMainWindowHandle
	 */
	public void switchToWindowNewWindow(String sMainWindowHandle) {
		Set<String> sWindowHandles = driver.getWindowHandles();
		for (String sHandles : sWindowHandles) {
			if (!sMainWindowHandle.contentEquals(sHandles)) {
				driver.switchTo().window(sHandles);
			}
		}
	}

	// Below method I added GetPageTitle
	/**
	 * Reusable Test utility method for gettings page title as a string
	 * 
	 * @param Element
	 * @return
	 */
	public String GetPageTitle(String Element) {
		String pgTitle = driver.getTitle();
		return pgTitle;
	}

	/**
	 * Reusable Test utility method for waiting for element to be visible
	 * 
	 * @param driver
	 * @param webElement
	 * @param elementName
	 * @param waitTime
	 */
	public void WaitUntilElementIsVisible(WebDriver driver, WebElement webElement, String elementName, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	/**
	 * Reusable Test utility method for going back to previous page
	 */
	public void GoBack() {
		driver.navigate().back();
	}

}
