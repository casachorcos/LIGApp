package prLIGAppVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Codigo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Codigo frame = new Codigo();
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
	public Codigo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 77, 566, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Inicio.codigo);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(227, 135, 118, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblComparteConEste = new JLabel("Comparte con este c\u00F3digo:");
		lblComparteConEste.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComparteConEste.setBounds(41, 32, 286, 23);
		contentPane.add(lblComparteConEste);
		
		this.setLocationRelativeTo(null);
	}
}
