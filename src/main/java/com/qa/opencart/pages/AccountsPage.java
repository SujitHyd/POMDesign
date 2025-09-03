package com.qa.opencart.pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By headers = By.cssSelector("div#content h2");
	private final By logoutLink = By.linkText("Logout");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	private final By rightSideMenuItems = By.cssSelector("div.list-group>a");
	
	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.waitForElementsPresence(headers,AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Total Number Of Header : " + headerList.size());
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headersValList.add(text);
			
		}
		return headersValList;
 	}
	
	public boolean isLogoutLinkExist() {
		WebElement logoutEle = eleUtil.waitForElementVisible(logoutLink,AppConstants.DEFAULT_MEDIUM_WAIT);
		return eleUtil.isElementDisplayed(logoutEle);
	}
	
	public List<String> getRightSideMenuItemList() {
		List<WebElement> MenuList = eleUtil.waitForElementsPresence(rightSideMenuItems,AppConstants.DEFAULT_MEDIUM_WAIT);
		System.out.println("Total Number Of Menu List  : " + MenuList.size());
		List<String> MenuListVal = new ArrayList<String>();
		for (WebElement e : MenuList) {
			String text = e.getText();
			MenuListVal.add(text);
			
		}
		return MenuListVal;
	}
	
	public SearchResultsPage doSearch(String searchKey) {		
		System.out.println("Search Key ---->" + searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(search,AppConstants.DEFAULT_MEDIUM_WAIT);
		searchEle.clear();
		searchEle.sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
		
	}
}
	
	
