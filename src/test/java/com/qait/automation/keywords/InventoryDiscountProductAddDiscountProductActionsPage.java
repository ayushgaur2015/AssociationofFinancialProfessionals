package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;


public class InventoryDiscountProductAddDiscountProductActionsPage extends GeneralActionsPage {

	public InventoryDiscountProductAddDiscountProductActionsPage(WebDriver driver) {
		super(driver, "InventoryDiscountProductAddDiscountProductActionsPage");
		
	}
	
	public void verifyUserOnInventory_DiscountProduct_AddDiscountProductPage(){
		verifyPageTitleContains();
	}

	public void enterProductName(String prodName){
		enterDetails("productName", prodName);
	}
	
	public void enterPrice_Rate(String priceRate){
		enterDetails("price_rate",priceRate);
	}
	
	public void enterRevenueAccount(String account){
		selectProvidedTextFromDropDown(element("dropDown_revenueAccount"), account);
	}
	
	public void enterEwebCode(String ewebCode){
		enterDetails("ewebCode",ewebCode);
	}
	
	public void clickOn(String button){
		element("btn_save_cancel",button).click();
	}
	
	public void user_CreateDiscountProduct(String prodName, String priceRate,String account,String eWebCode){
		enterProductName(prodName);
		enterPrice_Rate(priceRate);
		enterRevenueAccount(account);	
		enterEwebCode(eWebCode);
		clickOn("Save");
		
	}
	
	public void user_AddDiscountProduct(String editBarHeading, String lookupcode, String currencytype){
		switchToFrame(element("frame_discountProduct"));
		enterDetails("priceLookupCode", lookupcode);
		selectProvidedTextFromDropDown(element("dropDown_currency"), currencytype);
		element("btn_save_cancel","Save").click();
		switchToDefaultContent();
	}
}
