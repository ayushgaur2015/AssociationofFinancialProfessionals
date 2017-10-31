package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Parameters;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Unit test for simple App.
 */
public class TestUserJoinAFP extends TestBase{

	@Test
	public void Step01_User_Launch_Application_And_Log_In_As_Non_Existing_User_And_Fill_Registration_Form() {
		test.launchApplicationURL(YamlReader.getData("joinUrl"));
		test.LogReq.verifyUserOnLoginPage();
		test.LogReq.userLogIntoApplicationAsNotExistingUser(YamlReader.getData("personalInformation.firstName"),
				YamlReader.getData("personalInformation.middleName"));
		test.AfpNewVisReg.userFillFormDetails(YamlReader.getYamlValues("personalInformation"));
	}

	@Test(dependsOnMethods = "Step01_User_Launch_Application_And_Log_In_As_Non_Existing_User_And_Fill_Registration_Form")
	public void Step02_User_Selects_Afp_Membership_And_Check_Invoice() {
		test.MembApp.verifyUserDirectedSuccessfullyTO_afpMembershipLinkPage();
		test.MembApp.verifyAfpMembershipLinkIsDisplayed("AFP Membership");
		test.MembApp.userClickSelectItem();
		test.OnlineStrBndls.verifyUserOnOnlineStoreBundlesPage();
		test.OnlineStrBndls.verifyUserDetailsOnOnlineStoreBundles();
	}

	@Test(dependsOnMethods = "Step02_User_Selects_Afp_Membership_And_Check_Invoice")
	public void Step03_User_Purchase_Afp_MemberShip_From_ShoppingCart() {
		test.ShoppingCart.verifyUserOnShoppingCartPage();
		test.ShoppingCart.userFinalConfirmWithAfpMembershipDetails("AFP Membership");
		test.ShoppingCart.userCheckoutAfpMembershipOrder();
		test.ShoppingCart.userEnterTransactionDetailsToCompleteAfpMembership(YamlReader.getYamlValues(YamlReader.getData("TestUserJoinAFP.paymentMethod")),YamlReader.getData("TestUserJoinAFP.paymentMethod"));
		test.ShoppingCart.userSubmitsOrderDetailsToCompleteTransaction();
		test.ShoppingCart.userVerifyTransactionReceipt();
	}
	
	@Test(dependsOnMethods = "Step03_User_Purchase_Afp_MemberShip_From_ShoppingCart")
	public void Step04_User_OpensIwebApplication(){
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}
	
	@Test(dependsOnMethods = "Step04_User_OpensIwebApplication")
	public void Step05_User_ApplyFilters_SearchIndividual(){
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals","Find Individual");
		test.CrmInd_FindInd.verifyUserIsOnFindIndividualPage();
		test.CrmInd_FindInd.userSelectSearchFieldAndStoredValue("Last Name", "lastName");
		test.CrmInd_FindInd.userClickOnButton("Go");
		test.CrmIndList.userSelectsRecord("1");
	}
	
	@Test(dependsOnMethods = "Step05_User_ApplyFilters_SearchIndividual")
	public void Step06_User_VerifyRegistrantDetails(){
		
		test.EventRegProf.verifyRegistrantProfileDetails();
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Membership", "individual memberships");
		test.CrmInd.userVerifyListItemsPresent_NotPresentInChildViewOf_(
				YamlReader.getData("TestUserJoinAFP.memberType"), "individual memberships", true);

	}

}
