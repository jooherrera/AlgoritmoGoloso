package test.goloso;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import app.goloso.Solucion;
import app.model.Vertice;

public class SolucionTest {
	
	private Vertice crearVertice(int id) {
		Vertice v = new Vertice();
		v.setId(id);
		v.setPeso(10.0);
		v.setLatitud(20);
		v.setLongitud(20);
		return v;
	}

	@Test
	public void agregarVerticeTest() {
		Solucion solucion = new Solucion();
		Vertice v = crearVertice(1);
		
		solucion.agregarVertice(v);
		
		assertEquals(10.0, solucion.obtenerPeso(), 0);
		
	}

	@Test
	public void obtenerCliqueTest() {
		Solucion solucion = new Solucion();
		Vertice v1 = crearVertice(1);
		Vertice v2 = crearVertice(2);
		solucion.agregarVertice(v1);
		solucion.agregarVertice(v2);
		ArrayList<Vertice> expected = new ArrayList<>();
		expected.add(v1);
		expected.add(v2);
		
		ArrayList<Vertice> clique = solucion.obtenerClique();
		
		assertEquals(expected, clique);	
	}
}
