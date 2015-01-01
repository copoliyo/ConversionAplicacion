package indices;

import general.DatosComunes;
import general.MysqlConnect;

import indices.IndiceMovimientosContables.Columna;
import indices.IndicePaises.TablaListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import tablas.EfectoPagar;
import tablas.FacturaRecibida;
import tablas.MovimientoContable;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.EscapeDialog;
import util.JTextFieldFecha;
import util.JTextFieldNumeroFijo;


public class IndicePrevisionesPago {
	public enum Columna {
		FECHA_VENCIMIENTO(0), EFECTO(1), IMPORTE(2), FECHA_FACTURA(3), FACTURA(4), ACUMULADO(5), PAGARE(6), CUENTA(7); 

		private int value;

		private Columna(int value) {
			this.value = value;
		}
	}
	
	
	JDialog pantalla;
	JTable jtPrevisionesPago;
	JScrollPane spPrevisionesPago;
	JLabel lFechaVencimiento, lCuentaContable, lDescripcionCuentaContable, lSaldo;
	JTextFieldFecha jtfeFecha;
	DefaultTableCellRenderer tcr;
	TableCellRenderer tcr2;
	boolean consultaTotal = false;
	double dSaldo = 0.0;
	
	ResultSet rs = null;
	MysqlConnect m = null;
	
	private static EfectoPagar efectoPagar = new EfectoPagar();
	
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
	
	public IndicePrevisionesPago(){
		this.strCuenta = "";
		m = MysqlConnect.getDbCon();
		consultaTotal = true;
		
		creaGui();
	}
	
	public IndicePrevisionesPago(String strCuenta){
		this.strCuenta = strCuenta.trim();
		m = MysqlConnect.getDbCon();
		consultaTotal = false;
		
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Efectos a Pagar");
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
		modeloTabla.addColumn("Pagaré");
		modeloTabla.addColumn("Cuenta");

		jtPrevisionesPago = new JTable(modeloTabla);
		jtPrevisionesPago.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
		
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtPrevisionesPago.getColumn("Vencimiento").setMaxWidth(80);
		jtPrevisionesPago.getColumn("Efecto").setMaxWidth(60);
		jtPrevisionesPago.getColumn("Importe").setMaxWidth(120);
		jtPrevisionesPago.getColumn("Fecha Factura").setMaxWidth(80);
		jtPrevisionesPago.getColumn("Factura").setMaxWidth(80);
		jtPrevisionesPago.getColumn("Acumulado").setMaxWidth(120);
		jtPrevisionesPago.getColumn("Pagaré").setMaxWidth(100);
		jtPrevisionesPago.getColumn("Cuenta").setMaxWidth(220);
		jtPrevisionesPago.setRowHeight(20);
		
		// Hacemos que las columnas se alineen a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		jtPrevisionesPago.getColumn("Vencimiento").setCellRenderer(tcr);
		jtPrevisionesPago.getColumn("Efecto").setCellRenderer(tcr);
		jtPrevisionesPago.getColumn("Fecha Factura").setCellRenderer(tcr);
		jtPrevisionesPago.getColumn("Factura").setCellRenderer(tcr);
		jtPrevisionesPago.getColumn("Pagaré").setCellRenderer(tcr);
				
		// Hacemos que la comluna del saldo se alinee a la derecha y
		// que salga en rojo si es negativa.
		tcr2 = new TableCellRenderer();
		jtPrevisionesPago.getColumn("Importe").setCellRenderer(tcr2);
		jtPrevisionesPago.getColumn("Acumulado").setCellRenderer(tcr2);
		// Necesitamos saber si hacemos doble click en una fila
		jtPrevisionesPago.addMouseListener(new TablaListener());
		
		
		// Creamos un JscrollPane y le agregamos la JTable
		spPrevisionesPago = new JScrollPane(jtPrevisionesPago);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		spPrevisionesPago.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		spPrevisionesPago.setBounds(10, 80, 870, 480);	
		spPrevisionesPago.setFont(Apariencia.cambiaFuente());
		pantalla.add(spPrevisionesPago);			
					
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
			
		cargaPrevisionesPago();
		
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
				
				String fechaVencimiento = (String) target.getValueAt(row, 0);				
				efectoPagar.setVencimiento(util.Cadena.cadenaAfecha(fechaVencimiento));
				efectoPagar.setEfecto((Integer) target.getValueAt(row, 1));
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

	private void borrarTabla(){
		// Vaciamos la tabla
		//int a = modeloTabla.getRowCount() - 1;

		//for (int i = a; i >= 0; i--)
		//	modeloTabla.removeRow(i);
		modeloTabla.setRowCount(0);
	}
	
	private void cargaPrevisionesPago(){
		
		// Primero cargamos TODAS las previsiones de pago.
		// La fecha sólo nos sirve para poner el foco en la primera
		// a partir de la fecha de vencimiento		
		String strDesdeFecha = String.valueOf(Cadena.cadenaAfecha(jtfeFecha.getText()));
		int fechaFiltro = 0;
		
		if(strDesdeFecha.length() > 0)
			fechaFiltro = Integer.valueOf(strDesdeFecha);

		Object fila[] = {"", "", "", "", "", "", "", ""};	
		
		// Necesitamos saber las que no tienen fecha de Pago, esto es, que están pendientes
		// OJO a la clausula ORDER!!!!!A
		String strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
				        " EFEPAG_CENTRO = " + DatosComunes.centroCont + " AND ";
		
		if(strCuenta.length() > 0)
			strSql += " EFEPAG_CUENTA = '" + strCuenta + "' AND ";
		
		if(consultaTotal)
			strSql += " EFEPAG_FECHA_PAGO = 0 ";
		
			strSql += " ORDER BY EFEPAG_VENCIM, EFEPAG_EFECTO, EFEPAG_CUENTA, EFEPAG_FECHA_PAGO ASC";
	   
	    Cuenta cuenta = new Cuenta();
								
		// Utilizados para leer sólo una vez y ver si hay que mostrarlos o
		// si son 0, mejor no sacar nada.
		double dAcumulado = 0.0, importe = 0.0;
		int fechaVencimiento = 0, efecto = 0, fechaFactura = 0, factura = 0, pagare = 0;
		String cuentaEfectoPagar = "";
		String tituloCuentaEfectoPagar = "";
		String strFechaVencimiento = "";
		
		
		m = MysqlConnect.getDbCon();
		
		int numeroRegistros = BaseDatos.countRows(strSql);
		int numeroFilas = 0;
		if(numeroRegistros != 0){
			try {					
								
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					efectoPagar.read(rs);																
					
					
					strFechaVencimiento = Cadena.fechaAcadena(efectoPagar.getVencimiento());
					fechaVencimiento = Cadena.cadenaAfecha(strFechaVencimiento);
					efecto = efectoPagar.getEfecto();
					importe = efectoPagar.getImporte();
					fechaFactura = efectoPagar.getFechaFactura();
					factura = efectoPagar.getDocumentoOrigen();
					pagare = efectoPagar.getPagare();
					cuentaEfectoPagar = efectoPagar.getCuenta();					
					
					// Obtenemos el título de la cuenta
					 String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' " +
			            "AND CONTAB_CENTRO = " + DatosComunes.centroCont +  " " +
			            "AND CONTAB_CUENTA = '" + cuentaEfectoPagar + "'";
					cuenta.read(strSqlCuenta);
					
					tituloCuentaEfectoPagar = cuenta.getTitulo();					
					
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
										
					dAcumulado += efectoPagar.getImporte();
					
					if(dAcumulado != 0.0)
						fila[Columna.ACUMULADO.value] = Cadena.formatoConComaDecimal(dAcumulado);
					else
						fila[Columna.ACUMULADO.value] = "";
					
					if(pagare == 0)
						fila[Columna.PAGARE.value] = "";
					else
						fila[Columna.PAGARE.value] = pagare;
					
					if(tituloCuentaEfectoPagar.length() == 0)
						fila[Columna.CUENTA.value] = "";
					else
						fila[Columna.CUENTA.value] = tituloCuentaEfectoPagar;
					
					if(fechaFiltro == 0){
						modeloTabla.addRow(fila);
						numeroFilas++;
					}else{
						if(fechaVencimiento > fechaFiltro){
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
			jtPrevisionesPago.clearSelection();
			jtPrevisionesPago.setRowSelectionInterval(numeroFilas, numeroFilas);
			Rectangle rect = jtPrevisionesPago.getCellRect(numeroFilas, 0, true);
			jtPrevisionesPago.scrollRectToVisible(rect);
			jtPrevisionesPago.clearSelection();
			jtPrevisionesPago.setRowSelectionInterval(numeroFilas, numeroFilas);
			DefaultTableModel modelo = (DefaultTableModel)jtPrevisionesPago.getModel();
			modelo.fireTableDataChanged();
			
			if(dSaldo != dAcumulado && !consultaTotal)
				Apariencia.mensajeInformativo(5, "No cuadra saldo con previsiones de pago." +
						"<BR>Previsiones Pago: " + 
						Cadena.formatoConComaDecimal(dAcumulado) +
						"<BR>Saldo: " + 
						Cadena.formatoConComaDecimal(dSaldo) + 						 
						"<BR>Diferencia: " + 
						Cadena.formatoConComaDecimal(dSaldo + dAcumulado));	
		}
		
	}
	
	class FechaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			borrarTabla();
			cargaPrevisionesPago();
		}
		
	}
	class EscapeListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			int id = arg0.getKeyCode();
			
	        if (id == KeyEvent.VK_ESCAPE)
	        	getEfectoPagar();	        	       
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	public EfectoPagar getEfectoPagar(){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pantalla.dispose();
		return efectoPagar;
	}
	
	public void muestra(){
		pantalla.setVisible(true);
	}
	
}
