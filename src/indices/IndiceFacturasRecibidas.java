package indices;

import general.DatosComunes;
import general.MysqlConnect;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import tablas.FacturaRecibida;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.EscapeDialog;



public class IndiceFacturasRecibidas {
	public enum Columna {
		FECHA(0), ORDEN(1), FACTURA(2), IMPORTE(3), IVA(4), TOTAL(5), FECHA_CONTABLE(6), ASIENTO_CONTABLE(7); 

		private int value;

		private Columna(int value) {
			this.value = value;
		}
	}
	
	JDialog pantalla;
	JTable jtFacturasRecibidas;
	JScrollPane spFacturasRecibidas;
	DefaultTableCellRenderer tcr;
	TableCellRenderer tcr2;
	
	ResultSet rs = null;
	MysqlConnect m = null;
	
	// Consulta SQL
	String strSql = "";
	String strCuenta = "";

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Para que no podamos editar los datos de la tabla
			return false;
		}
	};
	
	public IndiceFacturasRecibidas(String strCuenta){
		this.strCuenta = strCuenta.trim();
		m = MysqlConnect.getDbCon();
		
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Facturas Recibidas");
		pantalla.setModal(true);
		pantalla.setSize(800, 600); 
		pantalla.setDefaultCloseOperation(0);
		pantalla.setLayout(null);
		
		// La tabla contendra estas columnas
		modeloTabla.addColumn("Fecha");
		modeloTabla.addColumn("Orden");
		modeloTabla.addColumn("Factura");
		modeloTabla.addColumn("Importe");
		modeloTabla.addColumn("IVA");
		modeloTabla.addColumn("Total");
		modeloTabla.addColumn("Fecha Contable");
		modeloTabla.addColumn("Asiento");

		jtFacturasRecibidas = new JTable(modeloTabla);
		jtFacturasRecibidas.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
		
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtFacturasRecibidas.getColumn("Fecha").setMaxWidth(80);
		jtFacturasRecibidas.getColumn("Orden").setMaxWidth(60);
		jtFacturasRecibidas.getColumn("Factura").setMaxWidth(100);
		jtFacturasRecibidas.getColumn("Importe").setMaxWidth(120);
		jtFacturasRecibidas.getColumn("IVA").setMaxWidth(120);
		jtFacturasRecibidas.getColumn("Total").setMaxWidth(120);
		jtFacturasRecibidas.getColumn("Fecha Contable").setMaxWidth(80);
		jtFacturasRecibidas.getColumn("Asiento").setMaxWidth(80);
		jtFacturasRecibidas.setRowHeight(20);
		
		
		// Hacemos que las columnas se alineen a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		jtFacturasRecibidas.getColumn("Fecha").setCellRenderer(tcr);
		jtFacturasRecibidas.getColumn("Orden").setCellRenderer(tcr);
		jtFacturasRecibidas.getColumn("Factura").setCellRenderer(tcr);
		jtFacturasRecibidas.getColumn("Fecha Contable").setCellRenderer(tcr);
		jtFacturasRecibidas.getColumn("Asiento").setCellRenderer(tcr);
		
		// Hacemos que la comluna del saldo se alinee a la derecha y
		// que salga en rojo si es negativa.
		tcr2 = new TableCellRenderer();
		jtFacturasRecibidas.getColumn("Importe").setCellRenderer(tcr2);
		jtFacturasRecibidas.getColumn("IVA").setCellRenderer(tcr2);
		jtFacturasRecibidas.getColumn("Total").setCellRenderer(tcr2);
		
		// Creamos un JscrollPane y le agregamos la JTable
		spFacturasRecibidas = new JScrollPane(jtFacturasRecibidas);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		spFacturasRecibidas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		spFacturasRecibidas.setBounds(10, 10, 770, 560);	
		spFacturasRecibidas.setFont(Apariencia.cambiaFuente());
		pantalla.add(spFacturasRecibidas);			
					
		
		cargaFacturasRecibidas();
		
		pantalla.setVisible(true);
	}	
	
	class TableCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int col) {

			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, col);
			this.setHorizontalAlignment(SwingConstants.RIGHT);
			Object valueAt = table.getModel().getValueAt(row, col);
			String s = "";		      

			if (valueAt != null) {
				s = valueAt.toString();
			}

			if (s.contains("-")) {
				c.setForeground(Color.RED);
				//c.setBackground(Color.gray);
			} else {
				c.setForeground(Color.black);
				//c.setBackground(Color.WHITE);
			}

			return c;
		}
	}
	
	public void cargaFacturasRecibidas(){
		borrarTabla();
						
		Object fila[] = {"", "", "", "", "", "", "", ""};	
		
		String strSql = "SELECT * FROM FACREC WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
				        " FACREC_CENTRO = " + DatosComunes.centroCont + " AND " +
				        " FACREC_PROVACRE = '" + strCuenta + "' ORDER BY FACREC_FECHA ASC";				
		String claveFechaAsientoApunte = "";
		double baseTotal = 0.0;
		
		m = MysqlConnect.getDbCon();
		
		int numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){
			String strFechaAsiento = "", strAsiento = "";
			try {							
				FacturaRecibida factura = new FacturaRecibida();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					factura.read(rs);
					
					claveFechaAsientoApunte = String.valueOf(factura.getFechaAsientoApunte());															
					
					strFechaAsiento = claveFechaAsientoApunte.substring(0, 8);
					strAsiento = claveFechaAsientoApunte.substring(8, 13);
					
					fila[Columna.FECHA.value] = Cadena.fechaAcadena(Integer.valueOf(factura.getFecha()));
					fila[Columna.ORDEN.value] = factura.getOrden();
					fila[Columna.FACTURA.value] = factura.getFactura();
					// Tenemos que sumar todas las bases
					baseTotal = 0.0;
					for(int i = 0; i < 4; i++)
						baseTotal += factura.getBase(i);
					
					fila[Columna.IMPORTE.value] = Cadena.formatoConComaDecimal(baseTotal);
					fila[Columna.IVA.value] = Cadena.formatoConComaDecimal(factura.getIva());
					fila[Columna.TOTAL.value] = Cadena.formatoConComaDecimal(factura.getTotal());					
					
					fila[Columna.FECHA_CONTABLE.value] = Cadena.fechaAcadena(Integer.valueOf(strFechaAsiento));
					fila[Columna.ASIENTO_CONTABLE.value] = Integer.valueOf(strAsiento);					
					
					modeloTabla.addRow(fila);
					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de FacturaRecibida<BR>Consulta Acumulados Contables");				
			}

			// Vamos al final de la tabla
			int numeroDeFilas = modeloTabla.getRowCount() - 1;
			jtFacturasRecibidas.clearSelection();
			jtFacturasRecibidas.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
			Rectangle rect = jtFacturasRecibidas.getCellRect(numeroDeFilas, 0, true);
			jtFacturasRecibidas.scrollRectToVisible(rect);
			jtFacturasRecibidas.clearSelection();
			jtFacturasRecibidas.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
			DefaultTableModel modelo = (DefaultTableModel)jtFacturasRecibidas.getModel();
			   modelo.fireTableDataChanged();
		}
	}
	
	private void borrarTabla(){
		// Vaciamos la tabla
		//int a = modeloTabla.getRowCount() - 1;

		//for (int i = a; i >= 0; i--)
		//	modeloTabla.removeRow(i);
		modeloTabla.setRowCount(0);
	}
}
