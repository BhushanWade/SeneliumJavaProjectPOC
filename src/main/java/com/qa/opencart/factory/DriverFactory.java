package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	public WebDriver driver; //make WebDriver public to access in other classes
	public Properties prop;  //make Properties public to access in other classes
	public OptionsManager optionsManager;
	
	public static String highlight;
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		
		System.out.println("Browser name is: " + browserName);//code to initialize the driver
		
		highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);
		
		//Cross browser logic:
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.out.println("Launching Chrome browser");
			driver = new ChromeDriver(optionsManager.getChromeOptions());
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			System.out.println("Launching Firefox browser");
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
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
		driver.get(prop.getProperty("url"));
		
		return driver;
		
		
	}


	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}


}
