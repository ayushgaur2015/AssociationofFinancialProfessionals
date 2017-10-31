package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestUserCopyEvent_Sessions extends TestBase{

	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}
	
	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsLinkCopyEvent() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Events");
		test.OverviewSetup.userClickOnOverViewWizard("CopyEvent");
	}
	
	@Test(dependsOnMethods = "Step02_User_SelectsLinkCopyEvent")
	public void Step03_User_CreateCopyEvent(){
		test.EventCopyWiz.userSelectCopyEvent_CreateNewEvent("2016 AFP Annual Conference", "2017 AFP Annual Conference", "AN17");
		test.EventCopyWiz.userEditProductName_Price("2017 AFP Annual Conference", "AN17", "2017 AFP Annual Conference", "AN17-2.Stan-Mbr");
		test.EventCopyWiz.userEditSessionTitle_Price("Advanced Cash Flow Forecasting (SA01) Copy", "SA01", "Advanced Cash Flow Forecasting (SA01) Copy", "SA01");
		test.EventCopyWiz.userGotoCreatedEventLink();
	}

	@Test(dependsOnMethods = "Step03_User_CreateCopyEvent")
	public void Step04_User_CreateCopyEvent(){
		test.EventPlanningProfile.userVerifyEventTitle("2017 AFP Annual Conference");
		test.EventPlanningProfile.userVerifyEventStart_endDate();
		test.EventPlanningProfile.userVerifyEventCodeType("AN17");

	}
	
	@Test(dependsOnMethods = "Step04_User_CreateCopyEvent")
	public void Step05_User_VerifyRegistrationPriceCode_Price(){
		test.EventPlanningProfile.userOpenCustomerProfile_MenuOptionAndSubItem("Fees", "registration fees");
		test.EventPlanningProfile.userExpandProductFeesUsingProductCode("AN17");
		test.EventPlanningProfile.userVerifyEventRegistrationFeesUsingRegistrationPriceCode("AN17-2.Stan-Mbr");
		test.EventPlanningProfile.userVerifyUncheckedPriceDisplayNameNotPresent();
	}
	
	
	@Test(dependsOnMethods = "Step05_User_VerifyRegistrationPriceCode_Price")
	public void Step06_User_VerifySessionProfile_SessionFee(){
		test.EventPlanningProfile.userOpenCustomerProfile_MenuOptionAndSubItem("Tracks/Sessions", "session");
		test.EventPlanningProfile.userGotoREquiredSEssionProfile("Advanced Cash Flow Forecasting (SA01) Copy");
		test.EventsSessionWorkshop.userVerifySessionTitle("Advanced Cash Flow Forecasting (SA01) Copy");
		test.EventsSessionWorkshop.userOpenCustomerProfile_MenuOptionAndSubItem("Fees", "session fees");
		test.EventsSessionWorkshop.userVisitsParentEventProfile();

	}
	
	@Test(dependsOnMethods = "Step06_User_VerifySessionProfile_SessionFee")
	public void Step06_User_DeletesNewCreatedEvent(){
	test.EventPlanningProfile.userClickEditProfileButton();
	test.EventPlanningProfile.userPerformOperation_("Delete");
	}

}
