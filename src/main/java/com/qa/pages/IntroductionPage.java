package com.qa.pages;


import org.openqa.selenium.By;

public class IntroductionPage extends AbstractPage {

	private static final By SIGNIN_BUTTON_LOCATOR = By.xpath("//a[@data-g-event=\"Drive\" and @data-g-action=\"Intro\"]");

	public IntroductionPage open() {
		browser.open(prop.getProperty("googleWelcomePage"));
		return new IntroductionPage();
	}
	public LoginPage clickSignInButton() {
		browser.click(SIGNIN_BUTTON_LOCATOR);
		return new LoginPage();
	}
}
