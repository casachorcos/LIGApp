package prTestJUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.*;

class PartidoJugadoTest {
	
	private static final Conexion conexion = ConexionJDBC.getInstance();
	
	private static Liga liga = new Liga(conexion.generarID(), "JUnitTestPartido");
	
	private static Jornada jornada;
	
	private static Equipo equipoLocal = new Equipo(conexion.generarID(), "JUnitLocal");
	private static Equipo equipoVisitante = new Equipo(conexion.generarID(), "JUnitVisitante");
	
	private static Partido partido;
	

	@BeforeAll
	static void initAll() {
		
		conexion.crearLiga(liga);
		
		conexion.crearEquipo(equipoLocal);
		conexion.crearEquipoEnLiga(equipoLocal, liga);
		conexion.crearEquipo(equipoVisitante);
		conexion.crearEquipoEnLiga(equipoVisitante, liga);
		
		jornada = new Jornada(conexion.generarCodJornada(), liga.getId());
		conexion.crearJornada(jornada);
		
		partido = new Partido(conexion.generarCodPartido(), equipoLocal.getId(), equipoVisitante.getId(), jornada.getCodigoJornada());
		conexion.crearPartido(partido);
	}
	
	
	@Test
	void testPartidoJugado() {
		

		// Introducimos un resultado aleatorio al partido
		
		Random r = new Random();
		int golesLocal = r.nextInt(10);
		int golesVisitante = r.nextInt(10);
		partido.setGolesLocal(golesLocal);
		partido.setGolesVisitante(golesVisitante);
		int puntosLocal;
		int puntosVisitante;
		if (golesLocal > golesVisitante) {
			puntosLocal = 3;
			puntosVisitante = 0;
		} else if (golesLocal < golesVisitante) {
			puntosLocal = 0;
			puntosVisitante = 3;
		} else {
			puntosLocal = 1;
			puntosVisitante = 1;
		}
		
		// Actualizamos la clasificacion con el resultado del partido
		
		conexion.actualizarclasi(liga.getId(), equipoLocal.getId(), puntosLocal, golesLocal, golesVisitante);
		conexion.actualizarclasi(liga.getId(), equipoVisitante.getId(), puntosVisitante, golesVisitante, golesLocal);
		partido.setJugado(true);
		
		// Accedemos a la tabla clasificacion para comprobar que se ha introducido la informacion correctamente
		
		int puntosLocalTrasIntroducirResultados = conexion.equipoclasificacion(liga.getId(), equipoLocal.getId()).getPuntos();
		int puntosVisitanteTrasIntroducirResultados = conexion.equipoclasificacion(liga.getId(), equipoVisitante.getId()).getPuntos();
		
		// Eliminamos el partido y volvemos a guardar los puntos de cada uno para comprobar que se actualiza
		
		conexion.eliminarClasiDePartido(partido);
		
		int puntosLocalTrasEliminarPartido = conexion.equipoclasificacion(liga.getId(), equipoLocal.getId()).getPuntos();
		int puntosVisitanteTrasEliminarPartido = conexion.equipoclasificacion(liga.getId(), equipoVisitante.getId()).getPuntos();
		
		assertAll("Actualizar clasificacion",
				() -> assertEquals(puntosLocal, puntosLocalTrasIntroducirResultados),
				() -> assertEquals(puntosVisitante, puntosVisitanteTrasIntroducirResultados),
				() -> assertEquals(0, puntosLocalTrasEliminarPartido),
				() -> assertEquals(0, puntosVisitanteTrasEliminarPartido));
		
	}
	
	
	@AfterAll
	static void tearDownAll() {
		
		conexion.eliminarPartido(partido);
		
		conexion.eliminarJornada(jornada);
		
		conexion.eliminarEquipo(equipoLocal);
		conexion.eliminarEquipoEnLiga(equipoLocal);
		conexion.eliminarEquipo(equipoVisitante);
		conexion.eliminarEquipoEnLiga(equipoVisitante);
		
		conexion.eliminarLiga(liga);
	}
	

}
