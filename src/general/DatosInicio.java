package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import util.Apariencia;
import util.Cadena;
import util.Fecha;
import util.JTextFieldFecha;


public class DatosInicio {
	JDialog pantallaDi;
	JLabel lEmpresa, lFecha, lUsuario, lPassword, lNombreEmpresa;
	JTextField tfEmpresa;
	JTextFieldFecha tfFecha;
	JPasswordField pfUsuario, pfPassword;
	JButton bCorrecto, bFinPrograma, bCalendario;
	MysqlConnect m = null;

	DatosInicio() {
		m = MysqlConnect.getDbCon();

		Fecha fec = new Fecha();

		pantallaDi = new JDialog();
		pantallaDi.setModal(true);
		pantallaDi.setSize(300, 200);
		pantallaDi
				.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		pantallaDi.setLayout(null);

		lEmpresa = new JLabel("Empresa");
		lEmpresa.setBounds(10, 10, 80, 20);

		tfEmpresa = new JTextField();
		tfEmpresa.setBounds(100, 10, 30, 20);
		tfEmpresa.addKeyListener(new EmpresaListener());
		tfEmpresa.addFocusListener(new EmpresaListener());

		lNombreEmpresa = new JLabel("");
		lNombreEmpresa.setBounds(150, 10, 200, 20);

		lFecha = new JLabel("Fecha");
		lFecha.setBounds(10, 35, 80, 20);

		bCalendario = new JButton();
		bCalendario.setBounds(70, 35, 20, 20);
		bCalendario.setIcon(new ImageIcon(getClass().getResource(
				"/Imagenes/icono_calendario.png")));
		bCalendario.addActionListener(new BotonCalendarioListener());

		// Cambiar
		// tfFecha = new JTextField();
		tfFecha = new JTextFieldFecha();
		tfFecha.setBounds(100, 35, 90, 20);
		tfFecha.setText(Cadena.fechaAcadena(Fecha.fechaDia()));
		// tfFecha.addKeyListener(new FechaListener());

		lUsuario = new JLabel("Usuario");
		lUsuario.setBounds(10, 60, 80, 20);

		pfUsuario = new JPasswordField();
		pfUsuario.setBounds(100, 60, 100, 20);
		pfUsuario.addActionListener(new UsuarioListener());

		lPassword = new JLabel("Password");
		lPassword.setBounds(10, 85, 80, 20);
		lPassword.setVisible(false);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(100, 85, 100, 20);
		pfPassword.addActionListener(new PasswordListener());
		pfPassword.setVisible(false);

		bCorrecto = new JButton("Correcto");
		bCorrecto.setBounds(30, 135, 100, 20);
		bCorrecto.addActionListener(new BotonListener());
		bCorrecto.setEnabled(false);

		bFinPrograma = new JButton("Fin Programa");
		bFinPrograma.setBounds(140, 135, 100, 20);
		bFinPrograma.addActionListener(new BotonListener());

		pantallaDi.add(lEmpresa);
		pantallaDi.add(tfEmpresa);
		pantallaDi.add(lNombreEmpresa);
		pantallaDi.add(lFecha);
		pantallaDi.add(bCalendario);
		pantallaDi.add(tfFecha);
		pantallaDi.add(lUsuario);
		pantallaDi.add(pfUsuario);
		pantallaDi.add(lPassword);
		pantallaDi.add(pfPassword);
		pantallaDi.add(bCorrecto);
		pantallaDi.add(bFinPrograma);
		pantallaDi.setVisible(true);

	}

	public void compruebaEmpresa() {
		ResultSet rs = null;
		boolean noError = true;

		String empresa = tfEmpresa.getText();

		// Por si acaso la empresa siempre en mayúsculas
		empresa = empresa.toUpperCase();
		// Ejecutamos la consulta
		if (empresa.length() == 2) {
			try {
				rs = m.query("SELECT * FROM SISTEM WHERE EMPRESA = '" + empresa
						+ "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Error en lectura fichero de Sistema!!!");
				noError = false;
			}

			if (noError == true) {
				try {
					boolean existeReg = false;

					while (rs.next() == true) {
						existeReg = true;

						lNombreEmpresa.setText(rs.getString("SISTEM_NOMBRE"));
						new DatosComunes(rs.getString("SISTEM_EMPRESA_CENTRAL"));
						tfFecha.requestFocus(true);
						tfFecha.selectAll();
					}
					if (existeReg == false) {
						// System.out.println("No existe la empresa, CREARLA!!!");
						JOptionPane.showMessageDialog(null,
								"No existe la empresa, CREARLA!!!");
						tfEmpresa.requestFocus();
					}

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"Error en lectura fichero de Sistema!!!");
					e.printStackTrace();
				}
			}
		}
	}

	private boolean existeUsuario(String usuario) {

		ResultSet rs = null;
		boolean existeUsuario = false;

		usuario = usuario.toUpperCase();

		if (usuario.length() > 0) {
			try {
				rs = m.query("SELECT * FROM ACCUSU WHERE EMPRESA = '"
						+ DatosComunes.eEmpresa + "' AND ACCUSU_USUARIO = '"
						+ usuario + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Error en lectura fichero de Usuarios!!!");
			}

			try {
				if (!rs.next())
					existeUsuario = false;
				else {
					DatosComunes.usuario = rs.getString("ACCUSU_USUARIO")
							.trim();
					DatosComunes.usuarioN = rs.getInt("ACCUSU_USUARION");
					DatosComunes.usuarioPassword = rs.getString(
							"ACCUSU_PASSWORD").trim();
					DatosComunes.centroCont = rs.getInt("ACCUSU_CENTRO_CONT");
					DatosComunes.centroGest = rs.getInt("ACCUSU_CENTRO_GEST");
                    if(DatosComunes.sisTienda == 1){
                    	DatosComunes.centroCont = 1;
                    	DatosComunes.centroGest = 1;
                    }
					DatosComunes.nivelAccont = rs.getInt("ACCUSU_NIVEL_ACCONT");
					DatosComunes.nivelAcgest = rs.getInt("ACCUSU_NIVEL_ACGEST");
					DatosComunes.nivelAclogi = rs.getInt("ACCUSU_NIVEL_LOGIST");
					if (DatosComunes.usuarioPassword.length() > 0) {
						lPassword.setVisible(true);
						pfPassword.setVisible(true);
						bCorrecto.setEnabled(false);
					} else {
						lPassword.setVisible(false);
						pfPassword.setVisible(false);
						bCorrecto.setEnabled(true);
					}

					existeUsuario = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Usuario incorrectos!!!");
			}
		}

		return existeUsuario;
	}

	class UsuarioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String usuario = new String(pfUsuario.getPassword());

			if (usuario.equals("copoliyo"))
				System.out.println(usuario);
			else {
				if (existeUsuario(usuario))
					// System.out.println("El usuario " + usuario +
					// " es válido.");
					pfUsuario.transferFocus();
				else
					pfUsuario.setText("");
			}

		}
	}

	class PasswordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String password = new String(pfPassword.getPassword());

			if (password.equalsIgnoreCase(DatosComunes.usuarioPassword)) {
				bCorrecto.setEnabled(true);
				pfPassword.transferFocus();
			}

			else
				bCorrecto.setEnabled(false);

		}
	}

	class BotonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			String usuario = new String(pfUsuario.getPassword());
			String password = new String(pfPassword.getPassword());

			ponIvaSegunFecha();

			usuario = usuario.toUpperCase();
			if (arg0.getSource() == bFinPrograma)
				System.exit(0);
			if (arg0.getSource() == bCorrecto) {
				// Si en el campo de usuario hay algo que no son espacios
				if (usuario.trim().length() > 0) {

					if (DatosComunes.usuario.equalsIgnoreCase(usuario)) {
						// Si el usuario tiene password
						if (DatosComunes.usuarioPassword.trim().length() > 0) {
							// Comprobamos el password
							if (DatosComunes.usuarioPassword.trim() == password) {
								pantallaDi.dispose();
							} else {
								bCorrecto.setEnabled(false);
								pfPassword.setText("");
							}
						}
						pantallaDi.dispose();
					}
				}
			}
		}
	}

	class BotonCalendarioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int fecha;

			Calendario cal = new Calendario();
			fecha = cal.getFecha();
			if (fecha != 0) {
				tfFecha.setText(Cadena.fechaAcadena(fecha));
			}
			pfUsuario.requestFocus(true);
			pfUsuario.selectAll();
		}
	}

	class EmpresaListener implements KeyListener, FocusListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// Leemos la tecla y la pasamos a mayúscula
			char caracter = Character.toUpperCase(arg0.getKeyChar());

			// Las empresas sólo pueden tener dos letras para identificarse
			if (tfEmpresa.getText().length() > 1)
				arg0.consume();
			// La tenemos que volver a poner en 'arg0' porque si no seguirá
			// trabajando con
			// el valor inicial
			arg0.setKeyChar(caracter);

			// Si es algo distinto de una letra o del caracter de borrado, lo
			// ignoramos
			if (((caracter < 'A') || (caracter > 'Z'))
					&& (caracter != KeyEvent.VK_BACK_SPACE)) {
				arg0.consume();
			}

			// if (arg0.getSource() == tfEmpresa)
			// JOptionPane.showMessageDialog(null, "Empresa");
			// Si pulsamos enter o
			if (caracter == KeyEvent.VK_TAB || caracter == KeyEvent.VK_ENTER)
				compruebaEmpresa();
		}

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			tfEmpresa.selectAll();
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			compruebaEmpresa();
		}

	}

	private void ponIvaSegunFecha() {
		// Sirve para cambiar el IVA de los DatosComunes en función de la
		// fecha que metemos en los Datos de Inicio
		// Se utiliza para reimprimir facturas/tickets/albaranes con
		// un IVA 'antiguo'.

		int fecha = Cadena.cadenaAfecha(tfFecha.getText());

		// Antes del primer cambio
		if (fecha < 20100701) {
			DatosComunes.porIva[0] = 7.0;
			DatosComunes.porIva[1] = 16.0;
			DatosComunes.porIva[2] = 4.0;
			DatosComunes.porIva[3] = 4.0;

			DatosComunes.porRequ[0] = 1.0;
			DatosComunes.porRequ[1] = 4.0;
			DatosComunes.porRequ[2] = 0.5;
			DatosComunes.porRequ[3] = 0.0;
		}

		// Segundo Cambio - Zapatero

		if (fecha >= 20100701 && fecha < 20120901) {
			DatosComunes.porIva[0] = 8.0;
			DatosComunes.porIva[1] = 18.0;
			DatosComunes.porIva[2] = 4.0;
			DatosComunes.porIva[3] = 4.0;

			DatosComunes.porRequ[0] = 1.0;
			DatosComunes.porRequ[1] = 4.0;
			DatosComunes.porRequ[2] = 0.5;
			DatosComunes.porRequ[3] = 0.0;
		}

		// Tercer Cambio - Rajoy

		/*
		 * if (fecha >= 20120901){ DatosComunes.porIva[0] = 10.0;
		 * DatosComunes.porIva[1] = 21.0; DatosComunes.porIva[2] = 4.0;
		 * DatosComunes.porIva[3] = 4.0;
		 * 
		 * DatosComunes.porRequ[0] = 1.4; DatosComunes.porRequ[1] = 5.2;
		 * DatosComunes.porRequ[2] = 0.5; DatosComunes.porRequ[3] = 0.0; }
		 */
	}

}
