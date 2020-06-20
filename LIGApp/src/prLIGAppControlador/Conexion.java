package prLIGAppControlador;


import java.util.List;
import java.util.PriorityQueue;

import prLIGAppModelo.Clasificacion;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Jornada;
import prLIGAppModelo.Jugador;
import prLIGAppModelo.Liga;
import prLIGAppModelo.Partido;
import prLIGAppModelo.Usuario;

public abstract class Conexion {
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
	static final String schema = "ligappdb";
	static final String user = "isco";
	static final String pass = "messironaldo";
	
	public abstract List<Usuario> listaUsuarios();
	public abstract List<Jugador> listaJugadores();
	public abstract List<Equipo> listaEquipos();
	public abstract List<Liga> listaLigas();
	
	public abstract void crearUsuario(Usuario u);
	
	public abstract void crearJugador(Jugador j);
	public abstract void crearEquipo(Equipo eq);
	public abstract void crearLiga(Liga a);
	
	public abstract void eliminarUsuario(Usuario u);
	public abstract void eliminarJugador(Jugador j);
	public abstract void eliminarEquipo(Equipo eq);
	public abstract void eliminarLiga(Liga a);
	
	public abstract int generarID();
	
	public abstract List<Jugador> usuario_jugador(String idUsuario);
	public abstract List<Liga> usuario_liga(String idUsuario);
	public abstract List<Equipo> usuario_equipo(String idUsuario);

	public abstract void crearJugador_Usuario(Jugador j, String usuario);
    public abstract void crearEquipo_Usuario(Equipo e, String usuario);
    public abstract void crearLiga_Usuario(Liga i, String usuario);
	
	public abstract List<Object[]> clasif(int idLiga);
	public abstract List<Jugador> plantilla(int idEquipo);
	
	public abstract void eliminarLiga_Us(Liga a, String usuario);
	public abstract void eliminarEquipo_Us(Equipo eq,String usuario);
	public abstract void eliminarJugador_Us(Jugador j,String usuario);
	
	
	public abstract void crearJornada(Jornada jor);
	public abstract void eliminarJornada(Jornada jor);
	public abstract int generarCodJornada();
	public abstract int generarCodPartido();
	public abstract void ajustarNumerosJornada(int numJor);
	public abstract void eliminarPartidosDeEquipo(Equipo e);
	public abstract void actualizarPartido(Partido p);
	
	
	public abstract void cambiarContrasena(String idUsuario, String cont);
	
	public abstract void parEnfrentado(Equipo l, Equipo v, Jornada jor);
	public abstract void emparejamientos(Jornada jor);
	public abstract List<Partido> listaPartidos(int codJornada);
	public abstract void eliminarPartido(Partido p);
	public abstract void crearPartido(Partido p);
	public abstract void eliminarClasiDePartido(Partido p);
	public abstract void eliminarPartidosDeEquipo(Equipo e);
	
	public abstract void crearJugadorEnEquipo(Jugador j, Equipo eq);
	public abstract void crearEquipoEnLiga(Equipo eq, Liga a);
	public abstract void eliminarJugadorEnEquipo(Jugador j);
	public abstract void eliminarJugadorEnEquipo(Jugador j, Equipo e);
	public abstract void eliminarJugadorEnEquipo(Equipo j);
	public abstract void eliminarEquipoEnLiga(Equipo eq);
	public abstract void eliminarEquipoEnLiga(Equipo eq, Liga lig);
	public abstract void eliminarEquipoEnLiga(Liga eq);
	public abstract List<Jornada> listaJornadas(int idLiga);
	public abstract List<Jornada> listaJornadas();
	
	public abstract void actualizarCodJornada(int cod);
	public abstract void actualizarCodPartido(int cod);
	
	public abstract void actualizarclasi(int codLiga, int codEquipo, int punt, int goMar, int goCont);
	public abstract Clasificacion equipoclasificacion(int codLiga, int codEquipo);

	public abstract List<Equipo> equiposLiga(int codLiga);
}
