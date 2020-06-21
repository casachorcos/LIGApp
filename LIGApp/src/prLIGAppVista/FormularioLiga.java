package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import prLIGAppClases.Jugador;
import prLIGAppClases.Liga;
import prLIGAppConexion.Conexion;
import prLIGAppConexion.ConexionJDBC;

public class FormularioLiga extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField textoCodigo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioLiga frame = new FormularioLiga();
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
	public FormularioLiga() {
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
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		error.setBounds(282, 25, 333, 30);
		panel_1.add(error);
		error.setForeground(Color.RED);
		
		JLabel errorCod = new JLabel("", SwingConstants.CENTER);
		errorCod.setBounds(205, 345, 289, 20);
		panel_1.add(errorCod);
		errorCod.setForeground(Color.RED);
		
		JLabel lblNewLabel = new JLabel("A\u00F1adir Liga");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblNewLabel.setBounds(25, 11, 438, 53);
		panel_1.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNombre.setBounds(92, 124, 95, 14);
		panel_1.add(lblNombre);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ligas j = new Ligas();
				j.setVisible(true);
				setVisible(false);
			}
		});
		cancelar.setBounds(387, 414, 130, 30);
		panel_1.add(cancelar);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!nombre.getText().isEmpty()) {
					Conexion accesoBD;
					accesoBD = ConexionJDBC.getInstance();
					accesoBD.crearLiga_Usuario(new Liga(accesoBD.generarID(), nombre.getText()), Inicio.nombreUsuario);
					Ligas ligas = new Ligas();
					ligas.setVisible(true);
					setVisible(false);
				}else {
					error.setText("Debes rellenar el campo");
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
							for(Liga x : accesoBD.usuario_liga(Inicio.nombreUsuario)) {
								if(x.getId() == id) {
									noesta = false;
								}
							}
							if(noesta) {
								for(Liga x : accesoBD.usuario_liga(user)) {
									if(x.getId() == id) {
										accesoBD.crearLiga_Usuario2(x, Inicio.nombreUsuario);
										anadido = true;
									}
								}
								if(!anadido) {
									errorCod.setText("Código inválido");
								} else {
									Ligas teams = new Ligas();
									setVisible(false);
									teams.setVisible(true);
									
								}
							}else {
								errorCod.setText("Esa liga ya ha sido añadida");
							}
						
						}else {
							errorCod.setText("Código inválido");
						}
					}else {
						errorCod.setText("Código inválido");
					}
				} catch (NumberFormatException excp) {
					errorCod.setText("Código inválido");
				}
			}
		});
		btnAadirConCdigo.setBounds(40, 293, 147, 30);
		panel_1.add(btnAadirConCdigo);
		
		textoCodigo = new JTextField();
		textoCodigo.setBounds(206, 298, 288, 19);
		panel_1.add(textoCodigo);
		textoCodigo.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(205, 123, 289, 20);
		panel_1.add(nombre);
		nombre.setColumns(10);
		
		this.setLocationRelativeTo(null);
	}
}
