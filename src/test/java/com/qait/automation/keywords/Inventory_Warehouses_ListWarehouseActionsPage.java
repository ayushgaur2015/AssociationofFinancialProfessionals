package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Inventory_Warehouses_ListWarehouseActionsPage extends GeneralActionsPage{

	public Inventory_Warehouses_ListWarehouseActionsPage(WebDriver driver) {
		super(driver, "Inventory_Warehouses_ListWarehouseActionsPage");
	}

	/**
	 * method to select warehouse list fromo the list of warehouse
	 * @param warehouse - user required warehouse from the list of warehouse
	 */
	public void userFromTheWarehouseListSelectWarehouse_(String warehouse){
		int count=0;
		boolean flag=false;
		for(WebElement warehouseName:elements("list_text_warehouseName")){
			if(warehouseName.getText().equals(warehouse)){
				flag=true;
				break;
			}
			count++;
		}
		Assert.assertTrue(flag,msg.failForAssert(warehouse+" warehouse is not present\n"));
		msg.pass(warehouse+" named warehouse is present\n");
		elements("list_link_warehouseProfile").get(count).click();
	}
	
}
