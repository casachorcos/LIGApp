package prTestJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Jugador;
import prLIGAppModelo.Liga;
import prLIGAppModelo.Usuario;

import java.util.List;

class BasicoTest {

	private static final Conexion conexion = ConexionJDBC.getInstance();
	
	private static Liga liga = new Liga(conexion.generarID(), "JUnitTestBasica");
	private static Liga ligaCreada = new Liga(conexion.generarID(), "JUnitTestCrear");
	
	private static Equipo equipo = new Equipo(conexion.generarID(), "JUnitTestBasica");
	private static Equipo equipoCreado = new Equipo(conexion.generarID(), "JUnitTestCrear");
	
	private static Jugador jugador = new Jugador(conexion.generarID(), "JUnitTestBasica", 0);
	private static Jugador jugadorCreado = new Jugador(conexion.generarID(), "JUnitTestCrear", 0);
	
	// Comenzamos con zzz para que quede el ultimo en la lista de usuarios
	
	// TODO: Cambiar contraseña cuando tenga un minimo de caracteres
	private static Usuario usuario = new Usuario("zzzATest", "", "");
	private static Usuario usuarioCreado = new Usuario("zzzBTest", "", "");

	
	/*
	 * Creamos un objeto de cada clase que vamos a probar
	 * para asegurarnos que cuando intentemos borrar siempre
	 * haya alguno
	 */
	
	@BeforeAll
	static void initAll() {
		
		conexion.crearLiga(liga);
		conexion.crearEquipo(equipo);
		conexion.crearJugador(jugador);
		conexion.crearUsuario(usuario);
		
	}
	
	/*
	 * Cuando insertamos una liga se posiciona al final
	 * de la tabla. Comprobamos que, en la lista, la
	 * última liga es diferente a la anterior antes de añadirse esta
	 */
	
	@Test
	void testCrearLiga() {
		List<Liga> ligasAntes = conexion.listaLigas();
		Liga ultimaAntes = ligasAntes.get(ligasAntes.size()-1);
		conexion.crearLiga(ligaCreada);
		List<Liga> ligasDespues = conexion.listaLigas();
		Liga ultimaDespues = ligasDespues.get(ligasDespues.size()-1);
		assertAll("Crear Liga",
				() -> assertNotEquals(ultimaAntes, ultimaDespues),
				() -> assertEquals(ligaCreada, ultimaDespues));
	}
	
	
	/*
	 * Comprobamos que la liga borrada no sigue en la lista
	 * y que la última liga es la misma que antes de la operación
	 */
	
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
	
	/*
	 * Cuando insertamos una equipo se posiciona al final
	 * de la tabla. Comprobamos que, en la lista, el
	 * último equipo es diferente al anterior antes de añadirse este
	 */
	
	@Test
	void testCrearEquipo() {
		List<Equipo> equiposAntes = conexion.listaEquipos();
		Equipo ultimoAntes = equiposAntes.get(equiposAntes.size()-1);
		conexion.crearEquipo(equipoCreado);
		List<Equipo> equiposDespues = conexion.listaEquipos();
		Equipo ultimoDespues = equiposDespues.get(equiposDespues.size()-1);
		assertAll("Crear Equipo",
				() -> assertNotEquals(ultimoAntes, ultimoDespues),
				() -> assertEquals(equipoCreado, ultimoDespues));
	}
	
	/*
	 * Comprobamos que el equipo borrado no sigue en la lista
	 * y que el último equipo es el mismo que antes de la operación
	 */
	
	@Test
	void testEliminarEquipo() {
		List<Equipo> equiposAntes = conexion.listaEquipos();
		Equipo ultimoAntes = equiposAntes.get(equiposAntes.size()-1);
		Equipo equipoBorrado = new Equipo(conexion.generarID(), "JUnitTestBorrar");
		conexion.eliminarEquipo(equipoBorrado);
		List<Equipo> equiposDespues = conexion.listaEquipos();
		Equipo ultimoDespues = equiposDespues.get(equiposDespues.size()-1);
		assertAll("Eliminar Equipo",
				() -> assertNotEquals(ultimoDespues, equipoBorrado),
				() -> assertEquals(ultimoAntes, ultimoDespues));
	}
	
	
	/*
	 * Cuando insertamos una jugador se posiciona al final
	 * de la tabla. Comprobamos que, en la lista, el
	 * último jugador es diferente al anterior antes de añadirse este
	 */
	
	@Test
	void testCrearJugador() {
		List<Jugador> jugadoresAntes = conexion.listaJugadores();
		Jugador ultimoAntes = jugadoresAntes.get(jugadoresAntes.size()-1);
		conexion.crearJugador(jugadorCreado);
		List<Jugador> jugadoresDespues = conexion.listaJugadores();
		Jugador ultimoDespues = jugadoresDespues.get(jugadoresDespues.size()-1);
		assertAll("Crear Jugador",
				() -> assertNotEquals(ultimoAntes, ultimoDespues),
				() -> assertEquals(jugadorCreado, ultimoDespues));
	}
	
	
	/*
	 * Comprobamos que el jugador borrado no sigue en la lista
	 * y que el último jugador es el mismo que antes de la operación
	 */
	
	@Test
	void testEliminarJugador() {
		List<Jugador> jugadoresAntes = conexion.listaJugadores();
		Jugador ultimoAntes = jugadoresAntes.get(jugadoresAntes.size()-1);
		Jugador jugadorBorrado = new Jugador(conexion.generarID(), "JUnitTestBorrar", 0);
		conexion.eliminarJugador(jugadorBorrado);
		List<Jugador> jugadoresDespues = conexion.listaJugadores();
		Jugador ultimoDespues = jugadoresDespues.get(jugadoresDespues.size()-1);
		assertAll("Eliminar Jugador",
				() -> assertNotEquals(ultimoDespues, jugadorBorrado),
				() -> assertEquals(ultimoAntes, ultimoDespues));
	}
	
	
	/*
	 * Cuando insertamos una usuario se posiciona al final
	 * de la tabla. Comprobamos que, en la lista, el
	 * último usuario es diferente al anterior antes de añadirse este
	 */
	
	@Test
	void testCrearUsuario() {
		List<Usuario> usuariosAntes = conexion.listaUsuarios();
		Usuario ultimoAntes = usuariosAntes.get(usuariosAntes.size()-1);
		conexion.crearUsuario(usuarioCreado);
		List<Usuario> usuariosDespues = conexion.listaUsuarios();
		Usuario ultimoDespues = usuariosDespues.get(usuariosDespues.size()-1);
		assertAll("Crear Usuario",
				() -> assertNotEquals(ultimoAntes, ultimoDespues),
				() -> assertEquals(usuarioCreado, ultimoDespues));
	}
	
	/*
	 * Comprobamos que el usuario borrado no sigue en la lista
	 * y que el último usuario es el mismo que antes de la operación
	 */
	
	@Test
	void testEliminarUsuario() {
		List<Usuario> usuariosAntes = conexion.listaUsuarios();
		Usuario ultimoAntes = usuariosAntes.get(usuariosAntes.size()-1);
		Usuario usuarioBorrado = new Usuario("zzzCTest", "", "");
		conexion.eliminarUsuario(usuarioBorrado);
		List<Usuario> usuariosDespues = conexion.listaUsuarios();
		Usuario ultimoDespues = usuariosDespues.get(usuariosDespues.size()-1);
		assertAll("Eliminar Usuario",
				() -> assertNotEquals(ultimoDespues, usuarioBorrado),
				() -> assertEquals(ultimoAntes, ultimoDespues));
	}
	
	
	
	/*
	 * Eliminamos objetos creados que no se habían eliminado
	 * durante los tests
	 */
	
	@AfterAll
	static void tearDownAll() {
		
		conexion.eliminarLiga(liga);
		conexion.eliminarLiga(ligaCreada);
		conexion.eliminarEquipo(equipo);
		conexion.eliminarEquipo(equipoCreado);
		conexion.eliminarJugador(jugador);
		conexion.eliminarJugador(jugadorCreado);
		conexion.eliminarUsuario(usuario);
		conexion.eliminarUsuario(usuarioCreado);
		
	}

	
}
