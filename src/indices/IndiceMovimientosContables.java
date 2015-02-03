package indices;

import general.DatosComunes;
import general.MysqlConnect;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import tablas.ConceptoMovContable;
import tablas.Cuenta;
import tablas.DebeHaber;
import tablas.MovimientoContable;
import tablas.Nota;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.EscapeDialog;
import util.JTextFieldFecha;
import util.JTextFieldNumeroFijo;

public class IndiceMovimientosContables implements ActionListener, PropertyChangeListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println(evt.getPropertyName());
        if ("progress".equalsIgnoreCase(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            jpbProgreso.setValue(progress);
            //System.out.println(progress);
        }
    }

    static final int MAX_REGISTROS = 20000;
    public enum Columna {

        FECHA(0),
        ASIENTO(1),
        APUNTE(2),
        CUENTA(3),
        DOCUMENTO(4),
        CLAVE(5),
        DESCRIPCION(6),
        DEBE(7),
        HABER(8),
        SALDO(9),
        DEBE_DOUBLE(10),
        HABER_DOUBLE(11);

        private int value;

        private Columna(int value) {
            this.value = value;
        }
    }

    // Para SwingWorker
    TareaCargaMovimientos tareaCargaMovimientos;
    private double dSaldo = 0.0;

    JDialog pantalla;
    JLabel lFecha, lAsiento, lSaldo, lRegistrosPendientes;
    JTextFieldFecha jtfeFecha;
    JTextFieldNumeroFijo jtnfAsiento;
    JTable jtMovimientosContables;
    JScrollPane spMovimientosContables;
    JButton jbSalir;
    JProgressBar jpbProgreso;
    Image imgSalir;
    DefaultTableCellRenderer tcr;
    TableCellRenderer tcr2;

    ResultSet rs = null;
    MysqlConnect m = null;

    Cuenta cuenta;

    private boolean conCuenta = false;
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

    public IndiceMovimientosContables() {
        conCuenta = false;
        m = MysqlConnect.getDbCon();

        creaGui();
    }

    public IndiceMovimientosContables(String strCuenta) {
        conCuenta = true;
        this.strCuenta = strCuenta.trim();
        m = MysqlConnect.getDbCon();

        creaGui();
    }

    private void creaGui() {
        pantalla = new EscapeDialog();

        if (conCuenta == true) {
            cuenta = new Cuenta();
            dSaldo = cuenta.getSaldoCuenta(strCuenta, DatosComunes.centroCont);
            pantalla.setTitle("Movimientos Contables - " + strCuenta + " - " + cuenta.getTitulo());
        } else {
            dSaldo = 0;
            pantalla.setTitle("Movimientos Contables");
        }

        pantalla.setModal(true);
        pantalla.setSize(1000, 630);
        //pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		 
        pantalla.setDefaultCloseOperation(0);
        pantalla.setLayout(null);

        // La tabla contendrá estas columnas
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Asiento");
        modeloTabla.addColumn("Apunte");
        modeloTabla.addColumn("Cuenta");
        modeloTabla.addColumn("Documento");
        modeloTabla.addColumn("Clave");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Debe");
        modeloTabla.addColumn("Haber");
        modeloTabla.addColumn("Saldo");
        modeloTabla.addColumn("DebeDouble");
        modeloTabla.addColumn("HaberDouble");

        jtMovimientosContables = new JTable(modeloTabla);
        jtMovimientosContables.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));

        TableColumn columna = new TableColumn();
        // Establecemos el ancho
        jtMovimientosContables.getColumn("Fecha").setMaxWidth(80);
        jtMovimientosContables.getColumn("Asiento").setMaxWidth(60);
        jtMovimientosContables.getColumn("Apunte").setMaxWidth(60);

        if (conCuenta == true) {
            jtMovimientosContables.getColumn("Cuenta").setMinWidth(0);
            jtMovimientosContables.getColumn("Cuenta").setMaxWidth(0);
            jtMovimientosContables.getColumn("Cuenta").setWidth(0);
        } else {
            jtMovimientosContables.getColumn("Cuenta").setMaxWidth(120);
        }

        jtMovimientosContables.getColumn("Documento").setMaxWidth(70);
        jtMovimientosContables.getColumn("Clave").setMaxWidth(40);
        jtMovimientosContables.getColumn("Descripcion").setMaxWidth(290);
        jtMovimientosContables.getColumn("Debe").setMaxWidth(120);
        jtMovimientosContables.getColumn("Haber").setMaxWidth(120);

        if (conCuenta == false) {
            jtMovimientosContables.getColumn("Saldo").setMinWidth(0);
            jtMovimientosContables.getColumn("Saldo").setMaxWidth(0);
            jtMovimientosContables.getColumn("Saldo").setWidth(0);
        } else {
            jtMovimientosContables.getColumn("Saldo").setMaxWidth(120);
        }

        jtMovimientosContables.getColumn("DebeDouble").setMinWidth(0);
        jtMovimientosContables.getColumn("DebeDouble").setMaxWidth(0);
        jtMovimientosContables.getColumn("DebeDouble").setWidth(0);
        jtMovimientosContables.getColumn("HaberDouble").setMinWidth(0);
        jtMovimientosContables.getColumn("HaberDouble").setMaxWidth(0);
        jtMovimientosContables.getColumn("HaberDouble").setWidth(0);
        jtMovimientosContables.setRowHeight(20);

        // Hacemos que las columnas se alineen a la DERECHA
        tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jtMovimientosContables.getColumn("Fecha").setCellRenderer(tcr);
        jtMovimientosContables.getColumn("Asiento").setCellRenderer(tcr);
        jtMovimientosContables.getColumn("Apunte").setCellRenderer(tcr);
        jtMovimientosContables.getColumn("Documento").setCellRenderer(tcr);
        jtMovimientosContables.getColumn("Clave").setCellRenderer(tcr);
        jtMovimientosContables.getColumn("Debe").setCellRenderer(tcr);
        jtMovimientosContables.getColumn("Haber").setCellRenderer(tcr);

        // Hacemos que la comluna del saldo se alinee a la derecha y
        // que salga en rojo si es negativa.
        tcr2 = new TableCellRenderer();
        jtMovimientosContables.getColumn("Saldo").setCellRenderer(tcr2);

        // Creamos un JscrollPane y le agregamos la JTable
        spMovimientosContables = new JScrollPane(jtMovimientosContables);
        // Si quisieramos barra horizontal, descomentar la linea siguiente
        spMovimientosContables.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // Agregamos el JScrollPane al contenedor
        spMovimientosContables.setBounds(10, 50, 970, 500);
        spMovimientosContables.setFont(Apariencia.cambiaFuente());
        pantalla.add(spMovimientosContables);

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

        lAsiento = new JLabel("Asiento");
        lAsiento.setBounds(180, 15, 60, 20);
        lAsiento.setFont(Apariencia.cambiaFuente());
        pantalla.add(lAsiento);

        jtnfAsiento = new JTextFieldNumeroFijo(5);
        jtnfAsiento.setBounds(250, 15, 90, 25);
        jtnfAsiento.setText("0");
        jtnfAsiento.setFont(Apariencia.cambiaFuente());
        jtnfAsiento.addActionListener(new AsientoListener());
        pantalla.add(jtnfAsiento);

        lSaldo = new JLabel("Saldo : ");
        lSaldo.setBounds(360, 15, 200, 20);
        lSaldo.setFont(Apariencia.cambiaFuente());
        lSaldo.setText("Saldo : " + Cadena.formatoConComaDecimal(dSaldo));
        if (dSaldo < 0.0) {
            lSaldo.setForeground(Color.RED);
        }

        lSaldo.setVisible(conCuenta);
        pantalla.add(lSaldo);

        lRegistrosPendientes = new JLabel();
        lRegistrosPendientes.setBounds(600, 15, 200, 20);
        lRegistrosPendientes.setFont(Apariencia.cambiaFuente());
        lRegistrosPendientes.setText("Saldo : " + Cadena.formatoConComaDecimal(dSaldo));
        pantalla.add(lRegistrosPendientes);

        jpbProgreso = new JProgressBar();
        jpbProgreso.setBounds(10, 560, 970, 20);
        jpbProgreso.setFont(Apariencia.cambiaFuente());
        jpbProgreso.setStringPainted(true);
        jpbProgreso.setValue(0);
        pantalla.add(jpbProgreso);

        borrarTabla();
        cargaMovimientos();
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

    private void cargaMovimientos() {
        tareaCargaMovimientos = new TareaCargaMovimientos();
        tareaCargaMovimientos.addPropertyChangeListener(this);
        tareaCargaMovimientos.setTipoCarga(strCuenta);
        tareaCargaMovimientos.execute();
    }

    class TareaCargaMovimientos extends SwingWorker<Void, Void> {

        private String strCuenta;
        private boolean conCuenta;

        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            int fecha = jtfeFecha.getFechaAAAAMMDD();
            String strDesdeFecha = jtfeFecha.getStrFechaAAAAMMDD();
            int asiento = Integer.valueOf(jtnfAsiento.getText().trim());
            lSaldo.setText("Saldo : 0,00");
            String strCodigoCuenta = "";

            String claveFechaAsientoApunte = String.valueOf(strDesdeFecha)
                    + String.format("%05d", asiento)
                    + "00000";

            Object fila[] = {"", "", "", "", "", "", "", "", "", "", "", ""};

            String strSql = "SELECT * FROM MOVCON WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                    + " MOVCON_CENTRO = " + DatosComunes.centroCont + " AND ";

            if (conCuenta == true) {
                strSql += " MOVCON_CUENTA = '" + strCuenta + "' AND ";
            }

            strSql += " MOVCON_FECH_ASTO_APT >= '" + claveFechaAsientoApunte + "'";

            String strSqlConcepto = "";

            // Aquí meteremos el Concepto del Movimiento Contable
            ResultSet rsConceptoMovContable = null;
            ConceptoMovContable concepto = new ConceptoMovContable();

            m = MysqlConnect.getDbCon();

            int numeroRegistros = BaseDatos.countRows(strSql);
            int registrosLeidos = 0;
            int progress = 0;
            int progressAnt = 0;
            setProgress(0);
            
            
            if (numeroRegistros > MAX_REGISTROS) {
                Apariencia.mensajeInformativo(5, "<center>Son demasiados Registros!!!<BR>Sólo se mostrarán los " + MAX_REGISTROS + " primeros.<BR>Cambiar la fecha para ver los demás.</center>");
                strSql = "SELECT * FROM MOVCON WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                        + " MOVCON_CENTRO = " + DatosComunes.centroCont + " AND ";
                if (conCuenta == true) {
                    strSql += " MOVCON_CUENTA = '" + strCuenta + "' AND ";
                }

                strSql += " MOVCON_FECH_ASTO_APT >= '" + claveFechaAsientoApunte + "' LIMIT " + MAX_REGISTROS;

                numeroRegistros = BaseDatos.countRows(strSql);
                numeroRegistros = MAX_REGISTROS;
            }
            
            if (numeroRegistros != 0) {
                String strFechaTabla = "", strAsientoTabla = "", strApunteTabla = "";
                try {
                    MovimientoContable movimiento = new MovimientoContable();
                    rs = m.query(strSql);

                    while (rs.next() || rs.isLast()) {

                        movimiento.read(rs);

                        registrosLeidos++;
                        
                        claveFechaAsientoApunte = String.valueOf(movimiento.getFechaAsientoApunte());
                        strCodigoCuenta = movimiento.getCuenta();
                        // Leemos el concepto ahora que sabemos la clave
                        strSqlConcepto = "SELECT * FROM MOCCEP WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                                + " MOCCEP_FECH_ASTO_APT = '" + movimiento.getFechaAsientoApunte() + "'";

                        if (BaseDatos.countRows(strSqlConcepto) != 0) {
                            rsConceptoMovContable = m.query(strSqlConcepto);
                            if (rsConceptoMovContable.next()) {
                                concepto.read(rsConceptoMovContable);
                            }
                        }

                        strFechaTabla = claveFechaAsientoApunte.substring(0, 8);
                        strAsientoTabla = claveFechaAsientoApunte.substring(8, 13);
                        strApunteTabla = claveFechaAsientoApunte.substring(13, 18);

                        fila[Columna.FECHA.value] = Cadena.fechaAcadena(Integer.valueOf(strFechaTabla));
                        fila[Columna.ASIENTO.value] = Integer.valueOf(strAsientoTabla);
                        fila[Columna.APUNTE.value] = Integer.valueOf(strApunteTabla);
                        if (conCuenta == false) {
                            fila[Columna.CUENTA.value] = Cadena.formateaCuentaContable(strCodigoCuenta);
                        } else {
                            fila[Columna.CUENTA.value] = "";
                        }

                        if (movimiento.getDocumento() == 0) {
                            fila[Columna.DOCUMENTO.value] = "";
                        } else {
                            fila[Columna.DOCUMENTO.value] = movimiento.getDocumento();
                        }
                        fila[Columna.CLAVE.value] = movimiento.getClave();
                        fila[Columna.DESCRIPCION.value] = concepto.getConcepto();
                        if (movimiento.getClave() < 50) {
                            fila[Columna.DEBE.value] = Cadena.formatoConComaDecimal(movimiento.getImporte());
                            fila[Columna.HABER.value] = "";
                        } else {
                            fila[Columna.DEBE.value] = "";
                            fila[Columna.HABER.value] = Cadena.formatoConComaDecimal(movimiento.getImporte());
                        }

                        fila[Columna.SALDO.value] = "Saldo";
                        if (movimiento.getClave() < 50) {
                            fila[Columna.DEBE_DOUBLE.value] = movimiento.getImporte();
                            fila[Columna.HABER_DOUBLE.value] = 0.0;
                        } else {
                            fila[Columna.DEBE_DOUBLE.value] = 0.0;
                            fila[Columna.HABER_DOUBLE.value] = movimiento.getImporte();
                        }

                        modeloTabla.addRow(fila);
                        
                        progress = (registrosLeidos * 100) / numeroRegistros;
                        /*
                        if(progress != progressAnt){
                            progressAnt = progress;
                            setProgress(progress);
                        }*/
                        setProgress(progress);
                    }
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Apariencia.mensajeInformativo(5, "<center>Error en lectura fichero de DebeHaber<BR>Consulta Acumulados Contables</center>");
                }

                int numeroDeFilas = modeloTabla.getRowCount() - 1;

                int topeFecha = 0;
                int topeAsiento = 0;
                int topeApunte = 0;

                topeFecha = Cadena.cadenaAfecha((String) modeloTabla.getValueAt(numeroDeFilas, Columna.FECHA.value));
                topeAsiento = (Integer) modeloTabla.getValueAt(numeroDeFilas, Columna.ASIENTO.value);
                topeApunte = (Integer) modeloTabla.getValueAt(numeroDeFilas, Columna.APUNTE.value);

                claveFechaAsientoApunte = String.valueOf(topeFecha)
                        + String.format("%05d", topeAsiento)
                        + String.format("%05d", topeApunte);

                if (conCuenta == true) {
                    Cuenta cSaldoFecha = new Cuenta();
                    dSaldo = cSaldoFecha.getSaldoCuentaEnFecha(strCuenta, DatosComunes.centroCont, claveFechaAsientoApunte);

                // Una vez conocemos el saldo a HOY, tenemos que ir recalculando hacia atrás.
                    // Saldo = SaldoAnterior + Debe - Haber
                    double saldo = dSaldo;
                    double debe = 0.0;
                    double haber = 0.0;

                    if (saldo == 0.0) {
                        modeloTabla.setValueAt("", numeroDeFilas, Columna.SALDO.value);
                    } else {
                        modeloTabla.setValueAt(Cadena.formatoConComaDecimal(saldo), numeroDeFilas, Columna.SALDO.value);
                    }

                    for (int i = numeroDeFilas - 1; i >= 0; i--) {
                        debe = (Double) modeloTabla.getValueAt(i + 1, Columna.DEBE_DOUBLE.value);
                        haber = (Double) modeloTabla.getValueAt(i + 1, Columna.HABER_DOUBLE.value);
                        saldo = saldo - debe + haber;
                        //modeloTabla.setValueAt(String.valueOf(saldo), i, 7);
                        if (saldo <= 0.01 && saldo >= -0.01) {
                            modeloTabla.setValueAt("", i, Columna.SALDO.value);
                        } else {
                            modeloTabla.setValueAt(Cadena.formatoConComaDecimal(saldo), i, Columna.SALDO.value);
                        }
                    }
                }

                // Vamos al final de la trabla
                jtMovimientosContables.clearSelection();
                jtMovimientosContables.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
                Rectangle rect = jtMovimientosContables.getCellRect(numeroDeFilas, 0, true);
                jtMovimientosContables.scrollRectToVisible(rect);
                jtMovimientosContables.clearSelection();
                jtMovimientosContables.setRowSelectionInterval(numeroDeFilas, numeroDeFilas);
                DefaultTableModel modelo = (DefaultTableModel) jtMovimientosContables.getModel();
                modelo.fireTableDataChanged();
            }

            if (conCuenta == true) {
                dSaldo = cuenta.getSaldoCuenta(strCuenta, DatosComunes.centroCont);

                lSaldo.setText("Saldo : " + Cadena.formatoConComaDecimal(dSaldo));
                if (dSaldo < 0.0) {
                    lSaldo.setForeground(Color.RED);
                }
                pantalla.add(lSaldo);
            }
            return null;
        }
        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            //System.out.println("Hecho!!!");
            tareaCargaMovimientos.cancel(true);
        }

        public void setTipoCarga(String Cuenta) {
            strCuenta = Cuenta;
            if(Cuenta.length() > 0)
                conCuenta = true;
            else
                conCuenta = false;
            
        }

    }

    private void borrarTabla() {
		// Vaciamos la tabla
        //int a = modeloTabla.getRowCount() - 1;

        //for (int i = a; i >= 0; i--)
        //	modeloTabla.removeRow(i);
        modeloTabla.setRowCount(0);
    }

    class FechaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            borrarTabla();
            cargaMovimientos();

        }

    }

    class AsientoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            borrarTabla();
            cargaMovimientos();
        }
    }

    public void recargarMovimientos(String strCuenta) {
        this.strCuenta = strCuenta.trim();
        borrarTabla();
        cargaMovimientos();
        pantalla.setVisible(true);
    }
}
