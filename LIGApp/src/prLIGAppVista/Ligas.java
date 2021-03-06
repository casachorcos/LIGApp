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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import prLIGAppClases.Liga;
import prLIGAppConexion.Conexion;
import prLIGAppConexion.ConexionJDBC;

public class Ligas extends JFrame {

	private JPanel contentPane;
	private List<Liga> listae;
	public static Liga seleccionado;
	DefaultListModel listaJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ligas frame = new Ligas();
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
	public Ligas() {
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
				Ligas ligas = new Ligas();
				ligas.setVisible(true);
				setVisible(false);
			}
		});
		ligas.setBounds(40, 299, 130, 30);
		panel.add(ligas);
		
		JButton btnAjustes = new JButton("Ajustes");
		btnAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajustes ajustes = new Ajustes();
				ajustes.setVisible(true);
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
		
		JButton anyadir = new JButton("A\u00F1adir Liga");
		anyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormularioLiga formu = new FormularioLiga();
				formu.setVisible(true);
				setVisible(false);
			}
		});
		anyadir.setBounds(450, 85, 152, 30);
		panel_1.add(anyadir);
		
		JLabel lblNewLabel = new JLabel("Mis Ligas");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		listae = accesoBD.usuario_liga(Inicio.nombreUsuario);
		listaJ = new DefaultListModel();
		
		JList list = new JList();
		list.setBounds(1, 1, 363, 346);
		list.setModel(listaJ);
		
		for (Liga j : listae) {
			listaJ.addElement(j.toString());
		}
		
		JButton eliminar = new JButton("Eliminar Liga");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listaJ.isEmpty() && !list.isSelectionEmpty()) {
					int res = JOptionPane.showConfirmDialog(null, "�Est� seguro de que desea borrar la liga?");
					if (res == 0) {
						int pos = list.getSelectedIndex();
						Liga j = listae.get(pos);
						accesoBD.eliminarEquipoEnLiga(j);
						accesoBD.eliminarLiga_Us(j, Inicio.nombreUsuario);
						listaJ.remove(pos);
					}
				}else {
					error.setText("Debes seleccionar primero una liga");
				}

			}
		});
		eliminar.setBounds(450, 191, 152, 30);
		panel_1.add(eliminar);
		
		JButton ver = new JButton("Ver Liga");
		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					seleccionado = listae.get(list.getSelectedIndex());
					DatosLiga datos = new DatosLiga();
					datos.setVisible(true);
					setVisible(false);
				}else {
					error.setText("Debes seleccionar primero una liga");
				}
			}
		});
		ver.setBounds(450, 297, 152, 30);
		panel_1.add(ver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 365, 348);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel_1.add(scrollPane);
		
		JButton btnCompartirLiga = new JButton("Compartir Liga");
		btnCompartirLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!list.isSelectionEmpty()) {
					seleccionado = listae.get(list.getSelectedIndex());
					Inicio.codigo = seleccionado.code(Inicio.nombreUsuario);
					Codigo cod = new Codigo();
					cod.setVisible(true);
				}else {
					error.setText("Debes seleccionar primero una liga");
				}
			}
		});
		btnCompartirLiga.setBounds(450, 403, 152, 30);
		panel_1.add(btnCompartirLiga);
		
		this.setLocationRelativeTo(null);
	}

}
