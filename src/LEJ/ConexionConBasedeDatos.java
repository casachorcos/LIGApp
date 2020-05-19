package LEJ;

import java.util.List;

public abstract class ConexionConBasedeDatos {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
	static final String DB_SCHEMA = "ligappdb";

	//  Database credentials
	static final String USER = "isco";
	static final String PASS = "messironaldo";

	public abstract List<Equipo> listaEquipos();
	public abstract List<Liga> listaLigas();
	public abstract List<Jugador> listaJugadores();

	public abstract void crearJugador(Jugador j);
	public abstract void eliminarJugador(Jugador j);
	
	public abstract void crearEquipo(Equipo e);
	public abstract void eliminarEquipo(Equipo e);
	
	public abstract void crearLiga(Liga l);
	public abstract void eliminarLiga(Liga l);
	
	

}
