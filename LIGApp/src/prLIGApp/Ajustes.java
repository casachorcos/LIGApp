package prLIGApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Ajustes extends JFrame {

	private JPanel contentPane;
	private JTextField cont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajustes frame = new Ajustes();
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
	public Ajustes() {
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
		
		JButton jugadores = new JButton("Mis Jugadores");
		jugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jugadores j = new jugadores();
				j.setVisible(true);
				setVisible(false);
			}
		});
		jugadores.setBounds(40, 141, 130, 30);
		panel.add(jugadores);
		
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
			public void actionPerformed(ActionEvent arg0) {
				Ligas ligas = new Ligas();
				ligas.setVisible(true);
				setVisible(false);
			}
		});
		ligas.setBounds(40, 299, 130, 30);
		panel.add(ligas);
		
		JButton btnAjustes = new JButton("Ajustes");
		btnAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ajustes ajus = new Ajustes();
				ajus.setVisible(true);
				setVisible(false);
			}
		});
		btnAjustes.setBounds(40, 385, 130, 30);
		panel.add(btnAjustes);
		
		JButton button = new JButton("");
		button.setForeground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
				setVisible(false);
			}
		});
		button.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 20));
		button.setBackground(new Color(51, 153, 102));
		button.setBounds(10, 47, 190, 35);
		button.setText(Inicio.nombreUsuario);
		button.setBorder(null);
	    button.setBorderPainted(false);
	    button.setContentAreaFilled(false);
		panel.add(button);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(209, 0, 653, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAjustes = new JLabel("Ajustes");
		lblAjustes.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 33));
		lblAjustes.setBounds(25, 21, 438, 53);
		panel_1.add(lblAjustes);
		
		JLabel lblNewLabel = new JLabel("Nueva contrase\u00F1a:");
		lblNewLabel.setBounds(57, 149, 140, 14);
		panel_1.add(lblNewLabel);
		
		cont = new JTextField();
		cont.setBounds(196, 146, 344, 20);
		panel_1.add(cont);
		cont.setColumns(10);
		
		JLabel lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a:");
		lblCambiarContrasea.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		lblCambiarContrasea.setBounds(47, 85, 438, 53);
		panel_1.add(lblCambiarContrasea);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion acceso;
				acceso = ConexionJDBC.getInstance();
				acceso.cambiarContrasena(Inicio.nombreUsuario, cont.getText());
				cont.setText("");
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(260, 201, 89, 23);
		panel_1.add(btnNewButton);
		
		this.setLocationRelativeTo(null);
	}
}
