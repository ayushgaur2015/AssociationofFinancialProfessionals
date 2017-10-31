package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class CRM_PaymentProfileActionsPage extends GeneralActionsPage {

	public CRM_PaymentProfileActionsPage(WebDriver driver) {
		super(driver, "CRM_PaymentProfileActionsPage");
	}

	/**
	 * method to verify that the payment for the product is complete
	 * @param bar - the childview to locate the item
	 * @param product - the item to be located
	 */
	public void userVerifyPaymentForProduct(String bar,String product) {
		Assert.assertTrue(isElementDisplayed("text_lineItems_"+bar.replaceAll("_", ""), product),
				msg.failForAssert(product + " is not displayed hence process incomplete\n"));
		msg.pass(product + " is displayed hence process complete\n");
		msg.actions("user verify payment for product completes\n");
	}

	/**
	 * method to verify that the user is navigated to payment profile page
	 */
	public void verifyUserOnPaymentProfile() {
		verifyPageTitleContains();
		msg.actions("verified user on invoice profile\n");
	}


}
