package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Jugador;

public class DatosEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private List<Jugador> listae;
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	DefaultListModel listaJ;
	DefaultListModel listaJUG;
	private List<Jugador> aux;
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
				Jugadores j = new Jugadores();
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
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		error.setBounds(282, 25, 333, 30);
		panel_1.add(error);
		error.setForeground(Color.RED);
		
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
		
		nombre.setEditable(false);
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		listae = accesoBD.plantilla(Equipos.seleccionado.getId());
		listaJ = new DefaultListModel();
		aux = accesoBD.usuario_jugador(Inicio.nombreUsuario);
		for(Jugador x : aux) {
			if(!listae.contains(x)) {
				jugadores.add(x);
			}
		}
		
		JList list = new JList();
		list.setBounds(1, 1, 14, 205);
		list.setModel(listaJ);
		
		JLabel lblPlantilla = new JLabel("Plantilla:");
		lblPlantilla.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblPlantilla.setBounds(47, 173, 95, 14);
		panel_1.add(lblPlantilla);
		
		for (Jugador j : listae) {
			if (accesoBD.capitan(Equipos.seleccionado, j)) {
				listaJ.addElement(j.toString() + " (C)");
			} else {
				listaJ.addElement(j.toString());
			}
			
		}
		
		JButton btnEliminarJugador = new JButton("Eliminar Jugador");
		btnEliminarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!list.isSelectionEmpty()) {
					accesoBD.eliminarJugadorEnEquipo(listae.get(list.getSelectedIndex()), Equipos.seleccionado);
					DatosEquipo de = new DatosEquipo();
					setVisible(false);
					de.setVisible(true);
				}else {
					error.setText("Debes seleccionar primero un jugador");
				}
			}
		});
		btnEliminarJugador.setBounds(60, 390, 130, 30);
		panel_1.add(btnEliminarJugador);
		
		JList list_1 = new JList();
		list_1.setBounds(1, 1, 140, 205);
		
		listaJUG = new DefaultListModel();
		
		list_1.setModel(listaJUG);
		
		for (Jugador jug : jugadores) {
			listaJUG.addElement(jug.toString());
		}
		
		JLabel lblJugadores = new JLabel("Jugadores:");
		lblJugadores.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblJugadores.setBounds(298, 171, 79, 16);
		panel_1.add(lblJugadores);
		
		JButton btnAadir = new JButton("A\u00F1adir Jugador");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!list_1.isSelectionEmpty()) {
					accesoBD.crearJugadorEnEquipo(jugadores.get(list_1.getSelectedIndex()), Equipos.seleccionado);
					DatosEquipo de = new DatosEquipo();
					setVisible(false);
					de.setVisible(true);
				}else {
					error.setText("Debes seleccionar primero un jugador");
				}
			}
		});
		btnAadir.setBounds(393, 390, 130, 30);
		panel_1.add(btnAadir);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(125, 172, 142, 207);
		scrollPane1.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel_1.add(scrollPane1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(387, 172, 142, 207);
		scrollPane2.setViewportView(list_1);
		list_1.setLayoutOrientation(JList.VERTICAL);
		panel_1.add(scrollPane2);
		
		JButton btnElegirCapitn = new JButton("Elegir Capit\u00E1n");
		btnElegirCapitn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					accesoBD.setCapi(Equipos.seleccionado, listae.get(list.getSelectedIndex()));
					DatosEquipo de = new DatosEquipo();
					setVisible(false);
					de.setVisible(true);
				} else {
					error.setText("Debes seleccionar primero un jugador");
				}
			}
		});
		btnElegirCapitn.setBounds(206, 390, 130, 30);
		panel_1.add(btnElegirCapitn);
		
		
		this.setLocationRelativeTo(null);
	}
}
