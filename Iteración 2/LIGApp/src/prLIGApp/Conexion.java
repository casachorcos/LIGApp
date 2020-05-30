package prLIGApp;


import java.util.List;
import java.util.PriorityQueue;

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
	
	public abstract List<Clasificacion> clasif(int idLiga);
	
	public abstract void eliminarLiga_Us(Liga a, String usuario);
	public abstract void eliminarEquipo_Us(Equipo eq,String usuario);
	public abstract void eliminarJugador_Us(Jugador j,String usuario);
	
	
	public abstract void crearJornada(Jornada jor);
	public abstract void eliminarJornada(Jornada jor);
	public abstract int generarCodJornada();
	public abstract int generarCodPartido();
	
}
