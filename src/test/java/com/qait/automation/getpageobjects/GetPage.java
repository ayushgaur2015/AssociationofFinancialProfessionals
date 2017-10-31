package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;
import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import junit.framework.Assert;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.msg;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class GetPage extends BaseUi {

    protected WebDriver webdriver;
    String pageName;
    public static Map <String,Object> dataStorage = new HashMap<String, Object>();
    public static int count = 0;
    public int timeOut=Integer.parseInt(ConfigPropertyReader.getProperty("timeout"));

    public GetPage(WebDriver driver, String pageName) {
        super(driver, pageName);
        this.webdriver = driver;
        this.pageName = pageName;
    }
    
    protected void switchToNestedFrames(String frameNames) {
        switchToDefaultContent();
        String[] frameIdentifiers = frameNames.split(":");
        for (String frameId : frameIdentifiers) {
            wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId
                    .trim()));
        }
    }

     
    protected WebElement element(String elementToken) {
        return element(elementToken, "");
    }

    protected WebElement element(String elementToken, String replacement1, String replacement2)
            throws NoSuchElementException {
        WebElement elem = null;
        try {
            elem = wait.waitForElementToBeVisible(webdriver
                    .findElement(getLocator(elementToken, replacement1, replacement2)));
        } catch (NoSuchElementException excp) {
            fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " !!!");
        }
        return elem;
    }

    protected WebElement element(String elementToken, String replacement)
    		   throws NoSuchElementException {
    		  WebElement elem = null;
    		  By locator = getLocator(elementToken, replacement);
    		  try {
    		   elem = wait.waitForElementToBeVisible(webdriver
    		     .findElement(locator));
    		  } catch (TimeoutException excp) {
    		   throw new NoSuchElementException("Element " + elementToken
    		     + " with locator " + locator.toString().substring(2)
    		     + " not found on the " + this.pageName + " !!!");
    		  }
    		  return elem;
    		 }
   
     
    protected WebElement element(String elementToken, int index)
 		   throws NoSuchElementException {
 		  WebElement elem = null;
 		 String replacement = Integer.toString(index);
 		  By locator = getLocator(elementToken, replacement);
 		  try {
 		   elem = wait.waitForElementToBeVisible(webdriver
 		     .findElement(locator));
 		  } catch (TimeoutException excp) {
 		   throw new NoSuchElementException("Element " + elementToken
 		     + " with locator " + locator.toString().substring(2)
 		     + " not found on the " + this.pageName + " !!!");
 		  }
 		  return elem;
 		 }
    
    protected List<WebElement> elements(String elementToken, String replacement) {
        return webdriver.findElements(getLocator(
                elementToken, replacement));
    }

    protected List<WebElement> elements(String elementToken, String replacement1, String replacement2) {
        return webdriver.findElements(getLocator(elementToken, replacement1, replacement2));
    }

    protected List<WebElement> elements(String elementToken) {
        return elements(elementToken, "");
    }
    
    protected void _waitForElementToDisappear(String elementToken, String replacement) {
    	int i = 0;
        int initTimeout = wait.getTimeout();
        wait.resetImplicitTimeout(2);
        int count;
        while (i <= 20) {
        	if (replacement.isEmpty()) count = elements(elementToken).size();
        	else count = elements(elementToken, replacement).size();
        	if (count == 0) break;
        	i += 2;
        }
        wait.resetImplicitTimeout(initTimeout);
    }

    protected void waitForElementToDisappear(String elementToken){
		_waitForElementToDisappear(elementToken, "");
	}

    protected void waitForElementToDisappear(String elementToken, String replacement){
		_waitForElementToDisappear(elementToken, replacement);
	}
    
    protected void isStringMatching(String actual, String expected) {
        Assert.assertEquals(actual, expected);
        msg.log("ACTUAL STRING : " + actual);
        msg.log("\nEXPECTED STRING :" + expected);
        msg.log("\nString compare Assertion passed.");
    }

    protected boolean isElementDisplayed(String elementName,
            String elementTextReplace) {
        wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
        boolean result = element(elementName, elementTextReplace).isDisplayed();
        assertTrue(result, msg.failForAssert("element '" + elementName
                + "with text " + elementTextReplace + "' is not displayed.\n"));
        msg.pass(" element " + elementName + " with text "
                + elementTextReplace + " is displayed.\n");
        return result;
    }
    
    protected boolean isElementDisplayedWithNoLogs(String elementName,
            String elementTextReplace) {
        wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
        boolean result = element(elementName, elementTextReplace).isDisplayed();
        assertTrue(result, msg.failForAssert("element '" + elementName
                + "with text " + elementTextReplace + "' is not displayed.\n"));
        return result;
    }
    
    protected boolean isElementDisplayed(String elementName,
            int elementTextReplace) {
        wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
        boolean result = element(elementName, elementTextReplace).isDisplayed();
        assertTrue(result, msg.failForAssert("element '" + elementName
                + "with text " + elementTextReplace + "' is not displayed.\n"));
        msg.pass(" element " + elementName + " with text "
                + elementTextReplace + " is displayed.\n");
        return result;
    }
    
    
    protected boolean isElementDisplayed(String elementName,
            String elementTextReplace1, String elementTextReplace2) {
        wait.waitForElementToBeVisible(element(elementName, elementTextReplace1,elementTextReplace2));
        boolean result = element(elementName, elementTextReplace1 , elementTextReplace2).isDisplayed();
        assertTrue(result, msg.failForAssert("element '" + elementName
                + "with text " + elementTextReplace1 + " and "+elementTextReplace2 + "' is not displayed.\n"));
        msg.pass(" element " + elementName + " with text "
                + elementTextReplace1 + " and "+elementTextReplace2 + " is displayed.\n");
        return result;
    }

    protected void verifyElementText(String elementName, String expectedText) {
        wait.waitForElementToBeVisible(element(elementName));
        assertEquals(element(elementName).getText().trim(), expectedText,
                msg.failForAssert(" element '" + elementName
                + "' Text is not as expected: \n"));
        msg.pass(" element " + elementName
                + " is visible and Text is " + expectedText+"\n");
    }
    
    protected void verifyElementTextContains(String elementName, String expectedText) {
        wait.waitForElementToBeVisible(element(elementName));
        assertThat(msg.failForAssert(" element '" + elementName + "' Text is not as expected: \n"), 
                element(elementName).getText().trim(), containsString(expectedText));
        msg.pass(" element " + elementName
                + " is visible and Text is " + expectedText+"\n");
    }

    protected boolean isElementDisplayed(String elementName) {
    	wait.waitForElementToBeVisible(element(elementName));
        boolean result = element(elementName).isDisplayed();
        assertTrue(result, msg.failForAssert(" element '" + elementName
                + "' is not displayed.\n"));
        msg.pass(" element " + elementName
                + " is displayed.\n");
        return result;
    }
    
    protected boolean isElementsDisplayed(String elementName) {
    	wait.waitForElementToBeVisible(element(elementName));
        boolean result = elements(elementName).get(1).isDisplayed();
        assertTrue(result, msg.failForAssert(" element '" + elementName
                + "' is not displayed.\n"));
        msg.pass(" element " + elementName
                + " is displayed.\n");
        return result;
    }
    
    
    protected boolean isElementDisplayedWithNoLogs(String elementName) {
    	wait.waitForElementToBeVisible(element(elementName));
        boolean result = element(elementName).isDisplayed();
        assertTrue(result, msg.failForAssert(" element '" + elementName
                + "' is not displayed.\n"));
        return result;
    }
    
    protected boolean checkIfElementNotDisplayed(String elementName) {
       try{ wait.waitForElementToBeVisible(element(elementName));
        boolean result = element(elementName).isDisplayed();
        if(result==false){
        	msg.failForAssert(" element '" + elementName
                + "' is not displayed.\n");
        }else{
        msg.pass(" element " + elementName
                + " is displayed.\n");}
        return result;}
       catch(NoSuchElementException e){
    	   return false;
       }
    }
    
    protected boolean checkIfElementDisplayed(String elementName) {
        try{ wait.waitForElementToBeVisible(element(elementName));
         boolean result = element(elementName).isDisplayed();
         if(result==false){
         	msg.failForAssert(" element '" + elementName
                 + "' is not displayed.\n");
         }else{
         msg.pass(" element " + elementName
                 + " is displayed.\n");}
         return result;}
        catch(NoSuchElementException e){
     	   return false;
        }
     }
    
    protected boolean checkIfElementNotDisplayed(String elementName, String replacement) {
        try{ wait.waitForElementToBeVisible(element(elementName,replacement));
         boolean result = element(elementName,replacement).isDisplayed();
         if(result==false){
         	msg.failForAssert(" element '" + elementName
                 + "' is not displayed.\n");
         }else{
         msg.pass(" element " + elementName
                 + " is displayed.\n");}
         return result;}
        catch(NoSuchElementException e){
     	   return false;
        }
     }

    
    protected boolean isElementEnabled(String elementName, boolean expected) {
        wait.waitForElementToBeVisible(element(elementName));
        boolean result = expected && element(elementName).isEnabled();
        assertTrue(result, msg.failForAssert(" element '" + elementName
                + "' is  ENABLED :- " + !expected+"\n"));
        msg.pass(" element " + elementName
                + " is enabled :- " + expected+"\n");
        return result;
    }

    /**
	 * Verifies that the element specified is not displayed on Page
	 * @param elementToken : Element locator from PageObject file
	 * @param ticket : Ticket Number if known issue, otherwise empty string
	 * @return
	 */
	protected boolean verifyElementNotDisplayed(String elementToken, String ticket) {
		return _verifyElementNotDisplayed(elementToken, "");
	}

	/**
	 * Verifies that the element specified(with replacement string) is not displayed on Page
	 * @param elementToken : Element locator from PageObject file
	 * @param replacement : Replacement String for Element locator
	 * @param ticket : Ticket Number if known issue, otherwise empty string
	 * @return
	 */
	protected boolean verifyElementNotDisplayed(String elementToken, String replacement, String ticket) {
		return _verifyElementNotDisplayed(elementToken, replacement);
	}
	
	private boolean _verifyElementNotDisplayed(String elementToken, String replacement) {
    	int count;
    	int timeout = wait.getTimeout();
    	wait.resetImplicitTimeout(15);
    	if (replacement.isEmpty()) count = elements(elementToken).size();
    	else count = elements(elementToken, replacement).size();
    	wait.resetImplicitTimeout(timeout);
    	Assert.assertEquals(msg.failForAssert("Element '" + elementToken + "' is displayed"),0, count+"\n");
    	msg.pass("Assertion Passed: Element '" + elementToken + "' is NOT displayed !!!\n");
    	return (count == 0);
    }
	
    protected By getLocator(String elementToken) {
        return getLocator(elementToken, "");
    }

    protected By getLocator(String elementToken, String replacement) {
        String[] locator = getELementFromFile(this.pageName, elementToken);
        locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
        return getBy(locator[1].trim(), locator[2].trim());
    }

   protected By getLocator(String elementToken, String replacement1, String replacement2) {
        String[] locator = getELementFromFile(this.pageName, elementToken);
        locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement1);
        locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement2);
        return getBy(locator[1].trim(), locator[2].trim());
      }
   
    private By getBy(String locatorType, String locatorValue) {
        switch (Locators.valueOf(locatorType)) {
            case id:
                return By.id(locatorValue);
            case xpath:
                return By.xpath(locatorValue);
            case css:
                return By.cssSelector(locatorValue);
            case name:
                return By.name(locatorValue);
            case classname:
                return By.className(locatorValue);
            case linktext:
                return By.linkText(locatorValue);
            default:
                return By.id(locatorValue);
        }
    }
    
    public void dynamicWait(int timeout,String ele){
    	try{
    		if(count<timeout){
        		wait.resetImplicitTimeout(timeout);

        		isElementDisplayedWithNoLogs(ele);
    		}
    		wait.resetImplicitTimeout(timeOut);
    		count=0;
    	}
    	catch(NoSuchElementException e){
    		count++;
    		dynamicWait(timeout,ele);
    	}
    	catch(AssertionError e){
    		count++;
    		dynamicWait(timeout,ele);
    	}
    	catch(StaleElementReferenceException exp){
      		count++;
    		dynamicWait(timeout,ele);
    	}
    }
    
   
    public void dynamicWait(int timeout,WebElement ele){
    	try{
    		if(count<timeout){
        		wait.resetImplicitTimeout(timeout);

        		Assert.assertTrue(ele.isDisplayed());
    		}
    		wait.resetImplicitTimeout(timeOut);
    		count=0;
    	}
    	catch(NoSuchElementException e){
    		count++;
    		dynamicWait(timeout,ele);
    	}
    	catch(AssertionError e){
    		count++;
    		dynamicWait(timeout,ele);
    	}
    	catch(StaleElementReferenceException exp){
      		count++;
    		dynamicWait(timeout,ele);
    	}
    }
    
    public void dynamicWait_withReplacement(int timeout,String ele,String replacement){
    	try{
    		if(count<timeout){
        		wait.resetImplicitTimeout(timeout);

        		isElementDisplayedWithNoLogs(ele,replacement);
    		}
    		wait.resetImplicitTimeout(timeOut);
    		count=0;
    	}
    	catch(NoSuchElementException e){
    		count++;
    		dynamicWait_withReplacement(timeout,ele,replacement);
    	}
    	catch(AssertionError e){
       		count++;
    		dynamicWait_withReplacement(timeout,ele,replacement);
    	}
    	catch(StaleElementReferenceException exp){
    		count++;
    		dynamicWait_withReplacement(timeout,ele,replacement);
    	}

    }
    
    public void dynamicWaitForAnAttribute(int timeout,String ele,String attribute){
    	try{
    		if(count<timeout){
    		wait.resetImplicitTimeout(timeout);
    		isElementDisplayedWithNoLogs(ele);
    		if(element(ele).getAttribute(attribute).equals("")||element(ele).getAttribute(attribute).equals(" ")||element(ele).getAttribute(attribute).isEmpty()||element(ele).getAttribute(attribute).equals(null)){
    			throw new NoSuchElementException(attribute);
    		}
    		}wait.resetImplicitTimeout(timeOut);
    		count=0;
    	}
    	catch(NoSuchElementException e){
    		count++;
    		dynamicWaitForAnAttribute(timeout,ele,attribute);
    	}
    	catch(AssertionError e){
      		count++;
    		dynamicWaitForAnAttribute(timeout,ele,attribute);
		
    	}
    	catch(StaleElementReferenceException exp){
    		count++;
    		dynamicWaitForAnAttribute(timeout,ele,attribute);

    	}

    }
    
    public void dynamicWaitForAnAttribute_withReplacement(int timeout,String ele,String replacement,String attribute){
    	try{
    		if(count<timeout){
    		wait.resetImplicitTimeout(timeout);
    		isElementDisplayedWithNoLogs(ele);
    		if(element(ele,replacement).getAttribute(attribute).equals("")||element(ele,replacement).getAttribute(attribute).equals(" ")||element(ele,replacement).getAttribute(attribute).isEmpty()||element(ele,replacement).getAttribute(attribute).equals(null)){
    			throw new NoSuchElementException(attribute);
    		}
    		}wait.resetImplicitTimeout(timeOut);
    		count=0;
    	}
    	catch(NoSuchElementException e){
    		count++;
    		dynamicWaitForAnAttribute_withReplacement(timeout,ele,attribute,replacement);
    	}
    	catch(AssertionError e){
    		count++;
    		dynamicWaitForAnAttribute_withReplacement(timeout,ele,attribute,replacement);

    	}
    	catch(StaleElementReferenceException exp){
    		count++;
    		dynamicWaitForAnAttribute_withReplacement(timeout,ele,attribute,replacement);
    	}
    }
    
    public void dynamicWaitFor_(String locatorType, String locatorValue, int timeout){
    	WebElement elem = null;
    	try{
       		if(count<timeout){
    		wait.resetImplicitTimeout(timeout);
    		elem = wait.waitForElementToBeVisible(webdriver.findElement(getBy(locatorType,locatorValue)));
    		
    		}wait.resetImplicitTimeout(timeOut);
    		count=0;
    	}
    	catch(NoSuchElementException e){
    		count++;
    		dynamicWaitFor_(locatorType,locatorValue,timeout);
    	}
    	catch(AssertionError e){
    		count++;
    		dynamicWaitFor_(locatorType,locatorValue,timeout);
    	}
    	catch(StaleElementReferenceException exp){
     		count++;
    		dynamicWaitFor_(locatorType,locatorValue,timeout);
    	}
    }
    
    public void clickOnElementUntil_(int timeout,String ele){
    	try{
    		if(count<timeout){
        		wait.resetImplicitTimeout(timeout);

        		isElementDisplayedWithNoLogs(ele);
        		clickUsingXpathInJavaScriptExecutor(element(ele));
    		}
    		wait.resetImplicitTimeout(timeOut);
    		count=0;
    	}
    	catch(NoSuchElementException e){
    		count++;
    		dynamicWait(timeout,ele);
    	}
    	catch(AssertionError e){
    		count++;
    		dynamicWait(timeout,ele);
    	}
    	catch(StaleElementReferenceException exp){
     		count++;
    		dynamicWait(timeout,ele);
    	}

    }

}
