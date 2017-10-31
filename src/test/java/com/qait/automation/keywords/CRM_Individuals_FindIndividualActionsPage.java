package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import com.qait.automation.utils.msg;

public class CRM_Individuals_FindIndividualActionsPage extends GeneralActionsPage {

	public CRM_Individuals_FindIndividualActionsPage(WebDriver driver) {
		super(driver, "CRM_Individuals_FindIndividualActionsPage");
	}

	/**
	 * method to verify that user is navigated to Find Individual page
	 */
	public void verifyUserIsOnFindIndividualPage() {
		verifyPageTitleContains();
		msg.actions("verified user on find individual page\n");
	}

	/**
	 * method to enter value in a required search field
	 * @param fieldName - user required field for searching individual
	 * @param fieldValue - value used for filtering of individual
	 */
	public void userSelectSearchFieldAndStoredValue(String fieldName, String fieldValue) {
		fieldValue = dataStorage.get(fieldValue).toString();
			isElementDisplayed("inp_searchField", fieldName);
			element("inp_searchField", fieldName).clear();
			element("inp_searchField", fieldName).sendKeys(fieldValue);
		msg.log(fieldValue + " is entered in " + fieldName + " \n");
		
	}
	
	
	/**
	 * method to enter value in a required search field
	 * @param fieldName - user required field for searching individual
	 * @param fieldValue - value used for filtering of individual
	 */
	public void userSelectSearchFieldAndEnterValue(String fieldName, String fieldValue) {
			isElementDisplayed("inp_searchField", fieldName);
			element("inp_searchField", fieldName).clear();
			element("inp_searchField", fieldName).sendKeys(fieldValue);
			msg.log(fieldValue + " is entered in " + fieldName + " \n");
		
	}

	/**
	 * user method to click on button
	 * @param action - type of the button to be clicked 
	 */
	public void userClickOnButton(String action) {
		isElementDisplayed("button_Go", " " + action + " ");
		click(element("button_Go", " " + action + " "));
	}
}
