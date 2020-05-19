import java.util.*;


import javax.swing.JFrame;

import LEJ.*;


public class main_ligapp {

	private static void base_datos() {
		ConexionConBasedeDatos accesoBD;

		accesoBD = ConexionBaseDatosJDBC.getInstance();
//		//		accesoBD = ConexionBaseDatosHibernate.getInstance();
//		List<Equipo> listEquipos = accesoBD.listaEquipos();
		List<Jugador> listJugadores = accesoBD.listaJugadores();
//		ListIterator<Jugador> it = listJugadores.listIterator();
//		while(it.hasNext()) {
//			if (it.next().getIdEquipo() != null) {
//				it.remove();
//			}
//		}
//		for (Jugador jgd : listJugadores) {
//			System.out.print(jgd.identificador);
//			System.out.print(" " + jgd.nombre);
//			System.out.print(" " + jgd.edad);
//			System.out.println(" " + jgd.idEquipo);
//		}
//		// Create and set up the window.
//		JFrame frame = new JFrame("HelloWorldJBDCHibernate");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		frame.getContentPane().add(createJTabbedPane(accesoBD, listEquipos, listJugadores));
//
//		// Display the window.
		System.out.println(listJugadores.toArray().toString());
	}
	 
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				base_datos();
			}
		});

	}

}
