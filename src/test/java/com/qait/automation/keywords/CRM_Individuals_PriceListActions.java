package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class CRM_Individuals_PriceListActions extends GeneralActionsPage{

	public CRM_Individuals_PriceListActions(WebDriver driver) {
		super(driver, "CRM_Individuals_PriceListActions");
	}
	
	/**
	 * method to select the discount product from the list of discounts
	 * @param DiscntProdName - name of the discount product required to be selected
	 */
	public void userSelectDiscountProductFromTheList(String DiscntProdName){
		switchToFrame(element("frame_centralizedOrderEntry"));
		int count=0;
		boolean flag=false;
		for(WebElement customer:elements("list_text_DiscountName")){
			if(customer.getText().contains(DiscntProdName)){
				elements("list_text_DiscountGoToLink").get(count).click();
				msg.actions(DiscntProdName+" customer profile is navigated from customer list\n");
				flag=true;
				break;
			}count++;
		}
		if(flag){
			Assert.assertTrue(flag,msg.failForAssert(DiscntProdName+"is not found from the list\n"));
		}
		msg.actions("Discount product is selected\n");
	}

}
