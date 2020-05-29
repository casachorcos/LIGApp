package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import prLIGApp.*;

class ConexionTest {

	private static final Conexion conexion = ConexionJDBC.getInstance();
	
	@BeforeAll
	static void initAll() {
		Liga liga = new Liga(conexion.generarID(), "JUnitTestBasica");
		conexion.crearLiga(liga);
	}
	
	@Test
	void testCrearLiga() {
		List<Liga> ligasAntes = conexion.listaLigas();
		Liga ultimaAntes = ligasAntes.get(ligasAntes.size()-1);
		Liga ligaCreada = new Liga(conexion.generarID(), "JUnitTestCrear");
		conexion.crearLiga(ligaCreada);
		List<Liga> ligasDespues = conexion.listaLigas();
		Liga ultimaDespues = ligasDespues.get(ligasDespues.size()-1);
		assertAll("Crear Liga",
				() -> assertNotEquals(ultimaAntes, ultimaDespues), //TODO: Identificador o nombre
				() -> assertEquals(ligaCreada, ultimaDespues));
	}
	
	@Test
	void testEliminarLiga() {
		List<Liga> ligasAntes = conexion.listaLigas();
		Liga ultimaAntes = ligasAntes.get(ligasAntes.size()-1);
		Liga ligaBorrada = new Liga(conexion.generarID(), "JUnitTestBorrar");
		conexion.eliminarLiga(ligaBorrada);
		List<Liga> ligasDespues = conexion.listaLigas();
		Liga ultimaDespues = ligasDespues.get(ligasDespues.size()-1);
		assertAll("Eliminar Liga",
				() -> assertNotEquals(ultimaDespues, ligaBorrada),
				() -> assertEquals(ultimaAntes, ultimaDespues));
	}
	
	@AfterAll
	static void tearDownAll() {
		Liga liga = new Liga(conexion.generarID(), "JUnitTestBasica");
		Liga ligaCreada = new Liga(conexion.generarID(), "JUnitTestCrear");
		conexion.eliminarLiga(liga);
		conexion.eliminarLiga(ligaCreada);
	}

	
}
