/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import general.DatosComunes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import util.Apariencia;
import util.Cadena;
import util.Fecha;
import util.LineaMovimientoContable;
import util.MovimientosContables;

/**
 *
 * @author Txus
 */
public class EntradaAsientosContables extends util.EscapeDialog implements PropertyChangeListener {

    JTable jtApuntes;
    JScrollPane spApuntes;
    DefaultTableCellRenderer tcr;
    TableCellRenderer tcr2;
    
    // Variables globales para cada asiento
    int asiento = 0;
    int fecha = 0;
    boolean asientoNuevo = true;
    int numeroApunte = 0;
    int numeroTotalApuntes = 0;

    Vector<LineaMovimientoContable> vectorLineaMovimientos;
    
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
        estableceVisibilidadInicial();
        
        Vector<Component> order;
        order = new Vector<>(4);
        order.add(jtffeFechaAsiento);
        order.add(jtfnfAsiento);
        order.add(jbOkAsiento);
        
        MyOFocusTraversalPolicy newPolicy = new MyOFocusTraversalPolicy(order);
        this.setFocusTraversalPolicy(newPolicy);
        
        jtffeFechaAsiento.setText(Cadena.fechaAcadena(Fecha.fechaDia()));
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

        // Hace que el fondo sea completamente blanco
        jtApuntes.setFillsViewportHeight(true);
        
        // Creamos un JscrollPane y le agregamos la JTable
        spApuntes = new JScrollPane(jtApuntes);
        // Si quisieramos barra horizontal, descomentar la linea siguiente
        spApuntes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // Agregamos el JScrollPane al contenedor
        spApuntes.setBounds(10, 80, 830, 250);
        spApuntes.setFont(Apariencia.cambiaFuente());
        spApuntes.setBackground(Color.yellow);
        getContentPane().add(spApuntes);
        
        
    }

    private void estableceVisibilidadInicial(){
    
        jlCuenta.setEnabled(false);
        jlDocumento.setEnabled(false);
        jlClave.setEnabled(false);
        jlConcepto.setEnabled(false);
        jlImporte.setEnabled(false);
        jtfnfCuenta.setEnabled(false);
        jbBuscarCuenta.setEnabled(false);
        jtfnfDocumento.setEnabled(false);
        jtfnfClave.setEnabled(false);
        jbBuscarClave.setEnabled(false);
        jtffConcepto.setEnabled(false);
        jtfn2DImporte.setEnabled(false);
        jbOkApunte.setEnabled(false);
        jlNombreCuenta.setEnabled(false);
        jlTituloSaldo.setEnabled(false);
        jlSaldo.setEnabled(false);
        jlCuadre.setEnabled(false);
        jtfnf2DCuadre.setEnabled(false);
        jbAnularAsiento.setEnabled(false);
        jbIvaAutomatico.setEnabled(false);
        jbMovimientos.setEnabled(false);
        jbPrevisiones.setEnabled(false);
        jbMasApuntes.setEnabled(false);
        jbAnularApunte.setEnabled(false);
        jbModificarApunte.setEnabled(false);
        
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
        jtfn2DImporte = new util.JTextFieldNumero2Decimales();
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
        jbMasApuntes = new javax.swing.JButton();
        jbAnularApunte = new javax.swing.JButton();
        jbModificarApunte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrada de Asientos Contables");
        setResizable(false);

        jpPanelFechaAsiento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlFechaAsiento.setText("Fecha");

        jlNumeroAsiento.setText("Asiento");

        jbBuscarAsiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.gif"))); // NOI18N
        jbBuscarAsiento.setPreferredSize(new java.awt.Dimension(30, 30));

        jtffeFechaAsiento.setText("00.00.00");

        jtfnfAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfnfAsientoActionPerformed(evt);
            }
        });

        jbOkAsiento.setText("Ok");
        jbOkAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOkAsientoActionPerformed(evt);
            }
        });
        jbOkAsiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbOkAsientoKeyPressed(evt);
            }
        });

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

        jtfn2DImporte.setText("jTextFieldNumero2Decimales1");

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

        jbMasApuntes.setText("Más Apuntes");

        jbAnularApunte.setText("Anular Apunte");

        jbModificarApunte.setText("Modificar Apunte");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jpPanelFechaAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jlNombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jlTituloSaldo)
                        .addGap(29, 29, 29)
                        .addComponent(jlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jlCuadre)
                        .addGap(10, 10, 10)
                        .addComponent(jtfnf2DCuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jlCuenta)
                        .addGap(108, 108, 108)
                        .addComponent(jlDocumento)
                        .addGap(10, 10, 10)
                        .addComponent(jlClave)
                        .addGap(43, 43, 43)
                        .addComponent(jlConcepto)
                        .addGap(340, 340, 340)
                        .addComponent(jlImporte))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbAnularAsiento))
                            .addComponent(jtfnfCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jbBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jtfnfDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jtfnfClave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jbBuscarClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jtffConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jtfn2DImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jbOkApunte))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbIvaAutomatico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbMasApuntes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbAnularApunte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbModificarApunte)
                                .addGap(18, 18, 18)
                                .addComponent(jbMovimientos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbPrevisiones, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jpPanelFechaAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlNombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfnf2DCuadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlTituloSaldo)
                            .addComponent(jlCuadre))))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlCuenta)
                    .addComponent(jlDocumento)
                    .addComponent(jlClave)
                    .addComponent(jlConcepto)
                    .addComponent(jlImporte))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfnfCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfnfDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfnfClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscarClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbOkApunte)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtffConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfn2DImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbAnularAsiento)
                        .addComponent(jbIvaAutomatico)
                        .addComponent(jbMasApuntes)
                        .addComponent(jbAnularApunte)
                        .addComponent(jbModificarApunte)
                        .addComponent(jbMovimientos)
                        .addComponent(jbPrevisiones))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtffConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtffConceptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtffConceptoActionPerformed

    private void jtfnf2DCuadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnf2DCuadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfnf2DCuadreActionPerformed

    private void jtfnfAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnfAsientoActionPerformed
        
        fecha = jtffeFechaAsiento.getFechaAAAAMMDD();
                
        if(jtfnfAsiento.getText().trim().length() == 0)
            jtfnfAsiento.setText("0");
        
        asiento = Integer.valueOf(jtfnfAsiento.getText().trim());

        // Si el campo de asiento está vacío o no existe el asiento que pretendemos visualizar, busca el primero libre.
        if (asiento == 0 || util.MovimientosContables.existeMovimiento(DatosComunes.centroCont, fecha, asiento) == false) {
            // Ponemos el número del primer asiento libre
            jtfnfAsiento.setText(String.valueOf(util.MovimientosContables.buscaPrimeroLibreEnDia(DatosComunes.centroCont, fecha)));
            // Borramos las lineas del asiento, está vacío
            modeloTabla.setRowCount(0);
            
        } else {
            if (util.MovimientosContables.existeMovimiento(DatosComunes.centroCont, fecha, asiento) == true) {
                vectorLineaMovimientos = MovimientosContables.leeAsiento(DatosComunes.centroCont, fecha, asiento);
                displayLineasAsiento();
                System.out.println("Apuntes en el asiento: " + vectorLineaMovimientos.size());
            }
        }
        
    }//GEN-LAST:event_jtfnfAsientoActionPerformed

    private void jbOkAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOkAsientoActionPerformed
        fecha = jtffeFechaAsiento.getFechaAAAAMMDD();
                
        if(jtfnfAsiento.getText().trim().length() == 0)
            jtfnfAsiento.setText("0");
        
        asiento = Integer.valueOf(jtfnfAsiento.getText().trim());

        // Si el campo de asiento está vacío o no existe el asiento que pretendemos visualizar, busca el primero libre.
        if (asiento == 0 || util.MovimientosContables.existeMovimiento(DatosComunes.centroCont, fecha, asiento) == false) {
            // Ponemos el número del primer asiento libre
            jtfnfAsiento.setText(String.valueOf(util.MovimientosContables.buscaPrimeroLibreEnDia(DatosComunes.centroCont, fecha)));
            // Borramos las lineas del asiento, está vacío
            modeloTabla.setRowCount(0);
            
        } else {
            if (util.MovimientosContables.existeMovimiento(DatosComunes.centroCont, fecha, asiento) == true) {
                vectorLineaMovimientos = MovimientosContables.leeAsiento(DatosComunes.centroCont, fecha, asiento);
                displayLineasAsiento();
                System.out.println("Apuntes en el asiento: " + vectorLineaMovimientos.size());
            }
        }
        
        if(fecha <= DatosComunes.fecUltCierre || fecha < DatosComunes.fecUltRegpro){
           util.Apariencia.mensajeInformativo(9, "Fecha no permitida!!!");
        }
        
        if(fecha == DatosComunes.fecUltRegpro && asiento != 99997){
           util.Apariencia.mensajeInformativo(9, "Fecha no permitida!!!"); 
        }
    }//GEN-LAST:event_jbOkAsientoActionPerformed

    private void jbOkAsientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbOkAsientoKeyPressed
        fecha = jtffeFechaAsiento.getFechaAAAAMMDD();
                
        if(jtfnfAsiento.getText().trim().length() == 0)
            jtfnfAsiento.setText("0");
        
        asiento = Integer.valueOf(jtfnfAsiento.getText().trim());

        // Si el campo de asiento está vacío o no existe el asiento que pretendemos visualizar, busca el primero libre.
        if (asiento == 0 || util.MovimientosContables.existeMovimiento(DatosComunes.centroCont, fecha, asiento) == false) {
            // Ponemos el número del primer asiento libre
            jtfnfAsiento.setText(String.valueOf(util.MovimientosContables.buscaPrimeroLibreEnDia(DatosComunes.centroCont, fecha)));
            // Borramos las lineas del asiento, está vacío
            modeloTabla.setRowCount(0);
            
        } else {
            if (util.MovimientosContables.existeMovimiento(DatosComunes.centroCont, fecha, asiento) == true) {
                vectorLineaMovimientos = MovimientosContables.leeAsiento(DatosComunes.centroCont, fecha, asiento);
                displayLineasAsiento();
                System.out.println("Apuntes en el asiento: " + vectorLineaMovimientos.size());
            }
        }
        
        if(fecha <= DatosComunes.fecUltCierre || fecha < DatosComunes.fecUltRegpro){
           util.Apariencia.mensajeInformativo(9, "Fecha no permitida!!!");
        }
        
        if(fecha == DatosComunes.fecUltRegpro && asiento != 99997){
           util.Apariencia.mensajeInformativo(9, "Fecha no permitida!!!"); 
        }
    }//GEN-LAST:event_jbOkAsientoKeyPressed
    
    private void displayLineasAsiento(){
        
        Object fila[] = {"", "", "", "", "", "", ""};

        double cuadre = 0.0;
        
        LineaMovimientoContable lmc = new LineaMovimientoContable();
        
        modeloTabla.setRowCount(0);
        if(vectorLineaMovimientos.size() > 0){
            for(int i = 0; i < vectorLineaMovimientos.size(); i++){
                lmc = vectorLineaMovimientos.elementAt(i);
                
                fila[Columna.APUNTE.value] = String.valueOf(lmc.getApunte());
                fila[Columna.CUENTA.value] = Cadena.formateaCuentaContable(lmc.getCuenta());                
                fila[Columna.DOCUMENTO.value] = String.valueOf(lmc.getDocumento());                
                fila[Columna.CLAVE.value] = String.valueOf(lmc.getClave());
                fila[Columna.CONCEPTO.value] = lmc.getConcepto();
                if(lmc.getClave() < 50){
                    fila[Columna.DEBE.value] = Cadena.formatoConComaDecimal(lmc.getImporte());
                    fila[Columna.HABER.value] = "";
                    cuadre += lmc.getImporte();
                }else{
                    fila[Columna.DEBE.value] = "";
                    fila[Columna.HABER.value] = Cadena.formatoConComaDecimal(lmc.getImporte());                    
                    cuadre -= lmc.getImporte();
                }
                modeloTabla.addRow(fila);
            }
        }
        
        jtfnf2DCuadre.setText(Cadena.formatoConComaDecimal(cuadre));
    }
    
    
    // Mis variables de pantalla
    private javax.swing.JButton jbPrueba;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAnularApunte;
    private javax.swing.JButton jbAnularAsiento;
    private javax.swing.JButton jbBuscarAsiento;
    private javax.swing.JButton jbBuscarClave;
    private javax.swing.JButton jbBuscarCuenta;
    private javax.swing.JButton jbIvaAutomatico;
    private javax.swing.JButton jbMasApuntes;
    private javax.swing.JButton jbModificarApunte;
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
    private util.JTextFieldNumero2Decimales jtfn2DImporte;
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

    public static class MyOFocusTraversalPolicy
            extends FocusTraversalPolicy {

        Vector<Component> order;

        public MyOFocusTraversalPolicy(Vector<Component> order) {
            this.order = new Vector<Component>(order.size());
            this.order.addAll(order);
        }

        public Component getComponentAfter(Container focusCycleRoot,
                Component aComponent) {
            int idx = (order.indexOf(aComponent) + 1) % order.size();
            return order.get(idx);
        }

        @Override
        public Component getComponentBefore(Container focusCycleRoot,
                Component aComponent) {
            int idx = order.indexOf(aComponent) - 1;
            if (idx < 0) {
                idx = order.size() - 1;
            }
            return order.get(idx);
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
            return order.get(0);
        }

        public Component getLastComponent(Container focusCycleRoot) {
            return order.lastElement();
        }

        public Component getFirstComponent(Container focusCycleRoot) {
            return order.get(0);
        }
    }
    
    

}
