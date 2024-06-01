package app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grafo {
	private Map<Integer, Vertice> _vertices; // id , Vertice
	private Map<Integer, ArrayList<Integer>> _vecinos; // id, vecinos

	public Grafo() {
		_vertices = new HashMap<>();
		_vecinos = new HashMap<>();
	}

	public void agregarVertice(Vertice vertice) {
		int id = vertice.getId();
		_vertices.put(id, vertice);
		_vecinos.put(id, new ArrayList<>());
	}

	public void agregarArista(int origen, int destino) {
		assertVertice(origen);
		assertVertice(destino);
		
		_vecinos.get(origen).add(destino);
		_vecinos.get(destino).add(origen);
	}

	public ArrayList<Integer> obtenerVecinos(int vertice) {
		assertVertice(vertice);
		ArrayList<Integer> ret = new ArrayList<>();
		for (int vecino : _vecinos.get(vertice)) {
			ret.add(vecino);
		}
		return ret;
	}

	public ArrayList<Vertice> obtenerVertices() {
		ArrayList<Vertice> ret = new ArrayList<>();
		for(Vertice vertice : _vertices.values()) {
			ret.add(Vertice.fromVertice(vertice));
		}
		return ret;
	}

	public boolean existeConexion(int vertice, int vecino) {
		assertVertice(vertice);
		assertVertice(vecino);
		return obtenerVecinos(vertice).contains(vecino);
	}

	public int grado(int vertice) {
		assertVertice(vertice);
		return obtenerVecinos(vertice).size();
	}
	
	private void assertVertice(int vertice) {
		if(vertice < 1) {
			throw new IllegalArgumentException("Vértice no válido: " + vertice);
		}
	}
	
}
