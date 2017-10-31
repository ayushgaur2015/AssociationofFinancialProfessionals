package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.DateUtil;
import com.qait.automation.utils.msg;

public class Certification_CertificantActionsPage extends GeneralActionsPage{

	String[] massReplaceParameters={"crt :: Certified? (crt_certified_flag)","crt :: revoke date (crt_cert_revoked_date_ext)","crt :: Status (crt_ces_key)"};
	String currentDate= DateUtil.getCurrentdateInStringWithGivenFormate("M/d/yyyy");
	String[] massReplaceParametersValue={"Un-Checked",currentDate,"Revoked"};
	public static int replacement=0;
	
	public Certification_CertificantActionsPage(WebDriver driver) {
		super(driver, "Certification_CertificantActionsPage");
	}
	
	/**
	 *  method to select replacement field name based on the massReplaceParamaeters string array
	 */
	public void userSelectFieldName(){
		selectProvidedTextFromDropDown(element("dropdown_fieldName"), massReplaceParameters[replacement]);
		dataStorage.put("fieldName_Iteration_"+replacement,massReplaceParameters[replacement]);
		msg.actions(massReplaceParameters[replacement]+" is selected from the field name\n");
	}

	/**
	 * method to select field value using massReplaceParametersValue string array
	 */
	public void userSelectFieldValue(){
		wait.hardWait(2);
		msg.log(currentDate+" is entered as current date\n");
        if(replacement!=1){
			selectProvidedTextFromDropDown(element("dropdown_fieldValue"), massReplaceParametersValue[replacement]);
			}
		
		else{
			element("inp_fieldValue").click();
			element("inp_fieldValue").sendKeys(massReplaceParametersValue[replacement]);

		}
		dataStorage.put("fieldValue_Iteration_"+replacement,massReplaceParametersValue[replacement]);
		msg.actions(massReplaceParametersValue[replacement]+" is selected as field value\n");
	}
	
	/**
	 * method to select clear value checkbox
	 */
	public void userSelectClearValueCheckBox(){
		element("checkBox_clear").click();
		msg.actions("User checks the clear value checkbopx");
	}
	
	/**
	 * method to to click on user required button
	 * @param button - user required button type
	 */
	public void userClickOn(String button){
		userClickOn("btn_replace_cancel", button);
		wait.hardWait(2);
		handleAlert();
		replacement++;
	 }
	}
