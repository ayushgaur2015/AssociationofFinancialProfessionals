package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;



public class QueryEventRegistrantActionsPage extends GeneralActionsPage{

	public QueryEventRegistrantActionsPage(WebDriver driver) {
		super(driver,"QueryEventRegistrantActionsPage");
		
	}

	/**
	 * method to verify user on Query Event Registration page
	 */
	public void verifyUserOnQueryEventRegistrationPage() {
		verifyPageTitleContains();
	}
		
	
}
