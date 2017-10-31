package com.qait.automation.TestAssociationFinancialProfessional;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qait.automation.TestSessionInitiator;

public class TestBase {

	TestSessionInitiator test;
	
	/** Intializing the objects and driver **/
	@BeforeClass
	@Parameters({ "browser", "os" })
	public void startTestSession(@Optional("firefox") String browser, @Optional("http://127.0.0.1:4444/wd/hub") String os) {
		test = new TestSessionInitiator(this.getClass().getSimpleName(), browser, os);
		test.classStartMessage(this.getClass().getSimpleName());
	}

	
	/** Displaying the method of name to be executed **/
	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

	/** Taking screenshot of failures **/
	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result) {
		test.takescreenshot.takeScreenShotOnException(result);
	}

	/** Closing the browser sessions **/
	
//	  @AfterClass 
//	  public void close_Test_Session() {
//	  test.closeBrowserSession(); 
//	  }
	 
	
}
