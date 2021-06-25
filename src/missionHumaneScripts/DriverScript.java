package missionHumaneScripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Utils.ExcelFunctions;
import missionHumanePages.DistrictPage;
import missionHumanePages.FindResourcesInfo;
import missionHumanePages.HomePage;
import missionHumanePages.ResourcePage;

public class DriverScript {

	public static WebDriver driver;
	public static Properties prop;

	public DriverScript() {
		File file = new File("./config/config.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println("Unable to find config file " + e.getMessage());
		}
	}

	@BeforeTest
	public static void initBrowser() {
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Browser/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./BrowserEXENew/geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.out.println("Unsupported Browser name");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void scrapping() {
		HomePage homePage = new HomePage(driver);
		DistrictPage districtPage = new DistrictPage(driver);
		ResourcePage resourcePage = new ResourcePage(driver);
		FindResourcesInfo resourceinfo = new FindResourcesInfo(driver);

		int statescount;
		ArrayList<String> outputLt = new ArrayList<String>();
		ArrayList<String> inputLt = new ArrayList<String>();
		try {
			statescount = homePage.getstatescount();

			for (int i = 1; i <= 2; i++) {
				String state = null;
				//
				homePage.getState(i);
				state = homePage.getState(i) + ";;";

				homePage.clickState(i);

				int distcount = districtPage.getdistrictcount();

				Thread.sleep(1000);
				for (int j = 1; j <= 2; j++) {
					String district = null;
					district = districtPage.getDistrict(j) + ";;";

					districtPage.clickDistrict(j);
//					Thread.sleep(1000);
					int rescount = resourcePage.getresourcecount();
					for (int k = 1; k <= 2; k++) {
						String resource = null;
						resource = resourcePage.getResource(k) + ";;";

						resourcePage.getResource(k);
						resourcePage.clickfindResource(k);
//						Thread.sleep(2000);
						int getrescount = resourceinfo.getResourceinfocount();
						if (getrescount == 0) {
							String finalData = null;
							finalData = state + district + resource + "No data found"+";;";
							outputLt.add(finalData);
						} else {

							for (int a = 1; a <= 2; a++) {

								String finalData = null;
								finalData = state + district + resource + resourceinfo.getResourceinfo(a) + ";;";
								outputLt.add(finalData);
//								Thread.sleep(2000);
							}
						}
						System.out.println(outputLt);
						resourceinfo.resinfobackbutton();
					}
					resourcePage.backbutton();
				}
				districtPage.disbkbutton();
			}

		}

		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExcelFunctions exlFn = new ExcelFunctions();
		exlFn.writeExcel("C:\\Users\\Venkatadri\\eclipse-workspace\\com.MissionHumane\\Files\\Output.xlsx", "Data",
				"State,District,Resource,ResourceInfo", outputLt);
		inputLt = exlFn.readExcel("C:\\Users\\Venkatadri\\eclipse-workspace\\com.MissionHumane\\Files\\Input.xlsx",
				"Data");

		if (inputLt.equals(outputLt)) {
//			Assert.assertTrue(true,"Both files are same");
			Reporter.log("Both input and output files are same");
		} else {
//			Assert.assertTrue(false,"Both files are not same");
			Reporter.log("Input and output files are not same");
		}
	}

	@BeforeTest
	public static void launchUrl() {
		String url = prop.getProperty("qaurl");
		driver.get(url);
	}

	@AfterTest
	public static void closeApp() {
		driver.close();
	}
}