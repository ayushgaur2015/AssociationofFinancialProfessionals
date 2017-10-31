package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import com.qait.automation.utils.msg;

public class MembershipApplicationActionsPage extends GeneralActionsPage{

	public MembershipApplicationActionsPage(WebDriver driver) {
		super(driver,"MembershipApplicationActionsPage");
	}
	
	/**
	 * method to verify AFP membership link is displayed
	 * @param link - user provided link to be verified
	 */
	public void verifyAfpMembershipLinkIsDisplayed(String link){
		isElementDisplayed("link_afpMembership",link);
		msg.pass(" AFp membership link is verified\n");
	}
	
	/**
	 * method to click Select Item button
	 */
	public void userClickSelectItem(){
		isElementDisplayed("btn_selectItem");
		element("btn_selectItem").click();
		msg.log("User click on selectItem button\n");
	}

	/**
	 * method to verify user is directed successfully to AFP Membership Link page
	 */
	public void verifyUserDirectedSuccessfullyTO_afpMembershipLinkPage() {
		wait.waitForElementToDisappear(element("btn_clickHere"),20);
		msg.pass("Verified user is directed successfully to afp membership page\n");
	}
	
	
}
