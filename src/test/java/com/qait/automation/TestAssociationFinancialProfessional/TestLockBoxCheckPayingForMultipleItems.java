package com.qait.automation.TestAssociationFinancialProfessional;


import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestLockBoxCheckPayingForMultipleItems extends TestBase{
	String lockbox_Product;
	String userName;

	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestLockBoxCheckPayingForMultipleItems"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_NavigateToMerchandise() {
		test.CrmInd.userStoresCustomerMailingLabel();
		test.CrmInd.userSelectsOptionFromUserProfileMenu("invoice");
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		userName = test.CentOrderEnt.userStoresCustomerInformation();
		test.CentOrderEnt.userSelectsItemFromDropDown("Select Product", "bundles");
	}

	@Test(dependsOnMethods = "Step03_User_NavigateToMerchandise")
	public void Step04_User_PurchaseBundleProduct() {
		test.bundles.userSwitchToBundlesWindow();
		lockbox_Product = test.bundles.userSelectAFP_BundleProduct(
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.bundleProduct"));
	}

	@Test(dependsOnMethods = "Step04_User_PurchaseBundleProduct")
	public void Step05_User_CompletesTransaction() {
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userVerifyCustomerInformation();
		test.CentOrderEnt.verifyLineItemInOrderEntry(lockbox_Product);
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.paymentType"),
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.paymentMethod"),2, "Save");
	}

	@Test(dependsOnMethods = "Step05_User_CompletesTransaction")
	public void Step06_User_VerifiesTransaction() {
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Payments", "payments (open batch)");
		test.CrmInd.userClickOnGotoLinkForRequiredProduct("Payments",
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.paymentMethod"));
		test.CrmPaymentProf.verifyUserOnPaymentProfile();
		test.CrmPaymentProf.userOpenCustomerProfile_MenuOptionAndSubItem("Details", "payment details");
		test.CrmPaymentProf.userVerifyPaymentForProduct("payment_details",lockbox_Product);
		test.CrmPaymentProf.userVerifyPaymentForProduct("payment_details",userName);

	}

	@Test(dependsOnMethods = "Step06_User_VerifiesTransaction")
	public void Step07_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestLockBoxCheckPayingForMultipleItems"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList_NotContainingRecord(userName);
	}

	@Test(dependsOnMethods = "Step07_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step08_User_NavigateToMerchandise() {
		test.CrmInd.verifyUserNotMember();
		test.CrmInd.userSelectsOptionFromUserProfileMenu("invoice");
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userStoresCustomerInformation();
		test.CentOrderEnt.userSelectsItemFromDropDown("Select Product", "bundles");
	}

	@Test(dependsOnMethods = "Step08_User_NavigateToMerchandise")
	public void Step09_User_PurchaseBundleProduct() {
		test.bundles.userSwitchToBundlesWindow();
		lockbox_Product = test.bundles.userSelectAFP_BundleProduct(
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.bundleProduct"));
	}

	@Test(dependsOnMethods = "Step09_User_PurchaseBundleProduct")
	public void Step10_User_CompletesTransaction() {
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userVerifyCustomerInformation();
		test.CentOrderEnt.verifyLineItemInOrderEntry(lockbox_Product);
		test.CentOrderEnt.userChangeBillTo_Customer(userName);
		test.CentOrderEnt.userClickOnSearchIcon("searchBillToName");
		test.CrmIndList.userSelectRecord_BillTo_UsingMailingLabel();
		test.CentOrderEnt.userApplyCreditOfBillToCustomer();
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.II-paymentType"),
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.II-paymentMethod"), "Save");

	}
	
	@Test(dependsOnMethods = "Step10_User_CompletesTransaction")
	public void Step11_User_verifiesTransaction() {
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Invoices", "invoices (open batch)");
		test.CrmInd.userClickOnGotoLinkForRequiredProduct("Invoices",YamlReader.getData("Batch"));
		test.CrmInvProf.userVerifyInvoiceMailingAddress();

	}

}
