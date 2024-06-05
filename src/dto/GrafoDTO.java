package dto;

import java.util.ArrayList;
import java.util.List;

import app.model.Arista;
import app.model.Vertice;

public class GrafoDTO {
	private List<Vertice> _vertices;
	private List<Arista> _aristas;
	
	
	public GrafoDTO(List<Vertice> vertices, List<Arista> aristas) {
		_vertices = new ArrayList<>(vertices);
		_aristas = new ArrayList<>(aristas);
	}
	

	public List<VerticeDTO> getVertices() {
    	ArrayList<VerticeDTO> vertices = new ArrayList<>();
    	for(Vertice v : _vertices) {
    		vertices.add(new VerticeDTO(v));
    	}
        return vertices;
	}

	public List<AristaDTO> getAristas() {
    	ArrayList<AristaDTO> aristas = new ArrayList<>();
    	for(Arista a : _aristas) {
    		aristas.add(new AristaDTO(a));
    	}
        return aristas;
	}
	
}
