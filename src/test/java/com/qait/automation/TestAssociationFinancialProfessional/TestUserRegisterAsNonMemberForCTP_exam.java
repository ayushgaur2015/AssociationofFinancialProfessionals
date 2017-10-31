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

import com.jcabi.aspects.Loggable;
import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

public class TestUserRegisterAsNonMemberForCTP_exam extends TestBase{
	String ctpProduct;

	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserRegisterAsNonMemberForCTP_exam"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_SelectsTheRequiredResults_AndOpenInvoice() {
		test.CrmInd.verifyUserNotMember();
		test.CrmInd.userSelectsOptionFromUserProfileMenu("invoice");
		test.CentOrderEnt.userStoresCustomerInformation();
		test.CentOrderEnt.userSelectsItemFromDropDown("Select Product", "bundles");
	}

	@Test(dependsOnMethods = "Step03_User_SelectsTheRequiredResults_AndOpenInvoice")
	public void Step04_User_PurchaseBundleProduct() {
		test.bundles.userSwitchToBundlesWindow();
		ctpProduct = test.bundles.userSelectCTP_BundleProduct("CTP Exam");
	}

	@Test(dependsOnMethods = "Step04_User_PurchaseBundleProduct")
	public void Step05_User_CompletesTransaction() {
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userVerifyCustomerInformation();
		test.CentOrderEnt.verifyLineItemInOrderEntry(ctpProduct);
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestUserPerformsRecertificationPayment.paymentType"),
				YamlReader.getData("TestUserPerformsRecertificationPayment.paymentMethod"), "Save");
		test.CrmInd.verifyUserNotMember();
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Invoices", "invoices (open batch)");
		test.CrmInd.userClickOnGotoLinkForRequiredProduct("Invoices",YamlReader.getData("Batch"));
		test.CrmInvProf.verifyUserOnInvoiceProfile();
		test.CrmInvProf.userOpenCustomerProfile_MenuOptionAndSubItem("Details", "line items");
		test.CrmInvProf.userVerifyRegistrationForExam(ctpProduct);
	}

}
