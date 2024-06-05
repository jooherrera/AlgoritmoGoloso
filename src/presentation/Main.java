package presentation;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.CliquePesoMaximo;
import dto.AristaDTO;
import dto.SolucionDTO;
import dto.VerticeDTO;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel _lblTemporizador;
	private JLabel _lblPesoMaximo;
	private JButton _btnResolverPorPeso;
	private JButton _btnMostrarGrafo;
	private JButton _btnResolverPorGrado;
	private MapWindow _mapWindow = null; // Referencia a NewWindow
	private CliquePesoMaximo _heuristica;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		_heuristica = new CliquePesoMaximo();
		cargarMain();
		crearTitulo();
		crearSubtiulo();
		crearLblTemporizador();
		crearLblPesoMaximo();
		btnCargarGrafo();
		btnMostrarGrafo();
		btnResolverPorPeso();
		btnResolverPorGrado();
	}

	private void cargarMain() {
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void crearSubtiulo() {
		JLabel lblCliquePesoMximo = new JLabel("Clique Peso Máximo");
		lblCliquePesoMximo.setBounds(164, 66, 132, 17);
		contentPane.add(lblCliquePesoMximo);
	}

	private void crearTitulo() {
		JLabel lblNewLabel = new JLabel("Algoritmo Goloso");
		lblNewLabel.setBounds(176, 33, 114, 27);
		contentPane.add(lblNewLabel);
	}

	private void crearLblTemporizador() {
		JLabel lblTiempoDeEjecucin = new JLabel("Tiempo de ejecución:");
		lblTiempoDeEjecucin.setBounds(86, 201, 132, 17);
		contentPane.add(lblTiempoDeEjecucin);

		_lblTemporizador = new JLabel("---");
		_lblTemporizador.setBounds(230, 201, 114, 17);
		contentPane.add(_lblTemporizador);
	}

	private void crearLblPesoMaximo() {
		JLabel lblPeso = new JLabel("Peso máximo:");
		lblPeso.setBounds(86, 230, 132, 17);
		contentPane.add(lblPeso);

		_lblPesoMaximo = new JLabel("---");
		_lblPesoMaximo.setBounds(230, 230, 114, 17);
		contentPane.add(_lblPesoMaximo);
	}

	private void btnCargarGrafo() {
		JButton btnCargarGrafo = new JButton("Cargar grafo");
		btnCargarGrafo.addActionListener(x -> cargarArchivo());
		btnCargarGrafo.setBounds(129, 96, 184, 27);
		contentPane.add(btnCargarGrafo);
	}

	private void cargarArchivo() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String ruta = selectedFile.getAbsolutePath();
				_heuristica.cargarArchivo(ruta);
				habilitarBotones();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void habilitarBotones() {
		_btnResolverPorPeso.setEnabled(true);
		_btnResolverPorGrado.setEnabled(true);
		_btnMostrarGrafo.setEnabled(true);
	}

	private void btnMostrarGrafo() {
		_btnMostrarGrafo = new JButton("Mostrar grafo");
		_btnMostrarGrafo.addActionListener(x -> cargarMapa());
		_btnMostrarGrafo.setBounds(306, 33, 125, 27);
		_btnMostrarGrafo.setEnabled(false);
		contentPane.add(_btnMostrarGrafo);
	}

	private void cargarMapa() {

		if (_mapWindow != null) {
			_mapWindow.cerrar();
		}

		if (_mapWindow == null || !_mapWindow.esVisible()) {
			_mapWindow = new MapWindow();
			List<VerticeDTO> vertices = _heuristica.obtenerGrafoCargado().getVertices();
			List<AristaDTO> aristas = _heuristica.obtenerGrafoCargado().getAristas();

			_mapWindow.setMarcas(vertices, aristas);
		}

	}

	private void btnResolverPorPeso() {
		_btnResolverPorPeso = new JButton("Peso");
		_btnResolverPorPeso.addActionListener(x -> resolverPorPeso());
		_btnResolverPorPeso.setBounds(24, 152, 62, 27);
		_btnResolverPorPeso.setEnabled(false);
		contentPane.add(_btnResolverPorPeso);
	}

	private void resolverPorPeso() {
		try {
			SolucionDTO solucion = _heuristica.resolverPorPeso();
			mostrarSolucion(solucion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void btnResolverPorGrado() {
		_btnResolverPorGrado = new JButton("Grado");
		_btnResolverPorGrado.addActionListener(x -> resolverPorGrado());
		_btnResolverPorGrado.setBounds(98, 152, 70, 27);
		_btnResolverPorGrado.setEnabled(false);
		contentPane.add(_btnResolverPorGrado);
	}

	private void resolverPorGrado() {
		try {
			SolucionDTO solucion = _heuristica.resolverPorGrado();
			mostrarSolucion(solucion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void mostrarSolucion(SolucionDTO solucion) {
		actualizarTemporizador(solucion.getTiempo());
		actualizarPesoMaximo(solucion.getPeso());

		List<VerticeDTO> vertices = solucion.getVertices();

		if (_mapWindow != null && _mapWindow.esVisible()) {
			_mapWindow.mostrarClique(vertices);
		}
	}

	private void actualizarTemporizador(long tiempo) {
		_lblTemporizador.setText(String.valueOf(tiempo) + " ms");
	}

	private void actualizarPesoMaximo(double peso) {
		_lblPesoMaximo.setText(String.valueOf(peso));
	}

}