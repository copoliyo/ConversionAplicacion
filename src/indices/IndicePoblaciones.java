package indices;

import general.DatosComunes;
import general.MysqlConnect;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.ResultSet;

import tablas.CpPais;
import tablas.CpPoblacion;
import tablas.Cuenta;
import util.Apariencia;
import util.BaseDatos;

public class IndicePoblaciones {
	JDialog pantalla;
	JTable jtCuentas;
	
	DefaultTableCellRenderer tcr;
	
	boolean inactivos = false;
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	int pais = 0;
	// Poblacion que devolveremos
	String poblacion  = "";
	// Consulta SQL
	String strSql = "";

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Para que no podamos editar los datos de la tabla
			return false;
		}
	};
	
	public IndicePoblaciones(int pais){
		this.pais = pais;
		
		m = MysqlConnect.getDbCon();

		pantalla = new JDialog();
		pantalla.setModal(true);
		pantalla.setSize(400, 500);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);				
		
		// La tabla solo contendra dos columnas
		modeloTabla.addColumn("Población");
		
		final JTable jtCuentas = new JTable(modeloTabla);

		jtCuentas.setFont(Apariencia.cambiaFuente());
				
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtCuentas.getColumn("Población").setPreferredWidth(250);


		// Hacemos que la columna numero 2 se alinee a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.LEFT);
		jtCuentas.getColumnModel().getColumn(0).setCellRenderer(tcr);
		// Listener para que al pulsar ESCAPE se salga del programa
		jtCuentas.addKeyListener(new EscapeListener());		
		jtCuentas.setPreferredScrollableViewportSize(new Dimension(
				300, 400));

		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(jtCuentas);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		scrollPane.setBounds(10, 10, 360, 430);
		// Necesitamos saber si hacemos doble click en una fila
		jtCuentas.addMouseListener(new TablaListener());

		pantalla.add(scrollPane);				
		
		limpiarTabla();
		rellenarTabla();
		
		pantalla.setVisible(true);
		
	}
	
	public void limpiarTabla(){
		// Vaciamos la tabla
		int a = modeloTabla.getRowCount() - 1;

		for (int i = a; i >= 0; i--)
			modeloTabla.removeRow(i);
	}
	
	public void rellenarTabla(){
		int numeroDeFilas = 0;
		
		Object fila[] = {""};
		
		if (m.conn == null)
			getPoblacion();
		
		strSql = "";
		
		strSql = "SELECT * FROM CPPOBL WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND CPPOBL_PAIS = " + pais + " ORDER BY CPPOBL_POBLACION";				
		
		numeroDeFilas = BaseDatos.countRows(strSql);
		if(numeroDeFilas > 0){
			try {
				limpiarTabla();
				CpPoblacion poblacion = new CpPoblacion(); 
				rs = (ResultSet) m.query(strSql);
				
				// Recorremos el recodSet para ir rellenando la tabla de marcas
				while (rs.next() == true) {
					poblacion.read(rs);

					fila[0] = poblacion.getPoblacion();
					
					modeloTabla.addRow(fila);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class TablaListener implements ActionListener, MouseListener{

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
				poblacion =  (String) target.getValueAt(row, 0);
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
	}

	class EscapeListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			int id = arg0.getKeyCode();
			
	        if (id == KeyEvent.VK_ESCAPE)
	        	getPoblacion();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	public String getPoblacion(){
		try {			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pantalla.dispose();
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poblacion;
	}
	
	public void muestra(){
		pantalla.setVisible(true);
	}
}
