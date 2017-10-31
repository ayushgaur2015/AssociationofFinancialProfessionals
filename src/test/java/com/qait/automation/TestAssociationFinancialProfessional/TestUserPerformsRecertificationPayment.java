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

public class TestUserPerformsRecertificationPayment extends TestBase{
    String ctpRecertProd;
	
	@Test
	public void Step01_UserLaunchApplication_CRM() {
		test.launchApplication(YamlReader.getData("iwebUrl"));
	}
	
	@Test(dependsOnMethods = "Step01_UserLaunchApplication_CRM")
	public void Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter() {
		test.OverviewSetup.verifyUserOnOverviewAndSetupPage();
		test.OverviewSetup.userSelectsFilterAndFilterOptionsOnLeftPanel("Individuals","Query Individual");
		test.IndQuery.userPerformsOperationOnExistingQuery(YamlReader.getData("Existing query.TestUserPerformsRecertificationPayment"), "Run Query");
		test.CrmIndList.userSelectsRandomRecordFromTheExistingList();	
		}
	
	@Test(dependsOnMethods = "Step02_User_SelectsProcessFromLeftPanel_AndApplyFilter")
	public void Step03_User_NavigateToMerchandise() {
		test.CrmInd.verifyUserNotMember();
		test.CrmInd.userSelectsOptionFromUserProfileMenu("invoice");
		test.CentOrderEnt.verifyUserOnCentralizedOrderEntryPage();
		test.CentOrderEnt.userStoresCustomerInformation();
		test.CentOrderEnt.userSelectsItemFromDropDown("Select Product", "merchandise");
	}
	
	@Test(dependsOnMethods = "Step03_User_NavigateToMerchandise")
	public void Step04_User_FillMerchandiseForm(){
		test.CentOrderEnt.userSelectProduct("Merchandise");
		test.CentOrderEnt.userSelectProductCategory("CTP Recertification");
		test.CentOrderEnt.userSelectProductDisplayName();
		
	}
	
	@Test(dependsOnMethods = "Step04_User_FillMerchandiseForm")
	public void Step05_User_CompletesAndVerifyRecertification(){
		ctpRecertProd = test.CentOrderEnt.userVerifyMerchandise();
		test.CentOrderEnt.userCompletesTransaction_andClickOn_(YamlReader.getData("Batch"),YamlReader.getData("TestUserPerformsRecertificationPayment.paymentType"),YamlReader.getData("TestUserPerformsRecertificationPayment.paymentMethod"), "Save");
		test.CrmInd.userOpenCustomerProfile_MenuOptionAndSubItem("Invoices", "invoices (open batch)");
		test.CrmInd.userClickOnGotoLinkForRequiredProduct("Invoices",YamlReader.getData("Batch"));
		test.CrmInvProf.verifyUserOnInvoiceProfile();
		test.CrmInvProf.userOpenCustomerProfile_MenuOptionAndSubItem("Details", "line items");
		test.CrmInvProf.userVerifyRecertificationForExam(ctpRecertProd);

	}
}
