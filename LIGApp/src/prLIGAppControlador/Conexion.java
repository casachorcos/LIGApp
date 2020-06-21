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
	
	// MÉTODOS CREAR BÁSICOS
	
	public abstract void crearUsuario(Usuario u);
	public abstract void crearJugador(Jugador j);
	public abstract void crearEquipo(Equipo eq);
	public abstract void crearLiga(Liga a);
	public abstract void crearJornada(Jornada jor);
	public abstract void crearPartido(Partido p);
	
	// MÉTODOS CREAR AVANZADOS
	
	public abstract void crearJugador_Usuario(Jugador j, String usuario);
	public abstract void crearJugador_Usuario2(Jugador j, String usuario);
    public abstract void crearEquipo_Usuario(Equipo e, String usuario);
    public abstract void crearEquipo_Usuario2(Equipo e, String usuario);
    public abstract void crearLiga_Usuario(Liga i, String usuario);
    public abstract void crearLiga_Usuario2(Liga i, String usuario);
    public abstract void crearJugadorEnEquipo(Jugador j, Equipo eq);
	public abstract void crearEquipoEnLiga(Equipo eq, Liga a);
	
	// MÉTODOS ELIMINAR BÁSICOS
	
	public abstract void eliminarUsuario(Usuario u);
	public abstract void eliminarJugador(Jugador j);
	public abstract void eliminarEquipo(Equipo eq);
	public abstract void eliminarLiga(Liga a);
	public abstract void eliminarJornada(Jornada jor);
	public abstract void eliminarPartido(Partido p);
	
	// MÉTODOS ELIMINAR AVANZADOS

	public abstract void eliminarJugador_Us(Jugador j,String usuario);
	public abstract void eliminarJugador_Us2(Jugador j,String usuario);
	public abstract void eliminarEquipo_Us(Equipo eq,String usuario);
	public abstract void eliminarEquipo_Us2(Equipo eq,String usuario);
	public abstract void eliminarLiga_Us(Liga a, String usuario);
	public abstract void eliminarLiga_Us2(Liga a, String usuario);
	public abstract void eliminarJugadorEnEquipo(Jugador j);
	public abstract void eliminarJugadorEnEquipo(Jugador j, Equipo e);
	public abstract void eliminarJugadorEnEquipo(Equipo j);
	public abstract void eliminarEquipoEnLiga(Equipo eq);
	public abstract void eliminarEquipoEnLiga(Equipo eq, Liga lig);
	public abstract void eliminarEquipoEnLiga(Liga eq);
	public abstract void eliminarClasiDePartido(Partido p);
	public abstract void eliminarPartidosDeEquipo(Equipo e);
	
	// MÉTODOS LISTA BÁSICOS
	
	public abstract List<Usuario> listaUsuarios();
	public abstract List<Jugador> listaJugadores();
	public abstract List<Equipo> listaEquipos();
	public abstract List<Liga> listaLigas();
	public abstract List<Jornada> listaJornadas();
	public abstract List<Jornada> listaJornadas(int idLiga);
	public abstract List<Partido> listaPartidos(int codJornada);
	
	// OTROS METODOS LISTA

	public abstract List<Jugador> usuario_jugador(String idUsuario);
	public abstract List<Equipo> usuario_equipo(String idUsuario);
	public abstract List<Liga> usuario_liga(String idUsuario);
	public abstract List<Object[]> clasif(int idLiga);
	public abstract List<Jugador> plantilla(int idEquipo);
	public abstract List<Equipo> equiposLiga(int codLiga);
	
	// MÉTODOS ACTUALIZAR Y GENERAR
	
	public abstract void actualizarID(int id);
	public abstract int generarID();
	public abstract void actualizarCodJornada(int cod);
	public abstract int generarCodJornada();
	public abstract void actualizarCodPartido(int cod);
	public abstract int generarCodPartido();
	public abstract void actualizarJugador(Jugador j);
	public abstract void actualizarFechaJornada(Jornada seleccionado, int dia, int mes, int anio, int dur);
	public abstract void actualizarPartido(Partido p);
	public abstract void actualizarclasi(int codLiga, int codEquipo, int punt, int goMar, int goCont);
	
	// OTROS MÉTODOS DE LA APLICACIÓN

	public abstract int contarJugador_Us(Jugador jug);
	public abstract int contarEquipo_Us(Equipo eq);
	public abstract int contarLiga_Us(Liga lig);
	public abstract void cambiarContrasena(String idUsuario, String cont);
	public abstract void ajustarNumerosJornada(int numJor);
	public abstract void parEnfrentado(Equipo l, Equipo v, Jornada jor);
	public abstract void emparejamientos(Jornada jor);
	public abstract Clasificacion equipoclasificacion(int codLiga, int codEquipo);
	public abstract void setCapi(Equipo equipo, Jugador jug);
	public abstract boolean capitan(Equipo equipo, Jugador jug);
}
