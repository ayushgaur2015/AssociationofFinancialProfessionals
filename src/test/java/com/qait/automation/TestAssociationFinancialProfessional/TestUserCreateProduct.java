
package com.qait.automation.TestAssociationFinancialProfessional;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

public class TestUserCreateProduct extends TestBase{

	String event;
	String pubEwebUrl = " ";
	static int pubProduct = 0;
	static int no_ofUser = 1;
	String[] userType = { "user.Member", "user.Non-Member" };

	@Test
	public void Step01_UserLaunchApplicationCRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplicationCRM")
	public void Step02_UserSelectsEventModuleAndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Inventory");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Publication", "Add Publication");

	}

	@Test(dependsOnMethods = "Step02_UserSelectsEventModuleAndApplyFilter")
	public void Step03_UserAddPublicationProduct_NonMemeber_MemberAndSave() {
		test.Inv_AddPubl.FillDetailsToAddPublicationProduct_Edoc("Member",YamlReader.getYamlValues("TestUserCreateProduct"), true, false, true, true, true);
		test.Inv_Pub_PubProf.userOpenCustomerProfile_MenuOptionAndSubItem("Prices", "publication price");
		test.Inv_Pub_PubProf.userEditAlreadyCreatedPublicationProduct(YamlReader.getYamlValue("TestUserCreateProduct.priceCode.Member"),YamlReader.getYamlValue("TestUserCreateProduct.price.Member"));
		test.Inv_Pub_PubProf.userEditAlreadyCreatedChildOfParentPublicationProduct(YamlReader.getYamlValue("TestUserCreateProduct.priceCode.Member"),"Member");
		test.Inv_Pub_PubProf.userAddNewPublicationPrice("Non-Member",YamlReader.getYamlValue("TestUserCreateProduct.priceCode.Non-Member"),YamlReader.getYamlValue("TestUserCreateProduct.price.Non-Member"),YamlReader.getYamlValues("TestUserCreateProduct"));
		test.Inv_Pub_PubProf.userOpenCustomerProfile_MenuOptionAndSubItem("Miscellaneous", "publication shipping");
		test.Inv_Pub_PubProf.userAddProductShippingRegion(YamlReader.getYamlValue("TestUserCreateProduct.shipping.type1.price"),YamlReader.getYamlValue("TestUserCreateProduct.shipping.type1.region"));
		test.Inv_Pub_PubProf.userAddProductShippingRegion(YamlReader.getYamlValue("TestUserCreateProduct.shipping.type2.price"),YamlReader.getYamlValue("TestUserCreateProduct.shipping.type2.region"));
	}

	@Test(dependsOnMethods = "Step03_UserAddPublicationProduct_NonMemeber_MemberAndSave")
	public void Step04_UserAddPublicationProductIntoWarehouse() {
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Warehouse", "List Warehouses");
		test.Inv_Warhs_ListWarhs.userFromTheWarehouseListSelectWarehouse_(YamlReader.getYamlValue("TestUserCreateProduct.warehouse"));
		test.Inv_Warhs_WarhsProf.userAddInventoryIntoWarehouse(
				YamlReader.getYamlValue("TestUserCreateProduct.productName"),
				YamlReader.getYamlValue("TestUserCreateProduct.productLocation")+System.currentTimeMillis());
		test.Inv_Warhs_WarhsProf.userUpdatePhysicalInventoryAccountOfProduct(
				YamlReader.getYamlValue("TestUserCreateProduct.productName"), "30", "0");
		test.Inv_Warhs_WarhsProf.userVerifyInventoryAddedIntoWarehouse();
	}

	@Test(dependsOnMethods = "Step04_UserAddPublicationProductIntoWarehouse")
	public void Step06_UserDeletesPublicationProduct() {
		test.launchApplicationURL(YamlReader.getData("iwebUrl"));
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Inventory");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Publication", "Find Publication");
		test.Inv_Pub_FindPub.verifyUserIsOnFindCertificantPage();
		test.Inv_Pub_FindPub.userSelectSearchFieldWithValue("Publication Code",
				YamlReader.getYamlValue("TestUserCreateProduct.productCode"));
		test.Inv_Pub_FindPub.userClickOnButton("Go");
		test.Inv_Pub_ListPub.userNavigatesToPublicationProfile();
		test.Inv_Pub_PubProf.userDeletePublicationProduct();

	}

	
}
