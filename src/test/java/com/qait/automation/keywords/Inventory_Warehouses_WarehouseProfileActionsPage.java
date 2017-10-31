package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Inventory_Warehouses_WarehouseProfileActionsPage extends GeneralActionsPage {

	static int count=0;
	public Inventory_Warehouses_WarehouseProfileActionsPage(WebDriver driver) {
		super(driver, "Inventory_Warehouses_WarehouseProfileActionsPage");
	}

	public void userVerifyProfileDetailsOfWarehouse_(String warehouse){
		Assert.assertEquals(element("").getText(), warehouse);
		msg.pass("Profile name of the warehouse is verified\n");
	}
	
	public void userAddInventoryIntoWarehouse(String item, String location){
		clickOnAddProductIcon();
		switchToFrame(element("frame_addInventoryWarehouse"));
		selectInventoryItemFromDropDown(item);
		enterInventoryLocation(location);
		selectchkBoxPrimaryLocation();
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
	}
	
	public void userUpdatePhysicalInventoryAccountOfProduct(String inventoryProduct,String quantity,String unitCost){
		clickPhysicalInventoryAccount();
		switchToFrame(element("frame_addInventoryWarehouse"));
		verifyInventory_Location(inventoryProduct);
		enterOnHandQuantity_UnitCost(inventoryProduct,quantity,unitCost);
		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();

	}

	public void clickOnAddProductIcon(){
		isElementDisplayed("icon_addProduct");
		element("icon_addProduct").click();
		msg.actions("Add product icon is clicked\n");
	}
	
	public void selectInventoryItemFromDropDown(String item){
		selectProvidedTextFromDropDown(element("dropdown_inventoryItem"), item);
		msg.actions(item+ " is selected from the inventory item \n ");
	}
	
	public void enterInventoryLocation(String location){
		enterDetails("inventoryLocation", location);
		dataStorage.put("inventoryLocation", location);
	}
	
	public void selectchkBoxPrimaryLocation(){
		checkCheckbox(element("chkbox_primaryLocation"));
		msg.actions("primary location checkbox is selected\n");
	}
	
	public void clickPhysicalInventoryAccount(){
		dynamicWait(5, "link_physicalInventoryCount");
		isElementDisplayed("link_physicalInventoryCount");
		element("link_physicalInventoryCount").click();
		msg.actions("physical inventory button is clicked\n");
		
	}
	
	public void verifyInventory_Location(String inventoryProduct){
		int count=0;
		boolean flag=false;
		waitForLoaderToDisappear();
		for(WebElement product:elements("list_text_productNamePhysicalInventoryCount")){
			if(product.getText().contains(inventoryProduct)){
				if(elements("list_inp_LocationPhysicalInventoryCount").get(count).getAttribute("value").contains(keyValue(dataStorage, "inventoryLocation"))){
					flag=true;
					break;
				}
			}
		count++;}
		Assert.assertTrue(flag, msg.failForAssert(elements("list_inp_LocationPhysicalInventoryCount").get(count).getText()+ " is actual"+keyValue(dataStorage, "inventoryLocation")+" Location of inventory is not verified\n"));
		msg.pass("Location of inventory is verified\n");
	}
	
	public void enterOnHandQuantity_UnitCost(String inventoryProduct,String quantity,String unitCost){
		int count=0;
		boolean flag=false;
		for(WebElement product:elements("list_text_productNamePhysicalInventoryCount")){
			if(product.getText().contains(inventoryProduct)){
					flag=true;
					break;				
					}
		count++;}
	
	if(flag){
		elements("list_inp_onHandQuantityPhysicalInventoryCount").get(count).clear();
		elements("list_inp_onHandQuantityPhysicalInventoryCount").get(count).sendKeys(quantity);
		msg.actions(quantity+" is entered as inventory quantity\n");
		elements("list_inpUnitCostPhysicalInventoryCount").get(count).clear();
		elements("list_inpUnitCostPhysicalInventoryCount").get(count).sendKeys(unitCost);
		msg.actions(unitCost+" is entered as inventory quantity\n");

	}
	else{
		msg.fail("Inventory product is not found\n");
	}
		
	}
	
	public boolean userVerifyInventoryAddedIntoWarehouse(){
		
		int product=0;
		boolean flag=false;
		expandview("products");

		for(WebElement location: elements("list_text_productLocationProductView")){
			if(location.getText().contains(keyValue(dataStorage, "inventoryLocation"))){
				flag=true;
				return true;
			}product++;
			if(product>=elements("list_text_productLocationProductView").size()){
				if(count<elements("list_link_pageProduct").size()){
					count++;	
				elements("list_link_pageProduct").get(count).click();
				flag=userVerifyInventoryAddedIntoWarehouse();
			}
			}
		}
			Assert.assertTrue(flag,msg.failForAssert("Inventory is not added into the warehouse\n"));
			msg.pass("Inventory is added\n");
			return true;
	}
}
