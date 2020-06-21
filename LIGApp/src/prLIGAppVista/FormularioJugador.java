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
import javax.swing.JComboBox;
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
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class FormularioJugador extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField edad;
	DefaultListModel listaJ;
	private List<Equipo> listae;
	private JTextField textoCodigo;

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
		panel_1.setBounds(206, 0, 653, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		error.setBounds(282, 25, 333, 30);
		panel_1.add(error);
		error.setForeground(Color.RED);
		
		JLabel errorCod = new JLabel("", SwingConstants.CENTER);
		errorCod.setBounds(206, 383, 289, 20);
		panel_1.add(errorCod);
		errorCod.setForeground(Color.RED);
		
		JLabel lblNewLabel = new JLabel("A�adir Jugador");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(46, 70, 95, 14);
		panel_1.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(206, 68, 289, 20);
		panel_1.add(nombre);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblEdad.setBounds(46, 102, 107, 14);
		panel_1.add(lblEdad);
		
		edad = new JTextField();
		edad.setColumns(10);
		edad.setBounds(206, 100, 289, 20);
		panel_1.add(edad);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jugadores j = new Jugadores();
				j.setVisible(true);
				setVisible(false);
			}
		});
		cancelar.setBounds(386, 414, 130, 30);
		panel_1.add(cancelar);
		
		listaJ = new DefaultListModel();
		
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(207, 166, 289, 167);
		panel_1.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(listaJ);
		list.setLayoutOrientation(JList.VERTICAL);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(206, 132, 289, 20);
		panel_1.add(comboBox);
		comboBox.addItem("Portero/a");
		comboBox.addItem("Defensa");
		comboBox.addItem("Centrocampista");
		comboBox.addItem("Delantero/a");
				
		JLabel lblPosicin = new JLabel("Posici\u00F3n:");
		lblPosicin.setBounds(47, 135, 95, 14);
		panel_1.add(lblPosicin);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.isSelectionEmpty() && !nombre.getText().isEmpty() && !edad.getText().isEmpty()) {
					Conexion accesoBD;
					accesoBD = ConexionJDBC.getInstance();
					Jugador jugador = new Jugador(accesoBD.generarID(), nombre.getText(), Integer.parseInt(edad.getText()));
					jugador.setRol(comboBox.getSelectedItem().toString());
					accesoBD.crearJugador_Usuario(jugador, Inicio.nombreUsuario);
					Jugadores j = new Jugadores();
					j.setVisible(true);
					setVisible(false);
				} else if (!list.isSelectionEmpty() && !nombre.getText().isEmpty() && !edad.getText().isEmpty()){
					Conexion accesoBD;
					accesoBD = ConexionJDBC.getInstance();
					int id = accesoBD.generarID();
					accesoBD.crearJugador_Usuario(new Jugador(id, nombre.getText(), Integer.parseInt(edad.getText())), Inicio.nombreUsuario);
					Equipo equipo = listae.get(list.getSelectedIndex());
					accesoBD.crearJugadorEnEquipo(new Jugador(id, nombre.getText(), Integer.parseInt(edad.getText())), equipo);
					Jugadores j = new Jugadores();
					j.setVisible(true);
					setVisible(false);
				} else {
					error.setText("Debes rellenar los campos");
				}
			}
		});
		aceptar.setBounds(111, 414, 130, 30);
		panel_1.add(aceptar);
		
		
		
		JButton btnAadirConCdigo = new JButton("A\u00F1adir con c\u00F3digo");
		btnAadirConCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String codtext = textoCodigo.getText();
					if(!codtext.isEmpty()) {
						
						//Decodifica el codigo y lo guarda en userid
						int i = 0;
						boolean cero = false, valido = false;
						String user = "", aux;
						int id;
						char letra;
						while(i < codtext.length() && !cero) {
							aux = codtext.substring(i,i+2);
							if(aux.equalsIgnoreCase("00")) {
								cero = true;
								valido = true;
							}else {
								letra = (char) (Integer.parseInt(aux) + 30);
								user += letra;
								i += 2;
							}
							
						}
						id = (Integer.parseInt(codtext.substring(i, codtext.length())) - 4)/3;

						if(valido) {
							Conexion accesoBD;
							accesoBD = ConexionJDBC.getInstance();
							boolean noesta = true;
							boolean anadido = false;
							for(Jugador x : accesoBD.usuario_jugador(Inicio.nombreUsuario)) {
								if(x.getId() == id) {
									noesta = false;
								}
							}
							if(noesta) {
								for(Jugador x : accesoBD.usuario_jugador(user)) {
									if(x.getId() == id) {
										accesoBD.crearJugador_Usuario2(x, Inicio.nombreUsuario);
										anadido = true;
									}
								}
								if(!anadido) {
									errorCod.setText("C�digo inv�lido");
								} else {
									Jugadores teams = new Jugadores();
									setVisible(false);
									teams.setVisible(true);
									
								}
							}else {
								errorCod.setText("Ese jugador ya ha sido a�adido");
							}
						
						}else {
							errorCod.setText("C�digo inv�lido");
						}
					}else {
						errorCod.setText("C�digo inv�lido");
					}
				} catch (NumberFormatException excp) {
					errorCod.setText("C�digo inv�lido");
				}
			}
		});
		btnAadirConCdigo.setBounds(42, 355, 147, 30);
		panel_1.add(btnAadirConCdigo);
		
		textoCodigo = new JTextField();
		textoCodigo.setBounds(206, 360, 288, 19);
		panel_1.add(textoCodigo);
		textoCodigo.setColumns(10);
		
		this.setLocationRelativeTo(null);
	}
}
