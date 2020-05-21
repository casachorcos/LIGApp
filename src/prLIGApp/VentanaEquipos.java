package prLIGApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class VentanaEquipos extends JFrame {

	private JPanel contentPane;
	
	DefaultListModel listaJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEquipos frame = new VentanaEquipos();
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
	public VentanaEquipos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 346);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(60, 179, 113));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Conexion accesoBD;
		accesoBD = ConexionJDBC.getInstance();

		List<Equipo> equipos = accesoBD.listaEquipos();

		JList list = new JList();
		list.setBounds(40, 69, 286, 214);
		contentPane.add(list);
		listaJ = new DefaultListModel();
		list.setModel(listaJ);

		for (Equipo e : equipos) {
			listaJ.addElement(e.toString());
		}

		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormularioEquipo f = new FormularioEquipo();
				f.setVisible(true);
				setVisible(false);
			}
		});
		btnAadir.setBounds(374, 115, 89, 23);
		contentPane.add(btnAadir);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar el equipo?");
				if (res == 0) {
					int pos = list.getSelectedIndex();
					String elemento = listaJ.get(pos).toString();

					try (Scanner sc = new Scanner(elemento)) {
						int id = sc.nextInt();
						accesoBD.eliminarEquipo(new Equipo(id, null));
						listaJ.remove(pos);

					}
				}

			}
		});
		btnEliminar.setBounds(374, 185, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Men\u00FA");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(40, 24, 89, 23);
		contentPane.add(btnVolver);

		setLocationRelativeTo(null);
	}
}
