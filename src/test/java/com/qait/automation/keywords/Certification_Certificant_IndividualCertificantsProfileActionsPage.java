package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Certification_Certificant_IndividualCertificantsProfileActionsPage extends GeneralActionsPage {

	static int page = 1;

	public Certification_Certificant_IndividualCertificantsProfileActionsPage(WebDriver driver) {
		super(driver, "Certification_Certificant_IndividualCertificantsProfileActionsPage");
	}

	public void userVerifyReplacePerformed_CertifiedFlag_Status(String certifiedFlag, String status) {
		isElementDisplayed("text_individualCertificationStatus");
		Assert.assertTrue(element("text_individualCertificationStatus").getText().contains(certifiedFlag),
				element("text_individualCertificationStatus").getText()
						+ "is the actual value of certified flag and is not replaced\n");
		msg.pass("Value of certified flag is verified\n");
		Assert.assertTrue(
				element("text_revocationDate").getText().contains(keyValue(dataStorage, "fieldValue_Iteration_1")),
				element("text_revocationDate").getText() + " actual value of revoked Date is not replaced with "
						+ keyValue(dataStorage, "fieldValue_Iteration_1") + "\n");
		Assert.assertTrue(element("text_individualStatus").getText().contains(status),
				element("text_individualStatus").getText() + "is the value of status and is not replaced with " + status
						+ " as expected\n");
		msg.pass("value of individual status is verified\n");
	}

	public void userVerifyUpdatePerformed_CertifiedFlag_Status(String certifiedFlag, String status) {
		dynamicWait(5, "text_individualCertificationStatus");
		isElementDisplayed("text_individualCertificationStatus");
		Assert.assertTrue(element("text_individualCertificationStatus").getText().contains(certifiedFlag),
				element("text_individualCertificationStatus").getText()
						+ "is the actual value of certified flag and is not replaced\n");
		msg.pass("Value of certified flag is verified\n");

		Assert.assertTrue(element("text_individualStatus").getText().contains(status),
				element("text_individualStatus").getText() + "is the value of status and is not replaced\n");
		msg.pass("value of individual status is verified\n");
	}

	public void userEditCertificantProfile() {
		userClickEditProfileButton();
		switchToFrame(element("iframe_certificant"));
		userUpdateCertificantStatus("Certified");
		userUpdateCertificantCertifiedFlag();
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
	}

	public void userClickEditProfileButton() {
		isElementDisplayed("btn_editCertificantProfile");
		element("btn_editCertificantProfile").click();
	}

	public void userUpdateCertificantStatus(String status) {
		selectProvidedTextFromDropDown(element("dropdown_editCertificant_status"), status);
	}

	public void userUpdateCertificantCertifiedFlag() {
		isElementDisplayed("chkbox_editCertificant_certifiedFlag");
		element("chkbox_editCertificant_certifiedFlag").click();

	}

	public void clickOnAddButton(String addTabHeading) {
		dynamicWait(5, element("icon_add", addTabHeading));
		isElementDisplayed("icon_add", addTabHeading);
		element("icon_add", addTabHeading).click();
		msg.actions("Clicked on add " + addTabHeading + "\n");
	}

	public void userAddCeuCredits() {
		clickOnAddButton("ceu credits");
		switchToFrame(element("iframe_certificant"));
		addCeuCredits();
		switchToDefaultContent();

	}

	public void addCeuCredits() {
		isElementDisplayed("dropdown_ceuCreditCategory");
		selectProvidedTextFromDropDown(element("dropdown_ceuCreditCategory"), "Webinars/Teleconferences");
		isElementDisplayed("dropdown_ceuCreditProduct");
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropdown_ceuCreditProduct"), "Webinars/Teleconferences-CTP");
		enterDetails("ceuCredit", "1");
		enterCeuCreditsComments();
		enterCeuCreditActivityDate();
		userClickOn("btn_saveCancel", "Save");
	}

	public void userVerifyCycleSummary_AllCeuCreditDetails() {
		boolean flag = false;
		int position = 0;
		wait.hardWait(2);
		int pageSize = elements("list_link_pageCeuDetails").size();
		int creditSize = elements("list_text_programAllCeuDetails").size();
		for (WebElement credits : elements("list_text_programAllCeuDetails")) {
			String creditPart[] = credits.getText().split("/");
			int limit = creditPart.length;
			if (("Webinars/Teleconferences ").contains(credits.getText())) {

				if (elements("list_text_activityDateAllCeuDetails").get(position).getText().contains("7/14/2016")) {
					if (elements("list_text_creditAllCeuDetails").get(position).getText().contains("1")) {
						flag = true;
						Assert.assertTrue(flag, msg.failForAssert(""));
						msg.pass("Credit value in ceu details is verified\n");
						break;
					}
				}
			}
			position++;
			System.out.println(page);
			System.out.println(pageSize);
			if (position > creditSize) {
				if (page > pageSize) {
					Assert.assertTrue(false);
				}

				elements("list_link_pageCeuDetails").get(page).click();
				page++;
				userVerifyCycleSummary_AllCeuCreditDetails();
			}
		}

	}

	public void userClick_goToCreditDetails() {
		boolean flag = false;
		int position = 0;
		for (WebElement credits : elements("list_text_creditProgramCeuCredit")) {
			if (credits.getText().contains("CTP")) {
				if (elements("list_text_ReqCeuCredit").get(position).getText().contains("WebTeleconf")) {
					if (elements("list_text_creditsCeuCredit").get(position).getText().contains("1")) {
						flag = true;
						break;
					}
				}
			}
			position++;
		}
		Assert.assertTrue(flag);
		msg.pass("Credit value in ceu credits is verified\n");
		elements("list_link_gotoCeuCreditProfile").get(position).click();

	}

	public void userEditDates_Effective_Expiry_Revoked() {
		userClickEditProfileButton();
		switchToFrame(element("iframe_certificant"));
		editRevokedDate("7/14/2016");
		editEffectiveDate("7/14/2016");
		editExpiryDate("7/14/2016");
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
	}

	public void editEffectiveDate(String Date) {
		enterDetails("effectiveDateEditProfile", Date);
	}

	public void editExpiryDate(String Date) {
		enterDetails("expiryDateEditProfile", Date);

	}

	public void editRevokedDate(String Date) {
		enterDetails("revokedDateEditProfile", Date);

	}

	public void enterCeuCreditActivityDate() {
		enterDetails("ceuCreditActivityDate", "7/14/2016");

	}

	public void enterCeuCreditsComments() {
		enterDetails("ceuCreditComments", "Activity dated 7/14/2016. Submitted for reinstatement");

	}

}
