package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.qait.automation.utils.msg;

public class IndividualsQueryActionsPage extends GeneralActionsPage {
	
	
	public IndividualsQueryActionsPage(WebDriver driver) {
		super(driver,"IndividualsQueryActionsPage");	}

	/**
	 * userclick on the corresponsing operation provided by user
	 * @param operation - query operation eg: run, update etc.
	 */
	public void queryOperation(String operation){
		userClickOn("inp_run_update_updateAndCreate_save","operation");
		msg.log(operation+"is complete\n");
	}
	
	/**
	 * user select existing query and calls method to perform operation on the query
	 * @param query - user created query
	 * @param operation - user provided operation to be applied on query
	 */
	public void userPerformsOperationOnExistingQuery(String query, String operation){
		SelectsFromExistingquey(query,operation);
		wait.waitForElementToBeVisible(element("inp_run_update_updateAndCreate_save", operation));
		userClickOn("inp_run_update_updateAndCreate_save",operation);
	}

	/**
	 * method to verify user canceled the event
	 * @param menuItem - the childview under which the cancelled element is present
	 */
	public void userVerifyCancellationOfEvent(String menuItem) {
		switchToDefaultContent();
		msg.log("Switched to default content\n");
		
		wait.waitForElementToBeVisible(element("tab_more"));
		hoverClick(element("link_userName"));
		msg.log("username is clicked");
		
		wait.waitForElementToBeVisible(element("tab_more"));
		if(element("text_profileTitle").getText()!="Other Actg"){
			wait.waitForElementToBeVisible(element("tab_more"));
			element("tab_more").click();
			msg.log("clicked on more button");
			wait.waitForElementToBeVisible(element("list_profileMenuItems","Other Actg"));
			hoverClick(element("list_profileMenuItems","Other Actg"));
			msg.log("Clicked on other Actg");
		}
		if(element("link_childView",menuItem).getAttribute("class").contains("down")){
			element("link_childView",menuItem).click();
			msg.log("expanded the view");
		}
		Assert.assertNotNull(element("row_refund"), msg.failForAssert("verification failed"));
		msg.pass("verification complete");
	}
	
}
