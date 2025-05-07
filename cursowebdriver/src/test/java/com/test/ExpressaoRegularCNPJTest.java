package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class ExpressaoRegularCNPJTest extends BaseTest{
		
	@BeforeEach
	public void setUp() throws Exception {	
		getDriver().get("https://toolshub.com.br/gerador-cnpj-filial");
	}
	
	@Test
	public void testExpressaoRegularCNPJ() {
		WebElement btnGerar = getDriver().findElement(By.xpath("//button[contains(text(),'gerar cnpj')][1]"));
		btnGerar.click();
		
		WebElement tfCnpj = getDriver().findElement(By.xpath("//input[@id='input']"));
		String cnpjValue = tfCnpj.getDomProperty("value");
		
		assertTrue(cnpjValue.matches("^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/0001-[0-9]{2}$"));
		
	}

}
