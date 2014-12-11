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


import tablas.Banco;
import tablas.IndiceBancos;
import util.BaseDatos;
import util.CuentaBancaria;
import util.CuentaBancaria;

//Usaremos la clase Banco para poder devolver el código del Banco

public class IndiceCodigosBanco {
	
	JDialog pantalla;
	JButton bSalir;
	JTable Bancos;
	JTextField tfBuscar;
	JLabel lFiltro;

	DefaultTableCellRenderer tcr;
	
	boolean inactivos = false;

	Banco bancoDevuelto = new Banco();
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	// Número(String) de banco que devolveremos
	String banco = "";
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

	public IndiceCodigosBanco(){
		m = MysqlConnect.getDbCon();

		pantalla = new JDialog();
		pantalla.setModal(true);
		pantalla.setSize(470, 550);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);				
		
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
		modeloTabla.addColumn("Banco");
		modeloTabla.addColumn("Nombre");
		
		final JTable Bancos = new JTable(modeloTabla);
		
		Bancos.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
				
		TableColumn columna = new TableColumn();
		// Establecemos el ancho
		Bancos.getColumn("Banco").setPreferredWidth(40);
		Bancos.getColumn("Nombre").setPreferredWidth(250);		

		// Hacemos que las columnas numero 0 y 1 se alineen a la DERECHA
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		Bancos.getColumnModel().getColumn(0).setCellRenderer(tcr);
		// Listener para que al pulsar ESCAPE se salga del programa
		Bancos.addKeyListener(new EscapeListener());
		// Cuando pinchamos en la cabecera, se obtiene el número de la columna por 
		// la que vamos a ordenar.
		Bancos.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {		    	
				strBuscar = "";
		        columnaOrden = Bancos.columnAtPoint(e.getPoint());
		        //String name = Bancos.getColumnName(columnaOrden);
		        //System.out.println("Column index selected " + columnaOrden + " " + name);
		        limpiarTabla();
				rellenarTabla();
		    }
		});	
		
		// Añadimos un lístener y un HeaderTableRender para poner el nombre de la columna
		// en NEGRITA y así saber cual es la columna por la que se ordena.
		JTableHeader bancosHeader = Bancos.getTableHeader();
        final Font boldFont = bancosHeader.getFont().deriveFont(Font.BOLD);
        final TableCellRenderer headerRenderer = bancosHeader.getDefaultRenderer();
        bancosHeader.setDefaultRenderer( new TableCellRenderer() {
             public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
                  Component comp = headerRenderer.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
                  if ( column == columnaOrden )
                       comp.setFont( boldFont );
                  // tfBuscar.setText("");
                  return comp;
             }
        });
		
		Bancos.setPreferredScrollableViewportSize(new Dimension(
				380, 425));

		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(Bancos);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
		// scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Agregamos el JScrollPane al contenedor
		scrollPane.setBounds(10, 30, 420, 425);
		// Necesitamos saber si hacemos doble click en una fila
		Bancos.addMouseListener(new TablaListener());

		pantalla.add(scrollPane);
		
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
		
		Object fila[] = {"", "", ""};
		
		if (m.conn == null)
			getBanco();
		
		strSql = "";
		
		strSql = "SELECT * FROM BANCOS WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' ";
		
		strSql += " AND BANCOS_CENTRO = " + DatosComunes.centroCont;
				
		strBuscar = tfBuscar.getText(); 
		
		switch(columnaOrden){
		    case 0 : if(strBuscar.length() > 0)
		    	        strSql += " AND BANCOS_BANCO LIKE '" + strBuscar + "%' ";		             
		             strSql += " ORDER BY BANCOS_BANCO ";	 
		    	     break;
		    case 1 : if(strBuscar.length() > 0);		    	     
		             
		             break;		    
		}
		
		numeroDeFilas = BaseDatos.countRows(strSql);
		if(numeroDeFilas > 0){
			try {
				limpiarTabla();
				Banco banco = new Banco(); 
				rs = m.query(strSql);
				
				// Recorremos el recodSet para ir rellenando la tabla de marcas
				while (rs.next() == true) {
					banco.read(rs);
					
					fila[0] = banco.getBanco();
					
					// Tenemos que buscar el nombre del banco en la tabla de indice de bancos
					String strSqlNombreBanco = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa +"' ";
					strSqlNombreBanco += " AND BCOIND_BANCO = " + banco.getBanco();
				
					IndiceBancos ib = new IndiceBancos();
					ib.read(strSqlNombreBanco);
					// Ya tenemos la descripción el Banco
					fila[1] = ib.getDescripcion();					
										
					modeloTabla.addRow(fila);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class SalirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Si pinchamos en el botón 'Salir', no pasamos banco y 'tiramos' la
			// pantalla
			banco = null;
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
	        	bancoDevuelto = null;
	        	getBanco();
	        }
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	public Banco getBanco(){
		pantalla.dispose();
		return bancoDevuelto;
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
				bancoDevuelto.setBanco(Integer.valueOf(String.valueOf(target.getValueAt(row, 0))));				
				getBanco();
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
