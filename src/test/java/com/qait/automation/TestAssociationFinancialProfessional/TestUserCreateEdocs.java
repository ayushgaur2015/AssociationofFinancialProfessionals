package com.qait.automation.TestAssociationFinancialProfessional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

public class TestUserCreateEdocs extends TestBase{

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
		test.Inv_AddPubl.FillDetailsToAddPublicationProduct_Edoc(YamlReader.getYamlValues("TestUserCreateEdocs"));
		test.Inv_Pub_PubProf.verifyPublicationProfileDetails();
		test.Inv_Pub_PubProf.userOpenCustomerProfile_MenuOptionAndSubItem("Prices", "publication price");
		test.Inv_Pub_PubProf.userEditAlreadyCreatedPublicationProduct(YamlReader.getYamlValue("TestUserCreateEdocs.priceCode.Member"),YamlReader.getYamlValue("TestUserCreateEdocs.price.Member"));
		test.Inv_Pub_PubProf.userEditAlreadyCreatedChildOfParentPublicationProduct(YamlReader.getYamlValue("TestUserCreateEdocs.priceCode.Member"),"Member");
		test.Inv_Pub_PubProf.userAddNewPublicationPrice("Non-Member",YamlReader.getYamlValue("TestUserCreateEdocs.priceCode.Non-Member"),YamlReader.getYamlValue("TestUserCreateEdocs.price.Non-Member"),YamlReader.getYamlValues("TestUserCreateEdocs"));
	}

	@Test(dependsOnMethods = "Step03_UserAddPublicationProduct_NonMemeber_MemberAndSave")
	public void Step04_UserLinkInventory() {
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "CMS");
		test.Cms_Overview.userManageWebContent_Select_("List Web Sites");
		test.Cms_MngWebsite_ListWebSite
				.userFromTheWebsiteListSelectWebsite_(YamlReader.getYamlValue("TestUserCreateEdocs.webSite"));
		test.EditWebsite.selectSectionFromDropDown(YamlReader.getYamlValue("TestUserCreateEdocs.webSiteSection"));
		test.EditWebsite.searchPageLinkAndClick(YamlReader.getYamlValue("TestUserCreateEdocs.pageLinkSearch"));
		pubEwebUrl = test.EditWebsite.getProduct_eWebLink(YamlReader.getYamlValue("TestUserCreateEdocs.productName"));
	}

	@Test(dependsOnMethods = "Step04_UserLinkInventory", dataProvider = "getUser")
	public void Step05_User_Register_Into_Event_As_Non_Existing_User(String userType) {
		test.launchApplicationURL(pubEwebUrl);
		test.LogReq.verifyUserOnLoginPage();
		test.LogReq.userLogIntoApplicationAsExistingUser(YamlReader.getData(userType + ".userName"),
				YamlReader.getData(userType + ".pass"));
		test.ShoppingCart.verifyUserOnShoppingCartPage();
		test.ShoppingCart.verifyItemInCart(YamlReader.getData("TestUserCreateEdocs.productName"));
	}

	@Test(dependsOnMethods = "Step05_User_Register_Into_Event_As_Non_Existing_User")
	public void Step06_UserDeletesPublicationProduct() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Inventory");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Publication", "Find Publication");
		test.Inv_Pub_FindPub.verifyUserIsOnFindCertificantPage();
		test.Inv_Pub_FindPub.userSelectSearchFieldWithValue("Publication Code",
				YamlReader.getYamlValue("TestUserCreateEdocs.productCode"));
		test.Inv_Pub_FindPub.userClickOnButton("Go");
		test.Inv_Pub_ListPub.userNavigatesToPublicationProfile();
		test.Inv_Pub_PubProf.userDeletePublicationProduct();

	}

	@DataProvider
	public Iterator<Object[]> getUser() {
		List<Object[]> data = new ArrayList<>();
		data.add(new Object[] { userType[0] });
		data.add(new Object[] { userType[1] });

		return data.iterator();
	}


}
