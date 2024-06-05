package dto;

public class VerticeDTO {
	
	private int _id;
	private double _peso;
	private double _latitud;
	private double _longitud;
		
	public VerticeDTO(int id, double peso, double latitud, double longitud) {
		_id = id;
		_peso = peso;
		_latitud = latitud;
		_longitud = longitud;
	}
	
	public int get_id() {
		return _id;
	}
	public double get_peso() {
		return _peso;
	}
	public double get_latitud() {
		return _latitud;
	}
	public double get_longitud() {
		return _longitud;
	}
	
}
