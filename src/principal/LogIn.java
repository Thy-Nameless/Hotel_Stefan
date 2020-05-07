package principal;

//Version 3

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

import InputOutput.IoBBDD;
import estaticos.Usuario;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import com.toedter.calendar.JCalendar;

import InputOutput.IoReservas;
import estaticos.Usuario;
import java.text.SimpleDateFormat;

@SuppressWarnings({ "unused", "serial" })
public class LogIn extends JFrame {

	private JPanel contentPane;
	private JLabel lblFondo;
	private JPanel panel;
	private JLabel lblNombreApp;
	private JLabel lblCerrar;
	private JLabel lblMinimizar;
	private JLabel lblMaximizar;
	private JTextField textFieldUser;
	private JPasswordField passwordFieldPass;
	private JLabel lblLogin;
	private JLabel btnEntrar;
	private JLabel btnSignup;
	private boolean maximizado = false;
	private Point initialClick;
	private IoReservas io;
	private ArrayList<Usuario> usus = new ArrayList<Usuario>();
	private IoBBDD bbdd;
	// private ArrayList<Usuario> vUsuarios;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
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
	public LogIn() {
		initApp();
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

	private class TextFieldUserFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent arg0) {
			textFieldUser.setText("");
			lblFondo.setFocusable(false);
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			if (textFieldUser.getText().equals("")) {
				textFieldUser.setText("Usuario");
			}
		}
	}

	private class BtnSignupMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Registro reg = new Registro();
			reg.setVisible(true);
			dispose();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btnSignup.setIcon(new ImageIcon(".\\recursos\\register.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnSignup.setIcon(new ImageIcon(".\\recursos\\registerBW.png"));
		}
	}

	private class PasswordFieldFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent arg0) {
			passwordFieldPass.setText("");
		}

		@Override
		public void focusLost(FocusEvent arg0) {

			if (String.copyValueOf(passwordFieldPass.getPassword()).equals("")) {
				passwordFieldPass.setText("Contraseña");
			}

			if ((passwordFieldPass.getPassword().toString().equals(""))
					&& (passwordFieldPass.getPassword().toString().equals("Contraseña"))) {
				passwordFieldPass.setText(passwordFieldPass.getPassword().toString());
			}

		}
	}
	
	private class BtnEntrarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if ((textFieldUser.getText().equals("") || String.copyValueOf(passwordFieldPass.getPassword()).equals(""))
					|| (textFieldUser.getText().equals("Usuario")
							|| String.copyValueOf(passwordFieldPass.getPassword()).equals("Contraseña"))) {
				JOptionPane.showMessageDialog(null, "Valores no introducidos");
			}

			ArrayList<Usuario> vUsuarios = io.devolverUsuarios();
			String contrasena = String.valueOf(passwordFieldPass.getPassword());
			for (Usuario user : vUsuarios) {
				if (user.getNombreUsuario().equals(textFieldUser.getText()) && user.getPassword().equals(contrasena)) {
					if (user.isEsAdmin()) {
						Hotel hotel = new Hotel();
						hotel.setVisible(true);
						dispose();
						break;
					}
					if (!user.isEsAdmin()) {
						String usuario = textFieldUser.getText();
						ReservasCliente cliente = new ReservasCliente(usuario);
						cliente.setVisible(true);
						dispose();
						break;
					}
				}
			} 
			JOptionPane.showMessageDialog(null,"Los datos del usuario introducidos no coinciden o este no esta registrado");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btnEntrar.setIcon(new ImageIcon(".\\recursos\\enter.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnEntrar.setIcon(new ImageIcon(".\\recursos\\enterBW.png"));
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
	
	public void initApp() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.addMouseListener(new PanelMouseListener());
		panel.setBackground(Color.WHITE);
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 0, 1280, 30);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNombreApp = new JLabel("Hotel Stefan *****");
		lblNombreApp.setBounds(538, 0, 212, 30);
		lblNombreApp.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombreApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreApp.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		lblNombreApp.setForeground(new Color(184, 134, 11));
		panel.add(lblNombreApp);

		lblCerrar = new JLabel("");
		lblCerrar.setBounds(22, 9, 14, 14);
		lblCerrar.addMouseListener(new LblCerrarMouseListener());
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setToolTipText("Cerrar");
		lblCerrar.setIcon(new ImageIcon(".\\recursos\\close.png"));
		panel.add(lblCerrar);

		lblMinimizar = new JLabel("");
		lblMinimizar.setBounds(46, 9, 14, 14);
		lblMinimizar.addMouseListener(new LblMinimizarMouseListener());
		lblMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setToolTipText("Minimizar");
		lblMinimizar.setIcon(new ImageIcon(".\\recursos\\minimize.png"));
		panel.add(lblMinimizar);

		lblMaximizar = new JLabel("");
		lblMaximizar.setBounds(70, 9, 14, 14);
		lblMaximizar.addMouseListener(new LblMaximizarMouseListener());
		lblMaximizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMaximizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaximizar.setToolTipText("Maximizar");
		lblMaximizar.setIcon(new ImageIcon(".\\recursos\\maximize.png"));
		panel.add(lblMaximizar);

		textFieldUser = new JTextField();
		textFieldUser.addFocusListener(new TextFieldUserFocusListener());
		textFieldUser.setText("Usuario");
		textFieldUser.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUser.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textFieldUser.setBounds(432, 250, 400, 40);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		passwordFieldPass = new JPasswordField();
		passwordFieldPass.addFocusListener(new PasswordFieldFocusListener());
		passwordFieldPass.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldPass.setEchoChar('*');
		passwordFieldPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordFieldPass.setBounds(432, 350, 400, 40);
		contentPane.add(passwordFieldPass);
		passwordFieldPass.setText("Contraseña");

		lblLogin = new JLabel("Acceso");
		lblLogin.setFocusable(false);
		lblLogin.setForeground(new Color(184, 134, 11));
		lblLogin.setFont(new Font("Monospaced", Font.PLAIN, 50));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(433, 95, 399, 70);
		contentPane.add(lblLogin);

		btnEntrar = new JLabel("");
		btnEntrar.setIcon(new ImageIcon(".\\recursos\\enterBW.png"));
		btnEntrar.addMouseListener(new BtnEntrarMouseListener());
		btnEntrar.setToolTipText("LogIn");
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnEntrar.setFocusable(false);
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEntrar.setBounds(380, 511, 128, 128);
		contentPane.add(btnEntrar);

		btnSignup = new JLabel("");
		btnSignup.setIcon(new ImageIcon(".\\recursos\\registerBW.png"));
		btnSignup.setToolTipText("Registrar usuario");
		btnSignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignup.setFocusable(false);
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignup.setBounds(762, 511, 128, 128);
		btnSignup.addMouseListener(new BtnSignupMouseListener());
		contentPane.add(btnSignup);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(".\\recursos\\fondo.jpg"));
		lblFondo.setBounds(0, 30, 1280, 720);
		contentPane.add(lblFondo);
		lblFondo.setFocusable(true);
		lblFondo.requestFocus();
		// vUsuarios = IoDatos.leerDatos();
		
		bbdd = new IoBBDD();
	}
}