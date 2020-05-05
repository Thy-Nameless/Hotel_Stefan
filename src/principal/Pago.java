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
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import InputOutput.OperacionHabitacion;
import estaticos.Reserva;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pago extends JFrame {

	private JLabel lblReservar;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNombreApp;
	private JLabel lblCerrar;
	private JLabel lblMinimizar;
	private JLabel lblMaximizar;
        private boolean maximizado = false;
        private OperacionHabitacion operaciones = new OperacionHabitacion();
        private JTextArea textNom;
        private JTextArea textApe;
        private JTextArea textDireccion;
        private JTextArea textNum;
        private JTextField textFieldNom;
        private JTextField textFieldApe;
        private JTextField textFieldNum;
        private JTextArea textCVV;
        private JTextField textFieldCVV;
        private JLabel btnNewButton;
        private JTextArea textArea;
        private String usuarioReserva;
        private Reserva reservaRealizada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pago frame = new Pago();
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
	public Pago() {
		initApp();
	}
	public Pago(String usuario, Reserva reserva) {
		initApp();
		usuarioReserva = usuario;
		reservaRealizada = reserva;
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
                        maximizado=true;
                    } else {
                        setExtendedState(JFrame.NORMAL);
                        maximizado=false;
                    }
		}
	}
	private class BtnNewButtonMouseListener extends MouseAdapter {
		@Override
		/** Comprobaciones generales de longitud de código de tarjeta, cvc y su validad */
		public void mouseClicked(MouseEvent e) {
			Pattern pattern = Pattern.compile("[0-9]{4}\\s[0-9]{4}\\s[0-9]{4}\\s[0-9]{4}");
			Matcher matcher = pattern.matcher(textFieldNum.getText());
			if (matcher.matches()) {
				pattern = Pattern.compile("[0-9]{3}");
				matcher = pattern.matcher(textFieldCVV.getText());
				if (matcher.matches()) {
					JOptionPane.showMessageDialog(rootPane, "Pago realizado con éxito");
					new ReservasCliente(usuarioReserva,reservaRealizada).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(rootPane, "El código CVC debe estar formado por 3 dígitos.");
					textFieldCVV.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(rootPane, "El número de tarjeta solo puede contener dígitos en bloques de 4 debidamente espaciados (0000 0000 0000 0000)");
				textFieldNum.setText("");
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btnNewButton.setIcon(new ImageIcon(".\\recursos\\payment.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnNewButton.setIcon(new ImageIcon(".\\recursos\\paymentBW.png"));
		}
	}
	private class TextFieldNumKeyListener extends KeyAdapter {
		@Override
		/** Este método permite la comprobación de sí el carácter ingresado es un número, excede el rango de la tarjeta, o es una letra o caracter especial */
		public void keyReleased(KeyEvent e) {
			int teclaPulsada = e.getKeyCode();
			int longt = 0;
			String tc = textFieldNum.getText();
			longt = tc.length();
			String txtmodif = "";
			switch (teclaPulsada) {
				case 8:
					if (longt ==  5 || longt == 10 || longt == 15) {
						switch (longt) {
							case 5:
								txtmodif = tc.substring(0,4);
								break;
							case 10:
								txtmodif = tc.substring(0,9);
								break;
							case 15:
								txtmodif = tc.substring(0,14);
								break;
						}
					} else {
						txtmodif = tc;
					}
					break;
					
				case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 96: case 97: case 98: case 99: case 100: case 101: case 102: case 103: case 104: case 105:
					if (longt == 20) {
						txtmodif = tc.substring(0,19);
					} else {
						if (longt ==  4 || longt == 9 || longt == 14) {
							txtmodif = tc + " ";
							System.out.println(txtmodif);
						} else {
							txtmodif = tc;
						}
					}
					break;
				case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79: case 80:
				case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90: case 44: case 45: case 46: case 47: case 32: case 59:
				case 61: case 91: case 92: case 93: case 106: case 107: case 108: case 109: case 110: case 111: case 150: case 151: case 152: case 160: case 161:
				case 162: case 192: case 512: case 513: case 514: case 515: case 516: case 517: case 518: case 519: case 520: case 521: case 522: case 523:
					txtmodif = tc.substring(0,tc.length()-1);
					break;
				default:
					txtmodif = tc;
					break;
			}
			textFieldNum.setText(txtmodif);
		}
	}
	private class TextFieldCVVKeyListener extends KeyAdapter {
		@Override
		/** Este método permite la comprobación de sí el carácter ingresado es un número, excede el rango de la tarjeta, o es una letra o caracter especial */
		public void keyReleased(KeyEvent e) {
			int teclaPulsada = e.getKeyCode();
			int longt = 0;
			String tc = textFieldCVV.getText();
			longt = tc.length();
			String txtmodif = "";
			switch (teclaPulsada) {
				case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 96: case 97: case 98: case 99: case 100: case 101: case 102: case 103: case 104: case 105:
					if (longt == 4) {
						txtmodif = tc.substring(0,3);
					} else {
						txtmodif = tc;
					}
					break;
				case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79: case 80:
				case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90: case 44: case 45: case 46: case 47: case 32: case 59:
				case 61: case 91: case 92: case 93: case 106: case 107: case 108: case 109: case 110: case 111: case 150: case 151: case 152: case 160: case 161:
				case 162: case 192: case 512: case 513: case 514: case 515: case 516: case 517: case 518: case 519: case 520: case 521: case 522: case 523:
					txtmodif = tc.substring(0,tc.length()-1);
					break;
				default:
					txtmodif = tc;
					break;
			}
			textFieldCVV.setText(txtmodif);
		}
	}
	/** Realización de la comprobación del código CVC */
	public boolean testComprobacionNumeroTarjetaYcvc(String numeroTarjeta, String cvc) {
		Pattern pattern = Pattern.compile("[0-9]{4}\\s[0-9]{4}\\s[0-9]{4}\\s[0-9]{4}");
		Matcher matcher = pattern.matcher(numeroTarjeta);
		if (matcher.matches()) {
			pattern = Pattern.compile("[0-9]{3}");
			matcher = pattern.matcher(cvc);
			if (matcher.matches()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/** Inicialización de todos los apartados del programa */
        private void initApp() {
            operaciones.cargarHabitaciones();
            setResizable(false);
    		setUndecorated(true);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setBounds(100, 100, 1280, 750);
    		contentPane = new JPanel();
    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    		setContentPane(contentPane);
    		contentPane.setLayout(null);
    		
    		textArea = new JTextArea();
    		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textArea.setWrapStyleWord(true);
    		textArea.setRows(19);
    		textArea.setBounds(680, 239, 300, 120);
    		contentPane.add(textArea);
    		
    		btnNewButton = new JLabel("");
    		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    		btnNewButton.setIcon(new ImageIcon(".\\recursos\\paymentBW.png"));
    		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
    		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
    		btnNewButton.setToolTipText("Pagar");
    		btnNewButton.setFocusable(false);
    		btnNewButton.addMouseListener(new BtnNewButtonMouseListener());
    		btnNewButton.setBounds(550, 500, 128, 128);
    		contentPane.add(btnNewButton);
    		
    		textFieldCVV = new JTextField();
    		textFieldCVV.addKeyListener(new TextFieldCVVKeyListener());
    		textFieldCVV.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textFieldCVV.setColumns(10);
    		textFieldCVV.setBounds(840, 400, 140, 30);
    		contentPane.add(textFieldCVV);
    		
    		textCVV = new JTextArea();
    		textCVV.setFocusable(false);
    		textCVV.setForeground(Color.WHITE);
    		textCVV.setOpaque(false);
    		textCVV.setText("CVV:");
    		textCVV.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textCVV.setBounds(780, 400, 50, 30);
    		contentPane.add(textCVV);
    		
    		textFieldNum = new JTextField();
    		textFieldNum.addKeyListener(new TextFieldNumKeyListener());
    		textFieldNum.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textFieldNum.setColumns(10);
    		textFieldNum.setBounds(520, 400, 250, 30);
    		contentPane.add(textFieldNum);
    		
    		textFieldApe = new JTextField();
    		textFieldApe.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textFieldApe.setColumns(10);
    		textFieldApe.setBounds(680, 198, 300, 30);
    		contentPane.add(textFieldApe);
    		
    		textFieldNom = new JTextField();
    		textFieldNom.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textFieldNom.setBounds(680, 152, 300, 30);
    		contentPane.add(textFieldNom);
    		textFieldNom.setColumns(10);
    		
    		textNum = new JTextArea();
    		textNum.setFocusable(false);
    		textNum.setForeground(Color.WHITE);
    		textNum.setOpaque(false);
    		textNum.setText("Numero Tarjeta:");
    		textNum.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textNum.setBounds(300, 400, 190, 30);
    		contentPane.add(textNum);
    		
    		textDireccion = new JTextArea();
    		textDireccion.setFocusable(false);
    		textDireccion.setForeground(Color.WHITE);
    		textDireccion.setOpaque(false);
    		textDireccion.setText("Direccion Titular Tarjeta:");
    		textDireccion.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textDireccion.setBounds(300, 232, 300, 30);
    		contentPane.add(textDireccion);
    		
    		textApe = new JTextArea();
    		textApe.setFocusable(false);
    		textApe.setForeground(Color.WHITE);
    		textApe.setOpaque(false);
    		textApe.setText("Apellido Titular Tarjeta:");
    		textApe.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textApe.setBounds(300, 191, 300, 30);
    		contentPane.add(textApe);
    		
    		textNom = new JTextArea();
    		textNom.setFocusable(false);
    		textNom.setForeground(Color.WHITE);
    		textNom.setOpaque(false);
    		textNom.setFont(new Font("Monospaced", Font.PLAIN, 18));
    		textNom.setText("Nombre Titular Tarjeta:");
    		textNom.setBounds(300, 150, 300, 30);
    		contentPane.add(textNom);
    		
    		lblReservar = new JLabel("New label");
    		lblReservar.setFocusable(false);
    		lblReservar.setIcon(new ImageIcon(".\\recursos\\fondo.jpg"));
    		lblReservar.setBounds(0, 30, 1280, 720);
    		contentPane.add(lblReservar);
    		
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
