package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import com.qait.automation.utils.msg;

public class OverviewAndSetupActionsPage extends GeneralActionsPage {

	public OverviewAndSetupActionsPage(WebDriver driver) {
		super(driver, "OverviewAndSetupActionsPage");
	}

	/**
	 * verification that user is on overview and setup page
	 */
	public void verifyUserOnOverviewAndSetupPage() {
		getCurrentWindow();
		verifyPageTitleContains();
		msg.actions("verifed user on overview and setup page \n");
	}
	
	/**
	 * user click on required wizard link
	 * @param wizardLink - link text provided by user
	 */
	public void userClickOnOverViewWizard(String wizardLink){
		isElementDisplayed("link_overviewWizards");
		element("link_overviewWizards",wizardLink).click();
		msg.actions(wizardLink+" link is clicked\n");
	}

}
