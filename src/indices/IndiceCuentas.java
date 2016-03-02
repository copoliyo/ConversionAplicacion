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

import tablas.Cuenta;
import util.Apariencia;
import util.BaseDatos;

public class IndiceCuentas {

    JDialog pantalla;
    JCheckBox cInactivos;
    JButton bClientes, bProvAcreed, bSalir;
    JTable jtCuentas;
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

    public IndiceCuentas() {
        m = MysqlConnect.getDbCon();

        pantalla = new JDialog();
        pantalla.setModal(true);
        pantalla.setSize(700, 550);
        pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pantalla.setLayout(null);

        cInactivos = new JCheckBox("Inactivos");
        cInactivos.setBounds(20, 5, 100, 20);
        cInactivos.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
        cInactivos.addActionListener(new CheckListener());

        lFiltro = new JLabel("Filtro: ");
        lFiltro.setBounds(150, 5, 80, 20);
        lFiltro.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));

        tfBuscar = new JTextField();
        tfBuscar.setBounds(210, 5, 120, 25);
        tfBuscar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
        tfBuscar.addKeyListener(new EscapeListener());
        tfBuscar.addKeyListener(new BuscarListener());

        bClientes = new JButton("Clientes");
        bClientes.setBounds(400, 490, 100, 20);
        bClientes.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
        bClientes.addActionListener(new BClienteListener());

        bProvAcreed = new JButton("Prov/acrd");
        bProvAcreed.setBounds(510, 490, 120, 20);
        bProvAcreed.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
        bProvAcreed.addActionListener(new BProveedorListener());

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
        modeloTabla.addColumn("Cuenta");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Saldo");

        final JTable jtCuentas = new JTable(modeloTabla);

        jtCuentas.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));

        TableColumn columna = new TableColumn();
        // Establecemos el ancho
        jtCuentas.getColumn("Cuenta").setPreferredWidth(120);
        jtCuentas.getColumn("Título").setPreferredWidth(400);
        jtCuentas.getColumn("Saldo").setPreferredWidth(150);

        // Hacemos que la columna numero 2 se alinee a la DERECHA
        tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jtCuentas.getColumnModel().getColumn(2).setCellRenderer(tcr);
        // Listener para que al pulsar ESCAPE se salga del programa
        jtCuentas.addKeyListener(new EscapeListener());
		// Cuando pinchamos en la cabecera, se obtiene el número de la columna por 
        // la que vamos a ordenar.
        jtCuentas.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                strBuscar = "";
                columnaOrden = jtCuentas.columnAtPoint(e.getPoint());
		        //String name = jtCuentas.getColumnName(columnaOrden);
                //System.out.println("Column index selected " + columnaOrden + " " + name);
                limpiarTabla();
                rellenarTabla();
            }
        });

		// Añadimos un lístener y un HeaderTableRender para poner el nombre de la columna
        // en NEGRITA y así saber cual es la columna por la que se ordena.
        JTableHeader cuentasHeader = jtCuentas.getTableHeader();
        final Font boldFont = cuentasHeader.getFont().deriveFont(Font.BOLD);
        final TableCellRenderer headerRenderer = cuentasHeader.getDefaultRenderer();
        cuentasHeader.setDefaultRenderer(new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = headerRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == columnaOrden) {
                    comp.setFont(boldFont);
                }
                // tfBuscar.setText("");
                return comp;
            }
        });

        jtCuentas.setPreferredScrollableViewportSize(new Dimension(
                380, 430));

        // Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(jtCuentas);
		// Si quisieramos barra horizontal, descomentar la linea siguiente
        // scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        // Agregamos el JScrollPane al contenedor
        scrollPane.setBounds(10, 30, 650, 430);
        // Necesitamos saber si hacemos doble click en una fila
        jtCuentas.addMouseListener(new TablaListener());

        pantalla.add(scrollPane);

        pantalla.add(cInactivos);
        pantalla.add(lFiltro);
        pantalla.add(tfBuscar);
        pantalla.add(bSalir);
        pantalla.add(bClientes);
        pantalla.add(bProvAcreed);

        limpiarTabla();
        rellenarTabla();
        // Ponemos el foco en el campo de BUSCAR nada mas mostrar el indice.
        pantalla.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                tfBuscar.requestFocus();
            }
        });

        pantalla.setVisible(true);

    }

    private void limpiarTabla() {
        // Vaciamos la tabla
        modeloTabla.setRowCount(0);
    }

    private void rellenarTabla() {
        int numeroDeFilas = 0;

        Object fila[] = {"", "", ""};

        if (m.conn == null) {
            getCuenta();
        }

        strSql = "";

        strSql = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' ";

        if (inactivos == false) {
            strSql += " AND CONTAB_ACTIVO = 1 ";
        }

        if (DatosComunes.centroCont != 0) {
            strSql += " AND CONTAB_CENTRO = " + DatosComunes.centroCont + " ";
        }

        strBuscar = tfBuscar.getText();

        switch (columnaOrden) {
            case 0:
                if (strBuscar.length() > 0) {
                    strSql += "AND CONTAB_CUENTA LIKE '" + strBuscar + "%' ";
                }
                strSql += "ORDER BY CONTAB_CUENTA ";
                break;
            case 1:
                if (strBuscar.length() > 0) {
                    strSql += "AND CONTAB_TITULO LIKE '%" + strBuscar + "%' ";
                }
                strSql += "ORDER BY CONTAB_TITULO ";
                break;
            case 2:
                strSql += "ORDER BY CONTAB_SALDO DESC";
                break;
        }

        numeroDeFilas = BaseDatos.countRows(strSql);
        if (numeroDeFilas > 0) {
            try {
                limpiarTabla();
                Cuenta cuenta = new Cuenta();
                rs = m.query(strSql);

                // Recorremos el recodSet para ir rellenando la tabla de marcas
                while (rs.next() == true) {
                    cuenta.read(rs);

					//String strCuenta = "";
                    //String strTitulo = "";
                    //String strSaldo = "";
                    fila[0] = cuenta.getCuenta();
                    fila[1] = cuenta.getTitulo();
                    if (cuenta.getActivo() == 0) {
                        fila[1] = "n" + fila[1];
                    }
                    if (cuenta.getSaldo() == 0.0) {
                        fila[2] = "";
                    } else {
                        String str = "";
                        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                        simbolos.setDecimalSeparator(',');
                        DecimalFormat myFormatter = new DecimalFormat("###,###,##0.00", simbolos);
                        str = myFormatter.format(cuenta.getSaldo());
                        fila[2] = String.valueOf(str);
                    }

                    modeloTabla.addRow(fila);
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
    public void recargarTabla(){
        limpiarTabla();
        rellenarTabla();
    }

    public void setVisible(boolean visible) {
        pantalla.setVisible(visible);
    }

    class CheckListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // CAmbiamos entre activos e inactivos
            if (arg0.getSource() == cInactivos) {
                if (cInactivos.isSelected()) {
                    inactivos = true;
                } else {
                    inactivos = false;
                }
            }

            limpiarTabla();
            rellenarTabla();
        }

    }

    class BClienteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // Abrimos el indice de Proveedores/Acreedores
            IndiceClientesContables icc = new IndiceClientesContables();
            strBuscar = icc.getCuenta();
            tfBuscar.setText(strBuscar);
            limpiarTabla();
            rellenarTabla();
        }
    }

    class BProveedorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // Abrimos el indice de Proveedores/Acreedores
            IndiceProveedores ip = new IndiceProveedores();
            strBuscar = ip.getCuenta();
            tfBuscar.setText(strBuscar);
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

    class EscapeListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent arg0) {
            // TODO Auto-generated method stub
            int id = arg0.getKeyCode();

            if (id == KeyEvent.VK_ESCAPE) {
                cuenta = "";
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

    public String getCuenta() {
        pantalla.setVisible(false);
        return cuenta;
    }

    class TablaListener implements ActionListener, MouseListener {

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
                cuenta = (String) target.getValueAt(row, 0);
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
