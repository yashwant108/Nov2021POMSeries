package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	WebDriver driver;
	//in order to use driver we need to create the object of DriverFactory
	DriverFactory df;
	Properties prop;
	
LoginPage loginPage;
AccountsPage accPage;
RegisterPage regPage;
ResultsPage resultsPage;
ProductInfoPage productInfoPage;
SoftAssert softAssert;
//not creating object for AccountPage because we create only reference here and create the object inside LoginPage doLogin() method

	@BeforeTest
	public void setUp()
	{
		df=new DriverFactory();
		prop=df.init_prop();
		
		//Here you are passing entire object prop and using the variables inside it otherwise 
		//we need to pass everything as parameters for function
		driver=df.init_driver(prop);
		//initially this driver is null but we are passing the DriverFactory initalizaztion to this local driver
		loginPage=new LoginPage(driver);
		//now again local driver which is initalized is passing throught the LoginPage and initializing the LoginPage Driver reference
		//so only one time object creation in Driverfactory but 2 references are used in BaseTest and LoginPage
		softAssert=new SoftAssert();
	}
	

	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
