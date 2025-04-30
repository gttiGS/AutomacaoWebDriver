package com.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class WebElementsTest {

    private WebDriver driver;

    //Irá rodar Antes de cada teste (Pré-condições)
    @BeforeEach
    public void setUP() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
    }

    //Irá rodar Depois de cada teste (Pós-condições)
    //Sempre que fizer um teste, fechar o browser
    @AfterEach
    public void tearDown() throws Exception{
        driver.quit();        
    }

    //Teste (Nome sempre cammel case, função smp começa com letra minúscula)
    @Test
    public void testHelloWorld() {
        WebElement input = driver.findElement(By.name("txtbox1"));
        String textoEsperado = "Hello World!";
        input.clear();
        input.sendKeys("Hello World!");
        String textoDigitado = input.getDomProperty("value");
        assertEquals(textoEsperado, textoDigitado);
    }

    @Test
    public void testValidaTitle(){
        assertEquals("WebElements Test Page Lab", driver.getTitle());
    }

    @Test
    public void testEnabledandDisabledComponent() {
        WebElement tfenable = driver.findElement(By.name("txtbox1"));
        WebElement tfdisable = driver.findElement(By.name("txtbox2"));
        
        assertTrue(tfenable.isEnabled());
        assertFalse(tfdisable.isEnabled());

    }

    @Test
    public void testValidateRadioBtn(){
        List<WebElement> radiolist = driver.findElements(By.name("radioGroup1"));

        assertEquals(4, radiolist.size());

        for(WebElement radio : radiolist){
            if (radio.getDomProperty("value").equals("Radio 3")) {
                radio.click();
                
            }
        }

        assertTrue(radiolist.get(2).isSelected());
        assertFalse(radiolist.get(0).isSelected());
        assertFalse(radiolist.get(1).isSelected());
        assertFalse(radiolist.get(3).isSelected());

    }

    @Test
    public void testValidateCheckbox() {
        List<WebElement> checklist = driver.findElements(By.name("chkbox"));

        assertEquals(4, checklist.size());

        checklist.stream()
        .filter(check -> "Check 3".equals(check.getDomProperty("value"))) 
        .findFirst()
        .ifPresent(WebElement::click);

        checklist.stream()
        .filter(check -> "Check 4".equals(check.getDomProperty("value")))
        .findFirst()
        .ifPresent(WebElement::click);

        assertTrue(checklist.get(2).isSelected());
        assertTrue(checklist.get(3).isSelected());
        assertFalse(checklist.get(0).isSelected());
        assertFalse(checklist.get(1).isSelected());
        
    }

    @Test

    public void testValidateSingleDropDown(){
        WebElement singledd = driver.findElement(By.name("dropdownlist"));
        Select selectDropSingle = new Select(singledd);

        selectDropSingle.selectByIndex(0);
        //Sleep para travar o tempo em 3s analisar teste
        //Não é uma boa prática
        //Thread.sleep(3000);
        selectDropSingle.selectByContainsVisibleText("Item 7");
        //Thread.sleep(3000);
        selectDropSingle.selectByValue("item9");
        
        assertEquals("Item 9", selectDropSingle.getFirstSelectedOption().getText());

    }

    @Test
    public void testValidateMultiSDropDown(){
        WebElement multisdd = driver.findElement(By.name("multiselectdropdown"));
        Select selectMultiDd = new Select(multisdd);

        selectMultiDd.selectByContainsVisibleText("Item 5");
        selectMultiDd.selectByContainsVisibleText("Item 8");
        selectMultiDd.selectByContainsVisibleText("Item 9");

        List<WebElement> ListSelecteds = selectMultiDd.getAllSelectedOptions();

        //Valida quantos estão selecionados
        assertEquals(3, ListSelecteds.size());

        //Valida quais estão selecionados
        assertEquals("Item 5", ListSelecteds.get(0).getText());
        assertEquals("Item 8", ListSelecteds.get(1).getText());
        assertEquals("Item 9", ListSelecteds.get(2).getText());
    }

    @Test
    public void testFrameValidate(){
        //Entra no Iframe
        driver.switchTo().frame(0);

        WebElement tfIframe = driver.findElement(By.id("tfiframe"));
        tfIframe.sendKeys("Automação Test");

        assertEquals("Automação Test", tfIframe.getDomProperty("value"));

        //Retorna para o contexto default
        driver.switchTo().defaultContent();
    }

    @Test
	public void testValidaAlerts() {
		WebElement btnAlert = driver.findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alert = driver.switchTo().alert();
		assertEquals("Eu sou um alerta!", alert.getText());
		
		alert.accept();
		
		WebElement btnConfirm = driver.findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirm = driver.switchTo().alert();
		assertEquals("Pressione um botão!", confirm.getText());
		
		confirm.dismiss();	
		
	}

    @Test
	public void testFluxoAlerts() {
		WebElement btnPrompt = driver.findElement(By.id("promptBtn"));
		btnPrompt.click();
		
		Alert alertDigiteAno = driver.switchTo().alert();
		alertDigiteAno.sendKeys("2025");
		alertDigiteAno.accept();
		
		Alert alertValidaAno = driver.switchTo().alert();
		
		assertEquals("O ano é 2025?", alertValidaAno.getText());
		alertValidaAno.accept();
		
		Alert alertFeito = driver.switchTo().alert();
		
		assertEquals("Feito!", alertFeito.getText());
		alertFeito.accept();
	}


}
