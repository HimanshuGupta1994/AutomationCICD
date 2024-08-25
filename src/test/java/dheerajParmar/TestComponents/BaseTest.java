package dheerajParmar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dheerajParmar.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

//	public Properties prop;

	public WebDriver initializeDriver() throws IOException {
		// we created GlobalData file which will have all generaly required data to run
		// test and to use it we need object of Properties classs
		Properties prop = new Properties();
		// Now requires to get GobalData.properties file which we can get as inputStream
		// using FileInputStream class
		// we need to give path of file to use it
		String file = System.getProperty("user.dir")

				+ "\\src\\main\\java\\dheerajParmar\\resources\\GlobalData.properties";

		FileInputStream fis = new FileInputStream(file);
//		FileInputStream fis = new FileInputStream(
//				System.getProperty("user.dir") + "\\src\\main\\java\\dheerajParmar\\resources\\GlobalData.properties");
		prop.load(fis);
		//if we are running from CMD and given particular browser also in command than we need to handle for that as well
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions(); // This we will use if we want to run test in headlss mode
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			this.driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //we can use this also instead of maximize method for browser.
		} else if (browserName.contains("firefox")) {
			
			// set propertiesfile for firefox driver and initialise Webdriver object with
			// fireFox driver.
			
		} else if (browserName.contains("edge")) {
			
			// set propertiesfile for edge driver and initialise Webdriver object with edge
			// driver.
			// sample:
//			System.setProperty("webdriver.edge.driver", "edge.exe");
			WebDriverManager.edgedriver().setup();
			this.driver = new EdgeDriver();
			driver = new EdgeDriver();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string

		String jsonContent = FileUtils
				.readFileToString(
						(new File(filePath)),
						StandardCharsets.UTF_8);

		// string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
		
		//now we have list of hashmap

	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
