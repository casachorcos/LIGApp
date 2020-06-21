package prueba;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Jugador;

public class PruebaMain {

	public static void main(String[] args) {
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();
		Jugador j = new Jugador(accesoBD.generarID(), "VIVA SPAIN", 100);
		j.setRol("Delantero/a");
		accesoBD.crearJugador(j);
		System.out.println(j.getRol());
		System.out.println(accesoBD.listaJugadores());
		
	}

}
