package com.core;

import static com.core.DriverFactory.*;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class BaseTest {
	
		
	@AfterEach
	public void tearDown(TestInfo testInfo) throws Exception {
		
		String nameTest = testInfo.getTestMethod().map(method -> method.getName()).orElse("test-desconhecido");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String timestamp = LocalDateTime.now().format(formatter);
		
		//Captura a tela do browser que está aberta
		File screen = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("target"+ File.separator + nameTest + "_" + timestamp + ".jpg"));		
		
		killDriver();
	}
}