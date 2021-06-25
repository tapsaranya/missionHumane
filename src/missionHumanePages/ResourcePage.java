package missionHumanePages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import missionHumaneScripts.DriverScript;

public class ResourcePage {

	String resource = "//div[@class='sc-eCApnc iylGhi']";
	String resourceList = "(//div[@class='sc-eCApnc iylGhi'])[VR]";
	String findresourcelink = "(//div[@class='sc-gKAaRy hydYaP'])[VR]";
	String bkbutton = "//div[@class='back cursor-pointer']";
	WebDriver driver;

	// Creating a constructor and passing driver parameter to this(constructor
	// overloading)

	public ResourcePage(WebDriver webDriver) {
		this.driver = webDriver;

	}

	public int getresourcecount() throws InterruptedException {
		int rescount = driver.findElements(By.xpath(resource)).size();
		return (rescount);

	}

	public void backbutton() throws InterruptedException {
		driver.findElement(By.xpath(bkbutton)).click();
//		Thread.sleep(5000);
	}

	public String getResource(int k) throws InterruptedException {
		String resource = driver.findElement(By.xpath(resourceList.replace("VR", String.valueOf(k)))).getText();

		System.out.println(
				"Resource is:" + driver.findElement(By.xpath(resourceList.replace("VR", String.valueOf(k)))).getText());
		// driver.findElement(By.xpath(resourceList.replace("VR",
		// String.valueOf(k)))).click();
//		Thread.sleep(5000);
		return (resource);

	}

	public void clickfindResource(int k) throws InterruptedException {
		driver.findElement(By.xpath(findresourcelink.replace("VR", String.valueOf(k)))).click();
		Thread.sleep(2000);
	}

}
