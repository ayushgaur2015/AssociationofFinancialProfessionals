package com.qait.automation.keywords;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;

public class GeneralActionsPage extends GetPage {

	protected WebDriver webdriver;
	String pageName;
	static int pageCount = 0;
	static int waitCount = 0;

	public GeneralActionsPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		webdriver = driver;
	}

	/**
	 * user expands header menu option and select sub option from the drop down
	 * 
	 * @param headerMenu
	 *            - option available in menu bar
	 * @param item
	 *            - sub option from the expanded menu option
	 */
	public void userSelectsTopHeaderMenuItems(String headerMenu, String item) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("link_dropDownHeaderMenu", headerMenu);
		clickUsingXpathInJavaScriptExecutor(element("link_dropDownHeaderMenu", headerMenu));
		msg.actions(headerMenu + " is clicked\n");
		dynamicWait_withReplacement(5, "link_dropDownHeaderMenuItems", item);
		isElementDisplayed("link_dropDownHeaderMenuItems", item);
		clickUsingXpathInJavaScriptExecutor(element("link_dropDownHeaderMenuItems", item));
		msg.actions(item + " is clicked\n");
		msg.log("From " + headerMenu + "Item " + item + " is selected\n");
	}

	public void dynamicWait(String strElement, int timeout) {
		try {

			if (waitCount < timeout) {
				wait.resetImplicitTimeout(3);
				isElementDisplayed(strElement);
				waitCount++;
			}
		} catch (NoSuchElementException nse) {
			dynamicWait(strElement, timeout);
		} catch (AssertionError nse) {
			dynamicWait(strElement, timeout);
		} finally {
			int configTimeout = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));
			wait.resetImplicitTimeout(configTimeout);
			waitCount = 0;
		}
	}

	/**
	 * method to click on add icon corresponding to a view
	 * 
	 * @param addTabHeading
	 *            - name of the view under which element is to be added
	 */
	public void clickOnAddButton(String addTabHeading) {
		dynamicWait_withReplacement(3, "icon_add", addTabHeading);
		isElementDisplayed("icon_add", addTabHeading);
		element("icon_add", addTabHeading).click();
		msg.actions("Clicked on add " + addTabHeading + "\n");
	}

	/**
	 * method to generate a random numeric value upto a given range
	 * 
	 * @param limit
	 *            - max. range provided by user
	 * @return string type random numeric value
	 */
	public String generateRandomOutput(int limit) {
		Random ran = new Random();
		int x = ran.nextInt(limit) + 1;
		return String.valueOf(x);
	}

	/**
	 * method to generate random numeric value within a given range
	 * 
	 * @param limit
	 *            - max. range provided by user
	 * @param initial
	 *            - min. range provided by user
	 * @return string type random numeric value
	 */
	public String generateRandomOutput(int limit, int initial) {
		Random ran = new Random();
		int x = ran.nextInt(limit) + initial;
		return String.valueOf(x);
	}

	/**
	 * 
	 * @param limit
	 * @param initial
	 * @return
	 */
	public int generateRandomInteger(int limit, int initial) {
		Random ran = new Random();
		int x = ran.nextInt(limit) + initial;
		return x;
	}

	@SuppressWarnings("null")
	public String generateRandomUniqueOutput(int limit) {
		Random ran = new Random();
		int i = 0;
		List<String> uniqueNumber = new ArrayList<String>();
		while (i < limit) {
			String x = String.valueOf(ran.nextInt(limit) + 1);
			if (uniqueNumber == null) {
				uniqueNumber.add(x);
			} else if (!uniqueNumber.contains(x)) {
				uniqueNumber.add(x);
				return x;
			}
			i++;
		}
		return null;
	}

	@SuppressWarnings("null")
	public String generateRandomUniqueOutput(int limit, int min) {
		Random ran = new Random();
		int i = 0;
		List<String> uniqueNumber = new ArrayList<String>();
		while (i < limit) {
			String x = String.valueOf(ran.nextInt(limit) + min);
			if (uniqueNumber == null) {
				uniqueNumber.add(x);
			} else if (!uniqueNumber.contains(x)) {
				uniqueNumber.add(x);
				return x;
			}
			i++;
		}
		return null;
	}

	public void storeUrlOfPage_(String pageObject) {
		dataStorage.put(pageObject, getCurrentURL());
		msg.log("stored url of the current page" + keyValue(dataStorage, pageObject) + "\n");
	}

	public void navigateToStoredUrl(String pageObject) {
		msg.actions("navigates to URL=" + keyValue(dataStorage, pageObject) + "\n");
		webdriver.get(keyValue(dataStorage, pageObject));
	}

	public void enterDetails(String fieldName, String fieldValue) {
		isElementDisplayed("inp_" + fieldName);
		element("inp_" + fieldName).clear();
		dynamicWait(3, "inp_" + fieldName);
		element("inp_" + fieldName).sendKeys(fieldValue);
		msg.actions(fieldValue + " is entered in " + fieldName + " \n");
	}

	/**
	 * method to click based on element type
	 * 
	 * @param strElement
	 *            - element name to be located
	 * @param operation
	 *            - replacement used to find that particular element
	 */
	public void userClickOn(String strElement, String operation) {
		isElementDisplayed(strElement, operation);
		element(strElement, operation).click();
		msg.actions(operation.replaceAll("»", " ") + " button is clicked in " + strElement + "\n");
	}

	public void userClickRadio(String element, String operation) {
		isElementDisplayed(element, operation);
		click(element(element, operation));
		msg.actions("Step :" + operation + "  radio is clicked in " + element + "\n");
	}

	public void selectFromIndex(String heading) {
		dynamicWait_withReplacement(5, "heading_leftContentIndex", heading);
		if (!element("heading_leftContentIndex", heading).getAttribute("class").contains("ui-state-active")) {
			click(element("heading_leftContentIndex", heading));
		}
		msg.actions(heading + " is selected From Index\n");
	}

	public void selectFromIndexList(String heading) {
		String[] leftIndex = heading.split(" ");
		boolean flag = false;
		int i = 0;
		for (WebElement index : elements("list_leftContentIndex")) {
			flag = false;
			while (i < leftIndex.length) {
				flag = false;
				if (index.getText().contains(leftIndex[i])) {
					flag = true;
				}
				if (!flag) {
					break;
				}
				i++;
			}
			if (flag) {
				if (!index.getAttribute("class").contains("ui-state-active")) {
					index.click();
				}
				break;
			}
		}
		msg.log(heading + " is selected From Index\n");
	}

	public void selectSubContentFromIndex(String subContent) {
		wait.waitForElementToBeVisible(element("link_leftSubContent", subContent));
		element("link_leftSubContent", subContent).click();
		msg.log(subContent + " is selected From Index \n");
	}

	public void selectSubContentFromIndex(String heading, String subContent) {
		wait.hardWait(3);
		String subCont[]=subContent.split(" ");
		element("link_leftSubContent", heading, subContent).click();
		msg.log(subContent + " is selected From Index \n");
	}

	/**
	 * method to select filter and sub filter option from the left container
	 * using direct string value
	 * 
	 * @param heading
	 *            - name of the filter to be applied
	 * @param subContent
	 *            - sub filter under the filter option
	 */
	public void userSelectsFilterAndFilterOptionsOnLeftPanel(String heading, String subContent) {
		selectFromIndex(heading);
		selectSubContentFromIndex(heading, subContent);
		msg.log("user selects " + heading + " and " + subContent + " operation completes \n");
	}

	public void userSelectsFilterAndFilterOptionsFromListOnLeftPanel(String heading, String subContent) {
		browserSpecificHolds();
		selectFromIndexList(heading);
		selectSubContentFromIndex(heading, subContent);
		msg.actions("user selects " + heading + " and " + subContent + " operation completes \n");
	}

	public void userSelectsFilterAndFilterOptionsFromListOnLeftPanel(String heading, String subContent,
			boolean lowerCase) {
		browserSpecificHolds();
		selectFromIndexList(heading);
		if (lowerCase) {
			selectSubContentFromIndex(heading.toLowerCase(), subContent);
		} else {
			selectSubContentFromIndex(heading.toLowerCase(), subContent);
		}
		msg.actions("user selects " + heading + " and " + subContent + " operation completes \n");
	}

	public void SelectsFromExistingquey(String existingQuery, String operation) {
		selectProvidedTextFromDropDown(element("dropDown_existingQuery"), existingQuery);
		msg.log(existingQuery + " query is selected from the existing query dropdown\n");
	}

	public void queryOperation(String operation) {
		userClickOn("inp_run_update_updateAndCreate_save", operation);
		msg.log(operation + " is complete\n");
	}

	public void userSelectsExistingQueryAndPerformOperation(String existingQuery, String operation) {
		browserSpecificHolds();
		SelectsFromExistingquey(existingQuery, operation);
		queryOperation(operation);
	}

	/**
	 * Workflow to open menu item from more tab and to expand the required child
	 * view
	 * 
	 * @param menuOptions
	 *            - user required option under the more tab
	 * @param menuItem
	 *            - is the childview under menu option
	 */
	public void userOpenCustomerProfile_MenuOptionAndSubItem(String menuOptions, String menuItem) {
		wait.waitForPageToLoadCompletely();
		dynamicWait(5, "text_profileTitle");
		if (!element("text_profileTitle").getText().equalsIgnoreCase(menuOptions)) {
			waitForLoaderToDisappear();
			dynamicWait(5, "tab_more");
			clickUsingXpathInJavaScriptExecutor(element("tab_more"));
			msg.actions("clicked on more button\n");
			dynamicWait(5, element("list_profileMenuItems", menuOptions));
			wait.waitForElementToBeVisible(element("list_profileMenuItems", menuOptions));
			hoverClick(element("list_profileMenuItems", menuOptions));
			msg.actions(menuOptions + " is clicked under more tab\n");
			waitForLoaderToDisappear();
			dynamicWait_withReplacement(3, "link_childView", menuItem);
		}
		if (element("link_childView", menuItem).getAttribute("class").contains("down")) {
			element("link_childView", menuItem).click();
			waitForLoaderToDisappear();
			msg.actions(menuItem + " view is expanded\n");
		}
		msg.actions("user open customer profile and menu option " + menuOptions + " and subitem " + menuItem + "\n");
	}

	public void expandview(String view) {
		wait.hardWait(2);
		if (element("link_childView", view).getAttribute("class").contains("down")) {
			element("link_childView", view).click();
			msg.actions(view + " view is expanded\n");
		}
	}

	public void userVerifyEventNameInFollowingTab_(String event, String tab) {
		boolean flag = false;
		for (WebElement eventName : elements("text_registrationColEventName", tab)) {
			flag = false;
			if (eventName.getText().startsWith(event)) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, msg.failForAssert("event " + event + " not matched\n"));
		msg.pass("Event matched\n");
	}

	public void browserSpecificHolds() {
		wait.hardWait(5);
	}

	public void waitForLoaderToDisappear() {
		int timeOut, hiddenFieldTimeOut;
		timeOut = Integer.parseInt(getProperty("Config.properties", "timeout"));
		hiddenFieldTimeOut = Integer.parseInt(getProperty("Config.properties", "hiddenFieldTimeOut"));
		try {
			wait.resetImplicitTimeout(2);
			wait.resetExplicitTimeout(hiddenFieldTimeOut);
			if (checkIfElementDisplayed("img_loginLoading")) {
				msg.log("wait for load to diasappear\n");
				wait.waitForElementToDisappear(element("img_loginLoading"));
			}
			wait.resetImplicitTimeout(timeOut);
			wait.resetExplicitTimeout(timeOut);
		} catch (Exception exp) {
			wait.resetImplicitTimeout(timeOut);
			wait.resetExplicitTimeout(timeOut);
		}

	}

	public void userSelectsRandomRecordFromTheExistingList() {
		boolean flag = false;
		String pageNo=null;
		String individualRow = null;
		int size = 0;
		int recordLimit = elements("list_link_individualProfileGotoLink").size();
		if (elements("list_link_pageTabExcptNext").size() != 0) {
			pageNo = generateRandomUniqueOutput((elements("list_link_pageTabExcptNext").size() - 1), 2);
			msg.log(String.valueOf(Integer.parseInt(pageNo) + 1) + " is selected as random Page number\n");
			isElementDisplayed("link_pageTabExcptNext", pageNo);
			element("link_pageTabExcptNext", pageNo).click();
		}
		while (size < recordLimit) {
			individualRow = generateRandomUniqueOutput(recordLimit - 1, 3);
			msg.log(individualRow + " is selected as random Row\n");
			wait.hardWait(2);
			if (!(element("text_individualListLastName", individualRow).getText().isEmpty()
					|| element("text_individualListAddress", individualRow).getText().isEmpty())
					&& !(element("text_individualListLastName", individualRow).getText() == ""
							|| element("text_individualListAddress", individualRow).getText() == "")) {
				flag = true;
				break;
			}
			size++;
		}
		if (flag) {
			msg.log(element("text_individualListLastName", individualRow).getText()
					+ " user selected as random individual with address "
					+ element("text_individualListAddress", individualRow).getText() + " \n");
			String gotoLink = String.valueOf(Integer.parseInt(individualRow) - 2);
			clickUsingXpathInJavaScriptExecutor(element("link_individualProfileGotoLink", gotoLink));
		}
		if (!flag) {
			userSelectsRandomRecordFromTheExistingList();
		}
		msg.actions("User selects random record from existing list");
	}

	public void userSelectsRandomRecordFromTheExistingList_NotContainingRecord(String username) {
		boolean flag = false;
		int size = 0;
		dynamicWait(3, elements("list_link_pageTabExcptNext").get(0));
		int recordLimit = elements("list_link_individualProfileGotoLink").size();
		String pageNo = generateRandomUniqueOutput((elements("list_link_pageTabExcptNext").size() - 1), 2);
		msg.log(pageNo + " is selected as random Page number\n");
		String individualRow = null;
		isElementDisplayed("link_pageTabExcptNext", pageNo);
		element("link_pageTabExcptNext", pageNo).click();
		while (size < recordLimit) {
			individualRow = generateRandomUniqueOutput(recordLimit - 2, 3);
			msg.log(individualRow + " is selected as random Row\n");
			if (!element("text_individualListLastName", individualRow).getText().isEmpty()
					&& !element("text_individualListAddress", individualRow).getText().isEmpty()
					&& !element("text_individualListAddress", individualRow).getText().contains(username)) {
				flag = true;
				break;
			}
			size++;
		}
		if (flag) {
			msg.log(element("text_individualListLastName", individualRow).getText()
					+ " user selected as random individual with address "
					+ element("text_individualListAddress", individualRow).getText() + " \n");
			element("link_individualProfileGotoLink", individualRow).click();
		}
		if (!flag) {
			userSelectsRandomRecordFromTheExistingList_NotContainingRecord(username);
		}
		msg.actions("User selected random record from existing list");
	}

	public void selectPaymentType(String item, String PaymentMethod) {
		try {
			wait.resetImplicitTimeout(10);
			Map<String, Object> paymentDetails = null;
			wait.waitForElementToBeVisible(element("dropDown_paymentType"));
			selectProvidedTextFromDropDown(element("dropDown_paymentType"), item);
			switch (item) {
			case "proforma":
				break;
			case "terms":
				selectProvidedTextFromDropDown(element("dropDown_invoiceTerms"),
						YamlReader.getData("paymentMethod.terms"));
				break;
			case "prepaid":
				switch (PaymentMethod) {
				case "Visa":
					paymentDetails = YamlReader.getYamlValues("Visa");
					msg.log("Visa is the prepaidType\n");
					selectProvidedTextFromDropDown(element("dropDown_paymentMethod"), "Visa");
					waitForLoaderToDisappear();
					msg.log(keyValue(paymentDetails, "cardHolderName") + " is the cardHolder name\n");
					enterDetails("cardholderName", keyValue(paymentDetails, "cardHolderName"));
					waitForLoaderToDisappear();
					msg.log(keyValue(paymentDetails, "creditCardNumber") + " is the creditCard number\n");
					enterDetails("creditCardNumber", keyValue(paymentDetails, "creditCardNumber"));
					msg.log("selecting " + keyValue(paymentDetails, "expiryDate") + " From expiryDate \n");
					selectProvidedTextFromDropDown(element("dropDown_expiryDate"),
							keyValue(paymentDetails, "expiryDate"));
					msg.log("selecting " + keyValue(paymentDetails, "cvv") + " is the CVV no. \n");
					enterDetails("cvv", keyValue(paymentDetails, "cvv"));
					break;

				case "Check":
					paymentDetails = YamlReader.getYamlValues("Check");
					msg.log("Check is the prepaidType\n");
					selectProvidedTextFromDropDown(element("dropDown_paymentMethod"), "Check");
					waitForLoaderToDisappear();
					dynamicWait(5, "text_netPayment");
					msg.log(keyValue(paymentDetails, "name on check") + " is the name on check\n");
					dynamicWait(5, element("inp_nameOnCheck"));
					enterDetails("nameOnCheck", keyValue(paymentDetails, "name on check"));
					waitForLoaderToDisappear();
					String checkNumber = generateRandomUniqueOutput(999999, 111111);
					dataStorage.put("checkNumber", checkNumber);
					msg.log(checkNumber + " is the number on check\n");
					enterDetails("checkNumber", checkNumber);
					waitForLoaderToDisappear();
					break;
				}
			}
			msg.log("user selects payment type " + item + " is complete\n");
		} catch (NoSuchElementException e) {
			msg.log("fees is not required \n");
		} catch (StaleElementReferenceException e) {
			msg.log("fees is not required \n");
		} finally {
			int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));
			wait.resetImplicitTimeout(timeOut);
		}
	}

	public void selectPaymentType(String item, String PaymentMethod, int amount) {
		try {
			wait.resetImplicitTimeout(5);
			Map<String, Object> paymentDetails = null;
			wait.waitForElementToBeVisible(element("dropDown_paymentType"));
			selectProvidedTextFromDropDown(element("dropDown_paymentType"), item);
			isElementDisplayed("text_netPayment");
			isElementDisplayed("dropDown_paymentMethod");
			switch (item) {
			case "proforma":
				break;
			case "terms":
				selectProvidedTextFromDropDown(element("dropDown_invoiceTerms"),
						YamlReader.getData("paymentMethod.terms"));
				break;
			case "prepaid":
				switch (PaymentMethod) {
				case "Visa":
					paymentDetails = YamlReader.getYamlValues("Visa");
					msg.log("Visa is the prepaidType\n");
					selectProvidedTextFromDropDown(element("dropDown_paymentMethod"), "Visa");
					waitForLoaderToDisappear();
					String netAmount = String
							.valueOf(Double.parseDouble(element("text_netPayment").getText()) * amount);
					msg.log(netAmount + " is the net payable amount\n");
					enterDetails("inp_paymentAmount", netAmount);
					msg.log(keyValue(paymentDetails, "cardHolderName") + " is the cardHolder name\n");
					enterDetails("cardholderName", keyValue(paymentDetails, "cardHolderName"));
					waitForLoaderToDisappear();
					msg.log(keyValue(paymentDetails, "creditCardNumber") + " is the creditCard number\n");
					enterDetails("creditCardNumber", keyValue(paymentDetails, "creditCardNumber"));
					waitForLoaderToDisappear();
					msg.log("selecting " + keyValue(paymentDetails, "expiryDate") + " From expiryDate \n");
					selectProvidedTextFromDropDown(element("dropDown_expiryDate"),
							keyValue(paymentDetails, "expiryDate"));
					waitForLoaderToDisappear();
					msg.log("selecting " + keyValue(paymentDetails, "cvv") + " is the CVV no. \n");
					enterDetails("cvv", keyValue(paymentDetails, "cvv"));
					break;

				case "Check":
					paymentDetails = YamlReader.getYamlValues("Check");
					msg.log("Check is the prepaidType\n");
					selectProvidedTextFromDropDown(element("dropDown_paymentMethod"), "Check");
					waitForLoaderToDisappear();
					dynamicWait(5, "text_netPayment");
					isElementDisplayed("text_netPayment");
					String amnt = element("text_netPayment").getText();
					netAmount = Double.toString(Double.parseDouble(amnt) * amount);
					dynamicWait(5, "inp_paymentAmount");
					msg.log(netAmount + " is the net payable amount\n");
					enterDetails("paymentAmount", netAmount);
					waitForLoaderToDisappear();
					dynamicWait(5, element("inp_nameOnCheck"));
					msg.log(keyValue(paymentDetails, "name on check") + " is the name on check\n");
					enterDetails("nameOnCheck", keyValue(paymentDetails, "name on check"));
					waitForLoaderToDisappear();
					String checkNumber = generateRandomUniqueOutput(999999, 111111);
					dataStorage.put("checkNumber", checkNumber);
					msg.log(checkNumber + " is the number on check\n");
					enterDetails("checkNumber", checkNumber);
					waitForLoaderToDisappear();
					break;
				}
			}
			msg.log("user selects payment type " + item + " is complete\n");
		} catch (StaleElementReferenceException e) {
			msg.log("fees is not required \n");
		} finally {
			int timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));
			wait.resetImplicitTimeout(timeOut);
		}
	}

	public boolean selectBatch(String batch) {
		boolean flag = selectProvidedTextFromDropDown(element("dropDown_batch"), batch);
		msg.log(batch + " batch is selected\n");
		return flag;
	}

	public WebElement getElementFromListUsingText(String list_element, String item) {
		for (WebElement element : elements(list_element)) {
			if (element.getText().contains(item)) {
				return element;
			}
		}
		return null;
	}
	
	public void clickOnButton_(String button){
		click(element("btn_saveCancel",button));
		msg.actions(button+" button is clicked\n");
	}
}
