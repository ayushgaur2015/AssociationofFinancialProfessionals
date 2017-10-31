package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;
import java.util.Map;

import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;

public class ShoppingCartActionsPage extends GeneralActionsPage {

	public ShoppingCartActionsPage(WebDriver driver) {
		super(driver, "ShoppingCartActionsPage");
	}

	public void verifyItemInCart(String itemName) {
		isElementDisplayed("text_item", itemName);
		msg.pass("ItemCart is verified\n");
	}

	public void verifyNetBalance() {
		isElementDisplayed("text_netBalance");
		String actualBalance = dataStorage.get("netBalance").toString();
		assertTrue(element("text_Confirmation").getText().contains(actualBalance),
				msg.failForAssert("Correct net balance is not displayed"));
		msg.pass("Correct net balance is displayed\n");
	}

	public void verifyOrderConfirmationMessage() {
		isElementDisplayed("text_Confirmation");
		assertTrue(element("text_Confirmation").getText().contains("Thank you for your order"),
				msg.failForAssert("Confirmation message is not displayed\n"));
		msg.pass("Confirmation message is displayed\n");
	}

	public void verifyAndStoreTransactionCode() {
		isElementDisplayed("text_transactionCode");
		dataStorage.put("TransactionCode", element("text_transactionCode").getText());
		msg.pass(element("text_transactionCode").getText()
				+ "Transactioncode has been verified and stored successfully\n");

	}

	public void storeNetBalance() {
		isElementDisplayed("text_netBalance");
		dataStorage.put("netBalance", element("text_netBalance").getText());
		msg.log(element("text_netBalance").getText() + "net balance is stored\n");
	}

	public void verifyDiscountField() {
		isElementDisplayed("inp_discount");
		msg.pass("Discount field is verified\n");
	}

	public void userFinalConfirmWithAfpMembershipDetails(String itemName) {
		verifyItemInCart(itemName);
		storeNetBalance();
		userClickOn("btn_finsh_chck_cont_subOrder_edit_contShop", "Finish");
		msg.log("User confirm the membership details \n");
	}

	public void userCheckoutAfpMembershipOrder() {
		verifyDiscountField();
		scrollDown(element("btn_finsh_chck_cont_subOrder_edit_contShop", "Check-Out"));
		clickUsingXpathInJavaScriptExecutor(element("btn_finsh_chck_cont_subOrder_edit_contShop", "Check-Out"));
		msg.actions("user check-out the order\n");
	}

	public void userVerifyDiscountAppliedOnProduct(String discountEvent){
		double itemPrice = 0.00;
		Assert.assertTrue(element("text_discountApplied").getText().contains("discounts applied"),msg.failForAssert("Discount not applied\n"));
		msg.pass("Discount applied \n");
		String[] discount = element("text_discountApplied").getText().split("\n");
		String discountValue = discount[1].replace(discountEvent+" ","");
		
		for(WebElement price: elements("list_tableItemPrice")){
			itemPrice += Double.parseDouble(price.getText());
		}
		
		msg.log(itemPrice+" is the subtotal from adding event and session fee \n");
		String subtotal = String.valueOf(itemPrice).replace(".0", ".00");
		
		msg.log(discountValue+" is the discount applied\n");
		dataStorage.put("discountValue", discountValue);
		dataStorage.put("subtotal", subtotal);
		
		msg.actions(" Verified discount is applied on product\n");
	}
	
	public void userApplyDiscountCode(String ewebcode) {
		verifyDiscountField();
		enterDiscountCode(ewebcode);
		click(element("btn_finsh_chck_cont_subOrder_edit_contShop", "apply"));
		msg.actions("user apply discount code completes\n");
	}

		
	
	public void productDetailsAfterDiscount(String discountEvent){
		isElementDisplayed("text_tableItemPrice",discountEvent);
		msg.log(element("text_tableItemPrice",discountEvent).getAttribute("innerText")+" is the price of item in table\n");
		dataStorage.put("itemPriceDiscount", element("text_tableItemPrice",discountEvent).getAttribute("innerText"));
		
		isElementDisplayed("text_tableItemDiscount",discountEvent);
		msg.log(element("text_tableItemDiscount",discountEvent).getText()+" is the discount on item in table\n");
		dataStorage.put("itemDiscount", element("text_tableItemDiscount",discountEvent).getText());


		isElementDisplayed("text_tableNetTotal",discountEvent);
		msg.log(element("text_tableNetTotal",discountEvent)+" is the nettotal of item in table\n");
		dataStorage.put("itemNetTotalDiscount", element("text_tableNetTotal",discountEvent).getText());

		msg.log("Product details after discount\n");

		
	}
	
	public void productDetailsBeforeDiscount(String discountEvent){
		isElementDisplayed("text_tableItemPrice",discountEvent);
		msg.log(element("text_tableItemPrice",discountEvent).getText()+" is the price of item in table\n");
		dataStorage.put("itemPriceNoDiscount", element("text_tableItemPrice",discountEvent).getText());
		
		isElementDisplayed("text_tableItemDiscount",discountEvent);
		msg.log(element("text_tableItemDiscount",discountEvent).getText()+" is the discount on item in table\n");
		dataStorage.put("itemNoDiscount", element("text_tableItemDiscount",discountEvent).getText());


		isElementDisplayed("text_tableNetTotal",discountEvent);
		msg.log(element("text_tableNetTotal",discountEvent).getText()+" is the nettotal of item in table\n");
		dataStorage.put("itemNetTotalNoDiscount", element("text_tableNetTotal",discountEvent).getText());

		msg.log("Product details before discount\n");
	}
	
	public void userVerifyProduct_Total_Net_SubTotal_DiscountDetailsOnShoppingCart(){
		verifyDetailsOf_("discount",keyValue(dataStorage,"itemDiscount"));
		verifyDetailsOf_("subtotal",keyValue(dataStorage,"subtotal"));
		msg.actions("user verified product total net-total subtotal and discount details on shopping cart complete\n");
	}
	
	public void verifyDetailsOf_(String product,String value){
		
		isElementDisplayed("text_"+product);
		Assert.assertEquals(element("text_"+product).getText(),value,msg.failForAssert("values are not equals\n"));
		msg.pass(value+"is verified as "+ product+"\n");
		
	}
	
	public void userProceedsToCheckOutOrder() {
		scrollDown(element("btn_finsh_chck_cont_subOrder_edit_contShop", "Check-Out"));
		clickUsingXpathInJavaScriptExecutor(element("btn_finsh_chck_cont_subOrder_edit_contShop", "Check-Out"));

	}

	public void enterDiscountCode(String ewebcode) {
		enterDetails("discount", ewebcode);
		msg.actions(ewebcode + " is entered as dicount code\n");
	}

	public void userEnterTransactionDetailsToCompleteAfpMembership(Map<String, Object> paymentDetails,
			String PaymentType) {
		selectProvidedTextFromDropDown(element("dropDown_paymentMethods"), PaymentType);

		msg.log(keyValue(paymentDetails, "cardHolderName") + " is the cardHolder name\n");
		enterDetails("cardholderName", keyValue(paymentDetails, "cardHolderName"));
		msg.log(keyValue(paymentDetails, "creditCardNumber") + " is the creditCard number\n");
		enterDetails("creditCardNumber", keyValue(paymentDetails, "creditCardNumber"));

		msg.log("selecting " + keyValue(paymentDetails, "expiryDate") + " From expiryDate \n");
		selectProvidedTextFromDropDown(element("dropDown_expiryDate"), keyValue(paymentDetails, "expiryDate"));

		enterDetails("cvv", keyValue(paymentDetails, "cvv"));
		userClickOn("btn_finsh_chck_cont_subOrder_edit_contShop", "Continue");
		msg.actions("User enter transaction details\n");
	}

	public void userSubmitsOrderDetailsToCompleteTransaction() {
		isElementDisplayed("btn_finsh_chck_cont_subOrder_edit_contShop", "Submit Order");
		userClickOn("btn_finsh_chck_cont_subOrder_edit_contShop", "Submit Order");
		msg.actions("User submits order details\n");
	}

	public void userVerifyTransactionReceipt() {
		verifyOrderConfirmationMessage();
		verifyAndStoreTransactionCode();
		userClickOn("btn_finsh_chck_cont_subOrder_edit_contShop", "Continue Shopping");
		msg.actions("User verify transaction receipt\n");
	}

	public void verifyUserOnShoppingCartPage() {
		verifyPageTitleContains();
		msg.actions("verified user on shoppingcart page\n");

	}
}
