package prTestJUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Jugador;
import prLIGAppModelo.Liga;
import prLIGAppModelo.Usuario;

class TablasRelacionUsuarioTest {
	
	//TODO: Drops de foreign key en Partido y Clasificacion
	
	
	private static final Conexion conexion = ConexionJDBC.getInstance();
	
	private static Liga liga = new Liga(conexion.generarID(), "JUnitTestBasica");
	private static Liga ligaCreada = new Liga(conexion.generarID(), "JUnitTestCrear");
	
	private static Equipo equipo = new Equipo(conexion.generarID(), "JUnitTestBasica");
	private static Equipo equipoCreado = new Equipo(conexion.generarID(), "JUnitTestCrear");
	
	private static Jugador jugador = new Jugador(conexion.generarID(), "JUnitTestBasica", 0);
	private static Jugador jugadorCreado = new Jugador(conexion.generarID(), "JUnitTestCrear", 0);
	
	
	// TODO: Cambiar contraseña cuando tenga un minimo de caracteres
	private static Usuario usuarioAux = new Usuario("JUnitTestUserAux", "", "");
	
	
	@BeforeAll
	static void initAll() {
		
		conexion.crearUsuario(usuarioAux);
		
		conexion.crearLiga(liga);
		conexion.crearLiga_Usuario(liga, usuarioAux.getNombre());
		//conexion.crearEquipo_Usuario(equipo, usuarioAux.getNombre());
		//conexion.crearJugador_Usuario(jugador, usuarioAux.getNombre());
		
	}
	

	@Test
	void testCrearLigaParaUsuario() {
		conexion.crearLiga(ligaCreada);
		conexion.crearLiga_Usuario(ligaCreada, usuarioAux.getNombre());
		
		List<Liga> ligasDespues = conexion.usuario_liga(usuarioAux.getNombre());
		Liga ultimaDespues = ligasDespues.get(ligasDespues.size()-1);
		assertAll("Crear Liga para Usuario",
				() -> assertEquals(ligaCreada, ultimaDespues));
	}
	
	
	@Test
	void testEliminarLigaParaUsuario() {
		List<Liga> ligasAntes = conexion.usuario_liga(usuarioAux.getNombre());
		Liga ultimaAntes = ligasAntes.get(ligasAntes.size()-1);
		
		Liga ligaBorrada = new Liga(conexion.generarID(), "JUnitTestBorrar");
		conexion.crearLiga_Usuario(ligaBorrada, usuarioAux.getNombre());
		conexion.crearLiga(ligaBorrada);
		conexion.eliminarLiga_Us(ligaBorrada, usuarioAux.getNombre());
		conexion.eliminarLiga(ligaBorrada);
		
		List<Liga> ligasDespues = conexion.usuario_liga(usuarioAux.getNombre());
		Liga ultimaDespues = ligasDespues.get(ligasDespues.size()-1);
		
		assertAll("Eliminar Liga para Usuario",
				() -> assertNotEquals(ultimaDespues, ligaBorrada),
				() -> assertEquals(ultimaAntes, ultimaDespues));
	}

	
	@AfterAll
	static void tearDownAll() {
		conexion.eliminarLiga(liga);
		conexion.eliminarLiga_Us(liga, usuarioAux.getNombre());
		conexion.eliminarLiga(ligaCreada);
		conexion.eliminarLiga_Us(ligaCreada, usuarioAux.getNombre());
		//conexion.eliminarEquipo_Us(equipo, usuarioAux.getNombre());
		//conexion.eliminarJugador_Us(jugador, usuarioAux.getNombre());
		
		
		conexion.eliminarUsuario(usuarioAux);
		
	}
	
	
	
}
