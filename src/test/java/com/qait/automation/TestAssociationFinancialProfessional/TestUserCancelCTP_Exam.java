package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestUserCancelCTP_Exam extends TestBase{
	
	String event;
	String Refund_Product;
	String userName;
	int EventRgistrantSize;

	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}
	
	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Events");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Registrant", "Run Query Event Registrant");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserCancelCTP_Exam"), "Run Query");
		test.EventRegList.verifyUserOnEventsRegistrantsListPage();
		userName = test.EventRegList.userSelectsRandomRegistrantFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_PerformsRefundAfterCancellationOfEvent() {
		test.EventRegProf.userStoreRegistrantData();
		event = test.EventRegProf.userStoresRegistrantEvent();
		test.EventRegProf.userSelectsRegistrantActionItemFromDropDown("Registrant Actions", "cancel");
		test.EventRegProf.userFillDetails_WaivedAndAutoRefundCheckboxStateOnEventCancellationWizard("false");
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestUserRefundsAfterCancelEvent.paymentType"),
				YamlReader.getData("TestUserRefundsAfterCancelEvent.paymentMethod"), "Save");
		test.EventRegProf.userVerifyCancellationDateIsDisplayed();
	}
	
	@Test(dependsOnMethods = "Step03_User_PerformsRefundAfterCancellationOfEvent")
	public void Step04_User_ProvideInvoicesDetailsForRefund() {
		test.EventRegProf.userOpenCustomerProfile_MenuOptionAndSubItem("Financial", "invoice");
		test.EventRegProf.userClickOnGotoLinkForRequired_Invoice_Batch();
		test.CrmInvProf.verifyUserOnInvoiceProfile();
		test.CrmInvProf.userClickVoidInvoiceProfileIcon();
		test.CrmInvProf.selectBatchForVoidInvoice(YamlReader.getData("Batch"));
		test.CrmInvProf.userProvideRefundDetails("AFP Membership");
		test.CrmInvProf.userSaveRefundDetail();
	}
	
	@Test(dependsOnMethods = "Step04_User_ProvideInvoicesDetailsForRefund")
	public void Step05_User_GeneratesRefundFromAccounts() {
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Accounting");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Refund", "Add Refund");
		test.Acc_Ref_AddRef.userSearchCustomerToPerformRefund(userName);
		test.Acc_Ref_ListCust.selectCustomer(userName);
		test.Acc_Ref_AddRef.userEntersRefundDetailsAndSearchForRefundInvoice(YamlReader.getData("BatchName"));
		test.Acc_Ref_AddRef.userEnterRefundAmtAndSave(userName,"495.00","195.00");
		test.Acc_Ref_AddRef.userSaveRefundApplied();
	}
	
	@Test(dependsOnMethods = "Step05_User_GeneratesRefundFromAccounts")
	public void Step06_User_GeneratesCreditForUser() {
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Accounting");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsFromListOnLeftPanel("Credit", "Add Credit",true);
		test.Acc_Cre_AddCre.userSearchCustomerToGenerateCredit(userName);
		test.Acc_Ref_ListCust.selectCustomer(userName);
		test.Acc_Cre_AddCre.userEntersCreditDetails("USD - United States Dollars","AFP: 2016-05-18-Scripting-testing-001","195","CTP Exam Cancellation","49000-200-000-5900:Discount- Membership Marketing Bundle ","20110-000-000-0000 : ORDER ENTRY CLEARING");
		test.Acc_Cre_AddCre.userClickOn("btn_saveCancel", "Save");
		test.Acc_Cre_CreProf.userGotoRegistantProfile();
	}
	
		
	@Test(dependsOnMethods = "Step06_User_GeneratesCreditForUser")
	public void Step07_User_NavigateToMerchandise() {
		test.CrmInd.userSelectsOptionFromUserProfileMenu("invoice");
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userStoresCustomerInformation();
		test.CentOrderEnt.userSelectsItemFromDropDown("Select Product", "bundles");
	}

	@Test(dependsOnMethods = "Step07_User_NavigateToMerchandise")
	public void Step08_User_PurchaseBundleProduct() {
		test.bundles.userSwitchToBundlesWindow();
		Refund_Product = test.bundles.userSelectAFP_BundleProduct(
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.bundleProduct"));
	}

	@Test(dependsOnMethods = "Step08_User_PurchaseBundleProduct")
	public void Step09_User_CompletesTransaction() {
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userVerifyCustomerInformation();
		test.CentOrderEnt.verifyLineItemInOrderEntry(Refund_Product);
		test.CentOrderEnt.userApplyCreditOfBillToCustomer();
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.II-paymentType"),
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.II-paymentMethod"), "Save");

	}
	
	@Test(dependsOnMethods = "Step09_User_CompletesTransaction")
	public void Step10_User_verifiesTransaction() {
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Invoices", "invoices (open batch)");
		test.CrmInd.userClickOnGotoLinkForRequiredProduct("Invoices",YamlReader.getData("Batch"));
		test.CrmInvProf.userVerifyEventInvoiceMailingAddress();
	}
}

