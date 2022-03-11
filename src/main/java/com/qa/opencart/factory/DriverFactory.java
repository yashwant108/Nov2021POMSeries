package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
public WebDriver driver;
Properties prop;
public OptionsManager optionsManager;

public static String highlight;

public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal();

/**
 * this method is used to initalize the driver using the given browser name
 * @param browserName
 * @return this returns webdriver
 */
//Here you are passing entire object prop and using the variables inside it otherwise 
//we need to pass everything as parameters for function
public WebDriver init_driver(Properties prop)
{
	String browserName=prop.getProperty("browser").trim();
	//String browserVersion=prop.getProperty("browserversion").trim();
	highlight=prop.getProperty("highlight").trim();
	System.out.println("Browser name:" + browserName );
	optionsManager=new OptionsManager(prop);
	
	if(browserName.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
	//	driver=new ChromeDriver(optionsManager.getChromeOptions());
		tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
	}else if(browserName.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		//driver=new EdgeDriver(optionsManager.getEdgeOptions());
		tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
	}else
	{
		System.out.println("Please pass the right browser name:" + browserName);
	}
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	getDriver().get(prop.getProperty("url").trim());
	return getDriver();
	}

/**
 * this will return the thread local copy of the driver
 * @return
 */
public static WebDriver getDriver()
{
	return tlDriver.get();
}

/**
 * this method is used to initalize the properties
 * @return this return returns properties class reference
 */
public Properties init_prop()
{
	//this will read the properties from config file
	prop=new Properties();
	try {
		FileInputStream ip=new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
	prop.load(ip);
	} catch (FileNotFoundException e) //if file is missing then we use this catch block
	{
		
		e.printStackTrace();
	} catch (IOException e)//prop.load we cannot load it then we get IO exception
	{
			e.printStackTrace();
	}
	return prop;
}
//Threadlocal --- JDK 8 --> create local copy of driver
//set driver with TL
//get driver -- driver
//driver null problem
//you can take driver copy anywhere in ur framework
//better thread management
//to avoid the dead lock conditions--TL driver copy
//largest test case count-200, 300 TCS-->proper tst results


/**
 *  take screenshot
 */

public String getScreenShot()
{
	File srcFile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	String path=System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
	File destination=new File(path);
	try
	{
		FileUtils.copyFile(srcFile, destination);
	}catch(IOException e)
	{
		e.printStackTrace();
	}
	return path;
}
}
