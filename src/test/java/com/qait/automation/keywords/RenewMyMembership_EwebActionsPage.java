package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class RenewMyMembership_EwebActionsPage extends GeneralActionsPage {

	public RenewMyMembership_EwebActionsPage(WebDriver driver) {
		super(driver, "RenewMyMembership_EwebActionsPage");
	}

	/**
	 * method to click on renew link
	 */
	public void customerClickRenew(){
		click(element("link_renew"));
		msg.actions("clicked on renew link to renew membership\n");
	}
	
}
