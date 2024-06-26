package app.loader;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CargadorDeDatos {
	public static <T> T cargarJSON(String rutaArchivo, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return  mapper.readValue(new File(rutaArchivo), clazz);
		} catch (IOException e) {
			throw new RuntimeException("Error al cargar: " + clazz.getName() + " -> " + e.getMessage());

		}
	}
}
