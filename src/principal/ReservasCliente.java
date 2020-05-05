package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Frame;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import InputOutput.OperacionReserva;
import estaticos.Reserva;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;

public class ReservasCliente extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNombreApp;
	private JLabel lblCerrar;
	private JLabel lblMinimizar;
	private JLabel lblMaximizar;
	private boolean maximizado = false;
	private JTextArea txtrReservas;
	private JTextArea txtrReservasLista;
	private JLabel btnNuevaReserva;
	private JLabel btnSalir;
	private OperacionReserva operacion = new OperacionReserva();
	private String usuarioReservas = "";
	private Reserva reservaRealizada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasCliente frame = new ReservasCliente();
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
	public ReservasCliente() {
		initApp();
	}

	public ReservasCliente(String usuario) {
		initApp();
		usuarioReservas = usuario;
		operacion.cargarReservas();
		cargarReservas();
	}

	public ReservasCliente(String usuario, Reserva reserva) {
		initApp();
		usuarioReservas = usuario;
		operacion.cargarReservas();
		reservaRealizada = reserva;
		operacion.anadirReserva(reservaRealizada);
		operacion.guardarReservas();
		cargarReservas();
	}

	public void cargarReservas() {
		String textoReservas = "";
		textoReservas = operacion.cargarArray(usuarioReservas);
		txtrReservasLista.setText(textoReservas);
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

		@Override
		public void mouseClicked(MouseEvent e) {
			setState(JFrame.ICONIFIED);
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

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!maximizado) {
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				maximizado = true;
			} else {
				setExtendedState(JFrame.NORMAL);
				maximizado = false;
			}

		}
	}

	private class BtnNuevaReservaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			new Reservar(usuarioReservas).setVisible(true);
			dispose();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btnNuevaReserva.setIcon(new ImageIcon(".\\recursos\\addReserva.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnNuevaReserva.setIcon(new ImageIcon(".\\recursos\\addReservaBW.png"));
		}
	}

	private class BtnSalirMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			LogIn log = new LogIn();
			log.setVisible(true);
			dispose();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btnSalir.setIcon(new ImageIcon(".\\recursos\\goBack.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnSalir.setIcon(new ImageIcon(".\\recursos\\goBackBW.png"));
		}
	}
	/** Todas las variables y los apartados para la ejecución de la app (VIVA NETBEANS) */
	private void initApp() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSalir = new JLabel("");
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalir.setToolTipText("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setIcon(new ImageIcon(".\\recursos\\goBackBW.png"));
		btnSalir.addMouseListener(new BtnSalirMouseListener());
		btnSalir.setFocusable(false);

		txtrReservasLista = new JTextArea();
		txtrReservasLista.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrReservasLista.setForeground(Color.BLACK);
		txtrReservasLista.setEditable(false);
		txtrReservasLista.setBounds(50, 150, 1180, 450);
		contentPane.add(txtrReservasLista);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSalir.setBounds(906, 611, 128, 128);
		contentPane.add(btnSalir);

		btnNuevaReserva = new JLabel("");
		btnNuevaReserva.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNuevaReserva.setToolTipText("Nueva reserva");
		btnNuevaReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevaReserva.setIcon(new ImageIcon(".\\recursos\\addReservaBW.png"));
		btnNuevaReserva.setHorizontalAlignment(SwingConstants.CENTER);
		btnNuevaReserva.addMouseListener(new BtnNuevaReservaMouseListener());
		btnNuevaReserva.setFocusable(false);
		btnNuevaReserva.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNuevaReserva.setBounds(213, 611, 128, 128);
		contentPane.add(btnNuevaReserva);

		txtrReservas = new JTextArea();
		txtrReservas.setFocusable(false);
		txtrReservas.setText("Tus Reservas:");
		txtrReservas.setOpaque(false);
		txtrReservas.setForeground(Color.WHITE);
		txtrReservas.setFont(new Font("Monospaced", Font.PLAIN, 30));
		txtrReservas.setEditable(false);
		txtrReservas.setBounds(50, 80, 231, 50);
		contentPane.add(txtrReservas);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFocusable(false);
		lblNewLabel.setIcon(new ImageIcon(".\\recursos\\fondo.jpg"));
		lblNewLabel.setBounds(0, 30, 1280, 720);
		contentPane.add(lblNewLabel);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 0, 1280, 30);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNombreApp = new JLabel("Hotel Stefan *****");
		lblNombreApp.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombreApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreApp.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		lblNombreApp.setForeground(new Color(184, 134, 11));
		lblNombreApp.setBounds(538, 0, 212, 30);
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
