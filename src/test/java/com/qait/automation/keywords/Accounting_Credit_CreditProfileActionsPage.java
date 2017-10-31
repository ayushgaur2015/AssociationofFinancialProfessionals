package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class Accounting_Credit_CreditProfileActionsPage extends GeneralActionsPage{

	public Accounting_Credit_CreditProfileActionsPage(WebDriver driver) {
		super(driver, "Accounting_Credit_CreditProfileActionsPage");
	}

	/**
	 * method to verify user is navigated to credit profile page
	 */
	public void verifyUserOnCreditProfilePage(){
		verifyPageTitleContains();
		msg.actions("verified user on credit profile page\n");
	}
	
	/**
	 * method to navigate to registrant profile page
	 */
	public void userGotoRegistantProfile(){
		isElementDisplayed("link_gotoRegistrantProfile");
		element("link_gotoRegistrantProfile").click();
		msg.actions("User click on goto link to navigate to registrant profile\n");
	}

}
