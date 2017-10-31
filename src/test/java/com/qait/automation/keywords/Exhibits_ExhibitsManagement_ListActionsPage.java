package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class Exhibits_ExhibitsManagement_ListActionsPage extends GeneralActionsPage{

	public Exhibits_ExhibitsManagement_ListActionsPage(WebDriver driver) {
		super(driver, "Exhibits_ExhibitsManagement_ListActionsPage");
	}

	/**
	 * method to store exhibits information like record number, bank name and booth number based on index
	 * @param index - user required record from the list
	 */
	public void storeExhibitsInformation(int index){
		dataStorage.put("ExhibitRecordNumber", elements("text_recordNumber").get(index).getText());
		msg.log(dataStorage.get("ExhibitRecordNumber")+" is stored as record number\n");
		dataStorage.put("ExhibitorOrganizationName", elements("text_exhibitorOrganization").get(index).getText());
		msg.log(dataStorage.get("ExhibitorOrganizationName")+" is stored as bank name\n");
		dataStorage.put("ExhibitorBoothNumber", elements("text_boothNumber").get(index).getText());
		msg.log(dataStorage.get("ExhibitorBoothNumber")+" is stored as booth number\n");
		
	}
	
	/**
	 * method to select record from the list generated based on user required index
	 * @param queryResult - user requierd index from the list
	 */
	public void userSelectsRecord_(String queryResult){
		wait.hardWait(2);
		isElementDisplayed("link_query",queryResult);
		element("link_query",queryResult).click();
		msg.pass(queryResult+" row is selected\n");
	}
	
}
