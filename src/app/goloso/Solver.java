package app.goloso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import app.model.Grafo;
import app.model.Vertice;

public class Solver {
	
	private Grafo _instancia;
	private Comparator<Vertice> _comparador;
	
	public Solver(Grafo instancia, Comparator<Vertice> comparador) {
		_instancia = instancia;
		_comparador = comparador;
	}
	
	public Solucion resolver() {
		Solucion ret = new Solucion();
		
		for (Vertice vertice : verticesOrdenados()) {
			if (estaConectadoConTodos(vertice, ret.obtenerClique())) {
				ret.agregarVertice(vertice);
			}
		}
		
		ret.finalizarTemporizador();
		return ret;
	}
	
	public boolean estaConectadoConTodos(Vertice vertice, ArrayList<Vertice> clique) {
		boolean ret = true;
		
		for (Vertice otro : clique) {
			ret = ret && _instancia.existeConexion(vertice.getId(), otro.getId());
		}
		
		return ret;
	}
	
	private ArrayList<Vertice> verticesOrdenados() {
		ArrayList<Vertice> ret = _instancia.obtenerVertices();
		Collections.sort(ret, _comparador);
		
		return ret;
	}
}