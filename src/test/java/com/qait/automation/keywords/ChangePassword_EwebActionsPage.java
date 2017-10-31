package com.qait.automation.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.utils.msg;

public class ChangePassword_EwebActionsPage extends GeneralActionsPage {

	public ChangePassword_EwebActionsPage(WebDriver driver) {
		super(driver, "ChangePassword_EwebActionsPage");
	}
	
	/**
	 * workflow to enter new passowrd and click on done
	 * @param password - user entered individual new password 
	 */
	public void changePassword(String password){
		for(int i=0;i<2;i++){
			enterDetails("newPassword", password);
			enterDetails("cnfrmNewPassword", password);
			click(element("btn_saveCancel","Change Password"));
			msg.actions("clicked on change password button\n");
		}
		click(element("btn_saveCancel"," Done "));
		msg.actions("Done button is clicked \n");
	}
}
