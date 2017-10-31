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

public class TestUserPerformsRevocations extends TestBase{

	String recordNumber="";
		
	@Test
	public void Step01_UserLaunchApplicationCRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}

	@Test(dependsOnMethods = "Step01_UserLaunchApplicationCRM")
	public void Step02_UserSelectsCertificationModuleAndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "Certification");
	}

	@Test(dependsOnMethods = "Step02_UserSelectsCertificationModuleAndApplyFilter")
	public void Step03_UserApplyMassReplace() {
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Certificant", "Query Certificant");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserPerformsRevocations"), "Run Query");
		test.CertList.userStoreCertificantRecordNumber();

	}

	@Test(invocationCount=3,dependsOnMethods = "Step03_UserApplyMassReplace")
	public void Step04_UserMassReplaceCertificantParameters() {
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Certificant", "Mass Replace");
		test.Cert_Certi.userSelectFieldName();
		test.Cert_Certi.userSelectFieldValue();
		test.Cert_Certi.userClickOn("Replace");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Certificant", "Query Certificant");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserPerformsRevocations"), "Run Query");

	}

	@Test(dependsOnMethods = "Step04_UserMassReplaceCertificantParameters")
	public void Step04_UserVerifyMassReplaceCertificantParameters() {
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Certificant", "Find Certificant");
		test.Cert_FindCerti.userSearchCertificantUsingRecordNumber();
		test.CertList.userSelectsRecord(1);
		test.Certi_IndCertProf.userVerifyReplacePerformed_CertifiedFlag_Status("No","Revoked");

	}

	@Test(dependsOnMethods = "Step04_UserVerifyMassReplaceCertificantParameters")
	public void Step05_UserPerformMassReplaceAtIndividualEnd() {
		test.OverviewSetup.userSelectsTopHeaderMenuItems("Modules", "CRM");
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals", "Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(
				YamlReader.getData("Existing query.TestUserPerformsRevocations"), "Run Query");

	}

	@Test(dependsOnMethods = "Step05_UserPerformMassReplaceAtIndividualEnd")
	public void Step06_UserMassReplaceIndividualParameters() {
		test.CrmIndList.userClickOnMassReplaceButton();
		test.CrmIndList.userSelectClearValueCheckBox();
		test.CrmIndList.userClickOn("Replace");

	}

	@Test(dependsOnMethods = "Step06_UserMassReplaceIndividualParameters")
	public void Step07_UserVerifyMassReplaceOfIndividual() {
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals","Find Individual");
		test.CrmInd_FindInd.userSelectSearchFieldAndStoredValue("Record Number", "RecordNumber");
		test.CrmInd_FindInd.userClickOnButton("Go");
		test.CrmIndList.userSelectsRecord_("1");
		test.CrmInd.userVerifyIndividualTitleReplaced("CTP");
	}
	
}
