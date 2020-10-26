package com.pageTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FiltroPage extends BasePage {

	public FiltroPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(id = "s0-14-11-0-1-2-6-2")
	private WebElement lnkTodosFiltros;

	@FindBy(xpath = "//*[contains(@id, 'mainPanel-Brand')]")
	private WebElement lnkMarcas;

	@FindBy(xpath = "//*[contains(@id, 'mainPanel-US%20Shoe%20Size%20%28Men%27s%29')]/span")
	private WebElement lnkTamanio;

	@FindBy(xpath = "//*[contains(@id, 'subPanel-_x-searchable')]/div[1]/input")
	private WebElement inputMarca;

	@FindBy(xpath = "//*[contains(@id, 'footerId')]/div[2]/button")
	private WebElement btnAceptarFiltro;

	@FindBy(xpath = "//*[starts-with(@id, 'nid-')]/button/span/span/span") // *[starts-with(@id, 'nid-')]/button
	private WebElement cmbOrden;

	@FindBy(xpath = "//*[contains(@id, 's0-14-11-5-1[0]-37-0-content-menu')]/a[4]/span")
	private WebElement selOrdenar;

	// Actions
	public void clickTodosFiltros() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnkTodosFiltros)).click();
	}

	public void clickMarcas() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnkMarcas)).click();
	}

	public void clickBuscarMarcas(String marca) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(inputMarca)).sendKeys(marca);
	}

	public void clickSeleccionarMarca() throws InterruptedException {
		Thread.sleep(1000);
		WebElement chkSelMarca = driver
				.findElement(By.xpath("//*[contains(@id, 'subPanel-_x-searchable-0-5[0[0]]-0_cbx')]"));
		chkSelMarca.click();
	}

	public void clickTamanios() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnkTamanio)).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void clickSeleccionarTamanio(String tamanios) throws InterruptedException {
		String tamanio = "//*[contains(@id, 'subPanel-US%20Shoe%20Size%20%28Men%27s%29_" + tamanios + "-0_cbx')]";
		WebElement chkTamanio = driver.findElement(By.xpath(tamanio));
		chkTamanio.click();
	}

	public void clickAceptaFiltro() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAceptarFiltro)).click();
	}

	public String mostrarNumeroResultadoFiltro() throws InterruptedException {
		Thread.sleep(1000);
		WebElement txtNumeroResultado = driver
				.findElement(By.xpath("//*[contains(@id, 'mainContent')]/div[1]/div/div[2]/div/div[1]/h1/span[1]"));
		String numReg = txtNumeroResultado.getText();
		System.out.println("Total de Productos Filtrados : " + numReg);
		return numReg;
	}

	public void clickOrdenarAscendente() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(cmbOrden)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selOrdenar)).click();
	}

	public WebElement mostrarResultados(String webElemento) throws InterruptedException {
		WebElement producto;
		return producto = driver.findElement(By.xpath(webElemento));
	}

}
