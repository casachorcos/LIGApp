package prLIGAppControlador;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.mysql.jdbc.PreparedStatement;

import prLIGAppModelo.Clasificacion;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Jornada;
import prLIGAppModelo.Jugador;
import prLIGAppModelo.Liga;
import prLIGAppModelo.Partido;
import prLIGAppModelo.Usuario;
import prLIGAppVista.Jornadas;
import prLIGAppVista.Jugadores;
import prLIGAppVista.Ligas;
import prLIGAppVista.Partidos;

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
	
	
	
	
	// MÉTODOS CREAR BÁSICOS (CREAN EN BASE DE DATOS EL OBJETO CORRESPONDIENTE) 
	
	
	
	
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
		String query = "INSERT INTO Jugador (nombre, id, edad, posicion) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pS.setString(1, j.getNombre());
			pS.setInt(2, j.getId());
			pS.setInt(3, j.getEdad());
			pS.setString(4, j.getRol());
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
		String query = "INSERT INTO Jornada (codjornada, numjornada, liga, iniciojornada, finjornada) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pS.setInt(1, jor.getCodigoJornada());
			pS.setInt(2, jor.getNumeroJornada());
			pS.setInt(3, jor.getNombreLiga());
			pS.setDate(4, jor.getFechaInicio());
			pS.setDate(5, jor.getFechaFin());
			int res = pS.executeUpdate();
			ResultSet rs = pS.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void crearPartido(Partido p) {
        String query = "INSERT INTO Partido (codpartido, local, visitante, jornada, goleslocal,"
        + " golesvisitante, campo, fecha, jugado, hora) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pS.setInt(1, p.getCodigoPartido());
            pS.setInt(2, p.getIdLocal());
            pS.setInt(3, p.getIdVisitante());
            pS.setInt(4, p.getCodigoJornada());
            pS.setInt(5, p.getGolesLocal());
            pS.setInt(6, p.getGolesVisitante());
            pS.setString(7, p.getCampo());
            pS.setDate(8, p.getFecha());
            pS.setBoolean(9, p.getJugado());
            pS.setString(10, p.getHora());
            int res = pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	
	// MÉTODOS CREAR AVANZADOS (CREAN EN BASE DE DATOS LA RELACION CORRESPONDIENTE) 
	
	
	
	
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
	
	
	public void crearJugador_Usuario2(Jugador e, String usuario) {
        String privado = "INSERT INTO Us_Jug (idUsuarioUs_Jug, idJugadorUs_Jug) VALUES (?, ?)";
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
	
	
	public void crearEquipo_Usuario2(Equipo e, String usuario) {
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
	
	
	public void crearLiga_Usuario2(Liga e, String usuario) {
        String privado = "INSERT INTO Us_Lig (idUsuarioUs_Lig, idLigaUs_Lig) VALUES (?, ?)";
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
	
	
	public void crearJugadorEnEquipo(Jugador j, Equipo eq) {
        String query = "INSERT INTO Plantilla (idJugador, idEquipo) VALUES (?, ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pS.setInt(1, j.getId());
            pS.setInt(2, eq.getId());
            int res = pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
    public void crearEquipoEnLiga(Equipo eq, Liga a) {
        String query = "INSERT INTO Clasificacion (idEquipoClasificacion, idLigaClasificacion) VALUES (?, ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pS.setInt(1, eq.getId());
            pS.setInt(2, a.getId());
            int res = pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	// MÉTODOS ELIMINAR BÁSICOS (ELIMINAN EN BASE DE DATOS EL OBJETO CORRESPONDIENTE) 
		
		
		
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
        String deleteBody = "DELETE FROM Partido WHERE (codpartido = ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
            pS.setInt(1, p.getCodigoPartido());
            int res = pS.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	// MÉTODOS ELIMINAR AVANZADOS (ELIMINAN EN BASE DE DATOS LA RELACION CORRESPONDIENTE) 
		
		
		
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	public void eliminarJugador_Us(Jugador j,String usuario) {
		
		String privado = "DELETE FROM Us_Jug WHERE (idUsuarioUs_Jug = ? && idJugadorUs_Jug = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, j.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		eliminarJugador(j);
	}
	
	
	public void eliminarJugador_Us2(Jugador eq,String usuario) {	
		String privado = "DELETE FROM Us_Jug WHERE (idUsuarioUs_Jug = ? && idJugadorUs_Jug = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, eq.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	public void eliminarEquipo_Us(Equipo eq,String usuario) {
		
		
		String privado = "DELETE FROM Us_Eq WHERE (idUsuarioUs_Eq = ? && idEquipoUs_Eq = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, eq.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		eliminarEquipo(eq);
		
	}
	
	
	public void eliminarEquipo_Us2(Equipo eq,String usuario) {	
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
	
	
	public void eliminarLiga_Us(Liga a, String usuario) {
		
		
		String privado = "DELETE FROM Us_Lig WHERE (idUsuarioUs_Lig = ? && idLigaUs_Lig = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, a.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		eliminarLiga(a);
	}
	
	
	public void eliminarLiga_Us2(Liga eq,String usuario) {	
		String privado = "DELETE FROM Us_Lig WHERE (idUsuarioUs_Lig = ? && idLigaUs_Lig = ?)";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(privado);
			pS.setString(1, usuario);
			pS.setInt(2, eq.getId());
			int res = pS.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
    
    public void eliminarJugadorEnEquipo(Jugador j) {
        String deleteBody = "DELETE FROM Plantilla WHERE (idJugador = ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
            pS.setInt(1, j.getId());
            int res = pS.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void eliminarJugadorEnEquipo(Jugador j, Equipo eq) {
        String deleteBody = "DELETE FROM Plantilla WHERE (idJugador = ?) AND (idEquipo = ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
            pS.setInt(1, j.getId());
            pS.setInt(2, eq.getId());
            int res = pS.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void eliminarJugadorEnEquipo(Equipo j) {
        String deleteBody = "DELETE FROM Plantilla WHERE (idEquipo = ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
            pS.setInt(1, j.getId());
            int res = pS.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void eliminarEquipoEnLiga(Equipo eq) {
        String deleteBody = "DELETE FROM Clasificacion WHERE (idEquipoClasificacion = ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
            pS.setInt(1, eq.getId());
            int res = pS.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void eliminarEquipoEnLiga(Equipo eq, Liga lig) {
        String deleteBody = "DELETE FROM Clasificacion WHERE (idEquipoClasificacion = ?) AND (idLigaClasificacion = ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
            pS.setInt(1, eq.getId());
            pS.setInt(2, lig.getId());
            int res = pS.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void eliminarEquipoEnLiga(Liga eq) {
        String deleteBody = "DELETE FROM Clasificacion WHERE (idLigaClasificacion = ?)";
        try {
            PreparedStatement pS = (PreparedStatement) con.prepareStatement(deleteBody);
            pS.setInt(1, eq.getId());
            int res = pS.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
	
	public void eliminarPartidosDeEquipo(Equipo e) {
		
		ArrayList<Partido> partidosDeEquipo = new ArrayList<>();
		
		String queryLocal = "SELECT * FROM Partido WHERE local = " + e.getId();
		String queryVisitante = "SELECT * FROM Partido WHERE visitante = " + e.getId();
		
		 Statement querySt1;
	        try {
	            querySt1 = con.createStatement();
	            ResultSet rs = querySt1.executeQuery(queryLocal);
	            if (rs.isBeforeFirst()) {
	                while (rs.next()) {
	                    int cod = rs.getInt(1);
	                    int idL = rs.getInt(2);
	                    int idV = rs.getInt(3);
	                    int codJor = rs.getInt(4);
	                    int gL = rs.getInt(5);
	                    int gV = rs.getInt(6);
	                    String ca = rs.getString(7);
	                    Date f = rs.getDate(8);
	                    boolean ju = rs.getBoolean(9);
	                    String h = rs.getString(10);
	                    partidosDeEquipo.add(new Partido(cod, idL, idV, codJor, gL, gV, ca, f, ju, h));
	                }
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        
	     Statement querySt2;
	        try {
	            querySt2 = con.createStatement();
	            ResultSet rs = querySt2.executeQuery(queryVisitante);
	            if (rs.isBeforeFirst()) {
	                while (rs.next()) {
	                    int cod = rs.getInt(1);
	                    int idL = rs.getInt(2);
	                    int idV = rs.getInt(3);
	                    int codJor = rs.getInt(4);
	                    int gL = rs.getInt(5);
	                    int gV = rs.getInt(6);
	                    String ca = rs.getString(7);
	                    Date f = rs.getDate(8);
	                    boolean ju = rs.getBoolean(9);
	                    String h = rs.getString(10);
	                    partidosDeEquipo.add(new Partido(cod, idL, idV, codJor, gL, gV, ca, f, ju, h));
	                }
	            }
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
		
		for (Partido p : partidosDeEquipo) {
			
			eliminarClasiDePartido(p);
			eliminarPartido(p);
		}
	}
	
	
	public void eliminarClasiDePartido(Partido p) {
		
		String updateBodyLoc = "UPDATE Clasificacion c SET puntos = puntos + ?, golesMarcados = golesMarcados + ?, golesEnContra = golesEnContra + ?,"
				+ " partidosJugados = partidosJugados + ? WHERE c.idEquipoClasificacion = " + p.getIdLocal();
		
		String updateBodyVis = "UPDATE Clasificacion c SET puntos = puntos + ?, golesMarcados = golesMarcados + ?, golesEnContra = golesEnContra + ?,"
				+ " partidosJugados = partidosJugados + ? WHERE c.idEquipoClasificacion = " + p.getIdVisitante();
		
		if (p.getJugado() == true) {
			
			int l;
			int v;
			
			if (p.getGolesLocal() > p.getGolesVisitante()) {
				l = - 3;
				v = 0;
			} else if (p.getGolesLocal() == p.getGolesVisitante()) {
				l = - 1;
				v = - 1;
			}
			else {
				l = 0;
				v = - 3;
			}
			try {
				PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBodyLoc);
					pS.setInt(1, l);
					pS.setInt(2, - p.getGolesLocal());
					pS.setInt(3, - p.getGolesVisitante());
					pS.setInt(4, -1);
					int res = pS.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
			}
			
			try {
				PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBodyVis);
					pS.setInt(1, v);
					pS.setInt(2, - p.getGolesVisitante());
					pS.setInt(3, - p.getGolesLocal());
					pS.setInt(4, -1);
					int res = pS.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}
	

	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	// MÉTODOS LISTA BÁSICOS (CREAN UNA LISTA DE LOS OBJETOS CORRESPONDIENTES) 
		
		
		
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
	
	
	public List<Jornada> listaJornadas() {
	        ArrayList<Jornada> jornadas = new ArrayList<>();
	        String query = "SELECT * FROM Jornada";
	        try {
	        	PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
	            ResultSet rs = pS.executeQuery(query);
	            if (rs.isBeforeFirst()) {
	                while (rs.next()) {
	                    int cod = rs.getInt(1);
	                    int numJor = rs.getInt(2);
	                    int id = rs.getInt(3);
	                    Date fIni = rs.getDate(4);
	                    Date fFin = rs.getDate(5);
	                    jornadas.add(new Jornada(cod, numJor, id, fIni, fFin));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return jornadas;
	    }
	
	
	public List<Jornada> listaJornadas(int idLiga) {
        ArrayList<Jornada> jornadas = new ArrayList<>();
        String query = "SELECT * FROM Jornada WHERE liga = " + idLiga;
        try {
        	PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
			//pS.setInt(1, idLiga);
            ResultSet rs = pS.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int cod = rs.getInt(1);
                    int numJor = rs.getInt(2);
                    int id = rs.getInt(3);
                    Date fIni = rs.getDate(4);
                    Date fFin = rs.getDate(5);
                    jornadas.add(new Jornada(cod, numJor, id, fIni, fFin));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jornadas;
    }
	
	
	public List<Partido> listaPartidos(int codJornada) {
        ArrayList<Partido> partidos = new ArrayList<>();
        String query = "SELECT * FROM Partido WHERE jornada = " + codJornada;
        Statement querySt;
        try {
            querySt = con.createStatement();
            ResultSet rs = querySt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int cod = rs.getInt(1);
                    int idL = rs.getInt(2);
                    int idV = rs.getInt(3);
                    int codJor = rs.getInt(4);
                    int gL = rs.getInt(5);
                    int gV = rs.getInt(6);
                    String ca = rs.getString(7);
                    Date f = rs.getDate(8);
                    boolean ju = rs.getBoolean(9);
                    String h = rs.getString(10);
                    partidos.add(new Partido(cod, idL, idV, codJor, gL, gV, ca, f, ju, h));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }
	
	
		
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
    
    
	
	// OTROS METODOS LISTA (CREAN UNA LISTA DE LOS OBJETOS RELACIONADOS CORRESPONDIENTES) 
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
    
   
	
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
					String pos = rs.getString(5);
					int goles = rs.getInt(6);
					int amarillas = rs.getInt(7);
					int rojas = rs.getInt(8);
					Jugador jug = new Jugador(id, nombre, edad);
					jug.setRol(pos);
					jug.setGoles(goles);
					jug.setAmarillas(amarillas);
					jug.setRojas(rojas);
					jugadores.add(jug);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	
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
	
	
	public List<Object[]> clasif(int idLiga) {
		List<Object[]> equipos = new ArrayList<Object[]>();

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
					
					Object[] o = new Object[5];
					o[0] = nombreequipo;
					o[1] = puntos;
					o[2] = golesmarcados;
					o[3] = golesencontra;
					o[4] = partidosjugados;
					
					equipos.add(o);
				}
			}
			pS.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return equipos;
	}
	
	
	public List<Jugador> plantilla(int idEquipo) {
		ArrayList<Jugador> jugadores = new ArrayList<>();

		String query = "SELECT e.nombre, e.id, e.edad FROM Plantilla c, Jugador e WHERE c.idJugador = e.id AND c.idEquipo = ?";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
			pS.setInt(1, idEquipo);
			ResultSet rs = pS.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String nombrejugador = rs.getString(1);
					int id = rs.getInt(2);
					int edad = rs.getInt(3);	
					jugadores.add(new Jugador(id,nombrejugador,edad));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	
	public List<Equipo> equiposLiga(int codLiga){
		List<Equipo> equipos = new ArrayList<>();

		String query = "SELECT e.id, e.nombre FROM Equipo e, Clasificacion c WHERE e.id =c.idEquipoClasificacion AND c.idLigaClasificacion = "+ codLiga;
		
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String nombre = rs.getString(2);
					equipos.add(new Equipo(id,nombre));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return equipos;
	}
	
	
	
		
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
    
    
	
	// MÉTODOS ACTUALIZAR Y GENERAR (MODIFICAN Y GENERAN LOS DISTINTOS OBJETOS DE LA APLICACIÓN) 
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
    
   
	public void actualizarID(int id) {
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
	
	
	public void actualizarCodJornada(int cod) {
		String updateBody = "UPDATE IncrementaCodJornada SET iteradorJornada = ?";
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cod;
	}
	
	
	public void actualizarCodPartido(int cod) {
		String updateBody = "UPDATE IncrementaCodPartido SET iteradorPartido = ?";
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBody);
			pS.setInt(1, cod);
			int res = pS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	
	public void actualizarJugador(Jugador j) {
		String updateBody = "UPDATE Jugador p SET goles = ?, amarillas = ?, rojas = ? WHERE id = " + 
				Jugadores.seleccionado.getId();
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBody);
				pS.setInt(1, j.getGoles());
				pS.setInt(2, j.getAmarillas());
				pS.setInt(3, j.getRojas());
	            int res = pS.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	 public void actualizarFechaJornada(Jornada jor, int dia, int mes, int anio, int dur) {
	    	
	    	@SuppressWarnings("deprecation")
			Date inicio = new Date(anio, mes - 1 , dia);
	    	Date fin = new Date(inicio.getTime() + (dur - 1) * 24 * 60 * 60 * 1000);
	    	
			@SuppressWarnings("deprecation")
			String query = "UPDATE Jornada j SET iniciojornada = '" + inicio.getYear() + "-" + (inicio.getMonth() + 1) + "-" + inicio.getDate() + "',"
	    			+ " finjornada = '" + fin.getYear() + "-" + (fin.getMonth() + 1) + "-" + fin.getDate() + "' WHERE j.codjornada = " + jor.getCodigoJornada();
	    	
	    	  try {
	          	PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
	          	int res = pS.executeUpdate();
	    	  
	          } catch (SQLException e) {
	              e.printStackTrace();
	     }
	    	
	}
	 
	 
	public void actualizarPartido(Partido p) {
		
		String updateBody = "UPDATE Partido p SET codpartido = ?, local = ?, visitante = ?, jornada = ?, goleslocal = ?,"
				+ " golesvisitante = ?, campo = ?, fecha = ?, jugado = ?, hora = ? WHERE p.codPartido = " + 
				Partidos.seleccionadoP.getCodigoPartido();
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBody);
				pS.setInt(1, p.getCodigoPartido());
	            pS.setInt(2, p.getIdLocal());
	            pS.setInt(3, p.getIdVisitante());
	            pS.setInt(4, p.getCodigoJornada());
	            pS.setInt(5, p.getGolesLocal());
	            pS.setInt(6, p.getGolesVisitante());
	            pS.setString(7, p.getCampo());
	            pS.setDate(8, p.getFecha());
	            pS.setBoolean(9, p.getJugado());
	            pS.setString(10, p.getHora());
	            int res = pS.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void actualizarclasi(int codLiga, int codEquipo, int punt, int goMar, int goCont) {
	    	Clasificacion cla = equipoclasificacion(codLiga, codEquipo);
			PreparedStatement ps = null;
			String query;
			try {
				query = "UPDATE Clasificacion SET puntos = ?, golesMarcados = ?, golesEnContra = ?, partidosJugados = ? WHERE idLigaClasificacion = ? AND idEquipoClasificacion = ?";
				ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, punt + cla.getPuntos());
				ps.setInt(2, goMar + cla.getGolesmarcados());
				ps.setInt(3, goCont + cla.getGolesencontra());
				ps.setInt(4, cla.getPartidosjugados() + 1);
				ps.setInt(5, codLiga);
				ps.setInt(6, codEquipo);
				int res = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	    
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
    
    
	
	// OTROS MÉTODOS DE LA APLICACIÓN
		
		
		
		
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
	public int contarJugador_Us(Jugador eq) {
		String query = "SELECT count(idUsuarioUs_Jug) FROM Us_Jug WHERE idJugadorUs_Jug = ?";
		int res = 0;
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
			pS.setInt(1, eq.getId());
			ResultSet rs = pS.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					res = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int contarEquipo_Us(Equipo eq) {
		String query = "SELECT count(idUsuarioUs_Eq) FROM Us_Eq WHERE idEquipoUs_Eq = ?";
		int res = 0;
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
			pS.setInt(1, eq.getId());
			ResultSet rs = pS.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					res = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int contarLiga_Us(Liga eq) {
		String query = "SELECT count(idUsuarioUs_Lig) FROM Us_Lig WHERE idLigaUs_Lig = ?";
		int res = 0;
		try {
			PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
			pS.setInt(1, eq.getId());
			ResultSet rs = pS.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					res = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	public void cambiarContrasena(String idUsuario, String cont) {
		PreparedStatement ps = null;
		String query;
		try {
			query = "UPDATE Usuario SET password = ? WHERE nombre = ?";
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, cont);
			ps.setString(2, idUsuario);
			int res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void ajustarNumerosJornada(int numJor) {
		int n = numJor;
		List<Jornada> lista = listaJornadas(Ligas.seleccionado.getId());
		
		for(int i = n ; i <= lista.size(); i++) {
			
			String updateBody = "UPDATE Jornada j SET numjornada = ? WHERE j.liga = " + 
					Ligas.seleccionado.getId() + " AND j.numJornada = " + (i + 1);
			try {
				PreparedStatement pS = (PreparedStatement) con.prepareStatement(updateBody);
				pS.setInt(1, i);
				int res = pS.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void parEnfrentado(Equipo l, Equipo v, Jornada jor) {
        int cod = generarCodPartido();
        Partido p = new Partido(cod, l.getId(),v.getId(), jor.getCodigoJornada());
        crearPartido(p);
        actualizarCodPartido(cod + 1);
    }

	
	public void emparejamientos(Jornada jor) {
		List<Equipo> equipos = equiposLiga(jor.getNombreLiga());
		listaEquipos();
		do {
			int i, j;
			Random r = new Random();
			
			i = Math.abs(r.nextInt() % equipos.size());
			Equipo eq1 = equipos.get(i);
			equipos.remove(i);
			j = Math.abs(r.nextInt() % equipos.size());
			Equipo eq2 = equipos.get(j);
			equipos.remove(j);
			parEnfrentado(eq1, eq2, jor);
		} while (equipos.size() > 1);
	}

	public Clasificacion equipoclasificacion(int codLiga, int codEquipo) {
    	Clasificacion clasi = null;
        String query = "SELECT * FROM Clasificacion WHERE idLigaClasificacion = " + codLiga + " AND idEquipoClasificacion = " + codEquipo;
        try {
        	PreparedStatement pS = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = pS.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int puntos = rs.getInt(3);
                    int gM = rs.getInt(4);
                    int gec = rs.getInt(5);
                    int pj = rs.getInt(6);
                    clasi = new Clasificacion("", puntos, gM, gec, pj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clasi;
    }
	
    
  	public void setCapi(Equipo equipo, Jugador jug) {
		PreparedStatement ps = null;
		String query;
		try {
			query = "UPDATE Equipo SET capitan = ? WHERE id = ?";
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, jug.getId());
			ps.setInt(2, equipo.getId());
			int res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean capitan(Equipo equipo, Jugador jug) {
		boolean res = false;

		String query = "SELECT capitan FROM Equipo WHERE id = " + equipo.getId();
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int c = rs.getInt(1);
					if (c == jug.getId()) {
						res = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
}
