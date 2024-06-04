package app.goloso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import app.model.Vertice;

public class Solucion {
	private Set<Vertice> _clique;
	private double _peso;
	private long _tiempoDeEjecucion;
	
	public Solucion() {
		_clique = new HashSet<Vertice>();
		iniciarTemporizador();
	}
	
	public void agregarVertice(Vertice vertice) {
		_clique.add(vertice);
		_peso += vertice.getPeso();
	}
	
	public ArrayList<Vertice> obtenerClique(){
		ArrayList<Vertice> ret = new ArrayList<>();
		for(Vertice vertice : _clique) {
			ret.add(Vertice.fromVertice(vertice));
		}
		return ret;
	}
	
	public void finalizarTemporizador() {
        _tiempoDeEjecucion =  System.currentTimeMillis() - _tiempoDeEjecucion;
	}
	
	public double obtenerPeso() {
		return _peso;
	}
	
	public long obtenerTiempo() {
		return _tiempoDeEjecucion;
	}
	private void iniciarTemporizador() {
        _tiempoDeEjecucion = System.currentTimeMillis();
	}

}
