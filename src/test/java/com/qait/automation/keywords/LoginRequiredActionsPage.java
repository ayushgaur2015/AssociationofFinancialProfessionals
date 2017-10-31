package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;
import com.qait.automation.utils.YamlReader;
import com.qait.automation.utils.msg;

public class LoginRequiredActionsPage extends GeneralActionsPage {

	public LoginRequiredActionsPage(WebDriver driver) {
		super(driver,"LoginRequiredActionsPage");
	}

	/**
	 * workflow user login into application as an existing user
	 * @param email - user provided email id
	 * @param password - user provided password
	 */
	public void userLogIntoApplicationAsExistingUser(String email, String password){
		enterDetails("email",email);
		enterDetails("password",password);
		userClickOn("btn_continue_cancel","Continue");
		msg.pass("User successfully logged in as an existing user\n");
	}

	/**
	 * workflow user login into application as non existing user
	 * @param firstName - user provided firstname of the non existant user
	 * @param middleName - user provided middlename of the non existing user
	 */
	public void userLogIntoApplicationAsNotExistingUser(String firstName, String middleName){
		String lastName = ""+System.currentTimeMillis();
		dataStorage.put("lastName", lastName);
		String emailExt = YamlReader.getData("emailExt");
		String email = firstName+middleName+lastName+"@"+emailExt;
		dataStorage.put("email",email);
		enterDetails("email",email);
		userClickRadio("radio_yes_no","No");
		userClickOn("btn_continue_cancel","Continue");
		msg.actions(email+"User successfully entered as a non existing user\n");
	}

	/**
	 * method to verify user on Eweb Login page
	 */
	public void verifyUserOnLoginPage() {
		verifyPageTitleContains();
		msg.actions("verified user on login page\n");
	}	
}
