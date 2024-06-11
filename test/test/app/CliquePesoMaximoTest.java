package test.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.CliquePesoMaximo;
import dto.GrafoDTO;
import dto.SolucionDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CliquePesoMaximoTest {

	private CliquePesoMaximo cliquePesoMaximo;

	@Before
	public void algoritmoCargadoConJson() {
		String filePath = "./test/archivoTestGrado.json";
		cliquePesoMaximo = new CliquePesoMaximo();
		cliquePesoMaximo.cargarArchivo(filePath);
	}

	@Test
	public void testCargarArchivo() {
		GrafoDTO grafoDTO = cliquePesoMaximo.obtenerGrafoCargado();
		assertNotNull(grafoDTO);
		assertEquals(5, grafoDTO.getVertices().size());
		assertEquals(4, grafoDTO.getAristas().size());
	}

	@Test(expected = RuntimeException.class)
	public void testCargarArchivoInexistente() {
		CliquePesoMaximo cpm = new CliquePesoMaximo();
		cpm.cargarArchivo("archivo_inexistente.json");
	}

	@Test(expected = RuntimeException.class)
	public void testResolverPorPesoSinArchivo() {
		CliquePesoMaximo cliquePesoMaximoSinCargar = new CliquePesoMaximo();
		cliquePesoMaximoSinCargar.resolverPorPeso();
	}

	@Test
	public void resolverPesoTest() {
		SolucionDTO result = cliquePesoMaximo.resolverPorPeso();

		assertEquals(result.getPeso(), 7.0, 0);
	}

	@Test
	public void resolverGradoTest() {
		SolucionDTO result = cliquePesoMaximo.resolverPorGrado();

		assertEquals(result.getPeso(), 3.0, 0);
	}

	@Test
	public void resolverMejorTest() {
		SolucionDTO result = cliquePesoMaximo.resolverMejor();

		assertEquals(result.getPeso(), 7.0, 0);
	}

}
