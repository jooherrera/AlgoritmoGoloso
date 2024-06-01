package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import app.model.Vertice;

public class VerticeTest {
	
	private Vertice verticeValido() {
		Vertice vertice = new Vertice();
		vertice.setId(1);
		vertice.setPeso(10.0);
		vertice.setLatitud(40.7128);
		vertice.setLongitud(-74.0060);
		return vertice;
	}

	@Test(expected = IllegalArgumentException.class)
	public void idNoValido() {
		Vertice vertice = new Vertice();
		vertice.setId(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void pesoNoValido() {
		Vertice vertice = new Vertice();
		vertice.setPeso(-1.0);
	}

	@Test
	public void obtenerId() {
		int expected = 1; 
		int result = verticeValido().getId();
		
		assertEquals(expected, result);
	}
	
	@Test
	public void obtenerPeso() {
		double expected = 10.0; 
		double result = verticeValido().getPeso();
		
		assertEquals(expected, result, 0.0);
	}
	
	@Test
	public void obtenerLatitud() {
		double expected = 40.7128; 
		double result = verticeValido().getLatitud();
		
		assertEquals(expected, result, 0.0);
	}
	
	@Test
	public void obtenerLongitud() {
		double expected = -74.0060; 
		double result = verticeValido().getLongitud();
		
		assertEquals(expected, result, 0.0);
	}
	
	@Test
	public void actualizarId() {
		int expected = 2; 
		Vertice vertice = verticeValido();
		vertice.setId(expected);
		int result = vertice.getId();
		
		assertEquals(expected, result);
	}
	
	@Test
	public void actualizarPeso() {
		double expected = 20.0; 
		Vertice vertice = verticeValido();
		vertice.setPeso(expected);
		double result = vertice.getPeso();
		
		assertEquals(expected, result, 0.0);
	}
	
	@Test
	public void actualizarLatitud() {
		double expected = 34.0522; 
		Vertice vertice = verticeValido();
		vertice.setLatitud(expected);
		double result = vertice.getLatitud();
		
		assertEquals(expected, result, 0.0);
	}
	
	@Test
	public void actualizarLongitud() {
		double expected = -118.2437; 
		Vertice vertice = verticeValido();
		vertice.setLongitud(expected);
		double result = vertice.getLongitud();
		
		assertEquals(expected, result, 0.0);
	}
	
	@Test
	public void verticesIguales() {
		Vertice vertice1 = verticeValido();
		Vertice vertice2 = verticeValido();
		
		assertEquals(vertice1, vertice2);	
	}
	
	@Test
	public void verticesNoIguales() {
		Vertice vertice1 = verticeValido();
		Vertice vertice2 = verticeValido();
	
		vertice2.setId(3);
		
		assertFalse(vertice1.equals(vertice2));	
	}
}
