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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;

public class DatosJornadas extends JFrame {

	private JPanel contentPane;
	private JTextField diatxt;
	private JTextField mestxt;
	private JTextField aniotxt;
	private JTextField duraciontxt;
	private JTextField visitantetxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatosPartidos frame = new DatosPartidos();
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
	public DatosJornadas() {
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
		
		JLabel lblNewLabel = new JLabel("Modificar Fecha de Jornada " + Jornadas.seleccionado.getNumeroJornada());
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(72, 31, 571, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Fecha de inicio de Jornada:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(122, 199, 146, 21);
		panel_1.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Días de duración:");
		lblEdad.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblEdad.setBounds(153, 275, 140, 21);
		panel_1.add(lblEdad);
		
		diatxt = new JTextField();
		diatxt.setBounds(278, 200, 40, 20);
		panel_1.add(diatxt);
		diatxt.setColumns(10);
		diatxt.setText("");
		
		mestxt = new JTextField();
		mestxt.setBounds(340, 200, 40, 20);
		panel_1.add(mestxt);
		mestxt.setColumns(10);
		mestxt.setText("");
		
		aniotxt = new JTextField();
		aniotxt.setBounds(403, 200, 56, 20);
		panel_1.add(aniotxt);
		aniotxt.setColumns(10);
		aniotxt.setText("");
		
		duraciontxt = new JTextField();
		duraciontxt.setBounds(340, 276, 86, 20);
		panel_1.add(duraciontxt);
		duraciontxt.setColumns(10);
		duraciontxt.setText("");
		
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partidos j = new Partidos();
				j.setVisible(true);
				setVisible(false);
			}
		});
		volver.setBounds(513, 433, 130, 30);
		panel_1.add(volver);
		
		Conexion acceso;
		acceso = ConexionJDBC.getInstance();
		
		JButton eliminar = new JButton("Aceptar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (diatxt.getText() != "" && mestxt.getText() != "" && aniotxt.getText() != "" && duraciontxt.getText() != "") {
					
					int dia = Integer.parseInt(diatxt.getText());
					int mes = Integer.parseInt(mestxt.getText());
					int anio = Integer.parseInt(aniotxt.getText());
					int dur = Integer.parseInt(duraciontxt.getText());
					
					acceso.actualizarFechaJornada(Jornadas.seleccionado, dia, mes, anio, dur);
					
					Partidos par = new Partidos();
					par.setVisible(true);
					setVisible(false);
				}
			}
		});
		eliminar.setBounds(260, 346, 130, 30);
		panel_1.add(eliminar);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblDia.setBounds(278, 168, 29, 21);
		panel_1.add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblMes.setBounds(340, 168, 29, 21);
		panel_1.add(lblMes);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblAo.setBounds(403, 168, 29, 21);
		panel_1.add(lblAo);
		
		this.setLocationRelativeTo(null);
	}
}
