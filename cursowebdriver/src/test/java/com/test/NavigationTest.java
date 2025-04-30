package com.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

import java.util.ArrayList;
import java.util.List;

public class NavigationTest {

    private WebDriver driver;

    //Irá rodar Antes de cada teste (Pré-condições)
    @BeforeEach
    public void setUP() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://antoniotrindade.com.br/treinoautomacao");
    }

    //Irá rodar Depois de cada teste (Pós-condições)
    //Sempre que fizer um teste, fechar o browser
    @AfterEach
    public void tearDown() throws Exception{
        driver.quit();        
    }

    @Test
    public void testTabsNav(){
        assertEquals("Treino Automação de Testes", driver.getTitle());

        WebElement linkGeradorCpf = driver.findElement(By.linkText("Gerador de CPF"));
        linkGeradorCpf.click();

        WebElement linkDragAndDrop = driver.findElement(By.linkText("Drag and Drop"));
        linkDragAndDrop.click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(2));
        assertEquals("Gerador de CPF", driver.getTitle());

        driver.switchTo().window(tabs.get(1));
        assertEquals("Mootools Drag and Drop Example", driver.getTitle());

        driver.switchTo().window(tabs.get(0));
        assertEquals("Treino Automação de Testes", driver.getTitle());

    }

    //"Skipar" testes com Junit (@Disabled)
    @Disabled("Bug1001 - Cache de Browser não funciona")
    @Test
    public void testBrowserNavigation(){
        assertEquals("Treino Automação de Testes", driver.getTitle());

        WebElement linkCalculadora  = driver.findElement(By.linkText("Calculadora"));
        linkCalculadora.click();

        assertEquals("Desafio Automação Cálculos", driver.getTitle());

        WebElement linkTable  = driver.findElement(By.linkText("Localizar Table"));
        linkTable.click();

        assertEquals("Trabalhando com tables", driver.getTitle());

        driver.navigate().back();
        assertEquals("Desafio Automação Cálculos", driver.getTitle());

        driver.navigate().back();
        assertEquals("Treino Automação de Testes", driver.getTitle());

        driver.navigate().forward();
        assertEquals("Desafio Automação Cálculos", driver.getTitle());

        driver.navigate().forward();
        assertEquals("Trabalhando com tables", driver.getTitle());

    }

}
