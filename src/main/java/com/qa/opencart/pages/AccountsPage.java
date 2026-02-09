package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstantsUtil;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	
	private WebDriver driver; 			// creating the reference of WebDriver interface
	private ElementUtil eleUtil; 		// creating the reference of ElementUtil class
	
	//1. private By locators
	
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By logoutLink = By.linkText("Logout");
	private By accSecHeader = By.cssSelector("div#content h2");
	
	//2. constructor of the page class
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. page actions/methods
	
	public String getAccPageTitle() {
		return eleUtil.waitForTitleContains(AppConstantsUtil.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	public String getAccPageURL() {
		return eleUtil.waitForUrlContains(AppConstantsUtil.ACCOUNT_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, TimeUtil.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public boolean isLogoutExist() {
		return eleUtil.waitForElementVisible(logoutLink, TimeUtil.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccPageSelectionHeaders() {
		List<WebElement> secHeaderList = eleUtil.waitForElementsVisible(accSecHeader, TimeUtil.DEFAULT_TIME_OUT);
		List<String> secHeaderValList =  new ArrayList<String>();
		for(WebElement e : secHeaderList) {
			String text = e.getText();
			secHeaderValList.add(text);
		}
		return secHeaderValList;
	}
	
	

}
