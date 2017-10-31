package com.qait.automation.keywords;

import java.util.List;

import org.openqa.selenium.WebDriver;
import com.qait.automation.utils.msg;

public class InventoryActivitiesOrderFulfillmentActionsPage extends GeneralActionsPage {

	public InventoryActivitiesOrderFulfillmentActionsPage(WebDriver driver){
		super(driver, "InventoryActivitiesOrderFulfillmentActionsPage");
	
	}

	public void verifyUserOnInventoryActivitiesOrderFulfillmentPage(){
		verifyPageTitleContains();
	}
	
	public void entersStartDate_EndDate(String startDate, String endDate){
		enterDetails("startDate", startDate);
		enterDetails("endDate", endDate);
		msg.actions("Start date and end date have been entered\n");
		}
	
	public void userClickOn(String value){
		element("inp_search_request_cancel",value).click();
	}
	
	public void selectRequiredWarehouse(String warehouse){
		int searchResultsSize = elements("list_OrdersToFullfill").size();
		int invoiceSearchResultsSize = elements("list_invoiceOrderToFulfill").size();
		boolean flag=false;
		
		if(element("chkBox_checkUncheckAll").getAttribute("checked")=="checked"){
				flag = true;
				msg.log("check/uncheck box is checked\n");
		}	
		
		for(int size=1;size<=searchResultsSize;size++){
			
				if(element("dropDown_childViewWarehouse",size).getText()!=warehouse){
					if(flag==true){ 	element("chkBox_orderFulfill",size).click();
					}
				}
				else{ 	if(element("dropDown_childViewAddress",size).getText()==""){
						}	
					
					   if(flag==true){ element("chkBox_orderFulfill",size).click();
					}
					else{element("chkBox_orderFulfill",size).click();
					}
				}	
		}
   }
	
	
	public void user_EntersDateAndClickOnSearch(String startDate, String endDate){
		entersStartDate_EndDate(startDate,endDate);
		userClickOn("Search");
	}
	
	public List<String> storeDetailsOfCheckedOrders(){
		List<String>details=null;
		
		return details;
	}
		
	public List<String> user_SelectsRequiredWarehouseAndClickOn_Proceed(String warehouse,String value){
		selectRequiredWarehouse(warehouse);
		List<String>details = storeDetailsOfCheckedOrders();
		userClickOn(value);
		return details;
	}
	
	public void verifyUserOnInventoryActivityOrderFulfillmentPage(){
		verifyPageTitleContains();
	}
}
