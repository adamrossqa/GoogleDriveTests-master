package com.qa.pages;

import org.openqa.selenium.By;

public class HomePage extends AbstractPage {

	private static final By NEW_FOLDER_LOCATOR = By.xpath("//div[contains(text(),'NewOne')]");
	private static final By FILE_LOCATOR = By.xpath("(//img[@class='l-u-Ab-zb-Ua'])[2]");
	private static final By GOOGLE_DRIVE_LOCATOR = By.xpath("//span[@data-tooltip='My Drive']");
	private static final By GOOGLE_DRIVE_LOCATOR2 = By.xpath("//span[@data-tooltip='My Drive']");

	public void newFolderCreation() {
		browser.rightClickAndNewFolderCreation(prop.getProperty("newFolderName"));
	}
	public void dragAndDropeFile(){
		browser.dragAndDrop(FILE_LOCATOR,NEW_FOLDER_LOCATOR);
	}
	public void openNewFolder(){
		browser.openNewFolder(NEW_FOLDER_LOCATOR);
	}
	public void relocateFileFromFolderToMyDrive(){
		browser.dragAndDrop(FILE_LOCATOR,GOOGLE_DRIVE_LOCATOR);
	}
	public void openDrive(){
		browser.openMyDrive(GOOGLE_DRIVE_LOCATOR2);
		browser.refresh();
	}
	public void refreshPage(){
		browser.refresh();
	}


	}

