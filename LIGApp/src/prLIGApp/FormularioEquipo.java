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

public class FormularioEquipo extends JFrame {

	private JPanel contentPane;
	DefaultListModel listaJ;
	private List<Liga> listae;
	private JTextField nombre;
	private JTextField nombreequipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioEquipo frame = new FormularioEquipo();
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
	public FormularioEquipo() {
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
		
		JLabel lblNewLabel = new JLabel("A\u00F1adir Equipo");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(47, 125, 95, 14);
		panel_1.add(lblNombre);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipos j = new Equipos();
				j.setVisible(true);
				setVisible(false);
			}
		});
		cancelar.setBounds(386, 414, 130, 30);
		panel_1.add(cancelar);
		
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		listae = accesoBD.usuario_liga(Inicio.nombreUsuario);
		listaJ = new DefaultListModel();
		
		JList list = new JList();
		list.setBounds(199, 205, 299, 172);
		panel_1.add(list);
		list.setModel(listaJ);
		
		for (Liga e : listae) {
			listaJ.addElement(e.toString());
		}
		
		JLabel lblSeleccinDeEquipo = new JLabel("Selecci\u00F3n de liga:");
		lblSeleccinDeEquipo.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblSeleccinDeEquipo.setBounds(35, 230, 154, 14);
		panel_1.add(lblSeleccinDeEquipo);
		
		nombreequipo = new JTextField();
		nombreequipo.setBounds(199, 123, 297, 20);
		panel_1.add(nombreequipo);
		nombreequipo.setColumns(10);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = nombreequipo.getText();
				if (list.isSelectionEmpty()) {
					Conexion accesoBD;
					accesoBD = ConexionJDBC.getInstance();
					accesoBD.crearEquipo_Usuario(new Equipo(accesoBD.generarID(), nom), Inicio.nombreUsuario);
					Equipos j = new Equipos();
					j.setVisible(true);
					setVisible(false);
				} else {
					Conexion accesoBD;
					accesoBD = ConexionJDBC.getInstance();
					int id = accesoBD.generarID();
					accesoBD.crearEquipo_Usuario(new Equipo(id, nom), Inicio.nombreUsuario);
					accesoBD.crearEquipoEnLiga(new Equipo(id, nom), listae.get(list.getSelectedIndex()));
					Equipos j = new Equipos();
					j.setVisible(true);
					setVisible(false);
				}
			}
		});
		aceptar.setBounds(111, 414, 130, 30);
		panel_1.add(aceptar);
		
		this.setLocationRelativeTo(null);
	}
}
