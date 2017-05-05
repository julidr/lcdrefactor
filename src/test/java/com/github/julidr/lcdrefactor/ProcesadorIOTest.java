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

}
