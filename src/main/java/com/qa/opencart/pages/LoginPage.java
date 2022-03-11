package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	//Actually this driver gives null variable, null.getTitle(); but constructor will  give chance to initialize the given driver
	//THis driver is not to initialize the driver but DriverFactory will initialize the driver and give it to this page
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1. private By locator
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	
	
	//2. page constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver=DriverFactory.getDriver();
		eleUtil=new ElementUtil(driver);
	}
	
	//3.Public page actions/methods
	
	public String getLoginPageTitle()
	{
		return eleUtil.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
	}
	public String getLoginPageUrl()
	{
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.doDisplay(forgotPwdLink);
				
	}
	
	public AccountsPage doLogin(String userName,String pwd)
	{
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	public RegisterPage goToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
