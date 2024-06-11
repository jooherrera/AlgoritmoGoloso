package app;

import java.util.Comparator;
import java.util.List;

import app.goloso.Solucion;
import app.goloso.Solver;
import app.loader.CargadorDeDatos;
import app.loader.GrafoWrapper;
import app.model.Arista;
import app.model.Grafo;
import app.model.Vertice;
import dto.GrafoDTO;
import dto.SolucionDTO;

public class CliquePesoMaximo {
	private Grafo _grafo;
	private GrafoWrapper _datosDeArchivo;

	public CliquePesoMaximo() {
		_grafo = new Grafo();
		_datosDeArchivo = null;
	}

	public void cargarArchivo(String rutaArchivo) {
		_datosDeArchivo = CargadorDeDatos.cargarJSON(rutaArchivo, GrafoWrapper.class);
		cargarVerticesEnGrafo();
		cargarAristasEnGrafo();
	}

	public SolucionDTO resolverMejor() {
		SolucionDTO solucionPeso = resolverPorPeso();
		SolucionDTO solucionGrado = resolverPorGrado();
		
		if (solucionPeso.getPeso() > solucionGrado.getPeso()) {
			return solucionPeso;
		}
		
		if (solucionGrado.getPeso() > solucionPeso.getPeso()) {
			return solucionGrado;
		}
		
        if (solucionPeso.getTiempo() < solucionGrado.getTiempo()) {
            return solucionPeso;
        }    
		
		return solucionGrado;
	}
	
	public SolucionDTO resolverPorPeso() {
		assertArchivoCargado();
		return resolver((v1, v2) -> compararPorPeso(v1, v2));
	}

	public SolucionDTO resolverPorGrado() {
		assertArchivoCargado();
		return resolver((v1, v2) -> compararPorGrado(v1, v2));
	}

	public GrafoDTO obtenerGrafoCargado() {
		List<Vertice> vertices = _datosDeArchivo.getVertices();
		List<Arista> aristas = _datosDeArchivo.getAristas();

		return new GrafoDTO(vertices, aristas);
	}

	private SolucionDTO resolver(Comparator<Vertice> comparador) {
		Solver solver = new Solver(_grafo, comparador);
		Solucion solucion = solver.resolver();
		return new SolucionDTO(solucion);

	}

	private void cargarAristasEnGrafo() {
		for (Arista a : _datosDeArchivo.getAristas()) {
			_grafo.agregarArista(a.getVertice1(), a.getVertice2());
		}
	}

	private void cargarVerticesEnGrafo() {
		for (Vertice v : _datosDeArchivo.getVertices()) {
			_grafo.agregarVertice(v);
		}
	}

	private int compararPorPeso(Vertice v1, Vertice v2) {
		return Double.compare(v2.getPeso(), v1.getPeso());
	}

	private int compararPorGrado(Vertice v1, Vertice v2) {
		int gradoV1 = _grafo.grado(v1.getId());
		int gradoV2 = _grafo.grado(v2.getId());
		return gradoV2 - gradoV1;
	}

	private void assertArchivoCargado() {
		if (_datosDeArchivo == null) {
			throw new RuntimeException("Debe cargar el grafo desde un archivo");
		}
	}

}
