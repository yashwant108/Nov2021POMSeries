package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
 private WebDriver driver;
 private JavaScriptUtil jsUtil;
 
 public ElementUtil(WebDriver driver)
 {
	 this.driver=driver;
	 jsUtil=new JavaScriptUtil(driver);
 }
 
 
 public By getBy(String locatorType,String locatorValue)
 {
	 By locator=null;
	 switch(locatorType.toLowerCase())
	 {
	 case "id":
		 locator=By.id(locatorValue);
		 break;
	 case "name":
		 locator=By.name(locatorValue);
		 break;
	 case "className":
		 locator=By.className(locatorValue);
		 break;
	 case "xpath":
		 locator=By.xpath(locatorValue);
		 break;
	 case "css":
		 locator=By.cssSelector(locatorValue);
		 break;
	 case "linkText":
		 locator=By.linkText(locatorValue);
		 break;
	 case "partiallinkText":
		 locator=By.partialLinkText(locatorValue);
		 break;
	 case "tagname":
		 locator=By.tagName(locatorValue);
		 break;
		 default:
			 break;
	 }
	 return locator;
 }
 
 
 public WebElement getElement(By locator)
 {
	 WebElement ele= driver.findElement(locator) ;
	 if(Boolean.parseBoolean(DriverFactory.highlight));
	 {
	 jsUtil.flash(ele);
	 }
	 return ele;
 }
 
 public void doClick(By locator)
 {
	getElement(locator).click();
 }
 
 
 public void doClick(String locatorType,String locatorValue)
 {
	getElement(getBy(locatorType,locatorValue)).click();;
 }
 public String doGetText(By locator)
 {
	 return getElement(locator).getText();
 }
 public void doSendKeys(By locator,String value)
 {
	 WebElement ele=getElement(locator);
	 ele.clear();
	 getElement(locator).sendKeys(value);
 }
 
 public void doSendKeys(String locatorType,String locatorValue,String value)
 {
	 
	getElement(getBy(locatorType,locatorValue)).sendKeys(value);
 }
 
 public String doGetAttribute(By locator,String attrName)
 {
	 return getElement(locator).getAttribute(attrName);
 }
 
 public boolean doDisplay(By locator)
 {
	 return getElement(locator).isDisplayed();
 }
 
 public boolean doEnabled(By locator)
 {
	 return getElement(locator).isEnabled();
 }
 public List<WebElement> getElements(By locator)
 {
	 return driver.findElements(locator);
 }
 
 public int getElementCount(By locator)
 {
	 return getElements(locator).size();
 }
  
 //*****************WebElement Wait Utils************************************************************
 public void clickWhenReady(By locator, int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 wait.until(ExpectedConditions.elementToBeClickable(locator));
 }
 public void waitForElementPresence(By locator, int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 wait.until(ExpectedConditions.presenceOfElementLocated(locator));
 }
 
 public WebElement waitforElementVisible(By locator, int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 }
 public List<WebElement> waitforElementPresence(By locator, int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
 }
 
 
 
 public List<WebElement> waitforElementsVisible(By locator, int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
 }
 
 
 
 
 ///**************Non WebElements Wait Utils(title,url,alerts etc)**************************
 
 public boolean waitForPageTitle(String titleVal,int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 return wait.until(ExpectedConditions.titleContains(titleVal));
 }
 
 public boolean waitForPageActTitle(String actTitle,int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 return wait.until(ExpectedConditions.titleIs(actTitle));
 }
 
 public String doGetPageTitle(String titleVal,int timeOut)
 {
	if(waitForPageTitle(titleVal,timeOut))
	{
		return driver.getTitle();
	}
	return null;
 }
 
 public String doGetPageTitleIs(String actTitle,int timeOut)
 {
	if(waitForPageTitle(actTitle,timeOut))
	{
		return driver.getTitle();
	}
	return null;
 }
 
 public String waitForUrlContains(String urlFraction,int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	if(wait.until(ExpectedConditions.urlContains(urlFraction)))
	{
		return driver.getCurrentUrl();
	}
	return null;
 }
 
 public String waitForUrl(String url,int timeOut)
 {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	if(wait.until(ExpectedConditions.urlToBe(url)))
	{
		return driver.getCurrentUrl();
	}
	return null;
 }
 
 
 
 
 
}