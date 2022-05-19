package com.assign.qa.pages;

import com.assign.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LoginPage extends TestBase{

	// Pagefactory
	@FindBy(css=".userSection > li:nth-child(3)")
	WebElement loginIcon;

	@FindBy(id="username")
	WebElement userName;

	@FindBy(xpath="//button[@data-cy='continueBtn']")
	WebElement continueButton;

	@FindBy(id="password")
	WebElement password;

	@FindBy(xpath="//button[@data-cy='login']")
	WebElement loginButton;

	@FindBy(xpath="//span[@data-cy='serverError']")
	WebElement errorText;

	//initialization
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLoginIcon() {
		return loginIcon;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getErrorText() {
		return errorText;
	}

	public WebElement getUserName() {
		return userName;
	}
}
