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

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

public class TestUserPerformsSubstitution extends TestBase{
	String event;

	@Test
	public void Step01_UserLaunchApplicationCRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplicationCRM")
	public void Step02_UserSelectsEventModuleAndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Events");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Registrants", "Run Query Event Registrant");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserRefundsAfterCancelEvent"), "Run Query");
		test.EventRegList.verifyUserOnEventsRegistrantsListPage();
		test.EventRegList.userSelectsRandomRegistrantFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_UserSelectsEventModuleAndApplyFilter")
	public void Step03_UserPerformsSubstitutionOfEvent() {
		test.EventRegProf.userStoreRegistrantData();
		event = test.EventRegProf.userStoresRegistrantEvent();
		test.EventRegProf.userSelectsRegistrantActionItemFromDropDown("Registrant Actions", "substitute");
		test.EventRegProf.userSelectsSubstituentRegistrant();
		test.EventRegProf.userSelectSubstituentFee();
		test.EventRegProf.userCompletesSubstitutionTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestUserPerformsSubstitution.paymentType"),YamlReader.getData("TestUserPerformsSubstitution.paymentMethod"), "Save");
		test.EventRegProf.userNavigatesToCustomerProfile();
		test.CrmInd.userVerifySubstitutionOfEventOnIndividualProfile(event);

	}

}
