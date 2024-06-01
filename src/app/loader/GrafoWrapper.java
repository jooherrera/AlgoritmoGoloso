package app.loader;

import java.util.List;

import app.model.Arista;
import app.model.Vertice;

public class GrafoWrapper {
    private List<Vertice> vertices;
    private List<Arista> aristas;

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
    }
}
