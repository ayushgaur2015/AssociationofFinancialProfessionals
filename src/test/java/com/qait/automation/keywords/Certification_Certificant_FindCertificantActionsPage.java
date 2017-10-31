package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class Certification_Certificant_FindCertificantActionsPage extends GeneralActionsPage {

	public Certification_Certificant_FindCertificantActionsPage(WebDriver driver) {
		super(driver,"Certification_Certificant_FindCertificantActionsPage");	
	}

	/**
	 * method user search certificant using stored record number
	 */
	public void userSearchCertificantUsingRecordNumber(){
		element("input_RecordNumber").sendKeys(keyValue(dataStorage, "RecordNumber"));
		msg.actions(keyValue(dataStorage, "RecordNumber")+" is entered as record number\n");
		userClickOn("button_Go", " Go ");
		msg.actions("user search certificant using record number completes\n");
	}
	
}
