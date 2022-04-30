package pageui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.AssertJUnit;

public class HomePageUI extends CommonPages {
	WebDriver driver;

	public HomePageUI(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	@FindBy(xpath = "//div[@class='l-region l-region--navigation']/nav[2]//a")
	private List<WebElement> headerLinks;

	@FindBy(css = "#homepage-slider ol li a")
	private List<WebElement> sliderLinks;

	@FindBy(css = ".slide.flex-active-slide a span")
	private WebElement activeSliderText;

//	This was original
//	@FindBy (id="edit-ustext")
//	private WebElement searchInput;

//	This I modify by Page Facotry. 	
	@FindBy(how = How.ID, using = "edit-ustext")
	private WebElement searchInput;

	@FindBy(id = "edit-submit")
	private WebElement searchButton;

	@FindBy(id = "search-results-header")
	private WebElement searchResultText;

	@FindBy(id = "search-results-header")
	private WebElement searchHeadingAboveText;

	/**
	 * purpose of this method is to verify Header link
	 * 
	 * @return
	 */
	public boolean verifyHeaderLink() {
		List<String> list = new ArrayList<String>();
		for (WebElement element : headerLinks) {
			list.add(element.getText());
		}
		List<String> expectedList = Arrays.asList("COMMUNITIES", "CAREERS", "INVESTORS", "ABOUT", "RESIDENTS");
		AssertJUnit.assertEquals("Not verified Page Title ", "b> " + expectedList + "</b>", "b> " + list + "</b>");
		if (list.equals(expectedList)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * purpose of this method is to verify Slider images
	 * 
	 * @return
	 */
	public boolean verifySliderImages() {
		List<String> list = new ArrayList<String>();

		sliderLinks = getWebElements(By.cssSelector("#homepage-slider ol li a"), MEDIUMWAIT);
		for (WebElement element : sliderLinks) {
			waitForElementToBeVisible(element, "", MEDIUMWAIT); // Reading time from config.xml
//			ClickElement(element, "", Home_pageconstants.m_searchButton_text);
			click(element, "SliderLinks", MEDIUMWAIT);
			sleep(3);
			list.add(activeSliderText.getText().trim());
		}
		Collections.sort(list);
		// List<String> expectedList = Arrays.asList("AVANT, LOS ANGELES, CA", "ONE
		// SOUTH MARKET, SAN JOSE, CA", "THE HIGHLANDS AT WYNHAVEN, ISSAQUAH,
		// WA","MOSSO, SAN FRANCISCO, CA");
		List<String> expectedList = Arrays.asList(
				"AVANT, LOS ANGELES, CA\r\n, ONE SOUTH MARKET, SAN JOSE, CA\r\n, THE HIGHLANDS AT WYNHAVEN, ISSAQUAH, WA\r\n, MOSSO, SAN FRANCISCO, CA");
		Collections.sort(expectedList);

		AssertJUnit.assertEquals("Slide Show Verified ", "b> " + expectedList + "</b>", "b> " + list + "</b>");

		if (list.equals(expectedList)) {
			return true;
		}
		return false;
	}

	/**
	 * purpose of this method is to click on Communities link
	 */
	public void clickOnCommunitiesLink() {
		waitForElementToBeVisible(headerLinks.get(0), "Communities", MEDIUMWAIT);
//		ClickElement(headerLinks.get(0), "Communities");
		click(headerLinks.get(0), "Communities", MEDIUMWAIT);
	}

	/**
	 * purpose of this method is to click on Careers link
	 */
	public void clickOnCarrers() {
		waitForElementToBeVisible(headerLinks.get(1), "Careers", MEDIUMWAIT);
//		ClickElement(headerLinks.get(1), "",
//				com.essex.pageconstants.Header_Menuconstants.m_CareersHeader_text);
		click(headerLinks.get(1), "Carrers", MEDIUMWAIT);
	}

	/**
	 * purpose of this method is to click on Investers link
	 */
	public void clickOnInvestersLink() {
		waitForElementToBeVisible(headerLinks.get(2), "InvestersLink", MEDIUMWAIT);
//		commonLib.ClickElement(headerLinks.get(2), "",
//				com.essex.pageconstants.Header_Menuconstants.m_InvesterHeader_text);

		click(headerLinks.get(2), "InvestersLink", MEDIUMWAIT);
	}

	/**
	 * purpose of this method is to click on About link
	 */
	public void clickOnAboutLink() {
		waitForElementToBeVisible(headerLinks.get(3), "AboutLink", MEDIUMWAIT);
//		commonLib.ClickElement(headerLinks.get(3), "", com.essex.pageconstants.Header_Menuconstants.m_AboutHeader_text);

		click(headerLinks.get(3), "AboutLink", MEDIUMWAIT);
	}

	/**
	 * purpose of this method is to click on Residents link
	 */
	public void clickOnResidentsLink() {
		waitForElementToBeVisible(headerLinks.get(4), "ResidentsLink", MEDIUMWAIT);
//		commonLib.ClickElement(headerLinks.get(4), "",
//				com.essex.pageconstants.Header_Menuconstants.m_ResidentsHeader_text);
		click(headerLinks.get(4), "ResidentsLink", MEDIUMWAIT);

	}

	/**
	 * 
	 * purpose of this method is to clear and type on Search input
	 * 
	 * @param text
	 */
	public void inputSearchText(String text) {
		waitForElementToBeVisible(searchInput, "searchInput", MEDIUMWAIT);
		clearAndType(searchInput, text, "searchText", MEDIUMWAIT);
//		commonLib.InputValueIntoElement(searchInput, text);

	}

	/**
	 * purpose of this method is to click on Search button
	 */
	public void clickOnSearchButton() {
		waitForElementToBeVisible(searchButton, "SearchBtn", MEDIUMWAIT);
//		commonLib.ClickElement(searchButton, "", Home_pageconstants.m_searchButton_text);
		click(searchButton, "searchButton", MEDIUMWAIT);
	}

	/**
	 * purpose of this method is to return Search result as a string
	 * 
	 * @return
	 */
	public String getSearchResult() {
		waitForElementToBeVisible(searchResultText, "SearchText", MEDIUMWAIT);

//		return commonLib.GetText(searchResultText, "").trim();

		return getText(searchResultText, "searchResultText", MEDIUMWAIT).trim();
	}

	// this will go in commlib
	/**
	 * purpose of this method is to return page title text as a string
	 * 
	 * @return
	 */
	public String getPageTitleText() {
		// commonLib.waitForElementToBeVisible( searchResultText,
		// pageconstants.Search_pageconstants.m_search_text, waitTime);
//		return commonLib.GetPageTitle(com.essex.pageconstants.Home_pageconstants.b_homePage_title);
		return getDriver().getTitle();
	}

}
