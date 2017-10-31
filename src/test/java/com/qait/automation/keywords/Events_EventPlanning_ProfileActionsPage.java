package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Events_EventPlanning_ProfileActionsPage extends GeneralActionsPage {

	static int page=1;
	
	public Events_EventPlanning_ProfileActionsPage(WebDriver driver) {
		super(driver, "Events_EventPlanning_ProfileActionsPage");
		
	}
	
	public void userVerifyEventTitle(String event){
		isElementDisplayed("text_eventProfileName");
		Assert.assertTrue(element("text_eventProfileName").getText().contains(event),msg.failForAssert(element("text_eventProfileName").getText()+" is the actual and "+event+" is the expected\n"));
		msg.pass("event profile title is verified\n");
	}
	
	public void userVerifyEventStart_endDate(){
		isElementDisplayed("text_eventStartDate");
		Assert.assertTrue(element("text_eventStartDate").getText().contains(keyValue(dataStorage, "startDate")),msg.failForAssert(element("text_eventStartDate").getText()+" is the actual and "+keyValue(dataStorage, "startDate")+" is the expected\n"));
		msg.pass("Start date is verified\n");
		isElementDisplayed("text_eventStartDate");
		Assert.assertTrue(element("text_eventEndDate").getText().contains(keyValue(dataStorage, "endDate")),msg.failForAssert(element("text_eventEndDate").getText()+" is the actual and "+keyValue(dataStorage, "startDate")+" is the expected\n"));
		msg.pass("end date is verified\n");
	
	}
	
	
	public void userVerifyEventCodeType(String eventCode){
		isElementDisplayed("text_eventCodeType");
		Assert.assertTrue(element("text_eventCodeType").getText().contains(eventCode),msg.failForAssert(element("text_eventCodeType").getText()+" event code not matched with the event displayed\n"));
		msg.pass("verified event code type of event\n");
	}
	
	public void userGotoRandomSessionProfile(){
		wait.hardWait(5);
		String linkIndex=generateRandomOutput(9);
		String linkPage=generateRandomUniqueOutput(9);
		isElementDisplayed("link_pageNoSessionView",linkPage);
		element("link_pageNoSessionView",linkPage).click();
		wait.hardWait(5);
		msg.actions(linkPage+" is the random page no under sessions view\n");
		System.out.println(linkIndex);
		System.out.println(Integer.parseInt(linkIndex)-1);
		msg.log(elements("list_text_Session").get(Integer.parseInt(linkIndex)-1).getText().replace("’","'")+" is the event session\n");
		dataStorage.put("Session", elements("list_text_Session").get(Integer.parseInt(linkIndex)-1).getText());
		waitForLoaderToDisappear();
		wait.hardWait(3);
		element("link_goto",linkIndex).click();
		msg.actions(linkIndex+" is the random session\n");
	}	
	
	public boolean userGotoREquiredSEssionProfile(String session){
		int count=0;
		boolean flag=false;
		
		for(WebElement sessions:elements("list_text_Session")){
			if(sessions.getText().contains(session)){
				element("link_goto",count+1).click();
				flag=true;
				return flag;
			}
			count++;
			if(count>=elements("list_text_Session").size()){
				if(page<elements("list_pageNoSessionView").size()){
				page++;
				element("link_pageNoSessionView",page).click();
				wait.hardWait(2);
				flag=userGotoREquiredSEssionProfile(session);
				}
			}
		}
		Assert.assertTrue(flag,msg.failForAssert("requires sessionis not found\n"));
		msg.pass("required session is found\n");
		return true;
	}
	
	public void userExpandProductFeesUsingProductCode(String prdCode){
		isElementDisplayed("expand_productCodeRegistrationFees",prdCode);
		element("expand_productCodeRegistrationFees",prdCode).click();
		msg.actions("user expand product fees using product code\n");
	}
	
	public void userVerifyEventRegistrationFeesUsingRegistrationPriceCode(String prcCode){
		Assert.assertTrue(isElementDisplayed("text_priceCodePrice",prcCode),msg.failForAssert("registration fee code is not displayed\n"));
		Assert.assertEquals(element("text_priceCodePrice", prcCode).getText().trim(),keyValue(dataStorage, "prdPrice"));
		
	}
	
	public void userVerifyUncheckedPriceDisplayNameNotPresent(){
			checkIfElementNotDisplayed("text_priceCode",keyValue(dataStorage, "uncheckDisplayCode"));
	}
	
	public void userClickEditProfileButton(){
		isElementDisplayed("btn_EditEventInfo");
		element("btn_EditEventInfo").click();
		switchToFrame(element("frame_eventPlanningProfile"));
	}
	
	public void userPerformOperation_(String operation){
		userClickOn("btn_saveCancel", operation);
		handleAlert();
		switchToDefaultContent();
	}
}
