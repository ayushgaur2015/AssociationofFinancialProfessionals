package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class EventsRegistrantsListActionsPage extends GeneralActionsPage{

	public EventsRegistrantsListActionsPage(WebDriver driver) {
		super(driver, "EventsRegistrantsListActionsPage");
	}
	
	/**
	 * method to verify user on event registrant list page
	 * @return int type size of total no of pages
	 */
	public void verifyUserOnEventsRegistrantsListPage(){
		verifyPageTitleContains();
		msg.actions("verified user on event registrants list page\n");
	}
	
	/**
	 * method to select random registrant from the list of existing registrant
	 * @param pageLimit - total number of pages available 
	 * @param recordLimit - total numbr of record per page
	 * @return string value of name of record
	 */
	public String userSelectsRandomRegistrantFromTheExistingList(){
		boolean flag=false;
		int size=0;
		String name="";
		String pageNo = generateRandomUniqueOutput((elements("list_pageTabExcptNext").size()-1),2);
		String individualRow = null;	
		int row=0;

		msg.log(pageNo+" is selected as random Page number\n");
		isElementDisplayed("link_pageTabExcptNext",pageNo);
		element("link_pageTabExcptNext",pageNo).click();
		msg.actions(pageNo+"is clicked\n");
		while(size<elements("list_individualListName").size()){
			 individualRow=generateRandomUniqueOutput(elements("list_individualListName").size()-1,3);
			 row=Integer.parseInt(individualRow)+2;
			 msg.log(individualRow+" is selected as random Row\n");
			 if(!element("text_individualListName",individualRow).getText().isEmpty() && !element("text_individualListAddress",individualRow).getText().isEmpty()){
					flag=true;
					break;
				}
				size++;
			}
			if(flag){
				name = element("text_individualListName",row).getText();
				msg.log(element("text_individualListName",row).getText()+" user selected as random individual \n");
				clickUsingXpathInJavaScriptExecutor(element("link_individualProfileGotoLink",individualRow));
			}
			
			if(!flag){
				userSelectsRandomRegistrantFromTheExistingList();
			}
			
			msg.actions("User selected random record from existing list\n");
			return name;
	}
		
}
