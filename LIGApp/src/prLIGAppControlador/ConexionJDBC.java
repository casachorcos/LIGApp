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
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
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






}
