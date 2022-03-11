package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
WebDriver driver;

public JavaScriptUtil(WebDriver driver)
{
	this.driver=driver;
}
public void flash(WebElement element)
{
	JavascriptExecutor js=((JavascriptExecutor) driver);
	String bgcolor=element.getCssValue("backgroundColor");
	for(int i=0;i<5;i++)
	{
		changeColor("#000000",element);
		changeColor(bgcolor,element);
	}
}
	public void changeColor(String color,WebElement element)
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor =' " + color+ "'", element);
	}
	
}

