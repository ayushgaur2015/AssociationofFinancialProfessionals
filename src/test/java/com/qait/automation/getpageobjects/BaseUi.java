/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getPageTitleFromFile;
import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.SeleniumWait;
import com.qait.automation.utils.msg;



/**
 *
 * @author QAIT
 */
public class BaseUi {

	WebDriver driver;
	protected SeleniumWait wait;
	protected ConfigPropertyReader data;
	private String pageName;
	public String dataProperties = "D:"+File.separator+"New workspace"+File.separator+
			"AssociationFinancialProfessional"+File.separator+"src"+File.separator+"test"+File.separator+
			"resources"+File.separator+"testdata"+File.separator+"Data.properties";
	
	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty(
				"config.properties", "timeout")));
		this.data = new ConfigPropertyReader();
		
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	protected void logMessage(String message) {
		Reporter.log("[INFO]:"+message, true);
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected void verifyPageTitleExact() {
		String pageTitle = getPageTitleFromFile(pageName);
		verifyPageTitleExact(pageTitle.trim());
	}

	protected void verifyPageTitleExact(String expectedPagetitle) {
		if ((
			(expectedPagetitle=="") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))  && (getProperty("browser").equalsIgnoreCase("chrome"))){
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToBeExact(expectedPagetitle);
			logMessage("TEST PASSED: PageTitle for " + pageName + " is exactly: '"
					+ expectedPagetitle + "'");
		} catch (TimeoutException ex) {
			Assert.fail("TEST FAILED: PageTitle for " + pageName + " is not exactly: '"
					+ expectedPagetitle + "'!!!\n instead it is :- " + driver.getTitle());
		}
	}
	
	
	public void getCurrentWindow(){
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	}
	
	public void changeWindow(int i) {
	   if(driver.toString().contains("safari")){
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND+"~");  
	   }
	   else{
		Set<String> windows = driver.getWindowHandles();
	    if (i > 0) {
	     for (int j = 0; j < 5; j++) {
	      if (windows.size() < 2) {
	       try {
	        Thread.sleep(2000);
	       } catch (Exception ex) {
	        ex.printStackTrace();
	       }
	      } else {
	       break;
	      }
	     }
	     windows = driver.getWindowHandles();
	    }
	    String wins[] = windows.toArray(new String[windows.size()]);
	    driver.switchTo().window(wins[i]);

	    while (driver.getTitle().equalsIgnoreCase("about:blank")
	      || driver.getTitle().equalsIgnoreCase("")) {
	     wait.hardWait(2);
	    }
	    }
	   }
	
	public void changeTab(int onTab){
	    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTab.get(onTab));

	}

	/**
	 * Verification of the page title with the title text provided in the page
	 * object repository
	 */
	protected void verifyPageTitleContains() {
		String expectedPagetitle = getPageTitleFromFile(pageName).trim();
		verifyPageTitleContains(expectedPagetitle);
	}

	/**
	 * this method will get page title of current window and match it partially
	 * with the param provided
	 *
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	protected void verifyPageTitleContains(String expectedPagetitle) {

		if ((
					(expectedPagetitle=="") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))
					&& (getProperty("browser").equalsIgnoreCase("chrome"))){
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
		} catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			Assert.assertTrue(false,msg.failForAssert("As actual Page Title: '" + actualPageTitle
					+ "' does not contain expected Page Title : '"
					+ expectedPagetitle + "'.\n"));
		}
		String actualPageTitle = getPageTitle().trim();
		msg.pass("PageTitle for " + actualPageTitle + " contains: '"
				+ expectedPagetitle + "'.\n");
	}

	protected WebElement getElementByIndex(List<WebElement> elementlist,
			int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}

	protected WebElement getElementByContainsText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}

	protected void switchToFrame(WebElement element) {
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
	}

	public void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}

	public void switchToFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	protected  Object executeJavascriptWithReturnType(String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}
	
	protected  List<String> executeJavascriptWithReturnTypes(String script) {
		return (List<String>)((JavascriptExecutor) driver).executeScript(script);
	}
	
	protected void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
		wait.hardWait(1);
		hoverOver.moveToElement(element, 5, 5).build().perform();
	}

	protected void handleAlert() {
		try {
			switchToAlert().accept();
			msg.log("Alert handled..\n");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			msg.log(" No Alert window appeared...\n");
		}
	}

	private Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	protected boolean selectProvidedTextFromDropDown(WebElement el, String text) {
		boolean flag=false;
		wait.waitForElementToBeVisible(el);
		Select sel = new Select(el);
		try {
			sel.selectByVisibleText(text);
		} catch (StaleElementReferenceException ex1) {
			sel.selectByVisibleText(text);
			logMessage("select Element " + el
					+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
      sel.selectByVisibleText(text);
		}
		return true;
	}
	
	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().perform();
	}

	protected void click(WebElement element) {
		if(driver.toString().contains("internet explorer")){		  
			clickUsingXpathInJavaScriptExecutor(element);
		}else{
		try {
     		scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			
			element.click();
			wait.hardWait(2);
			msg.actions("Clicked Element " + element
					+ " after catching Stale Element Exception \n");
		} catch (Exception ex2) {
			msg.fail("Element " + element + " could not be clicked! "
					+ ex2.getMessage()+"\n");
		}
	}
  }
	
	protected void clickAndHold(WebElement element){
		Actions action = new Actions(driver);
		action.clickAndHold(element).click().build().perform();

	}
	
	protected void hoverjs(WebElement element){
           JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("arguments[0].onmouseover()", element); 
	}
	
	protected String getSelectedTextFromDropDown(WebElement el) {
		try {
			wait.waitForElementToBeVisible(el);
			scrollDown(el);
			Select sel = new Select(el);
			return sel.getFirstSelectedOption().getText();

		} catch (StaleElementReferenceException ex1) {
			// wait.waitForElementToBeVisible(el);
			// scrollDown(el);
			Select sel = new Select(el);
			logMessage("get selected Element " + el
					+ " after catching Stale Element Exception");
			return sel.getFirstSelectedOption().getText();

		} catch (Exception ex2) {
			logMessage("Element " + el + " could not be clicked! "
					+ ex2.getMessage());
			return null;
		}
	}
	
	public void checkCheckbox(WebElement ele) {
		if (!ele.isSelected()) {
			clickUsingXpathInJavaScriptExecutor(ele);
			msg.actions("check checkbox \n");
		} else {
			msg.actions("check box is already selected\n");
		}
	}
	
	public void clickUsingXpathInJavaScriptExecutor(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickUsingXpathInJavaScriptExecutor(WebElement element,String item) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	protected void verifySelectedTextFromDropDown(WebElement el, String text) {
		Assert.assertTrue(getSelectedTextFromDropDown(el)
				.equalsIgnoreCase(text));
		logMessage("AASERT PASSED : " + text
				+ " is verified which is selected \n");
	}
	
		
	protected String keyValue(Map <String, Object> details, String fieldName){
		if(details.get(fieldName)!="null"||!details.get(fieldName).equals("null")){
		return details.get(fieldName).toString();
		}
		else
			return "null";
	}
	
	public void waitIfBrowserIE(){
		if(driver.toString().contains("internet explorer")){
		wait.hardWait(3);
		}
	}
	
	public void openUrl(String url){
		driver.get(url);
	}
}
