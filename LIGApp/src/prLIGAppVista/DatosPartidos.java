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

public class DatosPartidos extends JFrame {

	private JPanel contentPane;
	private JTextField localtxt;
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
	public DatosPartidos() {
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
		
		JLabel lblNewLabel = new JLabel(Partidos.seleccionadoS);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 618, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Goles equipo local:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(47, 125, 146, 21);
		panel_1.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Goles equipo visitante:");
		lblEdad.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblEdad.setBounds(47, 165, 140, 21);
		panel_1.add(lblEdad);
		
		localtxt = new JTextField();
		localtxt.setBounds(203, 126, 86, 20);
		panel_1.add(localtxt);
		localtxt.setColumns(10);
		localtxt.setText("");
		
		visitantetxt = new JTextField();
		visitantetxt.setColumns(10);
		visitantetxt.setBounds(203, 166, 86, 20);
		panel_1.add(visitantetxt);
		visitantetxt.setText("");
		
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
				if (localtxt.getText() != "" && visitantetxt.getText() != "") {
					int loc = Integer.parseInt(localtxt.getText());
					int vis = Integer.parseInt(visitantetxt.getText());
					if (loc > vis) {
						acceso.actualizarclasi(Ligas.seleccionado.getId(), Partidos.seleccionadoP.getIdLocal(), 3, loc, vis);
						acceso.actualizarclasi(Ligas.seleccionado.getId(), Partidos.seleccionadoP.getIdVisitante(), 0, vis, loc);
					} else if (loc == vis) {
						acceso.actualizarclasi(Ligas.seleccionado.getId(), Partidos.seleccionadoP.getIdLocal(), 1, loc, vis);
						acceso.actualizarclasi(Ligas.seleccionado.getId(), Partidos.seleccionadoP.getIdVisitante(), 1, vis, loc);
					} else {
						acceso.actualizarclasi(Ligas.seleccionado.getId(), Partidos.seleccionadoP.getIdLocal(), 0, loc, vis);
						acceso.actualizarclasi(Ligas.seleccionado.getId(), Partidos.seleccionadoP.getIdVisitante(), 3, vis, loc);
					}
					
					Partidos par = new Partidos();
					par.setVisible(true);
					setVisible(false);
				}
			}
		});
		eliminar.setBounds(159, 433, 130, 30);
		panel_1.add(eliminar);
		
		this.setLocationRelativeTo(null);
	}
}
