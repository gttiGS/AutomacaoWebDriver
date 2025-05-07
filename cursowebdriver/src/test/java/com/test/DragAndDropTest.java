package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.core.BaseTest;


public class DragAndDropTest extends BaseTest{
	

	@BeforeEach
	public void setUp() throws Exception {
		getDriver().get("https://jqueryui.com/resources/demos/droppable/default.html");
	}

		
	@Test
	public void testDragAndDrop() {
		WebElement origem = getDriver().findElement(By.id("draggable"));
		WebElement destino = getDriver().findElement(By.id("droppable"));
		
		assertEquals("Drop here", destino.getText());
		
		new Actions(getDriver()).dragAndDrop(origem, destino).perform();
		
		assertEquals("Dropped!", destino.getText());
	}

}
