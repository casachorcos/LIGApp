package prLIGApp;

import java.util.List;

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
	public abstract List<Jornada> listaJornadas();
	
	public abstract void crearUsuario(Usuario u);
	public abstract void crearJugador(Jugador j);
	public abstract void crearEquipo(Equipo eq);
	public abstract void crearLiga(Liga a);
	public abstract void crearJornada(Jornada jor);
	
	public abstract void eliminarUsuario(Usuario u);
	public abstract void eliminarJugador(Jugador j);
	public abstract void eliminarEquipo(Equipo eq);
	public abstract void eliminarLiga(Liga a);
	public abstract void eliminarJornada(Jornada jor);
	
	public abstract int generarID();
	
	public abstract List<Jugador> usuario_jugador(Usuario u);
	public abstract List<Equipo> usuario_equipo(Usuario u);
	public abstract List<Liga> usuario_liga(Usuario u);
	public abstract List<Jugador> plantilla(Equipo e);
	public abstract List<Equipo> equipo_liga(Liga l);
	
}
