package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstantsUtil;
import com.qa.opencart.utils.AppErrorsUtil;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title: " + actTitle);
		Assert.assertEquals(actTitle, AppConstantsUtil.LOGIN_PAGE_TITLE, AppErrorsUtil.NO_TITLE_MATCH);
	}
	
	@Test
	public void LoginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		System.out.println("Login Page URL: " + actURL);
		Assert.assertTrue(actURL.contains(AppConstantsUtil.LOGIN_PAGE_URL_FRACTION),AppErrorsUtil.NO_URL_MATCH);
	}
	
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin("wade.bhushan+102@gmail.com", "Bhushan@123");// valid credentials from properties file can be used here
		Assert.assertTrue(accPage.isLogoutExist(),AppErrorsUtil.LOGIN_UNSUCCESSFUL);
	}

}

