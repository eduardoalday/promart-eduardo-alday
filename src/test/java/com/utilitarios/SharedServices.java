package com.utilitarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.jacob.com.LibraryLoader;

import autoitx4java.AutoItX;
import freemarker.core.LibraryLoad;

public class SharedServices {
	public String getHoraExacta() {
		Date date = new Date();
		// Caso 1: obtener la hora y salida por pantalla con formato:
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		// Caso 2: obtener la fecha y salida por pantalla con formato:
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// Caso 3: obtenerhora y fecha y salida por pantalla con formato:
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String hora = hourdateFormat.format(date);
		hora = hora.replaceAll(":", "");
		hora = hora.replaceAll("/", "");
		hora = hora.replaceAll(" ", "_");
		//System.out.println(hora);
		return hora;
	}

	public String getDirectorioProyecto() {
		String directorio = System.getProperty("user.dir");
		directorio = directorio.replace("\\", "\\\\");
		System.out.println(directorio);
		return directorio;
	}

	public void selectByText(WebElement combolocator, String text) {
		try {
			Select sel = new Select(combolocator);
			sel.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("No se ha podido seleccionar la opcion " + text);
			e.printStackTrace();

		}
	}

	public void waitUntilNotEmpty(WebElement element) throws Throwable {
		for (int time = 0; time < 30; time++) {
			try {
				Thread.sleep(1000);
				if (!element.getText().equals("")) {
					break;
				}
				if (!element.getAttribute("value").equals("")) {
					break;
				}
				if (!element.getAttribute("text").equals("")) {
					break;
				}
				if (!element.getAttribute("contentText").equals("")) {
					break;
				}
			} catch (Exception e) {
				continue;
			}
			if (time == 29) {
				System.out.println("Tiempo de espera excedido");
			}
		}
	}

}
