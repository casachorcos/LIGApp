package prLIGApp;

import java.util.List;

public class Main1 {
	
	private static void bd() {

		Conexion accesoBD;

		accesoBD = ConexionJDBC.getInstance();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		bd();
	}

}
