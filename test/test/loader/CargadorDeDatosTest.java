package test.loader;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.loader.CargadorDeDatos;
import app.loader.GrafoWrapper;
import app.model.Arista;
import app.model.Vertice;

public class CargadorDeDatosTest {

	ArrayList<Vertice> vertices;
	ArrayList<Arista> aristas ;
	
	@Before
	public void setup() {
		setupVertices();		
		setupAristas();
	}

	private void setupAristas() {
		// Se hace de esta manera porque la librería funciona así
		Arista arista = new Arista();
		arista.setVertice1(1);
		arista.setVertice2(2);
		aristas = new ArrayList<>();
		aristas.add(arista);
	}

	private void setupVertices() {
		// Se hace de esta manera porque la librería funciona así
		Vertice vertice1 = new Vertice();
		vertice1.setId(1);
		vertice1.setPeso(1);
		vertice1.setLatitud(1);
		vertice1.setLongitud(1);
		Vertice vertice2 = new Vertice();
		vertice2.setId(2);
		vertice2.setPeso(2);
		vertice2.setLatitud(2);
		vertice2.setLongitud(2);
		vertices = new ArrayList<>();	
		vertices.add(vertice1);
		vertices.add(vertice2);
	}
	
	@Test
	public void cargaJSON() {
		String filePath = "./test/archivoTest.json";
		GrafoWrapper expected = new GrafoWrapper();
		
		expected.setVertices(vertices);
		expected.setAristas(aristas);
			
		GrafoWrapper datosDelArchivo = CargadorDeDatos.cargarJSON(filePath, GrafoWrapper.class);
		
		assertEquals(datosDelArchivo.getVertices(), expected.getVertices());
		assertEquals(datosDelArchivo.getAristas(), expected.getAristas());
	}

}
