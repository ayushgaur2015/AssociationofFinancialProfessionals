package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class HomePage_EwebActionsPage extends GeneralActionsPage{

	public HomePage_EwebActionsPage(WebDriver driver) {
		super(driver, "HomePage_EwebActionsPage");
	}

	/**
	 * method to select option from the left panel on home page
	 * @param option - user required option on the left panel
	 */
	public void userSelectOptionFromLeftPanel(String option){
		isElementDisplayed("link_leftPanel",option);
		element("link_leftPanel",option).click();
		msg.actions(option+" is selected from the left panel\n");
	}
	
}
