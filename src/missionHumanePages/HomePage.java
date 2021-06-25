package missionHumanePages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import missionHumaneScripts.DriverScript;

public class HomePage {

	String state = "//div[@class='location-row']";
	String statesList = "//div[@class='location-row'][VR]";
	WebDriver driver;

	// Creating a constructor and passing driver parameter to this(constructor
	// overloading)

	public HomePage(WebDriver webDriver) {
		this.driver = webDriver;

	}
	// Method

	public int getstatescount() throws InterruptedException {
		int statescount = driver.findElements(By.xpath(state)).size();

		return (statescount);

	}
	// method to iterate to next state, value of i will be picked from driverscript
	// and VR will be replaced by value of i

	public void clickState(int i) throws InterruptedException {

		System.out.println(
				"State is:" + driver.findElement(By.xpath(statesList.replace("VR", String.valueOf(i)))).getText());
		driver.findElement(By.xpath(statesList.replace("VR", String.valueOf(i)))).click();
//			 Thread.sleep(5000);
	}

	public String getState(int i) {
		String state = driver.findElement(By.xpath(statesList.replace("VR", String.valueOf(i)))).getText();
		return (state);
	}
}
