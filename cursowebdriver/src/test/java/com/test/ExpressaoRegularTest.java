package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class ExpressaoRegularTest extends BaseTest{
	
	private WebElement btnGerarCPF;
	private WebElement tfNumeroCpf;
	private WebElement chkPontos;
	

	@BeforeEach
	public void setUp() throws Exception {
		getDriver().get("https://www.geradordecpf.org/");
		
		//mapeando componentes usados em mais de um teste dentro da classe
		btnGerarCPF = getDriver().findElement(By.id("btn-gerar-cpf"));
		tfNumeroCpf = getDriver().findElement(By.id("numero"));
		chkPontos = getDriver().findElement(By.id("cbPontos"));
	}
		
	@Test
	public void testValidaCPFComMascara() {
		
		chkPontos.click();			
		btnGerarCPF.click();
		
		String numeroCpf = tfNumeroCpf.getDomProperty("value");
		
		System.out.println(numeroCpf);
		
		assertTrue(numeroCpf.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));		
	}
	
	@Test
	public void testValidaCPFSemMascara() {
		
		btnGerarCPF.click();	
		
		String numeroCpf = tfNumeroCpf.getDomProperty("value");
		
		System.out.println(numeroCpf);
		
		assertTrue(numeroCpf.matches("^[0-9]{11}$"));		
						
	}


}
