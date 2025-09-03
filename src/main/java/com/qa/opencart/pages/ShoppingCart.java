package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingCart {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By header = By.tagName("h1");
	
	public String getShoppingCartHeaderValue() {
		String headerText = eleUtil.doElementGetText(header);
		System.out.println("Results Header --->" + headerText);
		return headerText;
	}

}
