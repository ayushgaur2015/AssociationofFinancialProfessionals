package com.qait.automation.keywords;

import java.util.Map;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.DateUtil;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;

public class EventRegistrantProfileActionsPage extends GeneralActionsPage {

	public EventRegistrantProfileActionsPage(WebDriver driver) {
		super(driver, "EventRegistrantProfileActionsPage");
	}

	public void selectSubstituentReigistrant() {
		String rand = generateRandomOutput(10);
		isElementDisplayed("link_query", rand);
		hoverClick(element("link_query", rand));
		msg.log("Substituent registrant has been selected\n");
	}

	public String userStoresRegistrantEvent() {
		String event;
		isElementDisplayed("link_eventName");
		event = element("link_eventName").getText();
		msg.log(event + " is the event of the registrant\n");
		return event;
	}

	public void clearSubstituentNameField() {
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		isElementDisplayed("inp_substituteName");
		element("inp_substituteName").clear();
		msg.actions("Registrant name has been cleared \n");
	}

	public void selectRandomSubstituent() {
		isElementDisplayed("icon_search_substituentName");
		click(element("icon_search_substituentName"));

		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		int userCount = 0;
		String pageCount = generateRandomOutput(10);
		String subtituentPosition = generateRandomOutput(20);
		while (element("link_queryFieldsName", subtituentPosition).getText().isEmpty()
				|| element("link_queryFieldsAddress", subtituentPosition).getText().isEmpty()) {
			subtituentPosition = generateRandomOutput(20);
			if (userCount == 10) {
				userCount = 0;
				element("link_individualListNextPage", pageCount).click();
			}
			userCount++;
		}

		element("link_query", subtituentPosition).click();
		dataStorage.put("Event Substituent name", element("inp_substituteName").getAttribute("value"));
		msg.log(element("inp_substituteName").getAttribute("value") + " Value is stored\n");
	}

	private void selectLineItems() {
		element("chkBox_selectAll").click();
		msg.actions("Line items are selected\n");
	}

	public void selectCancellationFeesORSubstituentFee() {
		int j = 0;
		dynamicWait(5, elements("list_nameCancellationFee").get(0));
		for (WebElement lineItem : elements("list_nameCancellationFee")) {
			
			if (!lineItem.getText().contains("Waived")) {
				msg.log("Fee selected " + elements("list_nameCancellationFee").get(j).getText() + "\n");
				clickUsingXpathInJavaScriptExecutor(elements("list_cancellationFee").get(j));
				j++;
			}
			msg.actions("Cancellation sbstitution fee is selected\n");
		}
	}

	public void selectCancellationFeesORSubstituent_WaivedFee() {
		int j = 0;
		for (WebElement lineItem : elements("list_nameCancellationFee")) {
			if (lineItem.getText().contains("Waived")) {
				dynamicWait(5, elements("list_nameCancellationFee").get(j));
				msg.actions("Fee selected " + elements("list_nameCancellationFee").get(j).getText() + "\n");
				clickUsingXpathInJavaScriptExecutor(elements("list_cancellationFee").get(j));
			}
			j++;
			msg.log("Cancellation sbstitution fee is selected\n");
		}
	}

	public void userSelectSubstituentFee() {
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		selectCancellationFeesORSubstituentFee("substitution");

		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));
	}

	public void selectCancellationFeesORSubstituentFee(String feeType) {
		int j = 0;
		if (feeType == "Cancel") {
			for (WebElement lineItem : elements("list_nameCancellationFee")) {
				if (!lineItem.getText().contains("Waived")) {
					msg.log("Fee selected " + elements("list_nameCancellationFee").get(j).getText() + "\n");
					clickUsingXpathInJavaScriptExecutor(elements("list_cancellationFee_SubstituentItem").get(j));

				}
				j++;
				msg.actions("Cancellation  fee is selected\n");
			}
		}
		if (feeType == "Substitution" || feeType == "substitution") {
			for (WebElement lineItem : elements("list_nameSubstitutionFee")) {
				if (!lineItem.getText().contains("Waived")) {
					msg.log("Fee selected " + elements("list_nameSubstitutionFee").get(j).getText() + "\n");
					clickUsingXpathInJavaScriptExecutor(elements("list_SubstitutionFee").get(j));

				}
				j++;
				msg.actions("Substitution fee is selected\n");
			}
		}
	}

	public void userStoreRegistrantData() {
		dynamicWait(5, "text_userInfo");
		dataStorage.put("Event Registrant Info", element("text_userInfo").getText());
		msg.log(element("text_userInfo").getText() + " \nis the event registrant info stored\n");
	}

	/**
	 * Workflow to expand the registrant action link and select item from the
	 * dropdown list
	 * 
	 * @param dropDown_link
	 *            - registrant action link
	 * @param item
	 *            - item from the dropdown link
	 */
	public void userSelectsRegistrantActionItemFromDropDown(String dropDown_link, String item) {
		dynamicWait_withReplacement(5, "dropDown_registrantActions", dropDown_link);
		clickUsingXpathInJavaScriptExecutor(element("dropDown_registrantActions", dropDown_link));
		msg.actions("user selects " + dropDown_link + " from user menu \n");
		browserSpecificHolds();
		switchToFrame(3);
		msg.actions("registrant actions dropdown is expanded\n");
		hoverClick(element("link_registrantActionsOptions", item));
		msg.actions(item + " is clicked\n");
		msg.log("user selects " + dropDown_link + " and item " + item + " is complete\n");
	}

	public void userSelectsSubstituentRegistrant() {
		clearSubstituentNameField();
		selectRandomSubstituent();
		msg.log("Substituten registrant has been selected \n");
	}

	public void selectPaymentType(String item) {
		try {

			Map<String, Object> paymentDetails = null;
			wait.waitForElementToBeVisible(element("dropDown_paymentType"));
			selectProvidedTextFromDropDown(element("dropDown_paymentType"), item);
			switchToDefaultContent();
			switchToFrame(element("frame_eventRegSubForm"));

			switch (item) {
			case "proforma":
				break;
			case "terms":
				selectProvidedTextFromDropDown(element("dropDown_invoiceTerms"),
						YamlReader.getData("paymentMethod.terms"));

				break;
			case "Visa":
				paymentDetails = YamlReader.getYamlValues("Visa");
				msg.log("Visa is the prepaidType\n");
				selectProvidedTextFromDropDown(element("dropDown_paymentMethod"), "Visa");
				waitForLoaderToDisappear();

				switchToDefaultContent();
				switchToFrame(element("frame_eventRegSubForm"));

				msg.log(keyValue(paymentDetails, "cardHolderName") + " is the cardHolder name\n");
				enterDetails("cardholderName", keyValue(paymentDetails, "cardHolderName"));
				waitForLoaderToDisappear();

				msg.log(keyValue(paymentDetails, "creditCardNumber") + " is the creditCard number\n");
				enterDetails("creditCardNumber", keyValue(paymentDetails, "creditCardNumber"));
				waitForLoaderToDisappear();

				msg.log("selecting " + keyValue(paymentDetails, "expiryDate") + " From expiryDate \n");
				selectProvidedTextFromDropDown(element("dropDown_expiryDate"), keyValue(paymentDetails, "expiryDate"));
				waitForLoaderToDisappear();

				msg.log("selecting " + keyValue(paymentDetails, "cvv") + " is the CVV no. \n");
				enterDetails("cvv", keyValue(paymentDetails, "cvv"));

				break;

			case "Check":
				paymentDetails = YamlReader.getYamlValues("Check");
				msg.log("Check is the prepaidType\n");
				selectProvidedTextFromDropDown(element("dropDown_paymentMethod"), "Check");
				switchToDefaultContent();
				switchToFrame(element("frame_eventRegSubForm"));
				waitForLoaderToDisappear();

				msg.log(keyValue(paymentDetails, "name on check") + " is the name on check\n");
				enterDetails("name on check", keyValue(paymentDetails, "name on check"));
				waitForLoaderToDisappear();

				break;

			}
			msg.log("user selects payment type " + item + " is complete\n");
		} catch (Exception e) {
			msg.log("fees is not required \n");
		}
	}

	public void userSelectsProductToSubstitute() {
		selectCancellationFeesORSubstituentFee();
		msg.actions("user selects product to substitute is complete \n");
	}

	public void userCompletesSubstituionProcess(String batch, String paymentType) {
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		selectBatch(batch);
		selectPaymentType(paymentType);
		userClickOn("btn_event_registration_actions", "Save");
		handleAlert();
		msg.actions("user completes subtitution process \n");
	}

	public void userFillDetailsAndAutoRefundCheckboxStateOnEventCancellationWizard(String autorefund) {
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));
		msg.log("Event Cancellation Wizard appeared\n");

		selectLineItems();
		browserSpecificHolds();
		if (autorefund == "true") {
			selectAutoRefund();
		}
		waitForLoaderToDisappear();
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		selectCancellationFeesORSubstituentFee();
		waitForLoaderToDisappear();
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		scrollDown(element("btn_event_registration_actions", "next"));
		clickUsingXpathInJavaScriptExecutor(element("btn_event_registration_actions", "next"), "next");
		msg.actions("user fill event cancellation details with refund type" + autorefund + "\n");
	}

	public void userFillDetails_WaivedAndAutoRefundCheckboxStateOnEventCancellationWizard(String autorefund) {
		switchToDefaultContent();
		dynamicWait(20, "frame_eventRegSubForm");
		switchToFrame(element("frame_eventRegSubForm"));
		msg.pass("Event Cancellation WIzard window is appeared\n");
		selectLineItems();
		if (autorefund == "true") {
			selectAutoRefund();
		}
		waitForLoaderToDisappear();
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		selectCancellationFeesORSubstituent_WaivedFee();
		waitForLoaderToDisappear();
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		scrollDown(element("btn_event_registration_actions", "next"));
		clickUsingXpathInJavaScriptExecutor(element("btn_event_registration_actions", "next"), "next");
		msg.actions("next button is clicked\n");
		msg.log("user fill event cancellation details with refund type" + autorefund + "\n");
	}

	private void selectAutoRefund() {
		dynamicWait(5, "chkBox_autoRefund");
		isElementDisplayed("chkBox_autoRefund");
		element("chkBox_autoRefund").click();
		msg.actions("Autorefund checkbox is selected\n");
	}

	public void userSelectsBatchAndPaymentType(String batch, String paymentType) {
		switchToDefaultContent();
		switchToFrame(element("frame_eventRegSubForm"));

		selectBatch(batch);
		waitForLoaderToDisappear();
		selectPaymentType(paymentType);
		waitForLoaderToDisappear();
		userClickOn("btn_event_registration_actions", "Save");
		msg.actions("user select batch type and payment type is complete \n");
	}

	public void verifyUserOnEventRegistrationProfilePage() {
		verifyPageTitleContains();
		msg.actions("verified user on event registrant profile page\n");

	}

	public void verifyRegistrantProfileDetails() {
		Assert.assertTrue(element("text_profileInfo").getText().contains(dataStorage.get("lastName").toString()),
				msg.failForAssert("Correct user is not selected. Actual " + element("text_profileInfo").getText()
						+ "expected substring" + dataStorage.get("lastName").toString() + "\n"));
		msg.pass("Correct user is selected\n");
		msg.actions("verified registrant profile details\n");
	}

	public void userOpensRegistrantProfile() {
		waitForLoaderToDisappear();
		clickUsingXpathInJavaScriptExecutor(element("link_userName"));
		msg.actions("username is clicked\n");
		waitForLoaderToDisappear();
	}

	public void userNavigatesToCustomerProfile() {
		switchToDefaultContent();
		isElementDisplayed("btn_edit");
		element("btn_edit").click();
		switchToFrame(element("frame_eventRegSubForm"));
		isElementDisplayed("text_onEdit_userDisplayName");
		String userName = element("text_onEdit_userDisplayName").getAttribute("value");
		userClickOn("btn_event_registration_actions", "Save");
		switchToDefaultContent();
		browserSpecificHolds();
		waitForLoaderToDisappear();
		isElementDisplayed("link_userName", userName);
		clickUsingXpathInJavaScriptExecutor(element("link_userName", userName), userName);
		msg.actions("user navigates to customer profile completes\n");
	}

	public void userClickOnGotoLinkForRequired_Invoice_Batch() {
		isElementDisplayed("link_gotoBatchInvoiceRefund");
		element("link_gotoBatchInvoiceRefund").click();
		msg.actions("user click on goto link gor required invoice batch is complete\n");
	}

	public void userCompletesTransaction_andClickOn_(String Batch, String PaymentType, String operation) {
		waitForLoaderToDisappear();
		boolean flag = false;
		clickUsingXpathInJavaScriptExecutor(element("dropDown_batch"));
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
			selectPaymentType(PaymentType);
			switchToDefaultContent();
			msg.log("Switched to default content\n");
			switchToFrame(element("frame_eventRegSubForm"));
			msg.log("Switched to frame content\n");

			scrollDown(element("btn_event_registration_actions", operation));
			userClickOn("btn_event_registration_actions", operation);
			msg.actions("btn_event_registration_actions done \n");
		}
	}

	public void userCompletesSubstitutionTransaction_andClickOn_(String Batch, String PaymentType, String PaymentMethod,
			String operation) {
		waitForLoaderToDisappear();
		boolean flag = false;
		clickUsingXpathInJavaScriptExecutor(element("dropDown_batch"));
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

			switchToDefaultContent();
			switchToFrame(element("frame_eventRegSubForm"));

			selectPaymentType(PaymentType, PaymentMethod);
			switchToDefaultContent();
			switchToFrame(element("frame_eventRegSubForm"));

			scrollDown(element("btn_event_registration_actions", operation));
			userClickOn("btn_event_registration_actions", operation);
			handleAlert();
			msg.actions("btn_event_registration_actions done \n");
		}
	}

	public void userVerifyCancellationDateIsDisplayed() {
		isElementDisplayed("text_cancellationDate");
		Assert.assertTrue(
				element("text_cancellationDate").getText()
						.contains(DateUtil.getCurrentdateInStringWithGivenFormate("M/d/yyyy")),
				msg.failForAssert(element("text_cancellationDate").getText()
						+ " is not the actul cancellation date or event is not cancelled properly\n"));
	}
}
