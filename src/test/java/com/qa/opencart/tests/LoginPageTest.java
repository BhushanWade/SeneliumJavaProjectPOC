package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstantsUtil;
import com.qa.opencart.utils.AppErrorsUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-100: Login Page Design for Open Cart Application")
@Story("US-101: Login Page features with title, URL, forgot password link and login functionality")
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title: " + actTitle);
		Assert.assertEquals(actTitle, AppConstantsUtil.LOGIN_PAGE_TITLE, AppErrorsUtil.NO_TITLE_MATCH);
	}
	
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void LoginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		System.out.println("Login Page URL: " + actURL);
		Assert.assertTrue(actURL.contains(AppConstantsUtil.LOGIN_PAGE_URL_FRACTION),AppErrorsUtil.NO_URL_MATCH);
	}
	
	@Description("Forgot Password Link Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Login Test with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));// valid credentials from properties file can be used here
		Assert.assertTrue(accPage.isLogoutExist(),AppErrorsUtil.LOGIN_UNSUCCESSFUL);
	}

}

