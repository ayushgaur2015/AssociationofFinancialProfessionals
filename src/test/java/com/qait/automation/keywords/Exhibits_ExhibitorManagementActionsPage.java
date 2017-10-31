package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Exhibits_ExhibitorManagementActionsPage extends GeneralActionsPage {

	public String boothCancelled = null;

	public Exhibits_ExhibitorManagementActionsPage(WebDriver driver) {
		super(driver, "Exhibits_ExhibitorManagementActionsPage");
	}

	/**
	 * workflow to click on cancel button
	 */
	public void userClickCancelExhibitBtn() {
		userClickOn("btn_cancel_transfer_edit", "cancel");
	}

	/**
	 * method to select exhibit to cancel based on the index
	 */
	public void selectExhibitProductToCancel(int index) {
		boothCancelled = elements("list_text_boothNumber").get(index).getText();
		click(elements("list_chkBox_cancelFirstBooth").get(index));
	}

	/**
	 * verify that on selecting exhibit to cancel the credit fees is generated
	 */
	public boolean verifyCreditGenerated(int index) {
		try {
			waitForLoaderToDisappear();
			switchToDefaultContent();
			switchToFrame(element("frame_editCancel"));
			wait.resetImplicitTimeout(10);
			wait.hardWait(3);
			Assert.assertFalse(element("text_value_creditAmnt", index).getAttribute("value").equals("0.00"));
			msg.log(element("text_value_creditAmnt", index).getText() + "Credit is generated for canellation\n");
			return true;
		} catch (AssertionError exp) {
			msg.log("Credit for cancellation is not generated\n");
			return false;
		}
	}

	/**
	 * select exhibits cancellation fees from the list of cancellation fees
	 * based on index value
	 * 
	 * @param index
	 */
	public void selectExhibitCancellationFees(int index) {
		msg.log(boothCancelled + " is the booth number to be cancelled\n");
		click(elements("chkbox_cancellationFee").get(index));

	}

	/**
	 * workflow to fill in details for the cancellation of the exhibit
	 */
	public void userCompleteCancellationProcess(String batch) {
		switchToFrame(element("frame_editCancel"));
		selectExhibitProductToCancel(0);
		boolean generated = verifyCreditGenerated(1);
		if (generated) {
			selectExhibitCancellationFees(0);
			selectBatch(batch);
			switchToDefaultContent();
			switchToFrame(element("frame_editCancel"));
			waitForLoaderToDisappear();
			scrollDown(element("dropDown_paymentType"));
			selectPaymentType("prepaid", "Check");
		} else {
			selectBatch(batch);
			switchToDefaultContent();
			isElementDisplayed("frame_editCancel");
			switchToFrame(element("frame_editCancel"));
			waitForLoaderToDisappear();
			scrollDown(element("dropDown_paymentType"));
			selectPaymentType("terms", "Due Upon Receipt");

		}
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
	}

	/**
	 * method to verify that exhibit is cancelled
	 */
	public void userVerifyExhibitCancelled() {
		boolean flag = true;
		for (WebElement assignedBooth : elements("list_assignedBooth_exhibitorBooths")) {
			if (assignedBooth.getText().contains(boothCancelled)) {
				flag = false;
				break;
			}

		}
		Assert.assertTrue(flag, msg.failForAssert(boothCancelled + " exhibit booth is not canelled\n"));
	}
}
