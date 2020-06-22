package practiceForm;

import org.testng.annotations.Test;

import Constant.File_Path;

import pageObjects.practiceForm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class happyPath {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		System.setProperty("webdriver.gecko.driver", File_Path.geckoDriver);
		FirefoxOptions options = new FirefoxOptions();
		options.setBinary(File_Path.fireFox);
		driver = new FirefoxDriver(options);
		driver.get("http://www.demoqa.com/automation-practice-form");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void submitForm() {

		practiceForm objects = new practiceForm(driver);
		objects.givenName("Scott");
		objects.surname("McKenzie");
		objects.email("testing@test.co.uk");
		objects.gender_Male();
		objects.contactNumber("07123456789");
		objects.hobbies_sport();
		objects.address("21 Test Street \nTest Town");
		objects.submitForm();

		WebElement modalHeading = objects.modal();
		WebElement actualText = modalHeading;
		String expectedText = "Thanks for submitting the form";

		Assert.assertEquals(actualText, expectedText);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
