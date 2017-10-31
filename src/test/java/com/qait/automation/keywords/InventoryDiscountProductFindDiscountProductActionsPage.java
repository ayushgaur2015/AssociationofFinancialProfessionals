package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class InventoryDiscountProductFindDiscountProductActionsPage extends GeneralActionsPage{

	public InventoryDiscountProductFindDiscountProductActionsPage(WebDriver driver) {
		super(driver, "InventoryDiscountProductFindDiscountProductActionsPage");
	}

	/**
	 * method to verify that the discount product is either present o
	 * @param event - name of the event to be verified
	 * @return - boolean true if present and false if absent
	 */
	public boolean userVerifyDiscountProductPresent(String event){
		for(WebElement option:elements("text_dropdown_discountKey")){
			if(option.getText().equals(event)){
				msg.log(event+" is present\n");
			msg.actions("user verified discount product present\n");
				return true;
				}
		}
		msg.log(event+" is not present\n");
		msg.actions("user verified discount product not present\n");

		return false;
	}
	
	/**
	 * based on user requirement check whether to check for presence or absence of discount product
	 * @param present - boolean user defined value
	 * @param eventName - name of the discount event to verify
	 */
	public void userFindDiscountUsingDisplayName_IfPresent(boolean present, String eventName){
		if(present){
		selectProvidedTextFromDropDown(element("dropDown_discountKey"), eventName);
		element("button_Go"," Go ").click();
		msg.actions("user find discount product using display name present \n");
		}
		else{
            
			msg.actions("user find discount product using display name is not present \n");

		}
	}
}
