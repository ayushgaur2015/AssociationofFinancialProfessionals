package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestUserCancelExhibits extends TestBase{
	
	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Exhibits");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsFromListOnLeftPanel("Exhibitor Management", "Query Exhibitor");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserCancelExhibits"), "Run Query");
		test.Exhibits_ExhibitsMgmt_Lists.storeExhibitsInformation(1);
		test.Exhibits_ExhibitsMgmt_Lists.userSelectsRecord_("1");
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step03_User_CancelExhibits(){
		test.Exhibits_ExhibitorMgmt.userClickCancelExhibitBtn();
		test.Exhibits_ExhibitorMgmt.userCompleteCancellationProcess(YamlReader.getData("BatchName"));
		test.Exhibits_ExhibitorMgmt.userOpenCustomerProfile_MenuOptionAndSubItem("Exhibitor Booths", "exhibitor booths");
		test.Exhibits_ExhibitorMgmt.userVerifyExhibitCancelled();
	}
	
	
}
