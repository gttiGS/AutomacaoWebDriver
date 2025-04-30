package com.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegularExprTest {

    private WebDriver driver;
	private WebElement btnGerarCPF;
	private WebElement tfNumeroCpf;
	private WebElement chkPontos;

    //Irá rodar Antes de cada teste (Pré-condições)
    @BeforeEach
    public void setUP() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.geradordecpf.org");

        //mapeando componentes usados em mais de um teste dentro da classe
		btnGerarCPF = driver.findElement(By.id("btn-gerar-cpf"));
		tfNumeroCpf = driver.findElement(By.id("numero"));
		chkPontos = driver.findElement(By.id("cbPontos"));
    }

    //Irá rodar Depois de cada teste (Pós-condições)
    //Sempre que fizer um teste, fechar o browser
    @AfterEach
    public void tearDown() throws Exception{
        driver.quit();        
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
