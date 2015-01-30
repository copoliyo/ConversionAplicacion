/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import general.DatosComunes;
import general.MysqlConnect;
import indices.IndiceCuentas;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tablas.Cuenta;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.Fecha;

/**
 *
 * @author Txus
 */
public class ConsultaPlanContable extends util.EscapeDialog {    
    
    /**
     * Creates new form ConsultaPlanContable
     */
    public ConsultaPlanContable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jtffeFecha.setText(Cadena.fechaAcadena(Fecha.fechaDia()));
        inicializaTabla();
        this.setVisible(true);
    }
    
     public enum Columna {

        CUENTA(0),
        TITULO(1),
        SALDO_DEUDOR(2),
        SALDO_ACREEDOR(3);        

        private int value;

        private Columna(int value) {
            this.value = value;
        }
    }

    // Definiciones de componentes de pantalla
    private IndiceCuentas indiceCuentas = null; 
     
    private String strSql = "";      
    private Cuenta cuenta = new Cuenta();        
    private String tipoConsulta = "PRIMER_GRADO";
   
    ResultSet rs = null;
    ResultSet rsDebeHaber = null;
    MysqlConnect m = null;
   
    private DefaultTableCellRenderer dtcrIzquierda, dtcrDerecha;
    //private TableCellRenderer tcr;
    JTable jtPlanContable;
    JScrollPane spPlanContable;
    
    DefaultTableModel modeloTablaPlanContable = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Para que no podamos editar los datos de la tabla
            return false;
        }
    };
      
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCuenta = new javax.swing.JLabel();
        jtfnfCuenta = new util.JTextFieldNumeroFijo(9);
        jlFecha = new javax.swing.JLabel();
        jtffeFecha = new util.JTextFieldFecha();
        jbSalir = new javax.swing.JButton();
        jlDeSaldo = new javax.swing.JLabel();
        jtfnf2DDeSaldo = new util.JTextFieldNumero2Decimales();
        jlASaldo = new javax.swing.JLabel();
        jtfnf2DASaldo = new util.JTextFieldNumero2Decimales();
        jbPrimerGrado = new javax.swing.JButton();
        jbTodas = new javax.swing.JButton();
        jbBuscarCuenta = new javax.swing.JButton();
        jbOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Plan Contable");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModal(true);
        setResizable(false);

        jlCuenta.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlCuenta.setText("Cuenta");

        jtfnfCuenta.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jtfnfCuenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnfCuentaFocusLost(evt);
            }
        });

        jlFecha.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlFecha.setText("Fecha");

        jtffeFecha.setText("00.00.00");
        jtffeFecha.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jtffeFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffeFechaFocusLost(evt);
            }
        });
        jtffeFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtffeFechaActionPerformed(evt);
            }
        });

        jbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SALIR.gif"))); // NOI18N
        jbSalir.setPreferredSize(new java.awt.Dimension(30, 30));
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jlDeSaldo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlDeSaldo.setText("De Saldo");

        jtfnf2DDeSaldo.setText("jTextFieldNumero2Decimales1");
        jtfnf2DDeSaldo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jtfnf2DDeSaldo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnf2DDeSaldoFocusLost(evt);
            }
        });

        jlASaldo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlASaldo.setText("A Saldo");

        jtfnf2DASaldo.setText("jTextFieldNumero2Decimales2");
        jtfnf2DASaldo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jtfnf2DASaldo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnf2DASaldoFocusLost(evt);
            }
        });

        jbPrimerGrado.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbPrimerGrado.setText("Primer Grado");
        jbPrimerGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPrimerGradoActionPerformed(evt);
            }
        });

        jbTodas.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbTodas.setText("Todas");
        jbTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTodasActionPerformed(evt);
            }
        });

        jbBuscarCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.gif"))); // NOI18N
        jbBuscarCuenta.setPreferredSize(new java.awt.Dimension(30, 30));
        jbBuscarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarCuentaActionPerformed(evt);
            }
        });

        jbOk.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbOk.setText("Ok");
        jbOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfnfCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtffeFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlDeSaldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfnf2DDeSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlASaldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfnf2DASaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jbPrimerGrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbTodas)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCuenta)
                    .addComponent(jtfnfCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlFecha)
                    .addComponent(jtffeFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbOk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 480, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlDeSaldo)
                        .addComponent(jtfnf2DDeSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlASaldo)
                        .addComponent(jtfnf2DASaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbPrimerGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbTodas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicializaTabla(){
       
        m = MysqlConnect.getDbCon();
        
        // La tabla contendra estas columnas
        modeloTablaPlanContable.addColumn("Cuenta");
        modeloTablaPlanContable.addColumn("T�tulo");
        modeloTablaPlanContable.addColumn("Saldo Deudor");
        modeloTablaPlanContable.addColumn("Saldo Acreedor");        
        
        
        jtPlanContable = new JTable(modeloTablaPlanContable);     
        
        jtPlanContable.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));      
        
        TableColumn columna = new TableColumn();
        // Establecemos el ancho
        jtPlanContable.getColumn("Cuenta").setMaxWidth(100);
        jtPlanContable.getColumn("T�tulo").setMaxWidth(400);
        jtPlanContable.getColumn("Saldo Deudor").setMaxWidth(120);
        jtPlanContable.getColumn("Saldo Acreedor").setMaxWidth(120);
        jtPlanContable.setRowHeight(20);
        
      

        // Hacemos que las columnas se alineen a la IZQUIERDA
        dtcrIzquierda = new DefaultTableCellRenderer();
        dtcrIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Hacemos que las columnas se alineen a la DERECHA
        dtcrDerecha = new DefaultTableCellRenderer();
        dtcrDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        
        
        jtPlanContable.getColumn("Cuenta").setCellRenderer(dtcrIzquierda);
        jtPlanContable.getColumn("T�tulo").setCellRenderer(dtcrIzquierda);
        jtPlanContable.getColumn("Saldo Deudor").setCellRenderer(dtcrDerecha);
        jtPlanContable.getColumn("Saldo Acreedor").setCellRenderer(dtcrDerecha);  
                
        // Creamos un JscrollPane y le agregamos la JTable
        spPlanContable = new JScrollPane(jtPlanContable);        
        // Si quisieramos barra horizontal, descomentar la linea siguiente
        spPlanContable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);        
        // Agregamos el JScrollPane al contenedor
        spPlanContable.setBounds(10, 40, 740, 470);
        spPlanContable.setFont(Apariencia.cambiaFuente());                
        
        this.add(spPlanContable);	        
        
        // Primero cargamos las de Debe
        cargaPlanContable("PRIMER_GRADO");                                        
        //cargaPlanContable("TODAS");                                        
        //cargaPlanContable("300");                                        
    }
    
    private void cargaPlanContable(String strQueCuentas){
        // srtQueCuentas = "TODAS"
        // strQueCuentas = "PRIMER_GRADO"
        // strQueCuentas = C�digo de cuenta -> esa y sus 'inferiores'
         
        int numeroDeFilas = 0;
        int clave, mes, any;        
        String strSqlListaCuentas;
        String strFecha, strAny, strMes, strSaldo;
        double dTotalDebe, dTotalHaber, dSaldo, dDeSaldo, aSaldo;
        
        dSaldo = dDeSaldo = aSaldo =  0.0;
        dDeSaldo = jtfnf2DDeSaldo.getDouble();
        aSaldo = jtfnf2DASaldo.getDouble();
        
        strFecha = jtffeFecha.getStrFechaAAAAMMDD();
        if(strFecha.length() == 0)
            strFecha = String.valueOf(Fecha.fechaDia());
        strAny = strFecha.substring(0, 4);
        strMes = strFecha.substring(4, 6);
        any = Integer.valueOf(strAny);
        mes = Integer.valueOf(strMes);
        
        Object fila[] = {"", "", "", ""};       

        strSqlListaCuentas = "";
        
        strSqlListaCuentas = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
             + "CONTAB_CENTRO = " + DatosComunes.centroCont + " AND ";
        
        if(strQueCuentas.equalsIgnoreCase("TODAS")){
            strSqlListaCuentas += "CONTAB_CUENTA > ''";
        }else{        
            if(strQueCuentas.equalsIgnoreCase("PRIMER_GRADO")){
                strSqlListaCuentas += "CONTAB_GRADO = '1'";
            }else{
                // Tenemos un c�digo de cuenta
                strSqlListaCuentas += "CONTAB_CUENTA LIKE '" + strQueCuentas + "%'";
            }
        }               
            
        
        strSqlListaCuentas += " ORDER BY CONTAB_CUENTA ASC, CONTAB_GRADO ASC";
        
        
        cuenta = new Cuenta();
        dTotalDebe = dTotalHaber = 0.0;
        numeroDeFilas = BaseDatos.countRows(strSqlListaCuentas);
        if (numeroDeFilas > 0) {
            try {
                limpiarTabla();                
                rs = (ResultSet) m.query(strSqlListaCuentas);

                // Recorremos el recodSet para ir rellenando la tabla de marcas
                while (rs.next() == true) {
                    cuenta.read(rs);                                        

                    fila[Columna.CUENTA.value] = cuenta.getCuenta();
                    fila[Columna.TITULO.value] = cuenta.getTitulo();
                   
                    if(cuenta.getCuenta().equalsIgnoreCase("608")){
                        strSaldo = "0.00";
                    }
                    // Ahora tenemos que calcular los saldos a la fecha dada
                    dSaldo = cuenta.getSaldoCuentaMesAny(cuenta.getCuenta().trim(), mes, any);
                    strSaldo = "0.00";
                    strSaldo = Cadena.formatear2Decimales(dSaldo);
                    
                    if(dDeSaldo > 0.0 && Math.abs(dSaldo) < dDeSaldo){
                        strSaldo = "0,00";
                        dSaldo = 0.0;
                    }
                    
                    if(aSaldo > 0.0 && Math.abs(dSaldo) > aSaldo){
                        strSaldo = "0,00";
                        dSaldo = 0.0;
                    }
                        
                    if (!strSaldo.equalsIgnoreCase("0,00")) {                        
                        strSaldo = strSaldo.replaceAll("-", "");
                        if (dSaldo < 0.0) {
                            dTotalHaber += Math.abs(dSaldo);                            
                            fila[Columna.SALDO_DEUDOR.value] = "";
                            fila[Columna.SALDO_ACREEDOR.value] = strSaldo;
                        } else {
                            dTotalDebe += Math.abs(dSaldo);
                            fila[Columna.SALDO_DEUDOR.value] = strSaldo;
                            fila[Columna.SALDO_ACREEDOR.value] = "";
                        }
                        modeloTablaPlanContable.addRow(fila);
                    }
                    
                }
                // A�adimos una fila con el total
                fila[Columna.CUENTA.value] = "";
                fila[Columna.TITULO.value] = "Total";
                fila[Columna.SALDO_DEUDOR.value] = Cadena.formatear2Decimales(dTotalDebe);
                fila[Columna.SALDO_ACREEDOR.value] = Cadena.formatear2Decimales(dTotalHaber);
                modeloTablaPlanContable.addRow(fila);
                
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
 public void limpiarTabla(){
        // Vaciamos la tabla        
        modeloTablaPlanContable.setRowCount(0);       
    }    
    
    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
       
        this.setVisible(false);
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbPrimerGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPrimerGradoActionPerformed
        
        limpiarTabla();
        tipoConsulta = "PRIMER_GRADO";
        jtfnfCuenta.setText("");
        cargaPlanContable("PRIMER_GRADO");
    }//GEN-LAST:event_jbPrimerGradoActionPerformed

    private void jbTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTodasActionPerformed
        
        limpiarTabla();
        tipoConsulta = "TODAS";
        jtfnfCuenta.setText("");
        cargaPlanContable("TODAS");
    }//GEN-LAST:event_jbTodasActionPerformed

    private void jtffeFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtffeFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtffeFechaActionPerformed

    private void jtffeFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffeFechaFocusLost
        
        limpiarTabla();
        cargaPlanContable(tipoConsulta);
    }//GEN-LAST:event_jtffeFechaFocusLost

    private void jtfnf2DDeSaldoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnf2DDeSaldoFocusLost
        
        limpiarTabla();        
        cargaPlanContable(tipoConsulta);
    }//GEN-LAST:event_jtfnf2DDeSaldoFocusLost

    private void jtfnf2DASaldoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnf2DASaldoFocusLost
        
        limpiarTabla();        
        cargaPlanContable(tipoConsulta);
    }//GEN-LAST:event_jtfnf2DASaldoFocusLost

    private void jtfnfCuentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnfCuentaFocusLost
       
        limpiarTabla();
        tipoConsulta = jtfnfCuenta.getText().trim();
        cargaPlanContable(tipoConsulta);
    }//GEN-LAST:event_jtfnfCuentaFocusLost

    private void jbOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOkActionPerformed
        
        limpiarTabla();
        tipoConsulta = jtfnfCuenta.getText().trim();
        cargaPlanContable(tipoConsulta);
    }//GEN-LAST:event_jbOkActionPerformed

    private void jbBuscarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarCuentaActionPerformed
                        
        if (indiceCuentas == null) {
            indiceCuentas = new IndiceCuentas();
        } else {
            indiceCuentas.setVisible(true);
        }

        String strCuenta = indiceCuentas.getCuenta();
        if (strCuenta != null) {
            jtfnfCuenta.setText(indiceCuentas.getCuenta().trim());
            limpiarTabla();
            tipoConsulta = jtfnfCuenta.getText().trim();
            cargaPlanContable(tipoConsulta);
        }

            
    }//GEN-LAST:event_jbBuscarCuentaActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbBuscarCuenta;
    private javax.swing.JButton jbOk;
    private javax.swing.JButton jbPrimerGrado;
    private javax.swing.JButton jbSalir;
    private javax.swing.JButton jbTodas;
    private javax.swing.JLabel jlASaldo;
    private javax.swing.JLabel jlCuenta;
    private javax.swing.JLabel jlDeSaldo;
    private javax.swing.JLabel jlFecha;
    private util.JTextFieldFecha jtffeFecha;
    private util.JTextFieldNumero2Decimales jtfnf2DASaldo;
    private util.JTextFieldNumero2Decimales jtfnf2DDeSaldo;
    private util.JTextFieldNumeroFijo jtfnfCuenta;
    // End of variables declaration//GEN-END:variables
}
