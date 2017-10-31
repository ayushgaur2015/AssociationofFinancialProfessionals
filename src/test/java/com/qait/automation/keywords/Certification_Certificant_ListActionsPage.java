package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class Certification_Certificant_ListActionsPage extends GeneralActionsPage{

	public Certification_Certificant_ListActionsPage(WebDriver driver) {
		super(driver, "Certification_Certificant_ListActionsPage");
	}
	
	/**
	 * method to store certificant record number
	 */
	public void userStoreCertificantRecordNumber(){
		String recordNumber = element("text_recordNumber").getText();
		msg.log(recordNumber+" is the record number saved\n");
		dataStorage.put("RecordNumber", recordNumber);
	}
	
	/**
	 * user select record based on the index value
	 * @param queryResult - integer value showing the position of the record
	 */
	public void userSelectsRecord(int queryResult){
		String row = String.valueOf(queryResult);
		isElementDisplayed("link_recordRevoked",row);
		element("link_recordRevoked",row).click();
		msg.pass(queryResult+" row is selected\n");
	}
	/**
	 * method to store record numbr of revoked certificant
	 */
	public void userStoreRevokedCertificantRecordNumber(){
		String recordName = element("text_recordRevokedName").getText();
		msg.log(recordName+" is the record number saved\n");
		dataStorage.put("Revoked Certificant Sort Name", recordName);
	}
	
}
