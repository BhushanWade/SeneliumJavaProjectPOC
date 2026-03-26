package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	//public WebDriver driver; //make WebDriver public to access in other classes
	public Properties prop;  //make Properties public to access in other classes
	public OptionsManager optionsManager;
	
	public static String highlight;
	
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); //create a ThreadLocal instance for WebDriver
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		
		System.out.println("Browser name is: " + browserName);//code to initialize the driver
		
		highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);
		
		//Cross browser logic:
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.out.println("Launching Chrome browser");
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); //set the WebDriver instance in ThreadLocal
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			System.out.println("Launching Firefox browser");
		//	driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions())); //set the WebDriver instance in ThreadLocal
		}
		else if(browserName.equalsIgnoreCase("Safari")) {
			System.out.println("Launching Safari browser");
		//	driver = new SafariDriver();
			tlDriver.set(new SafariDriver()); //set the WebDriver instance in ThreadLocal
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			System.out.println("Launching Edge browser");
		//	driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver()); //set the WebDriver instance in ThreadLocal
		}
		else {
			System.out.println("Please pass the right browser: " + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
		
	}
	
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get(); //get the WebDriver instance from ThreadLocal
	}


	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running tests on environment: " + envName);
		if(envName == null) {
			System.out.println("No environment is specified, running tests on QA environment");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass the right environment: " + envName);
					break;
				}
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
				}
			
			try {
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return prop;
	}
	
	//code to capture screenshot
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}


}
