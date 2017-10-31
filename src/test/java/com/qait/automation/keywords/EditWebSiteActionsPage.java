package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.utils.msg;

public class EditWebSiteActionsPage extends GeneralActionsPage {

	
	public  int pages=0;
	public static int pageEweb=0;
	
	public EditWebSiteActionsPage(WebDriver driver) {
		super(driver, "EditWebSiteActionsPage");
	}

	/**
	 * method to select the section from the dropdown
	 * @param section - user required section
	 */
	public void selectSectionFromDropDown(String section){
		selectProvidedTextFromDropDown(element("dropDown_sections"), section);
		msg.actions(section+"is selected from the sections\n");
	}
	
	/**
	 * method to search page link and click on it
	 * @param page - user required page link
	 */
	public void searchPageLinkAndClick(String page){
		int count=1;
		int pagesDisplayed=elements("list_link_pagetitle").size();
		msg.log(pagesDisplayed+" is the total number of page displayed");
		waitForLoaderToDisappear();
		wait.hardWait(2);
		for(WebElement pageListWebsite: elements("list_link_pagetitle")){
			if(pageListWebsite.getText().equals(page)){
				pageListWebsite.click();
				msg.actions(page+" link is clicked \n");
				break;
			}
			if(count>=pagesDisplayed){
				pages++;
				elements("list_pageLink").get(pages).click();
				msg.log(pages+" page number is clicked\n");
				searchPageLinkAndClick(page);
			}count++;
		}
	}
	
	/**
	 * method to get product eweb Url 
	 * @param productName -  name of the product 
	 * @return String eWeb URL or null in case not found
	 */
	public String getProduct_eWebLink(String productName){
		int products=0;
		int i=0;
		String expected_eWebUrl="";
		switchToDefaultContent();
		switchToFrame(element("frame"));
		int length = elements("text_webSite").size();
		int pageLength= elements("list_link_pageNUmberWebsite").size();
		for(WebElement link: elements("text_webSite")){
			if(link.getText().contains(productName)){
				String eWebUrl=elements("link_webSite").get(products).getAttribute("href").replaceAll(" ", "%20");
				msg.log(eWebUrl+" is the actual eWebUrl\n");
				String actual_eWebUrl[] = eWebUrl.split("&");
				while(i<actual_eWebUrl.length){
					if(!actual_eWebUrl[i].contains("ivd_cst_key")){
						expected_eWebUrl+=actual_eWebUrl[i]+"&";
					}
					else{
						expected_eWebUrl+=actual_eWebUrl[i+1];
						break;
					}
				i++;
				
				}
				msg.log(expected_eWebUrl+" is the generated eweb url\n");
				return expected_eWebUrl;
			}
			products++;
			if(products>=length){
				if(pageEweb<=pageLength){
					pageEweb++;
					elements("list_link_pageNUmberWebsite").get(pageEweb).click();
					
					return getProduct_eWebLink(productName);
				    }
				else{
					return null;
				}
				}
		}
		return null;
	}
}
