package GUI;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Iniciar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iniciar frame = new Iniciar();
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
	public Iniciar() {
		setTitle("Sudoku");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/s.jpg")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 444, 271);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel L_icon = new JLabel("");
		L_icon.setIcon(new ImageIcon(Iniciar.class.getResource("/img/logo.jpg")));
		L_icon.setBounds(171, 47, 92, 111);
		panel.add(L_icon);

		JButton B_iniciar = new JButton("Iniciar");
		B_iniciar.setBounds(122, 169, 189, 23);
		panel.add(B_iniciar);

		JLabel L_fondo = new JLabel("");
		L_fondo.setIcon(new ImageIcon(Iniciar.class.getResource("/img/Fondo_inicio.jpg")));
		L_fondo.setBounds(0, 0, 444, 271);
		panel.add(L_fondo);


		B_iniciar.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				GUI g=new GUI();//Instancio una Gui para hacerla visible al momento que el usuario presione le boton Iniciar.
				contentPane.setVisible(false);
				g.setVisible(true);
				dispose();

			}
		});
	}
}
