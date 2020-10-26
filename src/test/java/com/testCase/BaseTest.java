package com.testCase;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.Status;
import com.pageTest.*;
import com.utilitarios.*;

public class BaseTest {
	protected WebDriver driver;
	protected Properties properties = new Properties();
	private ExtendReportsUtilities report = new ExtendReportsUtilities();
	private SharedServices sharedServices = new SharedServices();
	private String rutaReporte = sharedServices.getDirectorioProyecto() + "\\reportes\\"
			+ sharedServices.getHoraExacta();
	String chromeWebService;

	@BeforeClass
	public void classSetUp() {

		try {
			properties.load(new FileReader("src/test/java/com/config/config.properties"));
			chromeWebService = properties.getProperty("chrome32");
			System.setProperty("webdriver.chrome.driver", chromeWebService);
			driver = new ChromeDriver();
			// ****
			String aplicativo = "RETO PROMART : EDUARDO ALDAY BRAN";
			String operacion = "CASOS DE PRUEBA";
			report.setUp(rutaReporte);
			report.test(aplicativo, operacion);
			report.crearDirectivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void ingresarEbay() throws Exception {
		driver.manage().window().maximize();
		driver.get("https://pe.ebay.com/");
		System.out.println(driver.getTitle());
		report.getScreenShotWithState(Status.PASS, "CASO 1 : Ingresar a eBay",
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);
	}

	protected void buscarArticulo(String articuloBusqueda) throws Exception {
		BusquedaPage busPage = new BusquedaPage(driver);
		busPage.writeArticuloBusqueda(articuloBusqueda);
		report.getScreenShotWithState(Status.PASS, "CASO 2 : Buscar zapatos",
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);
		busPage.clickCriteriosBusqueda();
	}

	protected void buscarMarca(String marca) throws Exception {
		FiltroPage busMarca = new FiltroPage(driver);
		busMarca.clickTodosFiltros();
		busMarca.clickMarcas();
		busMarca.clickBuscarMarcas(marca);
		busMarca.clickSeleccionarMarca();
		report.getScreenShotWithState(Status.PASS, "CASO 3 : Buscar marca PUMA",
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);
	}

	protected void elegirTamanio(String tamanio) throws Exception {
		FiltroPage selTamanio = new FiltroPage(driver);
		selTamanio.clickTamanios();
		selTamanio.clickSeleccionarTamanio(tamanio);
		selTamanio.clickAceptaFiltro();
		report.getScreenShotWithState(Status.PASS, "CASO 4 : Seleccionar talla 10 US",
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);
	}

	protected void mostrarResultadoFiltro() throws Exception {
		FiltroPage numRegistros = new FiltroPage(driver);
		String resultadoFiltro = numRegistros.mostrarNumeroResultadoFiltro();
		report.getScreenShotWithState(Status.PASS,
				"CASO 5 : Numero de registros filtrados : " + resultadoFiltro + " articulos",
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);

	}

	protected void ordenarAscendenteFiltro() throws Exception {
		FiltroPage ordenarAscendente = new FiltroPage(driver);
		ordenarAscendente.clickOrdenarAscendente();
		report.getScreenShotWithState(Status.PASS,
				"CASO 6 : Se selecciona Precio orden ascendente : Precio + Envío: más bajo primero ",
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);

	}

	protected void tomarCincoPrimerosResultadosOrden() throws Exception {
		FiltroPage cincoResultados = new FiltroPage(driver);
		String nomProd, preProd, desProd, prod = "";
		WebElement producto;
		Thread.sleep(1000);
		for (int i = 1; i <= 5; i++) {
			nomProd = "//*[contains(@id, 'srp-river-results')]/ul/li[" + i + "]/div/div[2]/a/h3";
			producto = cincoResultados.mostrarResultados(nomProd);
			desProd = i + ") " + producto.getText();
			nomProd = "//*[contains(@id, 'srp-river-results')]/ul/li[" + i + "]/div/div[2]/div[3]/div[1]/span";
			producto = cincoResultados.mostrarResultados(nomProd);
			preProd = producto.getText();
			prod = prod + " <br> " + desProd + " --> Precio: " + preProd;
		}
		report.getScreenShotWithState(Status.PASS, "CASO 7 : Se selecciona primeros 5 productos" + "<br>" + prod,
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);
	}

	protected void precioDescendente() throws Exception {
		FiltroPage preDes = new FiltroPage(driver);
		String nomProd, desProd, preProd, preEnvio, prod = "";
		WebElement producto;
		for (int i = 5; i >= 1; i--) {
			nomProd = "//*[contains(@id, 'srp-river-results')]/ul/li[" + i + "]/div/div[2]/a/h3";
			producto = preDes.mostrarResultados(nomProd);
			desProd = i + ") " + producto.getText();
			nomProd = "//*[contains(@id, 'srp-river-results')]/ul/li[" + i + "]/div/div[2]/div[3]/div[1]/span";
			producto = preDes.mostrarResultados(nomProd);
			preProd = producto.getText();
			nomProd = "//*[contains(@id, 'srp-river-results')]/ul/li[" + i + "]/div/div[2]/div[3]/div[3]/span";
			producto = preDes.mostrarResultados(nomProd);
			preEnvio = producto.getText();
			prod = prod + " <br> " + desProd + " --> Precio: " + preProd + " --> Precio Envio: " + preEnvio;
		}
		report.getScreenShotWithState(Status.PASS,
				"CASO 8 : Precio Descendente por la suma de Precio + Envio\"" + "<br>" + prod,
				rutaReporte + "\\screenshots\\" + this.sharedServices.getHoraExacta() + ".png", null);
	}

	@AfterClass
	public void classTearDown() {
		report.getTestObject().info("Automatizacion Completa");
		report.terminarReporte(rutaReporte + "\\report.html");
		driver.quit();
	}

}
