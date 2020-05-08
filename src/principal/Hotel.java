package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;

import estaticos.Reserva;

import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import com.toedter.calendar.JDateChooser;

import InputOutput.IoReservas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

@SuppressWarnings({ "unused", "serial", "rawtypes","unchecked" })
public class Hotel extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNombreApp;
	private JLabel lblCerrar;
	private JLabel lblMinimizar;
	private JLabel lblMaximizar;
	private JLabel lblReserva;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JLabel btnBuscar;
	private JLabel btnBorrarBusqueda;
	private JLabel btnAsignar;
	private JLabel btnSalir;
	private boolean maximizado = false;
	private JTextArea txtrFechaEntrada;
	private JTextArea txtrFechaSalida;
	private IoReservas io;
	private JTextArea txtAreaReservas;
	private boolean fecha = false;
	private boolean fechaEntr = false;
	private boolean fechaSalid = false;
	private ArrayList<Reserva> listaReservas;
	private int mesEntrada;
	private int mesSalida;
	private int diaEntrada;
	private int diaSalida;
	private int anoEntrada;
	private int anoSalida;
	private Point initialClick;
	private JDateChooser dateChooserSalida;
	private JDateChooser dateChooserEntrada;
	private Date hoy = Calendar.getInstance().getTime();
	private Date hoyArray = Calendar.getInstance().getTime();
	private Calendar calHoyArray = Calendar.getInstance();
	private Calendar calEntrada;
	private Calendar calSalida;
	private int diaHoy;
	private int mesHoy;
	private int anoHoy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hotel frame = new Hotel();
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
	public Hotel() {
		io = new IoReservas();
		initApp();
		
		//listaReservas = operacion.devolverReservas(diaHoy, mesHoy, anoHoy);
		cargarReservas(io.devolverArrayReservasString());
	}

	public void cargarReservas(String reservas) {
		txtAreaReservas.setText(reservas);
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
	} //

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

	private class BtnBorrarBusquedaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			txtCodigo.setText("Codigo");
			txtNombre.setText("Nombre");
			txtApellidos.setText("Apellidos");
			txtAreaReservas.setText("");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btnBorrarBusqueda.setIcon(new ImageIcon(".//recursos//clear.png"));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btnBorrarBusqueda.setIcon(new ImageIcon(".//recursos//clearBW.png"));
		}
	}

	private class BtnAsignarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(null, "Habitacion asignada");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btnAsignar.setIcon(new ImageIcon(".//recursos//asignRoom.png"));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btnAsignar.setIcon(new ImageIcon(".//recursos//asignRoomBW.png"));
		}
	}

	private class BtnBuscarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			conseguirTexto();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btnBuscar.setIcon(new ImageIcon(".//recursos//search.png"));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btnBuscar.setIcon(new ImageIcon(".//recursos//searchBW.png"));
		}
	}

	/** Metodo para filtrar el fichero en busca de variables */

	private void conseguirTexto() {
		boolean porNombre, porApellido;
		comprobarFecha();
		listaReservas = io.devolverReservas();
		ArrayList<Reserva> dumpArray = new ArrayList<Reserva>();
		String nombre = txtNombre.getText();
		String apellido = txtApellidos.getText();
		String contenido = "";

		if (nombre.equals("") || nombre.equals("Nombre")) {
			porNombre = false;
		} else {
			porNombre = true;
		}
		if (apellido.equals("") || apellido.equals("Apellidos")) {
			porApellido = false;
		} else {
			porApellido = true;
		}

		if (porNombre) {
			for (Reserva res : listaReservas) {
				if (res.getNombreReserva().equals(nombre))
					dumpArray.add(res);
			}
			listaReservas = new ArrayList<Reserva>();
			listaReservas = dumpArray;
			dumpArray = new ArrayList<Reserva>();
		}
		if (porApellido) {
			for (Reserva res : listaReservas) {
				if (res.getApellidosReserva().equals(apellido))
					dumpArray.add(res);
			}
			listaReservas = new ArrayList<Reserva>();
			listaReservas = dumpArray;
			dumpArray = new ArrayList<Reserva>();
		}
		if (fecha) {
			String fechaEntrString = (diaEntrada + "/" + mesEntrada + "/" + anoEntrada);
			String fechaSalidString = (diaSalida + "/" + mesSalida + "/" + anoSalida);
			for (Reserva res : listaReservas) {

				if (res.getFechaEntrada().equals(fechaEntrString) && res.getFechaSalida().equals(fechaSalidString))
					dumpArray.add(res);
			}
			listaReservas = new ArrayList<Reserva>();
			listaReservas = dumpArray;
			dumpArray = new ArrayList<Reserva>();
		} else {
			comprobarFechaEntrSalid();
			if (fechaEntr) {
				String fechaEntrString = (diaEntrada + "/" + mesEntrada + "/" + anoEntrada);
				for (Reserva res : listaReservas) {
					if (res.getFechaEntrada().equals(fechaEntrString))
						dumpArray.add(res);
				}
				listaReservas = new ArrayList<Reserva>();
				listaReservas = dumpArray;
				dumpArray = new ArrayList<Reserva>();
			}
			if (fechaSalid) {
				String fechaSalidString = (diaSalida + "/" + mesSalida + "/" + anoSalida);
				for (Reserva res : listaReservas) {
					if (res.getFechaSalida().equals(fechaSalidString))
						dumpArray.add(res);
				}
				listaReservas = new ArrayList<Reserva>();
				listaReservas = dumpArray;
				dumpArray = new ArrayList<Reserva>();
			}
		}
		Iterator it = listaReservas.iterator();
		while (it.hasNext()) {
			contenido += it.next() + "\n";
		}
		txtAreaReservas.setText(contenido);
	}

	/** Metodo para comprobar la fecha si los dos campos no estan rellenos */

	private void comprobarFechaEntrSalid() {
		if (anoSalida != 0 &&  diaSalida != 0) {
			fechaSalid = true;
		} else {
			fechaSalid = false;
		}
		if (anoEntrada != 0 &&  diaEntrada != 0) {
			fechaEntr = true;
		} else {
			fechaEntr = false;
		}
	}

	/** Metodo para comprobar la fecha si los dos campos estan rellenos */

	private void comprobarFecha() {
		if ((anoSalida != 0  && diaSalida != 0)
				&& (anoEntrada != 0 && diaEntrada != 0)) {
			fecha = true;
		} else {
			fecha = false;
		}
	}

	/** Metodo para comprobar el mes de las fechas */

	private class BtnSalirMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			LogIn log = new LogIn();
			log.setVisible(true);
			dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btnSalir.setIcon(new ImageIcon(".//recursos//goBack.png"));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btnSalir.setIcon(new ImageIcon(".//recursos//goBackBW.png"));
		}
	}

	private class TxtCodigoFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			String texto = txtCodigo.getText();
			if (texto.equals("Codigo")) {
				txtCodigo.setText("");
				txtCodigo.setForeground(Color.black);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			String texto = txtCodigo.getText();
			if (texto.equals("")) {
				txtCodigo.setForeground(Color.gray);
				txtCodigo.setText("Codigo");
			}
		}
	}

	private class TxtNombreFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			String texto = txtNombre.getText();
			if (texto.equals("Nombre")) {
				txtNombre.setText("");
				txtNombre.setForeground(Color.black);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			String texto = txtNombre.getText();
			if (texto.equals("")) {
				txtNombre.setForeground(Color.gray);
				txtNombre.setText("Nombre");
			}
		}
	}

	private class TxtApellidosFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			String texto = txtApellidos.getText();
			if (texto.equals("Apellidos")) {
				txtApellidos.setText("");
				txtApellidos.setForeground(Color.black);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			String texto = txtApellidos.getText();
			if (texto.equals("")) {
				txtApellidos.setForeground(Color.gray);
				txtApellidos.setText("Apellidos");
			}
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
	private class DateChooserEntradaPropertyChangeListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			Date dateEntrada = new Date();
			try {
				dateEntrada = dateChooserEntrada.getDate();
				calEntrada = Calendar.getInstance();
				calEntrada.setTime(dateEntrada);
				mesEntrada = calEntrada.get(Calendar.MONTH);
				diaEntrada = calEntrada.get(Calendar.DAY_OF_MONTH);
				anoEntrada = calEntrada.get(Calendar.YEAR);
			} catch (Exception e) {
			}
		}
	}
	private class DateChooserSalidaPropertyChangeListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			Date dateSalida = new Date();
			try {
				dateSalida = dateChooserSalida.getDate();
				calSalida = Calendar.getInstance();
				calSalida.setTime(dateSalida);
				mesSalida = calSalida.get(Calendar.MONTH);
				diaSalida = calSalida.get(Calendar.DAY_OF_MONTH);
				anoSalida = calSalida.get(Calendar.YEAR);
			} catch (Exception e) {
			}
		}
	}

	/** MĂ©todo para iniciar todas las variables de la app */

	public void initApp() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAsignar = new JLabel("");
		btnAsignar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAsignar.setIcon(new ImageIcon(".\\recursos\\asignRoomBW.png"));
		btnAsignar.setToolTipText("Asignar habitaci\u00F3n");
		btnAsignar.setHorizontalAlignment(SwingConstants.CENTER);
		btnAsignar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAsignar.addMouseListener(new BtnAsignarMouseListener());
		btnAsignar.setFocusable(false);

		btnSalir = new JLabel("");
		btnSalir.setIcon(new ImageIcon(".\\recursos\\goBackBW.png"));
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalir.setToolTipText("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.addMouseListener(new BtnSalirMouseListener());
		btnSalir.setFocusable(false);
		
		dateChooserEntrada = new JDateChooser();
		dateChooserEntrada.addPropertyChangeListener(new DateChooserEntradaPropertyChangeListener());
		dateChooserEntrada.setBounds(800, 109, 170, 20);
		contentPane.add(dateChooserEntrada);
		
		dateChooserSalida = new JDateChooser();
		dateChooserSalida.addPropertyChangeListener(new DateChooserSalidaPropertyChangeListener());
		dateChooserSalida.setBounds(800, 171, 170, 20);
		contentPane.add(dateChooserSalida);

		txtAreaReservas = new JTextArea();
		txtAreaReservas.setBounds(130, 380, 1000, 220);
		contentPane.add(txtAreaReservas);

		txtrFechaSalida = new JTextArea();
		txtrFechaSalida.setText("Fecha Salida:");
		txtrFechaSalida.setOpaque(false);
		txtrFechaSalida.setForeground(Color.WHITE);
		txtrFechaSalida.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrFechaSalida.setFocusable(false);
		txtrFechaSalida.setEditable(false);
		txtrFechaSalida.setBounds(610, 171, 180, 30);
		contentPane.add(txtrFechaSalida);

		txtrFechaEntrada = new JTextArea();
		txtrFechaEntrada.setText("Fecha Entrada:");
		txtrFechaEntrada.setOpaque(false);
		txtrFechaEntrada.setForeground(Color.WHITE);
		txtrFechaEntrada.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrFechaEntrada.setFocusable(false);
		txtrFechaEntrada.setEditable(false);
		txtrFechaEntrada.setBounds(610, 109, 180, 30);
		contentPane.add(txtrFechaEntrada);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalir.setBounds(780, 611, 128, 128);
		contentPane.add(btnSalir);
		btnAsignar.setBounds(320, 611, 128, 128);
		contentPane.add(btnAsignar);

		lblReserva = new JLabel("Reserva:");
		lblReserva.setHorizontalAlignment(SwingConstants.LEFT);
		lblReserva.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblReserva.setFocusable(false);
		lblReserva.setForeground(Color.WHITE);
		lblReserva.setBounds(235, 50, 81, 29);
		contentPane.add(lblReserva);

		lblNombre = new JLabel("Nombre: ");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNombre.setFocusable(false);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(235, 109, 81, 30);
		contentPane.add(lblNombre);

		btnBorrarBusqueda = new JLabel("");
		btnBorrarBusqueda.setIcon(new ImageIcon(".\\recursos\\clearBW.png"));
		btnBorrarBusqueda.setToolTipText("Borrar filtros");
		btnBorrarBusqueda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBorrarBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
		btnBorrarBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBorrarBusqueda.setFocusable(false);
		btnBorrarBusqueda.setBounds(780, 241, 128, 128);
		btnBorrarBusqueda.addMouseListener(new BtnBorrarBusquedaMouseListener());
		contentPane.add(btnBorrarBusqueda);

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new TxtNombreFocusListener());
		txtNombre.setForeground(Color.GRAY);
		txtNombre.setText("Nombre");
		txtNombre.setColumns(10);
		txtNombre.setBounds(362, 111, 150, 30);
		contentPane.add(txtNombre);

		txtApellidos = new JTextField();
		txtApellidos.addFocusListener(new TxtApellidosFocusListener());
		txtApellidos.setForeground(Color.GRAY);
		txtApellidos.setText("Apellidos");
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(362, 172, 150, 30);
		contentPane.add(txtApellidos);

		txtCodigo = new JTextField();
		txtCodigo.addFocusListener(new TxtCodigoFocusListener());
		txtCodigo.setForeground(Color.GRAY);
		txtCodigo.setText("Codigo");
		txtCodigo.setBounds(362, 51, 150, 30);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		btnBuscar = new JLabel("");
		btnBuscar.setToolTipText("B\u00FAsqueda");
		btnBuscar.setIcon(new ImageIcon(".\\recursos\\searchBW.png"));
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuscar.setFocusable(false);
		btnBuscar.setBounds(320, 241, 128, 128);
		btnBuscar.addMouseListener(new BtnBuscarMouseListener());
		contentPane.add(btnBuscar);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblApellidos.setFocusable(false);
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setBounds(235, 172, 117, 30);
		contentPane.add(lblApellidos);

		lblNewLabel = new JLabel("");
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
		///
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
		dateChooserEntrada.setMinSelectableDate(hoy);
		dateChooserSalida.setMinSelectableDate(hoy);
		calHoyArray.setTime(hoyArray);
		mesHoy = calHoyArray.get(Calendar.MONTH);
		diaHoy = calHoyArray.get(Calendar.DAY_OF_MONTH);
		anoHoy = calHoyArray.get(Calendar.YEAR);
		
		io = new IoReservas();
	}

	public boolean testFechaEntradaSalida(int aSalida, int mSalida, int dSalida, int aEntrada, int mEntrada,
			int dEntrada) {
		if (aSalida != 0 && mSalida != 0 && dSalida != 0) {
			return true;
		} else {
			if (aEntrada != 0 && mEntrada != 0 && dEntrada != 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean testFechasHotel(int aSalida, int mSalida, int dSalida, int aEntrada, int mEntrada, int dEntrada) {
		if ((aSalida != 0 && mSalida != 0 && dSalida != 0) && (aEntrada != 0 && mEntrada != 0 && dEntrada != 0)) {
			return true;
		} else {
			return false;
		}
	}
}