package principal;

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
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import InputOutput.IoDatos;
import estaticos.Usuario;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JLabel lblFondo;
	private JPanel panel;
	private JLabel lblNombreApp;
	private JLabel lblCerrar;
	private JLabel lblMinimizar;
	private JLabel lblMaximizar;
	private JTextField textFieldUser;
	private JLabel lblRegistro;
	private JLabel btnAnadirUser;
	private JLabel btnSignIn;
	private JCheckBox chckbxAdmin;
	private ArrayList<Usuario> vUsuarios;
	private boolean maximizado = false;
	private JLabel btnVerContrasena;
	private boolean mostrar;
	private JPasswordField pwdContrasena;
	private boolean escondida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {

		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
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
		textFieldUser.setBounds(457, 200, 368, 40);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setFocusable(false);
		lblRegistro.setForeground(new Color(184, 134, 11));
		lblRegistro.setFont(new Font("Monospaced", Font.PLAIN, 50));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setBounds(457, 62, 368, 70);
		contentPane.add(lblRegistro);

		btnAnadirUser = new JLabel("");
		btnAnadirUser.addMouseListener(new BtnAnadirUserMouseListener());
		btnAnadirUser.setToolTipText("Añadir usuario");
		btnAnadirUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnadirUser.setIcon(new ImageIcon(".\\recursos\\addUserBW.png"));
		btnAnadirUser.setHorizontalAlignment(SwingConstants.CENTER);
		btnAnadirUser.setFocusable(false);
		btnAnadirUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAnadirUser.setBounds(406, 550, 128, 128);
		contentPane.add(btnAnadirUser);

		btnSignIn = new JLabel("");
		btnSignIn.setToolTipText("Volver a Login");
		btnSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignIn.setIcon(
				new ImageIcon(".\\recursos\\goBackBW.png"));
		btnSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		btnSignIn.setFocusable(false);
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignIn.setBounds(751, 550, 128, 128);
		btnSignIn.addMouseListener(new BtnSignInMouseListener());

		contentPane.add(btnSignIn);

		chckbxAdmin = new JCheckBox(" Administrador");
		chckbxAdmin.setForeground(SystemColor.window);
		chckbxAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxAdmin.setOpaque(false);
		chckbxAdmin.setFont(new Font("Monospaced", Font.PLAIN, 20));
		chckbxAdmin.setBounds(457, 400, 368, 40);
		contentPane.add(chckbxAdmin);

		btnVerContrasena = new JLabel("");
		btnVerContrasena.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerContrasena.setIcon(new ImageIcon(".\\recursos\\viewPassBW.png"));
		btnVerContrasena.setToolTipText("Mostrar password");
		btnVerContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		btnVerContrasena.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVerContrasena.setBounds(845, 264, 64, 64);
		btnVerContrasena.addMouseListener(new BtnVerContrasenaMouseListener());
		contentPane.add(btnVerContrasena);

		pwdContrasena = new JPasswordField();
		pwdContrasena.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pwdContrasena.addFocusListener(new PwdContrasenaFocusListener());
		pwdContrasena.setEchoChar((char)0);
		pwdContrasena.setText("Contraseña");
		pwdContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		pwdContrasena.setBounds(457, 275, 368, 40);
		contentPane.add(pwdContrasena);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(".\\recursos\\fondo.jpg"));
		lblFondo.setBounds(0, 30, 1280, 720);
		contentPane.add(lblFondo);
		lblFondo.setFocusable(true);
		lblFondo.requestFocus();
		vUsuarios = IoDatos.leerDatos();
		pwdContrasena.setVisible(true);
		escondida = false;
		mostrar = false;

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
			if (textFieldUser.getText().equals("Usuario")) {
				textFieldUser.setText("");
			}

			lblFondo.setFocusable(false);
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			if (textFieldUser.getText().equals("")) {
				textFieldUser.setText("Usuario");
			}
		}
	}

	private class BtnSignInMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			LogIn log = new LogIn();
			log.setVisible(true);
			dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btnSignIn.setIcon(new ImageIcon(".\\recursos\\goBack.png"));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btnSignIn.setIcon(new ImageIcon(".\\recursos\\goBackBW.png"));
		}
	}

	


	private void esconderContrasena() {
		char[] contrChar = pwdContrasena.getPassword();
		String contr = "";
		for (int i=0;i<contrChar.length;i++) {
			contr += contrChar[i];
		}
		if (!contr.equals("Contraseña")) {
			pwdContrasena.setEchoChar('*');
		}
	}
	private void ensenarContrasena() {
		pwdContrasena.setEchoChar((char)0);
	}
	private class PwdContrasenaFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			char[] contrChar = pwdContrasena.getPassword();
			String contr = "";
			for (int i=0;i<contrChar.length;i++) {
				contr += contrChar[i];
			}
			if (contr.equals("Contraseña")) {
				pwdContrasena.setText("");
				if (!escondida) {
					esconderContrasena();
					escondida = true;
				}
			}
			
		}
		@Override
		public void focusLost(FocusEvent e) {
			char[] contrChar = pwdContrasena.getPassword();
			String contr = "";
			for (int i=0;i<contrChar.length;i++) {
				contr += contrChar[i];
			}
			if (contr.equals("")) {
				pwdContrasena.setText("Contraseña");
			}
		}
	}

	private class BtnAnadirUserMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			char[] contrChar = pwdContrasena.getPassword();
			String contr = "";
			for (int i=0;i<contrChar.length;i++) {
				contr += contrChar[i];
			}
			// ERROR CONTRASEĹ�A
			Pattern pPasswd = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,16}$");
			Matcher mPasswd = pPasswd.matcher(contr);

			if (!mPasswd.matches()) {
				JOptionPane.showMessageDialog(null, "La contraseña no cumple los requisitos");
				return;
			}
			// FIN CORRECCIĂ“N ERROR CONTRASEĹ�A
			for (Usuario usuario : vUsuarios) {
				if (usuario.getNombreUsuario().equals(textFieldUser.getText())) {
					JOptionPane.showMessageDialog(null, "El usuario indicado ya fue introducido anteriormente");
					textFieldUser.setText("Usuario");
					pwdContrasena.setText("");
					chckbxAdmin.setSelected(false);
					return;
				}
			}

			JOptionPane.showMessageDialog(null, "El nuevo usuario fue introducido");

			Usuario usu = new Usuario(textFieldUser.getText(), contr, chckbxAdmin.isSelected());
			vUsuarios.add(usu);

			IoDatos.guardarUsusarios(vUsuarios);
			textFieldUser.setText("Usuario");
			pwdContrasena.setText("");
			chckbxAdmin.setSelected(false);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btnAnadirUser.setIcon(new ImageIcon(".\\recursos\\addUser.png"));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnAnadirUser.setIcon(new ImageIcon(".\\recursos\\addUserBW.png"));
		}
	}
	private class BtnVerContrasenaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (escondida) {
				btnVerContrasena.setIcon(new ImageIcon(".//recursos//viewPass.png"));
				btnVerContrasena.setToolTipText("Ocultar password");
				ensenarContrasena();
				escondida = false;
			} else {
				btnVerContrasena.setIcon(new ImageIcon(".//recursos//viewPassBW.png"));
				btnVerContrasena.setToolTipText("Mostrar password");
				esconderContrasena();
				escondida = true;
			}
		}
	}
}
