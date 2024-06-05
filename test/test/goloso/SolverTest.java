package test.goloso;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import app.goloso.Solucion;
import app.goloso.Solver;
import app.model.Grafo;
import app.model.Vertice;

public class SolverTest {

	@Test
	public void cliqueCompletoTest() {
		Solver solver = new Solver(grafoTriangulo(), new Comparator<Vertice>() {
			@Override
			public int compare(Vertice uno, Vertice otro) {
				return (int)(otro.getPeso() - uno.getPeso());
			}
		});
		
		Solucion sol = solver.resolver();
		Grafo esperado = grafoTriangulo();
		
		Assert.iguales(esperado.obtenerVertices(), sol.obtenerClique());
	}
	
	@Test
	public void cliqueDosVerticesTest() {
		Solver solver = new Solver(grafoTrianguloConAntena(), new Comparator<Vertice>() {
			@Override
			public int compare(Vertice uno, Vertice otro) {
				return (int)(otro.getPeso() - uno.getPeso());
			}
		});
		
		Solucion sol = solver.resolver();
		Grafo esperado = grafoDosVerticesArista_1_4();
		
		Assert.iguales(esperado.obtenerVertices(), sol.obtenerClique());
	}
	
	@Test
	public void aisladosTest() {
		Solver solver = new Solver(grafoDosVerticesAislados(), new Comparator<Vertice>() {
			@Override
			public int compare(Vertice uno, Vertice otro) {
				return (int)(otro.getPeso() - uno.getPeso());
			}
		});
		
		Solucion sol = solver.resolver();
		Grafo esperado = grafoUnicoVertice();
		
		Assert.iguales(esperado.obtenerVertices(), sol.obtenerClique());
	}
	
	
	private Grafo grafoTriangulo() {
		Grafo ret = new Grafo();
		
		Vertice v1 = new Vertice();
		v1.setId(1);
		v1.setPeso(4);
		v1.setLatitud(-31);
		v1.setLongitud(-61);
		
		Vertice v2 = new Vertice();
		v2.setId(2);
		v2.setPeso(2);
		v2.setLatitud(-36);
		v2.setLongitud(-60);
		
		Vertice v3 = new Vertice();
		v3.setId(3);
		v3.setPeso(5);
		v3.setLatitud(-37);
		v3.setLongitud(-64);
		
		ret.agregarVertice(v1);
		ret.agregarVertice(v2);
		ret.agregarVertice(v3);
		
		ret.agregarArista(1, 2);
		ret.agregarArista(1, 3);
		ret.agregarArista(2, 3);
		
		return ret;
	}
	
	private Grafo grafoTrianguloConAntena() {
		Grafo ret = new Grafo();
		
		Vertice v1 = new Vertice();
		v1.setId(1);
		v1.setPeso(4);
		v1.setLatitud(-31);
		v1.setLongitud(-61);
		
		Vertice v2 = new Vertice();
		v2.setId(2);
		v2.setPeso(2);
		v2.setLatitud(-36);
		v2.setLongitud(-60);
		
		Vertice v3 = new Vertice();
		v3.setId(3);
		v3.setPeso(5);
		v3.setLatitud(-37);
		v3.setLongitud(-64);
		
		Vertice v4 = new Vertice();
		v4.setId(4);
		v4.setPeso(8);
		v4.setLatitud(-29);
		v4.setLongitud(-61);
		
		ret.agregarVertice(v1);
		ret.agregarVertice(v2);
		ret.agregarVertice(v3);
		ret.agregarVertice(v4);
		
		ret.agregarArista(1, 2);
		ret.agregarArista(1, 3);
		ret.agregarArista(2, 3);
		ret.agregarArista(1, 4);
		
		return ret;
	}
	
	private Grafo grafoDosVerticesArista_1_4() {
		Grafo ret = new Grafo();
		
		Vertice v1 = new Vertice();
		v1.setId(1);
		v1.setPeso(4);
		v1.setLatitud(-31);
		v1.setLongitud(-61);
		
		Vertice v4 = new Vertice();
		v4.setId(4);
		v4.setPeso(8);
		v4.setLatitud(-29);
		v4.setLongitud(-61);
		
		ret.agregarVertice(v1);
		ret.agregarVertice(v4);
		
		ret.agregarArista(1, 4);
		
		return ret;
	}
	
	private Grafo grafoDosVerticesAislados() {
		Grafo ret = new Grafo();
		
		Vertice v1 = new Vertice();
		v1.setId(1);
		v1.setPeso(4);
		v1.setLatitud(-31);
		v1.setLongitud(-61);
		
		Vertice v2 = new Vertice();
		v2.setId(2);
		v2.setPeso(2);
		v2.setLatitud(-36);
		v2.setLongitud(-60);
		
		ret.agregarVertice(v1);
		ret.agregarVertice(v2);
		
		return ret;
	}

	private Grafo grafoUnicoVertice() {
		Grafo ret = new Grafo();
		
		Vertice v1 = new Vertice();
		v1.setId(1);
		v1.setPeso(4);
		v1.setLatitud(-31);
		v1.setLongitud(-61);
		
		ret.agregarVertice(v1);
		
		return ret;
	}
}
