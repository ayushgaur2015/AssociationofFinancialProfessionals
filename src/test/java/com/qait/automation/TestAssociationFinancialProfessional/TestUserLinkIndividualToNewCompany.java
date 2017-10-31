package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Test;
import com.qait.automation.utils.YamlReader;

public class TestUserLinkIndividualToNewCompany extends TestBase{
	String Organization;
	
	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserLinkIndividualToNewCompany"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_EndsIndividualPreviousRelationship() {
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Relations", "organizations");
		test.CrmInd.userEditEndDateOfCurrentRelation();
		test.CrmInd.verifyEndDateOfOrganizationInRelationUpdated();
		Organization = test.CrmInd.userDeleteRelationWithCurrentOrganization();
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Log", "historical organization relationships");
		test.CrmInd.userVerifiesRelationRemovedAndDispalyedInHistory(Organization);
	}
	
	@Test(dependsOnMethods = "Step03_User_EndsIndividualPreviousRelationship")
	public void Step04_User_CreatesIndividualNewRelationship() {
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Relations", "organizations");
		test.CrmInd.userAddRelationWithNewOrganization(Organization);
		test.CrmInd.userEditTitle(YamlReader.getData("TestUserLinkIndividualToNewCompany.title"));
		test.CrmInd.userVerifyOrganizationDetailsUpdated(YamlReader.getData("TestUserLinkIndividualToNewCompany.title"));
	}

}

