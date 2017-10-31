package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.DateUtil;
import com.qait.automation.utils.msg;

public class EventCopyWizardActionsPage extends GeneralActionsPage {
	static String startDate="";
	static String endDAte= "";
	
	public EventCopyWizardActionsPage(WebDriver driver) {
		super(driver, "EventCopyWizardActionsPage");
	}

	/**
	 * method to select event to be copied
	 * @param event - name of the event to be copied
	 */
	public void selectCopyEvent(String event){
		selectProvidedTextFromDropDown(element("dropdown_copyFromEvent"), event);
		msg.actions(event+ " is selected as copy event\n");
	}
	
	/**
	 * workflow to select copy event to create new event
	 * @param event - name of the event to be copied
	 * @param newEvent - name of the new event
	 * @param newCode - code of the new event
	 */
	public void userSelectCopyEvent_CreateNewEvent(String event,String newEvent, String newCode){
		startDate = DateUtil.getAnyDateForType("M/d/YYYY",1, "year");
		endDAte = DateUtil.getAnyDateForType("M/d/YYYY", 2, "year");
		dataStorage.put("startDate", startDate);
		dataStorage.put("endDate", endDAte);
		selectCopyEvent(event);
		enterDetails("newEventName", newEvent);
		enterDetails("newEventCode", newCode);
		enterDetails("startDate", startDate);
		enterDetails("endDate", endDAte);
		userClickOn("btn_saveCancel", " Next »");
		msg.actions("user select copy event and create new event completes\n");
	}

	/**
	 * workflow to edit product name and price
	 * @param prdName - name of the product to be edited
	 * @param prdCode - new value of product code
	 * @param prcDisplayName - new display name
	 * @param prcCode - new price code 
	 */
	public void userEditProductName_Price(String prdName,String prdCode, String prcDisplayName, String prcCode){ 
	enterDetails("prdName", prdName);
	enterDetails("prdCode", prdCode);
	enterDetails("prdStartDate", startDate);
	enterDetails("prdEndDate", endDAte);
	element("img_expandPrice").click();
	msg.actions("expanded the price view\n");
	enterDetails("priceDisplayName", prcDisplayName);
	dataStorage.put("prdPrice", element("inp_priceDisplayPrice").getAttribute("value"));
	enterDetails("priceDisplayPriceCode", prcCode);
	enterDetails("priceDisplayStartDate", startDate);
	enterDetails("priceDisplayEndDate", endDAte);
	userUncheckRandomRegistrationPriceDisplayName();
	userClickOn("btn_saveCancel", " Next »");
	msg.actions("User edit product name and price completes\n");
	}
	
	/**
	 * method to edit session title and price
	 * @param sessTitle - title of the session to be edited
	 * @param sessCode - new session code value
	 * @param SessFeeName - new session fee name
	 * @param SessFeeCode - new session fee code
	 */
	public void userEditSessionTitle_Price(String sessTitle,String sessCode, String SessFeeName, String SessFeeCode){ 
		enterDetails("sessionTitle", sessTitle);
		enterDetails("sessionCode", sessCode);
		enterDetails("sessionStartDate", startDate);
		enterDetails("sessionEndDate", endDAte);
		element("img_expandSessionFee").click();
		msg.actions("expanded the fee view\n");
		enterDetails("sessionFeePrdName", SessFeeName);
		enterDetails("sessionFeePrdCode", SessFeeCode);
		userClickOn("btn_saveCancel", " Next »");
		msg.actions("User edit session title and price completes\n");
	}
	
	/**
	 * method user goto created event link
	 */
	public void userGotoCreatedEventLink(){
		userClickOn("btn_saveCancel", " Next »");
		userClickOn("btn_saveCancel", " Save ");
		isElementDisplayed("link_eventProfile");
		element("link_eventProfile").click();
		msg.actions("User goto created event link completes\n");
	}
	
	/**
	 * method to uncheck random registratation price display name
	 */
	public void userUncheckRandomRegistrationPriceDisplayName(){
		int check= generateRandomInteger(elements("list_chkBox_priceDisplayName").size()-1, 2);
		msg.log(elements("list_inp_priceDisplayPriceCode").get(check).getAttribute("value")+"  is the Registration fee unchecked\n");
		dataStorage.put("uncheckDisplayCode", elements("list_inp_priceDisplayPriceCode").get(check).getAttribute("value"));
		clickUsingXpathInJavaScriptExecutor(elements("list_chkBox_priceDisplayName").get(check));
		msg.actions("Use uncheck random registrant price display name completes/n");
	}	
}
