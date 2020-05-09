package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import InputOutput.IoReservas;
import estaticos.Reserva;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;


@SuppressWarnings({ "serial", "unused","unchecked","rawtypes" })
public class AsignarHabitacion extends JFrame {
	private Point initialClick;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNombreApp;
	private JLabel lblCerrar;
	private JLabel lblMinimizar;
	private JLabel lblMaximizar;
	private JTextArea txtrHabitacion;
	private JComboBox comboBoxHabitacion;
	private ArrayList<String> cont;
	private JButton btnNewButton;
	private String[] vDui = {"101","102","103","104","105","201","202","203","204","205","301","302","303","304","305"};
	private String[] vDb = {"106","107","108","109","110","206","207","208","209","210","306","307","308","309","310"};
	private String[] vTri = {"111","112","113","114","115","211","212","213","214","215","311","312","313","314","315"};
	private String[] vSuite = {"116","117","118","119","120","216","217","218","219","220","316","317","318","319","320"};
	private ArrayList<String> habDisponiblesArrayList = new ArrayList<String>();
	private String[] habDisponibles;
	private DefaultComboBoxModel modeloCombox;
	private IoReservas io = new IoReservas();
	private Reserva reserva;
	private String[] fechEntrada;
	private String[] fechSalida;
	private boolean existe;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarHabitacion frame = new AsignarHabitacion();
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
	public AsignarHabitacion() {
		initApp();
	}
	public AsignarHabitacion(Reserva res) {
		initApp();
		reserva = res;
		asignarComboBox();
	}
	private void asignarComboBox() {
		fechEntrada = reserva.getFechaEntrada().split("/");
		int diaEntrada = Integer.parseInt(fechEntrada[0]);
		int mesEntrada = Integer.parseInt(fechEntrada[1]);
		int anoEntrada = Integer.parseInt(fechEntrada[2]);
		fechSalida = reserva.getFechaSalida().split("/");
		int diaSalida = Integer.parseInt(fechSalida[0]);
		int mesSalida = Integer.parseInt(fechSalida[1]);
		int anoSalida = Integer.parseInt(fechSalida[2]);
		String tipoHab = reserva.getTipoHabitacion();
		cont = io.comprobarDisponibilidadHabitacion(diaEntrada, mesEntrada, anoEntrada, diaSalida, mesSalida, anoSalida, tipoHab);
		for (String hab : cont) {
		}
		int contNumIterator = 0;
		Iterator it;
		switch (reserva.getTipoHabitacion()) {
			case "DUI":
				for (String dui : vDui) {
					existe = false;
					for (String hab : cont) {
						if (dui.equals(hab)) {
							existe = true;
							break;
						}
					}
					if (!existe) {
						habDisponiblesArrayList.add(dui);
					}
				}
				habDisponibles = new String[habDisponiblesArrayList.size()];
				it = habDisponiblesArrayList.iterator();
				contNumIterator = 0;
				while (it.hasNext()) {
					habDisponibles[contNumIterator] = (String) it.next();
					contNumIterator++;
				}
				modeloCombox = new DefaultComboBoxModel(habDisponibles);
				comboBoxHabitacion.setModel(modeloCombox);
				break;
			case "DB":
				for (String db : vDb) {
					existe = false;
					for (String hab : cont) {
						if (db.equals(hab)) {
							existe = true;
							break;
						}
					}
					if (!existe) {
						habDisponiblesArrayList.add(db);
					}
				}
				habDisponibles = new String[habDisponiblesArrayList.size()];
				it = habDisponiblesArrayList.iterator();
				contNumIterator = 0;
				while (it.hasNext()) {
					habDisponibles[contNumIterator] = (String) it.next();
					contNumIterator++;
				}
				System.out.println(habDisponibles.length);
				modeloCombox = new DefaultComboBoxModel(habDisponibles);
				comboBoxHabitacion.setModel(modeloCombox);
				break;
			case "TRI":
				for (String tri : vTri) {
					existe = false;
					for (String hab : cont) {
						if (tri.equals(hab)) {
							existe = true;
							break;
						}
					}
					if (!existe) {
						habDisponiblesArrayList.add(tri);
					}
				}
				habDisponibles = new String[habDisponiblesArrayList.size()];
				it = habDisponiblesArrayList.iterator();
				contNumIterator = 0;
				while (it.hasNext()) {
					habDisponibles[contNumIterator] = (String) it.next();
					contNumIterator++;
				}
				modeloCombox = new DefaultComboBoxModel(habDisponibles);
				comboBoxHabitacion.setModel(modeloCombox);
				break;
			case "SUITE":
				for (String suite : vSuite) {
					existe = false;
					for (String hab : cont) {
						if (suite.equals(hab)) {
							existe = true;
							break;
						}
					}
					if (!existe) {
						habDisponiblesArrayList.add(suite);
					}
				}
				habDisponibles = new String[habDisponiblesArrayList.size()];
				it = habDisponiblesArrayList.iterator();
				contNumIterator = 0;
				while (it.hasNext()) {
					habDisponibles[contNumIterator] = (String) it.next();
					contNumIterator++;
				}
				modeloCombox = new DefaultComboBoxModel(habDisponibles);
				comboBoxHabitacion.setModel(modeloCombox);
				break;
		}
		
	}
	private class LblCerrarMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			lblCerrar.setIcon(new ImageIcon(".\\recursos\\closeSelected.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			lblCerrar.setIcon(new ImageIcon(".\\recursos\\close.png"));
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			System.exit(0);
		}
	}
	private class LblMinimizarMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			lblMinimizar.setIcon(new ImageIcon(".\\recursos\\minimizeSelected.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			lblMinimizar.setIcon(new ImageIcon(".\\recursos\\minimize.png"));
		}
	}
	private class LblMaximizarMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			lblMaximizar.setIcon(new ImageIcon(".\\recursos\\maximizeSelected.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			lblMaximizar.setIcon(new ImageIcon(".\\recursos\\maximize.png"));
		}
	}
	private class BtnNewButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String hab = (String) comboBoxHabitacion.getSelectedItem();
			int numHab = Integer.parseInt(hab);
			io.asignarHabitacion(numHab, reserva.getCodReserva());
			JOptionPane.showMessageDialog(null, "Habitacion asignada");
			dispose();
		}
	}
	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			initialClick = e.getPoint();
            getComponentAt(initialClick);
		}
	}
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			int thisX = getLocation().x;
            int thisY = getLocation().y;

            // Determine how much the mouse moved since the initial click
            int xMoved = e.getX() - initialClick.x;
            int yMoved = e.getY() - initialClick.y;

            // Move window to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            setLocation(X, Y);
		}
	}
	private void initApp() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("Asignar");
		btnNewButton.addMouseListener(new BtnNewButtonMouseListener());
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton.setBounds(139, 200, 200, 100);
		contentPane.add(btnNewButton);
		
		comboBoxHabitacion = new JComboBox();
		comboBoxHabitacion.setBounds(280, 80, 120, 40);
		contentPane.add(comboBoxHabitacion);
		
		txtrHabitacion = new JTextArea();
		txtrHabitacion.setText("Habitacion:");
		txtrHabitacion.setOpaque(false);
		txtrHabitacion.setForeground(Color.WHITE);
		txtrHabitacion.setFont(new Font("Monospaced", Font.PLAIN, 27));
		txtrHabitacion.setFocusable(false);
		txtrHabitacion.setEditable(false);
		txtrHabitacion.setBounds(50, 80, 200, 51);
		contentPane.add(txtrHabitacion);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(".\\recursos\\fondo.jpg"));
		lblNewLabel.setBounds(0, 30, 1280, 720);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.addMouseListener(new PanelMouseListener());
		panel.setBackground(Color.WHITE);
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 0, 500, 30);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNombreApp = new JLabel("Hotel Stefan *****");
		lblNombreApp.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombreApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreApp.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		lblNombreApp.setForeground(new Color(184, 134, 11));
		lblNombreApp.setBounds(158, 0, 212, 30);
		panel.add(lblNombreApp);
		
		lblCerrar = new JLabel("");
		lblCerrar.addMouseListener(new LblCerrarMouseListener());
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setToolTipText("Cerrar");
		lblCerrar.setIcon(new ImageIcon(".\\recursos\\close.png"));
		lblCerrar.setBounds(22, 9, 14, 14);
		panel.add(lblCerrar);
		
		lblMinimizar = new JLabel("");
		lblMinimizar.addMouseListener(new LblMinimizarMouseListener());
		lblMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setToolTipText("Minimizar");
		lblMinimizar.setIcon(new ImageIcon(".\\recursos\\minimize.png"));
		lblMinimizar.setBounds(46, 9, 14, 14);
		panel.add(lblMinimizar);
		
		lblMaximizar = new JLabel("");
		lblMaximizar.addMouseListener(new LblMaximizarMouseListener());
		lblMaximizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMaximizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaximizar.setToolTipText("Maximizar");
		lblMaximizar.setIcon(new ImageIcon(".\\recursos\\maximize.png"));
		lblMaximizar.setBounds(70, 9, 14, 14);
		panel.add(lblMaximizar);
	}
}