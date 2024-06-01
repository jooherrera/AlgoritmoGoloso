package app.model;

public class Arista {
	private int _vertice1;
	private int _vertice2;

	static Arista fromArista(Arista otro) {
		int v1 = otro.getVertice1();
		int v2 = otro.getVertice2();
		return new Arista(v1,v2);
	}
	
	public Arista() {}
	
	private Arista(int v1, int v2) {
		assertVertice(v1);
		assertVertice(v2);
		_vertice1 = v1;
		_vertice2 = v2;
	}

	public int getVertice1() {
		return _vertice1;
	}
	
	public void setVertice1(int vertice) {
		assertVertice(vertice);
		_vertice1 = vertice;
	}

	public void setVertice2(int vertice) {
		assertVertice(vertice);
		_vertice2 = vertice;
	}
	
	public int getVertice2() {
		return _vertice2;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Arista arista = (Arista) o;

	    if (_vertice1 != arista._vertice1) return false;
	    return _vertice2 == arista._vertice2;
	}
	
	private void assertVertice(int vertice) {
		if(vertice < 1) {
			throw new IllegalArgumentException("Vértice no válido: " + vertice);
		}
	}
	
}
