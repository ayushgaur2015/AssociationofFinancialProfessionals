package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class Inventory_Publication_FindPublicationActionsPage extends GeneralActionsPage {

	public Inventory_Publication_FindPublicationActionsPage(WebDriver driver) {
		super(driver, "Inventory_Publication_FindPublicationActionsPage");
	}

	/**
	 * method to verify user on find certificant page
	 */
	public void verifyUserIsOnFindCertificantPage() {
		wait.waitForPageToLoadCompletely();
		verifyPageTitleContains();
		msg.actions("verified user on find individual page\n");
	}

	/**
	 * method to select a search field and value either stored earlier or
	 * provided by user
	 * 
	 * @param fieldName
	 *            - name of the search field
	 * @param fieldValue
	 *            - value to be entered in the search field
	 */
	public void userSelectSearchFieldWithValue(String fieldName, String fieldValue) {
		isElementDisplayed("inp_searchField", fieldName);
		element("inp_searchField", fieldName).clear();
		element("inp_searchField", fieldName).sendKeys(fieldValue);
		msg.log(fieldValue + " is entered in " + fieldName + " \n");
	}

	/**
	 * method to filter publication product based on search field and value
	 */
	public void userSelectSearchFieldAndStoredFieldValue(String fieldName, String fieldValue) {
		fieldValue = dataStorage.get(fieldValue).toString();
		isElementDisplayed("inp_searchField", fieldName);
		element("inp_searchField", fieldName).clear();
		element("inp_searchField", fieldName).sendKeys(fieldValue);
		msg.log(fieldValue + " is entered in " + fieldName + " \n");
	}

	/**
	 * method to click on button on inventory find publication page
	 * 
	 * @param action
	 *            - user provided button type
	 */
	public void userClickOnButton(String action) {
		isElementDisplayed("button_Go", " " + action + " ");
		click(element("button_Go", " " + action + " "));
	}

}
