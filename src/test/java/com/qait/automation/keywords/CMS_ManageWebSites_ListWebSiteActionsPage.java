package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

public class CMS_ManageWebSites_ListWebSiteActionsPage extends GeneralActionsPage{

	public CMS_ManageWebSites_ListWebSiteActionsPage(WebDriver driver) {
		super(driver, "CMS_ManageWebSites_ListWebSiteActionsPage");
	}

	/**
	 * user from the available website list select website user defined website
	 * @param website - website name provided by user
	 */
	public void userFromTheWebsiteListSelectWebsite_(String website){
		userClickOn("link_website",website);
	}
	
}
