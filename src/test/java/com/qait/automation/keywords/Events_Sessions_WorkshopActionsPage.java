package com.qait.automation.keywords;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.utils.msg;

public class Events_Sessions_WorkshopActionsPage extends GeneralActionsPage {

	public Events_Sessions_WorkshopActionsPage(WebDriver driver) {
		super(driver, "Events_Sessions_WorkshopActionsPage");
	}

	public void userVerifyWorkshopTitle() {
		dynamicWait(5, "text_workshopTitle");
		String[] workshopTitle = element("text_workshopTitle").getText().split(" ");
		for (int i = 0; i < 2; i++) {
			Assert.assertTrue(keyValue(dataStorage, "Session").contains(workshopTitle[i]),
					msg.failForAssert(keyValue(dataStorage, "Session") + " is the expected and "
							+ element("text_workshopTitle").getText()
							+ " is the actual Session workshop name is not verified\n"));
		}
		msg.pass("Verified session workshop title\n");
	}

	public void userVerifySessionTitle(String title) {

		String[] workshopTitle = element("text_workshopTitle").getText().split(" ");
		for (int i = 0; i < 2; i++) {

			Assert.assertTrue(title.contains(workshopTitle[i]),
					msg.failForAssert(title + " is the expected and " + element("text_workshopTitle").getText()
							+ " is the actual Session workshop name is not verified\n"));
		}
		msg.pass("Verified session workshop title\n");
	}

	public void userDeletesSessions() {
		int sessionCount = 0;
		int sessionsListCount = elements("list_deleteRecordIcon").size();
		if (sessionsListCount != 0) {
			msg.log(elements("list_text_eventSessCreditsTitle").get(sessionCount).getText().replace("–", "-")
					+ " session is deleted from the event session credit list\n");
			msg.log(elements("list_text_eventSessCreditsProgram").get(sessionCount).getText()
					+ " is the program of the sesion deleted from the event session credit list\n");
			elements("list_deleteRecordIcon").get(0).click();
			handleAlert();
			userDeletesSessions();
		} else {
			msg.log("all sessions are deleted\n");
		}

	}

	public void userVerifySessionTitleListIsEmtpy_() {
		Assert.assertTrue(verifyMessageInTheView("event session credits", "There are no results to display"),
				msg.failForAssert("Verification failed user title list is not empty\n"));
		msg.pass("verified session title list is empty\n");
	}

	public void userVerifySessionTitleListIsNotEmtpy_() {
		Assert.assertFalse(verifyMessageInTheView("event session credits", "There are no results to display"),
				msg.failForAssert("Verification failed user title list is empty\n"));
		msg.pass("verified session title list is not empty\n");
	}

	public boolean verifyMessageInTheView(String view, String Message) {
		try {
			isElementDisplayed("text_message", Message);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void userVisitsParentEventProfile() {
		dynamicWait(5, "link_parentEvent");
		isElementDisplayed("link_parentEvent");
		element("link_parentEvent").click();
		msg.actions("parent event link is clicked\n");
	}

}
