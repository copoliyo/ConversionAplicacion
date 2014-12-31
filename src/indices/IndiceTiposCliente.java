package indices;

import general.DatosComunes;
import general.MysqlConnect;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import tablas.TipoCliente;
import tablas.TipoCliente;
import util.Apariencia;
import util.BaseDatos;
import util.EscapeDialog;

import com.mysql.jdbc.ResultSet;

public class IndiceTiposCliente {
	EscapeDialog pantalla;
	JTable jtTiposCliente;
	
	DefaultTableCellRenderer tcr;
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	// Número de tipoCliente que devolveremos
	int tipoCliente  = 0;
	// Consulta SQL
	String strSql = "";

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Para que no podamos editar los datos de la tabla
			return false;
		}
	};
	
	public IndiceTiposCliente(){
		m = MysqlConnect.getDbCon();

		pantalla = new EscapeDialog();
		pantalla.setModal(true);
		pantalla.setSize(400, 300);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);				
		
		// La tabla solo contendra dos columnas
		modeloTabla.addColumn("Código");
		modeloTabla.addColumn("Tipo");
		
		final JTable jtTiposCliente = new JTable(modeloTabla);

		jtTiposCliente.setFont(Apariencia.cambiaFuente());
				
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtTiposCliente.getColumn("Código").setPreferredWidth(20);
		jtTiposCliente.getColumn("Tipo").setPreferredWidth(220);	

		// Hacemos que la columna numero 2 se alinee a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		jtTiposCliente.getColumnModel().getColumn(0).setCellRenderer(tcr);	
		jtTiposCliente.setPreferredScrollableViewportSize(new Dimension(
				300, 200));

		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(jtTiposCliente);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		scrollPane.setBounds(10, 10, 360, 230);
		// Necesitamos saber si hacemos doble click en una fila
		jtTiposCliente.addMouseListener(new TablaListener());

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
		
		Object fila[] = {"", ""};
		
		if (m.conn == null)
			getTipoCliente();
		
		strSql = "";
		
		strSql = "SELECT * FROM TIPCLI WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' ";		
		
		numeroDeFilas = BaseDatos.countRows(strSql);
		if(numeroDeFilas > 0){
			try {
				limpiarTabla();
				TipoCliente tipoCliente = new TipoCliente(); 
				rs = (ResultSet) m.query(strSql);
				
				// Recorremos el recodSet para ir rellenando la tabla de marcas
				while (rs.next() == true) {
					tipoCliente.read(rs);

					fila[0] = tipoCliente.getTipo();
					fila[1] = tipoCliente.getDescripcion();
					
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
				tipoCliente =  (Integer) target.getValueAt(row, 0);
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
	
	public int getTipoCliente(){
		try {			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pantalla.dispose();
		return tipoCliente;
	}
	
	public void muestra(){
		pantalla.setVisible(true);
	}
}
