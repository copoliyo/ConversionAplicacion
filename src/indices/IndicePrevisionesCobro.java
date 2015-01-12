package indices;

import general.DatosComunes;
import general.MysqlConnect;

import indices.IndicePrevisionesCobro.Columna;
import indices.IndicePrevisionesCobro.FechaListener;
import indices.IndicePrevisionesCobro.TableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import tablas.ConceptoMovContable;
import tablas.Cuenta;
import tablas.EfectoCobrar;
import tablas.FacturaRecibida;
import tablas.MovimientoContable;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.EscapeDialog;
import util.JTextFieldFecha;
import util.JTextFieldNumeroFijo;

public class IndicePrevisionesCobro {
	public enum Columna {
		FECHA_VENCIMIENTO(0), EFECTO(1), IMPORTE(2), FECHA_FACTURA(3), FACTURA(4), ACUMULADO(5), CUENTA(6); 

		private int value;

		private Columna(int value) {
			this.value = value;
		}
	}
	
	
	JDialog pantalla;
	JTable jtPrevisionesCobro;
	JScrollPane spPrevisionesCobro;
	JLabel lFechaVencimiento, lCuentaContable, lDescripcionCuentaContable, lSaldo;
	JTextFieldFecha jtfeFecha;
	DefaultTableCellRenderer tcr;
	TableCellRenderer tcr2;
	boolean consultaTotal = false;
	double dSaldo = 0.0;
        
        private static EfectoCobrar efectoCobrar = new EfectoCobrar();
	
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
	
	public IndicePrevisionesCobro(){
		this.strCuenta = "";
		m = MysqlConnect.getDbCon();
		consultaTotal = true;
		
		creaGui();
	}
	
	public IndicePrevisionesCobro(String strCuenta){
		this.strCuenta = strCuenta.trim();
		m = MysqlConnect.getDbCon();
		consultaTotal = false;
		
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Efectos a Cobrar");
		pantalla.setModal(true);
		pantalla.setSize(900, 600); 
		pantalla.setDefaultCloseOperation(0);
		pantalla.setLayout(null);
		
		// La tabla contendra estas columnas
		modeloTabla.addColumn("Vencimiento");
		modeloTabla.addColumn("Efecto");
		modeloTabla.addColumn("Importe");
		modeloTabla.addColumn("Fecha Factura");
		modeloTabla.addColumn("Factura");
		modeloTabla.addColumn("Acumulado");
		modeloTabla.addColumn("Cuenta");

		jtPrevisionesCobro = new JTable(modeloTabla);
		jtPrevisionesCobro.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
		
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtPrevisionesCobro.getColumn("Vencimiento").setMaxWidth(80);
		jtPrevisionesCobro.getColumn("Efecto").setMaxWidth(60);
		jtPrevisionesCobro.getColumn("Importe").setMaxWidth(120);
		jtPrevisionesCobro.getColumn("Fecha Factura").setMaxWidth(80);
		jtPrevisionesCobro.getColumn("Factura").setMaxWidth(80);
		jtPrevisionesCobro.getColumn("Acumulado").setMaxWidth(120);
		jtPrevisionesCobro.getColumn("Cuenta").setMaxWidth(320);
		jtPrevisionesCobro.setRowHeight(20);
		
		// Hacemos que las columnas se alineen a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		jtPrevisionesCobro.getColumn("Vencimiento").setCellRenderer(tcr);
		jtPrevisionesCobro.getColumn("Efecto").setCellRenderer(tcr);
		jtPrevisionesCobro.getColumn("Fecha Factura").setCellRenderer(tcr);
		jtPrevisionesCobro.getColumn("Factura").setCellRenderer(tcr);
				
		// Hacemos que la comluna del saldo se alinee a la derecha y
		// que salga en rojo si es negativa.
		tcr2 = new TableCellRenderer();
		jtPrevisionesCobro.getColumn("Importe").setCellRenderer(tcr2);
		jtPrevisionesCobro.getColumn("Acumulado").setCellRenderer(tcr2);
		
		
		// Creamos un JscrollPane y le agregamos la JTable
		spPrevisionesCobro = new JScrollPane(jtPrevisionesCobro);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		spPrevisionesCobro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		spPrevisionesCobro.setBounds(10, 80, 870, 480);	
		spPrevisionesCobro.setFont(Apariencia.cambiaFuente());
		pantalla.add(spPrevisionesCobro);			
					
		lFechaVencimiento = new JLabel("Vencimiento");
		lFechaVencimiento.setBounds(10, 15, 100, 20);
		lFechaVencimiento.setFont(Apariencia.cambiaFuente());
		pantalla.add(lFechaVencimiento);
		
		jtfeFecha = new JTextFieldFecha();
		jtfeFecha.setBounds(110, 15, 90, 25);
		jtfeFecha.setText("0");
		jtfeFecha.setFont(Apariencia.cambiaFuente());
		jtfeFecha.setTextoAyuda("Si 0 vemos todas las previsiones.<BR>Si ponemos fecha, vemos SOLO desde esa fecha!!!", 4);
		jtfeFecha.addActionListener(new FechaListener());
		pantalla.add(jtfeFecha);
		
		lCuentaContable = new JLabel();
		lCuentaContable.setBounds(300, 15, 100, 20);
		lCuentaContable.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCuentaContable);
		
		lDescripcionCuentaContable = new JLabel();
		lDescripcionCuentaContable.setBounds(300, 45, 280, 20);
		lDescripcionCuentaContable.setFont(Apariencia.cambiaFuente());
		pantalla.add(lDescripcionCuentaContable);
		
		lSaldo = new JLabel();
		lSaldo.setBounds(680, 45, 100, 20);
		lSaldo.setFont(Apariencia.cambiaFuente());
		pantalla.add(lSaldo);
		
		lCuentaContable.setText(strCuenta);
		
		// Obetenemos los valores de la cuenta que llama a esta consulta:
		// - Descripción de la cuenta
		// - Saldo de la cuenta
		
		String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' " +
				              "AND CONTAB_CENTRO = " + DatosComunes.centroCont +  " " +
				              "AND CONTAB_CUENTA = '" + strCuenta + "'";
		Cuenta cuenta = new Cuenta();
		cuenta.read(strSqlCuenta);
		if(cuenta.getTitulo().length() < 1){
			if(consultaTotal)
				lDescripcionCuentaContable.setText("");
			else
				lDescripcionCuentaContable.setText("Inexistente!!!");
			lSaldo.setText("");
		}else{		
			lDescripcionCuentaContable.setText(cuenta.getTitulo());
			dSaldo = cuenta.getSaldo();
			lSaldo.setText(Cadena.formatoConComaDecimal(dSaldo));
			if(dSaldo < 0.0)
				lSaldo.setForeground(Color.RED);
			else
				lSaldo.setForeground(Color.BLACK);			
		}
			
		cargaPrevisionesCobro();
		
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
	
	private void borrarTabla(){
		// Vaciamos la tabla
		//int a = modeloTabla.getRowCount() - 1;

		//for (int i = a; i >= 0; i--)
		//	modeloTabla.removeRow(i);
		modeloTabla.setRowCount(0);
	}
	
	private void cargaPrevisionesCobro(){
		
		// Primero cargamos TODAS las previsiones de Cobro.
		// La fecha sólo nos sirve para poner el foco en la primera
		// a partir de la fecha de vencimiento		
		String strDesdeFecha = String.valueOf(Cadena.cadenaAfecha(jtfeFecha.getText()));
		int fechaFiltro = 0;
		
		if(strDesdeFecha.length() > 0)
			fechaFiltro = Integer.valueOf(strDesdeFecha);

		Object fila[] = {"", "", "", "", "", "", ""};	
		
		// Necesitamos saber las que no tienen fecha de Cobro, esto es, que están pendientes
		// OJO a la clausula ORDER!!!!!A
		String strSql = "SELECT * FROM EFECOB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND ";
                
                /* El indice del programa original pasa del CENTRO????
                if(DatosComunes.centroCont != 0)
                    strSql = strSql + " EFECOB_CENTRO = " + DatosComunes.centroCont + " AND ";
                else
                    strSql = strSql + " AND ";
                */                         
                    
		if(strCuenta.length() > 0)
			strSql += " EFECOB_CUENTA = '" + strCuenta + "' AND ";
		
			strSql += " EFECOB_FECHA_COBRO = 0 " +
	        "ORDER BY EFECOB_VENCIM, EFECOB_EFECTO, EFECOB_CUENTA, EFECOB_FECHA_COBRO ASC";
	   
	    Cuenta cuenta = new Cuenta();
								
		// Utilizados para leer sólo una vez y ver si hay que mostrarlos o
		// si son 0, mejor no sacar nada.
		double dAcumulado = 0.0, importe = 0.0;
		int fechaVencimiento = 0, efecto = 0, fechaFactura = 0, factura = 0;
		String cuentaEfectoCobrar = "";
		String tituloCuentaEfectoCobrar = "";
		String strFechaVencimiento = "";
		
		
		m = MysqlConnect.getDbCon();
		
		int numeroRegistros = BaseDatos.countRows(strSql);
		int numeroFilas = 0;
		if(numeroRegistros != 0){
			try {					
				EfectoCobrar efectoCobrar = new EfectoCobrar();				
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					efectoCobrar.read(rs);																
					
					
					strFechaVencimiento = Cadena.fechaAcadena(efectoCobrar.getVencimiento());
					fechaVencimiento = Cadena.cadenaAfecha(strFechaVencimiento);
					efecto = efectoCobrar.getEfecto();
					importe = efectoCobrar.getImporte();
					fechaFactura = efectoCobrar.getFechaFactura();
					factura = efectoCobrar.getFactura();
					cuentaEfectoCobrar = efectoCobrar.getCuenta();					
					
					// Obtenemos el título de la cuenta
					 String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' " +
			            "AND CONTAB_CENTRO = " + DatosComunes.centroCont +  " " +
			            "AND CONTAB_CUENTA = '" + cuentaEfectoCobrar + "'";
					cuenta.read(strSqlCuenta);
					
					tituloCuentaEfectoCobrar = cuenta.getTitulo();					
					
					fila[Columna.FECHA_VENCIMIENTO.value] = strFechaVencimiento;
					if(efecto == 0)
						fila[Columna.EFECTO.value] = "";
					else
						fila[Columna.EFECTO.value] = efecto;
				
					if(importe == 0.0)
						fila[Columna.IMPORTE.value] = "";
					else
						fila[Columna.IMPORTE.value] = Cadena.formatoConComaDecimal(importe);
					
					if(fechaFactura == 0)
						fila[Columna.FECHA_FACTURA.value] = "";
					else
						fila[Columna.FECHA_FACTURA.value] = Cadena.fechaAcadena(fechaFactura);
					
					if(factura == 0)
						fila[Columna.FACTURA.value] = "";
					else
						fila[Columna.FACTURA.value] = factura;
					
					dAcumulado += efectoCobrar.getImporte();
					if(dAcumulado != 0.0)
						fila[Columna.ACUMULADO.value] = Cadena.formatoConComaDecimal(dAcumulado);
					else
						fila[Columna.ACUMULADO.value] = "";
									
					if(tituloCuentaEfectoCobrar.length() == 0)
						fila[Columna.CUENTA.value] = "";
					else{
						String situacion = "";
						switch(efectoCobrar.getSituacion()){
						case 0 : situacion = "Rec ";
									break;
						case 2 : situacion = "lib ";
									break;
						case 3 : situacion = "Rem ";
									break;
						case 4 : situacion = "Acp ";
									break;
						case 5 : situacion = "Cob ";
									break;
						default : situacion = "    ";
								break;
						}
						fila[Columna.CUENTA.value] = situacion + tituloCuentaEfectoCobrar;
					}
					
					if(fechaFiltro == 0){
						modeloTabla.addRow(fila);
						numeroFilas++;
					}else{
						if(fechaVencimiento >= fechaFiltro){
							modeloTabla.addRow(fila);
							numeroFilas++;
						}
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de DebeHaber<BR>Consulta Acumulados Contables");				
			}		

			if(fechaFiltro != 0)
				numeroFilas = 0;
			else
				numeroFilas--;
			// Vamos al final de la trabla
			jtPrevisionesCobro.clearSelection();
			jtPrevisionesCobro.setRowSelectionInterval(numeroFilas, numeroFilas);
			Rectangle rect = jtPrevisionesCobro.getCellRect(numeroFilas, 0, true);
			jtPrevisionesCobro.scrollRectToVisible(rect);
			jtPrevisionesCobro.clearSelection();
			jtPrevisionesCobro.setRowSelectionInterval(numeroFilas, numeroFilas);
			DefaultTableModel modelo = (DefaultTableModel)jtPrevisionesCobro.getModel();
			modelo.fireTableDataChanged();
			
			if(dSaldo != dAcumulado)
				Apariencia.mensajeInformativo(5, "No cuadra saldo con previsiones de Cobro." +
						"<BR>Previsiones Cobro: " + 
						Cadena.formatoConComaDecimal(dAcumulado) +
						"<BR>Saldo: " + 
						Cadena.formatoConComaDecimal(dSaldo) + 						 
						"<BR>Diferencia: " + 
						Cadena.formatoConComaDecimal(dAcumulado - dSaldo));	
		}
		
	}
	
	class FechaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			borrarTabla();
			cargaPrevisionesCobro();
		}
		
	}
        
        public EfectoCobrar getEfectoCobrar(){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pantalla.dispose();
		return efectoCobrar;
	}
        
        
        public void muestra(){
		pantalla.setVisible(true);
	}
}