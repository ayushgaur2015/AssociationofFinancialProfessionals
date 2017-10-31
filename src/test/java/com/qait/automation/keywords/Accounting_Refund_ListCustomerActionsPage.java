package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Accounting_Refund_ListCustomerActionsPage extends GeneralActionsPage{

	public Accounting_Refund_ListCustomerActionsPage(WebDriver driver) {
		super(driver, "Accounting_Refund_ListCustomerActionsPage");
		
	}

	/**
	 * method to select customer list based on customer name
	 * @param customerName - user required customer name 
	 */
	public void selectCustomer(String customerName){
		int count=0;
		boolean flag=false;
		for(WebElement customer:elements("list_text_customerSortName")){
			if((customer.getText().contains(customerName))&&(elements("list_text_customerMailingAddress").get(count).getText().contains(keyValue(dataStorage, "Event Registrant Info")))){
				elements("list_text_customerGoToLink").get(count).click();
				msg.actions(customerName+" customer profile is navigated from customer list\n");
				flag=true;
				break;
			}count++;
		}
		if(flag){
			Assert.assertTrue(flag,msg.failForAssert(customerName+" named customer is not found from the list\n"));
		}
	}
	
}
