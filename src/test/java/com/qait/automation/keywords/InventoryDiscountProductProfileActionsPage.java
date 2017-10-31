package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class InventoryDiscountProductProfileActionsPage extends GeneralActionsPage {

	public InventoryDiscountProductProfileActionsPage(WebDriver driver) {
		super(driver, "InventoryDiscountProductProfileActionsPage");
	}

	public void verifyUserOnInventoryDiscountProductProfileActionsPage() {
		verifyPageTitleContains();
	}

	public void clickAddIconOf_(String heading) {
		isElementDisplayed("icon_addChildView", heading);
		element("icon_addChildView", heading).click();
		msg.log("Clicked on add product icon of " + heading + " is complete\n");
	}

	public void verifyProductNameOnDiscountProductXProductWindow(String productName) {
		Assert.assertTrue(element("text_prdNamediscountProductXproduct").getText().contains(productName),
				msg.failForAssert("Invalid product name is displayed on DiscountProductXProductWindow\n "));
		msg.pass("Valid product name is displayed on DiscountProductXProductWindow\n ");
	}

	public void selectProductType(String prodType) {
		selectProvidedTextFromDropDown(element("dropDown_productType"), prodType);
		msg.log("Select product type is complete \n");
	}

	public void selectProduct(String product) {
		selectProvidedTextFromDropDown(element("dropDown_product"), product);
		msg.log("Select product is complete \n");

	}

	public void user_VerifyProductName(String productName) {
		Assert.assertTrue(element("text_prdName").getText().contains(productName),
				msg.failForAssert("Invalid discount product name is displayed \n"));
		msg.pass("Valid discount product name is displayed \n");
	}

	public void enterPriceValue(String price) {
		selectProvidedTextFromDropDown(element("dropDown_price"), price);
		msg.log("Select price is complete \n");

	}

	public void userEditDiscountProduct(){
		isElementDisplayed("icon_editDiscount","discount amounts");
		element("icon_editDiscount","discount amounts").click();;
	}
	
	public void user_VerifyProductAddedIN_(String heading, String productName, String priceRate) {
		if (!element("icon_childViewState", heading).getAttribute("title").contains("close")) {
			element("icon_childView", heading).click();
		}

		Assert.assertTrue(element("text_childView").getText().contains(productName),
				msg.failForAssert("Discount product name is displayed in child view \n"));
		msg.pass("Discount product name is displayed in child view \n");

		Assert.assertTrue(element("text_childView").getText().contains(priceRate),
				msg.failForAssert("Invalid price/rate is displayed in child view\n"));
		msg.pass("Invalid price/rate is displayed in child view\n");

	}

	public void clickOn(String button) {
		element("btn_save_cancel", button).click();
		msg.log(button + " is clicked using element btn_save_cancel \n");
	}

	public void user_AddDiscountProductXProductWithValue_(String heading, String productName, String prodType,
			String product, String price) {
		wait.hardWait(3);
		clickAddIconOf_(heading);
		switchToFrame(element("frame_discountProduct"));
		verifyProductNameOnDiscountProductXProductWindow(productName);

		waitForLoaderToDisappear();
		selectProductType(prodType);
		switchToDefaultContent();
		switchToFrame(element("frame_discountProduct"));
		waitForLoaderToDisappear();
		browserSpecificHolds();
		selectProduct(product);
		waitForLoaderToDisappear();

		switchToDefaultContent();
		switchToFrame(element("frame_discountProduct"));

		enterPriceValue(price);
		clickOn("Save");
		switchToDefaultContent();

	}
	
	public void userVerifyEventNameInFollowingTab_(String event, String tab) {
		boolean flag = false;
		for (WebElement eventName : elements("text_EventName", tab)) {
			flag = false;
			System.out.println(eventName.getText());
			if(eventName.getText().startsWith(event)){
				flag=true;
				break;
			}
		}
		Assert.assertTrue(flag, msg.failForAssert("event " + event + " not matched\n"));
		msg.pass("Event matched\n");
	}
	
	public void userDeleteDiscountProductIfPresent_(boolean present){
		if(present){
			click(element("btn_EditDiscount"));
			
			switchToDefaultContent();
			switchToFrame(element("frame_discountProduct"));
			wait.hardWait(3);
			clickOn("Delete");
			handleAlert();
			msg.actions("user delete discount product completes\n");

		}
		else{
			msg.log("Discount product not present cannot be deleted\n");
		}
	}
}
