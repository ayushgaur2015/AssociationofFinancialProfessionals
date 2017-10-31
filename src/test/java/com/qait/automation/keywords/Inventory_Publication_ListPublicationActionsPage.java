package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

public class Inventory_Publication_ListPublicationActionsPage extends GeneralActionsPage {

	public Inventory_Publication_ListPublicationActionsPage(WebDriver driver) {
		super(driver, "Inventory_Publication_ListPublicationActionsPage");
	}

	/**
	 * method to navigate to publication profile page
	 */
	public void userNavigatesToPublicationProfile(){
	//	isElementDisplayed("link_gotoPubProf");
		element("link_gotoPubProf").click();
	}
}
