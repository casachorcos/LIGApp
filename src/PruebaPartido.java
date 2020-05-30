

import java.util.List;

import prLIGApp.*;

public class PruebaPartido {

	public static void main(String[] args) {
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();
		
		List<Partido> partidos = accesoBD.listaPartidos();
		
		accesoBD.eliminarPartido(partidos.get(0));
	}
}
