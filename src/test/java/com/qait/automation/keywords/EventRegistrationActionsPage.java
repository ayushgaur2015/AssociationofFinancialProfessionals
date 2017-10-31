package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;

public class EventRegistrationActionsPage extends GeneralActionsPage{

	public EventRegistrationActionsPage(WebDriver driver) {
		super(driver,"EventRegistrationActionsPage");	
		}
	
	public void verifyCorrectUserNameIsDisplayed(){
		assertTrue(element("text_name").getText().contains(YamlReader.getYamlValue("personalInformation.firstName")),msg.failForAssert("Name of the registering user doesnot match"));	
		msg.pass("Name of the registering user"+element("text_name").getText()+" matches\n ");
	}
	
	public void verifyFieldAddressType(){
		isElementDisplayed("dropDown_addType");
		msg.pass("Address type field is verified\n");
	}
	
	public void verifyRegType(){
		isElementDisplayed("dropDown_regtype");
		msg.pass("Registration type is verified\n");
	}
	
	public void userAddEventToTheCart(){
		clickUsingXpathInJavaScriptExecutor(element("btn_addToCart","Add To Cart"));
		msg.actions("User has added the event into the cart\n");
	}

	public void verifyCorrectProductNameIsDisplayed(String product) {
		Assert.assertTrue(isElementDisplayed("text_product",product), msg.failForAssert("Appropriate product not displayed"));
		msg.pass("Appropriate product "+product+" is added into the cart\n");
	}
	
	public String verifyCorrectProductEventAndGetFee(String product){
		Assert.assertTrue(isElementDisplayed("text_pruductEvent",product), msg.failForAssert("Appropriate product not displayed"));
		msg.pass("Appropriate product "+product+" is added into the cart\n");
		isElementDisplayed("text_productEventFee",product);
		return element("text_productEventFee",product).getText();
	}
	

}
