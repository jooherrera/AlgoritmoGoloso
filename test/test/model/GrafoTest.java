package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.model.Grafo;
import app.model.Vertice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

public class GrafoTest {

    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
    }

    private Vertice crearVertice(int id, double peso, double latitud, double longitud) {
        Vertice vertice = new Vertice();
        vertice.setId(id);
        vertice.setPeso(peso);
        vertice.setLatitud(latitud);
        vertice.setLongitud(longitud);
        return vertice;
    }

    @Test
    public void agregarYObtenerVertice() {
        Vertice vertice = crearVertice(1, 10.0, 40.7128, -74.0060);
        grafo.agregarVertice(vertice);

        ArrayList<Vertice> vertices = grafo.obtenerVertices();
        assertEquals(1, vertices.size());
        assertEquals(vertice, vertices.get(0));
    }

    @Test
    public void agregarYObtenerAristas() {
        Vertice vertice1 = crearVertice(1, 10.0, 40.7128, -74.0060);
        Vertice vertice2 = crearVertice(2, 15.0, 34.0522, -118.2437);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);
        grafo.agregarArista(1, 2);

        ArrayList<Integer> vecinos1 = grafo.obtenerVecinos(1);
        ArrayList<Integer> vecinos2 = grafo.obtenerVecinos(2);

        assertTrue(vecinos1.contains(2));
        assertTrue(vecinos2.contains(1));
    }

    @Test
    public void existeConexion() {
        Vertice vertice1 = crearVertice(1, 10.0, 40.7128, -74.0060);
        Vertice vertice2 = crearVertice(2, 15.0, 34.0522, -118.2437);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);
        grafo.agregarArista(1, 2);

        assertTrue(grafo.existeConexion(1, 2));
        assertTrue(grafo.existeConexion(2, 1));
    }

    @Test
    public void noExisteConexion() {
        Vertice vertice1 = crearVertice(1, 10.0, 40.7128, -74.0060);
        Vertice vertice2 = crearVertice(2, 15.0, 34.0522, -118.2437);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);

        assertFalse(grafo.existeConexion(1, 2));
    }

    @Test
    public void obtenerGrado() {
        Vertice vertice1 = crearVertice(1, 10.0, 40.7128, -74.0060);
        Vertice vertice2 = crearVertice(2, 15.0, 34.0522, -118.2437);
        Vertice vertice3 = crearVertice(3, 20.0, 51.5074, -0.1278);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);
        grafo.agregarVertice(vertice3);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);

        assertEquals(2, grafo.grado(1));
        assertEquals(1, grafo.grado(2));
        assertEquals(1, grafo.grado(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verticeNoValido() {
        grafo.agregarArista(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verticeNoValidoEnObtencionVecinos() {
        grafo.obtenerVecinos(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verticeNoValidoEnGrado() {
        grafo.grado(0);
    }
}
