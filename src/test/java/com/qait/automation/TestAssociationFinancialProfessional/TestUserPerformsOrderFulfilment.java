package com.qait.automation.TestAssociationFinancialProfessional;

import java.util.List;
import org.testng.annotations.Test;
import com.qait.automation.utils.DateUtil;
import com.qait.automation.utils.YamlReader;

public class TestUserPerformsOrderFulfilment extends TestBase{

	String publications_Product;
	List<String> details = null;

	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserPerformsOrderFulfilment"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_SelectsTheRequiredResults_AndOpenInvoice() {
		test.CrmInd.verifyUserNotMember();
		test.CrmInd.verifyUserSourceType();
		test.CrmInd.userStoresCustomerMailingLabel();
		test.CrmInd.userSelectsOptionFromUserProfileMenu("invoice");
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userStoresCustomerInformation();
		test.CentOrderEnt.userSelectsItemFromDropDown("Select Product", "publications");
	}

	@Test(dependsOnMethods = "Step03_User_SelectsTheRequiredResults_AndOpenInvoice")
	public void Step04_User_PurchaseBundleProduct() {
		test.publications
				.userSelectAFP_PublicationsProduct(YamlReader.getData("TestUserPerformsOrderFulfilment.publications"));
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userVerifyCustomerInformation();
		test.CentOrderEnt.verifyPublicationsLineItemsINOrderEntry(
				YamlReader.getData("TestUserPerformsOrderFulfilment.publicationsDisplayName"));
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestUserPerformsOrderFulfilment.paymentType"),
				YamlReader.getData("TestUserPerformsOrderFulfilment.paymentMethod"), "Save");
	}

	@Test(dependsOnMethods = "Step04_User_PurchaseBundleProduct")
	public void Step06_User_SelectsEventModule_AndApplyFilter() {
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Inventory");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsFromListOnLeftPanel("Fulfillment Orders", "Overview");
		test.InvFulfilOrdOverview.verifyUserOnInventoryFulfillmentOrderOverviewPage();
		test.InvFulfilOrdOverview.userClickOnLink("- Fulfill Orders");

	}

	@Test(dependsOnMethods = "Step06_User_SelectsEventModule_AndApplyFilter")
	public void Step07_User_SearchForOrdersToFulfill() {
		test.InvActOrdFulfill.verifyUserOnInventoryActivityOrderFulfillmentPage();
		test.InvActOrdFulfill.user_EntersDateAndClickOnSearch(
				DateUtil.getCurrentdateInStringWithGivenFormate("MM/dd/yyyy"),
				DateUtil.getCurrentdateInStringWithGivenFormate("MM/dd/yyyy"));
		details = test.InvActOrdFulfill.user_SelectsRequiredWarehouseAndClickOn_Proceed("IFC", "Process Requests");
	}

	@Test(dependsOnMethods = "Step07_User_SearchForOrdersToFulfill")
	public void Step08_User_verifyOrdersDetails() {
		test.InvFulfilOrd_FulfilGrpProf.expandview("fulfilled items");
		test.InvFulfilOrd_FulfilGrpProf
				.verifyShippingDetails(YamlReader.getData("TestUserPerformsOrderFulfilment.publicationsDisplayName"));

	}
}