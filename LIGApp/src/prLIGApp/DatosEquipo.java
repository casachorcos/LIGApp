package prLIGApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DatosEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private List<Jugador> listae;
	private List<Jugador> jugadores;
	DefaultListModel listaJ;
	DefaultListModel listaJUG;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatosEquipo frame = new DatosEquipo();
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
	public DatosEquipo() {
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(209, 0, 653, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datos Equipo");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(47, 125, 95, 14);
		panel_1.add(lblNombre);
		
		
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipos j = new Equipos();
				j.setVisible(true);
				setVisible(false);
			}
		});
		volver.setBounds(564, 433, 79, 30);
		panel_1.add(volver);
		
		nombre = new JTextField();
		nombre.setBounds(125, 123, 357, 20);
		panel_1.add(nombre);
		nombre.setText(Equipos.seleccionado.getNombre());
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		listae = accesoBD.plantilla(Equipos.seleccionado.getId());
		listaJ = new DefaultListModel();
		
		jugadores = accesoBD.usuario_jugador(Inicio.nombreUsuario);
		
		JList list = new JList();
		list.setBounds(125, 172, 142, 207);
		panel_1.add(list);
		list.setModel(listaJ);
		
		JLabel lblPlantilla = new JLabel("Plantilla:");
		lblPlantilla.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblPlantilla.setBounds(47, 173, 95, 14);
		panel_1.add(lblPlantilla);
		
		for (Jugador j : listae) {
			listaJ.addElement(j.toString());
		}
		
		JButton button = new JButton("Eliminar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accesoBD.eliminarJugadorEnEquipo(listae.get(list.getSelectedIndex()));
				DatosEquipo de = new DatosEquipo();
				setVisible(false);
				de.setVisible(true);
			}
		});
		button.setBounds(132, 390, 130, 30);
		panel_1.add(button);
		
		JList list_1 = new JList();
		list_1.setBounds(387, 172, 142, 207);
		panel_1.add(list_1);
		
		listaJUG = new DefaultListModel();
		
		list_1.setModel(listaJUG);
		
		for (Jugador jug : jugadores) {
			listaJUG.addElement(jug.toString());
		}
		
		JLabel lblJugadores = new JLabel("Jugadores:");
		lblJugadores.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblJugadores.setBounds(298, 171, 79, 16);
		panel_1.add(lblJugadores);
		
		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accesoBD.crearJugadorEnEquipo(jugadores.get(list_1.getSelectedIndex()), Equipos.seleccionado);
				DatosEquipo de = new DatosEquipo();
				setVisible(false);
				de.setVisible(true);
			}
		});
		btnAadir.setBounds(399, 397, 130, 30);
		panel_1.add(btnAadir);
		
		
		this.setLocationRelativeTo(null);
	}
}
