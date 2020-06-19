/**
 * 
 */
package practiceForm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Constant.File_Path;

/**
 * @author Mck3nz1e-PC
 *
 */
public class Test {
	
	static WebDriver driver;
	

	public static void main (String[]args) {
		
		System.setProperty("webdriver.gecko.driver", File_Path.geckoDriver);
		FirefoxOptions options = new FirefoxOptions();
		options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver(options);
		driver.get("https://github.com/mozilla/geckodriver/releases");
		
	}

}