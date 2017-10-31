package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Inventory_FulfillmentOrders_FulfillmentGroupProfileActionsPage extends GeneralActionsPage {

	public Inventory_FulfillmentOrders_FulfillmentGroupProfileActionsPage(WebDriver driver) {
		super(driver, "Inventory_FulfillmentOrders_FulfillmentGroupProfileActionsPage");
	}

	/**
	 * method to verify shipping details of an event
	 * @param EventName - name of the event for which the details are to be verified
	 */
	public void verifyShippingDetails(String EventName){
		msg.log(elements("list_text_fulfillmentOrdername").get(0).getText()+" is actual and "+EventName+" expected\n");
		Assert.assertTrue(elements("list_text_fulfillmentOrdername").get(0).getText().contains(EventName),msg.failForAssert(elements("list_text_fulfillmentOrdername").get(0).getText()+" user name is not matched with "+EventName));
		msg.pass("fullfilled order name is verified\n");
		msg.log("*Actual* ==\n"+elements("list_text_fulfillmentOrderSendTo").get(0).getText()+" \n and *Expected*\n "+keyValue(dataStorage,"mailingLabel")+"\n");

		Assert.assertTrue(elements("list_text_fulfillmentOrderSendTo").get(0).getText().contains(keyValue(dataStorage,"mailingLabel")),msg.failForAssert(elements("list_text_fulfillmentOrderSendTo").get(0).getText()+" user name does not contains "+keyValue(dataStorage,"mailingLabel")));
		msg.pass("SendTo address is verified\n");
		msg.actions("verified shipping details\n");
	
	
	}
	
	
}
