package com.assign.qa.testcases;

import com.assign.qa.base.TestBase;
import com.assign.qa.pages.FlightInfoPage;
import com.assign.qa.pages.LoginPage;
import com.assign.qa.util.utilFunctions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.Map;

public class LoginTest extends TestBase {

public static LoginPage login;
	
	@BeforeClass
	public void initTest() {
		login = new LoginPage();
	}
	
	@Test(priority=0)
	public void parentTest() {	
		test = extent.createTest("Validate invalid login");
		test.assignCategory("Functional Test");
		childTest = test.createNode("create to keep 'invalidLoginTest()' as a child test.");
	}


	@Test(priority=1)
	public void invalidLoginTest() throws Exception {

		childTest = test.createNode("Invalid Login Test ");
		test.assignCategory("Functional Test");
		SoftAssert validateInvalidLogin = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		login.getLoginIcon().click();
		login.getUserName().sendKeys("abc@gmail.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[@data-cy='continueBtn']"))));
		login.getContinueButton().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		login.getPassword().sendKeys("test2345");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-cy='login']")));
		login.getLoginButton().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='serverError']")));
		String invalidLogin = login.getErrorText().getText();
		if(invalidLogin.equalsIgnoreCase(("Looks like we are facing some technical issues, please try again in some time."))) {
			validateInvalidLogin.assertEquals("Looks like we are facing some technical issues, please try again in some time.", invalidLogin);
			childTest.log(Status.INFO,"Invalid login validation success");
		}
		else {
			validateInvalidLogin.assertEquals("Your password has expired. Please click on Reset Password to reset your password.", invalidLogin);
			childTest.log(Status.INFO,"Invalid login validation success");
		}
		validateInvalidLogin.assertAll();
	}

	@Test(priority=2)
	public void validLoginTest() throws Exception {

		childTest = test.createNode("Valid Login Test ");
		test.assignCategory("Functional Test");
		SoftAssert validateValidLogin = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		login.getLoginIcon().click();
		login.getUserName().sendKeys("chalakwala.ravi0@gmail.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[@data-cy='continueBtn']"))));
		login.getContinueButton().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		login.getPassword().sendKeys("R9909207136$");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-cy='login']")));
		login.getLoginButton().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@data-cy='commonModal']")));
		String text = driver.findElement(By.xpath("//p[@data-cy='commonModal']")).getText();
		validateValidLogin.assertEquals("Verify Your E-mail ID", text);
		childTest.log(Status.INFO,"Valid login validation success");
		validateValidLogin.assertAll();
	}
	


	@AfterMethod
	 public void checkResult(ITestResult result) throws Exception   {
	  
		if (result.getStatus() == ITestResult.FAILURE) {
			
			childTest.log(Status.FAIL, result.getName()+" Test Case Failed");
//			   test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
//			   test.fail(MarkupHelper.createLabel(result.getName()+" Test case Failed", ExtentColor.RED));

			   String screenshotPath = utilFunctions.getScreenshot(result.getName());
			   childTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			   childTest.addScreenCaptureFromPath(screenshotPath);
			   
		} else if (result.getStatus() == ITestResult.SKIP) {
			  //test.log(Status.SKIP, result.getName()+ " Test Case Skipped");
			childTest.skip(MarkupHelper.createLabel(result.getName()+" Test case Skipped", ExtentColor.YELLOW));
			childTest.skip(result.getThrowable());
			  
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			  //test.log(Status.PASS, result.getName()+ " Test Case Passed");
			childTest.pass(MarkupHelper.createLabel(" Test case passed", ExtentColor.GREEN));
		  }
	 
	 }
	
	
}
