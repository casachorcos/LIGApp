package LEJ;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBaseDatosJDBC extends ConexionConBasedeDatos {

	private Connection conn;

	private static ConexionBaseDatosJDBC instanciaInterfaz = null;
	
	
	
	
	

	private ConexionBaseDatosJDBC() {
		try {
			// create connection for database called DBB_SCHEMA in a server installed in
			// DB_URL, with a user USER with password PASS
			conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA, USER, PASS);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ConexionBaseDatosJDBC getInstance() {
		if (instanciaInterfaz == null) {
			instanciaInterfaz = new ConexionBaseDatosJDBC();
		}
		return instanciaInterfaz;
	}

	public List<Equipo> listaEquipos() {
		ArrayList<Equipo> lEquipos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM EQUIPO";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					Equipo eq = new Equipo(id, name);
					lEquipos.add(eq);
					System.out.println(eq.getId() + " " + eq.getNombre());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEquipos;
	}

	public List<Liga> listaLigas() {
		ArrayList<Liga> lLigas = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM LIGA";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					Liga li = new Liga(id, name);
					lLigas.add(li);
					System.out.println(li.getId() + " " + li.getNombre());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lLigas;
	}	
	
	public List<Jugador> listaJugadores() {
		ArrayList<Jugador> lJugadores = new ArrayList<>();
		String selectQueryBody = "SELECT nombre, id, edad FROM Jugador";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String name = rs.getString(1);
					int id = rs.getInt(3);
					int edad = rs.getInt(3);
					lJugadores.add(new Jugador(id, name, edad));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lJugadores;
	}

	public void crearJugador(Jugador j) {
		String insertBody = "INSERT INTO JUGADOR (id, nombre, edad) VALUES (?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, j.getId());
			preparedStatement.setString(2, j.getNombre());
			preparedStatement.setInt(3, j.getEdad());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarJugador(Jugador j) {
		String deleteBody = "DELETE FROM JUGADOR WHERE (identificador = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setInt(1, j.getId());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public void crearEquipo(Equipo eq) {
		String insertBody = "INSERT INTO EQUIPO (id, nombre) VALUES (?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, eq.getId());
			preparedStatement.setString(2, eq.getNombre());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarEquipo(Equipo eq) {
		String deleteBody = "DELETE FROM EQUIPO WHERE (identificador = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setInt(1, eq.getId());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public void crearLiga(Liga l) {
		String insertBody = "INSERT INTO LIGA (id, nombre) VALUES (?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, l.getId());
			preparedStatement.setString(2, l.getNombre());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarLiga(Liga l) {
		String deleteBody = "DELETE FROM LIGA WHERE (identificador = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setInt(1, l.getId());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
