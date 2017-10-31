package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;


public class PublicationsActionsPage extends GeneralActionsPage {

	static int pageNo = 0;
	
	
	public PublicationsActionsPage(WebDriver driver) {
		super(driver, "PublicationsActionsPage");
	}

	/**
	 * method to click on search icon
	 */
	public void clickOnIconSearch() {
		waitForLoaderToDisappear();
		isElementDisplayed("icon_searchPublicationDisplayName");
		element("icon_searchPublicationDisplayName").click();
		msg.actions("Clicked on search icon publication display name\n");
	}

	/**
	 * method to select publication display name
	 * @param name - name of the publication to be selected
	 */
	public void selectPublicationDisplayName(String name) {
		
		boolean flag = false;
		int page = elements("list_link_pagePublicationDisplayName").size();
		switchToDefaultContent();
		switchToFrame("iframe1");
		int count = 0;
		while (!flag) {
			count = 0;
			for (WebElement displayName : elements("list_textgotoPublicationDisplayName")) {
				if (displayName.getText().startsWith(name)) {
					flag = true;
					break;
				   } 
				count++;}
			   if(flag){
				 break;
			    }
			      if(!flag) {
					clickUsingXpathInJavaScriptExecutor(elements("list_link_pagePublicationDisplayName").get(pageNo));
					pageNo++;
					selectPublicationDisplayName(name);
	                break;
				}
				 if(pageNo < page-2){
					 break;
				 }
				
		}
			if (flag) {
				Assert.assertTrue(flag,msg.failForAssert(name+"====Publication name is not found\n"));
				msg.pass(name+ " publication name is found\n");
				clickUsingXpathInJavaScriptExecutor(elements("list_link_gotoPublicationDisplayName").get(count));
				msg.actions("Select display name of the publication product is complete\n");
		}
	}	

	/**
	 * workflow to select AFP publication product
	 * @param name - name of the publication product to be selected
	 */
	public void userSelectAFP_PublicationsProduct(String name) {
		switchToFrame("iframe1");
		clickOnIconSearch();
		switchToDefaultContent();
		switchToFrame("iframe1");
		selectPublicationDisplayName(name);
//		switchToDefaultContent();
//		switchToFrame("iframe1");
		waitForLoaderToDisappear();
		click(element("btn_publicationDisplayNameList", "Save & Finish"));
		msg.log("clicked on Save & Finish button\n");
		switchToDefaultContent();
	}
}
