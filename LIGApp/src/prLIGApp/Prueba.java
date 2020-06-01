package prLIGApp;

import java.util.List;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Conexion acceso;
		acceso = ConexionJDBC.getInstance();
		
		List<Jornada> lista = acceso.listaJornadas();
		System.out.println(lista.toString());
		System.out.println("--------------");
		List<Jornada> lista2 = acceso.listaJornadas(25);
		System.out.println(lista2.toString());
	}

}
