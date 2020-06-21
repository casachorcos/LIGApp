package prueba;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Equipo;

public class PruebaMain {

	public static void main(String[] args) {
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();
		System.out.println(accesoBD.contarEquipo_Us(new Equipo(260,"")));
		
	}

}
