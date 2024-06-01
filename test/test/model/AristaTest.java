package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import app.model.Arista;

public class AristaTest {
	
	private Arista aristaValida() {
		Arista arista = new Arista();
		arista.setVertice1(1);
		arista.setVertice2(2);
		return arista;

	}

	@Test(expected = IllegalArgumentException.class)
	public void vertice1NoValido() {
		Arista arista = new Arista();
		arista.setVertice1(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void vertice2NoValido() {
		Arista arista = new Arista();
		arista.setVertice2(0);
	}

	@Test
	public void obtenerVertice1() {
		int expected = 1; 
		int result = aristaValida().getVertice1();
		
		assertEquals(result,expected);
	}
	
	@Test
	public void obtenerVertice2() {
		int expected = 2; 
		int result = aristaValida().getVertice2();
		
		assertEquals(result,expected);
	}
	
	@Test
	public void actualizarVertice1() {
		int expected = 4; 
		Arista arista = aristaValida();
		arista.setVertice1(expected);
		int result = arista.getVertice1();
		
		assertEquals(result,expected);
	}
	
	@Test
	public void actualizarVertice2() {
		int expected = 4; 
		Arista arista = aristaValida();
		arista.setVertice2(expected);
		int result = arista.getVertice2();
		
		assertEquals(result,expected);
	}
	
	@Test
	public void aristasIguales() {
		Arista arista1 = aristaValida();
		Arista arista2 = aristaValida();
		
		assertEquals(arista1, arista2);	
	}
	
	@Test
	public void aristasNoIguales() {
		Arista arista1 = aristaValida();
		Arista arista2 = aristaValida();
	
		arista2.setVertice1(5);
		
		assertFalse(arista1.equals(arista2));	
	}
	
}
