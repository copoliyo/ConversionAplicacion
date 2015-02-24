/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import util.Apariencia;

/**
 *
 * @author Txus
 */
public class EntradaAsientosContables extends util.EscapeDialog implements PropertyChangeListener {

    JTable jtApuntes;
    JScrollPane spApuntes;
    DefaultTableCellRenderer tcr;
    TableCellRenderer tcr2;
    
    DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Para que no podamos editar los datos de la tabla
            return false;
        }
    };
    
     public enum Columna {

        APUNTE(0),
        CUENTA(1),
        DOCUMENTO(2),
        CLAVE(3),
        CONCEPTO(4),
        DEBE(5),
        HABER(6);        

        private int value;

        private Columna(int value) {
            this.value = value;
        }
    }
    
    /**
     * Creates new form EntradaAsientosContables
     */
    public EntradaAsientosContables(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initMisComponentes();
        this.setVisible(true);
    }

    // Con esto conseguimos agregar componentes 'hechos a mano' a la
    // pantalla que ya contiene los componentes generados por NetBeans
    private void initMisComponentes(){

        
        // La tabla contendrá estas columnas
        modeloTabla.addColumn("Apunte");
        modeloTabla.addColumn("Cuenta");
        modeloTabla.addColumn("Documento");
        modeloTabla.addColumn("Clave");
        modeloTabla.addColumn("Concepto");
        modeloTabla.addColumn("Debe");
        modeloTabla.addColumn("Haber");
        
        jtApuntes = new JTable(modeloTabla);
        //jtApuntes.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));

        TableColumn columna = new TableColumn();
        // Establecemos el ancho
        jtApuntes.getColumn("Apunte").setMaxWidth(60);
        jtApuntes.getColumn("Cuenta").setMaxWidth(100);
        jtApuntes.getColumn("Documento").setMaxWidth(80);
        jtApuntes.getColumn("Clave").setMaxWidth(50);
        jtApuntes.getColumn("Concepto").setMaxWidth(330);
        jtApuntes.getColumn("Debe").setMaxWidth(120);
        jtApuntes.getColumn("Haber").setMaxWidth(120);
        
         // Hacemos que las columnas se alineen a la DERECHA
        tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);        
        jtApuntes.getColumn("Documento").setCellRenderer(tcr);
        jtApuntes.getColumn("Clave").setCellRenderer(tcr);
        
        // Hacemos que la comluna del saldo se alinee a la derecha y
        // que salga en rojo si es negativa.
        tcr2 = new TableCellRenderer();
        jtApuntes.getColumn("Debe").setCellRenderer(tcr2);
        jtApuntes.getColumn("Haber").setCellRenderer(tcr2);
        
        // Creamos un JscrollPane y le agregamos la JTable
        spApuntes = new JScrollPane(jtApuntes);
        // Si quisieramos barra horizontal, descomentar la linea siguiente
        spApuntes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // Agregamos el JScrollPane al contenedor
        spApuntes.setBounds(10, 80, 830, 250);
        spApuntes.setFont(Apariencia.cambiaFuente());
        getContentPane().add(spApuntes);
        
        
    }

    private void estableceVisibilidadInicial(){
    
        jlCuenta.setVisible(false);
        jlDocumento.setVisible(false);
        jlClave.setVisible(false);
        jlConcepto.setVisible(false);
        jlImporte.setVisible(false);
        
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPanelFechaAsiento = new javax.swing.JPanel();
        jlFechaAsiento = new javax.swing.JLabel();
        jlNumeroAsiento = new javax.swing.JLabel();
        jbBuscarAsiento = new javax.swing.JButton();
        jtffeFechaAsiento = new util.JTextFieldFecha();
        jtfnfAsiento = new util.JTextFieldNumeroFijo(6);
        jbOkAsiento = new javax.swing.JButton();
        jlCuenta = new javax.swing.JLabel();
        jtfnfCuenta = new util.JTextFieldNumeroFijo(9);
        jbBuscarCuenta = new javax.swing.JButton();
        jlDocumento = new javax.swing.JLabel();
        jtfnfDocumento = new util.JTextFieldNumeroFijo(5);
        jlClave = new javax.swing.JLabel();
        jtfnfClave = new util.JTextFieldNumeroFijo();
        jbBuscarClave = new javax.swing.JButton();
        jlConcepto = new javax.swing.JLabel();
        jtffConcepto = new util.JTextFieldFijo();
        jtfn2dImporte = new util.JTextFieldNumero2Decimales();
        jlImporte = new javax.swing.JLabel();
        jbOkApunte = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jbAnularAsiento = new javax.swing.JButton();
        jbIvaAutomatico = new javax.swing.JButton();
        jbMovimientos = new javax.swing.JButton();
        jbPrevisiones = new javax.swing.JButton();
        jlCuadre = new javax.swing.JLabel();
        jtfnf2DCuadre = new util.JTextFieldNumero2Decimales();
        jlNombreCuenta = new javax.swing.JLabel();
        jlTituloSaldo = new javax.swing.JLabel();
        jlSaldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrada de Asientos Contables");
        setResizable(false);

        jpPanelFechaAsiento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlFechaAsiento.setText("Fecha");

        jlNumeroAsiento.setText("Asiento");

        jbBuscarAsiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.gif"))); // NOI18N
        jbBuscarAsiento.setPreferredSize(new java.awt.Dimension(30, 30));

        jtffeFechaAsiento.setText("00.00.00");

        jbOkAsiento.setText("Ok");

        javax.swing.GroupLayout jpPanelFechaAsientoLayout = new javax.swing.GroupLayout(jpPanelFechaAsiento);
        jpPanelFechaAsiento.setLayout(jpPanelFechaAsientoLayout);
        jpPanelFechaAsientoLayout.setHorizontalGroup(
            jpPanelFechaAsientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelFechaAsientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelFechaAsientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPanelFechaAsientoLayout.createSequentialGroup()
                        .addComponent(jlNumeroAsiento)
                        .addGap(18, 18, 18)
                        .addComponent(jbBuscarAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlFechaAsiento))
                .addGap(18, 18, 18)
                .addGroup(jpPanelFechaAsientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtffeFechaAsiento, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jtfnfAsiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbOkAsiento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPanelFechaAsientoLayout.setVerticalGroup(
            jpPanelFechaAsientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPanelFechaAsientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelFechaAsientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPanelFechaAsientoLayout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(jpPanelFechaAsientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbBuscarAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfnfAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbOkAsiento)
                            .addComponent(jlNumeroAsiento))
                        .addContainerGap())
                    .addGroup(jpPanelFechaAsientoLayout.createSequentialGroup()
                        .addGroup(jpPanelFechaAsientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlFechaAsiento)
                            .addComponent(jtffeFechaAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jlCuenta.setText("Cuenta");

        jbBuscarCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.gif"))); // NOI18N
        jbBuscarCuenta.setPreferredSize(new java.awt.Dimension(25, 25));

        jlDocumento.setText("Documento");

        jlClave.setText("Clave");

        jbBuscarClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.gif"))); // NOI18N
        jbBuscarClave.setPreferredSize(new java.awt.Dimension(25, 25));

        jlConcepto.setText("Concepto");

        jtffConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtffConceptoActionPerformed(evt);
            }
        });

        jtfn2dImporte.setText("jTextFieldNumero2Decimales1");

        jlImporte.setText("Importe");

        jbOkApunte.setText("Ok");

        jbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SALIR.gif"))); // NOI18N
        jbSalir.setPreferredSize(new java.awt.Dimension(30, 30));

        jbAnularAsiento.setText("Anular Asiento");

        jbIvaAutomatico.setText("IVA Automático");

        jbMovimientos.setText("Movimientos");

        jbPrevisiones.setText("Previsiones");

        jlCuadre.setText("Cuadre");

        jtfnf2DCuadre.setText("jTextFieldNumero2Decimales1");
        jtfnf2DCuadre.setEnabled(false);
        jtfnf2DCuadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfnf2DCuadreActionPerformed(evt);
            }
        });

        jlNombreCuenta.setBackground(new java.awt.Color(153, 255, 255));

        jlTituloSaldo.setText("Saldo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpPanelFechaAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(131, 131, 131))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jtfnfCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jbBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jtfnfDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtfnfClave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(577, 577, 577))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbAnularAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jbIvaAutomatico, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addComponent(jbMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jbPrevisiones, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 34, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlNombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jlTituloSaldo)
                                .addGap(29, 29, 29)
                                .addComponent(jlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jlCuadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfnf2DCuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlCuenta)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(211, 211, 211)
                                        .addComponent(jbBuscarClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(108, 108, 108)
                                        .addComponent(jlDocumento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlClave)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlConcepto)
                                        .addGap(340, 340, 340)
                                        .addComponent(jlImporte))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jtffConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfn2dImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbOkApunte)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jpPanelFechaAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlNombreCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlCuadre)
                        .addComponent(jtfnf2DCuadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlTituloSaldo)
                        .addComponent(jlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlCuenta)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlClave)
                        .addComponent(jlDocumento)
                        .addComponent(jlConcepto))
                    .addComponent(jlImporte))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfnfCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfnfDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfnfClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbBuscarClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtffConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfn2dImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbOkApunte)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbIvaAutomatico)
                    .addComponent(jbMovimientos)
                    .addComponent(jbPrevisiones)
                    .addComponent(jbAnularAsiento))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtffConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtffConceptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtffConceptoActionPerformed

    private void jtfnf2DCuadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnf2DCuadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfnf2DCuadreActionPerformed
    
    // Mis variables de pantalla
    private javax.swing.JButton jbPrueba;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAnularAsiento;
    private javax.swing.JButton jbBuscarAsiento;
    private javax.swing.JButton jbBuscarClave;
    private javax.swing.JButton jbBuscarCuenta;
    private javax.swing.JButton jbIvaAutomatico;
    private javax.swing.JButton jbMovimientos;
    private javax.swing.JButton jbOkApunte;
    private javax.swing.JButton jbOkAsiento;
    private javax.swing.JButton jbPrevisiones;
    private javax.swing.JButton jbSalir;
    private javax.swing.JLabel jlClave;
    private javax.swing.JLabel jlConcepto;
    private javax.swing.JLabel jlCuadre;
    private javax.swing.JLabel jlCuenta;
    private javax.swing.JLabel jlDocumento;
    private javax.swing.JLabel jlFechaAsiento;
    private javax.swing.JLabel jlImporte;
    private javax.swing.JLabel jlNombreCuenta;
    private javax.swing.JLabel jlNumeroAsiento;
    private javax.swing.JLabel jlSaldo;
    private javax.swing.JLabel jlTituloSaldo;
    private javax.swing.JPanel jpPanelFechaAsiento;
    private util.JTextFieldFijo jtffConcepto;
    private util.JTextFieldFecha jtffeFechaAsiento;
    private util.JTextFieldNumero2Decimales jtfn2dImporte;
    private util.JTextFieldNumero2Decimales jtfnf2DCuadre;
    private util.JTextFieldNumeroFijo jtfnfAsiento;
    private util.JTextFieldNumeroFijo jtfnfClave;
    private util.JTextFieldNumeroFijo jtfnfCuenta;
    private util.JTextFieldNumeroFijo jtfnfDocumento;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
