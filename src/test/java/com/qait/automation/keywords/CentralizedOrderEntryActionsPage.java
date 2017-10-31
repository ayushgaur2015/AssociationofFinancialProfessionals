package com.qait.automation.keywords;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class CentralizedOrderEntryActionsPage extends GeneralActionsPage {

	private Double itemPrice;
	private Double itemDiscount;
	private Double itemNetTotal;
	private Double itemNetBalance;
	private Double amntDiscount;

	public CentralizedOrderEntryActionsPage(WebDriver driver) {
		super(driver, "CentralizedOrderEntryActionsPage");
	}

	public void selectDropDownLink(String dropDown_link) {
		dynamicWait_withReplacement(3, "link_SelectProd_complType_addItem_shipTO_BillTO", dropDown_link);
		clickUsingXpathInJavaScriptExecutor(element("link_SelectProd_complType_addItem_shipTO_BillTO", dropDown_link));
		msg.pass(dropDown_link + " is clicked from link_SelectProd_complType_addItem_shipTO_BillTO \n");
	}

	public void selectItemFromDropDown(String item) {
		isElementDisplayed("link_productSelector", item);
		element("link_productSelector", item).click();
		msg.pass(item + " is selected from link_productSelector\n");
	}

	public void userSelectsItemFromDropDown(String dropDown_link, String item) {
		waitForLoaderToDisappear();
		selectDropDownLink(dropDown_link);
		switchToFrame(4);
		msg.log("select item link is expanded\n");
		selectItemFromDropDown(item);
		switchToDefaultContent();
		msg.actions("user selects " + dropDown_link + " and item " + item + " is complete\n");
	}

	public void userSelectsAdditionalItemFromDropDown(String dropDown_link, String item) {
		dynamicWait_withReplacement(3, "link_SelectProd_complType_addItem_shipTO_BillTO", dropDown_link);
		clickUsingXpathInJavaScriptExecutor(element("link_SelectProd_complType_addItem_shipTO_BillTO", dropDown_link));
		msg.pass(dropDown_link + " is clicked from link_SelectProd_complType_addItem_shipTO_BillTO \n");
		switchToFrame(5);
		msg.pass("Add discount product is expanded\n");
		selectItemFromDropDown(item);
		switchToDefaultContent();
		msg.actions("user selects " + dropDown_link + " and item " + item + " is complete\n");
		msg.actions("user selects Additional Item and dropdown " + item + " is complete\n");
	}

	public void userSelectsItemFromDropDownOnCentralizedEntryPage(String dropDown_link, String item) {
		browserSpecificHolds();
		selectDropDownLink(dropDown_link);
		selectItemFromDropDown(item);
		msg.pass("user selects " + dropDown_link + " and item " + item + " is complete\n");
	}

	public void userSelectProduct(String product) {
		switchToFrame(element("frame_eventRegSubForm"));
		selectProvidedTextFromDropDown(element("dropDown_selectProduct"), product);
		msg.log(product + " Product is selected\n");

	}

	public void userSelectProductCategory(String category) {
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropdDown_selectProductCategory"), category);
		msg.log(category + " category is selected\n");
	}

	public void userSelectProductDisplayName() {
		switchToDefaultContent();
		msg.log("Switched to default content\n");
		switchToFrame(element("frame_eventRegSubForm"));
		msg.log("Switched to frame content\n");

		browserSpecificHolds();
		isElementDisplayed("icon_searchDisplayName");
		wait.waitForElementToBeClickable(element("icon_searchDisplayName"));
		element("icon_searchDisplayName").click();
		String event = generateRandomOutput(8, 2);
		msg.log(element("link_DisplayName", event).getText() + " Display name is selected \n");
		clickUsingXpathInJavaScriptExecutor(element("link_DisplayName", event));
		waitForLoaderToDisappear();
		msg.log("Merchandise display name " + element("inp_displayName").getAttribute("value") + "\n");
		dataStorage.put("Merchandise display name", element("inp_displayName").getAttribute("value"));
		waitForLoaderToDisappear();
		userClickOn("btn_save_cancel_next_finish_back", "Save & Finish");
		msg.log("user selcts product and finish recertification\n");

	}

	public void userClickOnSearchIcon(String iconSearchOf) {
		isElementDisplayed("icon_" + iconSearchOf);
		hoverClick(element("icon_" + iconSearchOf));
		msg.actions("User click on search icon completes\n");
		System.out.println(keyValue(dataStorage,"mailingLabel"));
		waitForLoaderToDisappear();
	}

	public String userVerifyMerchandise() {
		switchToDefaultContent();
		msg.log("Actual merchandise selected " + dataStorage.get("Merchandise display name").toString() + "\n");
		Assert.assertTrue(
				element("text_lineItems").getText()
						.contains(
								dataStorage.get("Merchandise display name").toString().split("-")[0]),
				msg.failForAssert("Recertification incomplete "
						+ (dataStorage.get("Merchandise display name").toString().split("-")[0])) + " and "
						+ element("text_lineItems").getText() + " are different ");
		msg.pass(dataStorage.get("Merchandise display name").toString() + " Merchandise in line item is verified\n");
		return element("text_lineItems").getText();
	}

	public String userStoresCustomerInformation() {
		dataStorage.put("AFPMemberName", element("inp_billTo").getAttribute("value"));
		msg.actions(element("inp_billTo").getAttribute("value") + " is the customer billto information\n");
		return keyValue(dataStorage, "AFPMemberName");
	}

	public void userVerifyCustomerInformation() {
		wait.waitForPageToLoadCompletely();
		wait.hardWait(3);
		Assert.assertTrue((dataStorage.get("AFPMemberName").toString()).contains(element("inp_billTo").getText()),
				msg.failForAssert("Invalid Member Details" + dataStorage.get("AFPMemberName").toString()) + " and "
						+ element("inp_billTo").getText() + " are different ");
		msg.pass("customer information is verified\n");
		msg.actions("user Verifies Customer Information completes \n");

	}

	public void userChangeBillTo_Customer(String customerName) {
		browserSpecificHolds();
		wait.waitForPageToLoadCompletely();
		element("inp_billTo").clear();
		element("inp_billTo").sendKeys(customerName);
		msg.pass("customer information is verified\n");
		msg.actions("user Verifies Customer Information completes \n");

	}

	public void userSelectRecord_BillTo_UsingMailingLabel() {
		switchToFrame(element("frame_lookBillToCustomer"));
		isElementDisplayed("text_individualMailingLabel", keyValue(dataStorage, "mailingLabel"));
		element("text_individualMailingLabel", keyValue(dataStorage, "mailingLabel")).click();
		waitForLoaderToDisappear();
		msg.actions("selected the customer for which the bill is generated\n");
	}

	public void userCompletesTransaction_andClickOn_(String Batch, String PaymentType, String PaymentMethod,
			String operation) {
		boolean flag = false;
		waitForLoaderToDisappear();
		wait.hardWait(3);
		for (WebElement batch : elements("text_dropDownBatchOption")) {
			flag = false;
			if (batch.getText().contains(Batch)) {
				Batch = batch.getText();
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, msg.failForAssert("Batch containing word " + Batch + " not found \n"));
		if (flag) {
			flag = selectBatch(Batch);
			Assert.assertTrue(flag, msg.failForAssert(Batch + " Batch not selected from dropdown \n"));
			selectPaymentType(PaymentType, PaymentMethod);
			scrollDown(element("btn_centralizedOrderEntry", operation));
			userClickOn("btn_centralizedOrderEntry", operation);
			msg.actions("user completes transaction and click on " + operation + " is done \n");
			switchToDefaultContent();
		}
	}

	public void userCompletesTransaction_andClickOn_(String Batch, String PaymentType, String PaymentMethod, int amount,
			String operation) {
		waitForLoaderToDisappear();
		boolean flag = false;
		for (WebElement batch : elements("text_dropDownBatchOption")) {
			flag = false;
			if (batch.getText().contains(Batch)) {
				Batch = batch.getText();
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, msg.failForAssert("Batch containing word " + Batch + " not found \n"));
		if (flag) {
			flag = selectBatch(Batch);
			Assert.assertTrue(flag, msg.failForAssert(Batch + " Batch not selected from dropdown \n"));
			selectPaymentType(PaymentType, PaymentMethod, amount);
			scrollDown(element("btn_centralizedOrderEntry", operation));
			userClickOn("btn_centralizedOrderEntry", operation);
			handleAlert();
			msg.actions("userCompletesTransaction_andClickOn_is done \n");
		}
	}

	public void verifyUserOnCentralizedOrderEntryPage() {
		verifyPageTitleContains();
		msg.actions("verified user is on centralized order entry page\n");
	}

	public void verifyLineItemInOrderEntry(String productType) {
		waitForLoaderToDisappear();
		Assert.assertEquals(element("text_lineItems").getText(), productType.replaceAll("/20", "/"));
		msg.pass(productType + " is displayed in line item\n");
		msg.log("verified line items in order entry\n");
	}

	public void verifyPublicationsLineItemsINOrderEntry(String product) {
		waitForLoaderToDisappear();
		Assert.assertEquals(element("text_lineItems").getText(), product);
		msg.pass(product + " is displayed in line item\n");
		msg.actions("verified line items in order entry\n");

	}

	public void clickOnNetCreditLink() {
		isElementDisplayed("link_netCredit");
		element("link_netCredit").click();
		msg.actions("clicked on net credit link\n");
	}

	public void enterCreditToApply() {
		String total = element("text_netTotalOnCreditApply").getText();
		enterDetails("creditAmntToApply", total);
		userClickOn("btn_centralizedOrderEntry", "OK");
		msg.log(total + " credit of billTo customer is applied successfully\n");
	}

	public void userApplyCreditOfBillToCustomer() {
		clickOnNetCreditLink();
		switchToFrame(element("frame_centralizedOrderEntry"));
		msg.pass("Apply Credit wizard window appeared\n");
		enterCreditToApply();
		switchToDefaultContent();
	}

	public void userSearchDiscountProduct(String discoutProd) {
		switchToFrame(element("frame_centralizedOrderEntry"));
		enterDetails("displayName", discoutProd);
		userClickOnSearchIcon("searchDisplayName");
		switchToDefaultContent();
	}

	public void userSelectRequiredDiscountProductAndClick(String operation) {
		wait.hardWait(3);
		amntDiscount = Double.parseDouble(getLineItemValueOf_("text_addDiscountPrice"));
		msg.log(amntDiscount + " is the applied discount value\n");
		isElementDisplayed("btn_centralizedOrderEntry", operation);
		element("btn_centralizedOrderEntry", operation).click();
		wait.hardWait(3);
		switchToDefaultContent();
	}

	public String getLineItemValueOf_(String lineItemHeading) {
		isElementDisplayed(lineItemHeading);
		return element(lineItemHeading).getText();
	}

	public void userStoreLineItemAmountValuesBeforeDiscount() {
		itemPrice = Double.parseDouble(getLineItemValueOf_("text_lineItemPrice"));
		msg.log(itemPrice + " is the line item price value\n");
		itemDiscount = Double.parseDouble(getLineItemValueOf_("text_lineItemDiscount"));
		msg.log(itemDiscount + " is the discount field value\n");
		itemNetTotal = Double.parseDouble(getLineItemValueOf_("text_lineItemNetTotal"));
		msg.log(itemNetTotal + " is the line item net toatl value\n");
		itemNetBalance = Double.parseDouble(getLineItemValueOf_("text_lineItemNetBalance"));
		msg.log(itemNetBalance + " is the line item net balance value\n");

	}

	public void userVerifyDiscountApplied() {
		wait.hardWait(3);
		Double Price = Double.parseDouble(getLineItemValueOf_("text_lineItemPrice"));
		msg.log(Price + " is the line item price value\n");
		Assert.assertEquals(Price, itemPrice, msg.failForAssert(
				Price + " is the after price and " + itemPrice + " is the before price are not equals\n"));
		msg.pass("Line item price before and after discount applied is verified\n");

		Double Discount = Double.parseDouble(getLineItemValueOf_("text_lineItemDiscount"));
		msg.log(Discount + " is the discount field value\n");
		Assert.assertEquals(Discount, itemDiscount + amntDiscount, msg.failForAssert(""));
		msg.pass("Line item discount before and after discount applied is verified\n");

		Double NetTotal = Double.parseDouble(getLineItemValueOf_("text_lineItemNetTotal"));
		msg.log(NetTotal + " is the line item net toatl value\n");
		Assert.assertEquals(NetTotal, itemNetTotal - Discount, msg.failForAssert(""));
		msg.pass("Line item total before and after discount applied is verified\n");

		Double NetBalance = Double.parseDouble(getLineItemValueOf_("text_lineItemNetBalance"));
		msg.log(NetBalance + " is the line item net balance value\n");
		Assert.assertEquals(NetBalance, NetTotal, msg.failForAssert(""));
		msg.pass("Line item nettotal before and after discount applied is verified\n");
	}

}
