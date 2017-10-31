package com.qait.automation.TestAssociationFinancialProfessional;

import org.testng.annotations.Test;

import com.qait.automation.utils.YamlReader;

public class TestUserCreateCEU_ReportingInHouse extends TestBase {

	String comment;

	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserCreateCEU_ReportingInHouse"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();
	}

	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_AddCEU_ReprotingInHouseCredit() {
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("CEU Credits", "ceu credits");
		test.CrmInd.userAddNewCEU_creditProduct(
				YamlReader.getData("TestUserCreateCEU_ReportingInHouse.ceuCreditCategoy"),
				YamlReader.getData("TestUserCreateCEU_ReportingInHouse.ceuCreditProductType"),
				YamlReader.getData("TestUserCreateCEU_ReportingInHouse.creditAmount"),
				YamlReader.getData("TestUserCreateCEU_ReportingInHouse.ceuCreditDate"),
				YamlReader.getData("TestUserCreateCEU_ReportingInHouse.ceuCreditComments"));
	}

	@Test(dependsOnMethods = "Step03_User_AddCEU_ReprotingInHouseCredit")
	public void Step03_User_VerifyCEU_ReprotingInHouseCredit() {
		test.CrmInd.userVerifyCeuCreditProductAdded("Individual_CEU_Credit_Amnt");
		test.CrmInd.userNavigatesCeuCreditProfile("Individual_CEU_Credit_Amnt");
		test.Ceu_CreditProf.userVerifyCeuCreditAmnt("Individual_CEU_Credit_Amnt");
		test.Ceu_CreditProf.userVerifyCreditActivityDate("Individual_CEU_Credit_Date");
		test.Ceu_CreditProf.userVerifyCreditComments("Individual_CEU_Credit_Cmnts");
	}

}
