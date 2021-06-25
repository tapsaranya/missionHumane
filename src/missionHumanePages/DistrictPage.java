package missionHumanePages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import missionHumaneScripts.DriverScript;

public class DistrictPage {

	String district = "//div[@class='location-row']";
	String districtList = "//div[@class='location-row'][VR]";
	String disbkbutton = "//div[text()='MissionHumane.org']";

	WebDriver driver;

	// Creating a constructor and passing driver parameter to this(constructor
	// overloading)

	public DistrictPage(WebDriver webDriver) {
		this.driver = webDriver;

	}

	// Method

	public int getdistrictcount() throws InterruptedException {
		int distcount = driver.findElements(By.xpath(district)).size();
		return (distcount);

	}

	public void clickDistrict(int j) throws InterruptedException {
		System.out.println(
				"District is:" + driver.findElement(By.xpath(districtList.replace("VR", String.valueOf(j)))).getText());
		driver.findElement(By.xpath(districtList.replace("VR", String.valueOf(j)))).click();
//		Thread.sleep(5000);

	}

	public void disbkbutton() throws InterruptedException {
		driver.findElement(By.xpath(disbkbutton)).click();
//		Thread.sleep(5000);
	}

	public String getDistrict(int j) {
		String district = driver.findElement(By.xpath(districtList.replace("VR", String.valueOf(j)))).getText();
		return (district);
	}
}
