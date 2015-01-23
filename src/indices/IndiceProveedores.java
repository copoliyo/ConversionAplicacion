package indices;

import general.DatosComunes;
import general.MysqlConnect;
import indices.IndiceCuentas.BuscarListener;
import indices.IndiceCuentas.CheckListener;
import indices.IndiceCuentas.EscapeListener;
import indices.IndiceCuentas.SalirListener;
import indices.IndiceCuentas.TablaListener;

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
import java.sql.ResultSet;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import tablas.Cuenta;
import tablas.Proveedor;
import util.Apariencia;
import util.BaseDatos;
import util.MiRender;

public class IndiceProveedores {
	
	JDialog pantalla;
	JCheckBox cInactivos;
	JButton bSalir;
	JTable jtProveedores;
	JTextField tfBuscar;
	JLabel lFiltro;

	DefaultTableCellRenderer tcr;
	
	boolean inactivos = false;
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	// Número(String) de cuenta que devolveremos
	String cuenta = null;
	// Cadena a buscar
	String strBuscar = "";
	// Consulta SQL
	String strSql = "";
	// Numero de columna por el que vamos a ordenar
	int columnaOrden = 0;

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Para que no podamos editar los datos de la tabla
			return false;
		}
	};
	
	public IndiceProveedores(){
		m = MysqlConnect.getDbCon();

		pantalla = new JDialog();
		pantalla.setModal(true);
		pantalla.setSize(1000, 550);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);				
		
		cInactivos = new JCheckBox("Inactivos");
		cInactivos.setBounds(20, 5, 100, 20);
		cInactivos.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		cInactivos.addActionListener(new CheckListener());
		
		lFiltro = new JLabel("Filtro: ");
		lFiltro.setBounds(150, 5, 80, 20);
		lFiltro.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(210, 5, 120, 20);
		tfBuscar.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));		
		tfBuscar.addKeyListener(new EscapeListener());
		tfBuscar.addKeyListener(new BuscarListener());			
		
		// Ponemos el botón de salir
		bSalir = new JButton();
		bSalir.setBounds(20, 480, 35, 35);
		bSalir.addActionListener(new SalirListener());
		try {
			Image img = ImageIO.read(getClass().getResource("/imagenes/SALIR.gif"));
			bSalir.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		// Ponemos la tabla con su modelo
		// Se crea la Tabla a partir de un modelo de tabla que
		// definimos nosotros, luego le añadiremos datos.

		//MiRender modeloDerecha = new MiRender();
		//modeloDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// La tabla solo contendra tres columnas
		modeloTabla.addColumn("Código");
		modeloTabla.addColumn("Título");
		modeloTabla.addColumn("Población");
		modeloTabla.addColumn("Teléfono");
		modeloTabla.addColumn("Contacto");
		modeloTabla.addColumn("Nif");
		
		
		final JTable jtProveedores = new JTable(modeloTabla);
		
		jtProveedores.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
				
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtProveedores.getColumn("Código").setPreferredWidth(120);
		jtProveedores.getColumn("Título").setPreferredWidth(400);
		jtProveedores.getColumn("Población").setPreferredWidth(150);
		jtProveedores.getColumn("Teléfono").setPreferredWidth(150);
		jtProveedores.getColumn("Contacto").setPreferredWidth(150);
		jtProveedores.getColumn("Nif").setPreferredWidth(150);

		// Listener para que al pulsar ESCAPE se salga del programa
		jtProveedores.addKeyListener(new EscapeListener());
		// Cuando pinchamos en la cabecera, se obtiene el número de la columna por 
		// la que vamos a ordenar.
		jtProveedores.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {		    	
				strBuscar = "";
		        columnaOrden = jtProveedores.columnAtPoint(e.getPoint());
		        //String name = jtProveedores.getColumnName(columnaOrden);
		        //System.out.println("Column index selected " + columnaOrden + " " + name);
		        limpiarTabla();
				rellenarTabla();
		    }
		});	
		
		// Añadimos un lístener y un HeaderTableRender para poner el nombre de la columna
		// en NEGRITA y así saber cual es la columna por la que se ordena.
		JTableHeader cuentasHeader = jtProveedores.getTableHeader();
        final Font boldFont = cuentasHeader.getFont().deriveFont(Font.BOLD);
        final TableCellRenderer headerRenderer = cuentasHeader.getDefaultRenderer();
        cuentasHeader.setDefaultRenderer( new TableCellRenderer() {
             public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
                  Component comp = headerRenderer.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
                  if ( column == columnaOrden )
                       comp.setFont( boldFont );
                  // tfBuscar.setText("");
                  return comp;
             }
        });
		
		jtProveedores.setPreferredScrollableViewportSize(new Dimension(
				980, 425));

		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(jtProveedores);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		// scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		scrollPane.setBounds(10, 30, 950, 425);
		// Necesitamos saber si hacemos doble click en una fila
		jtProveedores.addMouseListener(new TablaListener());

		pantalla.add(scrollPane);
		
		pantalla.add(cInactivos);
		pantalla.add(lFiltro);
		pantalla.add(tfBuscar);
		pantalla.add(bSalir);

		
		limpiarTabla();
		rellenarTabla();
		// Ponemos el foco en el campo de BUSCAR nada mas mostrar el indice.
		pantalla.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        tfBuscar.requestFocus();
		    }
		});
		
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
		
		Object fila[] = {"", "", "", "", "", ""};
		
		if (m.conn == null)
			getCuenta();
		
		strSql = "";
		
		strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' ";
		
		if (inactivos == false)
			strSql += " AND PROVAC_ACTIVO = 1 ";
		
		strBuscar = tfBuscar.getText(); 
		
		switch(columnaOrden){
		    case 0 : if(strBuscar.length() > 0)
		    	        strSql += "AND PROVAC_PROVACRE LIKE '" + strBuscar + "%' ";		             
		             strSql += "ORDER BY PROVAC_PROVACRE ";	 
		    	     break;
		    case 1 : if(strBuscar.length() > 0)
		    	        strSql += "AND PROVAC_APELRAZON LIKE '%" + strBuscar + "%' ";
		             strSql += "ORDER BY PROVAC_APELRAZON ";
		             break;
		    case 2 : if(strBuscar.length() > 0)
    	                strSql += "AND PROVAC_POB LIKE '%" + strBuscar + "%' ";
		    	     strSql += "ORDER BY PROVAC_POB";
		             break;
		    case 3 : if(strBuscar.length() > 0)
                        strSql += "AND PROVAC_TLFNO LIKE '%" + strBuscar + "%' ";
    	             strSql += "ORDER BY PROVAC_TLFNO";
                     break;
		    case 4 : if(strBuscar.length() > 0)
                        strSql += "AND PROVAC_CONTACTO LIKE '%" + strBuscar + "%' ";
    	             strSql += "ORDER BY PROVAC_CONTACTO";
                     break;
		    case 5 : if(strBuscar.length() > 0)
                        strSql += "AND PROVAC_NIF LIKE '%" + strBuscar + "%' ";
    	             strSql += "ORDER BY PROVAC_NIF";
                     break;
		}
		
		numeroDeFilas = BaseDatos.countRows(strSql);
		if(numeroDeFilas > 0){
			try {
				limpiarTabla();
				Proveedor proveedor = new Proveedor(); 
				rs = m.query(strSql);
				
				// Recorremos el recodSet para ir rellenando la tabla de marcas
				while (rs.next() == true) {
					proveedor.read(rs);
					
					fila[0] = proveedor.getProveedor();
					if(proveedor.getActivo() == 0)
						fila[1] = "n* " + proveedor.getApellidosRazonSocial();
					else
						fila[1] = proveedor.getApellidosRazonSocial();
					fila[2] = proveedor.getPoblacion();
					fila[3] = proveedor.getTelefono();
					fila[4] = proveedor.getContacto();
					fila[5] = proveedor.getNif();					
					
					modeloTabla.addRow(fila);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class CheckListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// CAmbiamos entre activos e inactivos
			if(arg0.getSource() == cInactivos){
				if(cInactivos.isSelected())
					inactivos = true;
				else
					inactivos = false;
			}
			
			limpiarTabla();
			rellenarTabla();
		}

	}
	
	class SalirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Si pinchamos en el botón 'Salir', no pasamos cuenta y 'tiramos' la
			// pantalla
			cuenta = null;
			pantalla.setVisible(false);
		}
	}

	class BuscarListener implements KeyListener {


		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			strBuscar = tfBuscar.getText();
			limpiarTabla();
			rellenarTabla();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class EscapeListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			int id = arg0.getKeyCode();
			
	        if (id == KeyEvent.VK_ESCAPE){
	        	cuenta = null;
	        	getCuenta();
	        }
	        	
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	public String getCuenta(){
		pantalla.setVisible(false);
		return cuenta;
	}
	
	class TablaListener implements ActionListener, MouseListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pantalla.setVisible(false);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// Si hacemos doble click fijamos el valor de la marca y salimos
			if (arg0.getClickCount() == 2) {
				JTable target = (JTable) arg0.getSource();
				int row = target.getSelectedRow();
				cuenta = (String) target.getValueAt(row, 0);
				pantalla.setVisible(false);
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
        
        public void setVisible(boolean visible){
            pantalla.setVisible(visible);
        }
}

