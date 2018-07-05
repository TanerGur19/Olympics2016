package com.olympics;

import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Olympics16New {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test(priority = 1)
	public void sortSet() throws InterruptedException {

		driver.navigate().to("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");

		List<WebElement> rank = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));

		for (int i = 0; i < rank.size() - 1; i++) {

			int actual = Integer.parseInt(rank.get(i).getText());

			Assert.assertEquals(actual, i + 1);

		}

		driver.findElement(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//th[.='NOC']"))
				.click();

		List<String> countryList = new ArrayList<>();
		List<WebElement> element = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));

		for (WebElement each : element) {

			countryList.add(each.getText());

		}
		Collections.sort(countryList);
		System.out.println(countryList);

		for (int i = 0; i < element.size(); i++) {

			String actual = element.get(i).getText();

			Assert.assertEquals(actual, countryList.get(i));

		}
		List<WebElement> notSorted = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		for (int j = 0; j < notSorted.size(); j++) {
			int actual = Integer.parseInt(notSorted.get(j).getText());
			assertNotEquals(actual, j + 1);
		}

	}

	@Test(priority = 2)
	public void theMost() {

		driver.navigate().to("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		System.out.println(goldMedals());
		System.out.println(silverMedals());
		System.out.println(bronzeMedals());
		System.out.println(mostMedals());

	}

	public String goldMedals() {
		List<WebElement> mostGoldCountry = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> mostGoldNo = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]"));

		int max = Integer.parseInt(mostGoldNo.get(0).getText());
		int countryRank = 0;

		for (int i = 1; i < mostGoldNo.size() - 1; i++) {

			if (Integer.parseInt(mostGoldNo.get(i).getText()) > max) {

				max = Integer.parseInt(mostGoldNo.get(i).getText());
				countryRank = i;

			}
		}

		return mostGoldCountry.get(countryRank).getText().trim();
	}

	public String silverMedals() {

		List<WebElement> mostSilverCountry = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> mostSilverNo = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));

		int max = Integer.parseInt(mostSilverNo.get(0).getText());
		int countryRank = 0;

		for (int i = 1; i < mostSilverNo.size() - 1; i++) {

			if (Integer.parseInt(mostSilverNo.get(i).getText()) > max) {

				max = Integer.parseInt(mostSilverNo.get(i).getText());
				countryRank = i;

			}
		}

		return mostSilverCountry.get(countryRank).getText().trim();
	}

	public String bronzeMedals() {

		List<WebElement> bronzeMedals = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> bronzeMedalsNo = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));

		int max = Integer.parseInt(bronzeMedalsNo.get(0).getText());
		int countryRank = 0;

		for (int i = 1; i < bronzeMedalsNo.size() - 1; i++) { // size niye -1 sor

			if (Integer.parseInt(bronzeMedalsNo.get(i).getText()) > max) {

				max = Integer.parseInt(bronzeMedalsNo.get(i).getText());
				countryRank = i;

			}
		}

		return bronzeMedals.get(countryRank).getText().trim();

	}

	public String mostMedals() {
		
		
		List<WebElement> totalMedals = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]"));
		List<WebElement> country = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		int max = Integer.parseInt(totalMedals.get(0).getText());
		int countryRank = 0;

		for (int i = 1; i < totalMedals.size() - 1; i++) {

			if (Integer.parseInt(totalMedals.get(i).getText()) > max) {
				max = Integer.parseInt(totalMedals.get(i).getText());
				countryRank = i;

			}
		}

		return country.get(countryRank).getText().trim();
	}

	@Test(priority = 3)
	public void countryByMedal() {

		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics.");
		System.out.println(medal18());

	}

	public List<String> medal18() {

		List<WebElement> countrySilver18 = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> silver18 = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
		List<String> country18Silver = new ArrayList<>();
		
		for (int i = 1; i < silver18.size() - 1; i++) {

			if (Integer.parseInt(silver18.get(i).getText()) == 18) {

				country18Silver.add(countrySilver18.get(i).getText().trim());

			}
		}
		return country18Silver;

	}

	@Test(priority = 4)
	public void getIndex()  {

		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");

		System.out.println(rowColumn("Japan"));
		// System.out.println(rowColumn());
	}

	public String rowColumn(String country) {

		List<WebElement> japanIndex = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		String rowColumn = "";
		for (int i = 0; i < japanIndex.size(); i++) {

			if (country.equalsIgnoreCase(
					japanIndex.get(i).getText().trim().substring(0, japanIndex.get(i).getText().trim().indexOf(" ")))) {

				rowColumn += (i + 1) + " 2";
			}
		}

		return rowColumn;
	}

	@Test(priority = 5)
	public void getSum() {

		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		System.out.println(sumOfTwo());

	}

	public List<String> sumOfTwo() {
		List<String> twoCountry = new ArrayList<>();
		List<WebElement> countries = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<WebElement> bronzeMedals = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));

		for (int i = 0; i < bronzeMedals.size(); i++) {
			int num1 = Integer.parseInt(bronzeMedals.get(i).getText());
			for (int j = i + 1; j < bronzeMedals.size(); j++) {
				int num2 = Integer.parseInt(bronzeMedals.get(j).getText());
				if (num1 + num2 == 18) {
					twoCountry.add(countries.get(i).getText().trim());
					twoCountry.add(countries.get(j).getText().trim());
				}

			}

		}
		return twoCountry;
	}

}
