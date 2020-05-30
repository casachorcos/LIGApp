package prLIGApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class jugadores extends JFrame {

	private JPanel contentPane;
	
	DefaultListModel listaJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jugadores frame = new jugadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jugadores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 102));
		panel.setBounds(0, 0, 210, 474);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton players = new JButton("Mis Jugadores");
		players.setBounds(40, 120, 130, 30);
		panel.add(players);
		
		JButton equipos = new JButton("Mis Equipos");
		equipos.setBounds(40, 205, 130, 30);
		panel.add(equipos);
		
		JButton ligas = new JButton("Mis Ligas");
		ligas.setBounds(40, 293, 130, 30);
		panel.add(ligas);
		
		JButton btnAjustes = new JButton("Ajustes");
		btnAjustes.setBounds(40, 385, 130, 30);
		panel.add(btnAjustes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(209, 0, 653, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		List<Jugador> jug = accesoBD.usuario_jugador(Inicio.nombreUsuario);
		listaJ = new DefaultListModel();
		
		JList list = new JList();
		list.setBounds(25, 81, 396, 382);
		panel_1.add(list);
		list.setModel(listaJ);
		
		for (Jugador j : jug) {
			listaJ.addElement(j.toString());
		}
		
		this.setLocationRelativeTo(null);
	}

}
