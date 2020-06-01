package prLIGApp;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Partidos extends JFrame {

	private JPanel contentPane;
	private List<Partido> listae = new ArrayList<Partido>();
	private List<Partido> partidoslista;
	DefaultListModel listaJ;
	public static Partido seleccionadoP;
	public static String seleccionadoS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Partidos frame = new Partidos();
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
	public Partidos() {
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
		
		JLabel lblNewLabel = new JLabel(Jornadas.nombreJornada);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		partidoslista = accesoBD.listaPartidos(Jornadas.seleccionado.getCodigoJornada());
		listaJ = new DefaultListModel();
		
		JList list = new JList();
		list.setBounds(25, 81, 396, 382);
		panel_1.add(list);
		list.setModel(listaJ);
		
		List<Equipo> teams = accesoBD.usuario_equipo(Inicio.nombreUsuario);
		List<String> enfren = new ArrayList<String>();
		
		for (int i = 0; i < partidoslista.size(); i++)  {
			String res = null;
			String n1 = null;
			String n2 = null;
			int id1 = partidoslista.get(i).getIdLocal();
			Equipo e1 = new Equipo(id1, null);
			int id2 = partidoslista.get(i).getIdVisitante();
			Equipo e2 = new Equipo(id2, null);
			for (Equipo equi : teams) {
				if (equi.equals(e1)) {
					n1 = equi.getNombre();
				}
				
				if (equi.equals(e2)) {
					n2 = equi.getNombre();
				}
			}
			
			if (n1 != null && n2 != null) {
				enfren.add(n1 + " - " + n2);
			}
		}
		
		for (String j : enfren) {
			listaJ.addElement(j);
		}
		
		JButton ver = new JButton("Resultado");
		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					seleccionadoP = partidoslista.get(list.getSelectedIndex());
					seleccionadoS = (String) listaJ.get(list.getSelectedIndex());
					DatosPartidos dat = new DatosPartidos();
					dat.setVisible(true);
					setVisible(false);
				}
			}
		});
		ver.setBounds(472, 362, 130, 30);
		panel_1.add(ver);
		
		JButton anyadir = new JButton("A\u00F1adir");
		anyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormularioPartido formu = new FormularioPartido();
				formu.setVisible(true);
				setVisible(false);
			}
		});
		anyadir.setBounds(472, 132, 130, 30);
		panel_1.add(anyadir);
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (!listaJ.isEmpty() && !list.isSelectionEmpty()) {
					int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar el partido?");
					if (res == 0) {
						accesoBD.eliminarPartido(partidoslista.get(list.getSelectedIndex()));
					}
					Partidos jorna = new Partidos();
					jorna.setVisible(true);
					setVisible(false);
				}
			}
		});
		eliminar.setBounds(472, 252, 130, 30);
		panel_1.add(eliminar);
		
		this.setLocationRelativeTo(null);
	}

}
