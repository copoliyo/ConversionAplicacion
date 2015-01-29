package indices;

import general.DatosComunes;
import general.MysqlConnect;

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
import tablas.Nota;
import util.Apariencia;
import util.BaseDatos;
import util.EscapeDialog;
import util.JTextFieldFecha;
import util.JTextFieldNumeroFijo;

public class IndiceAcumuladosContables {
	
	public enum Columna {
		AÑO(0), MES(1), DEBE(2), HABER(3), SALDO(4), DEBE_DOUBLE(5), HABER_DOUBLE(6), SALDO_DOUBLE(7);  //; is optional

		private int value;

		private Columna(int value) {
			this.value = value;
		}
	}
	
	JDialog pantalla;
	JLabel lFecha, lDesCripcionCuenta;		
	JTextFieldFecha jtfeFecha;
	JTable jtDebeHaber;
	JScrollPane spDebeHaber;
	DefaultTableCellRenderer tcr;
	TableCellRenderer tcr2;
	
	ResultSet rs = null;
	MysqlConnect m = null;
	
	// Consulta SQL
	String strSql = "";
	String strCuenta = "";
	String strDescripcionCuenta = "";

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Para que no podamos editar los datos de la tabla
			return false;
		}
	};
	
	public IndiceAcumuladosContables(String strCuenta, String strDescripcionCuenta){
		this.strCuenta = strCuenta.trim();
		this.strDescripcionCuenta = strDescripcionCuenta;
		m = MysqlConnect.getDbCon();
		
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Acumulados Contables");
		pantalla.setModal(true);
		pantalla.setSize(450, 460);
		//pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		 
		pantalla.setDefaultCloseOperation(0);
		pantalla.setLayout(null);
		
		// La tabla contendra estas columnas
		modeloTabla.addColumn("Año");
		modeloTabla.addColumn("Mes");
		modeloTabla.addColumn("Debe");
		modeloTabla.addColumn("Haber");
		modeloTabla.addColumn("Saldo");
		modeloTabla.addColumn("DebeDouble");
		modeloTabla.addColumn("HaberDouble");
		modeloTabla.addColumn("SaldoDouble");

		jtDebeHaber = new JTable(modeloTabla);
		jtDebeHaber.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
		
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtDebeHaber.getColumn("Año").setPreferredWidth(60);
		jtDebeHaber.getColumn("Mes").setPreferredWidth(30);
		jtDebeHaber.getColumn("Debe").setPreferredWidth(100);
		jtDebeHaber.getColumn("Haber").setPreferredWidth(100);
		jtDebeHaber.getColumn("Saldo").setPreferredWidth(100);
		jtDebeHaber.getColumn("DebeDouble").setMinWidth(0);
		jtDebeHaber.getColumn("DebeDouble").setMaxWidth(0);
		jtDebeHaber.getColumn("DebeDouble").setWidth(0);
		jtDebeHaber.getColumn("HaberDouble").setMinWidth(0);
		jtDebeHaber.getColumn("HaberDouble").setMaxWidth(0);
		jtDebeHaber.getColumn("HaberDouble").setWidth(0);
		jtDebeHaber.getColumn("SaldoDouble").setMinWidth(0);
		jtDebeHaber.getColumn("SaldoDouble").setMaxWidth(0);
		jtDebeHaber.getColumn("SaldoDouble").setWidth(0);
		jtDebeHaber.setRowHeight(20);
		
		
		// Hacemos que las columnas se alineen a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		jtDebeHaber.getColumn("Año").setCellRenderer(tcr);
		jtDebeHaber.getColumn("Mes").setCellRenderer(tcr);
		jtDebeHaber.getColumn("Debe").setCellRenderer(tcr);
		jtDebeHaber.getColumn("Haber").setCellRenderer(tcr);
		
		// Hacemos que la comluna del saldo se alinee a la derecha y
		// que salga en rojo si es negativa.
		tcr2 = new TableCellRenderer();
		jtDebeHaber.getColumn("Saldo").setCellRenderer(tcr2);
		
		// Creamos un JscrollPane y le agregamos la JTable
		spDebeHaber = new JScrollPane(jtDebeHaber);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		spDebeHaber.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		spDebeHaber.setBounds(10, 50, 420, 360);	
		spDebeHaber.setFont(Apariencia.cambiaFuente());
		pantalla.add(spDebeHaber);
		
		lFecha = new JLabel("Fecha");
		lFecha.setBounds(10, 15, 60, 20);
		lFecha.setFont(Apariencia.cambiaFuente());
		pantalla.add(lFecha);
		
		jtfeFecha = new JTextFieldFecha();
		jtfeFecha.setBounds(70, 15, 90, 25);
		jtfeFecha.setText("0");
		jtfeFecha.setFont(Apariencia.cambiaFuente());
		jtfeFecha.addActionListener(new FechaListener());
		pantalla.add(jtfeFecha);
		
		lDesCripcionCuenta = new JLabel();
		lDesCripcionCuenta.setBounds(170, 15, 340, 20);
		lDesCripcionCuenta.setFont(Apariencia.cambiaFuente(Font.BOLD, 12));
		lDesCripcionCuenta.setText(strDescripcionCuenta);
		pantalla.add(lDesCripcionCuenta);			
		
		cargaDebeHaber();
		
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
	
	class FechaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			borrarTabla();
			cargaDebeHaber();		
		}		
	}
	
	private void borrarTabla(){
		// Vaciamos la tabla
		int a = modeloTabla.getRowCount() - 1;

		for (int i = a; i >= 0; i--)
			modeloTabla.removeRow(i);
	}
	
	private void cargaDebeHaber(){
		int desdeAnio = 0;
		int desdeMes = 0;
		int desdeFecha = jtfeFecha.getFechaAAAAMMDD();
		String strDesdeFecha = jtfeFecha.getStrFechaAAAAMMDD();
		
		//Apariencia.mensajeInformativo(4, strDesdeFecha);
		if(desdeFecha != 0){
			desdeAnio = Integer.valueOf(strDesdeFecha.substring(0, 4));
			desdeMes  = Integer.valueOf(strDesdeFecha.substring(4, 6));
		}
		
		String claveCuentaAnioMes = String.format("%1$-9s", strCuenta) + 
		                            String.format("%04d", desdeAnio) + 
		                            String.format("%02d", desdeMes);
		String hastaClaveCuentaAnioMes = String.format("%1$-9s", strCuenta) + "9999" + "99";        								

		String strSql = "SELECT * FROM DEBHAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
		                "DEBHAB_CTA_ANYMES >= '" + claveCuentaAnioMes + "' AND " +
		                "DEBHAB_CTA_ANYMES < '" + hastaClaveCuentaAnioMes + "'";
		
		Object fila[] = {"", "", "", "", "", "", "", ""};
		
		String strDecimalFormateado = "";
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator(',');
		DecimalFormat myFormatter = new DecimalFormat("###,###,##0.00", simbolos);
		
		m = MysqlConnect.getDbCon();

		int mes = 0;
		
		if(BaseDatos.countRows(strSql) != 0){
			try {
				DebeHaber debeHaber = new DebeHaber();
				rs = m.query(strSql);				
				
				while(rs.next() || rs.isLast()){									
					
					debeHaber.read(rs);
					
					fila[Columna.AÑO.value] = debeHaber.getCuentaAñoMes().substring(9, 13);
					mes = Integer.valueOf(debeHaber.getCuentaAñoMes().substring(13, 15));
					
					fila[Columna.MES.value] = String.valueOf(mes);
					
					if(debeHaber.getDebe() != 0.0)
						strDecimalFormateado = myFormatter.format(debeHaber.getDebe());
					else
						strDecimalFormateado = "";
					fila[Columna.DEBE.value] = strDecimalFormateado;		
					
					if(debeHaber.getHaber() != 0.0)
						strDecimalFormateado = myFormatter.format(debeHaber.getHaber());
					else
						strDecimalFormateado = "";					
					fila[Columna.HABER.value] = strDecimalFormateado;
					fila[Columna.SALDO.value] = "Saldo";
					
					fila[Columna.DEBE_DOUBLE.value] = String.valueOf(debeHaber.getDebe());
					fila[Columna.HABER_DOUBLE.value] = String.valueOf(debeHaber.getHaber());
					fila[Columna.SALDO_DOUBLE.value] = "0.0";
					
					modeloTabla.addRow(fila);					
								
				}

				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de DebeHaber<BR>Consulta Acumulados Contables");				
			}

			double saldo = 0.0;
			
			Cuenta cuenta = new Cuenta();
			saldo = cuenta.getSaldoCuenta(strCuenta, DatosComunes.centroCont);
			
			// Una vez conocemos el saldo a HOY, tenemos que ir recalculando hacia atrás.
			// Saldo = SaldoAnterior + Debe - Haber
			double debe = 0.0;
			double haber = 0.0;
			int numeroDeFilas = modeloTabla.getRowCount() - 1;
			if(saldo == 0.0)
				modeloTabla.setValueAt("", numeroDeFilas, 4);
			else
				modeloTabla.setValueAt(myFormatter.format(saldo), numeroDeFilas, 4);
			
			for(int i = numeroDeFilas - 1; i >= 0; i--){
				debe = Double.valueOf((String)modeloTabla.getValueAt(i + 1, 5));
				haber = Double.valueOf((String)modeloTabla.getValueAt(i + 1, 6));
				saldo = saldo - debe + haber;
				modeloTabla.setValueAt(String.valueOf(saldo), i, 7);
				if(saldo <= 0.01 && saldo >= -0.01)
					modeloTabla.setValueAt("", i, 4);
				else
					modeloTabla.setValueAt(myFormatter.format(saldo), i, 4);							
			}
			// Vamos al final de la trabla
			jtDebeHaber.clearSelection();
			jtDebeHaber.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
			Rectangle rect = jtDebeHaber.getCellRect(numeroDeFilas, 0, true);
			jtDebeHaber.scrollRectToVisible(rect);
			jtDebeHaber.clearSelection();
			jtDebeHaber.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
			DefaultTableModel modelo = (DefaultTableModel)jtDebeHaber.getModel();
			   modelo.fireTableDataChanged();
		}
	}
        
        public void recargaAcumulados(String strCuenta, String strDescripcionCuenta){
            this.strCuenta = strCuenta.trim();
            this.strDescripcionCuenta = strDescripcionCuenta;
            borrarTabla();
            cargaDebeHaber();
            pantalla.setVisible(true);
        }
}
