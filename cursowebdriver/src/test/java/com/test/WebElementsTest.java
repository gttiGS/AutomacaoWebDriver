package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.core.BaseTest;

public class WebElementsTest extends BaseTest{
	
	
	@BeforeEach
	public void setUp() throws Exception {	
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}	

	@Test
	public void testEscreveHelloWorld() {		
		//1. Localizar um elemento na tela
		WebElement textFieldEnable = getDriver().findElement(By.xpath("//*[@name='txtbox1']"));
		
		//2. Interagir com o elemento
		textFieldEnable.sendKeys("Hello World!!!");
		
		//3. Validar o resultado 
		assertEquals("Hello World!!!", textFieldEnable.getDomProperty("value"));
	}
	
	@Test
	public void testValidaTitle() {
		assertEquals("WebElements Test Page Lab", getDriver().getTitle());
	}
	
	@Test
	public void testHabilitadoEDesabilitado() {
		WebElement textFieldEnable = getDriver().findElement(By.name("txtbox1"));
		WebElement textFieldDisable = getDriver().findElement(By.name("txtbox2"));
		
		assertTrue(textFieldEnable.isEnabled());
		assertFalse(textFieldDisable.isEnabled());		
	}
	
	@Test
	public void testValidaRadioButton() {
		List<WebElement> listRadio = getDriver().findElements(By.name("radioGroup1"));		
		assertEquals(4, listRadio.size());
		
		for (WebElement radio : listRadio) {
			if (radio.getDomProperty("value").equals("Radio 3")) {
				radio.click();
			}
		}
		
		//listRadio.get(2).click();
		
		assertTrue(listRadio.get(2).isSelected());
		assertFalse(listRadio.get(0).isSelected());
		assertFalse(listRadio.get(1).isSelected());
		assertFalse(listRadio.get(3).isSelected());		
	}
	
	@Test
	public void testValidaCheckBoxes() {
		List<WebElement> listCheck = getDriver().findElements(By.name("chkbox"));
		
		//validar o tamanho da lista
		assertEquals(4, listCheck.size());
		
		for (WebElement check : listCheck) {
			//System.out.println(check.getDomProperty("value"));
			
			// Operador lógico ou (||)
			if ((check.getDomProperty("value").equals("Check 3")) 
					|| (check.getDomProperty("value").equals("Check 4"))) {
				check.click();
			}		
		}
		
		assertTrue(listCheck.get(2).isSelected());
		assertTrue(listCheck.get(3).isSelected());
		assertFalse(listCheck.get(0).isSelected());
		assertFalse(listCheck.get(1).isSelected());
		
	}
	
	@Test
	public void testValidaDropDownSingle() throws InterruptedException {
		WebElement dropSingle = getDriver().findElement(By.name("dropdownlist"));		
		Select selectDropSingle = new Select(dropSingle);
		
		selectDropSingle.selectByIndex(0);
		//Sleep para travar o tempo em 3s //Não é uma boa prática
		Thread.sleep(3000);
		selectDropSingle.selectByValue("item9");
		Thread.sleep(3000);
		selectDropSingle.selectByVisibleText("Item 7");
		
		assertEquals("Item 7", selectDropSingle.getFirstSelectedOption().getText());
				
	}
	
	@Test
	public void testValidaDropDownMultiple() {
		WebElement dropMulti = getDriver().findElement(By.name("multiselectdropdown"));
		Select selectDropMulti = new Select(dropMulti);
		
		selectDropMulti.selectByVisibleText("Item 5");
		selectDropMulti.selectByVisibleText("Item 8");
		selectDropMulti.selectByVisibleText("Item 9");
		
		List<WebElement> listSelected = selectDropMulti.getAllSelectedOptions();
		
		//Valida quantos
		assertEquals(3, listSelected.size());
		
		//Valida quais
		assertEquals("Item 5", listSelected.get(0).getText());
		assertEquals("Item 8", listSelected.get(1).getText());
		assertEquals("Item 9", listSelected.get(2).getText());		
	}
	
	@Test
	public void testValidaIFrame() {
		//entra no iframe
		getDriver().switchTo().frame(0);
		
		WebElement tfIframe = getDriver().findElement(By.id("tfiframe"));
		tfIframe.sendKeys("Automação de teste");
		
		assertEquals("Automação de teste", tfIframe.getDomProperty("value"));
		
		//retorna para o contexto default
		getDriver().switchTo().defaultContent();
	}
	
	@Test
	public void testValidaAlerts() {
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alert = getDriver().switchTo().alert();
		assertEquals("Eu sou um alerta!", alert.getText());
		
		alert.accept();
		
		WebElement btnConfirm = getDriver().findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirm = getDriver().switchTo().alert();
		assertEquals("Pressione um botão!", confirm.getText());
		
		confirm.dismiss();	
		
	}
	
	@Test
	public void testFluxoAlerts() {
		WebElement btnPrompt = getDriver().findElement(By.id("promptBtn"));
		btnPrompt.click();
		
		Alert alertDigiteAno = getDriver().switchTo().alert();
		alertDigiteAno.sendKeys("2025");
		alertDigiteAno.accept();
		
		Alert alertValidaAno = getDriver().switchTo().alert();
		
		assertEquals("O ano é 2025?", alertValidaAno.getText());
		alertValidaAno.accept();
		
		Alert alertFeito = getDriver().switchTo().alert();
		
		assertEquals("Feito!", alertFeito.getText());
		alertFeito.accept();
	}
	

}
