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
    private String archivo = "test/archivoTest.json";

    @Before
    public void setUp() throws IOException {
        String textoArchivoTest = "{\n" +
                "\t\"vertices\": [\n" +
                "\t\t{ \"id\": 1, \"peso\": 1, \"latitud\": 1, \"longitud\": 1 },\n" +
                "\t\t{ \"id\": 2, \"peso\": 2, \"latitud\": 2, \"longitud\": 2 }\n" +
                "\t],\n" +
                "\t\"aristas\": [{ \"vertice1\": 1, \"vertice2\": 2 }]\n" +
                "}";
        
        File archivoTest = new File(archivo);
        if (!archivoTest.exists()) {
        	archivoTest.createNewFile();
        }
        try (FileWriter writer = new FileWriter(archivoTest)) {
            writer.write(textoArchivoTest);
        }

        cliquePesoMaximo = new CliquePesoMaximo();
        cliquePesoMaximo.cargarArchivo(archivoTest.getAbsolutePath());
    }

    @Test
    public void testCargarArchivo() {
        GrafoDTO grafoDTO = cliquePesoMaximo.obtenerGrafoCargado();
        assertNotNull(grafoDTO);
        assertEquals(2, grafoDTO.getVertices().size());
        assertEquals(1, grafoDTO.getAristas().size());
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

}
