package dto;

import app.model.Vertice;

public class VerticeDTO {
	
	private int _id;
	private double _peso;
	private double _latitud;
	private double _longitud;
		
	public VerticeDTO(Vertice v) {
		_id = v.getId();
		_peso = v.getPeso();
		_latitud = v.getLatitud();
		_longitud = v.getLongitud();
	}
	
	public int getId() {
		return _id;
	}
	public double getPeso() {
		return _peso;
	}
	public double getLatitud() {
		return _latitud;
	}
	public double getLongitud() {
		return _longitud;
	}
	
}
