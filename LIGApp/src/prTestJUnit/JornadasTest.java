package prTestJUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import prLIGAppControlador.*;
import prLIGAppModelo.*;

class JornadasTest {
	
	private static final Conexion conexion = ConexionJDBC.getInstance();
	
	private static Jornada jornada;
	
	private static List<Partido> partidos;
	
	private static Liga liga = new Liga(conexion.generarID(), "JUnitTestJornada");
	
	private final static int N = 7;
	private static boolean[] emparejados = new boolean[N];
	private static int numeroEquiposEmparejados = 0;
	
	private static List<Integer> listaEquipos = new ArrayList<>();
	
	

	@BeforeAll
	static void initAll() {
		
		conexion.crearLiga(liga);
		
		for (int i = 0; i < N; i++) {
			Equipo e = new Equipo(conexion.generarID(), "JUnit" + i);
			listaEquipos.add(e.getId());
			conexion.crearEquipo(e);
			conexion.crearEquipoEnLiga(e, liga);
		}
		
		jornada = new Jornada(conexion.generarCodJornada(), liga.getId());
		conexion.crearJornada(jornada);
		
		
		
	}
	
	
	@Test
	void testEmparejamiento() {
		conexion.emparejamientos(jornada);
		partidos = conexion.listaPartidos(jornada.getCodigoJornada());
		
		for (Partido p : partidos) {
			int indexLocal = listaEquipos.indexOf(p.getIdLocal());
			if (!emparejados[indexLocal]) {
				emparejados[indexLocal] = true;
				numeroEquiposEmparejados++;
			}
			
			int indexVisitante = listaEquipos.indexOf(p.getIdVisitante());
			if (!emparejados[indexVisitante]) {
				emparejados[indexVisitante] = true;
				numeroEquiposEmparejados++;
			}
		}
		
		assertAll("Emparejar",
				() -> assertEquals(N - 1, numeroEquiposEmparejados));
	}
	
	
	@AfterAll
	static void tearDownAll() {
		conexion.eliminarEquipoEnLiga(liga);
		for (int i = 0; i < N; i++) {
			conexion.eliminarEquipo(new Equipo(listaEquipos.get(i), null));
		}
		conexion.eliminarLiga(liga);
		conexion.eliminarJornada(jornada);
		
		for (Partido p : partidos) {
			conexion.eliminarPartido(p);
		}
	}
	

}
