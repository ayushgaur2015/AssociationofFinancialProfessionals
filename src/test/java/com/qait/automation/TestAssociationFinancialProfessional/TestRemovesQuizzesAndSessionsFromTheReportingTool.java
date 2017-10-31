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

public class TestRemovesQuizzesAndSessionsFromTheReportingTool extends TestBase{
	boolean empty;

	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsEventModule_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Events");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsFromListOnLeftPanel("Event Planning", "Find Event");
		test.EventPlanningFindEvents.verifyUserOnFindEventPage();
		test.EventPlanningFindEvents.user_EntersEventCodeToFindEvent(
				YamlReader.getData("TestRemovesQuizzesAndSessionsFromTheReportingTool.EventCode"));
		test.EventPlanningProfile.storeUrlOfPage_("EventPlanningProfileUrl");
	}

	@Test(dependsOnMethods = "Step02_User_SelectsEventModule_AndApplyFilter")
	public void Step03_User_SelectsSessionAndNavigateToSessionsProfile() {
		test.CrmIndList.userSelectsRecord("1");
		test.EventPlanningProfile.userVerifyEventCodeType(
				YamlReader.getData("TestRemovesQuizzesAndSessionsFromTheReportingTool.EventCode"));
		test.EventPlanningProfile.userOpenCustomerProfile_MenuOptionAndSubItem("Tracks/Sessions", "sessions");
		test.EventPlanningProfile.userGotoRandomSessionProfile();
	}

	@Test(dependsOnMethods = "Step03_User_SelectsSessionAndNavigateToSessionsProfile")
	public void Step04_User_DeleteSessionCredits() {
		test.EventsSessionWorkshop.userVerifyWorkshopTitle();
		test.EventsSessionWorkshop.userOpenCustomerProfile_MenuOptionAndSubItem("Credits", "event session credits");
		empty = test.EventsSessionWorkshop.verifyMessageInTheView("event session credits",
				"There are no results to display");
		while (empty) {
			test.EventPlanningProfile.navigateToStoredUrl("EventPlanningProfileUrl");
			Step03_User_SelectsSessionAndNavigateToSessionsProfile();
			empty = test.EventsSessionWorkshop.verifyMessageInTheView("event session credits",
					"There are no results to display");
		}
		
		test.EventsSessionWorkshop.userDeletesSessions();
		test.EventsSessionWorkshop.userVerifySessionTitleListIsEmtpy_();
	}

}
