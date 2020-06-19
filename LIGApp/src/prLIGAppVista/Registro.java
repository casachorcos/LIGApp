package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Usuario;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField correo;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(0, 0, 316, 474);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 153, 102));
		panel_2.setBounds(0, 0, 316, 46);
		panel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(51, 153, 102));
		panel_3.setBounds(0, 428, 316, 46);
		panel.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/prImagenes/3.png")));
		lblNewLabel.setBounds(54, 45, 210, 108);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(41, 164, 46, 14);
		panel.add(lblNewLabel_1);
		
		JTextField usuario = new JTextField();
		usuario.setBounds(97, 162, 186, 20);
		panel.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblContasea = new JLabel("Contrase\u00F1a:");
		lblContasea.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblContasea.setBounds(19, 255, 68, 14);
		panel.add(lblContasea);
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		error.setForeground(Color.RED);
		error.setBounds(19, 350, 264, 14);
		panel.add(error);
		
		
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio inic = new Inicio();
				inic.setVisible(true);
				setVisible(false);
			}
		});
		cancelar.setBounds(95, 385, 118, 23);
		panel.add(cancelar);
		
		
		
		password = new JPasswordField();
		password.setBounds(97, 253, 186, 20);
		panel.add(password);
		
		correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(97, 208, 186, 20);
		panel.add(correo);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblCorreo.setBounds(41, 211, 46, 14);
		panel.add(lblCorreo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(315, 0, 547, 474);
		panel_1.setBackground(new Color(51, 153, 102));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(this.getClass().getResource("/prImagenes/4.png")));
		lblNewLabel_2.setBounds(49, 11, 475, 452);
		panel_1.add(lblNewLabel_2);
		
		ImageIcon image = new ImageIcon("1.png");
		JLabel titulo = new JLabel(image);
		titulo.setIcon(new ImageIcon("1.png"));
		
		JButton aceptar = new JButton("Crear");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion accesoBD;
				accesoBD = ConexionJDBC.getInstance();
				String usuar = usuario.getText();
				String cor = correo.getText();
				String pass = String.valueOf(password.getPassword());
				Usuario user = new Usuario(usuar, cor, pass);
				List<Usuario> usuarios = accesoBD.listaUsuarios();
				
				if (estaUsuario(usuarios, usuar) || usuar.isEmpty()) {
					error.setText("Este usuario ya existe o no es válido");
				} else {
					accesoBD.crearUsuario(user);
					Inicio i = new Inicio();
					i.setVisible(true);
					setVisible(false);
				}
				usuario.setText("");
				correo.setText("");
				password.setText("");
			}
		});
		aceptar.setBounds(109, 305, 89, 23);
		panel.add(aceptar);
		
		this.setLocationRelativeTo(null);
	}
}
