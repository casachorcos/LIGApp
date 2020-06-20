package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Equipo;
import prLIGAppModelo.Partido;

public class FormularioPartido extends JFrame {

	private JPanel contentPane;
	private List<Equipo> listae;
	private List<Equipo> copia;
	private List<Equipo> listajor;
	DefaultListModel listaJ;
	private List<Partido> partidoslista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioPartido frame = new FormularioPartido();
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
	public FormularioPartido() {
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
		
		JLabel lblNewLabel = new JLabel("A\u00F1adir partido");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partidos j = new Partidos();
				j.setVisible(true);
				setVisible(false);
			}
		});
		cancelar.setBounds(437, 433, 130, 30);
		panel_1.add(cancelar);
		
		
		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		listae = accesoBD.equiposLiga(Ligas.seleccionado.getId());
		listaJ = new DefaultListModel();
		partidoslista = accesoBD.listaPartidos(Jornadas.seleccionado.getCodigoJornada());
		listajor = new ArrayList<Equipo> (listae.size());
		copia = accesoBD.equiposLiga(Ligas.seleccionado.getId());
		
		JList list = new JList();
		list.setBounds(217, 75, 299, 144);
		panel_1.add(list);
		list.setModel(listaJ);
		
		JList list_1 = new JList();
		list_1.setBounds(217, 230, 299, 144);
		panel_1.add(list_1);
		
		list_1.setModel(listaJ);
		
		for (int i = 0; i < partidoslista.size(); i++) {
			
			int id1 = partidoslista.get(i).getIdLocal();
			Equipo e1 = new Equipo(id1, null);
			int id2 = partidoslista.get(i).getIdVisitante();
			Equipo e2 = new Equipo(id2, null);
			
			listajor.add(e1);
			listajor.add(e2);
		}
		
		for (Equipo e : listae) {
			
			for (int i = 0; i < listajor.size(); i++) {
				
				if ( e.equals(listajor.get(i))) {
					copia.remove(e);
				}	
			}
		}
		
		for (Equipo e : copia) {
			
			listaJ.addElement(e.toString());
		}
		
		
		JLabel lblSeleccinDeEquipo = new JLabel("Selecci\u00F3n de equipo local:");
		lblSeleccinDeEquipo.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblSeleccinDeEquipo.setBounds(33, 99, 176, 30);
		panel_1.add(lblSeleccinDeEquipo);
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		error.setBounds(151, 400, 461, 14);
		panel_1.add(error);
		error.setForeground(Color.RED);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty() && !list_1.isSelectionEmpty() && list.getSelectedIndex() != list_1.getSelectedIndex()) {
					accesoBD.crearPartido(new Partido(accesoBD.generarCodPartido(), copia.get(list.getSelectedIndex()).getId(), copia.get(list_1.getSelectedIndex()).getId(), Jornadas.seleccionado.getCodigoJornada()));
					accesoBD.actualizarCodPartido(accesoBD.generarCodPartido() + 1);
					Partidos j = new Partidos();
					j.setVisible(true);
					setVisible(false);
				} else {
					error.setText("Se deben seleccionar dos equipos distintos");
				}
			}
		});
		aceptar.setBounds(120, 433, 130, 30);
		panel_1.add(aceptar);
		
		JLabel lblSeleccinDeEquipo_1 = new JLabel("Selecci\u00F3n de equipo visitante:");
		lblSeleccinDeEquipo_1.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblSeleccinDeEquipo_1.setBounds(25, 231, 176, 30);
		panel_1.add(lblSeleccinDeEquipo_1);
		
		this.setLocationRelativeTo(null);
	}
}
