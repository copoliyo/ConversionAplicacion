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

import tablas.FacturaEmitida;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.EscapeDialog;

public class IndiceFacturasEmitidas {
	public enum Columna {
		FECHA(0), FACTURA(1), IMPORTE(2), IVA(3), RECARGO_EQUIVALENCIA(4), TOTAL(5), FECHA_CONTABLE(6), ASIENTO_CONTABLE(7); 

		private int value;

		private Columna(int value) {
			this.value = value;
		}
	}
	
	JDialog pantalla;
	JTable jtFacturasEmitidas;
	JScrollPane spFacturasEmitidas;
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
	
	public IndiceFacturasEmitidas(String strCuenta){
		this.strCuenta = strCuenta.trim();
		m = MysqlConnect.getDbCon();
		
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Facturas Emitidas");
		pantalla.setModal(true);
		pantalla.setSize(800, 600); 
		pantalla.setDefaultCloseOperation(0);
		pantalla.setLayout(null);
		
		// La tabla contendra estas columnas
		modeloTabla.addColumn("Fecha");
		modeloTabla.addColumn("Factura");
		modeloTabla.addColumn("Importe");
		modeloTabla.addColumn("IVA");
		modeloTabla.addColumn("Rec.Equiv.");
		modeloTabla.addColumn("Total");
		modeloTabla.addColumn("Fecha Contable");
		modeloTabla.addColumn("Asiento");

		jtFacturasEmitidas = new JTable(modeloTabla);
		jtFacturasEmitidas.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
		
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtFacturasEmitidas.getColumn("Fecha").setMaxWidth(80);
		jtFacturasEmitidas.getColumn("Factura").setMaxWidth(60);
		jtFacturasEmitidas.getColumn("Importe").setMaxWidth(100);
		jtFacturasEmitidas.getColumn("IVA").setMaxWidth(120);
		jtFacturasEmitidas.getColumn("Rec.Equiv.").setMaxWidth(120);
		jtFacturasEmitidas.getColumn("Total").setMaxWidth(120);
		jtFacturasEmitidas.getColumn("Fecha Contable").setMaxWidth(80);
		jtFacturasEmitidas.getColumn("Asiento").setMaxWidth(80);
		jtFacturasEmitidas.setRowHeight(20);
		
		
		// Hacemos que las columnas se alineen a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		jtFacturasEmitidas.getColumn("Fecha").setCellRenderer(tcr);
		jtFacturasEmitidas.getColumn("Factura").setCellRenderer(tcr);
		jtFacturasEmitidas.getColumn("Fecha Contable").setCellRenderer(tcr);
		jtFacturasEmitidas.getColumn("Asiento").setCellRenderer(tcr);
		
		// Hacemos que la comluna del saldo se alinee a la derecha y
		// que salga en rojo si es negativa.
		tcr2 = new TableCellRenderer();
		jtFacturasEmitidas.getColumn("Importe").setCellRenderer(tcr2);
		jtFacturasEmitidas.getColumn("IVA").setCellRenderer(tcr2);
		jtFacturasEmitidas.getColumn("Rec.Equiv.").setCellRenderer(tcr2);
		jtFacturasEmitidas.getColumn("Total").setCellRenderer(tcr2);
		
		// Creamos un JscrollPane y le agregamos la JTable
		spFacturasEmitidas = new JScrollPane(jtFacturasEmitidas);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		spFacturasEmitidas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		spFacturasEmitidas.setBounds(10, 10, 770, 560);	
		spFacturasEmitidas.setFont(Apariencia.cambiaFuente());
		pantalla.add(spFacturasEmitidas);			
					
		
		cargaFacturasEmitidas();
		
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
	
	public void cargaFacturasEmitidas(){
		borrarTabla();
						
		Object fila[] = {"", "", "", "", "", "", "", ""};	
		
		String strSql = "SELECT * FROM FACEMI WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
				        " FACEMI_CENTRO = " + DatosComunes.centroCont + " AND " +
				        " FACEMI_CLIENTE = '" + strCuenta + "' ORDER BY FACEMI_FECHA ASC";				
		String claveFechaAsientoApunte = "";
		double baseTotal = 0.0;
		
		m = MysqlConnect.getDbCon();
		
		int numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){
			String strFechaAsiento = "", strAsiento = "";
			try {							
				FacturaEmitida factura = new FacturaEmitida();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					factura.read(rs);
					
					claveFechaAsientoApunte = String.valueOf(factura.getFechaAsientoApunte());															
					
					if(claveFechaAsientoApunte.length() > 7)
						strFechaAsiento = claveFechaAsientoApunte.substring(0, 8);
					else
						strFechaAsiento = "";
					
					if(claveFechaAsientoApunte.length() > 12)
						strAsiento = claveFechaAsientoApunte.substring(8, 13);
					else
						strAsiento = "";
					
					fila[Columna.FECHA.value] = Cadena.fechaAcadena(Integer.valueOf(factura.getFecha()));
					fila[Columna.FACTURA.value] = factura.getFactura();
					// Tenemos que sumar todas las bases
					baseTotal = 0.0;
					for(int i = 0; i < 3; i++)
						baseTotal += factura.getBaseIva(i);
					fila[Columna.IMPORTE.value] = Cadena.formatoConComaDecimal(baseTotal);
					fila[Columna.IVA.value] = Cadena.formatoConComaDecimal(factura.getIva());
					
					if(factura.getRecargoEquivalencia() == 0.0)
						fila[Columna.RECARGO_EQUIVALENCIA.value] = "";
					else
						fila[Columna.RECARGO_EQUIVALENCIA.value] = Cadena.formatoConComaDecimal(factura.getRecargoEquivalencia());
					
					fila[Columna.TOTAL.value] = Cadena.formatoConComaDecimal(factura.getTotal());					
					
					fila[Columna.FECHA_CONTABLE.value] = Cadena.fechaAcadena(Integer.valueOf(strFechaAsiento));
					fila[Columna.ASIENTO_CONTABLE.value] = Integer.valueOf(strAsiento);					
					
					modeloTabla.addRow(fila);
					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de FacturaEmitida<BR>Consulta Acumulados Contables");				
			}

			// Vamos al final de la tabla
			int numeroDeFilas = modeloTabla.getRowCount() - 1;
			jtFacturasEmitidas.clearSelection();
			jtFacturasEmitidas.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
			Rectangle rect = jtFacturasEmitidas.getCellRect(numeroDeFilas, 0, true);
			jtFacturasEmitidas.scrollRectToVisible(rect);
			jtFacturasEmitidas.clearSelection();
			jtFacturasEmitidas.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
			DefaultTableModel modelo = (DefaultTableModel)jtFacturasEmitidas.getModel();
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
