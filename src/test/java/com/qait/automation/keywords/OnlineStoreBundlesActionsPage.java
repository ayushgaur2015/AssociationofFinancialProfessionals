package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;

public class OnlineStoreBundlesActionsPage extends GeneralActionsPage{

	public OnlineStoreBundlesActionsPage(WebDriver driver) {
		super(driver,"OnlineStoreBundlesActionsPage");
	}
	
	/**
	 * method to verify user details on online store bundles
	 */
	public void verifyUserDetailsOnOnlineStoreBundles(){
		userClickOn("btn_next_cancel","Next");
		assertTrue(element("text_memberName").getText().contains(YamlReader.getYamlValue("personalInformation.firstName")),msg.failForAssert("Invoice for invalid user is created"));
		msg.pass("Invoice for the user "+element("text_memberName")+" is generated\n");
		userClickOn("btn_next_cancel","Next");
		msg.log("verify details on online store bundles");
	}

	/**
	 * method to verify user on Online store bundles page
	 */
	public void verifyUserOnOnlineStoreBundlesPage() {
		verifyPageTitleContains();
	}
	
}
