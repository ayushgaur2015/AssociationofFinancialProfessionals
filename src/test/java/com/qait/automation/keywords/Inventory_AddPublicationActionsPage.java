package com.qait.automation.keywords;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.utils.msg;

public class Inventory_AddPublicationActionsPage extends GeneralActionsPage{

	public Inventory_AddPublicationActionsPage(WebDriver driver) {
		super(driver, "Inventory_AddPublicationActionsPage");
	}

	public void FillDetailsToAddPublicationProduct_Edoc(Map<String,Object> testCase){
		int count=0;
		enterDetails("prdcode", keyValue(testCase,"productCode"));
		enterDetails("prdName", keyValue(testCase,"productName"));
		selectProvidedTextFromDropDown(element("dropDown_prdCatgry"), keyValue(testCase,"productCategory"));
		checkCheckbox(element("chkBox_sellOnline"));
		dataStorage.put("sellOnline", "Yes");
		checkCheckbox(element("chkbox_web"));
		dataStorage.put("web", "Yes");
		enterDetails("prdDescrp", keyValue(testCase,"description"));
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
		waitForLoaderToDisappear();
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropDown_writeOffAcc"), keyValue(testCase,"writeOffAccount"));
		msg.actions(keyValue(testCase,"writeOffAccount")+" is selected as writeOff account\n");
		userClickOn("btn_saveCanel", "Save");
		msg.actions("New publication product detailes are filled\n");
	}

	
	public void FillDetailsToAddPublicationProduct_EdocAlongWithMemberType(String member,Map<String,Object> testCase){
		int count=0;
		enterDetails("prdcode", keyValue(testCase,"productCode"));
		enterDetails("prdName", keyValue(testCase,"productName"));
		selectProvidedTextFromDropDown(element("dropDown_prdCatgry"), keyValue(testCase,"productCategory"));
		checkCheckbox(element("chkBox_sellOnline"));
		dataStorage.put("sellOnline", "Yes");
		checkCheckbox(element("chkbox_web"));
		dataStorage.put("web", "Yes");
		enterDetails("prdDescrp", keyValue(testCase,"description"));
		waitForLoaderToDisappear();
		if(member!=null&&member.equals("Member")){
			selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Member");
			msg.actions("Member option is selected for the member type\n");
		}
		if(member!=null&&member.equals("Non-member")){
			selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Non-Member");
			msg.actions("Non-member is selected for the member type\n");

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
		waitForLoaderToDisappear();
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropDown_writeOffAcc"), keyValue(testCase,"writeOffAccount"));
		msg.actions(keyValue(testCase,"writeOffAccount")+" is selected as writeOff account\n");
		userClickOn("btn_saveCanel", "Save");
		msg.actions("New publication product detailes are filled\n");
	}
	
	public void FillDetailsToAddPublicationProduct_Edoc(String member,Map<String,Object> testCase, boolean sellOnline, boolean web, boolean taxable,
			boolean shippable, boolean trackInventory) {

		enterDetails("prdcode", keyValue(testCase,"productCode"));
		enterDetails("prdName", keyValue(testCase,"productName"));
		enterDetails("prdDescrp", keyValue(testCase,"description"));
		if (keyValue(testCase,"productCategory") != "null" && !keyValue(testCase,"productCategory").equals("null")){
		selectProvidedTextFromDropDown(element("dropDown_prdCatgry"),
				keyValue(testCase,"productCategory"));
		msg.actions("Required product category is selected from the product category dropdown\n");
		}
		chkCheckboxes( sellOnline, web,  taxable,
			 shippable, trackInventory);
		if (member != null && member.equals("Member")) {
			selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Member");
			msg.actions("Member option is selected for the member type\n");

		}
		if (member != null && member.equals("Non-member")) {
			selectProvidedTextFromDropDown(element("dropDown_memberFlag"), "Non-Member");
			msg.actions("Non-member is selected for the member type\n");

		}
		waitForLoaderToDisappear();
		wait.hardWait(3);
		selectProvidedTextFromDropDown(element("dropdown_arAcc"),
				keyValue(testCase,"a_rAccount"));
		msg.actions(keyValue(testCase,"a_rAccount") + " is selected as A/R account\n");
		selectProvidedTextFromDropDown(element("dropDown_revenueAcc"),
				keyValue(testCase,"revenueAccount"));
		msg.actions(
				keyValue(testCase,"revenueAccount") + " is selected as revenue account\n");
		selectProvidedTextFromDropDown(element("dropDown_liabilityAcc"),
				keyValue(testCase,"liabilityAccount"));
		msg.actions(keyValue(testCase,"liabilityAccount")
				+ " is selected as liability account\n");
		selectProvidedTextFromDropDown(element("dropDown_returnAcc"),
				keyValue(testCase,"returnAccount"));
		msg.actions(keyValue(testCase,"returnAccount") + " is selected as return account\n");
		wait.hardWait(3);
				//element("dropDown_writeOffAcc").click();
		selectProvidedTextFromDropDown(element("dropDown_writeOffAcc"),
				keyValue(testCase,"writeOffAccount"));
		msg.actions(
				keyValue(testCase,"writeOffAccount") + " is selected as writeOff account\n");
		waitForLoaderToDisappear();
		userClickOn("btn_saveCanel", "Save");
		msg.actions("New publication product detailes are filled\n");


	}

	public void chkCheckboxes(boolean sellOnline, boolean web, boolean taxable, boolean shippable, boolean trackInventory) {
		if (sellOnline) {
			checkCheckbox(element("chkBox_sellOnline"));
			dataStorage.put("sellOnline", "Yes");
		}
		if (web) {
			checkCheckbox(element("chkbox_web"));
			dataStorage.put("web", "Yes");
		}
		if (taxable) {
			checkCheckbox(element("chkbox_taxable"));
			dataStorage.put("taxable", "Yes");
		}
		if (shippable) {
			checkCheckbox(element("chkbox_shippable"));
			dataStorage.put("shippable", "Yes");
		}

		if (trackInventory) {
			waitForLoaderToDisappear();
			clickUsingXpathInJavaScriptExecutor(element("chkbox_trackInventory"));
			dataStorage.put("taxable", "Yes");
		}
	}
	

	
}
