package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;;

public class NavegacaoTest extends BaseTest {
	
	@BeforeEach
	public void setUp() throws Exception {
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao");
	}

	@Test
	public void testNavegacaoTabs() throws InterruptedException {
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkGeradorCpf = getDriver().findElement(By.linkText("Gerador de CPF"));
		linkGeradorCpf.click();
		
		WebElement linkDragAndDrop = getDriver().findElement(By.linkText("Drag and Drop"));
		linkDragAndDrop.click();
		
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		
		getDriver().switchTo().window(tabs.get(2));
		assertEquals("Gerador de CPF", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(1));
		assertEquals("Mootools Drag and Drop Example", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", getDriver().getTitle());			
		
	}
	
	@Test
	public void testNavegacaoAcoesBrowser() {
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkCalculadora = getDriver().findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		WebElement linkTable = getDriver().findElement(By.linkText("Localizar Table"));
		linkTable.click();
		
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
		//Navegacao a partir do cash criado
		getDriver().navigate().back();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().back();
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
		
	}

}
