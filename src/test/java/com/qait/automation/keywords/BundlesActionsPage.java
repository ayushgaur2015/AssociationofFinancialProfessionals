package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.qait.automation.utils.msg;

public class BundlesActionsPage extends GeneralActionsPage {

	public BundlesActionsPage(WebDriver driver) {
		super(driver, "BundlesActionsPage");
		// TODO Auto-generated constructor stub
	}

	/**
	 * method to switch to bundle window
	 */
	public void userSwitchToBundlesWindow() {
		changeWindow(1);
		msg.pass("Bundles window appeared\n");
	}

	/**
	 * method to switch to default window
	 */
	public void userSwitchToDefaultWindow() {
		changeWindow(0);
	}

	/**
	 * method to verify page title heading
	 * @param title - title of the window
	 * @param heading - heading on the window
	 */
	public void verifyPageTitle_Heading(String title, String heading){
		verifyPageTitleContains(title);
		Assert.assertTrue(isElementDisplayed("text_pageHeading",heading),msg.failForAssert(heading+" is not displayed\n"));
		msg.pass(heading+" heading of page is verified\n");
	}
	
	/**
	 * method to verify page title and headings
	 * @param title - title of the window
	 * @param heading1 - first heading on the bundle window
	 * @param heading2 - second heading on the bundle window
	 */
	public void verifyPageTitle_Heading(String title, String heading1, String heading2){
		verifyPageTitleContains(title);
		Assert.assertTrue(isElementDisplayed("text_pageHeading",heading1),msg.failForAssert(heading1+" is not displayed\n"));
		Assert.assertTrue(isElementDisplayed("text_pageHeading",heading2),msg.failForAssert(heading2+" is not displayed\n"));
		msg.pass(heading1+"|"+heading2+" heading of page is verified\n");
	}
	
	/**
	 * method to select CTP bundle product based on user provided product type
	 * @param productType - user required product ot be selected
	 * @return String - product display name
	 */
	public String userSelectCTP_BundleProduct(String productType) {
		String[] productCategory=productType.split(" ");
		for (WebElement product : elements("text_option_dropDown_selectBundle")) {
			if ((product.getText().startsWith(productCategory[0]))
					&& !(product.getText().contains("Member") || product.getText().contains("Student"))) {
				selectProvidedTextFromDropDown(element("dropDown_selectBundle"), product.getText());
				msg.log(product.getText()+" is selected as CTP Bundled product\n");
				dataStorage.put("CTP_BundleProduct", product.getText().replaceAll("/20", "/"));
				break;
			}

		}

		String product = getElementFromListUsingText("list_prdctName",productType).getText();
		
		verifyPageTitle_Heading("netFORUM", "Bundle", "Product");
		userClickOn_("Next");
		wait.waitForPageToLoadCompletely();

		
		verifyPageTitle_Heading("netFORUM", "Bundle", "Membership");
		userClickOn_("Next");
		wait.waitForPageToLoadCompletely();


		verifyPageTitle_Heading("netFORUM", "Bundle", "Certification");
		userClickOn_("Next");
		wait.waitForPageToLoadCompletely();


		verifyPageTitle_Heading("Event Registration", "Bundle", "Event Registration");
		userClickOn_("Next");
		handleAlert();
		wait.waitForPageToLoadCompletely();


		verifyPageTitle_Heading("netFORUM", "Bundle", "Confirm");
		userClickOn_("Next");

		userSwitchToDefaultWindow();
		msg.actions("User selects CTP_Bundle product\n");
		return product;
	}

	/**
	 * method to select AFP bundle product based on user provided product type
	 * @param productType - user required product ot be selected
	 * @return String - product display name
	 */
	public String userSelectAFP_BundleProduct(String productType) {
		boolean flag = false;
		for (WebElement product : elements("text_option_dropDown_selectBundle")) {
			flag = false;
			if (product.getText().equals(productType)) {
				selectProvidedTextFromDropDown(element("dropDown_selectBundle"), product.getText());
				flag = true;
				break;
			}

		}

		Assert.assertTrue(flag, msg.failForAssert(productType + " bundle product not found\n"));
		msg.pass(productType + " bundle product found\n");
		
		String product = getElementFromListUsingText("list_prdctName",productType).getText();

		waitForLoaderToDisappear();
		Assert.assertEquals(element("text_bundleProduct").getText(), productType);

		verifyPageTitle_Heading("netFORUM", "Bundle", "Product");
		userClickOn_("Next");
		wait.waitForPageToLoadCompletely();

		
		verifyPageTitle_Heading("netFORUM", "Bundle", "Membership");
		userClickOn_("Next");
		wait.waitForPageToLoadCompletely();

		verifyPageTitle_Heading("netFORUM", "Bundle", "Confirm");
		userClickOn_("Next");

		userSwitchToDefaultWindow();
		msg.actions("User selects afp bundle product completes \n");
		return product;
	}

	/**
	 * method user click on specified button
	 * @param Button - user required button to be clicked
	 */
	public void userClickOn_(String Button) {
		browserSpecificHolds();
		isElementDisplayed("btn_onBundleProduct", Button);
		element("btn_onBundleProduct", Button).click();
		msg.actions("User click on " + Button + " btn_onBundleProduct\n");
	}

}
