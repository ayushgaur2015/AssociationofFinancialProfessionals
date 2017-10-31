package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.qait.automation.utils.msg;

public class Accounting_Refund_AddRefundActionsPage extends GeneralActionsPage {

	public Accounting_Refund_AddRefundActionsPage(WebDriver driver) {
		super(driver, "Accounting_Refund_AddRefundActionsPage");
	}

	public void verifyUserOnAddRefundPage() {
		verifyPageTitleContains();
		msg.actions("verified user on add refund page\n");
	}

	/**
	 * method to enter customer sorted name
	 * @param name - sorted name of the customer
	 */
	public void enterSortName(String name) {
		enterDetails("sortName", name);
		msg.actions("Sorted name of the customer is entered\n");
	}

	public void clickSearchIcon() {
		hoverClick(element("icon_searchSortName"));
		msg.actions("clicked on search icon completes\n");

	}

	public void selectRefundType(String refundType) {
		selectProvidedTextFromDropDown(element("dropdown_refundType"), refundType);
		msg.log(refundType + " is selected as the refund type\n");
		msg.actions("refund type is selected\n");
	}

	public void selectBatchType(String batch) {
		dynamicWait(5, "dropdown_batch");
		selectProvidedTextFromDropDown(element("dropdown_batch"), batch);
		msg.log(batch + " is selected as the batch type\n");
		msg.actions("batch type is selected\n");
	}

	public void enterRefundAmount(String userName, String ref_amnt, String applied) {
		int searchResult=0;
		for (WebElement amnt : elements("list_text_amntAvailable")) {
			if(elements("list_name_sortName").get(searchResult).equals(userName))
			if (amnt.getText().equals(ref_amnt)) {
				msg.log(ref_amnt + " is the refund amount available\n");
				element("list_inp_refundAmnt").clear();
				msg.log(applied + " is the applied amount\n");
				element("list_inp_refundAmnt").sendKeys(applied);
				break;
			}searchResult++;
		}
		msg.actions("refund amount is entered\n");
	}

	/**
	 * workflow to enter sorted name and search for the customer
	 * @param name - sorted name of the customer
	 */
	public void userSearchCustomerToPerformRefund(String name) {
		enterSortName(name);
		clickSearchIcon();
		msg.actions("user search customer to perform refund completes\n");
	}

	public void userEntersRefundDetailsAndSearchForRefundInvoice(String batch) {
		selectBatchType(batch);
		dynamicWait_withReplacement(3, "btn_saveCancel", "search");
		userClickOn("btn_saveCancel", "search");
		msg.log("user enter refund details and search for refund completes\n");
	}

	public void userEnterRefundAmtAndSave(String userName, String ref_amnt, String applied) {
		enterRefundAmount(userName,ref_amnt, applied);
		msg.log("user enter refund amount and save compltes\n");
	}

	public void userSaveRefundApplied() {
		userClickOn("btn_saveCancel", "Save");
		handleAlert();
		msg.log("user save refund applied compltes \n");
	}

}
