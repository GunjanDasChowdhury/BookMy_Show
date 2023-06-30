package com.BookMyShowPage;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.BaseUD.BaseUd;
import com.ExcelReadWrite.ExcelReadWrite;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class FindSports extends BaseUd {

	public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	public int i;
	public static TakesScreenshot capture = (TakesScreenshot) driver;

	By cancel = By.id("wzrk-cancel");
	By cityList = By.xpath("//*[@id='modal-root']/div/div/div/div[2]/ul/li/div/span");
	//By Citylist1 = By.xpath("//*[@id='modal-root']/div/div/div/div[2]/ul/li[" + (i) + "]");
	By pageList = By.xpath("//a[@class='sc-iGPElx dYqNfN']");
	//By Pagelist1 = By.xpath("//a[" + (i + 1) + "][@class='sc-iGPElx dYqNfN']");
	By weekend = By.xpath(
			"//*[@id=\"super-container\"]/div[2]/div[4]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/div/div");
	By name = By.xpath("//*[@id='super-container']/div[2]/div[4]/div[2]/div/div/div/div/a/div/div[3]/div[1]/div");
	By price = By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div/div/div/div/a/div/div[3]/div[4]/div");
	By priceSelection = By.xpath("(//div[normalize-space()='Price'])[1]");
	By zeroToFivehundred = By.xpath("(//div[contains(text(),'0 - 500')])[2]");
	By Weekendfilter = By.xpath("//div[2][@class='sc-1ljcxl3-0 iUuHNJ']//a");
	By sportsname = By.xpath("(//div[2][@class='sc-1ljcxl3-0 iUuHNJ']//a)[" + (i + 1) + "]");
	By sportsname1 = By.xpath("(//div[2][@class='sc-1ljcxl3-0 iUuHNJ']//a)[" + (i + 1) + "]");
	By Title = By.xpath(
			"(//div[@class='df-b df-c df-d df-e df-f df-g df-h df-i df-j']/div[1]/div[2]//div[1]/header/div/div/div/div/div)[1]");
	By Date = By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div");
	By Price = By
			.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div[1]");

	@Test(priority = 2)
	public void cancelPopUp() throws Exception {

		exttest = report.createTest("Cancelling Popup");
		wait(30, cancel); // webdriver wait
		driver.findElement(cancel).click();
		exttest.log(Status.PASS, "Cancelled Popup Successfully");
	}

	@Test(priority = 3)
	public void selectCity() throws Exception {
		exttest = report.createTest("Compare and Select the city using Excel read");
		wait(30, cityList);
		Actions action = new Actions(driver);
		List<WebElement> cities = driver.findElements(cityList);
		String c = ExcelReadWrite.readExcel(0, 0);
		c1: for (i = 1; i <= cities.size(); i++) {
			WebElement l = driver.findElement(By.xpath("//*[@id='modal-root']/div/div/div/div[2]/ul/li[" + (i) + "]"));
			String s = l.getText();
			if (s.equalsIgnoreCase(c)) {
				//l.click();
				action.moveToElement(l).click().perform();
				break c1;
			}
		}

		exttest.log(Status.PASS, "Selected City Successfully");
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile1 = capture.getScreenshotAs(OutputType.FILE);
		File destFile1 = new File(System.getProperty("user.dir") + "/Screenshot/Selected city.png");
		Files.copy(srcFile1, destFile1);
		exttest.log(Status.PASS, "Captured Screenshot Successfully");
	}

	@Test(priority = 4)
	public void selectSportPage() throws Exception {
		exttest = report.createTest("Compare and Select the Sports page using Excel read");
		wait(30, pageList);
		Actions action = new Actions(driver);
		List<WebElement> pages = driver.findElements(pageList);
		String m = ExcelReadWrite.readExcel(1, 0);
		c2: for ( i = 1; i <= pages.size(); i++) {
			WebElement K = driver.findElement(By.xpath("//a[" + (i + 1) + "][@class='sc-iGPElx dYqNfN']"));
			String s = K.getText();

			if (s.equalsIgnoreCase(m)) {
				action.moveToElement(K).click().perform();
				break c2;
			}
		}
		exttest.log(Status.PASS, "Selected Sports page Successfully");
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile2 = capture.getScreenshotAs(OutputType.FILE);
		File destFile2 = new File(System.getProperty("user.dir") + "/Screenshot/Selected sports.png");
		Files.copy(srcFile2, destFile2);
		exttest.log(Status.PASS, "Captured Screenshot Successfully");
	}

	@Test(priority = 5)
	public void selectWeekend() throws Exception {
		exttest = report.createTest("Select 'This Weekend' from the Date filter");
		wait(30, weekend);
		// for selecting This weekend option
		driver.findElement(weekend).click();
		exttest.log(Status.PASS, "'This Weekend' selected Successfully");
	}

	@Test(priority = 6)
	public void selectPrice() throws Exception {
		exttest = report.createTest("Select '0-500' from the Price filter");
		wait(30, priceSelection);
		// selecting price 0-500
		driver.findElement(priceSelection).click();
		wait(30, zeroToFivehundred);
		driver.findElement(zeroToFivehundred).click();
		exttest.log(Status.PASS, "Zero to five hundred selected successfully");
	}

	@Test(priority = 7)
	public void sortedSportsList() throws Exception {
		exttest = report.createTest("Display Sports Activities of this weekend");
		// List all the sports available for this weekend and price between 0-500
		System.out.println("=========================================================================");
		System.out.println("Display sports activities. ");
		exttest.log(Status.PASS, "Captured the List of sports Activities present");
		List<WebElement> weekendfilter = driver.findElements(Weekendfilter);
		for (i = 0; i < weekendfilter.size(); i++) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(sportsname));
			jse.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("(//div[2][@class='sc-1ljcxl3-0 iUuHNJ']//a)[" + (i + 1) + "]")));
			String title = driver.findElement(Title).getText();
			String d = driver.findElement(Date).getText();
			String[] date = d.split("-");
			String p = driver.findElement(Price).getText();
			String[] price = p.split(" ");
			int priceint = Integer.parseInt(price[1]);
			map.put(title + " " + date[0], priceint);
			driver.navigate().back();
		}
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		for (Entry<String, Integer> item : list) {
			System.out.println(item);
		}
		exttest.log(Status.PASS, "Sorted the Sports list from low to high based on the Price.");
		System.out.println("Sport activities are displayed Successfully");
		System.out.println("=========================================================================");
	}
}