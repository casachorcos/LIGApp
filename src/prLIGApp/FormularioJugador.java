package prLIGApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioJugador extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(40, 30, 96, 14);
		panel.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(40, 55, 210, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(40, 98, 60, 14);
		panel.add(lblEdad);
		
		textField_1 = new JTextField();
		textField_1.setBounds(40, 123, 60, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion accesoBD;
				accesoBD = ConexionJDBC.getInstance();
				String nombre = textField.getText();
				int edad = Integer.parseInt(textField_1.getText());
				if (!nombre.isEmpty()) {
					accesoBD.crearJugador(new Jugador(accesoBD.generarID(), nombre, edad));
				}
				VentanaJugadores v = new VentanaJugadores();
				v.setVisible(true);
				setVisible(false);
			}
		});
		btnConfirmar.setBounds(70, 252, 102, 23);
		panel.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaJugadores v = new VentanaJugadores();
				v.setVisible(true);
				setVisible(false);
			}
		});
		btnCancelar.setBounds(328, 252, 102, 23);
		panel.add(btnCancelar);
		
		setLocationRelativeTo(null);
	}
}
