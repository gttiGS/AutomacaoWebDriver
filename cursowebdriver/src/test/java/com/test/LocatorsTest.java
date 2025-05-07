package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;;

public class LocatorsTest extends BaseTest{
	
	private String nome;
	private String mail;

	@BeforeEach
	public void setUp() throws Exception {

		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/localizandovalorestable.html");
		
		nome = "Antônio";
		mail = "antoniosilva@gmail.com";
	}

	@Test
	public void testReservaVaga() {		
		
		WebElement labelEmail = getDriver().findElement(By.xpath("//td[contains(text(),'" + nome + "')]/..//td[2]"));
		String email = labelEmail.getText();
		
		WebElement tfEmailReserva = getDriver().findElement(By.xpath("//input[@id='txt01']"));
		tfEmailReserva.sendKeys(email);
		
		assertEquals(mail, tfEmailReserva.getDomProperty("value"));
		
	}
	
	@Test
	public void testCheckVaga() {
		WebElement chkVaga = getDriver().findElement(By.xpath("//td[contains(text(),'Fulano')]/following-sibling::td/input"));
		chkVaga.click();
		assertTrue(chkVaga.isSelected());
	}

}
