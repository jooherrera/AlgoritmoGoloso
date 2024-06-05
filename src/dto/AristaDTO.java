package dto;

import app.model.Arista;

public class AristaDTO {
	
	private int _vertice1;
	private int _vertice2;
	
	public AristaDTO(Arista arista) {
		this._vertice1 = arista.getVertice1();
		this._vertice2 = arista.getVertice2();
	}

	public int getVertice1() {
		return _vertice1;
	}
	public int getVertice2() {
		return _vertice2;
	}

}
