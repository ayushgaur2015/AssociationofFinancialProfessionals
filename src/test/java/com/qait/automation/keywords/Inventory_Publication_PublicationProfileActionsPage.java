package com.qait.automation.keywords;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

public class Inventory_Publication_PublicationProfileActionsPage extends GeneralActionsPage {

	public static int publicationProduct = 0;
	public static int publicationSubProduct = 0;

	public Inventory_Publication_PublicationProfileActionsPage(WebDriver driver) {
		super(driver, "Inventory_Publication_PublicationProfileActionsPage");
	}

	public void FillDetailsToAddPublicationProduct_Edoc(String member,Map<String,Object> testCase) {

		if(member!=null&&member.equals("Member")){
			selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Member");
		}
		if(member!=null&&member.equals("Non-member")){
			selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Non-Member");

		}
		waitForLoaderToDisappear();
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropdown_arAcc"), keyValue(testCase,"a_rAccount"));
		msg.actions(keyValue(testCase,"a_rAccount")+" is selected as A/R account\n");
		selectProvidedTextFromDropDown(element("dropDown_revenueAcc"), keyValue(testCase,"revenueAccount"));
		msg.actions(keyValue(testCase,"revenueAccount")+" is selected as revenue account\n");
		selectProvidedTextFromDropDown(element("dropDown_liabilityAcc"), keyValue(testCase,"liabilityAccount"));
		msg.actions(keyValue(testCase,"liabilityAccount")+" is selected as liability account\n");
		selectProvidedTextFromDropDown(element("dropDown_returnAcc"), keyValue(testCase,"returnAccount"));
		msg.actions(keyValue(testCase,"returnAccount")+" is selected as return account\n");
		selectProvidedTextFromDropDown(element("dropDown_writeOffAcc"), keyValue(testCase,"writeOffAccount"));
		msg.actions(keyValue(testCase,"writeOffAccount")+" is selected as writeOff account\n");
		userClickOn("btn_saveCancel", "Save");
		


	}
	
	public void FillDetailsToAddPublicationProduct_Edoc(String member,String code, String price, Map<String,Object> testCase) {
		enterDetails("editParentPubPrcCode", code);
		enterDetails("prdPrice", price);
		checkCheckbox(element("chkBox_sellOnline"));
			if(member!=null&&member.equals("Member")){
				selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Member");
			}
			if(member!=null&&member.equals("Non-member")){
				selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Non-Member");

			}
			waitForLoaderToDisappear();
			wait.hardWait(3);
			selectProvidedTextFromDropDown(element("dropdown_arAcc"), keyValue(testCase,"a_rAccount"));
			msg.actions(keyValue(testCase,"a_rAccount")+" is selected as A/R account\n");
			selectProvidedTextFromDropDown(element("dropDown_revenueAcc"), keyValue(testCase,"revenueAccount"));
			msg.actions(keyValue(testCase,"revenueAccount")+" is selected as revenue account\n");
			selectProvidedTextFromDropDown(element("dropDown_liabilityAcc"), keyValue(testCase,"liabilityAccount"));
			msg.actions(keyValue(testCase,"liabilityAccount")+" is selected as liability account\n");
			selectProvidedTextFromDropDown(element("dropDown_returnAcc"), keyValue(testCase,"returnAccount"));
			msg.actions(keyValue(testCase,"returnAccount")+" is selected as return account\n");
			selectProvidedTextFromDropDown(element("dropDown_writeOffAcc"), keyValue(testCase,"writeOffAccount"));
			msg.actions(keyValue(testCase,"writeOffAccount")+" is selected as writeOff account\n");
			selectProvidedTextFromDropDown(element("dropDown_memberFlag"), member);
			msg.actions(member+" is selected from the member type dropdown\n");
			userClickOn("btn_saveCancel", "Save");
		}

	public void userEditAlreadyCreatedPublicationProduct(String prodCode, String price){
		elements("list_icon_editFolderRecord").get(publicationProduct).click();
		msg.actions(publicationProduct+ "icon edit for the parent folder record is clicked\n");
		switchToFrame(element("frame_addPub"));
		enterDetails("editParentPubPrcCode", prodCode);
		enterDetails("prdPrice", price);
		switchToDefaultContent();
		switchToFrame(element("frame_addPub"));
		isElementDisplayed("btn_saveCancel", "Save");
		clickUsingXpathInJavaScriptExecutor(element("btn_saveCancel", "Save"));
		msg.actions("Clicked on save button to save the changes\n");
		msg.actions("User edit already created publication completes\n");
		switchToDefaultContent();

	}
	
	public void userEditAlreadyCreatedChildOfParentPublicationProduct(String prodCode, String memberType){
		wait.hardWait(2);
		if (elements("list_icon_expandfolder").get(publicationProduct).getAttribute("alt").contains("expand")) {
			elements("list_icon_expandfolder").get(publicationProduct).click();
			msg.actions("Folder review is expanded\n");
		}
		elements("list_icon_editSubFodlerRecord").get(0).click();
		msg.actions("edit icon of sub folder record is clicked\n");
		switchToFrame(element("frame_addPub"));
		enterDetails("editChildPubPrcCode", prodCode);
		selectProvidedTextFromDropDown(element("dropDown_memberFlag"), memberType);
		msg.actions(memberType+" is selected from the member type dropdown\n");
		switchToDefaultContent();
		switchToFrame(element("frame_addPub"));
		clickUsingXpathInJavaScriptExecutor(element("btn_saveCancel", "Save"));
		msg.actions("Clicked on save button to save the changes\n");
		msg.actions("User edit already created publication child completes\n");

		switchToDefaultContent();
	}
	
	public void userAddPublicationProduct(String memberType,String prodCode,Map<String,Object> testCase) {
		waitForLoaderToDisappear();
		clickOnAddButton("publication price");
		switchToFrame(element("frame_addPub"));
		if(prodCode!=null&&!prodCode.equals(null)||prodCode!=" "){
			enterDetails("editParentPubPrcCode", prodCode);
		}
		FillDetailsToAddPublicationProduct_Edoc(memberType,testCase);
		switchToDefaultContent();

	}
	
	
	public void verifyPublicationProfileDetails() {
		wait.waitForPageToLoadCompletely();
		Assert.assertEquals(element("text_sellOnline").getText(), dataStorage.put("sellOnline", "Yes"),
				msg.failForAssert(element("text_sellOnline").getText()
						+ " value of sell online is not correct on publication profile\n "));
		msg.pass("Correct value of sell online is displayed on publication profile\n");
		Assert.assertEquals(element("text_webService").getText(), dataStorage.put("web", "Yes"),
				msg.failForAssert(element("text_webService").getText()
						+ " value of webService is not the correct value on publication \n"));
		msg.pass("Correct value of webSerivce is displayed on publication profile\n");
		msg.actions("Publication profile is verified\n");
	}

	public void userAddInventory() {
		selectProvidedTextFromDropDown(element("dropDown_warehouse"),
				YamlReader.getYamlValue("TestUserCreateEdocs.warehouse"));
		msg.actions(YamlReader.getYamlValue("TestUserCreateEdocs.warehouse") + " is selected as wareouse\n");
		enterDetails("warehouseLocation", YamlReader.getYamlValue("TestUserCreateEdocs.warehouseLocation"));
		userClickOn("btn_saveCancel", "Save");

	}

	public void userDeletePublicationProduct() {
		clickEditPublication();
		switchToFrame(element("frame_addPub"));
		onEditPublicationWindowClick_("Delete");
		handleAlert();
		wait.hardWait(3);
		msg.actions("user deletes publication product completes\n");
		switchToDefaultContent();

	}

	public void clickEditPublication() {
		isElementDisplayed("btn_editPublication");
		element("btn_editPublication").click();
		msg.actions("edit button on publication profile is clicked\n");
	}

	public void onEditPublicationWindowClick_(String action) {
		userClickOn("btn_saveCancel", action);

	}
	
	public void userAddProductShippingRegion(String shippingPrice, String shippingRegion){
		clickOnAddButton("publication shipping");
		switchToFrame(element("frame_addPub"));
		selectProvidedTextFromDropDown(element("dropdown_shippingProductPrice"), shippingPrice);
		msg.actions(shippingPrice+" is the shipping product price selected\n");
		selectProvidedTextFromDropDown(element("dropdown_shippingRegion"), shippingRegion);
		msg.actions(shippingRegion+" is the shipping region price selected\n");

		userClickOn("btn_saveCancel", "Save");
		switchToDefaultContent();
	}

	public void userAddNewPublicationPrice(String member,String code, String price, Map<String,Object> testCase){
		waitForLoaderToDisappear();
		clickOnAddButton("publication price");
		switchToFrame(element("frame_addPub"));
		FillDetailsToAddPublicationProduct_Edoc(member, code, price, testCase);
		msg.actions("user add new publication price completes\n");
		switchToDefaultContent();

	}
	
}
