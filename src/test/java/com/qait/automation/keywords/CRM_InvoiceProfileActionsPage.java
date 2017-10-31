package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class CRM_InvoiceProfileActionsPage extends GeneralActionsPage {

	public CRM_InvoiceProfileActionsPage(WebDriver driver) {
		super(driver, "CRM_InvoiceProfileActionsPage");
	}

	public void verifyIfPaymentIsCompleteOrNot(String paymentFlag) {
		Assert.assertTrue(isElementDisplayed("text_paidInFull", paymentFlag),
				msg.failForAssert(paymentFlag + " is not displayed as payment in full option\n"));
		msg.pass(paymentFlag + " is displayed as payment in full option\n");

	}

	public void userVerifyRecertificationForExam(String product) {
		String[] productName = product.split(" ");
		dynamicWait(3, element("text_lineItems"));
		for(int i=0;i<productName.length;i++){
		Assert.assertTrue(element("text_productLineItems").getText().contains(productName[i]),
				msg.failForAssert(element("text_productLineItems").getText()+"not contains "+product + " hence process incomplete\n"));}
		msg.pass(product + " is displayed hence process complete\n");

	}

	public void userVerifyRegistrationForExam(String product) {
		Assert.assertTrue(isElementDisplayed("text_lineItems", product.replaceAll("/20", "/")),
				msg.failForAssert(product + " is not displayed hence process incomplete\n"));
		msg.pass(product + " is displayed hence process complete\n");
		msg.actions("user verify registration for exam completes\n");

	}

	public void userVerifySubstitutionForExam(String product) {
		Assert.assertTrue(isElementDisplayed("text_lineItems", product),
				msg.failForAssert(product + " is not displayed hence process incomplete\n"));
		msg.pass(product + " is displayed hence process complete\n");

	}

	public void userNavigatesToCustomerProfile() {
		isElementDisplayed("btn_edit");
		element("btn_edit").click();
		switchToFrame(element("frame_eventRegSubForm"));
		isElementDisplayed("text_onEdit_userDisplayName");
		String userName = element("text_onEdit_userDisplayName").getAttribute("value");
		userClickOn("btn_event_registration_actions", "Save");
		switchToDefaultContent();
		browserSpecificHolds();
		waitForLoaderToDisappear();
		isElementDisplayed("link_userName", userName);
		clickUsingXpathInJavaScriptExecutor(element("link_userName", userName), userName);
		msg.actions("user navigates to customer profile completes\n");
	}
	
	public void userVerifyRefundForExam(String product) {
		Assert.assertTrue(isElementDisplayed("text_lineItems", product),
				msg.failForAssert(product + " is not displayed hence process incomplete\n"));
		msg.pass(product + " is displayed hence process complete\n");

	}

	public void verifyUserOnInvoiceProfile() {
		verifyPageTitleContains();
		msg.log(element("link_batchInvoiceProf").getText()+" is the Invoice Batch Profile\n");
		msg.actions("verified user on invoice profile\n");
	}

	
	public void userVerifyInvoiceMailingAddress(){
		Assert.assertEquals(element("text_mailingLabel").getText(), keyValue(dataStorage,"mailingLabel"),msg.failForAssert(element("text_mailingLabel").getText()+" is the actual and is "+keyValue(dataStorage,"mailingLabel")+" expected\n"));
		msg.pass("Invoice generated is verified\n");
	}
	
	public void userVerifyEventInvoiceMailingAddress(){
		Assert.assertEquals(element("text_mailingLabel").getText(), keyValue(dataStorage,"Event Registrant Info"));
		msg.pass("Invoice is verified\n");
	}
	
	
	public void userClickVoidInvoiceProfileIcon(){
		isElementDisplayed("icon_voidInvoice");
		element("icon_voidInvoice").click();
		msg.actions("Voide invoice icon is clicked\n");
	}
	
	public void userProvideRefundDetails(String productName){
		switchToFrame(element("frame_invoiceProfile"));
		int count =0;
		boolean flag=true;
		String actionProduct="";
		for(WebElement product:elements("dropdDown_option_actionProductVoidInvoice",productName)){
			flag=false;
			if(product.getText().contains("Void with Adjustment")){
				flag=true;
				actionProduct=product.getText();
				break;}
		}	

		if(!flag){
			for(WebElement product:elements("dropdDown_option_actionProductVoidInvoice",productName)){
				flag=false;
				if(product.getText().contains("Void")){
					flag=true;
					actionProduct=product.getText();

					break;}
			}
		}
				
				selectProvidedTextFromDropDown(element("dropdDown_actionProductVoidInvoice",productName),actionProduct);
				msg.actions(actionProduct+" is action selected from the dropdown\n");
		
				actionProduct="";
				
				for(WebElement product:elements("dropDown_option_actionPaymentProductVoidInvoice",productName)){
					flag=false;
					if(product.getText().contains("Create Credit")){
						flag=true;
						actionProduct=product.getText();

						break;}
				}	
				if(!flag){
					for(WebElement product:elements("dropDown_option_actionPaymentProductVoidInvoice",productName)){
						flag=false;
						if(product.getText().contains("Void")){
							flag=true;
							actionProduct=product.getText();

							break;}
					}
				}		
				
				selectProvidedTextFromDropDown(element("dropDown_actionPaymentProductVoidInvoice",productName),actionProduct);
				msg.actions(actionProduct+" is action selected from the dropdown\n");
				
				msg.log(elements("list_text_productPriceVoidInvoice").get(count).getText()+"is the amount refunded for "+productName+"\n");
				dataStorage.put("Refund_"+productName,elements("list_text_productPriceVoidInvoice").get(count).getText());
			
		
		Assert.assertTrue(flag, msg.failForAssert("Product is not found on void invoice page\n"));
		msg.pass("Refund details are provided\n");
		switchToDefaultContent();

	}
	
	public void selectBatchForVoidInvoice(String batch){
		switchToFrame(element("frame_invoiceProfile"));
		msg.pass("Void Invoice wizard windows appeared\n");
		boolean flag=false;
		for(WebElement batchRefund: elements("dropdown_batchOptionsVoidInvoice")){
			flag=false;
			if(batchRefund.getText().contains(batch)){
				batch=batchRefund.getText();
			flag=true;
			break;
			}
		}
		Assert.assertTrue(flag, msg.failForAssert("Batch containing word "+batch+" not found \n"));
		if(flag){
		flag=selectBatch(batch);
		Assert.assertTrue(flag, msg.failForAssert(batch +" Batch not selected from dropdown \n"));
		}
		switchToDefaultContent();
	}
	
	public void userSaveRefundDetail(){
		switchToFrame(element("frame_invoiceProfile"));
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();


	}
}
