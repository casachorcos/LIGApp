package prLIGApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FormularioRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JButton btnIniciarSesin;
	private JLabel lblError;
	private JPasswordField txtContrasea;
	private JTextField textField;
	private JLabel lblRegistro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioRegistro frame = new FormularioRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private boolean estaUsuario(List<Usuario> usuarios, String usuario) {
		for (Usuario u : usuarios) {
			if (u.getNombre().equals(usuario)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Create the frame.
	 */
	public FormularioRegistro() {
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
		txtUsuario.setBounds(163, 93, 178, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(175, 74, 155, 14);
		panel.add(lblUsuario);
		
		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(175, 178, 155, 14);
		panel.add(lblContrasea);
		
		
		setLocationRelativeTo(null);
		
		btnIniciarSesin = new JButton("Crear");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion accesoBD;
				accesoBD = ConexionJDBC.getInstance();
				String usuario = txtUsuario.getText();
				String correo = textField.getText();
				String password = String.valueOf(txtContrasea.getPassword());
				Usuario user = new Usuario(usuario, correo, password);
				List<Usuario> usuarios = accesoBD.listaUsuarios();
				
				if (estaUsuario(usuarios, usuario) || usuario.isEmpty()) {
					lblError.setText("Este usuario ya existe o no es valido");
				} else {
					accesoBD.crearUsuario(new Usuario(usuario, correo, password));
					Inicio i = new Inicio();
					i.setVisible(true);
					setVisible(false);
				}
				txtUsuario.setText("");
				textField.setText("");
				txtContrasea.setText("");
			}
		});
		btnIniciarSesin.setBounds(175, 240, 155, 31);
		panel.add(btnIniciarSesin);
		
		lblError = new JLabel("", SwingConstants.CENTER);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(96, 282, 317, 14);
		panel.add(lblError);
		
		txtContrasea = new JPasswordField();
		txtContrasea.setBounds(163, 194, 178, 20);
		panel.add(txtContrasea);
		
		JLabel lblCorreoElectrnico = new JLabel("Correo electr\u00F3nico:");
		lblCorreoElectrnico.setBounds(175, 124, 155, 14);
		panel.add(lblCorreoElectrnico);
		
		textField = new JTextField();
		textField.setBounds(163, 147, 178, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio i = new Inicio();
				i.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(26, 21, 89, 23);
		panel.add(btnVolver);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblRegistro.setBounds(369, 15, 117, 31);
		panel.add(lblRegistro);
	}
}
