package com.qait.automation.TestAssociationFinancialProfessional;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

public class TestUserRegisterEventInAFP extends TestBase{
	String paymentType;
	
	@Test
	public void Step01_User_Register_Into_Event_As_Non_Existing_User(){
		test.launchApplicationURL(YamlReader.getData("registerUrl"));
		test.LogReq.verifyUserOnLoginPage();
		test.LogReq.userLogIntoApplicationAsNotExistingUser(YamlReader.getData("personalInformation.firstName"),
				YamlReader.getData("personalInformation.middleName"));
		test.AfpNewVisReg.userFillFormDetails(YamlReader.getYamlValues("personalInformation"));

	}
	
	@Test(dependsOnMethods = "Step01_User_Register_Into_Event_As_Non_Existing_User")
	public void Step02_User_Adds_The_Event_In_Shopping_Cart(){
		test.EventReg.verifyCorrectUserNameIsDisplayed();
		test.EventReg.verifyCorrectProductNameIsDisplayed(YamlReader.getData("TestUserRegisterEventInAFP.event"));
		test.EventReg.userAddEventToTheCart();
	}
	
	@Test(dependsOnMethods = "Step02_User_Adds_The_Event_In_Shopping_Cart")
	public void Step03_User_Purchase_Event_Registration_From_Shopping_Cart() {
		test.ShoppingCart.verifyUserOnShoppingCartPage();
		test.ShoppingCart.userCheckoutAfpMembershipOrder();
		test.ShoppingCart.userEnterTransactionDetailsToCompleteAfpMembership(YamlReader.getYamlValues(YamlReader.getData("TestUserRegisterEventInAFP.paymentMethod")),YamlReader.getData("TestUserRegisterEventInAFP.paymentMethod"));
		test.ShoppingCart.userSubmitsOrderDetailsToCompleteTransaction();
		test.ShoppingCart.userVerifyTransactionReceipt();
	}
	
	@Test(dependsOnMethods = "Step03_User_Purchase_Event_Registration_From_Shopping_Cart")
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
		test.CrmInd.verifyRegistrantProfileDetails();
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Events","registrations");
		test.CrmInd.userVerifyListItemsPresent_NotPresentInChildViewOf_(
				YamlReader.getData("TestUserRegisterEventInAFP.eventType"), "registrations", true);

	}
	
	
}
