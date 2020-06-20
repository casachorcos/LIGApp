package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Jornada;
import prLIGAppModelo.Partido;

public class Jornadas extends JFrame {

	private JPanel contentPane;
	DefaultListModel listaJ;
	private List<Jornada> listae;
	public static Jornada seleccionado;
	public static String nombreJornada;
	public static String nombreJornadaSimple;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jornadas frame = new Jornadas();
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
	public Jornadas() {
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
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		error.setBounds(282, 25, 333, 30);
		panel_1.add(error);
		error.setForeground(Color.RED);
		
		JLabel lblNewLabel = new JLabel("Jornadas");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		listae = accesoBD.listaJornadas(Ligas.seleccionado.getId());
		listaJ = new DefaultListModel();
		
		JList list = new JList();
		list.setBounds(1, 1, 363, 346);
		list.setModel(listaJ);
		
		for (Jornada j : listae) {
			listaJ.addElement("Jornada " + j.getNumeroJornada() + "    -    Inicio: " + j.getFechaInicio() + "    Fin: " + j.getFechaFin());
		}
		
		JButton ver = new JButton("Ver Datos de Jornada");
		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					seleccionado = listae.get(list.getSelectedIndex());
					nombreJornada = (String) listaJ.elementAt(list.getSelectedIndex());
					Partidos match = new Partidos();
					match.setVisible(true);
					setVisible(false);
				}
				else {
				
					error.setText("Debes seleccionar primero una jornada");
				}
			}
		});
		
		ver.setBounds(456, 312, 160, 30);
		panel_1.add(ver);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosLiga j = new DatosLiga();
				j.setVisible(true);
				setVisible(false);
			}
		});
		volver.setBounds(513, 433, 130, 30);
		panel_1.add(volver);
		
		JButton anyadir = new JButton("Añadir Nueva Jornada");
		anyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listae.isEmpty()) {
					Jornada jor = new Jornada(accesoBD.generarCodJornada(), Ligas.seleccionado.getId());
					accesoBD.crearJornada(jor);
					accesoBD.actualizarCodJornada(accesoBD.generarCodJornada() + 1);
					accesoBD.emparejamientos(jor);
					Jornadas jorna = new Jornadas();
					jorna.setVisible(true);
					setVisible(false);
				} else {
					int id = accesoBD.generarCodJornada();
					Date d = new Date(listae.get(listae.size() - 1).getFechaFin().getTime() + 1 * 24 * 60 * 60 * 1000);
					Jornada jor = new Jornada(id, listae.size() + 1, Ligas.seleccionado.getId(), d);
					accesoBD.crearJornada(jor);
					accesoBD.actualizarCodJornada(accesoBD.generarCodJornada() + 1);
					accesoBD.emparejamientos(jor);
					Jornadas jorna = new Jornadas();
					jorna.setVisible(true);
					setVisible(false);
				}
			}
		});
		anyadir.setBounds(456, 104, 160, 30);
		panel_1.add(anyadir);
		
		JButton eliminar = new JButton("Eliminar Jornada");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (!listaJ.isEmpty() && !list.isSelectionEmpty()) {
					int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar la jornada?");
					if (res == 0) {
						
						List<Partido> partidos = accesoBD.listaPartidos(listae.get(list.getSelectedIndex()).getCodigoJornada());
						for (Partido p : partidos) {
							accesoBD.eliminarPartido(p);
							accesoBD.eliminarClasiDePartido(p);
						}
					}
					int numJor = listae.get(list.getSelectedIndex()).getNumeroJornada();
					accesoBD.eliminarJornada(listae.get(list.getSelectedIndex()));
					accesoBD.ajustarNumerosJornada(numJor);
					
					Jornadas jorna = new Jornadas();
					jorna.setVisible(true);
					setVisible(false);
				}
				else {
					
					error.setText("Debes seleccionar primero una jornada");
				}
			}
		});
		eliminar.setBounds(456, 207, 160, 30);
		panel_1.add(eliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 365, 348);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel_1.add(scrollPane);
		
		this.setLocationRelativeTo(null);
	}

}
