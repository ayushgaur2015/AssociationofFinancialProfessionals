package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class CEU_CreditProfileActionsPage extends GeneralActionsPage{

	public CEU_CreditProfileActionsPage(WebDriver driver) {
		super(driver, "CEU_CreditProfileActionsPage");
	}
	
	/**
	 * method to verify ceu credit amnt details
	 * @param amnt - user entered date value for creating ceu credit date
	 */
	public void userVerifyCeuCreditAmnt(String amnt){
		Assert.assertTrue(element("text_creditAmnt").getText().contains(keyValue(dataStorage,amnt)),msg.failForAssert("credit amount is invalid\n") );		
		msg.pass("credit amount verified\n");
	}

	/**
	 * method to verify ceu credit activity date
	 * @param date - user entered date value for creating ceu credit date
	 */
	public void userVerifyCreditActivityDate(String date){
		String dateEntered[] = keyValue(dataStorage,date).split("/");
		String month = dateEntered[0].replace("0", "");
		String userDate = dateEntered[1].replace("0", "");
		Assert.assertTrue(element("text_activityDate").getText().contains(month+"/"+userDate+"/"+dateEntered[2]),msg.failForAssert("credit date is invalid\n") );		
		msg.pass("activity date verified\n");
	}
	
	/**
	 * method to verify ceu credit comments
	 * @param comments - user entered comments value for creating ceu credit date
	 */
	public void userVerifyCreditComments(String comments){
		Assert.assertTrue(element("text_comments").getText().contains(keyValue(dataStorage,comments)),msg.failForAssert(element("text_comments").getText()+"not matched" +keyValue(dataStorage,comments)+"credit comment is invalid\n") );
		msg.pass("comment verified\n");
	}

	/**
	 * workflow user edit the already created ceu credit 
	 */
	public void userEditCeuCredit(String optionProduct, String optionCategory){
		clickCeuCreditEditButton();
		switchToFrame(element("frame_ceuCreditProfile"));
		editCreditDetails(optionProduct);
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
		waitForLoaderToDisappear();
		wait.hardWait(2);
		editCreditCategory(optionCategory);
	}
	
	/**
	 * method to click ceu credit edit button
	 */
	public void clickCeuCreditEditButton(){
		isElementDisplayed("btn_edit");
		element("btn_edit").click();
		msg.actions("ceu credit edit button is clicked\n");
	}
	
	/**
	 * method to edit credit details
	 * @param optionProduct
	 */
	public void editCreditDetails(String optionProduct){
		selectProvidedTextFromDropDown(element("dropdown_product"), optionProduct);
		msg.actions(optionProduct+" is selected from the product dropdown\n");
	}
	
	/**
	 * method to edit credit category
	 * @param optionCategory
	 */
	public void editCreditCategory(String optionCategory){
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropdown_category"), optionCategory);
		msg.actions(" is selected from credit category\n");
	}
}
