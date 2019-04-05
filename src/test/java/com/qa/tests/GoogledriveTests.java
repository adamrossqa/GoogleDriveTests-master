package com.qa.tests;

import com.qa.base.Browser;
import com.qa.pages.AbstractPage;
import com.qa.pages.HomePage;
import com.qa.pages.IntroductionPage;
import com.qa.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class GoogledriveTests extends AbstractPage {

	@Test
	public void createFolderTest() {
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"),prop.getProperty("password"));
		new HomePage().newFolderCreation();

	}
	@Test
	public void moveFileToNewFolder(){
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"),prop.getProperty("password"));
		new HomePage().newFolderCreation();
		new HomePage().dragAndDropeFile();
		new HomePage().openNewFolder();
		new HomePage().relocateFileFromFolderToMyDrive();
		new HomePage().refreshPage();
	}
	@Test
	public void openNewFolderTest(){
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"),prop.getProperty("password"));
		new HomePage().newFolderCreation();
		new HomePage().openNewFolder();
	}
	@Test
	public void relocateFileFromFolderTest(){
		new HomePage().refreshPage();
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"),prop.getProperty("password"));
		new HomePage().newFolderCreation();
		new HomePage().dragAndDropeFile();
		new HomePage().openNewFolder();
		new HomePage().relocateFileFromFolderToMyDrive();
	}
	@Test
	public void openMyDriveTest(){
		new IntroductionPage().open().clickSignInButton();
		new LoginPage().logIn(prop.getProperty("username"),prop.getProperty("password"));
		new HomePage().newFolderCreation();
		new HomePage().dragAndDropeFile();
		new HomePage().openNewFolder();
		new HomePage().relocateFileFromFolderToMyDrive();
		new HomePage().openDrive();
	}
	@AfterMethod(description = "close browser")
	public void kill() { Browser.kill(); }
}
