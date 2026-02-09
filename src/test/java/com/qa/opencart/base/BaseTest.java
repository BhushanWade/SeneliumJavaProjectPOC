package com.qa.opencart.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	DriverFactory df;				// creating the reference of DriverFactory class
	WebDriver driver;				// creating the reference of WebDriver interface
	public LoginPage loginPage;		// creating the reference of LoginPage class
	public AccountsPage accPage;	// creating the reference of AccountsPage class
	
	@BeforeTest
	public void setup() {
		
		df = new DriverFactory();  
		driver = df.initDriver("Chrome"); 
		loginPage = new LoginPage(driver); // creating the object of LoginPage class
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
