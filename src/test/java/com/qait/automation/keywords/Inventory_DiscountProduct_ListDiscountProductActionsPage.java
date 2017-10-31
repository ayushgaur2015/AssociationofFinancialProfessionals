package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.utils.msg;

public class Inventory_DiscountProduct_ListDiscountProductActionsPage extends GeneralActionsPage{

	public Inventory_DiscountProduct_ListDiscountProductActionsPage(WebDriver driver) {
		super(driver, "Inventory_DiscountProduct_ListDiscountProductActionsPage");
	}

	/**
	 * method to get the row index value
	 * @param rowValue - value to be matched for getting a particular row
	 * @return int row index value
	 */
	public int getRow(String rowValue){
		int rowCount=1;
		wait.hardWait(2);
		for(WebElement row : elements("list_queryResults")){
			if(row.getText().contains(rowValue)){
				return rowCount;
			}
			rowCount++;
		}
		return -1;
	}
	
	/**
	 * user select a particular record based on the index position
	 * @param queryResult - string value of the index 
	 */
	public void userSelectsRecord(String queryResult){
		String row = String.valueOf(queryResult);
		isElementDisplayed("link_query",row);
		element("link_query",row).click();
		msg.pass(queryResult+" row is selected\n");
	}
	
	/**
	 * method to select record on the basis of presence required by user
	 * @param present - boolean value ot check if present
	 * @param queryResult - string type row index 
	 */
	public void userSelectsRecord(boolean present, String queryResult){
		if(present){
			String row = String.valueOf(queryResult);
		
		isElementDisplayed("link_query",row);
		element("link_query",row).click();
		msg.pass(queryResult+" row is selected\n");
		}
	}
	
}
