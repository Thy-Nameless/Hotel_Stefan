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
import java.awt.Point;

import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import estaticos.Reserva;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseMotionAdapter;
import com.toedter.calendar.JMonthChooser;

import InputOutput.IoReservas;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.beans.PropertyChangeEvent;

@SuppressWarnings({ "unused", "serial", "rawtypes", "unchecked" })
public class Reservar extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JPanel panel;
	private JLabel lblNombreApp;
	private JLabel lblCerrar;
	private JLabel lblMinimizar;
	private JLabel lblMaximizar;
	private boolean maximizado = false;
	private JTextArea txtrNombre;
	private JTextArea txtrApellido;
	private JTextArea txtrTipoHabitacion;
	private JRadioButton rdbtnDui;
	private JRadioButton rdbtnDb;
	private JRadioButton rdbtnTri;
	private JRadioButton rdbtnSuite;
	private final ButtonGroup buttonGroupHabitaciones = new ButtonGroup();
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextArea txtrTipoRegimen;
	private JRadioButton rdbtnHa;
	private JRadioButton rdbtnAd;
	private JRadioButton rdbtnMp;
	private JRadioButton rdbtnPc;
	private final ButtonGroup buttonGroupRegimen = new ButtonGroup();
	private JTextArea txtrSexo;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private JTextArea txtrFechaEntrada;
	private JTextArea txtrFechaSalida;
	private JTextArea txtrNumeroNoches;
	private JTextField textFieldNumeroNoches;
	private JTextArea txtrImporteTotal;
	private JTextField textFieldImporte;
	private JLabel btnPagar;
	private JLabel btnSalir;
	private JLabel lblComprobacionHabitacion;
	// private OperacionHabitacion operaciones = new OperacionHabitacion();
	private ImageIcon imagentick = new ImageIcon(".\\recursos\\tick.png");
	private ImageIcon imagenx = new ImageIcon(".\\recursos\\nodisponible.png");
	private Reserva reserva;
	private String usuarioReservas = "";
	private int mesEntrada;
	private int mesSalida;
	private int diaEntrada;
	private int diaSalida;
	private int anoEntrada;
	private int anoSalida;
	private IoReservas io;
	 boolean disponible;
	@SuppressWarnings({})
	/**
	 * private final DefaultComboBoxModel dia = new DefaultComboBoxModel(new
	 * String[] { "Dia" }); private final DefaultComboBoxModel dia31 = new
	 * DefaultComboBoxModel( new String[] { "Dia", "1", "2", "3", "4", "5", "6",
	 * "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
	 * "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" });
	 * private final DefaultComboBoxModel dia30 = new DefaultComboBoxModel( new
	 * String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
	 * "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
	 * "25", "26", "27", "28", "29", "30" }); private final DefaultComboBoxModel
	 * dia29 = new DefaultComboBoxModel( new String[] { "Dia", "1", "2", "3", "4",
	 * "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
	 * "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29" });
	 * private final DefaultComboBoxModel dia28 = new DefaultComboBoxModel( new
	 * String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
	 * "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
	 * "25", "26", "27", "28" }); private final DefaultComboBoxModel dias = new
	 * DefaultComboBoxModel(new String[] { "Dia" }); private final
	 * DefaultComboBoxModel dia31s = new DefaultComboBoxModel( new String[] { "Dia",
	 * "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
	 * "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
	 * "28", "29", "30", "31" }); private final DefaultComboBoxModel dia30s = new
	 * DefaultComboBoxModel( new String[] { "Dia", "1", "2", "3", "4", "5", "6",
	 * "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
	 * "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }); private
	 * final DefaultComboBoxModel dia29s = new DefaultComboBoxModel( new String[] {
	 * "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
	 * "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
	 * "27", "28", "29" }); private final DefaultComboBoxModel dia28s = new
	 * DefaultComboBoxModel( new String[] { "Dia", "1", "2", "3", "4", "5", "6",
	 * "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
	 * "20", "21", "22", "23", "24", "25", "26", "27", "28" }); private final
	 * DefaultComboBoxModel ano2021 = new DefaultComboBoxModel(new String[] { "Año",
	 * "2020", "2021" }); private final DefaultComboBoxModel ano20 = new
	 * DefaultComboBoxModel(new String[] { "Año", "2020" }); private final
	 * DefaultComboBoxModel ano21 = new DefaultComboBoxModel(new String[] { "Año",
	 * "2021" });
	 */
	private Point initialClick;
	private Date hoy = Calendar.getInstance().getTime();
	private JDateChooser dateChooserEntrada;
	private JDateChooser dateChooserSalida;
	private Calendar calEntrada;
	private Calendar calSalida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservar frame = new Reservar();
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
	public Reservar() {

		initApp();

	}

	public Reservar(String usuario) {
		// operaciones.cargarHabitaciones();
		usuarioReservas = usuario;
		initApp();

	}

	/** Método para añadir y quitar texto de los apartados de entrada de texto */
	private void cambiarTextoNombreDefault() {
		textNombre.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textNombre.setForeground(Color.gray);
		textNombre.setText("Nombre");
	}

	private void cambiarTextoNombre() {
		textNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		textNombre.setForeground(Color.black);
	}

	private void cambiarTextoApellidoDefault() {
		textApellido.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textApellido.setForeground(Color.gray);
		textApellido.setText("Apellido");
	}

	private void cambiarTextoApellido() {
		textApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		textApellido.setForeground(Color.black);
	}

	private void comprobarBotonEnviar() {
		comprobarDisponibilidadHabitacion();
		if (!textNombre.getText().equals("Nombre") && !textApellido.getText().equals("Apellido")
				&& !textFieldImporte.getText().equals("0") && disponible) {
			
			btnPagar.setEnabled(true);
		} else {
			btnPagar.setEnabled(false);
		}
	}

	/** Método para comprobar si existen habitaciones del tipo elegido */

	/**
	 * private void comprobarDisponibilidad() {
	 * 
	 * String seleccion = ""; if (rdbtnDui.isSelected()) { seleccion = "dui"; } if
	 * (rdbtnDb.isSelected()) { seleccion = "db"; } if (rdbtnTri.isSelected()) {
	 * seleccion = "tri"; } if (rdbtnSuite.isSelected()) { seleccion = "suite"; }
	 * 
	 * switch (seleccion) { case "dui": do { if
	 * (operaciones.comprobarHabitacion(pos)) { break; } if (pos == 44) { pos = 100;
	 * } pos++; if (pos == 5) { pos = pos + 20 - 5; } } while (pos != 44); break;
	 * case "db": do { if (operaciones.comprobarHabitacion(pos)) { break; } if (pos
	 * == 49) { pos = 100; } pos++; if (pos == 5) { pos = pos + 20 - 5; } } while
	 * (pos != 49); break; case "tri": do { if
	 * (operaciones.comprobarHabitacion(pos)) { break; } if (pos == 54) { pos = 100;
	 * } pos++; if (pos == 5) { pos = pos + 20 - 5; } } while (pos != 54); break;
	 * case "suite": do { if (operaciones.comprobarHabitacion(pos)) { break; } if
	 * (pos == 59) { pos = 100; } pos++; if (pos == 5) { pos = pos + 20 - 5; } }
	 * while (pos != 59); break; } if (pos != 100) {
	 * lblComprobacionHabitacion.setIcon(imagentick); } else {
	 * lblComprobacionHabitacion.setIcon(imagenx); } }
	 */

	/** Método para comprobar el importe de las habitaciones */
	private void comprobarImporte() {
		if (!textFieldNumeroNoches.getText().equals("")) {
			String seleccionHabitacion = "";
			if (rdbtnDui.isSelected()) {
				seleccionHabitacion = "dui";
			}
			if (rdbtnDb.isSelected()) {
				seleccionHabitacion = "db";
			}
			if (rdbtnTri.isSelected()) {
				seleccionHabitacion = "tri";
			}
			if (rdbtnSuite.isSelected()) {
				seleccionHabitacion = "suite";
			}

			String seleccionRegimen = "";
			if (rdbtnHa.isSelected()) {
				seleccionRegimen = "ha";
			}
			if (rdbtnAd.isSelected()) {
				seleccionRegimen = "ad";
			}
			if (rdbtnMp.isSelected()) {
				seleccionRegimen = "mp";
			}
			if (rdbtnPc.isSelected()) {
				seleccionRegimen = "pc";
			}
			int precio;
			int precioHab = 0;
			if (!textFieldNumeroNoches.getText().equals("")) {
				precioHab = Integer.parseInt(textFieldNumeroNoches.getText());
			}

			int precioReg = precioHab;

			switch (seleccionHabitacion) {
			case "dui":
				precioHab = precioHab * 30;
				break;
			case "db":
				precioHab = precioHab * 50;
				break;
			case "tri":
				precioHab = precioHab * 70;
				break;
			case "suite":
				precioHab = precioHab * 140;
				break;
			}
			switch (seleccionRegimen) {
			case "ha":
				break;
			case "ad":
				precioReg = precioReg * 10;
				break;
			case "mp":
				precioReg = precioReg * 15;
				break;
			case "pc":
				precioReg = precioReg * 25;
				break;
			}

			precio = precioHab + precioReg;
			textFieldImporte.setText(Integer.toString(precio));

		}
	}

	// Método para imprimir los números de las noches
	private void comprobarNumNoches() {
		textFieldNumeroNoches.setText("0");
		textFieldImporte.setText("0");
		try {
			LocalDate entrada = new LocalDate(calEntrada.getTimeInMillis());
			LocalDate salida = new LocalDate(calSalida.getTimeInMillis());
			int dias = Days.daysBetween(entrada, salida).getDays();
			if (dias > 0) {
				textFieldNumeroNoches.setText(Integer.toString(dias));
			}
		} catch (Exception e) {

		}

		// if (comprobarMes(anoSalida, mesSalida, diaSalida) && comprobarMes(anoEntrada,
		// mesEntrada, diaEntrada)) {
		comprobarImporte();
		comprobarBotonEnviar();

	}

	/** Método para comprobar si el mes y el dia son válidos */
	/**
	 * private boolean comprobarMes(int ano, int mes, int dia) { if (dia > 0 && dia
	 * < 32 && mes > 0 && mes < 13) { switch (mes) { case 1: case 3: case 5: case 7:
	 * case 8: case 10: case 12: return true; case 4: case 6: case 9: case 11: if
	 * (dia < 31) { return true; } break; case 2: if (dia < 29 || ((((ano % 4 == 0)
	 * && !(ano % 100 == 0)) || (ano % 400 == 0)) && dia < 30)) { return true; }
	 * break; } } return false;
	 * 
	 * }
	 */

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

	private class TextNombreFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			if (textNombre.getText().equals("Nombre")) {
				textNombre.setText("");
				cambiarTextoNombre();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (textNombre.getText().equals("")) {
				cambiarTextoNombreDefault();
			}
		}
	}

	private class TextApellidoFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			if (textApellido.getText().equals("Apellido")) {
				textApellido.setText("");
				cambiarTextoApellido();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (textApellido.getText().equals("")) {
				cambiarTextoApellidoDefault();
			}
		}
	}

	/** utilizado para obtener el String de habitación */
	private String sacarHabitacion() {
		String seleccionHabitacion = "";
		if (rdbtnDui.isSelected()) {
			seleccionHabitacion = "DUI";
		}
		if (rdbtnDb.isSelected()) {
			seleccionHabitacion = "DB";
		}
		if (rdbtnTri.isSelected()) {
			seleccionHabitacion = "TRI";
		}
		if (rdbtnSuite.isSelected()) {
			seleccionHabitacion = "SUITE";
		}

		return seleccionHabitacion;
	}

	/** utilizado para obtener el String de régimen */
	private String sacarRegimen() {
		String seleccionRegimen = "";
		if (rdbtnHa.isSelected()) {
			seleccionRegimen = "HA";
		}
		if (rdbtnAd.isSelected()) {
			seleccionRegimen = "AD";
		}
		if (rdbtnMp.isSelected()) {
			seleccionRegimen = "MP";
		}
		if (rdbtnPc.isSelected()) {
			seleccionRegimen = "PC";
		}
		return seleccionRegimen;
	}

	/** utilizado para obtener el String de sexo */
	private String sacarSexo() {
		String seleccionSexo = "";
		if (rdbtnHombre.isSelected()) {
			seleccionSexo = "Hombre";
		} else {
			seleccionSexo = "Mujer";
		}
		return seleccionSexo;
	}

	/**
	 * método previamente private int reservarHabitacion() {
	 * operaciones.reservarHabitacion(pos); return
	 * operaciones.sacarNumeroHabitacion(pos); }
	 */

	private class BtnPagarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (btnPagar.isEnabled()) {
				reserva = new Reserva(usuarioReservas, textNombre.getText(), textApellido.getText(),
						(diaEntrada + "/" + mesEntrada + "/" + anoEntrada),
						(diaSalida + "/" + mesSalida + "/" + anoSalida), sacarHabitacion(), sacarRegimen(), sacarSexo(),
						Integer.parseInt(textFieldImporte.getText()),
						Integer.parseInt(textFieldNumeroNoches.getText()));
				new Pago(usuarioReservas, reserva).setVisible(true);
				dispose();
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btnPagar.setIcon(new ImageIcon(".\\recursos\\goToPay.png"));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btnPagar.setIcon(new ImageIcon(".\\recursos\\goToPayBW.png"));
		}
	}

	private class BtnSalirMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			new ReservasCliente(usuarioReservas).setVisible(true);
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

	private class RdbtnHaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class RdbtnDuiMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class RdbtnDbMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class RdbtnTriMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class RdbtnAdMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class RdbtnSuiteMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class RdbtnMpMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class RdbtnPcMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			comprobarImporte();
		}
	}

	private class TextApellidoKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			comprobarBotonEnviar();
		}
	}

	private class TextNombreKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			comprobarBotonEnviar();
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

	private class DateChooserPropertyChangeListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			Date dateEntrada = new Date();
			try {
				dateEntrada = dateChooserEntrada.getDate();
				calEntrada = Calendar.getInstance();
				calEntrada.setTime(dateEntrada);
				mesEntrada = calEntrada.get(Calendar.MONTH);
				mesEntrada++;
				diaEntrada = calEntrada.get(Calendar.DAY_OF_MONTH);
				anoEntrada = calEntrada.get(Calendar.YEAR);
				comprobarNumNoches();
			} catch (Exception e) {
			}
		}
	}

	private class DateChooserSalidaPropertyChangeListener implements PropertyChangeListener {
		@SuppressWarnings("static-access")
		public void propertyChange(PropertyChangeEvent evt) {
			Date dateSalida = new Date();
			try {
				dateSalida = dateChooserSalida.getDate();
				calSalida = Calendar.getInstance();
				calSalida.setTime(dateSalida);
				mesSalida = calSalida.get(Calendar.MONTH);
				mesSalida++;
				diaSalida = calSalida.get(Calendar.DAY_OF_MONTH);
				anoSalida = calSalida.get(Calendar.YEAR);
				comprobarNumNoches();
			} catch (Exception e) {
			}
		}
	}
	
	private void comprobarDisponibilidadHabitacion() {
		String seleccionHabitacion = "";
		int num = 0;
		if (rdbtnDui.isSelected()) {
			seleccionHabitacion = "DUI";
		}
		if (rdbtnDb.isSelected()) {
			seleccionHabitacion = "DB";
		}
		if (rdbtnTri.isSelected()) {
			seleccionHabitacion = "TRI";
		}
		if (rdbtnSuite.isSelected()) {
			seleccionHabitacion = "SUITE";
		}
		try {
			num = io.comprobarHab(diaEntrada, mesEntrada, anoEntrada, diaSalida, mesSalida, anoSalida, seleccionHabitacion);
		} catch (Exception e) {
		}
		if (num<20) {
			disponible = true;
		} else {
			disponible = false;
		}
	}

	/**
	 * La inicialización de todas las variables y todos los apartados del jframe
	 */
	private void initApp() {
		// operaciones.cargarHabitaciones();
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dateChooserSalida = new JDateChooser();
		dateChooserSalida.addPropertyChangeListener(new DateChooserSalidaPropertyChangeListener());
		dateChooserSalida.setBounds(881, 240, 170, 20);
		contentPane.add(dateChooserSalida);

		dateChooserEntrada = new JDateChooser();
		dateChooserEntrada.addPropertyChangeListener(new DateChooserPropertyChangeListener());
		dateChooserEntrada.setBounds(881, 189, 170, 20);
		contentPane.add(dateChooserEntrada);

		lblComprobacionHabitacion = new JLabel("");
		lblComprobacionHabitacion.setFocusable(false);
		lblComprobacionHabitacion.setBounds(512, 347, 30, 30);
		contentPane.add(lblComprobacionHabitacion);

		btnSalir = new JLabel("");
		btnSalir.setFocusable(false);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalir.setToolTipText("Volver");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setIcon(new ImageIcon(".\\recursos\\goBackBW.png"));
		btnSalir.addMouseListener(new BtnSalirMouseListener());
		btnSalir.setBounds(900, 600, 128, 128);
		contentPane.add(btnSalir);

		btnPagar = new JLabel("");
		btnPagar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPagar.setIcon(new ImageIcon(".\\recursos\\goToPayBW.png"));
		btnPagar.setToolTipText("Proceder al pago");
		btnPagar.setHorizontalAlignment(SwingConstants.CENTER);
		btnPagar.setFocusable(false);
		btnPagar.addMouseListener(new BtnPagarMouseListener());
		btnPagar.setBounds(262, 600, 128, 128);
		contentPane.add(btnPagar);

		txtrImporteTotal = new JTextArea();
		txtrImporteTotal.setFocusable(false);
		txtrImporteTotal.setText("Importe Total:");
		txtrImporteTotal.setOpaque(false);
		txtrImporteTotal.setForeground(Color.WHITE);
		txtrImporteTotal.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrImporteTotal.setEditable(false);
		txtrImporteTotal.setBounds(691, 500, 180, 30);
		contentPane.add(txtrImporteTotal);

		textFieldImporte = new JTextField();
		textFieldImporte.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textFieldImporte.setFocusable(false);
		textFieldImporte.setEditable(false);
		textFieldImporte.setColumns(10);
		textFieldImporte.setBackground(Color.WHITE);
		textFieldImporte.setBounds(900, 500, 105, 30);
		contentPane.add(textFieldImporte);

		textFieldNumeroNoches = new JTextField();
		textFieldNumeroNoches.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textFieldNumeroNoches.setFocusable(false);
		textFieldNumeroNoches.setEditable(false);
		textFieldNumeroNoches.setColumns(10);
		textFieldNumeroNoches.setBackground(Color.WHITE);
		textFieldNumeroNoches.setBounds(900, 385, 40, 30);
		contentPane.add(textFieldNumeroNoches);

		txtrNumeroNoches = new JTextArea();
		txtrNumeroNoches.setFocusable(false);
		txtrNumeroNoches.setEditable(false);
		txtrNumeroNoches.setText("Numero Noches:");
		txtrNumeroNoches.setOpaque(false);
		txtrNumeroNoches.setForeground(Color.WHITE);
		txtrNumeroNoches.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrNumeroNoches.setBounds(691, 385, 180, 30);
		contentPane.add(txtrNumeroNoches);

		txtrFechaSalida = new JTextArea();
		txtrFechaSalida.setFocusable(false);
		txtrFechaSalida.setEditable(false);
		txtrFechaSalida.setForeground(Color.WHITE);
		txtrFechaSalida.setText("Fecha Salida:");
		txtrFechaSalida.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrFechaSalida.setBounds(691, 240, 180, 30);
		txtrFechaSalida.setOpaque(false);
		contentPane.add(txtrFechaSalida);

		txtrFechaEntrada = new JTextArea();
		txtrFechaEntrada.setFocusable(false);
		txtrFechaEntrada.setEditable(false);
		txtrFechaEntrada.setForeground(Color.WHITE);
		txtrFechaEntrada.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrFechaEntrada.setText("Fecha Entrada:");
		txtrFechaEntrada.setBounds(691, 189, 180, 30);
		txtrFechaEntrada.setOpaque(false);
		contentPane.add(txtrFechaEntrada);
		;

		rdbtnHombre = new JRadioButton("Hombre");
		buttonGroupSexo.add(rdbtnHombre);
		rdbtnHombre.setOpaque(false);
		rdbtnHombre.setForeground(Color.WHITE);
		rdbtnHombre.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnHombre.setBounds(200, 497, 136, 30);
		contentPane.add(rdbtnHombre);

		txtrSexo = new JTextArea();
		txtrSexo.setFocusable(false);
		txtrSexo.setText("Sexo:");
		txtrSexo.setOpaque(false);
		txtrSexo.setForeground(Color.WHITE);
		txtrSexo.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrSexo.setEditable(false);
		txtrSexo.setBounds(200, 460, 180, 30);
		contentPane.add(txtrSexo);

		rdbtnMujer = new JRadioButton("Mujer");
		buttonGroupSexo.add(rdbtnMujer);
		rdbtnMujer.setOpaque(false);
		rdbtnMujer.setForeground(Color.WHITE);
		rdbtnMujer.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnMujer.setBounds(344, 497, 136, 30);
		contentPane.add(rdbtnMujer);

		txtrTipoRegimen = new JTextArea();
		txtrTipoRegimen.setFocusable(false);
		txtrTipoRegimen.setText("Tipo Regimen:");
		txtrTipoRegimen.setOpaque(false);
		txtrTipoRegimen.setForeground(Color.WHITE);
		txtrTipoRegimen.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrTipoRegimen.setEditable(false);
		txtrTipoRegimen.setBounds(200, 385, 180, 30);
		contentPane.add(txtrTipoRegimen);

		rdbtnHa = new JRadioButton("HA");
		rdbtnHa.addMouseListener(new RdbtnHaMouseListener());
		buttonGroupRegimen.add(rdbtnHa);
		rdbtnHa.setOpaque(false);
		rdbtnHa.setForeground(Color.WHITE);
		rdbtnHa.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnHa.setBounds(200, 423, 70, 30);
		contentPane.add(rdbtnHa);

		rdbtnMp = new JRadioButton("MP");
		rdbtnMp.addMouseListener(new RdbtnMpMouseListener());
		buttonGroupRegimen.add(rdbtnMp);
		rdbtnMp.setOpaque(false);
		rdbtnMp.setForeground(Color.WHITE);
		rdbtnMp.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnMp.setBounds(344, 423, 70, 30);
		contentPane.add(rdbtnMp);

		rdbtnPc = new JRadioButton("PC");
		rdbtnPc.addMouseListener(new RdbtnPcMouseListener());
		buttonGroupRegimen.add(rdbtnPc);
		rdbtnPc.setOpaque(false);
		rdbtnPc.setForeground(Color.WHITE);
		rdbtnPc.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnPc.setBounds(416, 423, 90, 30);
		contentPane.add(rdbtnPc);

		rdbtnAd = new JRadioButton("AD");
		rdbtnAd.addMouseListener(new RdbtnAdMouseListener());
		buttonGroupRegimen.add(rdbtnAd);
		rdbtnAd.setOpaque(false);
		rdbtnAd.setForeground(Color.WHITE);
		rdbtnAd.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnAd.setBounds(272, 423, 70, 30);
		contentPane.add(rdbtnAd);

		textNombre = new JTextField();
		textNombre.addKeyListener(new TextNombreKeyListener());
		textNombre.addFocusListener(new TextNombreFocusListener());
		textNombre.setText("Nombre");
		cambiarTextoNombreDefault();
		textNombre.setBackground(Color.WHITE);
		textNombre.setBounds(310, 189, 170, 30);

		contentPane.add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.addKeyListener(new TextApellidoKeyListener());
		textApellido.addFocusListener(new TextApellidoFocusListener());
		textApellido.setText("Apellido");
		textApellido.setColumns(10);
		cambiarTextoApellidoDefault();
		textApellido.setBounds(310, 240, 170, 30);
		contentPane.add(textApellido);

		rdbtnTri = new JRadioButton("TRI");
		rdbtnTri.addMouseListener(new RdbtnTriMouseListener());
		rdbtnTri.setForeground(Color.WHITE);
		buttonGroupHabitaciones.add(rdbtnTri);
		rdbtnTri.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnTri.setOpaque(false);
		rdbtnTri.setBounds(344, 347, 70, 30);
		contentPane.add(rdbtnTri);

		rdbtnSuite = new JRadioButton("SUITE");
		rdbtnSuite.addMouseListener(new RdbtnSuiteMouseListener());
		rdbtnSuite.setForeground(Color.WHITE);
		buttonGroupHabitaciones.add(rdbtnSuite);
		rdbtnSuite.setOpaque(false);
		rdbtnSuite.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnSuite.setBounds(416, 347, 90, 30);
		contentPane.add(rdbtnSuite);

		rdbtnDb = new JRadioButton("DB");
		rdbtnDb.addMouseListener(new RdbtnDbMouseListener());
		rdbtnDb.setForeground(Color.WHITE);
		buttonGroupHabitaciones.add(rdbtnDb);
		rdbtnDb.setOpaque(false);
		rdbtnDb.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnDb.setBounds(272, 347, 70, 30);
		contentPane.add(rdbtnDb);

		rdbtnDui = new JRadioButton("DUI");
		rdbtnDui.addMouseListener(new RdbtnDuiMouseListener());
		rdbtnDui.setForeground(Color.WHITE);
		buttonGroupHabitaciones.add(rdbtnDui);
		rdbtnDui.setOpaque(false);
		rdbtnDui.setFont(new Font("Monospaced", Font.PLAIN, 17));
		rdbtnDui.setBounds(200, 347, 70, 30);
		contentPane.add(rdbtnDui);

		txtrTipoHabitacion = new JTextArea();
		txtrTipoHabitacion.setFocusable(false);
		txtrTipoHabitacion.setForeground(Color.WHITE);
		txtrTipoHabitacion.setText("Tipo Habitaci\u00F3n:");
		txtrTipoHabitacion.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrTipoHabitacion.setOpaque(false);
		txtrTipoHabitacion.setEditable(false);
		txtrTipoHabitacion.setBounds(200, 305, 180, 30);
		contentPane.add(txtrTipoHabitacion);

		txtrApellido = new JTextArea();
		txtrApellido.setFocusable(false);
		txtrApellido.setForeground(Color.WHITE);
		txtrApellido.setText("Apellido:");
		txtrApellido.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrApellido.setEditable(false);
		txtrApellido.setOpaque(false);
		txtrApellido.setBounds(200, 240, 100, 30);
		contentPane.add(txtrApellido);

		txtrNombre = new JTextArea();
		txtrNombre.setFocusable(false);
		txtrNombre.setForeground(Color.WHITE);
		txtrNombre.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrNombre.setText("Nombre:");
		txtrNombre.setOpaque(false);
		txtrNombre.setEditable(false);
		txtrNombre.setBounds(200, 189, 100, 30);
		contentPane.add(txtrNombre);

		label = new JLabel("New label");
		label.setFocusable(false);
		label.setIcon(new ImageIcon(".\\recursos\\fondo.jpg"));
		label.setBounds(0, 30, 1280, 720);
		contentPane.add(label);

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
		rdbtnDui.setSelected(true);
		rdbtnHa.setSelected(true);
		rdbtnHombre.setSelected(true);

		dateChooserEntrada.setMinSelectableDate(hoy);
		dateChooserSalida.setMinSelectableDate(hoy);
		// comprobarDisponibilidad();
	}
}
