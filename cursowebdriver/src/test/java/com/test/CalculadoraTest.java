package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class CalculadoraTest extends BaseTest{
	
	private WebDriverWait wait;

	@BeforeEach
	public void setUp() throws Exception {
		
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");
		//Cria o wait
		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	
	}
		
	@Test
	public void testSomaDoisNumeros() throws InterruptedException {
		WebElement tfNumber1 = getDriver().findElement(By.id("number1"));
		tfNumber1.sendKeys("78");
		
		WebElement tfNumber2 = getDriver().findElement(By.id("number2"));
		tfNumber2.sendKeys("10");
		
		WebElement btnSomar = getDriver().findElement(By.id("somar"));
		btnSomar.click();
		
		WebElement tfTotal = getDriver().findElement(By.id("total"));
		
		//expera explícita		
		wait.until(ExpectedConditions.textToBePresentInElementValue(tfTotal, "88"));
		
		assertEquals("88", tfTotal.getDomProperty("value"));	
		
	}

}
