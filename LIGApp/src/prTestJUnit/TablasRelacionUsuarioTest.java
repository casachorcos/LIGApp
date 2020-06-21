package prTestJUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
	
	
	private static final Conexion conexion = ConexionJDBC.getInstance();
	
	private static Liga liga = new Liga(conexion.generarID(), "JUnitTestBasica");
	private static Liga ligaCreada = new Liga(conexion.generarID(), "JUnitTestCrear");
	
	private static Equipo equipo = new Equipo(conexion.generarID(), "JUnitTestBasica");
	private static Equipo equipoCreado = new Equipo(conexion.generarID(), "JUnitTestCrear");
	
	private static Jugador jugador = new Jugador(conexion.generarID(), "JUnitTestBasica", 0);
	private static Jugador jugadorCreado = new Jugador(conexion.generarID(), "JUnitTestCrear", 0);
	
	
	private static Usuario usuarioAux = new Usuario("JUnitTestUserAux", "", "");
	
	
	@BeforeAll
	static void initAll() {
		
		conexion.crearUsuario(usuarioAux);
		
		conexion.crearLiga_Usuario(liga, usuarioAux.getNombre());
		conexion.crearEquipo_Usuario(equipo, usuarioAux.getNombre());
		conexion.crearJugador_Usuario(jugador, usuarioAux.getNombre());
		
	}
	

	@Test
	void testCrearLigaParaUsuario() {
		List<Liga> ligasAntes = conexion.usuario_liga(usuarioAux.getNombre());
		Liga ultimaAntes = ligasAntes.get(ligasAntes.size()-1);
		
		conexion.crearLiga_Usuario(ligaCreada, usuarioAux.getNombre());
		
		List<Liga> ligasDespues = conexion.usuario_liga(usuarioAux.getNombre());
		Liga ultimaDespues = ligasDespues.get(ligasDespues.size()-1);
		
		assertAll("Crear Liga para Usuario",
				() -> assertNotEquals(ultimaAntes, ultimaDespues),
				() -> assertEquals(ligaCreada, ultimaDespues));
	}
	
	
	@Test
	void testEliminarLigaParaUsuario() {
		List<Liga> ligasAntes = conexion.usuario_liga(usuarioAux.getNombre());
		Liga ultimaAntes = ligasAntes.get(ligasAntes.size()-1);
		
		Liga ligaBorrada = new Liga(conexion.generarID(), "JUnitTestBorrar");
		conexion.crearLiga_Usuario(ligaBorrada, usuarioAux.getNombre());
		conexion.eliminarLiga_Us(ligaBorrada, usuarioAux.getNombre());
		
		List<Liga> ligasDespues = conexion.usuario_liga(usuarioAux.getNombre());
		Liga ultimaDespues = ligasDespues.get(ligasDespues.size()-1);
		
		assertAll("Eliminar Liga para Usuario",
				() -> assertNotEquals(ultimaDespues, ligaBorrada),
				() -> assertEquals(ultimaAntes, ultimaDespues));
	}
	
	
	@Test
	void testCrearEquipoParaUsuario() {
		List<Equipo> equiposAntes = conexion.usuario_equipo(usuarioAux.getNombre());
		Equipo ultimoAntes = equiposAntes.get(equiposAntes.size()-1);
		
		conexion.crearEquipo_Usuario(equipoCreado, usuarioAux.getNombre());
		
		List<Equipo> equiposDespues = conexion.usuario_equipo(usuarioAux.getNombre());
		Equipo ultimoDespues = equiposDespues.get(equiposDespues.size()-1);
		
		assertAll("Crear Equipo para Usuario",
				() -> assertNotEquals(ultimoAntes, ultimoDespues),
				() -> assertEquals(equipoCreado, ultimoDespues));
	}
	
	
	@Test
	void testEliminarEquipoParaUsuario() {
		List<Equipo> equiposAntes = conexion.usuario_equipo(usuarioAux.getNombre());
		Equipo ultimoAntes = equiposAntes.get(equiposAntes.size()-1);
		
		Equipo equipoBorrado = new Equipo(conexion.generarID(), "JUnitTestBorrar");
		conexion.crearEquipo_Usuario(equipoBorrado, usuarioAux.getNombre());
		conexion.eliminarEquipo_Us(equipoBorrado, usuarioAux.getNombre());
		
		List<Equipo> equiposDespues = conexion.usuario_equipo(usuarioAux.getNombre());
		Equipo ultimoDespues = equiposDespues.get(equiposDespues.size()-1);
		
		assertAll("Eliminar Equipo para Usuario",
				() -> assertNotEquals(ultimoDespues, equipoBorrado),
				() -> assertEquals(ultimoAntes, ultimoDespues));
	}
	
	@Test
	void testCrearJugadorParaUsuario() {
		List<Jugador> jugadoresAntes = conexion.usuario_jugador(usuarioAux.getNombre());
		Jugador ultimoAntes = jugadoresAntes.get(jugadoresAntes.size()-1);
		
		conexion.crearJugador_Usuario(jugadorCreado, usuarioAux.getNombre());
		
		List<Jugador> jugadoresDespues = conexion.usuario_jugador(usuarioAux.getNombre());
		Jugador ultimoDespues = jugadoresDespues.get(jugadoresDespues.size()-1);
		
		assertAll("Crear Jugador para Usuario",
				() -> assertNotEquals(ultimoAntes, ultimoDespues),
				() -> assertEquals(jugadorCreado, ultimoDespues));
	}
	
	
	@Test
	void testEliminarJugadorParaUsuario() {
		List<Jugador> jugadoresAntes = conexion.usuario_jugador(usuarioAux.getNombre());
		Jugador ultimoAntes = jugadoresAntes.get(jugadoresAntes.size()-1);
		
		Jugador jugadorBorrado = new Jugador(conexion.generarID(), "JUnitTestBorrar", 0);
		conexion.crearJugador_Usuario(jugadorBorrado, usuarioAux.getNombre());
		conexion.eliminarJugador_Us(jugadorBorrado, usuarioAux.getNombre());
		
		List<Jugador> jugadoresDespues = conexion.usuario_jugador(usuarioAux.getNombre());
		Jugador ultimoDespues = jugadoresDespues.get(jugadoresDespues.size()-1);
		
		assertAll("Eliminar Jugador para Usuario",
				() -> assertNotEquals(ultimoDespues, jugadorBorrado),
				() -> assertEquals(ultimoAntes, ultimoDespues));
	}

	
	@AfterAll
	static void tearDownAll() {
		conexion.eliminarLiga(liga);
		conexion.eliminarLiga_Us(liga, usuarioAux.getNombre());
		conexion.eliminarLiga(ligaCreada);
		conexion.eliminarLiga_Us(ligaCreada, usuarioAux.getNombre());
		
		conexion.eliminarEquipo(equipo);
		conexion.eliminarEquipo_Us(equipo, usuarioAux.getNombre());
		conexion.eliminarEquipo(equipoCreado);
		conexion.eliminarEquipo_Us(equipoCreado, usuarioAux.getNombre());
		
		conexion.eliminarJugador(jugador);
		conexion.eliminarJugador_Us(jugador, usuarioAux.getNombre());
		conexion.eliminarJugador(jugadorCreado);
		conexion.eliminarJugador_Us(jugadorCreado, usuarioAux.getNombre());
		
		
		conexion.eliminarUsuario(usuarioAux);
		
	}
	
	
	
}
