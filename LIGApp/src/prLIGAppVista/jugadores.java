package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Jugador;

public class jugadores extends JFrame {

	private JPanel contentPane;
	public static Jugador seleccionado;
	private List<Jugador> bdlista;
	
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
		players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jugadores j = new jugadores();
				j.setVisible(true);
				setVisible(false);
			}
		});
		players.setBounds(40, 141, 130, 30);
		panel.add(players);
		
		JButton equipos = new JButton("Mis Equipos");
		equipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipos ert = new Equipos();
				ert.setVisible(true);
				setVisible(false);
			}
		});
		equipos.setBounds(40, 219, 130, 30);
		panel.add(equipos);
		
		JButton ligas = new JButton("Mis Ligas");
		ligas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ligas lig = new Ligas();
				lig.setVisible(true);
				setVisible(false);
			}
		});
		ligas.setBounds(40, 299, 130, 30);
		panel.add(ligas);
		
		JButton btnAjustes = new JButton("Ajustes");
		btnAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajustes aj = new Ajustes();
				aj.setVisible(true);
				setVisible(false);
			}
		});
		btnAjustes.setBounds(40, 385, 130, 30);
		panel.add(btnAjustes);
		
		JButton user = new JButton("");
		user.setForeground(Color.WHITE);
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
				setVisible(false);
			}
		});
		user.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 20));
		user.setBounds(10, 47, 190, 35);
		panel.add(user);
		user.setBackground(new Color(51, 153, 102));
		user.setText(Inicio.nombreUsuario);
		user.setBorder(null);
	    user.setBorderPainted(false);
	    user.setContentAreaFilled(false);
	    
	    JList list = new JList();
	    list.setBounds(0, 0, 365, 348);
	    listaJ = new DefaultListModel();
	    list.setModel(listaJ);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(209, 0, 653, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton anyadir = new JButton("A\u00F1adir");
		anyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormularioJugador fj = new FormularioJugador();
				fj.setVisible(true);
				setVisible(false);
			}
		});
		anyadir.setBounds(472, 132, 130, 30);
		panel_1.add(anyadir);
		
		JLabel lblNewLabel = new JLabel("Mis jugadores");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		bdlista = accesoBD.usuario_jugador(Inicio.nombreUsuario);
		
		for (Jugador j : bdlista) {
			listaJ.addElement(j.toString());
		}
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listaJ.isEmpty() && !list.isSelectionEmpty()) {
					int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar el jugador?");
					if (res == 0) {
						int pos = list.getSelectedIndex();
						Jugador j = bdlista.get(pos);
						accesoBD.eliminarJugadorEnEquipo(j);
						accesoBD.eliminarJugador_Us(j, Inicio.nombreUsuario);
						listaJ.remove(pos);
					}
				}

			}
		});
		eliminar.setBounds(472, 252, 130, 30);
		panel_1.add(eliminar);
		
		JButton ver = new JButton("Ver");
		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					int pos = list.getSelectedIndex();
					seleccionado = bdlista.get(pos);
					DatosJugadores dj = new DatosJugadores();
					dj.setVisible(true);
					setVisible(false);
				}
			}
		});
		ver.setBounds(472, 362, 130, 30);
		panel_1.add(ver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 365, 348);
		scrollPane.setLayout(new BorderLayout());
		scrollPane.add(list, BorderLayout.NORTH);
		panel_1.add(scrollPane);
		
		this.setLocationRelativeTo(null);
	}
}
