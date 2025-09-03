package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ShopingCartPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void shoppingCartHeaderTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		shoppingcart = productInfoPage.getshoppingCartItem("5");
		String shoppingCartHeaderText = shoppingcart.getShoppingCartHeaderValue();
		System.out.println(shoppingCartHeaderText);
		Assert.assertTrue(shoppingCartHeaderText.contains("Shopping Cart"));
		//Assert.assertEquals(shoppingCartHeaderText, "Shopping Cart  (405.00kg)");
		
		
		
	}
	

}
