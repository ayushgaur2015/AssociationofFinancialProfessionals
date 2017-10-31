package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

public class Events_EventPlanning_FindEventsActionsPage extends GeneralActionsPage{

	public Events_EventPlanning_FindEventsActionsPage(WebDriver driver) {
		super(driver, "Events_EventPlanning_FindEventsActionsPage");
	}
	
	/**
	 * method to verify user is navigated to find event page
	 */
	public void verifyUserOnFindEventPage(){
		verifyPageTitleContains();
	}
	
	/**
	 * method to click on a button if find event page
	 * @param action - type of the button tto be clicked
	 */
	public void userClickOnButton(String action) {
		isElementDisplayed("button_Go", " " + action + " ");
		click(element("button_Go", " " + action + " "));
	}
	
	/**
	 * method to find event using event code
	 * @param eventCode - user provided event code for finding event
	 */
	public void user_EntersEventCodeToFindEvent(String eventCode){
		enterDetails("textField", eventCode);
		userClickOnButton("Go");
	}

}
