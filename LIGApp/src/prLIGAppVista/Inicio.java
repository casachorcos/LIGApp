package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prLIGAppControlador.Conexion;
import prLIGAppControlador.ConexionJDBC;
import prLIGAppModelo.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField password;
	public static String nombreUsuario = "";

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
		lblNewLabel_1.setBounds(41, 206, 46, 14);
		panel.add(lblNewLabel_1);
		
		JTextField usuario = new JTextField();
		usuario.setBounds(97, 204, 186, 20);
		panel.add(usuario);
		usuario.setColumns(10);
		
		JLabel lblContasea = new JLabel("Contrase\u00F1a:");
		lblContasea.setFont(new Font("Gadugi", Font.PLAIN, 12));
		lblContasea.setBounds(19, 246, 68, 14);
		panel.add(lblContasea);
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		error.setForeground(Color.RED);
		error.setBounds(19, 350, 264, 14);
		panel.add(error);
		
		JButton acceder = new JButton("Acceder");
		acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion accesoBD;
				accesoBD = ConexionJDBC.getInstance();
				String user = usuario.getText();
				String pass = String.valueOf(password.getPassword());
				Usuario u = new Usuario(user, null, pass);
				List<Usuario> usuarios = accesoBD.listaUsuarios();
				if (usuarios.contains(u)) {
					nombreUsuario = u.getNombre();
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					setVisible(false);
				} else {
					error.setText("Nombre de usuario o contraseña incorrecto");
				}
				usuario.setText("");
				password.setText("");
			}
		});
		acceder.setBounds(109, 305, 89, 23);
		panel.add(acceder);
		
		JButton registro = new JButton("Registrarse");
		registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro regis = new Registro();
				regis.setVisible(true);
				setVisible(false);
			}
		});
		registro.setBounds(95, 385, 118, 23);
		panel.add(registro);
		
		
		
		password = new JPasswordField();
		password.setBounds(97, 244, 186, 20);
		panel.add(password);
		
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
		
		this.setLocationRelativeTo(null);
		
		panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
            	if(e.getKeyCode()==KeyEvent.VK_ENTER){
            		Conexion accesoBD;
    				accesoBD = ConexionJDBC.getInstance();
    				String user = usuario.getText();
    				String pass = String.valueOf(password.getPassword());
    				Usuario u = new Usuario(user, null, pass);
    				List<Usuario> usuarios = accesoBD.listaUsuarios();
    				if (usuarios.contains(u)) {
    					nombreUsuario = u.getNombre();
    					MenuPrincipal menu = new MenuPrincipal();
    					menu.setVisible(true);
    					setVisible(false);
    				} else {
    					error.setText("Nombre de usuario o contraseï¿½a incorrecto");
    				}
    				usuario.setText("");
    				password.setText("");
                }
            	
            }
         });
	}
}
