package com.testCase;

import org.testng.annotations.Test;

public class RetoPromartTest extends BaseTest {

	@Test(priority = 0)
	public void ingresarAEbay() throws Exception {
		ingresarEbay();
	}

	@Test(priority = 1)
	public void buscarZapatos() throws Exception {
		buscarArticulo("zapatos");
	}

	@Test(priority = 2)
	public void buscarMarca() throws Exception {
		buscarMarca("puma");
	}

	@Test(priority = 3)
	public void elegirTamanio() throws Exception {
		elegirTamanio("10");
	}

	@Test(priority = 4)
	public void imprimirNumeroResultado() throws Exception {
		System.out.println("=======================");
		mostrarResultadoFiltro();
		System.out.println("=======================");
	}

	@Test(priority = 5)
	public void ordenarPrecioAscendente() throws Exception {
		ordenarAscendenteFiltro();
	}

	@Test(priority = 6)
	public void mantenerOrdenCincoResultados() throws Exception {
		tomarCincoPrimerosResultadosOrden();
	}

	@Test(priority = 7)
	public void mostrarPrecioDescendente() throws Exception {
		precioDescendente();
	}

}
