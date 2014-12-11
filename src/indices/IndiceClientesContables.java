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

import tablas.ClienteContable;
import tablas.Cuenta;
import tablas.Proveedor;
import util.Apariencia;
import util.BaseDatos;
import util.MiRender;


public class IndiceClientesContables {

	JDialog pantalla;
	JCheckBox cInactivos;
	JButton bSalir;
	JTable jtClientesContables;
	JTextField tfBuscar;
	JLabel lFiltro;

	DefaultTableCellRenderer tcr;
	
	boolean inactivos = false;
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	// Número(String) de cuenta que devolveremos
	String cuenta = "";
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
	
	public IndiceClientesContables(){
		m = MysqlConnect.getDbCon();
        
		pantalla = new JDialog();
		pantalla.setModal(true);
		pantalla.setSize(1000, 550);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);				
		
		cInactivos = new JCheckBox("Inactivos");
		cInactivos.setBounds(20, 5, 120, 20);
		cInactivos.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		cInactivos.addActionListener(new CheckListener());
		
		lFiltro = new JLabel("Filtro: ");
		lFiltro.setBounds(170, 5, 80, 20);
		lFiltro.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(230, 5, 120, 20);
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
		modeloTabla.addColumn("Nombre Comercial");
		modeloTabla.addColumn("Teléfono");
		modeloTabla.addColumn("Población");
		modeloTabla.addColumn("Dirección");
		modeloTabla.addColumn("Contacto");
		modeloTabla.addColumn("Nif");
				
		final JTable jtClientesContables = new JTable(modeloTabla);
		
		jtClientesContables.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		jtClientesContables.setAutoscrolls(true);
				
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		jtClientesContables.getColumn("Código").setPreferredWidth(120);
		jtClientesContables.getColumn("Título").setPreferredWidth(350);
		jtClientesContables.getColumn("Nombre Comercial").setPreferredWidth(130);
		jtClientesContables.getColumn("Teléfono").setPreferredWidth(150);
		jtClientesContables.getColumn("Población").setPreferredWidth(150);
		jtClientesContables.getColumn("Dirección").setPreferredWidth(180);
		jtClientesContables.getColumn("Contacto").setPreferredWidth(150);
		jtClientesContables.getColumn("Nif").setPreferredWidth(150);
		// Importantísimo para la barra de desplazamiento horizontal
		jtClientesContables.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtClientesContables.doLayout();
		// Listener para que al pulsar ESCAPE se salga del programa
		jtClientesContables.addKeyListener(new EscapeListener());
		// Cuando pinchamos en la cabecera, se obtiene el número de la columna por 
		// la que vamos a ordenar.
		jtClientesContables.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {		    	
				strBuscar = "";
		        columnaOrden = jtClientesContables.columnAtPoint(e.getPoint());
		        //String name = jtClientesContables.getColumnName(columnaOrden);
		        //System.out.println("Column index selected " + columnaOrden + " " + name);
		        limpiarTabla();
				rellenarTabla();
		    }
		});	
		
		// Añadimos un lístener y un HeaderTableRender para poner el nombre de la columna
		// en NEGRITA y así saber cual es la columna por la que se ordena.
		JTableHeader cuentasHeader = jtClientesContables.getTableHeader();
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
		
		jtClientesContables.setPreferredScrollableViewportSize(new Dimension(
				1180, 425));

		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(jtClientesContables);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		// scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor		
		scrollPane.setBounds(10, 30, 950, 425);
		// Necesitamos saber si hacemos doble click en una fila
		jtClientesContables.addMouseListener(new TablaListener());

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
		
		Object fila[] = {"", "", "", "", "", "", "", ""};
		
		if (m.conn == null)
			getCuenta();
		
		strSql = "";
		
		strSql = "SELECT * FROM CLITES WHERE EMPRESA = '" + 
			      DatosComunes.eEmpresa + 
			      "' AND CLITES_CENTRO = " + 
			      DatosComunes.centroCont + 
			      " ";
		
		if (inactivos == false)
			strSql += " AND CLITES_ACTIVO = 1 ";
		
		strBuscar = tfBuscar.getText(); 
		
		switch(columnaOrden){
		    case 0 : if(strBuscar.length() > 0)
		    	        strSql += "AND CLITES_CLIENTE LIKE '" + strBuscar + "%' ";		             
		             strSql += "ORDER BY CLITES_CLIENTE ";	 
		    	     break;
		    case 1 : if(strBuscar.length() > 0)
		    	        strSql += "AND CLITES_APELRAZON LIKE '%" + strBuscar + "%' ";
		             strSql += "ORDER BY CLITES_APELRAZON ";
		             break;
		    case 2 : if(strBuscar.length() > 0)
    	                strSql += "AND CLITES_2DO_NOMBR LIKE '%" + strBuscar + "%' ";
		    	     strSql += "ORDER BY CLITES_2DO_NOMBR";
		             break;
		    case 3 : if(strBuscar.length() > 0)
                        strSql += "AND CLITES_TLFNO LIKE '%" + strBuscar + "%' ";
    	             strSql += "ORDER BY CLITES_TLFNO";
                     break;
		    case 4 : if(strBuscar.length() > 0)
                        strSql += "AND CLITES_POB LIKE '%" + strBuscar + "%' ";
    	             strSql += "ORDER BY CLITES_POB";
                     break;
		    case 5 : if(strBuscar.length() > 0)
                        strSql += "AND CLITES_DIR LIKE '%" + strBuscar + "%' ";
    	             strSql += "ORDER BY CLITES_DIR";
                     break;
		    case 6 : if(strBuscar.length() > 0)
                     	strSql += "AND CLITES_CONTACTO LIKE '%" + strBuscar + "%' ";
                     strSql += "ORDER BY CLITES_CONTACTO";
                     break;
		    case 7 : if(strBuscar.length() > 0)
                     	strSql += "AND CLITES_NIF LIKE '%" + strBuscar + "%' ";
                     strSql += "ORDER BY CLITES_NIF";
                     break;
		}
		
		numeroDeFilas = BaseDatos.countRows(strSql);
		if(numeroDeFilas > 0){
			try {
				limpiarTabla();
				ClienteContable cliente = new ClienteContable(); 
				rs = m.query(strSql);
				
				// Recorremos el recodSet para ir rellenando la tabla de marcas
				while (rs.next() == true) {
					cliente.read(rs);
					
					fila[0] = String.format("%07d", cliente.getCliente());
					if(cliente.getActivo() == 0)
						fila[1] = "n* " + cliente.getApellidosRazonSocial() + ", " + cliente.getNombre();
					else
						fila[1] = cliente.getApellidosRazonSocial() + ", " + cliente.getNombre();
					fila[2] = cliente.getSegundoNombre();
					fila[3] = cliente.getTelefono();
					fila[4] = cliente.getPoblacion();
					fila[5] = cliente.getDireccion();
					fila[6] = cliente.getContacto();
					fila[7] = cliente.getNif();
					
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
			pantalla.dispose();
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
	       // 	getCuenta();
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
		pantalla.dispose();
		return cuenta;
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
				cuenta = (String) "43" + target.getValueAt(row, 0);
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
}


