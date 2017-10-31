package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class Accounting_Credit_AddCreditActionsPage extends GeneralActionsPage{

	public Accounting_Credit_AddCreditActionsPage(WebDriver driver) {
		super(driver, "Accounting_Credit_AddCreditActionsPage");
	}

	public void verifyUserOnAccounting_Credit_AddCreditPage(){
		verifyPageTitleContains();
		msg.actions("Verified user on Accounting Credit add credit page\n");
	}
	
	public void enterCustomerName(String name){
		enterDetails("sortName", name);
		msg.actions("Customer name is entered\n");
	}
	
	public void clickOnSearchIcon(){
		hoverClick(element("icon_searchSortName"));
		msg.actions("clicked on search icon completes\n");
	}
	
	public void selectCurrencyType(String currency){
		selectProvidedTextFromDropDown(element("dropdown_currency"), currency);
		msg.log(currency+" is selected as the currrency type\n");
		msg.actions("currency type is selected\n");
	}
	
	public void selectCreditBatch(String batch){
		selectProvidedTextFromDropDown(element("dropdown_batch"), batch);
		msg.log(batch+" is selected as the batch type\n");
		msg.actions("batch type is selected\n");

	}
	
	public void enterCreditAmnt(String amnt){
		enterDetails("creditAmount", amnt);
		msg.actions("credit amount is entered\n");
	}
	
	public void selectCreditReason(String reason){
		selectProvidedTextFromDropDown(element("dropdown_creditReason"), reason);
		msg.actions(reason+"credit reason is selected\n");
	}
	
	public void selectCashAccnt(String Accnt){
		dynamicWait(5, "dropdown_cashReturn");
		selectProvidedTextFromDropDown(element("dropdown_cashReturn"), Accnt);
		msg.actions(Accnt+"cash account is selected\n");

	}
	
	public void selectLiabilityAccnt(String Accnt){
		selectProvidedTextFromDropDown(element("dropdown_liabilitExpenses"), Accnt);
		msg.actions(Accnt+"liability account is selected\n");

	}

	public void userSearchCustomerToGenerateCredit(String name){
		enterCustomerName(name);
		clickOnSearchIcon();
		msg.actions("user search customer to generate credit completes\n");
	}
	
	public void userEntersCreditDetails(String currency,String batch, String creditAmnt, String creditReason, String cashAccnt, String liabilityAccnt){
		selectCurrencyType(currency);
		selectCreditBatch(batch);
		enterCreditAmnt(creditAmnt);
		selectCreditReason(creditReason);
		selectCashAccnt(cashAccnt);
		selectLiabilityAccnt(liabilityAccnt);
		msg.actions("user enters credit details completes\n");
	}
	
}
