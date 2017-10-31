package com.qait.automation.TestAssociationFinancialProfessional;


import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestUserCreateDiscountForWebinar extends TestBase{

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
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.prodName"));
		test.InvDiscProdFindDiscProd.userFindDiscountUsingDisplayName_IfPresent(present,
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.prodName"));
		test.Inv_DiscntProdListDiscntProd.userSelectsRecord(present,"1");
		test.InvDiscntProdProf.userDeleteDiscountProductIfPresent_(present);
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Inventory");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsFromListOnLeftPanel("Discount Product", "Add Discount");

	}

	@Test(dependsOnMethods = "Step02_User_SelectsEventModule_AndApplyFilter")
	public void Step03_User_AddsDiscountProduct() {
		test.InvAddDiscntProd.verifyUserOnInventory_DiscountProduct_AddDiscountProductPage();
		test.InvAddDiscntProd.user_CreateDiscountProduct(
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.prodName"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.priceRate"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.account"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.ewbCode"));
		test.InvDiscntProdProf.userOpenCustomerProfile_MenuOptionAndSubItem("Info", "discount amounts");
		test.InvDiscntProdProf.userEditDiscountProduct();
		test.InvAddDiscntProd.user_AddDiscountProduct(
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.heading"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.prodName"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.currencyType"));
	}

	@Test(dependsOnMethods = "Step03_User_AddsDiscountProduct")
	public void Step04_User_AddsDiscountProduct_X_Product() {
		test.InvDiscntProdProf.verifyUserOnInventoryDiscountProductProfileActionsPage();
		test.InvDiscntProdProf.user_AddDiscountProductXProductWithValue_(
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.heading"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.productName"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.prodType"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.product"),
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.price"));
	}

	@Test(dependsOnMethods = "Step04_User_AddsDiscountProduct_X_Product")
	public void Step05_User_VerifyDiscountAndDiscountProduct_X_Product() {
		test.InvDiscntProdProf.userOpenCustomerProfile_MenuOptionAndSubItem("Info", "discount amounts");
		test.InvDiscntProdProf.userVerifyEventNameInFollowingTab_(
				YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.prodName"), "discount amounts");
		test.InvDiscntProdProf.userOpenCustomerProfile_MenuOptionAndSubItem("Info", "discount product x product");
		test.InvDiscntProdProf.userVerifyEventNameInFollowingTab_(
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.product"), "discount product x product");

	}

	@Test(dependsOnMethods = "Step05_User_VerifyDiscountAndDiscountProduct_X_Product")
	public void Step06_User_Register_Into_Event_As_Non_Existing_User() {
		test.launchApplicationURL(YamlReader.getData("eWebURL_AddDiscount"));
		test.LogReq.verifyUserOnLoginPage();
		test.LogReq.userLogIntoApplicationAsExistingUser(YamlReader.getData("user_AddDiscount.username"),
				YamlReader.getData("user_AddDiscount.password"));
	}

	@Test(dependsOnMethods = "Step06_User_Register_Into_Event_As_Non_Existing_User")
	public void Step07_User_Adds_The_Event_In_Shopping_Cart() {
		discountProdFee = test.EventReg.verifyCorrectProductEventAndGetFee(
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.product"));
		test.EventReg.userAddEventToTheCart();
	}

	@Test(dependsOnMethods = "Step07_User_Adds_The_Event_In_Shopping_Cart")
	public void Step08_User_Purchase_Event_Registration_From_Shopping_Cart() {
		test.ShoppingCart.verifyUserOnShoppingCartPage();
		test.ShoppingCart
				.productDetailsBeforeDiscount(YamlReader.getData("TestUserCreateDiscountForWebinar.Product.product"));
		test.ShoppingCart
				.userApplyDiscountCode(YamlReader.getData("TestUserCreateDiscountForWebinar.Discount.ewbCode"));
		test.ShoppingCart
				.productDetailsAfterDiscount(YamlReader.getData("TestUserCreateDiscountForWebinar.Product.product"));
		test.ShoppingCart.userVerifyDiscountAppliedOnProduct(
				YamlReader.getData("TestUserCreateDiscountForWebinar.Product.product"));
		test.ShoppingCart.userVerifyProduct_Total_Net_SubTotal_DiscountDetailsOnShoppingCart();
	}
}
