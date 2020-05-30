package prLIGApp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.mysql.jdbc.PreparedStatement;

public class ConexionJDBC extends Conexion {
	private Connection con;
	private static ConexionJDBC instanciaInterfaz = null;
	
	private ConexionJDBC() {
		try {
			con = DriverManager.getConnection(url + "/" + schema, user, pass);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConexionJDBC getInstance() {
		if (instanciaInterfaz == null) {
			instanciaInterfaz = new ConexionJDBC();
		}
		
		return instanciaInterfaz;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public List<Usuario> listaUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String query = "SELECT * FROM Usuario";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String nombre = rs.getString(1);
					String correo = rs.getString(2);
					String password = rs.getString(3);
					
					usuarios.add(new Usuario(nombre, correo, password));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public List<Jugador> listaJugadores() {
		ArrayList<Jugador> jugadores = new ArrayList<>();
		String query = "SELECT * FROM Jugador";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String nombre = rs.getString(1);
					int id = rs.getInt(3);
					int edad = rs.getInt(4);
					
					jugadores.add(new Jugador(id, nombre, edad));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jugadores;
	}
	

	public List<Equipo> listaEquipos() {
		ArrayList<Equipo> equipos = new ArrayList<>();
		String query = "SELECT * FROM Equipo";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String nombre = rs.getString(2);	
					equipos.add(new Equipo(id, nombre));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return equipos;
	}	
	
	public List<Liga> listaLigas() {
		ArrayList<Liga> ligas = new ArrayList<>();
		String query = "SELECT * FROM Liga";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String nombre = rs.getString(2);	
					ligas.add(new Liga(id, nombre));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ligas;
	}		
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void crearUsuario(Usuario u) {
		String query = "INSERT INTO Usuario (nombre, correo, password) VALUES (?, ?, ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pS.setString(1, u.getNombre());
			pS.setString(2, u.getCorreo());
			pS.setString(3, u.getPassword());
			int res = pS.executeUpdate();
			ResultSet rs = pS.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void crearJugador(Jugador j) {
		String query = "INSERT INTO Jugador (nombre, id, edad) VALUES (?, ?, ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pS.setString(1, j.getNombre());
			pS.setInt(2, j.getId());
			pS.setInt(3, j.getEdad());
			int res = pS.executeUpdate();
			ResultSet rs = pS.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearEquipo(Equipo eq) {
		String query = "INSERT INTO Equipo (id, nombre) VALUES (?, ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pS.setInt(1, eq.getId());
			pS.setString(2, eq.getNombre());
			int res = pS.executeUpdate();
			ResultSet rs = pS.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearLiga(Liga a) {
		String query = "INSERT INTO Liga (id, nombre) VALUES (?, ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pS.setInt(1, a.getId());
			pS.setString(2, a.getNombre());
			int res = pS.executeUpdate();
			ResultSet rs = pS.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public void eliminarUsuario(Usuario u) {
		String deleteBody = "DELETE FROM Usuario WHERE (nombre = ? && correo = ? && password = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
			pS.setString(1, u.getNombre());
			pS.setString(2, u.getCorreo());
			pS.setString(3, u.getPassword());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarJugador(Jugador j) {
		String deleteBody = "DELETE FROM Jugador WHERE (id = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
			pS.setInt(1, j.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarEquipo(Equipo eq) {
		String deleteBody = "DELETE FROM Equipo WHERE (id = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
			pS.setInt(1, eq.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public void eliminarLiga(Liga a) {
		String deleteBody = "DELETE FROM Liga WHERE (id = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
			pS.setInt(1, a.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private void actualizarID(int id) {
		String updateBody = "UPDATE IncrementaId SET iteradorId = ?";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBody);
			pS.setInt(1, id);
			int res = pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int generarID() {
		int id = 0;
		String query = "SELECT * FROM IncrementaId";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					id = rs.getInt(1);	
				}
			}
			actualizarID(id+1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public List<Equipo> usuario_equipo(String idUsuario) {
        ArrayList<Equipo> equipos = new ArrayList<>();
        String query = "SELECT * FROM Equipo WHERE id IN (SELECT idEquipoUs_Eq FROM Us_Eq where idUsuarioUs_Eq = ?)" ;
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
            pS.setString(1, idUsuario);
            ResultSet rs = pS.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String nombre = rs.getString(2);

                    equipos.add(new Equipo(id, nombre));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipos;
    }
	
	public List<Jugador> usuario_jugador(String idUsuario) {
		ArrayList<Jugador> jugadores = new ArrayList<>();
		String query = "SELECT * FROM Jugador WHERE id IN (SELECT idJugadorUs_Jug FROM Us_Jug where idUsuarioUs_Jug = ?)" ;
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
			pS.setString(1, idUsuario);
			ResultSet rs = pS.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String nombre = rs.getString(1);
					int id = rs.getInt(3);
					int edad = rs.getInt(4);
					
					jugadores.add(new Jugador(id, nombre, edad));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public List<Liga> usuario_liga(String idUsuario) {
        ArrayList<Liga> ligas = new ArrayList<>();
        String query = "SELECT * FROM Liga WHERE id IN (SELECT idLigaUs_Lig FROM Us_Lig where idUsuarioUs_Lig = ?)" ;
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
            pS.setString(1, idUsuario);
            ResultSet rs = pS.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String nombre = rs.getString(2);

                    ligas.add(new Liga(id, nombre));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ligas;
    }
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void crearJugador_Usuario(Jugador j, String usuario) {
        crearJugador(j);

        String privado = "INSERT INTO Us_Jug (idUsuarioUs_Jug, idJugadorUs_Jug) VALUES (?, ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado, PreparedStatement.RETURN_GENERATED_KEYS);
            pS.setString(1, usuario);
            pS.setInt(2, j.getId());

            int res = pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void crearEquipo_Usuario(Equipo e, String usuario) {
        crearEquipo(e);

        String privado = "INSERT INTO Us_Eq (idUsuarioUs_Eq, idEquipoUs_Eq) VALUES (?, ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado, PreparedStatement.RETURN_GENERATED_KEYS);
            pS.setString(1, usuario);
            pS.setInt(2, e.getId());
            int res = pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
	
	public void crearLiga_Usuario(Liga l, String usuario) {
        crearLiga(l);

        String privado = "INSERT INTO Us_Lig (idUsuarioUs_Lig, idLigaUs_Lig) VALUES (?, ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado, PreparedStatement.RETURN_GENERATED_KEYS);
            pS.setString(1, usuario);
            pS.setInt(2, l.getId());
            int res = pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public List<Clasificacion> clasif(int idLiga) {
		ArrayList<Clasificacion> equipos = new ArrayList<>();

		String query = "SELECT e.nombre, c.puntos, c.golesMarcados, c.golesEnContra, c.partidosJugados FROM Clasificacion c, Equipo e WHERE c.idEquipoClasificacion = e.id AND c.idLigaClasificacion = ? ORDER BY puntos DESC, (golesMarcados - golesEnContra) DESC";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
			pS.setInt(1, idLiga);
			ResultSet rs = pS.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String nombreequipo = rs.getString(1);
					int puntos = rs.getInt(2);
					int golesmarcados = rs.getInt(3);	
					int golesencontra = rs.getInt(4);
					int partidosjugados = rs.getInt(5);
					
					equipos.add(new Clasificacion(nombreequipo,puntos,golesmarcados,golesencontra,partidosjugados ));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return equipos;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void eliminarLiga_Us(Liga a, String usuario) {
		eliminarLiga(a);
		
		String privado = "DELETE FROM Us_Lig WHERE (idUsuarioUs_Lig = ? && idLigaUs_Lig = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, a.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarEquipo_Us(Equipo eq,String usuario) {
		eliminarEquipo(eq);
		
		String privado = "DELETE FROM Us_Eq WHERE (idUsuarioUs_Eq = ? && idEquipoUs_Eq = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, eq.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarJugador_Us(Jugador j,String usuario) {
		eliminarJugador(j);
		
		String privado = "DELETE FROM Us_Jug WHERE (idUsuarioUs_Jug = ? && idJugadorUs_Jug = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, j.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void crearJornada(Jornada jor) {
		String query = "INSERT INTO Jornada (codjornada, numjornada, liga,"
				+ " iniciojornada, finjornada) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pS.setInt(1, jor.getCodigoJornada());
			pS.setInt(2, jor.getNumeroJornada());
			pS.setString(3, jor.getNombreLiga());
			pS.setDate(4, jor.getFechaInicio());
			pS.setDate(5, jor.getFechaFin());
			int res = pS.executeUpdate();
			ResultSet rs = pS.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void eliminarJornada(Jornada jor) {
		String deleteBody = "DELETE FROM Jornada WHERE (codjornada = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
			pS.setInt(1, jor.getCodigoJornada());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarCodJornada(int cod) {
		String updateBody = "UPDATE IncrementaCodJornada SET iteradorJornada = ?";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBody);
			pS.setInt(1, cod);
			int res = pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarCodPartido(int cod) {
		String updateBody = "UPDATE IncrementaCodPartido SET iteradorPartido = ?";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBody);
			pS.setInt(1, cod);
			int res = pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int generarCodJornada() {
		int cod = 0;
		String query = "SELECT * FROM IncrementaCodJornada";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					cod = rs.getInt(1);	
				}
			}
			actualizarCodJornada(cod+1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cod;
	}
	
	public int generarCodPartido() {
		int cod = 0;
		String query = "SELECT * FROM IncrementaCodPartido";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					cod = rs.getInt(1);	
				}
			}
			actualizarCodPartido(cod+1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cod;
	}
}
