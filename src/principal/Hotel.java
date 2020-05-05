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

import InputOutput.OperacionReserva;
import estaticos.Reserva;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
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
	private OperacionReserva operacion = new OperacionReserva();
	private JTextArea txtAreaReservas;
	private boolean fecha = false;
	private boolean fechaEntr = false;
	private boolean fechaSalid = false;
	private ArrayList<Reserva> listaReservas;
	@SuppressWarnings("rawtypes")
	private JComboBox listMesEntrada;
	private JTextArea textAreaDiaMesEntrada;
	@SuppressWarnings("rawtypes")
	private JComboBox listDiaEntrada;
	private JTextArea textAreaMesAnoEntrada;
	@SuppressWarnings("rawtypes")
	private JComboBox listAnoEntrada;
	@SuppressWarnings("rawtypes")
	private JComboBox listAnoSalida;
	private JTextArea textAreaMesAnoSalida;
	@SuppressWarnings("rawtypes")
	private JComboBox listDiaSalida;
	private JTextArea textAreaDiaMesSalida;
	@SuppressWarnings("rawtypes")
	private JComboBox listMesSalida;
	private int mesEntrada;
	private int mesSalida;
	private int diaEntrada;
	private int diaSalida;
	private int anoEntrada;
	private int anoSalida;
	boolean entrada;
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private final DefaultComboBoxModel dia = new DefaultComboBoxModel(new String[] { "Dia" });
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private final DefaultComboBoxModel dia31 = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel dia30 = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel dia29 = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel dia28 = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28" });
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private final DefaultComboBoxModel dias = new DefaultComboBoxModel(new String[] { "Dia" });
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private final DefaultComboBoxModel dia31s = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel dia30s = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel dia29s = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel dia28s = new DefaultComboBoxModel(
			new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel ano2021 = new DefaultComboBoxModel(new String[] { "Año", "2020", "2021" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel ano20 = new DefaultComboBoxModel(new String[] { "Año", "2020" });
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private final DefaultComboBoxModel ano21 = new DefaultComboBoxModel(new String[] { "Año", "2021" });

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
		initApp();
		operacion.cargarReservas();
		cargarReservas(operacion.cargarArrayHotel());
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

	/** Método para filtrar el fichero en busca de variables */

	private void conseguirTexto() {
		boolean porNombre, porApellido;
		comprobarFecha();
		listaReservas = operacion.devolverArray();
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
			System.out.println("jestem w fch");
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
				System.out.println("jestem w fch entr");
				String fechaEntrString = (diaEntrada + "/" + mesEntrada + "/" + anoEntrada);
				for (Reserva res : listaReservas) {
					System.out.println(fechaEntrString);
					System.out.println(res.getFechaEntrada());
					if (res.getFechaEntrada().equals(fechaEntrString))
						dumpArray.add(res);
				}
				listaReservas = new ArrayList<Reserva>();
				listaReservas = dumpArray;
				dumpArray = new ArrayList<Reserva>();
			}
			if (fechaSalid) {
				System.out.println("jestem w fch salid");
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
		@SuppressWarnings("rawtypes")
		Iterator it = listaReservas.iterator();
		while (it.hasNext()) {
			contenido += it.next() + "\n";
		}
		txtAreaReservas.setText(contenido);
	}

	/** Método para comprobar la fecha sí los dos campos no están rellenos */

	private void comprobarFechaEntrSalid() {
		if (anoSalida != 0 && mesSalida != 0 && diaSalida != 0) {
			fechaSalid = true;
		} else {
			fechaSalid = false;
		}
		if (anoEntrada != 0 && mesEntrada != 0 && diaEntrada != 0) {
			fechaEntr = true;
		} else {
			fechaEntr = false;
		}
	}

	/** Método para comprobar la fecha sí los dos campos están rellenos */

	private void comprobarFecha() {
		if ((anoSalida != 0 && mesSalida != 0 && diaSalida != 0)
				&& (anoEntrada != 0 && mesEntrada != 0 && diaEntrada != 0)) {
			fecha = true;
		} else {
			fecha = false;
		}
	}

	/** Método para comprobar el mes de las fechas */

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

	private class ListMesEntradaActionListener implements ActionListener {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e) {
			String mes = (String) listMesEntrada.getSelectedItem();
			if (!mes.equals("Mes")) {
				switch (mes) {
				case "Enero":
					listDiaEntrada.setModel(dia31);
					mesEntrada = 1;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Enero", "Febrero" }));
					break;
				case "Febrero":
					String ano = (String) listAnoEntrada.getSelectedItem();
					if (!ano.equals("Año")) {
						int anoInt = Integer.parseInt(ano);
						if ((((anoInt % 4 == 0) && !(anoInt % 100 == 0)) || (anoInt % 400 == 0))) {
							listDiaEntrada.setModel(dia29);
						} else {
							listDiaEntrada.setModel(dia28);
						}
					} else {
						listDiaEntrada.setModel(dia28);
					}
					mesEntrada = 2;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Febrero", "Marzo" }));
					break;
				case "Marzo":
					listDiaEntrada.setModel(dia31);
					mesEntrada = 3;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Marzo", "Abril" }));
					break;
				case "Abril":
					listDiaEntrada.setModel(dia30);
					mesEntrada = 4;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Abril", "Mayo" }));
					break;
				case "Mayo":
					listDiaEntrada.setModel(dia31);
					mesEntrada = 5;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Mayo", "Junio" }));
					break;
				case "Junio":
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Junio", "Julio" }));
					listDiaEntrada.setModel(dia30);
					mesEntrada = 6;
					break;
				case "Julio":
					listDiaEntrada.setModel(dia31);
					mesEntrada = 7;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Julio", "Agosto" }));
					break;
				case "Agosto":
					listDiaEntrada.setModel(dia31);
					mesEntrada = 8;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Agosto", "Septiembre" }));
					break;
				case "Septiembre":
					listDiaEntrada.setModel(dia30);
					mesEntrada = 9;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Septiembre", "Octubre" }));
					break;
				case "Octubre":
					listDiaEntrada.setModel(dia31);
					mesEntrada = 10;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Octubre", "Noviembre" }));
					break;
				case "Noviembre":
					listDiaEntrada.setModel(dia30);
					mesEntrada = 11;
					listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Noviembre", "Diciembre" }));
					break;
				case "Diciembre":
					listDiaEntrada.setModel(dia31);
					mesEntrada = 12;
					if (anoEntrada == 2020 && anoSalida == 2021) {
						listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Enero" }));
					} else {
						if (anoEntrada == 2020 && anoSalida == 2020) {
							listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Diciembre" }));
						} else {
							listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes" }));
						}
					}

					break;
				}

			} else {
				mesEntrada = 0;
				listDiaEntrada.setModel(dia);
			}
		}
	}

	private class ListAnoEntradaActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			String ano = (String) listAnoEntrada.getSelectedItem();
			if (!ano.equals("Año")) {
				anoEntrada = Integer.parseInt(ano);
				switch (anoEntrada) {
				case 2020:
					if (mesEntrada == 12) {
						listAnoSalida.setModel(ano2021);
					} else {
						if (mesEntrada == 2) {
							listDiaEntrada.setModel(dia29);
						}
						listAnoSalida.setModel(ano20);
					}

					break;
				case 2021:
					listAnoSalida.setModel(ano21);
					break;
				}
			} else {
				anoEntrada = 0;
			}
		}
	}

	private class ListMesSalidaActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			String mes = (String) listMesSalida.getSelectedItem();
			if (!mes.equals("Mes")) {
				switch (mes) {
				case "Enero":
					listDiaSalida.setModel(dia31s);
					mesSalida = 1;
					break;
				case "Febrero":
					String ano = (String) listAnoSalida.getSelectedItem();
					if (!ano.equals("Año")) {
						int anoInt = Integer.parseInt(ano);
						if ((((anoInt % 4 == 0) && !(anoInt % 100 == 0)) || (anoInt % 400 == 0))) {
							listDiaSalida.setModel(dia29s);
						} else {
							listDiaSalida.setModel(dia28s);
						}
					} else {
						listDiaSalida.setModel(dia28s);
					}
					mesSalida = 2;
					break;
				case "Marzo":
					listDiaSalida.setModel(dia31s);
					mesSalida = 3;
					break;
				case "Abril":
					listDiaSalida.setModel(dia30s);
					mesSalida = 4;
					break;
				case "Mayo":
					listDiaSalida.setModel(dia31s);
					mesSalida = 5;
					break;
				case "Junio":
					listDiaSalida.setModel(dia30s);
					mesSalida = 6;
					break;
				case "Julio":
					listDiaSalida.setModel(dia31s);
					mesSalida = 7;
					break;
				case "Agosto":
					listDiaSalida.setModel(dia31s);
					mesSalida = 8;
					break;
				case "Septiembre":
					listDiaSalida.setModel(dia30s);
					mesSalida = 9;
					break;
				case "Octubre":
					listDiaSalida.setModel(dia31s);
					mesSalida = 10;
					break;
				case "Noviembre":
					listDiaSalida.setModel(dia30s);
					mesSalida = 11;
					break;
				case "Diciembre":
					listDiaSalida.setModel(dia31s);
					mesSalida = 12;
					break;
				}

			} else {
				mesSalida = 0;
				listDiaSalida.setModel(dias);
			}
		}
	}

	private class ListAnoSalidaActionListener implements ActionListener {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e) {
			String ano = (String) listAnoSalida.getSelectedItem();
			if (!ano.equals("Año")) {
				anoSalida = Integer.parseInt(ano);
				if (mesEntrada == 12) {
					switch (anoSalida) {
					case 2020:
						listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Diciembre" }));
						break;
					case 2021:
						listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Enero" }));
						break;
					}
				}
			} else {
				anoSalida = 0;
			}
		}
	}

	private class ListDiaEntradaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String diaEntradaStr = (String) listDiaEntrada.getSelectedItem();
			if (!diaEntradaStr.equals("Dia")) {
				diaEntrada = Integer.parseInt(diaEntradaStr);
			} else {
				diaEntrada = 0;
			}
		}
	}

	private class ListDiaSalidaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String diaSalidaStr = (String) listDiaSalida.getSelectedItem();
			if (!diaSalidaStr.equals("Dia")) {
				diaSalida = Integer.parseInt(diaSalidaStr);
			} else {
				diaSalida = 0;
			}
		}
	}

	/** Método para iniciar todas las variables de la app */

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

		listMesEntrada = new JComboBox();
		listMesEntrada.addActionListener(new ListMesEntradaActionListener());
		listMesEntrada.setModel(new DefaultComboBoxModel(new String[] { "Mes", "Enero", "Febrero", "Marzo", "Abril",
				"Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
		listMesEntrada.setBounds(911, 183, 136, 30);
		contentPane.add(listMesEntrada);

		textAreaMesAnoEntrada = new JTextArea();
		textAreaMesAnoEntrada.setText("/");
		textAreaMesAnoEntrada.setOpaque(false);
		textAreaMesAnoEntrada.setForeground(Color.WHITE);
		textAreaMesAnoEntrada.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textAreaMesAnoEntrada.setFocusable(false);
		textAreaMesAnoEntrada.setEditable(false);
		textAreaMesAnoEntrada.setBounds(1136, 172, 30, 47);
		contentPane.add(textAreaMesAnoEntrada);

		listDiaEntrada = new JComboBox();
		listDiaEntrada.addActionListener(new ListDiaEntradaActionListener());
		listDiaEntrada.setBounds(1080, 183, 50, 30);
		listDiaEntrada.setModel(new DefaultComboBoxModel(new String[] { "Dia" }));
		contentPane.add(listDiaEntrada);

		textAreaDiaMesEntrada = new JTextArea();
		textAreaDiaMesEntrada.setText("/");
		textAreaDiaMesEntrada.setOpaque(false);
		textAreaDiaMesEntrada.setForeground(Color.WHITE);
		textAreaDiaMesEntrada.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textAreaDiaMesEntrada.setFocusable(false);
		textAreaDiaMesEntrada.setEditable(false);
		textAreaDiaMesEntrada.setBounds(1054, 172, 30, 47);
		contentPane.add(textAreaDiaMesEntrada);

		listAnoEntrada = new JComboBox();
		listAnoEntrada.addActionListener(new ListAnoEntradaActionListener());
		listAnoEntrada.setBounds(1161, 183, 80, 30);
		listAnoEntrada.setModel(new DefaultComboBoxModel(new String[] { "Año", "2020", "2021" }));
		contentPane.add(listAnoEntrada);

		listMesSalida = new JComboBox();
		listMesSalida.addActionListener(new ListMesSalidaActionListener());
		listMesSalida.setBounds(911, 241, 136, 30);
		listMesSalida.setModel(new DefaultComboBoxModel(new String[] { "Mes" }));
		contentPane.add(listMesSalida);

		listDiaSalida = new JComboBox();
		listDiaSalida.addActionListener(new ListDiaSalidaActionListener());
		listDiaSalida.setModel(new DefaultComboBoxModel(new String[] { "Dia" }));
		listDiaSalida.setBounds(1080, 241, 50, 30);
		contentPane.add(listDiaSalida);

		textAreaDiaMesSalida = new JTextArea();
		textAreaDiaMesSalida.setText("/");
		textAreaDiaMesSalida.setOpaque(false);
		textAreaDiaMesSalida.setForeground(Color.WHITE);
		textAreaDiaMesSalida.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textAreaDiaMesSalida.setFocusable(false);
		textAreaDiaMesSalida.setEditable(false);
		textAreaDiaMesSalida.setBounds(1054, 230, 30, 47);
		contentPane.add(textAreaDiaMesSalida);

		listAnoSalida = new JComboBox();
		listAnoSalida.addActionListener(new ListAnoSalidaActionListener());
		listAnoSalida.setModel(new DefaultComboBoxModel(new String[] { "Año" }));
		listAnoSalida.setBounds(1161, 241, 80, 30);
		contentPane.add(listAnoSalida);

		textAreaMesAnoSalida = new JTextArea();
		textAreaMesAnoSalida.setText("/");
		textAreaMesAnoSalida.setOpaque(false);
		textAreaMesAnoSalida.setForeground(Color.WHITE);
		textAreaMesAnoSalida.setFont(new Font("Monospaced", Font.PLAIN, 32));
		textAreaMesAnoSalida.setFocusable(false);
		textAreaMesAnoSalida.setEditable(false);
		textAreaMesAnoSalida.setBounds(1136, 230, 30, 47);
		contentPane.add(textAreaMesAnoSalida);

		txtAreaReservas = new JTextArea();
		txtAreaReservas.setBounds(326, 422, 793, 175);
		contentPane.add(txtAreaReservas);

		txtrFechaSalida = new JTextArea();
		txtrFechaSalida.setText("Fecha Salida:");
		txtrFechaSalida.setOpaque(false);
		txtrFechaSalida.setForeground(Color.WHITE);
		txtrFechaSalida.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrFechaSalida.setFocusable(false);
		txtrFechaSalida.setEditable(false);
		txtrFechaSalida.setBounds(721, 247, 180, 30);
		contentPane.add(txtrFechaSalida);

		txtrFechaEntrada = new JTextArea();
		txtrFechaEntrada.setText("Fecha Entrada:");
		txtrFechaEntrada.setOpaque(false);
		txtrFechaEntrada.setForeground(Color.WHITE);
		txtrFechaEntrada.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrFechaEntrada.setFocusable(false);
		txtrFechaEntrada.setEditable(false);
		txtrFechaEntrada.setBounds(721, 189, 180, 30);
		contentPane.add(txtrFechaEntrada);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalir.setBounds(762, 608, 128, 128);
		contentPane.add(btnSalir);
		btnAsignar.setBounds(576, 608, 128, 128);
		contentPane.add(btnAsignar);

		lblReserva = new JLabel("Reserva:");
		lblReserva.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblReserva.setFocusable(false);
		lblReserva.setForeground(Color.WHITE);
		lblReserva.setBounds(326, 113, 81, 29);
		contentPane.add(lblReserva);

		lblNombre = new JLabel("Nombre: ");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNombre.setFocusable(false);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(326, 176, 81, 30);
		contentPane.add(lblNombre);

		btnBorrarBusqueda = new JLabel("");
		btnBorrarBusqueda.setIcon(new ImageIcon(".\\recursos\\clearBW.png"));
		btnBorrarBusqueda.setToolTipText("Borrar filtros");
		btnBorrarBusqueda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBorrarBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
		btnBorrarBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBorrarBusqueda.setFocusable(false);
		btnBorrarBusqueda.setBounds(762, 288, 128, 128);
		btnBorrarBusqueda.addMouseListener(new BtnBorrarBusquedaMouseListener());
		contentPane.add(btnBorrarBusqueda);

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new TxtNombreFocusListener());
		txtNombre.setForeground(Color.GRAY);
		txtNombre.setText("Nombre");
		txtNombre.setColumns(10);
		txtNombre.setBounds(430, 176, 150, 30);
		contentPane.add(txtNombre);

		txtApellidos = new JTextField();
		txtApellidos.addFocusListener(new TxtApellidosFocusListener());
		txtApellidos.setForeground(Color.GRAY);
		txtApellidos.setText("Apellidos");
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(430, 247, 150, 30);
		contentPane.add(txtApellidos);

		txtCodigo = new JTextField();
		txtCodigo.addFocusListener(new TxtCodigoFocusListener());
		txtCodigo.setForeground(Color.GRAY);
		txtCodigo.setText("Codigo");
		txtCodigo.setBounds(430, 113, 150, 30);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		btnBuscar = new JLabel("");
		btnBuscar.setToolTipText("B\u00FAsqueda");
		btnBuscar.setIcon(new ImageIcon(".\\recursos\\searchBW.png"));
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuscar.setFocusable(false);
		btnBuscar.setBounds(576, 277, 128, 128);
		btnBuscar.addMouseListener(new BtnBuscarMouseListener());
		contentPane.add(btnBuscar);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblApellidos.setFocusable(false);
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setBounds(315, 245, 117, 30);
		contentPane.add(lblApellidos);

		lblNewLabel = new JLabel("");
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
