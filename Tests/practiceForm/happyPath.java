package practiceForm;

import org.testng.annotations.Test;

import Constant.File_Path;
import General.BaseTest;
import ScreenShots.screenCaptureBase64;
import pageObjects.practiceForm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.MediaEntityBuilder;

public class happyPath extends BaseTest {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		logger = extent.createTest(this.getClass().getSimpleName());

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

		String actualText = objects.modalHeader();
		String expectedText = "Thanks for submitting the forms";

		logger.info("Expected text: " + expectedText);
		logger.info("Actual text: " + actualText);

		Assert.assertEquals(actualText, expectedText);

	}

	@AfterMethod
	public void closeBrowser(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			Thread.sleep(1000);
			String temp = screenCaptureBase64.CaptureScreenShot64(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(temp).build());
		}

		extent.flush();
		driver.close();
	}

}
