package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.testlisteners.AnnotationTransformer;
import com.qa.opencart.testlisteners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: Design Login Page for Open Cart Application...")
@Story("US  -101:Login Page features")
@Listeners({AnnotationTransformer.class,TestAllureListener.class})
public class LoginPageTest  extends BaseTest{
//After 1 st commit will check
	@Description("TC01:Login Page title test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest()
	{
		String title=loginPage.getLoginPageTitle();
		System.out.println("page title :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("TC02:Login Page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest()
	{
		String url=loginPage.getLoginPageUrl();
		System.out.println("Login page url:" + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("TC03:This is Forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("TC04:This is positive test case for Login to the page")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description="login test with correct username and password"	)
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}
