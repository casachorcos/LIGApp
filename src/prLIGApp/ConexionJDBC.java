package prLIGApp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Jornada> listaJornadas() {
		ArrayList<Jornada> jornadas = new ArrayList<>();
		String query = "SELECT * FROM Jornada";
		Statement querySt;
		try {
			querySt = con.createStatement();
			ResultSet rs = querySt.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int cod = rs.getInt(1);
					int numJor = rs.getInt(2);
					String nomLiga = rs.getString(3);
					Date fIni = rs.getDate(4);
					Date fFin = rs.getDate(5);
					jornadas.add(new Jornada(cod, numJor, nomLiga, fIni, fFin));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jornadas;
	}	
	
	public List<Partido> listaPartidos() {
		ArrayList<Partido> partidos = new ArrayList<>();
		return partidos;
	}	
	
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
	
	public void crearPartido(Partido p) {
	}

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
	
	@Override
	public void eliminarPartido(Partido p) {
	}
	
	
	
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
	
	private List<Integer> jugadores(String n) {
        ArrayList<Integer> jugadores = new ArrayList<>();
        String query = "SELECT idJugadorR4 FROM R4 WHERE idUsuarioR4 = " + n;
        Statement querySt;
        try {
            querySt = con.createStatement();
            ResultSet rs =  querySt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int id = rs.getInt(1);

                    jugadores.add(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jugadores;
    }
	
	public List<Jugador> usuario_jugador(Usuario u) {
		List<Integer> jugadores = jugadores(u.getNombre());
		ArrayList<Jugador> res = new ArrayList<>();
		
		for (int i = 0; i < jugadores.size(); i++) {
			String query = "SELECT * FROM Jugador WHERE id = " + jugadores.get(i);
			Statement querySt;
			try {
				querySt = con.createStatement();
				ResultSet rs = querySt.executeQuery(query);
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						int id = rs.getInt(3);
						String n = rs.getString(1);
						int e = rs.getInt(4);
						
						res.add(new Jugador(id, n, e));
					}
				}
			} catch (SQLException e) {
	            e.printStackTrace();
			}
		}
			
		return res;
	}
	
	private List<Integer> equipos(String n) {
        ArrayList<Integer> equipos = new ArrayList<>();
        String query = "SELECT idEquipoR3 FROM R3 WHERE idUsuarioR3 = " + n;
        Statement querySt;
        try {
            querySt = con.createStatement();
            ResultSet rs =  querySt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int id = rs.getInt(1);

                    equipos.add(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipos;
    }
	
	public List<Equipo> usuario_equipo(Usuario u) {
		List<Integer> equipos = equipos(u.getNombre());
		ArrayList<Equipo> res = new ArrayList<>();
		
		for (int i = 0; i < equipos.size(); i++) {
			String query = "SELECT * FROM Equipo WHERE id = " + equipos.get(i);
			Statement querySt;
			try {
				querySt = con.createStatement();
				ResultSet rs = querySt.executeQuery(query);
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						int id = rs.getInt(1);
						String n = rs.getString(2);
						
						res.add(new Equipo(id, n));
					}
				}
			} catch (SQLException e) {
	            e.printStackTrace();
			}
		}
			
		return res;
	}
	
	private List<Integer> ligas(String n) {
        ArrayList<Integer> ligas = new ArrayList<>();
        String query = "SELECT idLigaR2 FROM R2 WHERE idUsuarioR2 = " + n;
        Statement querySt;
        try {
            querySt = con.createStatement();
            ResultSet rs =  querySt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int id = rs.getInt(1);

                    ligas.add(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ligas;
    }
	
	public List<Liga> usuario_liga(Usuario u) {
		List<Integer> ligas = ligas(u.getNombre());
		ArrayList<Liga> res = new ArrayList<>();
		
		for (int i = 0; i < ligas.size(); i++) {
			String query = "SELECT * FROM Liga WHERE id = " + ligas.get(i);
			Statement querySt;
			try {
				querySt = con.createStatement();
				ResultSet rs = querySt.executeQuery(query);
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						int id = rs.getInt(1);
						String n = rs.getString(2);
						
						res.add(new Liga(id, n));
					}
				}
			} catch (SQLException e) {
	            e.printStackTrace();
			}
		}
			
		return res;
	}
	
	private List<Integer> plantillita(int id) {
        ArrayList<Integer> plantillita = new ArrayList<>();
        String query = "SELECT idJugador FROM R2 WHERE idEquipo = " + id;
        Statement querySt;
        try {
            querySt = con.createStatement();
            ResultSet rs =  querySt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int d = rs.getInt(1);

                    plantillita.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantillita;
    }
	
	public List<Jugador> plantilla(Equipo e) {
		List<Integer> plantillita = plantillita(e.getId());
		ArrayList<Jugador> res = new ArrayList<>();
		
		for (int i = 0; i < plantillita.size(); i++) {
			String query = "SELECT * FROM Jugador WHERE id = " + plantillita.get(i);
			Statement querySt;
			try {
				querySt = con.createStatement();
				ResultSet rs = querySt.executeQuery(query);
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						int id = rs.getInt(3);
						String n = rs.getString(1);
						int edad = rs.getInt(4);
						
						res.add(new Jugador(id, n, edad));
					}
				}
			} catch (SQLException q) {
	            q.printStackTrace();
			}
		}
			
		return res;
	}
	
	private List<Integer> equipitos(int id) {
        ArrayList<Integer> equipitos = new ArrayList<>();
        String query = "SELECT idEquipoR1 FROM R1 WHERE idLigaR1 = " + id;
        Statement querySt;
        try {
            querySt = con.createStatement();
            ResultSet rs =  querySt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int d = rs.getInt(1);

                    equipitos.add(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipitos;
    }
	
	public List<Equipo> equipo_liga(Liga l) {
		List<Integer> equipitos = equipitos(l.getId());
		ArrayList<Equipo> res = new ArrayList<>();
		
		for (int i = 0; i < equipitos.size(); i++) {
			String query = "SELECT * FROM Jugador WHERE id = " + equipitos.get(i);
			Statement querySt;
			try {
				querySt = con.createStatement();
				ResultSet rs = querySt.executeQuery(query);
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						int id = rs.getInt(1);
						String n = rs.getString(2);
						
						res.add(new Equipo(id, n));
					}
				}
			} catch (SQLException q) {
	            q.printStackTrace();
			}
		}
			
		return res;
	}
}
