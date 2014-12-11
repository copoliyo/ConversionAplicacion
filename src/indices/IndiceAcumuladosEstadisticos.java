package indices;

import general.DatosComunes;
import general.MysqlConnect;


import indices.IndiceFacturasRecibidas.Columna;

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



import tablas.Cuenta;
import tablas.DebeHaber;
import tablas.EstadisticaContable;
import tablas.FacturaRecibida;
import tablas.Nota;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.EscapeDialog;
import util.Fecha;
import util.JTextFieldFecha;
import util.JTextFieldNumeroFijo;

/*
 *       *   CACEST-FICHERO
      *   00 ??????
      *   01 FICHERO CLITES
      *      Acum 1 Facturado
      *      Acum 2 Ventas
      *      Acum 3 Rappel
      *      Acum 4 Nro. Impagados
      *      Acum 5 Beneficio
      *      Acum 6 Ptas. Impagados
      *   02 FICHERO PROVAC
      *      Acum 1 Facturado
      *      Acum 2 Compras
      *      Acum 3 Rappel
      *   03 FICHERO REPRESENTANTES
      *      Acum 1 Ventas
      *      Acum 2 Comision
      *      Acum 3 Retencion IRPF
      * Viene x TACEST los Acum en Tiendas antes
      *           ahora 4  Acum 1 Valor Vta Artic. Codif.
      *           ahora 1  Acum 2 Valor Vta Totales
      *                    Acum 5 Beneficio
      * puestos nuevos cambiados
      *      Acum 4 Valor Vta Artic. Codif. en tiendas 1
      *      Acum 5 Beneficio
      *   04 FICHERO TIPOS CLIENTE
      *      Acum 1 Ventas
      *      Acum 5 Beneficio
      *      Acum 6 Presupuesto
      *   05 FICHERO ZONAS CLIENTE
      *      Acum 1 Ventas
      *      Acum 5 Beneficio
      *      Acum 6 Presupuesto
      *   06 FICHERO CUENTAS CONTABLES
      *      Acum 1 Saldo fin a�o para restar de Acum a�o
      *      Acum 6 Presupuesto
      *   07 FICHERO TRABAJADORES
      *      Acum 1 Bruto
      *      Acum 2 Retencion S.S.
      *      Acum 3 Retencion I.R.P.F.
      *   08 FICHERO CENTROS/TIPCLI
      *      Acum 1 Ventas
      *      Acum 5 Beneficio
      *   09 FICHERO ACCCEN   Bolsa Descuentos (CMPRESUP)
      *      Acum 4 Dtos MES
      *      Acum 6 Bolsa DTOS
      *      
      *    if sw-fic = 1
             display
             label "Ventas____________________"      line 1,75 col 11,00
                                                     font small-font
             label "Beneficio______________________________"
                                                     line 1,75 col 34,00
                                                     font small-font
             label "Impagados_____________________________"
                                                     line 1,75 col 69,00
                                                     font small-font
             label "Nro."    line 3,15 col 69,00 font small-font
             label "%/V"     line 3,15 col 79,00 font small-font
             label "Importe" line 3,15 col 85,00 font small-font
             label "Rappel"  line 3,15 col 93,00 font small-font.
           if sw-fic = 2
             display
             label "Compras___________________"      line 1,75 col 11,00
                                                     font small-font
             label "Rappels___________________________"
                                                     line 1,75 col 34,00
                                                     font small-font
             label "Facturado_____________________"  line 1,75 col 65,00
                                                     font small-font
             label "A�o"   line 3,15 col 75,00 font small-font
             label "%/A"   line 3,15 col 79,00 font small-font
             label "A.Ant" line 3,15 col 86,00 font small-font.
           if sw-fic = 3
             display
             label "Ventas____________________"      line 1,75 col 11,00
                                                     font small-font.
           if sw-fic = 3 AND
              NIVEL-ACCONT < 4
             display
             label "Beneficio s/artic__________________________"
                                                     line 1,75 col 34,00
                                                     font small-font
             label "Comisiones______________________"
                                                     line 1,75 col 69,00
                                                     font small-font
             label "A�o"   line 3,15 col 74,00 font small-font
             label "%/A"   line 3,15 col 79,00 font small-font
             label "A.Ant" line 3,15 col 86,00 font small-font.
           if sw-fic = 3 AND
              NIVEL-ACCONT > 3
              destroy control line 3,15 col 38,00
              destroy control line 3,15 col 42,00
              destroy control line 3,15 col 46,50
              destroy control line 3,15 col 53,00
              destroy control line 3,15 col 60,00.
           if sw-fic = 4 OR 5 OR 8
             display label "Ventas____________________"
                                                    line 1,75 col 11,00
                                                    font small-font
                     label "Beneficio________________________________"
                                                    line 1,75 col 34,00
                                                    font small-font.
           if sw-fic = 4 OR 5
             display label "Presup" line 3,15 col 72,00 font small-font
                     label "%/P"    col 80,00 font small-font.
 */



public class IndiceAcumuladosEstadisticos {
	public enum ColumnaClientes {
		MES(0),
		VENTAS_A�O(1), 
		VENTAS_PORCENTAJE_SOBRE_A�O(2), 
		VENTAS_A�O_ANTERIOR(3), 
		BENEFICIO_A�O(4), 
		BENEFICIO_PORCENTAJE_SOBRE_VENTAS(5), 
		BENEFICIO_PORCENTAJE_SOBRE_A�O(6), 
		BENEFICIO_A�O_ANTERIOR(7), 
		BENEFICIO_PORCENTAJE_SOBRE_VENTAS_A�O_ANTERIOR(8),
		IMPAGADOS_NUMERO(9), 
		IMPAGADOS_PORCENTAJE_SOBRE_VENTAS(10), 
		IMPAGADOS_IMPORTE(11), 
		IMPAGADOS_RAPPEL(12); 

		private int value;

		private ColumnaClientes(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaProveedorAcreedor {
		MES(0),
		COMPRAS_A�O(1), 
		COMPRAS_PORCENTAJE_SOBRE_A�O(2), 
		COMPRAS_A�O_ANTERIOR(3), 
		RAPPELS_A�O(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_A�O(6), 
		RAPPELS_A�O_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_A�O(9), 
		FACTURADO_PORCENTAJE_SOBRE_A�O(10), 
		FACTURADO_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaProveedorAcreedor(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaRepresentantes {
		MES(0),
		VENTAS_A�O(1), 
		VENTAS_PORCENTAJE_SOBRE_A�O(2), 
		VENTAS_A�O_ANTERIOR(3), 
		BENEFICIO_ART_A�O(4), 
		BENEFICIO_ART_PORCENTAJE_SOBRE_VENTAS(5), 
		BENEFICIO_ART_PORCENTAJE_SOBRE_A�O(6), 
		BENEFICIO_ART_A�O_ANTERIOR(7), 
		//BENEFICIO_ART_PORCENTAJE_SOBRE_VENTAS(8), 
		COMISIONES_A�O(9), 
		COMISIONES_PORCENTAJE_SOBRE_A�O(10), 
		COMISIONES_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaRepresentantes(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaTiposCliente {
		MES(0),
		COMPRAS_A�O(1), 
		COMPRAS_PORCENTAJE_SOBRE_A�O(2), 
		COMPRAS_A�O_ANTERIOR(3), 
		RAPPELS_A�O(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_A�O(6), 
		RAPPELS_A�O_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_A�O(9), 
		FACTURADO_PROCENTAJE_SOBRE_A�O(10), 
		FACTURADO_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaTiposCliente(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaZonasCliente {
		MES(0),
		COMPRAS_A�O(1), 
		COMPRAS_PORCENTAJE_SOBRE_A�O(2), 
		COMPRAS_A�O_ANTERIOR(3), 
		RAPPELS_A�O(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_A�O(6), 
		RAPPELS_A�O_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_A�O(9), 
		FACTURADO_PROCENTAJE_SOBRE_A�O(10), 
		FACTURADO_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaZonasCliente(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaCuentasContables {
		MES(0),
		COMPRAS_A�O(1), 
		COMPRAS_PORCENTAJE_SOBRE_A�O(2), 
		COMPRAS_A�O_ANTERIOR(3), 
		RAPPELS_A�O(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_A�O(6), 
		RAPPELS_A�O_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_A�O(9), 
		FACTURADO_PROCENTAJE_SOBRE_A�O(10), 
		FACTURADO_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaCuentasContables(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaTrabajadores {
		MES(0),
		COMPRAS_A�O(1), 
		COMPRAS_PORCENTAJE_SOBRE_A�O(2), 
		COMPRAS_A�O_ANTERIOR(3), 
		RAPPELS_A�O(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_A�O(6), 
		RAPPELS_A�O_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_A�O(9), 
		FACTURADO_PROCENTAJE_SOBRE_A�O(10), 
		FACTURADO_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaTrabajadores(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaCentros {
		MES(0),
		COMPRAS_A�O(1), 
		COMPRAS_PORCENTAJE_SOBRE_A�O(2), 
		COMPRAS_A�O_ANTERIOR(3), 
		RAPPELS_A�O(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_A�O(6), 
		RAPPELS_A�O_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_A�O(9), 
		FACTURADO_PROCENTAJE_SOBRE_A�O(10), 
		FACTURADO_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaCentros(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaBolsaDescuentos {
		MES(0),
		COMPRAS_A�O(1), 
		COMPRAS_PORCENTAJE_SOBRE_A�O(2), 
		COMPRAS_A�O_ANTERIOR(3), 
		RAPPELS_A�O(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_A�O(6), 
		RAPPELS_A�O_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_A�O(9), 
		FACTURADO_PROCENTAJE_SOBRE_A�O(10), 
		FACTURADO_A�O_ANTERIOR(11); 

		private int value;

		private ColumnaBolsaDescuentos(int value) {
			this.value = value;
		}
	}
	
	String nombreMes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	JDialog pantalla;
	JLabel lA�o, lLiteralA�o, lDesCripcionCuenta, lZonaIzquierda, lZonaCentro, lZonaDerecha;		
	JButton jbAtras, jbAdelante;
	Image imgAtras, imgAdelante;
	
	JTable jtEstadistico;
	JScrollPane spEstadistico;
	DefaultTableCellRenderer tcr;
	TableCellRenderer tcr2;
	
	ResultSet rs = null;
	MysqlConnect m = null;
	
	// Consulta SQL
	String strSql = "";
	String strCuenta = "";
	String strTituloCuenta = "";
	int extensionOtroFichero = 0;
	Fecha fecha = new Fecha();
	int a�o = fecha.getAnio();
	
	
	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Para que no podamos editar los datos de la tabla
			return false;
		}
	};
	
	public IndiceAcumuladosEstadisticos(String strCuenta, String strTituloCuenta, int extensionOtroFichero){
		this.strCuenta = strCuenta.trim();
		this.strTituloCuenta = strTituloCuenta;
		this.extensionOtroFichero = extensionOtroFichero;
		m = MysqlConnect.getDbCon();
		
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Acumulados Estad�sticos" + " - " + strTituloCuenta);
		pantalla.setModal(true);
		pantalla.setSize(730, 500);
		//pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		 
		pantalla.setDefaultCloseOperation(0);
		pantalla.setLayout(null);
		
		lZonaIzquierda = new JLabel();
		lZonaIzquierda.setBounds(100, 15, 170, 20);
		lZonaIzquierda.setFont(Apariencia.cambiaFuente());
		pantalla.add(lZonaIzquierda);
		
		lZonaCentro = new JLabel();
		lZonaCentro.setBounds(280, 15, 210, 20);
		lZonaCentro.setFont(Apariencia.cambiaFuente());
		pantalla.add(lZonaCentro);
		
		lZonaDerecha = new JLabel();
		lZonaDerecha.setBounds(500, 15, 190, 20);
		lZonaDerecha.setFont(Apariencia.cambiaFuente());
		pantalla.add(lZonaDerecha);
		
		TableColumn columna = new TableColumn();
		// Dependiendo de que estad�sticas tenemos que visualizar, montaremos
		// una pantalla u otra.
		switch(extensionOtroFichero){
		// Clientes
		case 1:lZonaIzquierda.setText("Ventas__________");
				lZonaCentro.setText("Beneficio____________");
				lZonaDerecha.setText("Impagados___________");
				modeloTabla.addColumn("Mes");
				modeloTabla.addColumn("V.A�o");
				modeloTabla.addColumn("V.%/A");
				modeloTabla.addColumn("V.A�o Ant.");
				modeloTabla.addColumn("B.A�o");
				modeloTabla.addColumn("B.%/V");
				modeloTabla.addColumn("B.%/A");
				modeloTabla.addColumn("B.A�o Ant.");
				modeloTabla.addColumn("B.%/V Ant.");
				modeloTabla.addColumn("I.%/V");
				modeloTabla.addColumn("I.A�o");
				modeloTabla.addColumn("I.%/A");
				modeloTabla.addColumn("I.A�o Ant.");
		
				jtEstadistico = new JTable(modeloTabla);
				jtEstadistico.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
		
				//TableColumn columna = new TableColumn();
				// Establecemos el ancho
				jtEstadistico.getColumn("Mes").setMaxWidth(80);
				jtEstadistico.getColumn("V.A�o").setMaxWidth(70);
				jtEstadistico.getColumn("V.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("V.A�o Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("B.A�o").setMaxWidth(70);
				jtEstadistico.getColumn("B.%/V").setMaxWidth(50);
				jtEstadistico.getColumn("B.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("B.A�o Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("B.%/V Ant.").setMaxWidth(40);
				jtEstadistico.getColumn("I.%/V").setMaxWidth(40);
				jtEstadistico.getColumn("I.A�o").setMaxWidth(70);
				jtEstadistico.getColumn("I.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("I.A�o Ant.").setMaxWidth(70);

				jtEstadistico.setRowHeight(25);
		
				// Hacemos que la comluna del saldo se alinee a la derecha y
				// que salga en rojo si es negativa.
				tcr2 = new TableCellRenderer();
				jtEstadistico.getColumn("V.A�o").setCellRenderer(tcr2);
				jtEstadistico.getColumn("V.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("V.A�o Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.A�o").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.A�o Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.%/V Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.A�o").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.A�o Ant.").setCellRenderer(tcr2);
				break;
		//Proveedores
		case 2: lZonaIzquierda.setText("Compras_________");
				lZonaCentro.setText("Rappels______________");
				lZonaDerecha.setText("Facturado___________");
				modeloTabla.addColumn("Mes");
				modeloTabla.addColumn("C.A�o");
				modeloTabla.addColumn("C.%/A");
				modeloTabla.addColumn("C.A�o Ant.");
				modeloTabla.addColumn("R.A�o");
				modeloTabla.addColumn("R.%/V");
				modeloTabla.addColumn("R.%/A");
				modeloTabla.addColumn("R.A�o Ant.");
				modeloTabla.addColumn("F.%/V");
				modeloTabla.addColumn("F.A�o");
				modeloTabla.addColumn("F.%/A");
				modeloTabla.addColumn("F.A�o Ant.");
				
				jtEstadistico = new JTable(modeloTabla);
				jtEstadistico.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
				

				// Establecemos el ancho
				jtEstadistico.getColumn("Mes").setMaxWidth(80);
				jtEstadistico.getColumn("C.A�o").setMaxWidth(70);
				jtEstadistico.getColumn("C.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("C.A�o Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("R.A�o").setMaxWidth(70);
				jtEstadistico.getColumn("R.%/V").setMaxWidth(40);
				jtEstadistico.getColumn("R.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("R.A�o Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("F.%/V").setMaxWidth(40);
				jtEstadistico.getColumn("F.A�o").setMaxWidth(70);
				jtEstadistico.getColumn("F.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("F.A�o Ant.").setMaxWidth(70);

				jtEstadistico.setRowHeight(25);
				
				// Hacemos que la comluna del saldo se alinee a la derecha y
				// que salga en rojo si es negativa.
				tcr2 = new TableCellRenderer();
				jtEstadistico.getColumn("C.A�o").setCellRenderer(tcr2);
				jtEstadistico.getColumn("C.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("C.A�o Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.A�o").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.A�o Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.A�o").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.A�o Ant.").setCellRenderer(tcr2);
				break;
		}
	
		
		// Creamos un JscrollPane y le agregamos la JTable
		spEstadistico = new JScrollPane(jtEstadistico);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		spEstadistico.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		// Agregamos el JScrollPane al contenedor
		spEstadistico.setBounds(10, 50, 700, 365);	
		spEstadistico.setFont(Apariencia.cambiaFuente());
		pantalla.add(spEstadistico);			
		
		lA�o = new JLabel();
		lA�o.setBounds(10, 15, 60, 20);
		lA�o.setFont(Apariencia.cambiaFuente());
		pantalla.add(lA�o);
		
		lLiteralA�o = new JLabel("A�o");
		lLiteralA�o.setBounds(280, 430, 40, 20);
		lLiteralA�o.setFont(Apariencia.cambiaFuente());
		pantalla.add(lLiteralA�o);
		
		jbAtras = new JButton();
		jbAtras.setBounds(340, 430, 30, 30);
		jbAtras.setFont(Apariencia.cambiaFuente());
		jbAtras.addActionListener(new AtrasListener());
		try {
			imgAtras = ImageIO.read(getClass().getResource("/imagenes/Atras.gif"));
			jbAtras.setIcon(new ImageIcon(imgAtras));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbAtras);
		
		jbAdelante = new JButton();
		jbAdelante.setBounds(380, 430, 30, 30);
		jbAdelante.setFont(Apariencia.cambiaFuente());
		jbAdelante.addActionListener(new AdelanteListener());
		try {
			imgAdelante = ImageIO.read(getClass().getResource("/imagenes/Adelante.gif"));
			jbAdelante.setIcon(new ImageIcon(imgAdelante));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbAdelante);
		
		
		switch(extensionOtroFichero){
		// Clientes
			case 1: cargaDatosClientes();
					break;
			case 2: cargaDatosProveedorAcreedor();
					break;
		}
		
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
			
			if(row < 12)
				c.setBackground(Color.WHITE);
			if(row == 12)			
				c.setBackground(Color.ORANGE);
			if(row == 13)			
				c.setBackground(Color.YELLOW);
			

			return c;
		}
	}
	
	public void cargaDatosProveedorAcreedor(){
		borrarTabla();
						
		Object fila[] = {"", "", "", "", "", "", "", "", "", "", "", ""};	
		
		int numeroRegistros = 0;
		int mes = fecha.getMes() + 1;
	
		lA�o.setText(String.valueOf(a�o));
		double dAcumuladoFacturadoTotalA�o = 0.0, 
		    dAcumuladoFacturadoMesA�o = 0.0, 
		    dAcumuladoFacturadoTotalA�oAnterior = 0.0,
		    dAcumuladoFacturadoMesA�oAnterior = 0.0,
		    dAcumuladoComprasTotalA�o = 0.0, 
		    dAcumuladoComprasMesA�o = 0.0, 
		    dAcumuladoComprasTotalA�oAnterior = 0.0,
		    dAcumuladoComprasMesA�oAnterior = 0.0,
			dAcumuladoRappelTotalA�o = 0.0, 
			dAcumuladoRappelMesA�o = 0.0, 
			dAcumuladoRappelTotalA�oAnterior = 0.0,
			dAcumuladoRappelMesA�oAnterior = 0.0;
		
		String strSql;				
		String claveFechaAsientoApunte = "";
		double baseTotal = 0.0;
		
		
		m = MysqlConnect.getDbCon();
		
		for(int i = 0; i < nombreMes.length; i++){
			fila[ColumnaProveedorAcreedor.MES.value] = nombreMes[i];
			fila[1] = "";
			fila[2] = "";
			fila[3] = "";
			fila[4] = "";
			fila[5] = "";
			fila[6] = "";
			fila[7] = "";
			fila[8] = "";
			fila[9] = "";
			fila[10] = "";
			fila[11] = "";
			modeloTabla.addRow(fila);
 		}
			fila[0] = "Totales";
			modeloTabla.addRow(fila);
			fila[0] = "Ac.Mes";
			modeloTabla.addRow(fila);
			
		
			
		// Cargamos los datos del a�o anterior
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + (a�o - 1);
					
		dAcumuladoFacturadoTotalA�oAnterior = 0.0;
	    dAcumuladoFacturadoMesA�oAnterior = 0.0;
	    dAcumuladoComprasTotalA�oAnterior = 0.0;
	    dAcumuladoComprasMesA�oAnterior = 0.0;
	    dAcumuladoRappelTotalA�oAnterior = 0.0;
		dAcumuladoRappelMesA�oAnterior = 0.0;
		
		numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){

			try {							
				EstadisticaContable estadistica = new EstadisticaContable();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					estadistica.read(rs);					
					
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.COMPRAS_A�O_ANTERIOR.value);
						dAcumuladoComprasTotalA�oAnterior += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoComprasMesA�oAnterior += estadistica.getAcumulado(1);
					}
					if((int)estadistica.getAcumulado(2) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(2), estadistica.getMes() - 1, ColumnaProveedorAcreedor.RAPPELS_A�O_ANTERIOR.value);
						dAcumuladoRappelTotalA�oAnterior += estadistica.getAcumulado(2);
						if(estadistica.getMes() <= mes)
							dAcumuladoRappelMesA�oAnterior += estadistica.getAcumulado(2);
					}
					if((int)estadistica.getAcumulado(0) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(0)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.FACTURADO_A�O_ANTERIOR.value);
						dAcumuladoFacturadoTotalA�oAnterior += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesA�oAnterior += estadistica.getAcumulado(0);
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Cargamos los datos de este a�o
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + a�o;
							
		dAcumuladoFacturadoTotalA�o = 0.0;
	    dAcumuladoFacturadoMesA�o = 0.0;
	    dAcumuladoComprasTotalA�o = 0.0;
	    dAcumuladoComprasMesA�o = 0.0;
	    dAcumuladoRappelTotalA�o = 0.0;
		dAcumuladoRappelMesA�o = 0.0;
		
		numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){
			try {							
				EstadisticaContable estadistica = new EstadisticaContable();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					estadistica.read(rs);					
					
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.COMPRAS_A�O.value);
						dAcumuladoComprasTotalA�o += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoComprasMesA�o += estadistica.getAcumulado(1);
					}
					if((int)estadistica.getAcumulado(2) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(2), estadistica.getMes() - 1, ColumnaProveedorAcreedor.RAPPELS_A�O.value);
						dAcumuladoRappelTotalA�o += estadistica.getAcumulado(2);
						if(estadistica.getMes() <= mes)
							dAcumuladoRappelMesA�o += estadistica.getAcumulado(2);
					}
					if((int)estadistica.getAcumulado(0) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(0)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.FACTURADO_A�O.value);
						dAcumuladoFacturadoTotalA�o += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesA�o += estadistica.getAcumulado(0);
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Ponemos los acumulados Totales de a�o
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasTotalA�o), 12, ColumnaProveedorAcreedor.COMPRAS_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasTotalA�oAnterior), 12, ColumnaProveedorAcreedor.COMPRAS_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelTotalA�o), 12, ColumnaProveedorAcreedor.RAPPELS_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelTotalA�oAnterior), 12, ColumnaProveedorAcreedor.RAPPELS_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoTotalA�o), 12, ColumnaProveedorAcreedor.FACTURADO_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoTotalA�oAnterior), 12, ColumnaProveedorAcreedor.FACTURADO_A�O_ANTERIOR.value);

		// Ponemos los acumulados Totales de mes
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasMesA�o), 13, ColumnaProveedorAcreedor.COMPRAS_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasMesA�oAnterior), 13, ColumnaProveedorAcreedor.COMPRAS_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelMesA�o), 13, ColumnaProveedorAcreedor.RAPPELS_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelMesA�oAnterior), 13, ColumnaProveedorAcreedor.RAPPELS_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoMesA�o), 13, ColumnaProveedorAcreedor.FACTURADO_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoMesA�oAnterior), 13, ColumnaProveedorAcreedor.FACTURADO_A�O_ANTERIOR.value);
		
		
		
		// Calculamos los % en Compras
		int porcent = 0, iA�o = 0, iA�oAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_A�O.value)).length() > 0)
				iA�o = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_A�O.value)).replaceAll("\\.", ""));
			else
				iA�o = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_A�O_ANTERIOR.value)).length() > 0)
				iA�oAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_A�O_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iA�oAnt = 0;
			
			if(iA�o == 0 || iA�oAnt == 0)
				porcent = 0;
			else
				porcent = (iA�o * 100) / iA�oAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaProveedorAcreedor.COMPRAS_PORCENTAJE_SOBRE_A�O.value);					
		}
		

		// Calculamos los % en Rappels
		porcent = 0; iA�o = 0; iA�oAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_A�O.value)).length() > 0)
				iA�o = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_A�O.value)).replaceAll("\\.", ""));
			else
				iA�o = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_A�O_ANTERIOR.value)).length() > 0)
				iA�oAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_A�O_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iA�oAnt = 0;
			
			if(iA�o == 0 || iA�oAnt == 0)
				porcent = 0;
			else
				porcent = (iA�o * 100) / iA�oAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaProveedorAcreedor.RAPPELS_PORCENTAJE_SOBRE_A�O.value);					
		}

		// Calculamos los % en Facturado
		porcent = 0; iA�o = 0; iA�oAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_A�O.value)).length() > 0)
				iA�o = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_A�O.value)).replaceAll("\\.", ""));
			else
				iA�o = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_A�O_ANTERIOR.value)).length() > 0)
				iA�oAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_A�O_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iA�oAnt = 0;
			
			if(iA�o == 0 || iA�oAnt == 0)
				porcent = 0;
			else
				porcent = (iA�o * 100) / iA�oAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaProveedorAcreedor.FACTURADO_PORCENTAJE_SOBRE_A�O.value);					
		}

	}
	
	
	public void cargaDatosClientes(){
		borrarTabla();
						
		Object fila[] = {"", "", "",  "", "", "", "", "", "", "", "", "", ""};	
		
		int numeroRegistros = 0;
		int mes = fecha.getMes() + 1;
	
		lA�o.setText(String.valueOf(a�o));
		double dAcumuladoVentasTotalA�o = 0.0, 
		    dAcumuladoVentasMesA�o = 0.0, 
		    dAcumuladoVentasTotalA�oAnterior = 0.0,
		    dAcumuladoVentasMesA�oAnterior = 0.0,
		    dAcumuladoBeneficioTotalA�o = 0.0, 
		    dAcumuladoBeneficioMesA�o = 0.0, 
		    dAcumuladoBeneficioTotalA�oAnterior = 0.0,
		    dAcumuladoBeneficioMesA�oAnterior = 0.0,
			dAcumuladoImpagadosTotalA�o = 0.0, 
			dAcumuladoImpagadosMesA�o = 0.0, 
			dAcumuladoImpagadosTotalA�oAnterior = 0.0,
			dAcumuladoImpagadosMesA�oAnterior = 0.0,
			dAcumuladoFacturadoTotalA�o = 0.0, 
			dAcumuladoFacturadoMesA�o = 0.0, 
			dAcumuladoFacturadoTotalA�oAnterior = 0.0,
			dAcumuladoFacturadoMesA�oAnterior = 0.0,
			dAcumuladoRappelTotalA�o = 0.0, 
			dAcumuladoRappelMesA�o = 0.0, 
			dAcumuladoRappelTotalA�oAnterior = 0.0,
			dAcumuladoRappelMesA�oAnterior = 0.0;
		int iAcumuladoNumeroImpagadosTotalA�o = 0,
		    iAcumuladoNumeroImpagadosMesA�o = 0;
		
		String strSql;				
		String claveFechaAsientoApunte = "";
		double baseTotal = 0.0;
		
		
		m = MysqlConnect.getDbCon();
		
		for(int i = 0; i < nombreMes.length; i++){
			fila[ColumnaClientes.MES.value] = nombreMes[i];
			fila[1] = "";
			fila[2] = "";
			fila[3] = "";
			fila[4] = "";
			fila[5] = "";
			fila[6] = "";
			fila[7] = "";
			fila[8] = "";
			fila[9] = "";
			fila[10] = "";
			fila[11] = "";
			fila[12] = "";
			modeloTabla.addRow(fila);
 		}
			fila[0] = "Totales";
			modeloTabla.addRow(fila);
			fila[0] = "Ac.Mes";
			modeloTabla.addRow(fila);
			
		
			
		// Cargamos los datos del a�o anterior
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + (a�o - 1);
				
		dAcumuladoFacturadoTotalA�oAnterior = 0.0;
		dAcumuladoFacturadoMesA�oAnterior = 0.0;
		dAcumuladoVentasTotalA�oAnterior = 0.0;
	    dAcumuladoVentasMesA�oAnterior = 0.0;
	    /* No hay con que comparar estos datos, no mostramos del a�o anterior
	    dAcumuladoBeneficioTotalA�oAnterior = 0.0;
	    dAcumuladoBeneficioMesA�oAnterior = 0.0;
	    dAcumuladoImpagadosTotalA�oAnterior = 0.0;
		dAcumuladoImpagadosMesA�oAnterior = 0.0;
		
		dAcumuladoRappelTotalA�oAnterior = 0.0;
		dAcumuladoRappelMesA�oAnterior = 0.0;
		*/
		
		numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){

			try {							
				EstadisticaContable estadistica = new EstadisticaContable();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					estadistica.read(rs);					
					/* No mostramos el facturado
					if((int)estadistica.getAcumulado(0) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(0)), estadistica.getMes() - 1, ColumnaClientes..value);
						dAcumuladoFacturadoTotalA�oAnterior += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesA�oAnterior += estadistica.getAcumulado(0);
					}*/
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaClientes.VENTAS_A�O_ANTERIOR.value);
						dAcumuladoVentasTotalA�oAnterior += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoVentasMesA�oAnterior += estadistica.getAcumulado(1);
					}
					
					if((int)estadistica.getAcumulado(4) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(4), estadistica.getMes() - 1, ColumnaClientes.BENEFICIO_A�O_ANTERIOR.value);
						dAcumuladoBeneficioTotalA�oAnterior += estadistica.getAcumulado(4);
						if(estadistica.getMes() <= mes)
							dAcumuladoBeneficioMesA�oAnterior += estadistica.getAcumulado(4);
					}					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Cargamos los datos de este a�o
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + a�o;
							
		dAcumuladoFacturadoTotalA�o = 0.0;
	    dAcumuladoFacturadoMesA�o = 0.0;
	    dAcumuladoVentasTotalA�o = 0.0;
	    dAcumuladoVentasMesA�o = 0.0;
	    dAcumuladoBeneficioTotalA�o = 0.0; 
	    dAcumuladoBeneficioMesA�o = 0.0;
	    iAcumuladoNumeroImpagadosTotalA�o = 0;
	    iAcumuladoNumeroImpagadosMesA�o = 0;	    
		dAcumuladoImpagadosTotalA�o = 0.0;
		dAcumuladoImpagadosMesA�o = 0.0;
		dAcumuladoRappelTotalA�o = 0.0;
		dAcumuladoRappelMesA�o = 0.0;
	    
		
		numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){
			try {							
				EstadisticaContable estadistica = new EstadisticaContable();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					estadistica.read(rs);					
					/* No mostramos el facturado en la tabla ???
					if((int)estadistica.getAcumulado(0) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(0)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.FACTURADO_A�O.value);
						dAcumuladoFacturadoTotalA�o += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesA�o += estadistica.getAcumulado(0);
					}*/
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaClientes.VENTAS_A�O.value);
						dAcumuladoVentasTotalA�o += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoVentasMesA�o += estadistica.getAcumulado(1);
					}
					if((int)estadistica.getAcumulado(2) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(2), estadistica.getMes() - 1, ColumnaClientes.IMPAGADOS_RAPPEL.value);
						dAcumuladoRappelTotalA�o += estadistica.getAcumulado(2);
						if(estadistica.getMes() <= mes)
							dAcumuladoRappelMesA�o += estadistica.getAcumulado(2);
					}
					if((int)estadistica.getAcumulado(3) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(3), estadistica.getMes() - 1, ColumnaClientes.IMPAGADOS_NUMERO.value);
						iAcumuladoNumeroImpagadosTotalA�o += estadistica.getAcumulado(3);
						if(estadistica.getMes() <= mes)
							iAcumuladoNumeroImpagadosMesA�o += estadistica.getAcumulado(3);
					}
					if((int)estadistica.getAcumulado(4) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(4), estadistica.getMes() - 1, ColumnaClientes.BENEFICIO_A�O.value);
						dAcumuladoBeneficioTotalA�o += estadistica.getAcumulado(4);
						if(estadistica.getMes() <= mes)
							dAcumuladoBeneficioMesA�o += estadistica.getAcumulado(4);
					}
					if((int)estadistica.getAcumulado(5) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(5), estadistica.getMes() - 1, ColumnaClientes.IMPAGADOS_IMPORTE.value);
						dAcumuladoImpagadosTotalA�o += estadistica.getAcumulado(5);
						if(estadistica.getMes() <= mes)
							dAcumuladoImpagadosMesA�o += estadistica.getAcumulado(5);
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Ponemos los acumulados Totales de a�o
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasTotalA�o), 12, ColumnaClientes.VENTAS_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasTotalA�oAnterior), 12, ColumnaClientes.VENTAS_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioTotalA�o), 12, ColumnaClientes.BENEFICIO_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioTotalA�oAnterior), 12, ColumnaClientes.BENEFICIO_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(iAcumuladoNumeroImpagadosTotalA�o), 12, ColumnaClientes.IMPAGADOS_NUMERO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoImpagadosTotalA�o), 12, ColumnaClientes.IMPAGADOS_IMPORTE.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelTotalA�o), 12, ColumnaClientes.IMPAGADOS_RAPPEL.value);

		// Ponemos los acumulados Totales de mes
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasMesA�o), 13, ColumnaClientes.VENTAS_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasMesA�oAnterior), 13, ColumnaClientes.VENTAS_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioMesA�o), 13, ColumnaClientes.BENEFICIO_A�O.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioMesA�oAnterior), 13, ColumnaClientes.BENEFICIO_A�O_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(iAcumuladoNumeroImpagadosMesA�o), 13, ColumnaClientes.IMPAGADOS_NUMERO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoImpagadosMesA�o), 13, ColumnaClientes.IMPAGADOS_IMPORTE.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelMesA�o), 13, ColumnaClientes.IMPAGADOS_RAPPEL.value);
		
		// Calculamos los % en Ventas
		int porcent = 0, iA�o = 0, iA�oAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O.value)).length() > 0)
				iA�o = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O.value)).replaceAll("\\.", ""));
			else
				iA�o = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O_ANTERIOR.value)).length() > 0)
				iA�oAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iA�oAnt = 0;
			
			if(iA�o == 0 || iA�oAnt == 0)
				porcent = 0;
			else
				porcent = (iA�o * 100) / iA�oAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaClientes.VENTAS_PORCENTAJE_SOBRE_A�O.value);					
		}
		

		// Calculamos los % en Beneficio
		porcent = 0; iA�o = 0; iA�oAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O.value)).length() > 0)
				iA�o = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O.value)).replaceAll("\\.", ""));
			else
				iA�o = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O_ANTERIOR.value)).length() > 0)
				iA�oAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iA�oAnt = 0;
			
			if(iA�o == 0 || iA�oAnt == 0)
				porcent = 0;
			else
				porcent = (iA�o * 100) / iA�oAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaClientes.BENEFICIO_PORCENTAJE_SOBRE_A�O.value);			
			
			// Calculo porcentaje beneficio
			double dPorcent = 0.0;
			double dVentas = 0.0;
			double dBeneficio = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O.value)).length() > 0)
				dVentas = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O.value)).replaceAll("\\.", ""));
			else
				dVentas = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O.value)).length() > 0)
				dBeneficio = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O.value)).replaceAll("\\.", ""));
			else
				dBeneficio = 0.0;
			
			if(dVentas == 0.0 || dBeneficio == 0.0)
				dPorcent = 0.0;
			else{
				dPorcent = (dBeneficio * 100.0) / dVentas;
				if(dPorcent > 999.9)
					dPorcent = 999.0;
				
				modeloTabla.setValueAt(Cadena.formatoConComaDecimal(dPorcent), i, ColumnaClientes.BENEFICIO_PORCENTAJE_SOBRE_VENTAS.value);
			}
			
			// Calculo porcentaje beneficio A�O ANTERIOR
			dPorcent = 0.0;
			dVentas = 0.0;
			dBeneficio = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O_ANTERIOR.value)).length() > 0)
				dVentas = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				dVentas = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O_ANTERIOR.value)).length() > 0)
				dBeneficio = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_A�O_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				dBeneficio = 0.0;
			
			if(dVentas == 0.0 || dBeneficio == 0.0)
				dPorcent = 0.0;
			else{
				dPorcent = (dBeneficio * 100.0) / dVentas;
				if(dPorcent > 999.9)
					dPorcent = 999.0;
				
				modeloTabla.setValueAt(Cadena.formatoConComaDecimal(dPorcent), i, ColumnaClientes.BENEFICIO_PORCENTAJE_SOBRE_VENTAS_A�O_ANTERIOR.value);
			}
			
			// Calculo porcentaje Impagados sobre Ventas
			dPorcent = 0.0;
			dVentas = 0.0;
			double dImpagados = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O.value)).length() > 0)
				dVentas = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_A�O.value)).replaceAll("\\.", ""));
			else
				dVentas = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.IMPAGADOS_IMPORTE.value)).length() > 0)
				dImpagados = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.IMPAGADOS_IMPORTE.value)).replaceAll("\\.", ""));
			else
				dImpagados = 0.0;
			
			if(dVentas == 0.0 || dImpagados == 0.0)
				dPorcent = 0.0;
			else{
				dPorcent = (dImpagados * 100.0) / dVentas;
				if(dPorcent > 999.9)
					dPorcent = 999.0;
				
				modeloTabla.setValueAt(Cadena.formatoConComaDecimal(dPorcent), i, ColumnaClientes.IMPAGADOS_PORCENTAJE_SOBRE_VENTAS.value);
			}

		}
	}
	
	private void borrarTabla(){
		// Vaciamos la tabla
		//int a = modeloTabla.getRowCount() - 1;

		//for (int i = a; i >= 0; i--)
		//	modeloTabla.removeRow(i);
		modeloTabla.setRowCount(0);
	}
	
	class AtrasListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			a�o--;
			switch(extensionOtroFichero){
			// Clientes
			case 1: cargaDatosClientes();
				break;
			//Proveedores
			case 2:  cargaDatosProveedorAcreedor();
				break;
			}
		}		
	}
	
	class AdelanteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			a�o++;
			switch(extensionOtroFichero){
			// Clientes
			case 1: cargaDatosClientes();
				break;
			//Proveedores
			case 2:  cargaDatosProveedorAcreedor();
				break;
			}
		}		
	}

}
