package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

public class EventsRegistrantsListEventsRegistrantProfileActionsPage extends GeneralActionsPage {

	public EventsRegistrantsListEventsRegistrantProfileActionsPage(WebDriver driver) {
		super(driver, "EventsRegistrantsListEventsRegistrantProfileActionsPage");
	}

	/**
	 * method to select random individuals from the list of event registrant profile page
	 */
	public void userSelectsRandomIndividuals(){
		int userCount=0;
	    String pageCount=generateRandomOutput(10);
	    String subtituentPosition = generateRandomOutput(20);
   	    while(element("link_queryFieldsName",subtituentPosition).getText().isEmpty()||element("link_queryFieldsCountry",subtituentPosition).getText().isEmpty()||!(element("link_queryFieldsCancelDate",subtituentPosition).getText().isEmpty())){
   	    	subtituentPosition = generateRandomOutput(20);
   	    	if(userCount==10){
   	    		userCount=0;
   	    		element("link_individualListNextPage",pageCount).click();
		     }
		     userCount++;
	     }
   	 element("link_query",subtituentPosition).click();
	 }
	
	/**
	 * method to verify user on list eventg registrant profile page
	 */
	public void verfiyUserOnListEventsRegistrantProfilePage(){
		verifyPageTitleContains();
	}
	
	
}
