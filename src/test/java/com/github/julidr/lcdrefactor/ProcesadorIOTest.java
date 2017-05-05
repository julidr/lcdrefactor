package com.github.julidr.lcdrefactor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.julidr.lcdrefactor.refactor.ProcesadorIO;

public class ProcesadorIOTest {
	
	ProcesadorIO procesadorIO;

	@Before
	public void setUp() throws Exception {
		procesadorIO = new ProcesadorIO();
	}

	@Test
	public void debeImprimirResultadoPorQueEsUnInputValido() {
		procesadorIO.procesarInput("1,123456789");
	}
	
	@Test
	public void debeLanzarExceptionPorQueElInputNoTieneComa(){
		String input = "12312";
		try {
			procesadorIO.procesarInput(input);
			fail("Debio lanzar un IllegalArgumentException por que el input no contenia ,");
		} catch (IllegalArgumentException e) {
			assertEquals("Cadena " + input + " no contiene caracter ,", e.getMessage());
		}
	}
	
	@Test
	public void debeLanzarExceptionPorQueElInputTieneMasDeUnaComa(){
		String input = "12,31,2";
		try {
			procesadorIO.procesarInput(input);
			fail("Debio lanzar un IllegalArgumentException por que el input tenia mas de una ,");
		} catch (IllegalArgumentException e) {
			assertEquals("Cadena " + input + " contiene mas caracteres ,", e.getMessage());
		}
	}
	
	@Test
	public void debeLanzarExceptionPorQueElInputTieneMenosParametrosDeLosRequeridos(){
		String input = "1,";
		try {
			procesadorIO.procesarInput(input);
			fail("Debio lanzar un IllegalArgumentException por que el input tiene menos parametros de los requeridos");
		} catch (IllegalArgumentException e) {
			assertEquals("Cadena " + input + " no contiene los parametros requeridos", e.getMessage());
		}
	}
	
	@Test
	public void debeLanzarExceptionPorQueElValorDelSizeNoEsUnNumero(){
		String input = "a,1234";
		try {
			procesadorIO.procesarInput(input);
			fail("Debio lanzar un IllegalArgumentException por que el valor del size no es un numero entero");
		} catch (IllegalArgumentException e) {
			assertEquals("Parametro Size [a] no es un numero", e.getMessage());
		}
	}
	
	@Test
	public void debeLanzarExceptionPorQueElValorDelSizeSobrePasaElLimite(){
		String input = "11,1234";
		try {
			procesadorIO.procesarInput(input);
			fail("Debio lanzar un IllegalArgumentException por que el valor del size no esta entre 1 y 10");
		} catch (IllegalArgumentException e) {
			assertEquals("El parametro size [11] debe estar entre 1 y 10", e.getMessage());
		}
	}
	
	@Test
	public void debeRetornarFalsoPorQueElValorIngresadoNoEsUnNumero(){
		boolean result = procesadorIO.isNumeric("Valar Morghulis");
		assertFalse(result);
	}
	
	@Test
	public void debeRetornarTruePorQueElValorIngresadoEsUnNumero(){
		boolean result = procesadorIO.isNumeric("1855");
		assertTrue(result);
	}

}
