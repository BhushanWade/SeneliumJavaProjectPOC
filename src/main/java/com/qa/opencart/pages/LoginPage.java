package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstantsUtil;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {
	
	private WebDriver driver;	 // creating the reference of WebDriver interface
	private ElementUtil eleUtil; // creating the reference of ElementUtil class
	
	//1. private By locators
	
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	
	//2. constructor of the page class
	
	public LoginPage(WebDriver driver) {
		this.driver = driver; 			   
		eleUtil = new ElementUtil(driver); // creating the object of ElementUtil class
	}
	
	//3. page actions/methods
	
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(AppConstantsUtil.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	
//	public String getLoginPageTitle(String title) {
//		return driver.getTitle();
//	}
	
	public String getLoginPageURL() {
		return eleUtil.waitForUrlContains(AppConstantsUtil.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String un, String pwd) { 		// return type is AccountsPage because after login it will navigate to Account page
		System.out.println("Login with: " + un + " : " + pwd);
		eleUtil.waitForElementVisible(emailID, TimeUtil.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver); // after login, it will navigate to Account page Chaining concept
	}
 

}
