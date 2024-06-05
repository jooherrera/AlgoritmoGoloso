package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import dto.AristaDTO;
import dto.VerticeDTO;

public class MapWindow {
	private JFrame newFrame;
	private JMapViewer _mapa;
	private Map<Integer, VerticeDTO> _vertices;
	private Map<Integer, List<Integer>> _conexiones;
	private Map<Integer, MapMarker> _marcas;

	public MapWindow() {
		_vertices = new HashMap<>();
		_conexiones = new HashMap<>();
		_marcas = new HashMap<>();
		ventanaPrincipal();
		posicionarVentana(newFrame);
		iniciarMapa();
		newFrame.setVisible(true);
	}

	private void ventanaPrincipal() {
		newFrame = new JFrame("Nueva Ventana");
		newFrame.setTitle("Map View");
		newFrame.setResizable(false);
		newFrame.setAutoRequestFocus(false);
		newFrame.setSize(400, 650);
		newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newFrame.getContentPane().add(new JLabel("¡Hola, soy una nueva ventana!"));
		newFrame.getContentPane().setLayout(new BorderLayout());
	}

	private void pintarMarcas() {
		_mapa.removeAllMapMarkers();
		for (VerticeDTO vertice : _vertices.values()) {
			MapMarker marca = new MapMarkerDot(new Coordinate(vertice.getLatitud(), vertice.getLongitud()));
			_marcas.put(vertice.getId(), marca);
			marca.getStyle().setBackColor(Color.red);
			marca.getStyle().setColor(Color.red);
			_mapa.addMapMarker(marca);
		}
	}

	private void pintarConexiones() {

		_mapa.removeAllMapPolygons();

		for (int key : _vertices.keySet()) {
			VerticeDTO vertice = _vertices.get(key);
			if (vertice != null) {
				Coordinate start = new Coordinate(vertice.getLatitud(), vertice.getLongitud());
				List<Integer> conexiones = _conexiones.get(vertice.getId());

				if (conexiones == null)
					return;

				for (int vecino : conexiones) {
					double latitud = _vertices.get(vecino).getLatitud();
					double longitud = _vertices.get(vecino).getLongitud();
					Coordinate end = new Coordinate(latitud, longitud);
					MapPolygon line = new MapPolygonImpl(Arrays.asList(start, end, start));
					_mapa.addMapPolygon(line);
				}
			}
		}
	}

	private void posicionarVentana(JFrame frame) {
		// Obtener las dimensiones de la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		// Calcular las coordenadas para centrar la ventana
		int x = (screenWidth - frame.getWidth() - 50);
		int y = (screenHeight - frame.getHeight()) / 2;

		// Establecer la ubicación de la ventana
		frame.setLocation(x, y);
	}

	private void iniciarMapa() {
		_mapa = new JMapViewer();
		_mapa.setZoomControlsVisible(false);
		Coordinate coordinate = new Coordinate(-40, -66);
		_mapa.setDisplayPosition(coordinate, 4);
		newFrame.getContentPane().add(_mapa);
	}

	private void cargarVertices(List<VerticeDTO> vertices) {
		_vertices.clear();
		for (VerticeDTO vertice : vertices) {
			int id = vertice.getId();
			if (!_vertices.containsKey(id)) {
				_vertices.put(id, vertice);
			}
		}
	}

	public boolean esVisible() {
		return newFrame.isVisible();
	}

	public void cerrar() {
		newFrame.dispose();
	}

	public void setMarcas(List<VerticeDTO> vertices, List<AristaDTO> conexiones) {
		cargarVertices(vertices);
		cargarConexiones(conexiones);
		pintarConexiones();
		pintarMarcas();
	}

	private void cargarConexiones(List<AristaDTO> conexiones) {
		_conexiones.clear();
		for (AristaDTO conexion : conexiones) {
			if (!_conexiones.containsKey(conexion.getVertice1())) {
				_conexiones.put(conexion.getVertice1(), new ArrayList<>());
			}

			if (!_conexiones.containsKey(conexion.getVertice2())) {
				_conexiones.put(conexion.getVertice2(), new ArrayList<>());
			}
			_conexiones.get(conexion.getVertice1()).add(conexion.getVertice2());
			_conexiones.get(conexion.getVertice2()).add(conexion.getVertice1());

		}

	}

	public void mostrarClique(List<VerticeDTO> vertices) {

		pintarMarcas();

		for (VerticeDTO vertice : vertices) {
			if (_marcas.containsKey(vertice.getId())) {
				MapMarker marca = _marcas.get(vertice.getId());
				marca.getStyle().setBackColor(Color.blue);
				marca.getStyle().setColor(Color.blue);
			}
		}
		_mapa.repaint();
	}

}
