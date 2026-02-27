package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstantsUtil;
import com.qa.opencart.utils.ExcelUtil;


public class RegPageTest extends BaseTest{
	
	@BeforeClass
	public void regPageSteup() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "priya"+random.nextInt(5000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstantsUtil.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String firstname, String lastname, String telephone, String password, String subscribe) {
		boolean flag = regPage.registerUser(firstname, lastname, getRandomEmail(), telephone, password, subscribe);
		Assert.assertTrue(flag);
	}
	
	

}
