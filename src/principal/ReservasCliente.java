package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import InputOutput.IoReservas;
import estaticos.Reserva;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTextArea;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JList;
@SuppressWarnings({ "unused", "serial", "rawtypes" })
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
	private JLabel btnNuevaReserva;
	private JLabel btnSalir;
	private IoReservas io;
	private ArrayList<Reserva> listaReservas;
	private String usuarioReservas = "";
	private Reserva reservaRealizada;
	private Point initialClick;
	private Date hoyArray = Calendar.getInstance().getTime();
	private Calendar calHoyArray = Calendar.getInstance();
	private int diaHoy;
	private int mesHoy;
	private int anoHoy;
	private JList listAreaReservas;
	private DefaultListModel model = new DefaultListModel();
	private JLabel btnCancelarReserva;

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
		cargarReservas();
	}

	public ReservasCliente(String usuario, Reserva reserva) {
		initApp();
		usuarioReservas = usuario;
		reservaRealizada = reserva;
		io.registrarReserva(usuario, reservaRealizada.getNombreReserva(), reservaRealizada.getApellidosReserva(), reservaRealizada.getFechaEntrada(), reservaRealizada.getFechaSalida(), reservaRealizada.getTipoHabitacion(), reservaRealizada.getRegimen(), reservaRealizada.getSexo(), reservaRealizada.getPrecio(), reservaRealizada.getNumeroNoches());
		cargarReservas();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void cargarReservas() {
		String textoReservas = "";
		listaReservas = io.devolverReservas();
		Reserva res = null;
		Iterator it = listaReservas.iterator();
		while (it.hasNext()) {
			res = (Reserva) it.next();
			if (res.getUsuario().equals(usuarioReservas))
				model.addElement(res);
		}
		listAreaReservas.setModel(model);
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
	private class ListAreaReservasMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
			Reserva res = (Reserva) listAreaReservas.getSelectedValue();
			String[] parts = res.getFechaEntrada().split("/");
			if (Integer.parseInt(parts[1]) < 10) {
				parts[1] = "0" + parts[1];
			}
			if (Integer.parseInt(parts[0]) < 10) {
				parts[0] = "0" + parts[0];
			}
			String fechaEntradaReserva="";
			if (diaHoy < 10) {
				fechaEntradaReserva = "0" + diaHoy +"/";
			} else {
				fechaEntradaReserva = Integer.toString(diaHoy) + "/";
			}
			if (mesHoy < 10) {
				fechaEntradaReserva += "0" + mesHoy +"/";
			}else {
				fechaEntradaReserva += Integer.toString(mesHoy)+ "/";
			}
			fechaEntradaReserva += Integer.toString(anoHoy);
			String fechaEntrada = parts[0]+"/"+parts[1]+"/"+parts[2];
			Date dateEntrada = null;
			Date dateEntradaReserva = null;
			try {
				dateEntrada = formatter1.parse(fechaEntrada);
				dateEntradaReserva = formatter1.parse(fechaEntradaReserva);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Calendar calEntrada = Calendar.getInstance();
			calEntrada.setTime(dateEntrada);
			Calendar calEntradaReserva = Calendar.getInstance();
			calEntradaReserva.setTime(dateEntradaReserva);
			LocalDate entrada = new LocalDate(calEntrada.getTimeInMillis());
			LocalDate entradaReserva = new LocalDate(calEntradaReserva.getTimeInMillis());
			int dias = Days.daysBetween(entradaReserva, entrada).getDays();
			if (dias>=14) {
				btnCancelarReserva.setEnabled(true);
			} else {
				btnCancelarReserva.setEnabled(false);
			}
			
		}
	}
	private class BtnCancelarReservaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Reserva res = (Reserva) listAreaReservas.getSelectedValue();
			if (btnCancelarReserva.isEnabled()) {
				io.cancelarReserva(res.getCodReserva());
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btnCancelarReserva.setIcon(new ImageIcon(".//recursos//cancel.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnCancelarReserva.setIcon(new ImageIcon(".//recursos/cancelBW.png"));
		}
	}
	/** Todas las variables y los apartados para la ejecuci�n de la app (VIVA NETBEANS) */
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
		
		btnCancelarReserva = new JLabel("");
		btnCancelarReserva.setIcon(new ImageIcon(".\\recursos\\cancelBW.png"));
		btnCancelarReserva.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelarReserva.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelarReserva.setToolTipText("Cancelar reserva");
		btnCancelarReserva.setEnabled(false);
		btnCancelarReserva.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCancelarReserva.setBounds(549, 604, 128, 128);
		btnCancelarReserva.addMouseListener(new BtnCancelarReservaMouseListener());
		contentPane.add(btnCancelarReserva);
		
		listAreaReservas = new JList();
		listAreaReservas.addMouseListener(new ListAreaReservasMouseListener());
		listAreaReservas.setBounds(50, 153, 1180, 440);
		contentPane.add(listAreaReservas);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSalir.setBounds(1102, 604, 128, 128);
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
		btnNuevaReserva.setBounds(50, 604, 128, 128);
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
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.addMouseListener(new PanelMouseListener());
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

		calHoyArray.setTime(hoyArray);
		mesHoy = calHoyArray.get(Calendar.MONTH);
		diaHoy = calHoyArray.get(Calendar.DAY_OF_MONTH);
		anoHoy = calHoyArray.get(Calendar.YEAR);
		
		io = new IoReservas();
	}
}
