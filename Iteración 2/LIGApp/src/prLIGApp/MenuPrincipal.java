package prLIGApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
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
		jugadores.setBounds(40, 120, 130, 30);
		panel.add(jugadores);
		
		JButton equipos = new JButton("Mis Equipos");
		equipos.setBounds(40, 205, 130, 30);
		panel.add(equipos);
		
		JButton ligas = new JButton("Mis Ligas");
		ligas.setBounds(40, 293, 130, 30);
		panel.add(ligas);
		
		JButton btnAjustes = new JButton("Ajustes");
		btnAjustes.setBounds(40, 385, 130, 30);
		panel.add(btnAjustes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(209, 0, 653, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Esto es");
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 62));
		lblNewLabel.setBounds(77, 64, 320, 161);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cerrar Sesi\u00F3n");
		btnNewButton.setBounds(517, 433, 126, 30);
		panel_1.add(btnNewButton);
		
		this.setLocationRelativeTo(null);
	}
}
