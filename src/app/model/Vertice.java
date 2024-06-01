package app.model;

public class Vertice {
	private int _id;
	private double _peso;
	private double _latitud;
	private double _longitud;
	
	public Vertice() {};

	private Vertice(int id, double peso, double latitud, double longitud) {
		assertVertice(id);
		assertPeso(peso);
		_id = id;
		_peso = peso;
		_latitud = latitud;
		_longitud = longitud;
	}



	static public Vertice fromVertice(Vertice otro) {
		int id = otro.getId();
		double peso = otro.getPeso();
		double latitud = otro.getLatitud();
		double longitud = otro.getLongitud();
		return new Vertice(id,peso,latitud,longitud);
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		assertVertice(id);
		this._id = id;
	}

	public double getPeso() {
		return _peso;
	}

	public void setPeso(double peso) {
		assertPeso(peso);
		this._peso = peso;
	}

	public double getLatitud() {
		return _latitud;
	}

	public void setLatitud(double latitud) {
		this._latitud = latitud;
	}

	public double getLongitud() {
		return _longitud;
	}

	public void setLongitud(double longitud) {
		this._longitud = longitud;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Vertice vertice = (Vertice) o;

	    if (_id != vertice._id) return false;
	    if (Double.compare(vertice._peso, _peso) != 0) return false;
	    if (Double.compare(vertice._latitud, _latitud) != 0) return false;
	    return Double.compare(vertice._longitud, _longitud) == 0;
	}
	
	private void assertVertice(int vertice) {
		if(vertice < 1) {
			throw new IllegalArgumentException("Vértice no válido: " + vertice);
		}
	}
	
	private void assertPeso(double peso) {
		if(peso < 0) {
			throw new IllegalArgumentException("Peso no válido: " + peso);

		}
	}

}
