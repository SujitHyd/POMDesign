package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "samsung", "Samsung SyncMaster 941BW" },
				{ "imac", "iMac" }, { "canon", "Canon EOS 5D" } };
	}

	@Test(dataProvider = "getProducts")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}

	@DataProvider
	public Object[][] getProductImages() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "samsung", "Samsung SyncMaster 941BW", 1 },
				{ "canon", "Canon EOS 5D", 3 } };
	}

	@Test(dataProvider = "getProductImages")
	public void productImageCountTest(String searchKey, String productName, int imageCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		int actImageCount = productInfoPage.getProductImages();
		Assert.assertEquals(actImageCount, imageCount);

	}

	
	
//	@DataProvider
//	public Object[][] productInfoValues() {
//		return new Object[][] { { "productname", "MacBook Pro"}, 
//				{ "Brand", "Apple"},		
//				{ "Availability", "Out Of Stock"},
//				{ "Reward Points", "800"},
//				{ "Product Code", "Product 18"},
//				{ "productprice", "$2,000.00"},
//				{ "extaxprice", "$2,000.00"} };
//	}
	
	
	@DataProvider
	public Object[][] getCSVData() {
		return CsvUtil.csvData("productInfo");
	}
	
	
	@Test(dataProvider = "getCSVData")
	public void productInfoTest(String prod, String prodCategory, String name, 
			String brand, String availability, String points, 
			String code, String price, String extax) 
	{
		
		searchResultPage = accPage.doSearch(prod);
		productInfoPage = searchResultPage.selectProduct(prodCategory);
		Map<String, String> productDataMap = productInfoPage.getProductData();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(productDataMap.get("productname"), name);
		softAssert.assertEquals(productDataMap.get("Brand"), brand);
		softAssert.assertEquals(productDataMap.get("Availability"), availability);
		
//		String value = points;
//				
//		private String convertNull(String value) {
//		    if (points == null) {
//		        return null;
//		    }
//		    return points;
//		}
		
		softAssert.assertEquals(productDataMap.get("Reward Points"), points);
		softAssert.assertEquals(productDataMap.get("Product Code"), code);
		softAssert.assertEquals(productDataMap.get("productprice"), price);
		softAssert.assertEquals(productDataMap.get("extaxprice"), extax);

		softAssert.assertAll();		
		
		
//		searchResultPage = accPage.doSearch(prod); // How can we pass this value from CSV
//		productInfoPage = searchResultPage.selectProduct(prodCategory); // // How can we pass this value from CSV
//		Map<String, String> productDataMap = productInfoPage.getProductData();
// 		String prod, String prodCategory,String name, String brand, String availability, String points, String code, String price, String extax

//		Assert.assertEquals(productDataMap.get("productname"), "MacBook Pro");//		
//		Assert.assertEquals(productDataMap.get("Brand"), "Apple");
//		Assert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
//		Assert.assertEquals(productDataMap.get("Reward Points"), "800");
//		Assert.assertEquals(productDataMap.get("Product Code"), "Product 18");
//		Assert.assertEquals(productDataMap.get("productprice"), "$2,000.00");
//		Assert.assertEquals(productDataMap.get("extaxprice"), "$2,000.00");
//
//		SoftAssert softAssert = new SoftAssert();
//
//		softAssert.assertEquals(productDataMap.get("productname"), name );
//		softAssert.assertEquals(productDataMap.get("Brand"), brand);
//		softAssert.assertEquals(productDataMap.get("Availability"), availability );
//		softAssert.assertEquals(productDataMap.get("Reward Points"), points );
//		softAssert.assertEquals(productDataMap.get("Product Code"), code);
//
//		softAssert.assertEquals(productDataMap.get("productprice"), price);
//		softAssert.assertEquals(productDataMap.get("extaxprice"), extax);
//
//		softAssert.assertAll();// 7 --> 1 failed
		


	}

	// AAA pattern -- Arrange Act Assert
	// we can have multiple soft assertions in a single test case
	// but only one hard assert in the test case

}
