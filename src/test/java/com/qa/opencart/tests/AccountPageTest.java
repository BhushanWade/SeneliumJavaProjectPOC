package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstantsUtil;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		System.out.println("Account Page Title: " + actTitle);
		Assert.assertEquals(actTitle, AppConstantsUtil.ACCOUNT_PAGE_TITLE);			
	}
	
	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccPageURL();
		System.out.println("Account Page URL: " + actURL);
		Assert.assertTrue(actURL.contains(AppConstantsUtil.ACCOUNT_PAGE_URL_FRACTION));			
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Test
	public void logoutExistTest() {
		Assert.assertTrue(accPage.isLogoutExist());
	}
	
	@Test
	public void accPageSectionHeaderTest() {
		List<String> actSecHeaderList = accPage.getAccPageSelectionHeaders();
		Assert.assertEquals(actSecHeaderList, AppConstantsUtil.EXPECTED_ACCOUNT_PAGE_SECTION_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
			
		
		};
	}
	
	@Test(dataProvider = "getProductName")
	public void productSearchTest(String productName) {
		resultsPage = accPage.performSearch(productName);
		String actTitle = resultsPage.getSearchPageTitle(productName);
		System.out.println("Search page Title: "+ actTitle);
		softAssert.assertEquals(actTitle, AppConstantsUtil.SEARCH_PAGE_TITLE+""+productName);
		Assert.assertTrue(resultsPage.getSearchProductCount()>0);
	}
	
	

}
