package prLIGApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JLabel lblLigapp;
	private JButton btnIniciarSesin;
	private JLabel lblError;
	private JPasswordField txtContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(60, 179, 113));
		panel.setBounds(0, 0, 507, 307);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(175, 93, 178, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormularioRegistro f = new FormularioRegistro();
				f.setVisible(true);
				setVisible(false);
			}
		});
		btnRegistrarse.setBounds(354, 273, 132, 23);
		panel.add(btnRegistrarse);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(63, 96, 102, 14);
		panel.add(lblUsuario);
		
		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(63, 139, 102, 14);
		panel.add(lblContrasea);
		
		lblLigapp = new JLabel("LIGApp");
		lblLigapp.setFont(new Font("Arial Black", Font.BOLD, 28));
		lblLigapp.setBounds(187, 11, 166, 52);
		panel.add(lblLigapp);
		
		setLocationRelativeTo(null);
		
		btnIniciarSesin = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion accesoBD;
				accesoBD = ConexionJDBC.getInstance();
				String usuario = txtUsuario.getText();
				String password = String.valueOf(txtContrasea.getPassword());
				Usuario user = new Usuario(usuario, null, password);
				List<Usuario> usuarios = accesoBD.listaUsuarios();
				if (usuarios.contains(user)) {
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					setVisible(false);
				} else {
					lblError.setText("Nombre de usuario o contraseña incorrecto");
				}
				txtUsuario.setText("");
				txtContrasea.setText("");
			}
		});
		btnIniciarSesin.setBounds(185, 220, 155, 31);
		panel.add(btnIniciarSesin);
		
		lblError = new JLabel("", SwingConstants.CENTER);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(100, 178, 317, 14);
		panel.add(lblError);
		
		txtContrasea = new JPasswordField();
		txtContrasea.setBounds(175, 136, 178, 20);
		panel.add(txtContrasea);
	}
}
