package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	
	DriverFactory df;				// creating the reference of DriverFactory class
	WebDriver driver;				// creating the reference of WebDriver interface
	public LoginPage loginPage;		// creating the reference of LoginPage class
	public AccountsPage accPage;	// creating the reference of AccountsPage class
	public ResultsPage resultsPage;  // creating the reference of ResultsPage class
	protected ProductInfoPage prodInfoPage; //creating the reference of ProductInfoPage class
	protected RegPage regPage;
	
	protected SoftAssert softAssert; // creating the reference of SoftAssert
	
	protected Properties prop ;      // creating the reference of Properties
	
	@BeforeTest
	public void setup() {
		
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop); 
		loginPage = new LoginPage(driver); // creating the object of LoginPage class
		softAssert = new SoftAssert(); // creating the object of softAssertion 
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
