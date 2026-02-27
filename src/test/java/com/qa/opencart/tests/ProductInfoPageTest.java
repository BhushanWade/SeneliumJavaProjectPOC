package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchKey, String mainProduct) {
		resultsPage = accPage.performSearch(searchKey);
		prodInfoPage = resultsPage.selectProduct(mainProduct);
		String actHeader = prodInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, mainProduct);
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro" , 4},
			{"Macbook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
			{"Apple", "Apple Cinema 30\"", 6}
		};
	}
	
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesTest(String searchKey, String mainProduct, int imageCount) {
		resultsPage = accPage.performSearch(searchKey);
		prodInfoPage = resultsPage.selectProduct(mainProduct);
		int actImageCount = prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actImageCount, imageCount);
	}
	
	@Test
	public void productMetaDataTest() {
		resultsPage = accPage.performSearch("MacBook");
		prodInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String, String>actProdInfoMap = prodInfoPage.getProductInformation();
		softAssert.assertEquals(actProdInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProdInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProdInfoMap.get("actualPrice"), "$2000.00");
		softAssert.assertEquals(actProdInfoMap.get("Reward Points"), "800");
		softAssert.assertAll();
	}
	
	

}
