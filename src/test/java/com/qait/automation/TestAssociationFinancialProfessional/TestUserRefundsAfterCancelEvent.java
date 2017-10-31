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

public class TestUserRefundsAfterCancelEvent extends TestBase{

	String event;

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
				YamlReader.getData("Existing query.TestUserRefundsAfterCancelEvent"), "Run Query");
		test.EventRegList.verifyUserOnEventsRegistrantsListPage();
		test.EventRegList.userSelectsRandomRegistrantFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_PerformsRefundAfterCancellationOfEvent() {
		test.EventRegProf.userStoreRegistrantData();
		event = test.EventRegProf.userStoresRegistrantEvent();
		test.EventRegProf.userSelectsRegistrantActionItemFromDropDown("Registrant Actions", "cancel");
		test.EventRegProf.userFillDetailsAndAutoRefundCheckboxStateOnEventCancellationWizard("false");
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),
				YamlReader.getData("TestUserRefundsAfterCancelEvent.paymentType"),
				YamlReader.getData("TestUserRefundsAfterCancelEvent.paymentMethod"), "Save");
		test.EventRegProf.userNavigatesToCustomerProfile();
		test.CrmInd.userVerifyRefundDetailsOnIndividualProfile(event);

	}

}
