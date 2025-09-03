package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;



public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	@Test
	public void isLogoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageHeaderTest() {
		List<String> actHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(actHeadersList.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
		Assert.assertEquals(actHeadersList,AppConstants.expectedAccPageHeaderList);
		
		
	}

	@Test
	public void RightSideMenuItemListTest() {
		List<String> actMenuItemList = accPage.getRightSideMenuItemList();
		Assert.assertEquals(actMenuItemList.size(), AppConstants.ACC_PAGE_MENULIST_COUNT);
		Assert.assertEquals(actMenuItemList, AppConstants.expectedAccMenuList);
	}

}
