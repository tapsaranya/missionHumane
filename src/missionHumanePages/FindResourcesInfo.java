package missionHumanePages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import missionHumaneScripts.DriverScript;

public class FindResourcesInfo {

	WebDriver driver;
	String resourceinfo = "//div[@class='MuiCardContent-root lead-container']";
	String resourceinfoList = "(//div[@class='MuiCardContent-root lead-container'])[VR]";
//	String resinfobkbutton ="//div[@class='back cursor-pointer']";
	String resinfobkbutton = "//img[@alt='back']";
	String noRecords = "//div[text()='No leads available. Search for other cities.']";
	
	public FindResourcesInfo(WebDriver webDriver) {
		this.driver = webDriver;

	}

	public int getResourceinfocount() {
		int getrescount;
		if (driver.findElements(By.xpath(resourceinfo)).size() <= 0) {
			getrescount = 0;
		} else
			getrescount = driver.findElements(By.xpath(resourceinfo)).size();
		return (getrescount);

	}

	public String getResourceinfo(int a) throws InterruptedException {
		String resinfo = driver.findElement(By.xpath(resourceinfoList.replace("VR", String.valueOf(a)))).getText();
		System.out.println("Resource is:"
				+ driver.findElement(By.xpath(resourceinfoList.replace("VR", String.valueOf(a)))).getText());
		return (resinfo);
	}

	public void resinfobackbutton() throws InterruptedException {
		driver.findElement(By.xpath(resinfobkbutton)).click();
		Thread.sleep(5000);
	}

}
