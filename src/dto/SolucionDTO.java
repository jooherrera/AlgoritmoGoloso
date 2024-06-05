package dto;

import java.util.ArrayList;
import java.util.List;

import app.goloso.Solucion;
import app.model.Vertice;

public class SolucionDTO {
	private double _peso;
	private List<Vertice> _vertices;
	private long _tiempoDeEjecucion;
	
	public SolucionDTO(Solucion solucion){
		_peso = solucion.obtenerPeso();
		_vertices = solucion.obtenerClique();
		_tiempoDeEjecucion = solucion.obtenerTiempo();
	}
	
	public double getPeso(){
		return _peso;
	}
	
	public List<VerticeDTO> getVertices(){
    	ArrayList<VerticeDTO> vertices = new ArrayList<>();
    	for(Vertice v : _vertices) {
    		vertices.add(new VerticeDTO(v));

    	}
        return vertices;
	}
	
	public long getTiempo() {
		return _tiempoDeEjecucion;
	}
}
