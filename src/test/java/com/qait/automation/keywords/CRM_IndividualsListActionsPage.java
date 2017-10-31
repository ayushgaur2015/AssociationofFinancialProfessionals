package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qait.automation.utils.msg;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

public class CRM_IndividualsListActionsPage extends GeneralActionsPage{

	public CRM_IndividualsListActionsPage(WebDriver driver) {
			super(driver,"CRM_IndividualsListActionsPage");
			}
	
	public int getColumn(String heading){
		int columnCount=0;
		for(WebElement column: elements("list_headerLink")){
			if(column.getText()==heading){
				return columnCount;
			}
			columnCount++;
		}
		return -1;
	}
	
	public int getRow(String rowValue){
		int rowCount=1;
		wait.hardWait(3);
		for(WebElement row : elements("list_queryResults")){
			if(row.getText().contains(rowValue)){
				return rowCount;
			}
			rowCount++;
		}
		return -1;
	}
	
	public void userSelectsRecord(String queryResult){
		String row = String.valueOf(getRow(queryResult));
		wait.hardWait(2);
		isElementDisplayed("link_query",row);
		element("link_query",row).click();
		msg.pass(queryResult+" row is selected\n");
	}
	
	public void userSelectsRecord_(String queryResult){
		wait.hardWait(2);
		isElementDisplayed("link_query",queryResult);
		element("link_query",queryResult).click();
		msg.pass(queryResult+" row is selected\n");
	}
		
	public void userSelectRecord_BillTo_UsingMailingLabel(){		
		for(WebElement indMailingLabl:elements("list_text_individualMailingLabel")){
			if(indMailingLabl.getText().contains(keyValue(dataStorage,"mailingLabel")));
			indMailingLabl.click();
			waitForLoaderToDisappear();
		}
		msg.actions("selected the cusotmer for which the bill is generated\n");
	}
	
	public void userClickOnMassReplaceButton(){
		element("icon_massReplace").click();
		msg.actions("clicked on mass replace button\n");
	}
	
	public void userSelectFieldNameDesignation(){
		selectProvidedTextFromDropDown(element("dropdown_fieldName"), "ind :: Designation (ind_designation)");
		dataStorage.put("fieldName_Iteration_","ind :: Designation (ind_designation)");
	}

	public void userSelectFieldValue_Designation(){
		wait.hardWait(2);
		selectProvidedTextFromDropDown(element("dropdown_fieldValue"), "CTP");
		dataStorage.put("dropdown_fieldValue","CTP");
	}
		
	public void userSelectClearValueCheckBox(){
		element("checkBox_clear").click();
		msg.actions("User checks the clear value checkbopx\n");
	}
	
	public void userInputFieldValue_Designation(){
		wait.hardWait(3);
		element("inp_fieldValue").click();
		element("inp_fieldValue").sendKeys("CTP");
		msg.actions("CTP" + " is entered in filed value \n");
		dataStorage.put("dropdown_fieldValue","CTP");
	}
	
	public void userClickOn(String button){
		userClickOn("btn_replace_cancel", button);
		msg.actions(button+" button is clicked\n");
		handleAlert();
	 }
}
