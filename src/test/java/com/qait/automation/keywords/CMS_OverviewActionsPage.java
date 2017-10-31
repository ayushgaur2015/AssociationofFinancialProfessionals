package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

public class CMS_OverviewActionsPage extends GeneralActionsPage {

	public CMS_OverviewActionsPage(WebDriver driver) {
		super(driver, "CMS_OverviewActionsPage");
	}
	
	/**
	 * method to select link on cms overview page
	 * @param linkManageContent - user required link om cms page
	 */
	public void userManageWebContent_Select_(String linkManageContent){
		userClickOn("link_manageWebContent", linkManageContent);
	}

}
