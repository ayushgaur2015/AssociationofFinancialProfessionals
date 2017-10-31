package com.qait.automation.keywords;

import org.omg.CORBA.Current;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.DateUtil;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;

public class CRM_IndividualActionsPage extends GeneralActionsPage {

	static int pageLink = 1;

	public CRM_IndividualActionsPage(WebDriver driver) {
		super(driver, "CRM_IndividualActionsPage");
	}

	/**
	 * method to select options from user profile menu list
	 * 
	 * @param menuOption
	 *            - profile menu options eg: [invoice]
	 */
	public void userSelectsOptionFromUserProfileMenu(String menuOption) {
		dynamicWait_withReplacement(3, "link_menu", menuOption);
		click(element("link_menu", menuOption));
		msg.actions("'" + menuOption + "' menu option is selected \n");
	}

	/**
	 * method to verify the registrant details based on the last name of the
	 * individual
	 */
	public void verifyRegistrantProfileDetails() {
		Assert.assertTrue(element("text_profileInfo").getText().contains(dataStorage.get("lastName").toString()),
				msg.failForAssert("Correct user is not selected. Actual " + element("text_profileInfo").getText()
						+ "and Expected " + dataStorage.get("lastName").toString() + "\n"));
		msg.pass("Correct user is selected\n");
		msg.actions("Verified registrant profile details\n");
	}

	/**
	 * method to store customer first name
	 */
	public void userStoresCustomerInformation() {
		String[] userProfileData = element("text_profileInfo").getText().split("\n");
		String[] userDesgFirstLastOrgName = userProfileData[0].split(" ");
		dataStorage.put("firstName", userDesgFirstLastOrgName[1]);
		msg.actions("User stores customer information completes \n");

	}

	/**
	 * method to store customer mailing address
	 */
	public void userStoresCustomerMailingLabel() {
		String userProfileData = element("text_profileInfo").getText();
		dataStorage.put("mailingLabel", userProfileData);
		msg.log(userProfileData + " is the customer mailing label  \n");
	}

	/**
	 * workflow to verify either list item present or not present in the child
	 * view
	 * 
	 * @param listItem
	 *            - is the item to be check whether present or not
	 * @param childItem
	 *            - is the view to be checked
	 * @param present
	 *            - [true] for present; [false] for not present
	 */
	public void userVerifyListItemsPresent_NotPresentInChildViewOf_(String listItem, String childItem,
			boolean present) {
		wait.waitForPageToLoadCompletely();
		verifyListItemsPresent_NotPresentInChildViewOf_("list_childView", childItem, listItem, present);
		msg.actions(
				"verified list of items " + listItem + " present in child view of " + childItem + ":" + present + "\n");
	}

	/**
	 * method to verify either list item present or not present in the child
	 * view
	 * 
	 * @param element
	 *            - the UI locator for which the child view need to be checked
	 * @param childItem
	 *            - is the view to be checked
	 * @param listItem
	 *            - is the view to be checked
	 * @param present
	 *            - [true] for present; [false] for not present
	 */
	public void verifyListItemsPresent_NotPresentInChildViewOf_(String element, String childItem, String listItem,
			boolean present) {
		boolean flag = !present;
		for (WebElement item : elements(element, childItem)) {
			flag = !present;
			if (item.getText().contains(listItem)) {
				flag = present;
				break;

			}
		}
		if (present) {
			Assert.assertEquals(flag, present, msg.failForAssert("Result list item present in child view " + flag));
			msg.pass(listItem + " displayed result is " + present + "\n");
		}
		if (!present) {
			Assert.assertNotEquals(flag, present, msg.failForAssert("list item present in child view " + flag));
			msg.pass(listItem + " displayed result is " + present + "\n");
		}

	}

	/**
	 * method to verify user is not a member
	 */
	public void verifyUserNotMember() {
		Assert.assertTrue(isElementDisplayed("img_nonMember"), msg.failForAssert("User is a memeber"));
		msg.pass("User is not a memeber\n");
	}

	/**
	 * method to verify user is a member
	 */
	public void verifyUserIsMember() {
		Assert.assertTrue(isElementDisplayed("img_isMember"), msg.failForAssert("User is not a memeber"));
		msg.pass("User is a memeber\n");
	}

	/**
	 * method to click on the goto link for the required product
	 * 
	 * @param bar
	 *            - bar heading to locate element heading
	 * @param match
	 *            - reference key for picking up the goto link
	 */
	public void userClickOnGotoLinkForRequiredProduct(String bar, String match) {
		switch (match) {
		case "Check":
			match = keyValue(dataStorage, "checkNumber");
			break;
		}
		isElementDisplayed("list_gotoLink" + bar, match);
		element("list_gotoLink" + bar, match).click();
		msg.actions("User click on goto link for required product completes\n");
	}

	/**
	 * workflow to verify refund details which include event cancellation and
	 * credits
	 * 
	 * @param event
	 *            - name of the event to be checked whether present or not
	 */
	public void userVerifyRefundDetailsOnIndividualProfile(String event) {
		switchToDefaultContent();
		waitForLoaderToDisappear();
		userOpenCustomerProfile_MenuOptionAndSubItem("Events", "cancellations");
		userVerifyListItemsPresent_NotPresentInChildViewOf_(event, "cancellations", true);
		userOpenCustomerProfile_MenuOptionAndSubItem("Other Actg", "credits");
		userVerifyListItemsPresent_NotPresentInChildViewOf_(YamlReader.getData("Batch"), "credits", true);
	}

	/**
	 * workflow to verify substitution of event by checking whether the
	 * substituted event present or not
	 * 
	 * @param event
	 *            - name of the event to be checked whether present or not
	 */
	public void userVerifySubstitutionOfEventOnIndividualProfile(String event) {
		switchToDefaultContent();
		waitForLoaderToDisappear();
		userOpenCustomerProfile_MenuOptionAndSubItem("Events", "substitutions");
		userVerifyListItemsPresent_NotPresentInChildViewOf_(event, "substitutions", true);
	}

	/**
	 * method to verify whether the source type of user is present
	 */
	public void verifyUserSourceType() {
		Assert.assertTrue(isElementDisplayed("text_sourceType"), msg.failForAssert("Source type not displayed\n"));
		msg.log(element("text_sourceType").getText() + " is the source type of customer\n");
		msg.actions("Verified user source tpe\n");
	}

	/**
	 * method to verify that the individual title is replaced
	 */
	public void userVerifyIndividualTitleReplaced(String replacedString) {
		Assert.assertFalse(element("text_profileTitle").getText().contains(replacedString),
				msg.failForAssert("Actual " + element("text_profileTitle").getText() + " and Expected " + replacedString
						+ " .Individual designation is not replaced\n"));
		msg.pass("Verified individual title is repalced\n");
	}

	/**
	 * method to verify individual title replaced
	 * 
	 * @param value
	 *            - value displayed after replacment
	 * @param valueDisplayed
	 *            - replaced value displayed or not
	 */
	public void userVerifyIndividualTitleReplaced(String value, boolean valueDisplayed) {
		wait.hardWait(2);
		if (valueDisplayed) {
			Assert.assertTrue(element("text_indProfileTitle").getText().contains(value),
					msg.failForAssert("Individual designation is not replaced\n"));

		} else {
			Assert.assertFalse(element("text_indProfileTitle").getText().contains(value),
					msg.failForAssert("Individual designation is not replaced\n"));

		}
		msg.pass("Individual Designation is replaced\n");
	}

	/**
	 * method to edit individual organization by cliking edit button
	 */
	public void clickOrganizationEditButton() {
		dynamicWait(3, "list_icon_editOrganizationRelations");
		isElementDisplayed("list_icon_editOrganizationRelations");
		element("list_icon_editOrganizationRelations").click();
		msg.actions("Clicked on organization edit button\n");
	}

	/**
	 * method to edit and store end date of current relation
	 */
	public void editEndDate() {
		dataStorage.put("OrgEndDate", DateUtil.getAnyDateForType("M/d/yyyy", 1, "month"));
		enterDetails("endDateOrgRel", DateUtil.getAnyDateForType("M/d/yyyy", 1, "month"));
	}

	/**
	 * workflow to remove primary location check of individual current
	 * organization
	 */
	public void userRemovesPrimaryLocationCheckForCurrentOrganization() {
		clickOrganizationEditButton();
		switchToFrame(element("frame_individualProfile"));
		if (element("chkbox_primaryLocEditOrganizationRel").getAttribute("checked").contains("true")) {
			element("chkbox_primaryLocEditOrganizationRel").click();
			msg.log("Checkbox is unchecked\n");
		} else {
			msg.log("Checkbox is already unchecked\n");
		}
		wait.hardWait(3);
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
	}

	/**
	 * method to verify that the primary location check is removed
	 */
	public void userVerifyPrimaryLocationCheckIsRemoved() {
		wait.hardWait(2);
		Assert.assertFalse(checkIfElementNotDisplayed("list_img_primaryLocationRelations"),
				msg.failForAssert("Primary location check is not removed\n"));
		msg.pass("primary location check is removed\n");
	}

	/**
	 * method to delete the relation with current organization
	 * 
	 * @return String organization name
	 */
	public String userDeleteRelationWithCurrentOrganization() {
		dynamicWait(2, "list_text_organizationNameRelations");
		String orgName = element("list_text_organizationNameRelations").getText();
		element("list_icon_delOrganizationRelations").click();
		msg.actions("clicked on delete button\n");
		handleAlert();
		waitForLoaderToDisappear();
		dynamicWait(2, "list_text_organizationNameRelations");
		Assert.assertFalse(checkIfElementNotDisplayed("list_text_organizationNameRelations"),
				msg.failForAssert("Current organization name is not deleted\n"));
		msg.pass("Relation with current organization gets deleted\n");
		return orgName;
	}

	/**
	 * method to verify that the organization name removed from relation is
	 * displayed in the history at individual profile
	 * 
	 * @param Organization
	 *            - name of the organization to be verified
	 */
	public void userVerifiesRelationRemovedAndDispalyedInHistory(String Organization) {
		boolean flag = false;
		for (WebElement OrgHis : elements("list_text_name_histOrgRelLogs")) {
			if (OrgHis.getText().contains(Organization)) {
				flag = true;
				break;
			}

		}
		Assert.assertTrue(flag, msg.failForAssert(Organization + " is not displayed in historical record\n"));
		msg.pass("Organization is displayed in historical relations\n");
	}

	/**
	 * method to  click on button on individual profile
	 * @param btnName - user required button to be clicked
	 */
	public void clickIndividualProfileButton(String btnName){
		isElementDisplayed("btn_indProfile",btnName);
		element("btn_indProfile",btnName).click();
		msg.log(btnName+" button is clicked\n");
	}
	
	
	/**
	 * workflow user add relation with new organization by selecting random
	 * organization
	 * 
	 * @param organization
	 *            - name of the organization not be selected
	 */
	public void userAddRelationWithNewOrganization(String organization) {
		clickIndividualProfileButton("Edit Name & Address");
		msg.actions("Edit Name & Address profile button is clicked\n");
		switchToFrame(element("frame_individualProfile"));
		msg.log("Edit Name and Adress wizard appeared\n");
		isElementDisplayed("icon_searchOrgEditNameAdd");
		element("icon_searchOrgEditNameAdd").click();
		msg.log("icon search organizatrion is clicked\n");
		userSelectsRandomRecordFromTheExistingList_NotContainingRecord(organization);
		switchToDefaultContent();
	}

	/**
	 * method to select random record from the list except the already removed
	 * name
	 * 
	 * @param orgName
	 *            - name of the organization not to be selected
	 */
	public void userSelectsRandomRecordFromTheExistingList_NotContainingRecord(String orgName) {
		boolean flag = false;
		int size = 0;
		switchToDefaultContent();
		switchToFrame(element("frame_individualProfile"));
		int pageLimit = elements("list_linkpageTabExcpNext").size();
		int recordLimit = elements("list_organizationRow").size() - 1;
		String pageNo = generateRandomUniqueOutput((pageLimit - 1), 2);
		msg.log(pageNo + " is selected as random Page number\n");
		int individualRow = 0;
		isElementDisplayed("link_pageTabExcptNext", pageNo);
		element("link_pageTabExcptNext", pageNo).click();
		while (size < recordLimit) {
			individualRow = Integer.parseInt(generateRandomUniqueOutput(recordLimit - 2, 3));
			msg.log(individualRow + " is selected as random Row\n");
			if (!elements("list_organizationRow").get(individualRow).getText().contains(orgName)) {
				flag = true;
				break;
			}
			size++;
		}
		if (flag) {
			msg.log(element("list_organizationRow", individualRow).getText()
					+ " user selected as random individual  \n");
			elements("list_link_gotoOrganization").get(individualRow).click();
		}

		if (!flag) {
			userSelectsRandomRecordFromTheExistingList_NotContainingRecord(orgName);
		}

		msg.actions("User selected random record from existing list");

	}

	/**
	 * workflow to edit individual organization title
	 * 
	 * @param title
	 *            - organization title name to be kept for individual profile
	 */
	public void userEditTitle(String title) {
		switchToFrame(element("frame_individualProfile"));
		enterDetails("titleOrgEditNameAdd", title);
		msg.log(element("inp_orgName").getAttribute("value") + " is the respective organization display name\n");
		dataStorage.put("organizationName", element("inp_orgName").getAttribute("value"));
		userClickOn("btn_saveCancel", "Save");
		msg.actions("clicked on Save button\n");
		switchToDefaultContent();
	}

	/**
	 * workflow to edit end date of current relation
	 */
	public void userEditEndDateOfCurrentRelation() {
		clickOrganizationEditButton();
		switchToFrame(element("frame_individualProfile"));
		msg.log("Add Affiliation Wizard is expanded\n");
		editEndDate();
		clickUsingXpathInJavaScriptExecutor(element("btn_saveCancel", "Save"));
		msg.log("clicked on Save button\n");
		switchToDefaultContent();

	}

	/**
	 * to verify that the title of the organization in relation view is updated
	 * 
	 * @param title
	 *            - name of the organization title to be verified
	 */
	public void verifyTitleOfOrganizationInRelationUpdated(String title) {
		isElementDisplayed("list_text_jobTitleRelations");
		Assert.assertTrue(element("list_text_jobTitleRelations").getText().contains(title),
				msg.failForAssert("Actual " + element("list_text_jobTitleRelations").getText() + " and Expected "
						+ title + " .Title name is not verified\n"));
		msg.pass("Title name is verified\n");
	}

	/**
	 * method to verify name of organization in relation is updated
	 */
	public void verifyNameOfOrganizationInRelationUpdated() {
		dynamicWait(3, "list_text_organizationNameRelations");
		isElementDisplayed("list_text_organizationNameRelations");
		Assert.assertTrue(
				element("list_text_organizationNameRelations").getText()
						.contains(keyValue(dataStorage, "organizationName")),
				msg.failForAssert("organization name is not verified\n"));
		msg.pass("Organization name is verified\n");
	}

	/**
	 * method to verify end date of organization in relation is updated
	 */
	public void verifyEndDateOfOrganizationInRelationUpdated() {
		waitForLoaderToDisappear();
		dynamicWait(5, "list_text_endDate");
		isElementDisplayed("list_text_endDate");
		Assert.assertTrue(element("list_text_endDate").getText().contains(keyValue(dataStorage, "OrgEndDate")),
				msg.failForAssert(element("list_text_endDate").getText() + " and " + keyValue(dataStorage, "OrgEndDate")
						+ " Date is not verified\n"));
		msg.pass("End date is verified\n");
	}

	/**
	 * method to verify organization details are updated
	 * 
	 * @param title
	 *            - title of the organization for which the details need to be
	 *            verified
	 */
	public void userVerifyOrganizationDetailsUpdated(String title) {
		waitForLoaderToDisappear();
		verifyNameOfOrganizationInRelationUpdated();
		verifyTitleOfOrganizationInRelationUpdated(title);

	}

	/**
	 * workflow to add a new ceu credit product on individual profile page
	 * 
	 * @param ceuCreditCategory
	 *            - ceu credit category type from the drop down for a new credit
	 * @param ceuCreditProductType
	 *            - ceu credit product category from the drop down for a new
	 *            credit
	 * @param ceuCreditDate
	 *            - ceu crdedit activity date for a new credit
	 * @param ceuCreditComments
	 *            - ceu credit comments for creating a new credit
	 */
	public void userAddNewCEU_creditProduct(String ceuCreditCategory, String ceuCreditProductType, String creditAmount,
			String ceuCreditDate, String ceuCreditComments) {
		clickOnAddButton("ceu credits");
		switchToFrame(element("frame_individualProfile"));
		selectProvidedTextFromDropDown(element("dropdown_categoryAddCeuCredit"), ceuCreditCategory);
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropdown_productAddCeuCredit"), ceuCreditProductType);
		dataStorage.put("Individual_CEU_Credit_Amnt", creditAmount);
		wait.hardWait(3);
		enterDetails("creditAddCeuCredit", creditAmount);
		dataStorage.put("Individual_CEU_Credit_Cmnts", ceuCreditComments);
		enterDetails("commentsAddCeuCredit", ceuCreditComments);
		dataStorage.put("Individual_CEU_Credit_Date", ceuCreditDate);
		enterDetails("activityDateAddCeuCredit", ceuCreditDate);
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
	}

	/**
	 * method to verify that the ceu credit product is added
	 * 
	 * @param amnt
	 *            - stored amount value for the ceu credit
	 * @return Boolean true in case the ceu credit is added
	 */
	public boolean userVerifyCeuCreditProductAdded(String amnt) {
		int creditCountPerPage = 1;
		boolean flag = false;
		String amount = keyValue(dataStorage, amnt);
		dynamicWait(5, elements("list_creditsAmountCeuCredit").get(0));
		for (WebElement creditAmnt : elements("list_creditsAmountCeuCredit")) {
			if (creditAmnt.getText().trim().equals(amount)) {
				flag = true;
				return flag;

			}
			if (pageLink >= elements("list_link_page", "ceu credits").size()) {
				return false;
			}
			if (creditCountPerPage >= elements("list_creditsAmountCeuCredit").size()) {
				pageLink++;
				elements("list_link_page", "ceu credits").get(pageLink).click();
				return userVerifyCeuCreditProductAdded(amnt);
			}
			creditCountPerPage++;
		}
		Assert.assertTrue(flag, msg.failForAssert("ceu credit product is not added\n"));
		msg.pass("ceu credit product is added\n");
		return true;
	}

	/**
	 * method to navigate to the ceu credit profile
	 * 
	 * @param amnt
	 *            - stored amount value for the ceu credit
	 * @return Boolean true in case the clicked on goto link ceu credit
	 */
	public boolean userNavigatesCeuCreditProfile(String amnt) {
		int creditCountPerPage = 0;
		boolean flag = false;
		String amount = keyValue(dataStorage, amnt);
		dynamicWait(3, elements("list_creditsAmountCeuCredit").get(0));
		for (WebElement creditAmnt : elements("list_creditsAmountCeuCredit")) {
			if (creditAmnt.getText().trim().equals(amount)) {
				elements("list_link_gotoCeuCreditProfile").get(creditCountPerPage).click();
				return true;
			}
			if (pageLink >= elements("list_link_page", "ceu credits").size()) {
				return false;
			}
			if (creditCountPerPage >= elements("list_creditsAmountCeuCredit").size()) {
				pageLink++;
				elements("list_link_page", "ceu credits").get(pageLink).click();
				flag = userVerifyCeuCreditProductAdded(amnt);
			}
			creditCountPerPage++;
		}
		Assert.assertTrue(flag, msg.failForAssert("ceu credit product is not added\n"));
		msg.pass("Navigating to ceu credit profile\n");
		return true;
	}
	
	/**
	 * workflow to fetch individual email di
	 * @return individual email id
	 */
	public String getIndividualEmailId(){
		dynamicWait(3, "frame_individualProfile");
		isElementDisplayed("frame_individualProfile");
		switchToFrame(element("frame_individualProfile"));
		msg.log("edit contact & info wizard is opened\n");
		String email = element("inp_emailEditContactInfo").getAttribute("value");
		msg.log(email+" is the email id of the individual\n");
		click(element("btn_saveCancel","Cancel"));
		msg.actions("clicked on cancel button\n");
		switchToDefaultContent();
		return email;
		
	}
	
	/**
	 * workflow to enter individual new password
	 * @param password - user entered new password
	 */
	public void userCreatesNewPassword(String password){
		dynamicWait(3, "frame_individualProfile");
		isElementDisplayed("frame_individualProfile");
		switchToFrame(element("frame_individualProfile"));
		msg.log("Web login wizard appears successfully\n");
		enterDetails("newPasswordWebLogin", password);
		enterDetails("confirmPasswordWebLogin", password);
		click(element("btn_saveCancel","Save"));
		switchToDefaultContent();	
	}
	
	public void userVerifiesMembershipRenewed(){
		isElementsDisplayed("text_rejoinDateIndividualMembership");
		Assert.assertTrue(elements("text_rejoinDateIndividualMembership").get(2).getText().contentEquals("08/27/2015"), msg.failForAssert("Membership not renewed\n"));
		msg.pass("Membership successfully renewed\n");
	}
}
