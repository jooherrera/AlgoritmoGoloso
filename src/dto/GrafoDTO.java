package dto;

import java.util.ArrayList;
import java.util.List;
import app.model.Arista;
import app.model.Vertice;

public class GrafoDTO {
	private List<Vertice> _vertices;
	private List<Arista> _aristas;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GrafoDTO(List<Vertice> vertices, List<Arista> aristas) {
		//Crea una copia de la lista original por encapsulamiento
	    //y convierte su tipo List a ArrayList mediante downcasting
		_vertices = new ArrayList(vertices);
		_aristas = new ArrayList(aristas);
	}
	

	public List<Vertice> get_vertices() {
		return _vertices;
	}

	public List<Arista> get_aristas() {
		return _aristas;
	}
	
}
