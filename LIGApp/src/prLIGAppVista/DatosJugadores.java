package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Jugador;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class DatosJugadores extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField edad;
	private JTextField positionField;
	DefaultListModel listaJ;
	private List<Equipo> listadelosequipos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatosJugadores frame = new DatosJugadores();
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
	public DatosJugadores() {
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
		
		JLabel lblNewLabel = new JLabel("Datos Jugador");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(47, 75, 95, 14);
		panel_1.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblEdad.setBounds(47, 121, 107, 14);
		panel_1.add(lblEdad);
		
		
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jugadores j = new Jugadores();
				j.setVisible(true);
				setVisible(false);
			}
		});
		volver.setBounds(513, 433, 130, 30);
		panel_1.add(volver);
		
		nombre = new JTextField();
		nombre.setBounds(125, 75, 357, 20);
		panel_1.add(nombre);
		nombre.setColumns(10);
		
		edad = new JTextField();
		edad.setColumns(10);
		edad.setBounds(125, 119, 357, 20);
		panel_1.add(edad);
		
		positionField = new JTextField();
		positionField.setBounds(125, 162, 357, 20);
		panel_1.add(positionField);
		positionField.setColumns(10);
		
		nombre.setText(Jugadores.seleccionado.getNombre());
		edad.setText(String.valueOf(Jugadores.seleccionado.getEdad()));
		positionField.setText(Jugadores.seleccionado.getRol());
		
		nombre.setEditable(false);
		edad.setEditable(false);
		positionField.setEditable(false);
		
		Conexion acceso;
		acceso = ConexionJDBC.getInstance();
		
		List<Equipo> listaequipos = acceso.usuario_equipo(Inicio.nombreUsuario);
		listadelosequipos = new ArrayList<Equipo>();
		
		for (Equipo equi : listaequipos) {
			List<Jugador> juga = acceso.plantilla(equi.getId());
			if (juga.contains(Jugadores.seleccionado)) {
				listadelosequipos.add(equi);
			}
		}
		
		listaJ = new DefaultListModel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 215, 357, 189);
		panel_1.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(listaJ);
		list.setLayoutOrientation(JList.VERTICAL);
		
		JLabel lblPlantilla = new JLabel("Juega en:");
		lblPlantilla.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblPlantilla.setBounds(47, 221, 74, 20);
		panel_1.add(lblPlantilla);
		
		JLabel lblPosicin = new JLabel("Posici\u00F3n:");
		lblPosicin.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblPosicin.setBounds(47, 165, 95, 14);
		panel_1.add(lblPosicin);
		
		JSpinner campoGoles = new JSpinner();
		campoGoles.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		campoGoles.setBounds(553, 73, 74, 20);
		panel_1.add(campoGoles);
		
		JSpinner campoAmarillas = new JSpinner();
		campoAmarillas.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		campoAmarillas.setBounds(553, 119, 74, 20);
		panel_1.add(campoAmarillas);
		
		JSpinner campoRojas = new JSpinner();
		campoRojas.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		campoRojas.setBounds(553, 162, 74, 20);
		panel_1.add(campoRojas);
		
		JLabel lblGoles = new JLabel("Goles:");
		lblGoles.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblGoles.setBounds(492, 76, 74, 14);
		panel_1.add(lblGoles);
		
		JLabel lblAmarillas = new JLabel("Amarillas:");
		lblAmarillas.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblAmarillas.setBounds(492, 122, 74, 14);
		panel_1.add(lblAmarillas);
		
		JLabel lblRojas = new JLabel("Rojas:");
		lblRojas.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblRojas.setBounds(492, 165, 74, 14);
		panel_1.add(lblRojas);
		
		
		
		campoGoles.setValue(Jugadores.seleccionado.getGoles());
		campoAmarillas.setValue(Jugadores.seleccionado.getAmarillas());
		campoRojas.setValue(Jugadores.seleccionado.getRojas());
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();
		
		JLabel lblGuardado = new JLabel("", SwingConstants.CENTER);
		lblGuardado.setForeground(Color.BLUE);
		lblGuardado.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblGuardado.setBounds(513, 247, 140, 14);
		panel_1.add(lblGuardado);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Jugador j = new Jugador(0, null, 0);
				j.setGoles((Integer)campoGoles.getValue());
				j.setAmarillas((Integer)campoAmarillas.getValue());
				j.setRojas((Integer)campoRojas.getValue());
				accesoBD.actualizarJugador(j);
				lblGuardado.setText("Guardado");
			}
		});
		btnGuardar.setBounds(538, 213, 89, 23);
		panel_1.add(btnGuardar);
		
		
		
		
		
		
		for (Equipo j : listadelosequipos) {
			if (accesoBD.capitan(j, Jugadores.seleccionado)) {
				listaJ.addElement(j.toString() + " (C)");
			} else {
				listaJ.addElement(j.toString());
			}
		}
		
		
		
		this.setLocationRelativeTo(null);
	}
}
