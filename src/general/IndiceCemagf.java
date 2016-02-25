package general;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * Argumentos: Conexion - conexión para poder consultar la base de datos.
 * 
 * Devuelve: un String con las tres letras de la marca que hemos elegido o NULL
 *           si pulsamos ESCAPE o salimos con 'la puerta'.
 */

public class IndiceCemagf {

	static final int INDICE_CENTROS = 1;
	static final int INDICE_MARCAS = 2;
	static final int INDICE_GAMAS = 3;
	static final int INDICE_FAMILIAS = 4;

	JDialog pantalla;
	JLabel lMarca;
	JCheckBox cInactivos;
	JButton bSalir;
	JTable TablaCemagf;
	// 'buscarMarca' contendrá la cadena a buscar en la descripción de la marca
	String buscarMarca = "";
	char ch;
	int tipoIndice = INDICE_MARCAS;

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Para que no podamos editar los datos de la tabla
			return false;
		}
	};

	String marca = "";
	Connection c;

	IndiceCemagf(int tipoIndice) {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c = abrirConexion();

		if (c != null) {
			// Recogemos la conexion para poder consultar la BD
			this.tipoIndice = tipoIndice;

			if (tipoIndice > 0 && tipoIndice < 5) {
				pantalla = new JDialog();
				pantalla.setModal(true);
				pantalla.setSize(300, 600);
				pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				pantalla.setLayout(null);

				lMarca = new JLabel(buscarMarca);
				lMarca.setBounds(30, 5, 50, 20);
				pantalla.add(lMarca);

				cInactivos = new JCheckBox("Inactivos");
				cInactivos.setBounds(200, 5, 100, 20);
				cInactivos.addActionListener(new CheckListener());

				pantalla.add(cInactivos);

				// Ponemos el botón de salir
				bSalir = new JButton("Salir");
				bSalir.setBounds(100, 540, 100, 20);
				bSalir.addActionListener(new SalirListener());

				pantalla.add(bSalir);

				// Ponemos la tabla son su modelo
				// Se crea la Tabla a partir de un modelo de tabla que
				// definimos nosotros, luego le añadiremos datos.

				DefaultTableCellRenderer modeloDerecha = new DefaultTableCellRenderer();
				modeloDerecha.setHorizontalAlignment(SwingConstants.RIGHT);

				// La tabla solo contendra dos columnas
				modeloTabla.addColumn("Código");
				modeloTabla.addColumn("Descripción");

				JTable TablaCemagf = new JTable(modeloTabla);

				TableColumn columna = new TableColumn();
				// Establecemos el ancho
				columna = TablaCemagf.getColumn("Código");
				columna.setPreferredWidth(60);
				// Establecemos el ancho
				columna = TablaCemagf.getColumn("Descripción");
				columna.setPreferredWidth(250);

				TablaCemagf.setPreferredScrollableViewportSize(new Dimension(
						200, 400));

				// Creamos un JscrollPane y le agregamos la JTable
				JScrollPane scrollPane = new JScrollPane(TablaCemagf);
				// Agregamos el JScrollPane al contenedor
				scrollPane.setBounds(10, 30, 270, 500);
				// Necesitamos saber si hacemos doble click en una fila
				TablaCemagf.addMouseListener(new TablaListener());
				// Añadimos el Listener para poder escribir la cadena a buscar
				// en la descripcion
				TablaCemagf.addKeyListener(new TablaListener());

				pantalla.add(scrollPane);
				limpiarTablaCemagf();
				rellenarTablaCemagf(cInactivos.isSelected());
				pantalla.setVisible(true);
			} else {
				marca = null;
			}
		} else {
			marca = null;
		}
	}

	private Connection abrirConexion() {
		// Abrimos la conexión a la base de datos para hacer la consulta
		Connection conexion = null;

		AbrirBD bd = new AbrirBD();
		try {
			conexion = bd.abreBaseDeDatos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println("Error al abrir la base de datos.");
			JOptionPane.showMessageDialog(null,
					"IndiceCemagf: Error al abrir la base de datos.");
			conexion = null;
			e.printStackTrace();
		}

		return conexion;
	}

	public void limpiarTablaCemagf() {
		// Vaciamos la tabla
		int a = modeloTabla.getRowCount() - 1;

		for (int i = a; i >= 0; i--)
			modeloTabla.removeRow(i);
	}

	public void rellenarTablaCemagf(boolean inactivos) {
		ResultSet rs = null;
		Statement s = null;
		String strSQL = "";
		Object fila[] = { "", "" };

		try {
			// Creamos el 'Statement'
			s = c.createStatement();
		} catch (SQLException err) {
			err.printStackTrace();
		}

		try {

			switch (tipoIndice) {
			case INDICE_CENTROS:
				strSQL = "SELECT CEMAGF_CENTRO, " + "CEMAGF_DESCRIP, "
						+ "CEMAGF_ACTIVO FROM CEMAGF "
						+ "WHERE EMPRESA = 'AL' AND "
						+ "CEMAGF_MARCA = '' AND " + "CEMAGF_GAMA = 0 AND "
						+ "CEMAGF_FAMILIA = 0";
				if (inactivos == false)
					strSQL += " AND CEMAGF_ACTIVO = 1";

				System.out.println("BBuscarMarca:" + buscarMarca);
				if (!buscarMarca.isEmpty()) {
					strSQL += " AND CEMAGF_DESCRIP LIKE '%" + buscarMarca
							+ "%'";
				}
				strSQL += " ORDER BY CEMAGF_CENTRO";
				break;
			case INDICE_MARCAS:
				strSQL = "SELECT CEMAGF_MARCA, " + "CEMAGF_DESCRIP, "
						+ "CEMAGF_ACTIVO FROM CEMAGF "
						+ "WHERE EMPRESA = 'AL' AND " + "CEMAGF_GAMA = 0 AND "
						+ "CEMAGF_FAMILIA = 0 AND " + "CEMAGF_CENTRO = 0";
				if (inactivos == false)
					strSQL += " AND CEMAGF_ACTIVO = 1";

				System.out.println("BBuscarMarca:" + buscarMarca);
				if (!buscarMarca.isEmpty()) {
					strSQL += " AND CEMAGF_DESCRIP LIKE '%" + buscarMarca
							+ "%'";
				}
				strSQL += " ORDER BY CEMAGF_MARCA";
				break;
			case INDICE_GAMAS:
				strSQL = "SELECT CEMAGF_GAMA, " + "CEMAGF_DESCRIP, "
						+ "CEMAGF_ACTIVO FROM CEMAGF "
						+ "WHERE EMPRESA = 'AL' AND "
						+ "CEMAGF_MARCA = '' AND " + "CEMAGF_FAMILIA = 0 AND "
						+ "CEMAGF_GAMA > 0 AND " + "CEMAGF_CENTRO = 0";
				if (inactivos == false)
					strSQL += " AND CEMAGF_ACTIVO = 1";

				System.out.println("BBuscarMarca:" + buscarMarca);
				if (!buscarMarca.isEmpty()) {
					strSQL += " AND CEMAGF_DESCRIP LIKE '%" + buscarMarca
							+ "%'";
				}
				strSQL += " ORDER BY CEMAGF_GAMA";
				break;
			case INDICE_FAMILIAS:
				strSQL = "SELECT CEMAGF_GAMA, " + "CEMAGF_FAMILIA, "
						+ "CEMAGF_DESCRIP, " + "CEMAGF_ACTIVO FROM CEMAGF "
						+ "WHERE EMPRESA = 'AL' AND "
						+ "CEMAGF_MARCA = '' AND " + "CEMAGF_GAMA > 0 AND "
						+ "CEMAGF_FAMILIA > 0 AND " + "CEMAGF_CENTRO = 0";
				if (inactivos == false)
					strSQL += " AND CEMAGF_ACTIVO = 1";

				System.out.println("BBuscarMarca:" + buscarMarca);
				if (!buscarMarca.isEmpty()) {
					strSQL += " AND CEMAGF_DESCRIP LIKE '%" + buscarMarca
							+ "%'";
				}
				strSQL += " ORDER BY CEMAGF_GAMA, CEMAGF_FAMILIA";
				break;
			}

			rs = s.executeQuery(strSQL);
		} catch (SQLException err) {
			err.printStackTrace();
		}

		try {
			limpiarTablaCemagf();
			int marcaActiva = 0;
			String gama, familia;
			// Recorremos el recodSet para ir rellenando la tabla de marcas
			while (rs.next() == true) {
				try {
					String strMarca = "";
					String strDescripcion = "";

					switch (tipoIndice) {
					case INDICE_CENTROS:
						fila[0] = rs.getString("CEMAGF_CENTRO");
						break;
					case INDICE_MARCAS:
						fila[0] = rs.getString("CEMAGF_MARCA");
						break;
					case INDICE_GAMAS:
						gama = rs.getString("CEMAGF_GAMA");
						while (gama.length() < 3)
							gama = "0" + gama;
						fila[0] = gama;

						break;
					case INDICE_FAMILIAS:
						gama = rs.getString("CEMAGF_GAMA");
						familia = rs.getString("CEMAGF_FAMILIA");

						while (gama.length() < 3)
							gama = "0" + gama;
						while (familia.length() < 3)
							familia = "0" + familia;

						fila[0] = gama + "." + familia;
						break;
					}

					fila[1] = rs.getString("CEMAGF_DESCRIP");
					marcaActiva = rs.getInt("CEMAGF_ACTIVO");
					// Si la marca está inactiva le ponemos un 'n' delante de la
					// descripción
					if (marcaActiva == 0)
						fila[1] = "n" + fila[1];
					modeloTabla.addRow(fila);
				} catch (SQLException err) {
					err.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		try {
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class SalirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Si pinchamos en el botón 'Salir', no pasamos marca y 'tiramos' la
			// pantalla
			marca = null;
			pantalla.dispose();
		}
	}

	class TablaListener implements ActionListener, MouseListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pantalla.dispose();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// Si hacemos doble click fijamos el valor de la marca y salimos
			if (arg0.getClickCount() == 2) {
				JTable target = (JTable) arg0.getSource();
				int row = target.getSelectedRow();
				marca = (String) target.getValueAt(row, 0);
				pantalla.dispose();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent arg0) {

			int id = arg0.getID();
			String keyString;
			// Si pulsamos un caracter, lo añadimos a la cadena a buscar en la
			// descripción
			if (id == KeyEvent.KEY_TYPED) {
				ch = arg0.getKeyChar();
				keyString = "key character = '" + ch + "'";
				buscarMarca += ch;
				lMarca.setText(buscarMarca);
				limpiarTablaCemagf();
				rellenarTablaCemagf(cInactivos.isSelected());
			} else {
				int keyCode = arg0.getKeyCode();
				keyString = "key code = " + keyCode + " ("
						+ KeyEvent.getKeyText(keyCode) + ")";
				ch = KeyEvent.getKeyText(keyCode).charAt(0);
				// Borramos el caracter de la derecha
				if (keyCode == 8 && buscarMarca.length() > 0) {
					buscarMarca = buscarMarca.substring(0,
							buscarMarca.length() - 1);

				}

				// Añadimos caracter
				if ((keyCode >= 48 && keyCode <= 59)
						|| (keyCode >= 65 && keyCode <= 90))
					buscarMarca += ch;

				// Actualizar tabla
				lMarca.setText(buscarMarca);
				limpiarTablaCemagf();
				rellenarTablaCemagf(cInactivos.isSelected());

			}

			System.out.println("Tecla: " + keyString + " buscarMarca: "
					+ buscarMarca);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	class CheckListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// CAmbiamos entre activos e inactivos
			limpiarTablaCemagf();
			rellenarTablaCemagf(cInactivos.isSelected());
		}

	}

	public String getMarca() {
		return marca;
	}
}
