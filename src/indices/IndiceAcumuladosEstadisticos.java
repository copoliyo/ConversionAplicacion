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
      *      Acum 1 Saldo fin a¤o para restar de Acum a¤o
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
             label "Año"   line 3,15 col 75,00 font small-font
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
             label "Año"   line 3,15 col 74,00 font small-font
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
		VENTAS_AÑO(1), 
		VENTAS_PORCENTAJE_SOBRE_AÑO(2), 
		VENTAS_AÑO_ANTERIOR(3), 
		BENEFICIO_AÑO(4), 
		BENEFICIO_PORCENTAJE_SOBRE_VENTAS(5), 
		BENEFICIO_PORCENTAJE_SOBRE_AÑO(6), 
		BENEFICIO_AÑO_ANTERIOR(7), 
		BENEFICIO_PORCENTAJE_SOBRE_VENTAS_AÑO_ANTERIOR(8),
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
		COMPRAS_AÑO(1), 
		COMPRAS_PORCENTAJE_SOBRE_AÑO(2), 
		COMPRAS_AÑO_ANTERIOR(3), 
		RAPPELS_AÑO(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_AÑO(6), 
		RAPPELS_AÑO_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_AÑO(9), 
		FACTURADO_PORCENTAJE_SOBRE_AÑO(10), 
		FACTURADO_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaProveedorAcreedor(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaRepresentantes {
		MES(0),
		VENTAS_AÑO(1), 
		VENTAS_PORCENTAJE_SOBRE_AÑO(2), 
		VENTAS_AÑO_ANTERIOR(3), 
		BENEFICIO_ART_AÑO(4), 
		BENEFICIO_ART_PORCENTAJE_SOBRE_VENTAS(5), 
		BENEFICIO_ART_PORCENTAJE_SOBRE_AÑO(6), 
		BENEFICIO_ART_AÑO_ANTERIOR(7), 
		//BENEFICIO_ART_PORCENTAJE_SOBRE_VENTAS(8), 
		COMISIONES_AÑO(9), 
		COMISIONES_PORCENTAJE_SOBRE_AÑO(10), 
		COMISIONES_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaRepresentantes(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaTiposCliente {
		MES(0),
		COMPRAS_AÑO(1), 
		COMPRAS_PORCENTAJE_SOBRE_AÑO(2), 
		COMPRAS_AÑO_ANTERIOR(3), 
		RAPPELS_AÑO(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_AÑO(6), 
		RAPPELS_AÑO_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_AÑO(9), 
		FACTURADO_PROCENTAJE_SOBRE_AÑO(10), 
		FACTURADO_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaTiposCliente(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaZonasCliente {
		MES(0),
		COMPRAS_AÑO(1), 
		COMPRAS_PORCENTAJE_SOBRE_AÑO(2), 
		COMPRAS_AÑO_ANTERIOR(3), 
		RAPPELS_AÑO(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_AÑO(6), 
		RAPPELS_AÑO_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_AÑO(9), 
		FACTURADO_PROCENTAJE_SOBRE_AÑO(10), 
		FACTURADO_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaZonasCliente(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaCuentasContables {
		MES(0),
		COMPRAS_AÑO(1), 
		COMPRAS_PORCENTAJE_SOBRE_AÑO(2), 
		COMPRAS_AÑO_ANTERIOR(3), 
		RAPPELS_AÑO(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_AÑO(6), 
		RAPPELS_AÑO_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_AÑO(9), 
		FACTURADO_PROCENTAJE_SOBRE_AÑO(10), 
		FACTURADO_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaCuentasContables(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaTrabajadores {
		MES(0),
		COMPRAS_AÑO(1), 
		COMPRAS_PORCENTAJE_SOBRE_AÑO(2), 
		COMPRAS_AÑO_ANTERIOR(3), 
		RAPPELS_AÑO(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_AÑO(6), 
		RAPPELS_AÑO_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_AÑO(9), 
		FACTURADO_PROCENTAJE_SOBRE_AÑO(10), 
		FACTURADO_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaTrabajadores(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaCentros {
		MES(0),
		COMPRAS_AÑO(1), 
		COMPRAS_PORCENTAJE_SOBRE_AÑO(2), 
		COMPRAS_AÑO_ANTERIOR(3), 
		RAPPELS_AÑO(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_AÑO(6), 
		RAPPELS_AÑO_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_AÑO(9), 
		FACTURADO_PROCENTAJE_SOBRE_AÑO(10), 
		FACTURADO_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaCentros(int value) {
			this.value = value;
		}
	}
	
	public enum ColumnaBolsaDescuentos {
		MES(0),
		COMPRAS_AÑO(1), 
		COMPRAS_PORCENTAJE_SOBRE_AÑO(2), 
		COMPRAS_AÑO_ANTERIOR(3), 
		RAPPELS_AÑO(4), 
		RAPPELS_PORCENTAJE_SOBRE_VENTAS(5), 
		RAPPELS_PORCENTAJE_SOBRE_AÑO(6), 
		RAPPELS_AÑO_ANTERIOR(7), 
		FACTURADO_PORCENTAJE_SOBRE_VENTAS(8), 
		FACTURADO_AÑO(9), 
		FACTURADO_PROCENTAJE_SOBRE_AÑO(10), 
		FACTURADO_AÑO_ANTERIOR(11); 

		private int value;

		private ColumnaBolsaDescuentos(int value) {
			this.value = value;
		}
	}
	
	String nombreMes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	JDialog pantalla;
	JLabel lAño, lLiteralAño, lDesCripcionCuenta, lZonaIzquierda, lZonaCentro, lZonaDerecha;		
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
	int año = fecha.getAnio();
	
	
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
		pantalla.setTitle("Acumulados Estadísticos" + " - " + strTituloCuenta);
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
		// Dependiendo de que estadísticas tenemos que visualizar, montaremos
		// una pantalla u otra.
		switch(extensionOtroFichero){
		// Clientes
		case 1:lZonaIzquierda.setText("Ventas__________");
				lZonaCentro.setText("Beneficio____________");
				lZonaDerecha.setText("Impagados___________");
				modeloTabla.addColumn("Mes");
				modeloTabla.addColumn("V.Año");
				modeloTabla.addColumn("V.%/A");
				modeloTabla.addColumn("V.Año Ant.");
				modeloTabla.addColumn("B.Año");
				modeloTabla.addColumn("B.%/V");
				modeloTabla.addColumn("B.%/A");
				modeloTabla.addColumn("B.Año Ant.");
				modeloTabla.addColumn("B.%/V Ant.");
				modeloTabla.addColumn("I.%/V");
				modeloTabla.addColumn("I.Año");
				modeloTabla.addColumn("I.%/A");
				modeloTabla.addColumn("I.Año Ant.");
		
				jtEstadistico = new JTable(modeloTabla);
				jtEstadistico.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
		
				//TableColumn columna = new TableColumn();
				// Establecemos el ancho
				jtEstadistico.getColumn("Mes").setMaxWidth(80);
				jtEstadistico.getColumn("V.Año").setMaxWidth(70);
				jtEstadistico.getColumn("V.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("V.Año Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("B.Año").setMaxWidth(70);
				jtEstadistico.getColumn("B.%/V").setMaxWidth(50);
				jtEstadistico.getColumn("B.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("B.Año Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("B.%/V Ant.").setMaxWidth(40);
				jtEstadistico.getColumn("I.%/V").setMaxWidth(40);
				jtEstadistico.getColumn("I.Año").setMaxWidth(70);
				jtEstadistico.getColumn("I.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("I.Año Ant.").setMaxWidth(70);

				jtEstadistico.setRowHeight(25);
		
				// Hacemos que la comluna del saldo se alinee a la derecha y
				// que salga en rojo si es negativa.
				tcr2 = new TableCellRenderer();
				jtEstadistico.getColumn("V.Año").setCellRenderer(tcr2);
				jtEstadistico.getColumn("V.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("V.Año Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.Año").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.Año Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("B.%/V Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.Año").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("I.Año Ant.").setCellRenderer(tcr2);
				break;
		//Proveedores
		case 2: lZonaIzquierda.setText("Compras_________");
				lZonaCentro.setText("Rappels______________");
				lZonaDerecha.setText("Facturado___________");
				modeloTabla.addColumn("Mes");
				modeloTabla.addColumn("C.Año");
				modeloTabla.addColumn("C.%/A");
				modeloTabla.addColumn("C.Año Ant.");
				modeloTabla.addColumn("R.Año");
				modeloTabla.addColumn("R.%/V");
				modeloTabla.addColumn("R.%/A");
				modeloTabla.addColumn("R.Año Ant.");
				modeloTabla.addColumn("F.%/V");
				modeloTabla.addColumn("F.Año");
				modeloTabla.addColumn("F.%/A");
				modeloTabla.addColumn("F.Año Ant.");
				
				jtEstadistico = new JTable(modeloTabla);
				jtEstadistico.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
				

				// Establecemos el ancho
				jtEstadistico.getColumn("Mes").setMaxWidth(80);
				jtEstadistico.getColumn("C.Año").setMaxWidth(70);
				jtEstadistico.getColumn("C.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("C.Año Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("R.Año").setMaxWidth(70);
				jtEstadistico.getColumn("R.%/V").setMaxWidth(40);
				jtEstadistico.getColumn("R.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("R.Año Ant.").setMaxWidth(70);
				jtEstadistico.getColumn("F.%/V").setMaxWidth(40);
				jtEstadistico.getColumn("F.Año").setMaxWidth(70);
				jtEstadistico.getColumn("F.%/A").setMaxWidth(40);
				jtEstadistico.getColumn("F.Año Ant.").setMaxWidth(70);

				jtEstadistico.setRowHeight(25);
				
				// Hacemos que la comluna del saldo se alinee a la derecha y
				// que salga en rojo si es negativa.
				tcr2 = new TableCellRenderer();
				jtEstadistico.getColumn("C.Año").setCellRenderer(tcr2);
				jtEstadistico.getColumn("C.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("C.Año Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.Año").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("R.Año Ant.").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.%/V").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.Año").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.%/A").setCellRenderer(tcr2);
				jtEstadistico.getColumn("F.Año Ant.").setCellRenderer(tcr2);
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
		
		lAño = new JLabel();
		lAño.setBounds(10, 15, 60, 20);
		lAño.setFont(Apariencia.cambiaFuente());
		pantalla.add(lAño);
		
		lLiteralAño = new JLabel("Año");
		lLiteralAño.setBounds(280, 430, 40, 20);
		lLiteralAño.setFont(Apariencia.cambiaFuente());
		pantalla.add(lLiteralAño);
		
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
	
		lAño.setText(String.valueOf(año));
		double dAcumuladoFacturadoTotalAño = 0.0, 
		    dAcumuladoFacturadoMesAño = 0.0, 
		    dAcumuladoFacturadoTotalAñoAnterior = 0.0,
		    dAcumuladoFacturadoMesAñoAnterior = 0.0,
		    dAcumuladoComprasTotalAño = 0.0, 
		    dAcumuladoComprasMesAño = 0.0, 
		    dAcumuladoComprasTotalAñoAnterior = 0.0,
		    dAcumuladoComprasMesAñoAnterior = 0.0,
			dAcumuladoRappelTotalAño = 0.0, 
			dAcumuladoRappelMesAño = 0.0, 
			dAcumuladoRappelTotalAñoAnterior = 0.0,
			dAcumuladoRappelMesAñoAnterior = 0.0;
		
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
			
		
			
		// Cargamos los datos del año anterior
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + (año - 1);
					
		dAcumuladoFacturadoTotalAñoAnterior = 0.0;
	    dAcumuladoFacturadoMesAñoAnterior = 0.0;
	    dAcumuladoComprasTotalAñoAnterior = 0.0;
	    dAcumuladoComprasMesAñoAnterior = 0.0;
	    dAcumuladoRappelTotalAñoAnterior = 0.0;
		dAcumuladoRappelMesAñoAnterior = 0.0;
		
		numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){

			try {							
				EstadisticaContable estadistica = new EstadisticaContable();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					estadistica.read(rs);					
					
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.COMPRAS_AÑO_ANTERIOR.value);
						dAcumuladoComprasTotalAñoAnterior += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoComprasMesAñoAnterior += estadistica.getAcumulado(1);
					}
					if((int)estadistica.getAcumulado(2) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(2), estadistica.getMes() - 1, ColumnaProveedorAcreedor.RAPPELS_AÑO_ANTERIOR.value);
						dAcumuladoRappelTotalAñoAnterior += estadistica.getAcumulado(2);
						if(estadistica.getMes() <= mes)
							dAcumuladoRappelMesAñoAnterior += estadistica.getAcumulado(2);
					}
					if((int)estadistica.getAcumulado(0) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(0)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.FACTURADO_AÑO_ANTERIOR.value);
						dAcumuladoFacturadoTotalAñoAnterior += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesAñoAnterior += estadistica.getAcumulado(0);
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Cargamos los datos de este año
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + año;
							
		dAcumuladoFacturadoTotalAño = 0.0;
	    dAcumuladoFacturadoMesAño = 0.0;
	    dAcumuladoComprasTotalAño = 0.0;
	    dAcumuladoComprasMesAño = 0.0;
	    dAcumuladoRappelTotalAño = 0.0;
		dAcumuladoRappelMesAño = 0.0;
		
		numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){
			try {							
				EstadisticaContable estadistica = new EstadisticaContable();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					estadistica.read(rs);					
					
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.COMPRAS_AÑO.value);
						dAcumuladoComprasTotalAño += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoComprasMesAño += estadistica.getAcumulado(1);
					}
					if((int)estadistica.getAcumulado(2) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(2), estadistica.getMes() - 1, ColumnaProveedorAcreedor.RAPPELS_AÑO.value);
						dAcumuladoRappelTotalAño += estadistica.getAcumulado(2);
						if(estadistica.getMes() <= mes)
							dAcumuladoRappelMesAño += estadistica.getAcumulado(2);
					}
					if((int)estadistica.getAcumulado(0) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(0)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.FACTURADO_AÑO.value);
						dAcumuladoFacturadoTotalAño += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesAño += estadistica.getAcumulado(0);
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Ponemos los acumulados Totales de año
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasTotalAño), 12, ColumnaProveedorAcreedor.COMPRAS_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasTotalAñoAnterior), 12, ColumnaProveedorAcreedor.COMPRAS_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelTotalAño), 12, ColumnaProveedorAcreedor.RAPPELS_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelTotalAñoAnterior), 12, ColumnaProveedorAcreedor.RAPPELS_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoTotalAño), 12, ColumnaProveedorAcreedor.FACTURADO_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoTotalAñoAnterior), 12, ColumnaProveedorAcreedor.FACTURADO_AÑO_ANTERIOR.value);

		// Ponemos los acumulados Totales de mes
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasMesAño), 13, ColumnaProveedorAcreedor.COMPRAS_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoComprasMesAñoAnterior), 13, ColumnaProveedorAcreedor.COMPRAS_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelMesAño), 13, ColumnaProveedorAcreedor.RAPPELS_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelMesAñoAnterior), 13, ColumnaProveedorAcreedor.RAPPELS_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoMesAño), 13, ColumnaProveedorAcreedor.FACTURADO_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoFacturadoMesAñoAnterior), 13, ColumnaProveedorAcreedor.FACTURADO_AÑO_ANTERIOR.value);
		
		
		
		// Calculamos los % en Compras
		int porcent = 0, iAño = 0, iAñoAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_AÑO.value)).length() > 0)
				iAño = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_AÑO.value)).replaceAll("\\.", ""));
			else
				iAño = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_AÑO_ANTERIOR.value)).length() > 0)
				iAñoAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.COMPRAS_AÑO_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iAñoAnt = 0;
			
			if(iAño == 0 || iAñoAnt == 0)
				porcent = 0;
			else
				porcent = (iAño * 100) / iAñoAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaProveedorAcreedor.COMPRAS_PORCENTAJE_SOBRE_AÑO.value);					
		}
		

		// Calculamos los % en Rappels
		porcent = 0; iAño = 0; iAñoAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_AÑO.value)).length() > 0)
				iAño = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_AÑO.value)).replaceAll("\\.", ""));
			else
				iAño = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_AÑO_ANTERIOR.value)).length() > 0)
				iAñoAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.RAPPELS_AÑO_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iAñoAnt = 0;
			
			if(iAño == 0 || iAñoAnt == 0)
				porcent = 0;
			else
				porcent = (iAño * 100) / iAñoAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaProveedorAcreedor.RAPPELS_PORCENTAJE_SOBRE_AÑO.value);					
		}

		// Calculamos los % en Facturado
		porcent = 0; iAño = 0; iAñoAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_AÑO.value)).length() > 0)
				iAño = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_AÑO.value)).replaceAll("\\.", ""));
			else
				iAño = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_AÑO_ANTERIOR.value)).length() > 0)
				iAñoAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaProveedorAcreedor.FACTURADO_AÑO_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iAñoAnt = 0;
			
			if(iAño == 0 || iAñoAnt == 0)
				porcent = 0;
			else
				porcent = (iAño * 100) / iAñoAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaProveedorAcreedor.FACTURADO_PORCENTAJE_SOBRE_AÑO.value);					
		}

	}
	
	
	public void cargaDatosClientes(){
		borrarTabla();
						
		Object fila[] = {"", "", "",  "", "", "", "", "", "", "", "", "", ""};	
		
		int numeroRegistros = 0;
		int mes = fecha.getMes() + 1;
	
		lAño.setText(String.valueOf(año));
		double dAcumuladoVentasTotalAño = 0.0, 
		    dAcumuladoVentasMesAño = 0.0, 
		    dAcumuladoVentasTotalAñoAnterior = 0.0,
		    dAcumuladoVentasMesAñoAnterior = 0.0,
		    dAcumuladoBeneficioTotalAño = 0.0, 
		    dAcumuladoBeneficioMesAño = 0.0, 
		    dAcumuladoBeneficioTotalAñoAnterior = 0.0,
		    dAcumuladoBeneficioMesAñoAnterior = 0.0,
			dAcumuladoImpagadosTotalAño = 0.0, 
			dAcumuladoImpagadosMesAño = 0.0, 
			dAcumuladoImpagadosTotalAñoAnterior = 0.0,
			dAcumuladoImpagadosMesAñoAnterior = 0.0,
			dAcumuladoFacturadoTotalAño = 0.0, 
			dAcumuladoFacturadoMesAño = 0.0, 
			dAcumuladoFacturadoTotalAñoAnterior = 0.0,
			dAcumuladoFacturadoMesAñoAnterior = 0.0,
			dAcumuladoRappelTotalAño = 0.0, 
			dAcumuladoRappelMesAño = 0.0, 
			dAcumuladoRappelTotalAñoAnterior = 0.0,
			dAcumuladoRappelMesAñoAnterior = 0.0;
		int iAcumuladoNumeroImpagadosTotalAño = 0,
		    iAcumuladoNumeroImpagadosMesAño = 0;
		
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
			
		
			
		// Cargamos los datos del año anterior
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + (año - 1);
				
		dAcumuladoFacturadoTotalAñoAnterior = 0.0;
		dAcumuladoFacturadoMesAñoAnterior = 0.0;
		dAcumuladoVentasTotalAñoAnterior = 0.0;
	    dAcumuladoVentasMesAñoAnterior = 0.0;
	    /* No hay con que comparar estos datos, no mostramos del año anterior
	    dAcumuladoBeneficioTotalAñoAnterior = 0.0;
	    dAcumuladoBeneficioMesAñoAnterior = 0.0;
	    dAcumuladoImpagadosTotalAñoAnterior = 0.0;
		dAcumuladoImpagadosMesAñoAnterior = 0.0;
		
		dAcumuladoRappelTotalAñoAnterior = 0.0;
		dAcumuladoRappelMesAñoAnterior = 0.0;
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
						dAcumuladoFacturadoTotalAñoAnterior += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesAñoAnterior += estadistica.getAcumulado(0);
					}*/
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaClientes.VENTAS_AÑO_ANTERIOR.value);
						dAcumuladoVentasTotalAñoAnterior += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoVentasMesAñoAnterior += estadistica.getAcumulado(1);
					}
					
					if((int)estadistica.getAcumulado(4) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(4), estadistica.getMes() - 1, ColumnaClientes.BENEFICIO_AÑO_ANTERIOR.value);
						dAcumuladoBeneficioTotalAñoAnterior += estadistica.getAcumulado(4);
						if(estadistica.getMes() <= mes)
							dAcumuladoBeneficioMesAñoAnterior += estadistica.getAcumulado(4);
					}					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Cargamos los datos de este año
		strSql	= "SELECT * FROM CACEST WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		        " CACEST_FICHERO = " + extensionOtroFichero + " AND " +
		        " CACEST_CLAVE = '" + strCuenta + "' AND " +
		        " CACEST_ANY = " + año;
							
		dAcumuladoFacturadoTotalAño = 0.0;
	    dAcumuladoFacturadoMesAño = 0.0;
	    dAcumuladoVentasTotalAño = 0.0;
	    dAcumuladoVentasMesAño = 0.0;
	    dAcumuladoBeneficioTotalAño = 0.0; 
	    dAcumuladoBeneficioMesAño = 0.0;
	    iAcumuladoNumeroImpagadosTotalAño = 0;
	    iAcumuladoNumeroImpagadosMesAño = 0;	    
		dAcumuladoImpagadosTotalAño = 0.0;
		dAcumuladoImpagadosMesAño = 0.0;
		dAcumuladoRappelTotalAño = 0.0;
		dAcumuladoRappelMesAño = 0.0;
	    
		
		numeroRegistros = BaseDatos.countRows(strSql);
		if(numeroRegistros != 0){
			try {							
				EstadisticaContable estadistica = new EstadisticaContable();
				rs = m.query(strSql);				
								
				while(rs.next() || rs.isLast()){									
					
					estadistica.read(rs);					
					/* No mostramos el facturado en la tabla ???
					if((int)estadistica.getAcumulado(0) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(0)), estadistica.getMes() - 1, ColumnaProveedorAcreedor.FACTURADO_AÑO.value);
						dAcumuladoFacturadoTotalAño += estadistica.getAcumulado(0);
						if(estadistica.getMes() <= mes)
							dAcumuladoFacturadoMesAño += estadistica.getAcumulado(0);
					}*/
					if((int)estadistica.getAcumulado(1) != 0){
						modeloTabla.setValueAt(Cadena.formatoDobleEntero(estadistica.getAcumulado(1)), estadistica.getMes() - 1, ColumnaClientes.VENTAS_AÑO.value);
						dAcumuladoVentasTotalAño += estadistica.getAcumulado(1);
						if(estadistica.getMes() <= mes)
							dAcumuladoVentasMesAño += estadistica.getAcumulado(1);
					}
					if((int)estadistica.getAcumulado(2) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(2), estadistica.getMes() - 1, ColumnaClientes.IMPAGADOS_RAPPEL.value);
						dAcumuladoRappelTotalAño += estadistica.getAcumulado(2);
						if(estadistica.getMes() <= mes)
							dAcumuladoRappelMesAño += estadistica.getAcumulado(2);
					}
					if((int)estadistica.getAcumulado(3) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(3), estadistica.getMes() - 1, ColumnaClientes.IMPAGADOS_NUMERO.value);
						iAcumuladoNumeroImpagadosTotalAño += estadistica.getAcumulado(3);
						if(estadistica.getMes() <= mes)
							iAcumuladoNumeroImpagadosMesAño += estadistica.getAcumulado(3);
					}
					if((int)estadistica.getAcumulado(4) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(4), estadistica.getMes() - 1, ColumnaClientes.BENEFICIO_AÑO.value);
						dAcumuladoBeneficioTotalAño += estadistica.getAcumulado(4);
						if(estadistica.getMes() <= mes)
							dAcumuladoBeneficioMesAño += estadistica.getAcumulado(4);
					}
					if((int)estadistica.getAcumulado(5) != 0){
						modeloTabla.setValueAt((int)estadistica.getAcumulado(5), estadistica.getMes() - 1, ColumnaClientes.IMPAGADOS_IMPORTE.value);
						dAcumuladoImpagadosTotalAño += estadistica.getAcumulado(5);
						if(estadistica.getMes() <= mes)
							dAcumuladoImpagadosMesAño += estadistica.getAcumulado(5);
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de EstadisticaContable<BR>Consulta Acumulados Contables");				
			}			
		}
		
		// Ponemos los acumulados Totales de año
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasTotalAño), 12, ColumnaClientes.VENTAS_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasTotalAñoAnterior), 12, ColumnaClientes.VENTAS_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioTotalAño), 12, ColumnaClientes.BENEFICIO_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioTotalAñoAnterior), 12, ColumnaClientes.BENEFICIO_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(iAcumuladoNumeroImpagadosTotalAño), 12, ColumnaClientes.IMPAGADOS_NUMERO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoImpagadosTotalAño), 12, ColumnaClientes.IMPAGADOS_IMPORTE.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelTotalAño), 12, ColumnaClientes.IMPAGADOS_RAPPEL.value);

		// Ponemos los acumulados Totales de mes
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasMesAño), 13, ColumnaClientes.VENTAS_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoVentasMesAñoAnterior), 13, ColumnaClientes.VENTAS_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioMesAño), 13, ColumnaClientes.BENEFICIO_AÑO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoBeneficioMesAñoAnterior), 13, ColumnaClientes.BENEFICIO_AÑO_ANTERIOR.value);
		
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(iAcumuladoNumeroImpagadosMesAño), 13, ColumnaClientes.IMPAGADOS_NUMERO.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoImpagadosMesAño), 13, ColumnaClientes.IMPAGADOS_IMPORTE.value);
		modeloTabla.setValueAt(Cadena.formatoDobleEntero(dAcumuladoRappelMesAño), 13, ColumnaClientes.IMPAGADOS_RAPPEL.value);
		
		// Calculamos los % en Ventas
		int porcent = 0, iAño = 0, iAñoAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO.value)).length() > 0)
				iAño = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO.value)).replaceAll("\\.", ""));
			else
				iAño = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO_ANTERIOR.value)).length() > 0)
				iAñoAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iAñoAnt = 0;
			
			if(iAño == 0 || iAñoAnt == 0)
				porcent = 0;
			else
				porcent = (iAño * 100) / iAñoAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaClientes.VENTAS_PORCENTAJE_SOBRE_AÑO.value);					
		}
		

		// Calculamos los % en Beneficio
		porcent = 0; iAño = 0; iAñoAnt = 0;
		for(int i = 0; i < 14; i++){
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO.value)).length() > 0)
				iAño = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO.value)).replaceAll("\\.", ""));
			else
				iAño = 0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO_ANTERIOR.value)).length() > 0)
				iAñoAnt = Integer.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				iAñoAnt = 0;
			
			if(iAño == 0 || iAñoAnt == 0)
				porcent = 0;
			else
				porcent = (iAño * 100) / iAñoAnt;
			
			if (porcent > 999)
				porcent = 999;
			
			if(porcent != 0)
				modeloTabla.setValueAt(porcent, i, ColumnaClientes.BENEFICIO_PORCENTAJE_SOBRE_AÑO.value);			
			
			// Calculo porcentaje beneficio
			double dPorcent = 0.0;
			double dVentas = 0.0;
			double dBeneficio = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO.value)).length() > 0)
				dVentas = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO.value)).replaceAll("\\.", ""));
			else
				dVentas = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO.value)).length() > 0)
				dBeneficio = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO.value)).replaceAll("\\.", ""));
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
			
			// Calculo porcentaje beneficio AÑO ANTERIOR
			dPorcent = 0.0;
			dVentas = 0.0;
			dBeneficio = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO_ANTERIOR.value)).length() > 0)
				dVentas = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				dVentas = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO_ANTERIOR.value)).length() > 0)
				dBeneficio = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.BENEFICIO_AÑO_ANTERIOR.value)).replaceAll("\\.", ""));
			else
				dBeneficio = 0.0;
			
			if(dVentas == 0.0 || dBeneficio == 0.0)
				dPorcent = 0.0;
			else{
				dPorcent = (dBeneficio * 100.0) / dVentas;
				if(dPorcent > 999.9)
					dPorcent = 999.0;
				
				modeloTabla.setValueAt(Cadena.formatoConComaDecimal(dPorcent), i, ColumnaClientes.BENEFICIO_PORCENTAJE_SOBRE_VENTAS_AÑO_ANTERIOR.value);
			}
			
			// Calculo porcentaje Impagados sobre Ventas
			dPorcent = 0.0;
			dVentas = 0.0;
			double dImpagados = 0.0;
			
			if(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO.value)).length() > 0)
				dVentas = Double.valueOf(String.valueOf(modeloTabla.getValueAt(i, ColumnaClientes.VENTAS_AÑO.value)).replaceAll("\\.", ""));
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
			año--;
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
			año++;
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
