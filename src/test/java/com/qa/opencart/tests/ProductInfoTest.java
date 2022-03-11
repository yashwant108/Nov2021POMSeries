package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		resultsPage = accPage.doSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeaderName(), "MacBook Pro");
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{ "Macbook", "MacBook Pro", Constants.MACBOOK_IMAGES_COUNT },
				{ "Macbook", "MacBook Air", Constants.MACBOOK_IMAGES_COUNT },
				{ "iMac", "iMac", Constants.IMAC_IMAGES_COUNT }, };
	}

	@Test(dataProvider="productData")
	public void productImagesCountTest(String productName,String mainProductName,int imagesCount) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		int totalImages = productInfoPage.getProductImagecount();
		System.out.println("total images for:" + "mainProductName" + ":" + totalImages);
		Assert.assertEquals(totalImages,imagesCount);
	}
	//Hash Map
//	main product name :MacBook Pro
//	Brand:Apple
//	Availability:Out Of Stock
//	ExTaxPrice:Ex Tax: $2,000.00
//	Price:$2,000.00
//	total Images:4
//	name:MacBook Pro
//	Product Code:Product 18
//	Reward Points:800
//	PASSED: ProductDataTest
	
	//LinkedHash Map
//	main product name :MacBook Pro
//	name:MacBook Pro
//	total Images:4
//	Brand:Apple
//	Product Code:Product 18
//	Reward Points:800
//	Availability:Out Of Stock
//	Price:$2,000.00
//	ExTaxPrice:Ex Tax: $2,000.00
//	PASSED: ProductDataTest
	
	//Tree Map
//	main product name :MacBook Pro
//	Availability:Out Of Stock
//	Brand:Apple
//	ExTaxPrice:Ex Tax: $2,000.00
//	Price:$2,000.00
//	Product Code:Product 18
//	Reward Points:800
//	name:MacBook Pro
//	total Images:4
//	PASSED: ProductDataTest
	
	@Test 
	public void ProductDataTest()
	{
		resultsPage=accPage.doSearch("Macbook");
	productInfoPage=resultsPage.selectProduct("MacBook Pro");
	Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
	actProductInfoMap.forEach((k,v)-> System.out.println(k + ":" +v));
	
	softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
	softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
	softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
	softAssert.assertEquals(actProductInfoMap.get("Price"), "$2,000.00");
	softAssert.assertAll();
	}
}
