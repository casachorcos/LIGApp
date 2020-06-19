package prLIGAppVista;

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
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Jugador;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class FormularioJugador extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField edad;
	DefaultListModel listaJ;
	private List<Equipo> listae;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioJugador frame = new FormularioJugador();
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
	public FormularioJugador() {
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
		
		JLabel lblNewLabel = new JLabel("Añadir Jugador");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(47, 125, 95, 14);
		panel_1.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(199, 123, 299, 20);
		panel_1.add(nombre);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblEdad.setBounds(47, 165, 107, 14);
		panel_1.add(lblEdad);
		
		edad = new JTextField();
		edad.setColumns(10);
		edad.setBounds(199, 163, 299, 20);
		panel_1.add(edad);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugadores j = new jugadores();
				j.setVisible(true);
				setVisible(false);
			}
		});
		cancelar.setBounds(386, 414, 130, 30);
		panel_1.add(cancelar);
		
		listaJ = new DefaultListModel();
		
		JList list = new JList();
		list.setBounds(209, 216, 289, 167);
		list.setModel(listaJ);
		list.setLayoutOrientation(JList.VERTICAL);
		
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		listae = accesoBD.usuario_equipo(Inicio.nombreUsuario);
		
		
		for (Equipo e : listae) {
			listaJ.addElement(e.toString());
		}
		
		JLabel lblSeleccinDeEquipo = new JLabel("Selecci\u00F3n de equipo:");
		lblSeleccinDeEquipo.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblSeleccinDeEquipo.setBounds(35, 230, 154, 14);
		panel_1.add(lblSeleccinDeEquipo);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.isSelectionEmpty()) {
					Conexion accesoBD;
					accesoBD = ConexionJDBC.getInstance();
					accesoBD.crearJugador_Usuario(new Jugador(accesoBD.generarID(), nombre.getText(), Integer.parseInt(edad.getText())), Inicio.nombreUsuario);
					jugadores j = new jugadores();
					j.setVisible(true);
					setVisible(false);
				} else {
					Conexion accesoBD;
					accesoBD = ConexionJDBC.getInstance();
					int id = accesoBD.generarID();
					accesoBD.crearJugador_Usuario(new Jugador(id, nombre.getText(), Integer.parseInt(edad.getText())), Inicio.nombreUsuario);
					Equipo equipo = listae.get(list.getSelectedIndex());
					accesoBD.crearJugadorEnEquipo(new Jugador(id, nombre.getText(), Integer.parseInt(edad.getText())), equipo);
					jugadores j = new jugadores();
					j.setVisible(true);
					setVisible(false);
				}
			}
		});
		aceptar.setBounds(111, 414, 130, 30);
		panel_1.add(aceptar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(209, 216, 289, 167);
		scrollPane.setViewportView(list);
		panel_1.add(scrollPane);
		
		this.setLocationRelativeTo(null);
	}
}
