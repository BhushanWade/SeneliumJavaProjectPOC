package com.qa.opencart.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	public WebDriver driver; //make WebDriver public to access in other classes
	
	public WebDriver initDriver(String browserName) {
		System.out.println("Browser name is: " + browserName);//code to initialize the driver
		
		//Cross browser logic:
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.out.println("Launching Chrome browser");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			System.out.println("Launching Firefox browser");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Safari")) {
			System.out.println("Launching Safari browser");
			driver = new SafariDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			System.out.println("Launching Edge browser");
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Please pass the right browser: " + browserName);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		
		return driver;
		
		
	}

}
