package com.pageTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BusquedaPage extends BasePage {

	public BusquedaPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(id = "gh-ac")
	private WebElement inputBuscar;

	@FindBy(id = "gh-btn")
	private WebElement btnBuscar;

	// Actions
	public void writeArticuloBusqueda(String articuloBusqueda) {
		wait.until(ExpectedConditions.elementToBeClickable(inputBuscar)).sendKeys(articuloBusqueda);
	}

	public void clickCriteriosBusqueda() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnBuscar)).click();
	}

}
