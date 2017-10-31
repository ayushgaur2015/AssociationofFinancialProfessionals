package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import com.qait.automation.utils.msg;

public class InventoryFulfillmentOrdersOverviewActionsPage extends GeneralActionsPage {

	public InventoryFulfillmentOrdersOverviewActionsPage(WebDriver driver) {
		super(driver, "InventoryFulfillmentOrdersOverviewActionsPage");
	}

	/**
	 * method user click on user provided link
	 * @param link - user provided link on inventory fulfillment Orders Overview page
	 */
	public void userClickOnLink(String link){
		element("link_onFullfilmentOrders",link).click();
		msg.pass("User Click on link "+link+" is complete\n");
	}
	
	/**
	 * method to verify user on inventory fulfillment order overview page
	 */
	public void verifyUserOnInventoryFulfillmentOrderOverviewPage(){
		verifyPageTitleContains();
	}
	
}
