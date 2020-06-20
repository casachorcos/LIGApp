package prueba;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;

public class PruebaMain {

	public static void main(String[] args) {
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();
		System.out.print(accesoBD.usuario_equipo("LIGApp"));
		
		
	}

}
