package prLIGApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

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
		
		JButton btnJugadores = new JButton("Jugadores");
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaJugadores v1 = new VentanaJugadores();
				v1.setVisible(true);
				setVisible(false);
			}
		});
		btnJugadores.setBounds(188, 76, 119, 23);
		panel.add(btnJugadores);
		
		JButton btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaEquipos v2 = new VentanaEquipos();
				v2.setVisible(true);
				setVisible(false);
			}
		});
		btnEquipos.setBounds(188, 143, 119, 23);
		panel.add(btnEquipos);
		
		JButton btnLigas = new JButton("Ligas");
		btnLigas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLigas v3 = new VentanaLigas();
				v3.setVisible(true);
				setVisible(false);
			}
		});
		btnLigas.setBounds(188, 214, 119, 23);
		panel.add(btnLigas);
		
		JLabel lblMenPrincipal = new JLabel("Men\u00FA principal");
		lblMenPrincipal.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblMenPrincipal.setBounds(188, 21, 165, 34);
		panel.add(lblMenPrincipal);
		
		setLocationRelativeTo(null);
	}
}
