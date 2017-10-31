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

public class TestUserReinstateCertificant extends TestBase{

	String designation;

	@Test
	public void Step01_UserLaunchApplicationCRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplicationCRM")
	public void Step02_UserSelectsEventModuleAndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Certification");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Certificant", "Query Certificant");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserReinstateCertificant"), "Run Query");
		test.CertList.userStoreRevokedCertificantRecordNumber();
		test.CertList.userSelectsRecord(1);
	}

	@Test(dependsOnMethods = "Step02_UserSelectsEventModuleAndApplyFilter")
	public void Step03_UserEditCertificantDetails() {
		test.Certi_IndCertProf.userEditCertificantProfile();
		test.Certi_IndCertProf.userVerifyUpdatePerformed_CertifiedFlag_Status("Yes", "Certified");
	}

	@Test(dependsOnMethods = "Step03_UserEditCertificantDetails")
	public void Step04_UserAddCreditsDetailsAndCycleSummary() {
		test.Certi_IndCertProf.userOpenCustomerProfile_MenuOptionAndSubItem("Credits", "ceu credits");
		test.Certi_IndCertProf.userAddCeuCredits();
		test.Certi_IndCertProf.userOpenCustomerProfile_MenuOptionAndSubItem("Cycle Summary", "all ceu details");
		test.Certi_IndCertProf.userVerifyCycleSummary_AllCeuCreditDetails();
	}

	@Test(dependsOnMethods = "Step04_UserAddCreditsDetailsAndCycleSummary")
	public void Step05_UserEditCreditsDetails() {
		test.Certi_IndCertProf.userOpenCustomerProfile_MenuOptionAndSubItem("Credits", "ceu credits");
		test.Certi_IndCertProf.storeUrlOfPage_("Certi_IndCertProf");
		test.Certi_IndCertProf.userClick_goToCreditDetails();
		test.Ceu_CreditProf.userEditCeuCredit("AFP Forums","AFP Forums");
		test.Ceu_CreditProf.navigateToStoredUrl("Certi_IndCertProf");
		test.Certi_IndCertProf.userOpenCustomerProfile_MenuOptionAndSubItem("Credits", "ceu credits");
		test.Certi_IndCertProf.userEditDates_Effective_Expiry_Revoked();
	}

	@Test(dependsOnMethods = "Step05_UserEditCreditsDetails")
	public void Step06_UserPerformMassReplaceAtIndividualEnd() {
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "CRM");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Find Individual");
		test.CrmInd_FindInd.userSelectSearchFieldAndStoredValue("Individual Sort Name", "Revoked Certificant Sort Name");
		test.CrmInd_FindInd.userClickOnButton("Go");
	}

	@Test(dependsOnMethods = "Step06_UserPerformMassReplaceAtIndividualEnd")
	public void Step07_UserMassReplaceIndividualParameters() {
		test.CrmIndList.userClickOnMassReplaceButton();
		test.CrmIndList.userSelectFieldNameDesignation();
		test.CrmIndList.userInputFieldValue_Designation();
		test.CrmIndList.userClickOn("Replace");

	}

	@Test(dependsOnMethods = "Step07_UserMassReplaceIndividualParameters")
	public void Step08_UserVerifyMassReplaceOfIndividual() {
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Find Individual");
		test.CrmInd_FindInd.userSelectSearchFieldAndStoredValue("Individual Sort Name", "Revoked Certificant Sort Name");
		test.CrmInd_FindInd.userClickOnButton("Go");
		test.CrmIndList.userSelectsRecord("1");
		test.CrmInd.userVerifyIndividualTitleReplaced("CTP", true);
	}


}
