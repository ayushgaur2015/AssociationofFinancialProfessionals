package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;


public class EventsRegistrantsFindEventsRegistrantActionsPage extends GeneralActionsPage {

	public EventsRegistrantsFindEventsRegistrantActionsPage(WebDriver driver) {
		super(driver, "EventsRegistrantsFindEventsRegistrantActionsPage");
	}

	/**
	 * method to select event from the dropdown
	 * @param Event - dropdown item to be selected
	 */
	public void userSelectsEventFromDropDown(String Event) {
		selectProvidedTextFromDropDown(element("dropDown_Event"), Event);
		
	}
	
	/**
	 * method to click button Go or Cancel on find event registrant page 
	 * @param btn - user required button either Go or Cancel
	 */
	public void userClickOn_Button(String btn){
		if(btn=="Go"){
			element("btn_go").click();
		}
		if(btn=="Cancel")
		{
			element("btn_cancel").click();
		}
	}
	
	/**
	 * workflow to search event based on event code
	 * @param Event - code of the event to be searched
	 */
	public void userPerformsSearchBasedOnEvent(String Event){
		dataStorage.put("EventToCancel", Event);
		userSelectsEventFromDropDown(Event);
		userClickOn_Button("Go");
	}

	/**
	 * method to verify user on Find Event Registrant page
	 */
	public void verifyUserOnFindEventsRegistrantPage(){
		verifyPageTitleContains();
	}
	
	
}
