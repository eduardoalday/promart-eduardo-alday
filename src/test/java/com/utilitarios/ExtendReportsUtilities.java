package com.utilitarios;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendReportsUtilities {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	public void setUp(String nombreDirectorio) {
		File directorio = new File(nombreDirectorio);
		directorio.mkdir();
		File directorioScreenShots = new File(nombreDirectorio + "\\screenshots\\");
		directorioScreenShots.mkdir();
		htmlReporter = new ExtentHtmlReporter(nombreDirectorio + "\\report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	public void test(String aplicativo, String operacion) {
		test = extent.createTest(aplicativo, operacion);
		test.log(Status.INFO, "Inicio Automatizacion");
	}

	public ExtentTest getTestObject() {
		return this.test;
	}

	public void terminarReporte(String nombreArchivo) {
		try {
			extent.flush();
			File reporte = new File(nombreArchivo);
			Desktop.getDesktop().open(reporte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String TakesScreenshot(String route, WebDriver driver, boolean robot) throws Exception {
		String strRoute = route;
		if (robot) {
			strRoute = route;
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "png", new File(route));
		} else {
			File scrFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(route));
			strRoute = route;
		}
		return strRoute;
	}

	public void getScreenShotWithState(Status status, String message, String route, WebDriver driver)
			throws IOException, Exception {
		test.log(status, message,
				MediaEntityBuilder.createScreenCaptureFromPath(TakesScreenshot(route, driver, true)).build());
		test.addScreenCaptureFromPath("screenshots/" + route.substring(route.length() - 19, route.length()));
	}

	public void crearDirectivo() {
		File directorio = new File("reportes");
		directorio.mkdir();
	}
}
