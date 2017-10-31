package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestUserCreateDiscountProductIweb extends TestBase{

	String discountProdFee;
	boolean present = false;
	
	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}
	
	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsEventModule_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Inventory");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsFromListOnLeftPanel("Discount Product", "Find Discount");
		present = test.InvDiscProdFindDiscProd.userVerifyDiscountProductPresent(
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.prodName"));
		test.InvDiscProdFindDiscProd.userFindDiscountUsingDisplayName_IfPresent(present,
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.prodName"));
		test.Inv_DiscntProdListDiscntProd.userSelectsRecord(present,"1");
		test.InvDiscntProdProf.userDeleteDiscountProductIfPresent_(present);
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Inventory");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsFromListOnLeftPanel("Discount Product", "Add Discount");

	}

	@Test(dependsOnMethods = "Step02_User_SelectsEventModule_AndApplyFilter")
	public void Step03_User_AddsDiscountProduct() {
		test.InvAddDiscntProd.verifyUserOnInventory_DiscountProduct_AddDiscountProductPage();
		test.InvAddDiscntProd.user_CreateDiscountProduct(
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.prodName"),
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.priceRate"),
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.account"),
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.ewbCode"));
		test.InvDiscntProdProf.userOpenCustomerProfile_MenuOptionAndSubItem("Info", "discount amounts");
		test.InvDiscntProdProf.userEditDiscountProduct();
		test.InvAddDiscntProd.user_AddDiscountProduct(
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.heading"),
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.prodName"),
				YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.currencyType"));
	}
	
	@Test(dependsOnMethods = "Step03_User_AddsDiscountProduct")
	public void Step04_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestLockBoxCheckPayingForMultipleItems"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step04_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step05_User_NavigateToMerchandise() {
		test.CrmInd.userStoresCustomerMailingLabel();
		test.CrmInd.userSelectsOptionFromUserProfileMenu("invoice");
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userSelectsItemFromDropDown("Select Product", "bundles");
	}

	@Test(dependsOnMethods = "Step05_User_NavigateToMerchandise")
	public void Step06_User_PurchaseBundleProduct() {
		test.bundles.userSwitchToBundlesWindow();
		test.bundles.userSelectAFP_BundleProduct(
				YamlReader.getData("TestLockBoxCheckPayingForMultipleItems.bundleProduct"));
	}

	@Test(dependsOnMethods = "Step06_User_PurchaseBundleProduct")
	public void Step07_User_ApplyDiscountOnProduct() {
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userStoreLineItemAmountValuesBeforeDiscount();
		test.CentOrderEnt.userSelectsAdditionalItemFromDropDown("Additional Items", "add discount");
		test.CentOrderEnt.userSearchDiscountProduct(YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.prodName"));
		test.CRM_Ind_PriceList.userSelectDiscountProductFromTheList(YamlReader.getData("TestUserCreateDiscountProductIweb.Discount.prodName"));
		test.CentOrderEnt.userSelectRequiredDiscountProductAndClick("OK");
	}
	
	@Test(dependsOnMethods = "Step07_User_ApplyDiscountOnProduct")
	public void Step09_User_verifiesDiscountApplied() {
		test.CentOrderEnt.userVerifyDiscountApplied();

	}


}
