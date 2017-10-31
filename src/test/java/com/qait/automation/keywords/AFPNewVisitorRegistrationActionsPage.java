package com.qait.automation.keywords;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class AFPNewVisitorRegistrationActionsPage extends GeneralActionsPage {

	public AFPNewVisitorRegistrationActionsPage(WebDriver driver) {
		super(driver, "AFPNewVisitorRegistrationActionsPage");
	}

	/**
	 * workflow to fill registration form on eweb
	 * @param details - Map of individual details provided by user
	 */
	public void userFillFormDetails(Map<String, Object> details) {
		msg.log("selecting " + keyValue(details, "prefix") + " From prefix \n");
		selectProvidedTextFromDropDown(element("dropDown_prefix"), keyValue(details, "prefix"));

		enterDetails("firstName", keyValue(details, "firstName"));
		enterDetails("middleName", keyValue(details, "middleName"));
		String lastName = "" + System.currentTimeMillis();
		dataStorage.put("lastName", lastName);
		enterDetails("lastName", lastName);

		enterDetails("jobTitle", keyValue(details, "jobTitle"));

		msg.log("selecting " + keyValue(details, "titleCategory") + " From titleCategory \n");
		selectProvidedTextFromDropDown(element("dropDown_jobCategory"), keyValue(details, "titleCategory"));

		msg.log("selecting " + keyValue(details, "areaActivity") + " From areaActivity \n");
		selectProvidedTextFromDropDown(element("dropDown_principleArea"), keyValue(details, "areaActivity"));

		enterDetails("companyName", keyValue(details, "companyName"));

		msg.log("selecting " + keyValue(details, "companyType") + " From companyType \n");
		selectProvidedTextFromDropDown(element("dropDown_companyType"), keyValue(details, "companyType"));

		msg.log("selecting " + keyValue(details, "companyRevenue") + " From companyRevenue \n");
		selectProvidedTextFromDropDown(element("dropDown_companyRevenue"), keyValue(details, "companyRevenue"));

		click(element("radio_individualType", keyValue(details, "individualType")));

		msg.log("selecting " + keyValue(details, "hearAboutUs") + " From hearAboutUs \n");
		selectProvidedTextFromDropDown(element("dropDown_hearAboutUs"), keyValue(details, "hearAboutUs"));

		msg.log("selecting " + keyValue(details, "addressType") + " From addressType \n");
		selectProvidedTextFromDropDown(element("dropDown_addressType"), keyValue(details, "addressType"));

		enterDetails("mailingAddress", keyValue(details, "mailingAddress"));
		enterDetails("city", keyValue(details, "city"));
		enterDetails("zipCode", keyValue(details, "zipCode"));

		msg.log("selecting " + keyValue(details, "country") + " From country \n");
		selectProvidedTextFromDropDown(element("dropDown_country"), keyValue(details, "country"));

		if (keyValue(details, "country") != "UNITED STATES") {
			wait.waitForElementToDisappear(element("dropDown_state"));
		}

		else {
			msg.log("selecting " + keyValue(details, "state") + " From state \n");
			selectProvidedTextFromDropDown(element("dropDown_state"), keyValue(details, "state"));

		}

		enterDetails("province", keyValue(details, "province"));

		enterDetails("phone", keyValue(details, "phone"));
		enterDetails("password", keyValue(details, "password"));
		enterDetails("confirmPassword", keyValue(details, "confirmPassword"));

		msg.log(keyValue(details, "newsletters") + " category under newsletters is selected\n");
		element("chkbx_newsletters", keyValue(details, "newsletters")).click();

		userClickOn("btn_continue_cancel", "Continue");
		msg.actions("Form has been filled completely \n");
	}

	/**
	 * method to verify user on registration page
	 */
	public void verifyUserOnRegistrationPage() {
		verifyPageTitleContains();
		msg.actions("verified user on registration page\n");
	}

}
