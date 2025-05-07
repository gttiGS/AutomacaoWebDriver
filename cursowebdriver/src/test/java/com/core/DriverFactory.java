package com.core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
	
	private static WebDriver driver = null;
	private static String browser = GlobalProperty.getProperty("webdriver.driver"); 
	private static String path = GlobalProperty.getProperty("webdriver.path");
	
	public static WebDriver getDriver() {
		
		if (driver == null) {
			driver = createDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
				
		return driver;
	}
	
	private static WebDriver createDriver() {
		
		if (browser.equals("chrome")) {
		
			System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
			driver = new ChromeDriver();
		}		
		
		else if (browser.equals("chrome-headless")){
			System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--windows-size=1200x960");
			
			driver = new ChromeDriver(options);
			
		}
		else if (browser.equals("firefox")) {
			System.setProperty("webdriver.geckodriver.driver", path + "geckodriver");
			driver = new FirefoxDriver();
		}
		else if (browser.equals("firefox-headless")) {
			System.setProperty("webdriver.geckodriver.driver", path + "geckodriver");
			
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("--windows-size=1200x960");
			
			driver = new FirefoxDriver(options);
		}
		else {
			System.out.println("Nenhum browser foi selecionado!");
		}
		
		return driver;
	}
	
	public static void killDriver() {	
		driver.quit();
		driver = null;		
	}

}
