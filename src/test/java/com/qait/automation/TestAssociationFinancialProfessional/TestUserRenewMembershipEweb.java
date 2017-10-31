package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestUserRenewMembershipEweb extends TestBase{

	String indEmail=null;
	String newPassword = "Test_1";
	
	@Test
	public void Step01_UserLaunchApplicationCRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}
	
	@Test(dependsOnMethods = "Step01_UserLaunchApplicationCRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserRenewMembershipEweb"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();
		
	}
	
	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_getIndividualEmailId_generateNewPassword() {
		test.CrmInd.clickIndividualProfileButton("edit contact info");
		indEmail = test.CrmInd.getIndividualEmailId();
		test.CrmInd.userSelectsOptionFromUserProfileMenu("Web Login");
		test.CrmInd.userCreatesNewPassword(newPassword);
	}
	
	@Test(dependsOnMethods = "Step03_User_getIndividualEmailId_generateNewPassword")
	public void Step04_individualResetPasswordEweb() {
		test.launchApplication(YamlReader.getData("ewebUrl"));
		test.homePageEweb.userSelectOptionFromLeftPanel("Renew My Membership");
		test.LogReq.userLogIntoApplicationAsExistingUser(indEmail,newPassword);	
		test.changePasswd_eweb.changePassword(newPassword);
		test.homePageEweb.userSelectOptionFromLeftPanel("Logout");
	}
	
	@Test(dependsOnMethods = "Step04_individualResetPasswordEweb")
	public void Step05_individualPurchaseMembershipEweb(){
		test.launchApplication(YamlReader.getData("ewebUrl"));
		test.homePageEweb.userSelectOptionFromLeftPanel("Renew My Membership");
		test.LogReq.userLogIntoApplicationAsExistingUser(indEmail,newPassword);	
		test.renewMembership_eweb.customerClickRenew();
		test.MembApp.clickOnButton_("Save & Finish");
		test.ShoppingCart.verifyUserOnShoppingCartPage();
		test.ShoppingCart.userCheckoutAfpMembershipOrder();
		test.ShoppingCart.userEnterTransactionDetailsToCompleteAfpMembership(YamlReader.getYamlValues(YamlReader.getData("TestUserRegisterEventInAFP.paymentMethod")),YamlReader.getData("TestUserRegisterEventInAFP.paymentMethod"));
		test.ShoppingCart.userSubmitsOrderDetailsToCompleteTransaction();
		test.ShoppingCart.userVerifyTransactionReceipt();
	}

	@Test(dependsOnMethods = "Step05_individualPurchaseMembershipEweb")
	public void Step06_User_verifyMembershipRenewedIweb(){
		test.launchApplication(YamlReader.getData("iwebUrl"));
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Find Individual");
		test.CrmInd_FindInd.userSelectSearchFieldAndEnterValue("E-Mail Address", indEmail);
		test.CrmInd_FindInd.userClickOnButton("Go");
		test.CrmIndList.userSelectsRecord("1");
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Membership", "individual memberships");
		test.CrmInd.userVerifiesMembershipRenewed();
	}

	

	
	
}
