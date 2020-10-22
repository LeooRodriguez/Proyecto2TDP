package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Sudoku.Celda;
import Sudoku.Juego;
import Sudoku.Reloj;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimerTask;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


/**
 * Clase GUI encargada de modelar la parte grafica de un Sudoku.
 * @author Leonardo Rodríguez
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Juego juego;
	private Celda [][] celdas;
	private JPanel panel_botones;
	private JPanel panel_comprobar;
	private Reloj reloj;
	private String[] imagenesReloj;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setBackground(Color.BLACK);


		juego=new Juego();
		if(juego.getValidoArch()) {
			this.imagenesReloj = new String[]{"/img/0r.png","/img/1r.png", "/img/2r.png","/img/3r.png","/img/4r.png","/img/5r.png","/img/6r.png","/img/7r.png","/img/8r.png","/img/9r.png"};
			setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/s.jpg")));
			setTitle("Sudoku");
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 654);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);


			panel_botones = new JPanel();
			panel_botones.setBackground(Color.WHITE);
			panel_botones.setBounds(0, 0, 506, 620);
			contentPane.add(panel_botones);
			panel_botones.setLayout(new GridLayout(9,9));
			panel_comprobar = new JPanel();
			panel_comprobar.setBounds(526, 210, 345, 391);
			panel_comprobar.setOpaque(false);
			contentPane.add(panel_comprobar);

			JButton Comprobar = new JButton("Comprobar Soluci\u00F3n");
			Comprobar.setBounds(0, 288, 340, 35);
			Comprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			panel_comprobar.setLayout(null);
			panel_comprobar.add(Comprobar);

			JButton Boton_Reglas = new JButton("Reglas");
			Boton_Reglas.setBounds(0, 356, 340, 35);
			panel_comprobar.add(Boton_Reglas);

			JButton boton_info = new JButton("Info");
			boton_info.setBounds(0, 322, 340, 35);
			panel_comprobar.add(boton_info);
			Comprobar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					boolean gano=juego.ganar();
					if(gano) {
						reloj.getTimer().cancel();
						JOptionPane.showMessageDialog(contentPane,"Felicidades!!! Has ganado","Estado del Sudoku", 1);
						dispose();
					}
					else
						JOptionPane.showMessageDialog(contentPane,"Solucion no valida, Revise el tablero","Estado del Sudoku", 2);
				}
			});

			Boton_Reglas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(Desktop.isDesktopSupported())
						try {
							Desktop.getDesktop().browse(new URI("https://www.learn-sudoku.com/sudoku-rules.html"));
						} catch (IOException | URISyntaxException e1) {
							e1.printStackTrace();
						}
				}
			});

			boton_info.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String[] opcion = new String[] {"Cancelar","GitHub"};
					int x=JOptionPane.showOptionDialog(null, "Version 1.0"+"   "+"Desarrollado por Leonardo Rodríguez", "Información adicional",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, opcion, opcion[0]);
					switch(x){
					case 1:
						if(Desktop.isDesktopSupported())
						{
							try {
								Desktop.getDesktop().browse(new URI("https://github.com/LeooRodriguez/Proyecto2TDP"));
							} catch (Exception ex) {}
						}
						break;
					default:
						break;
					}
				}
			});


			JPanel panel_timer = new JPanel();
			panel_timer.setBounds(516, 88, 356, 105);
			contentPane.add(panel_timer);
			panel_timer.setLayout(null);

			JLabel Hora1 = new JLabel("h1");
			Hora1.setForeground(Color.WHITE);
			Hora1.setFont(new Font("Tahoma", Font.PLAIN, 45));
			Hora1.setBounds(20, 0, 50, 70);
			panel_timer.add(Hora1);
			panel_timer.setOpaque(false);

			JLabel Hora2 = new JLabel("H2");
			Hora2.setFont(new Font("Tahoma", Font.PLAIN, 25));
			Hora2.setBounds(73, 0, 50, 70);
			panel_timer.add(Hora2);

			JLabel Min1 = new JLabel("Min 1");
			Min1.setFont(new Font("Tahoma", Font.PLAIN, 25));
			Min1.setBounds(130, 0, 50, 70);
			panel_timer.add(Min1);

			JLabel Min2 = new JLabel("Min 2");
			Min2.setFont(new Font("Tahoma", Font.PLAIN, 25));
			Min2.setBounds(183, 0, 50, 70);
			panel_timer.add(Min2);

			JLabel Sec1 = new JLabel("Seg 1");
			Sec1.setFont(new Font("Tahoma", Font.PLAIN, 25));
			Sec1.setBounds(240, 0, 50, 70);
			panel_timer.add(Sec1);

			JLabel Sec2 = new JLabel("Seg 2");
			Sec2.setBounds(293, 0, 50, 70);
			panel_timer.add(Sec2);

			JPanel panelImgSud = new JPanel();
			panelImgSud.setBounds(539, 11, 315, 66);
			contentPane.add(panelImgSud);
			panelImgSud.setLayout(null);
			panelImgSud.setOpaque(false);

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(GUI.class.getResource(("/img/sud.png"))));
			lblNewLabel.setBounds(10, 11, 295, 44);
			panelImgSud.add(lblNewLabel);

			JLabel L_fondo = new JLabel("New label");
			L_fondo.setIcon(new ImageIcon(GUI.class.getResource("/img/fondjpg.jpg")));
			L_fondo.setBounds(0, 0, 894, 625);
			contentPane.add(L_fondo);

			reloj= new Reloj();
			TimerTask task = new TimerTask() {
				int p=0;
				public void run() {
					String tiempo=reloj.getTime(p);
					Hora1.setText(tiempo);
					p++;

					//Guardo horas minutos y segundos de mi reloj cada segundo para actualizar las imagenes.
					String h=reloj.getStrHours();
					String m=reloj.getStrMins();
					String s=reloj.getStrSecs();

					int sec=Integer.parseInt(s);

					int sec1= (sec/10)%10;
					ImageIcon iconSec1=new ImageIcon(this.getClass().getResource(imagenesReloj[sec1]));
					Sec1.setIcon(iconSec1);
					reDimensionarL(Sec1,iconSec1);

					int sec2= sec%10;
					ImageIcon iconSec2=new ImageIcon(this.getClass().getResource(imagenesReloj[sec2]));
					Sec2.setIcon(iconSec2);
					reDimensionarL(Sec2,iconSec2);

					int min=Integer.parseInt(m);

					int min1= (min/10)%10;
					ImageIcon iconMin1=new ImageIcon(this.getClass().getResource(imagenesReloj[min1]));
					Min1.setIcon(iconMin1);
					reDimensionarL(Min1,iconMin1);

					int min2= min%10;
					ImageIcon iconMin2=new ImageIcon(this.getClass().getResource(imagenesReloj[min2]));
					Min2.setIcon(iconMin2);
					reDimensionarL(Min2,iconMin2);

					int horas=Integer.parseInt(h);

					int hora1= (horas/10)%10;
					ImageIcon iconHora1=new ImageIcon(this.getClass().getResource(imagenesReloj[hora1]));
					Hora1.setIcon(iconHora1);
					reDimensionarL(Hora1,iconHora1);

					int hora2= horas%10;
					ImageIcon iconHora2=new ImageIcon(this.getClass().getResource(imagenesReloj[hora2]));
					Hora2.setIcon(iconHora2);
					reDimensionarL(Hora2,iconHora2);





				}
			};
			iniciarReloj(task,reloj);





			inicializarBotones();	

		}

	}
	/**
	 * Inicializa cada botón del panel en base a la lógica.
	 */
	private void inicializarBotones() {
		int filas=juego.getCantFilas();
		celdas= new Celda [filas][filas];
		for(int i=0;i<filas;i++) {
			for(int j=0;j<filas;j++) {
				Celda celda= juego.getCelda(i, j);
				celdas[i][j]=celda;
				ImageIcon icono=celda.getGrafico();
				if(celda.getValor()!=null) {
					celdas[i][j].setEnabled(false);
					celdas[i][j].setBackground(Color.blue);
				}
				celda.setIcon(icono);
				celda.addComponentListener(new ComponentAdapter(){
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(celda,icono);
						celda.setIcon(icono);
						celda.setBorder(BorderFactory.createBevelBorder(1,Color.white,Color.black));
						celda.setBackground(Color.blue);
					}		
				});

				celda.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
						if(celda.isEnabled()) {
							celda.actualizar();	
							reDimensionar(celda,icono);
							juego.reset();
							juego.verificar(celda);
						}

					}
				});
				panel_botones.add(celda);
			}

		}
	}




	private void iniciarReloj(TimerTask task, Reloj r) {
		r.getTimer().schedule(task,0,1000);
	}

	/**
	 * Redimensiona el icono de un boton.
	 * @param boton, boton donde se encuentra la imagen a redimensionar.
	 * @param grafico, icono a redimensionar.
	 */
	private void reDimensionar(JButton boton, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(boton.getWidth(), boton.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			boton.repaint();
		}
	}
	/**
	 * Redimensiona el icono de un label.
	 * @param label, label donde se encuentra la imagen a redimensionar.
	 * @param grafico, icono a redimensionar.
	 */
	private void reDimensionarL(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
