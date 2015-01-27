/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientos;

import general.DatosComunes;
import indices.IndiceBancosSucursales;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;
import javax.swing.JOptionPane;
import tablas.IndiceBancos;
import util.Apariencia;
import util.BaseDatos;
import util.CuentaBancaria;
import util.Fecha;

/**
 *
 * @author Txus
 */
public class MantenimientoIndiceBancos extends util.EscapeDialog {
    
    private IndiceBancos indiceBancos;
    private CuentaBancaria cuentaBancaria;
    private IndiceBancosSucursales ibs = null;
    
    /**
     * Creates new form MantenimientoIndiceBancos
     */
    public MantenimientoIndiceBancos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargaInicial();
        cuentaBancaria = new CuentaBancaria();
        
        Vector<Component> order;
        order = new Vector<>(2);
        order.add(jtfnfCodigoBanco);
        order.add(jtffDescripcion);
        MyOFocusTraversalPolicy newPolicy = new MyOFocusTraversalPolicy(order);
        this.setFocusTraversalPolicy(newPolicy);
        
        this.setVisible(true);
    }

    private void borrarPantalla(){
        jtfnfCodigoBanco.setText("0");
        jtffDescripcion.setText("");
    }
    
    private void cargaInicial(){
        borrarPantalla();
        
        indiceBancos = new IndiceBancos();
        
        String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                      + "BCOIND_SUCURSAL = 0 "
                      + "ORDER BY BCOIND_BANCO ASC, BCOIND_SUCURSAL ASC LIMIT 1";
        
        indiceBancos.read(strSql);
        
        cargaDatos(indiceBancos);
    }
    
    private void cargaDatos(IndiceBancos ib){
        jtfnfCodigoBanco.setText(String.valueOf(ib.getBanco()));
        jtffDescripcion.setText(ib.getDescripcion());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCodigoBanco = new javax.swing.JLabel();
        jtfnfCodigoBanco = new util.JTextFieldNumeroFijo(4);
        jbBuscarBanco = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtffDescripcion = new util.JTextFieldFijo(36);
        jbSalir = new javax.swing.JButton();
        jbBorrar = new javax.swing.JButton();
        jbGrabar = new javax.swing.JButton();
        jbAtras = new javax.swing.JButton();
        jbAdelante = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento Indice de Bancos");

        jlCodigoBanco.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlCodigoBanco.setText("C�digo Banco");

        jtfnfCodigoBanco.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jtfnfCodigoBanco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnfCodigoBancoFocusLost(evt);
            }
        });
        jtfnfCodigoBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfnfCodigoBancoActionPerformed(evt);
            }
        });

        jbBuscarBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.gif"))); // NOI18N
        jbBuscarBanco.setPreferredSize(new java.awt.Dimension(30, 30));
        jbBuscarBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarBancoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel1.setText("Nombre");

        jtffDescripcion.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N

        jbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SALIR.gif"))); // NOI18N
        jbSalir.setPreferredSize(new java.awt.Dimension(30, 30));
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbBorrar.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbBorrar.setText("Borrar");
        jbBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarActionPerformed(evt);
            }
        });

        jbGrabar.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbGrabar.setText("Grabar");
        jbGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGrabarActionPerformed(evt);
            }
        });

        jbAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Atras.gif"))); // NOI18N
        jbAtras.setPreferredSize(new java.awt.Dimension(30, 30));
        jbAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtrasActionPerformed(evt);
            }
        });

        jbAdelante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Adelante.gif"))); // NOI18N
        jbAdelante.setPreferredSize(new java.awt.Dimension(30, 30));
        jbAdelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdelanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbBorrar)
                        .addGap(18, 18, 18)
                        .addComponent(jbGrabar)
                        .addGap(18, 18, 18)
                        .addComponent(jbAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlCodigoBanco)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfnfCodigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbBuscarBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtffDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbBuscarBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlCodigoBanco)
                                .addComponent(jtfnfCodigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtffDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
       this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbBuscarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarBancoActionPerformed
        
        if(ibs == null)
            ibs = new IndiceBancosSucursales();
        else
            ibs.setVisible(true);
        
        if(ibs.getBancoSucursal() != null){
           cuentaBancaria = ibs.getBancoSucursal();
           indiceBancos.read(Integer.valueOf(cuentaBancaria.getBanco()), 0);
           cargaDatos(indiceBancos);
        }
    }//GEN-LAST:event_jbBuscarBancoActionPerformed

    private void jbAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdelanteActionPerformed
        
        String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                      + "BCOIND_BANCO > " + Integer.valueOf(jtfnfCodigoBanco.getText().trim()) + " AND "
                      + "BCOIND_SUCURSAL = 0 "
                      + "ORDER BY BCOIND_BANCO ASC, BCOIND_SUCURSAL ASC LIMIT 1";
        
        if(indiceBancos.read(strSql) == true)
            cargaDatos(indiceBancos);
    }//GEN-LAST:event_jbAdelanteActionPerformed

    private void jbAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtrasActionPerformed
        
        String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                      + "BCOIND_BANCO < " + Integer.valueOf(jtfnfCodigoBanco.getText().trim()) + " AND "
                      + "BCOIND_SUCURSAL = 0 "
                      + "ORDER BY BCOIND_BANCO DESC, BCOIND_SUCURSAL ASC LIMIT 1";
        
        if(indiceBancos.read(strSql) == true)
            cargaDatos(indiceBancos);
    }//GEN-LAST:event_jbAtrasActionPerformed

    private void jbGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGrabarActionPerformed
        
        if (Integer.valueOf(jtfnfCodigoBanco.getText()) != 0) {
            indiceBancos.setEmpresa(DatosComunes.eEmpresa);
            indiceBancos.setBanco(Integer.valueOf(jtfnfCodigoBanco.getText()));
            indiceBancos.setSucursal(String.valueOf(""));
            indiceBancos.setDescripcion(jtffDescripcion.getText().trim());
            // En toda la tabla no se usa la provincia
            indiceBancos.setProvincia(0);
            if(indiceBancos.write() == true)
                Apariencia.mensajeInformativo(5, "Banco grabado correctamente."); 
        }
    }//GEN-LAST:event_jbGrabarActionPerformed

    private void jbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarActionPerformed

        int banco = Integer.valueOf(jtfnfCodigoBanco.getText());
         String strSql;
        
        if (banco > 0) {

            // Vamos a mirar si el banco tiene sucursales, si es asi, NO SE PUEDE BORRAR!
            strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                    + "BCOIND_BANCO = " + banco;

            if (BaseDatos.countRows(strSql) > 1) {
                Apariencia.mensajeInformativo(4, "<center>Banco con sucursales,<br>no se puede borrar!!!</center>");
            } else {

                if (indiceBancos.read(banco, 0) == true) {
                    // Damos la oportunidad de no borrar
                    Object[] opciones = {"Si", "No"};

                    int n = JOptionPane.showOptionDialog(this,
                            "<html><font size='4'><strong>"
                            + "Desea borrar el banco<br>"
                            + "Banco  : " + indiceBancos.getDescripcion() + "<br>"
                            + "C�digo : " + banco,
                            "Borrar factura Recibida",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, // Sin Icono personalizado.
                            opciones, // T�tulo de los botonoes
                            opciones[1]); // Bot�n por defecto.
                    //Si
                    if (n == 0) {
                        if (indiceBancos.delete(banco, "    ") > 0) {
                            Apariencia.mensajeInformativo(4, "Banco borrado.");

                            strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                                    + "BCOIND_BANCO > " + banco + " AND "
                                    + "BCOIND_SUCURSAL = 0 "
                                    + "ORDER BY BCOIND_BANCO ASC, BCOIND_SUCURSAL ASC LIMIT 1";

                            if (indiceBancos.read(strSql) == true) {
                                cargaDatos(indiceBancos);
                            }
                        } else {
                            Apariencia.mensajeInformativo(4, "No se ha podido borrar el Banco!!!");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jbBorrarActionPerformed

    private void jtfnfCodigoBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnfCodigoBancoActionPerformed
        
        int banco = Integer.valueOf(jtfnfCodigoBanco.getText().trim());
        
        if (banco != 0) {            
            if (indiceBancos.read(banco, 0) == true) {
                cargaDatos(indiceBancos);
            } else {
                jtffDescripcion.setText("");
            }
        }
    }//GEN-LAST:event_jtfnfCodigoBancoActionPerformed

    private void jtfnfCodigoBancoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnfCodigoBancoFocusLost
        
        int banco = Integer.valueOf(jtfnfCodigoBanco.getText().trim());
        
        if (banco != 0) {
            if (indiceBancos.read(banco, 0) == true) {
                cargaDatos(indiceBancos);
            } else {
                jtffDescripcion.setText("");
            }
        }
    }//GEN-LAST:event_jtfnfCodigoBancoFocusLost

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbAdelante;
    private javax.swing.JButton jbAtras;
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbBuscarBanco;
    private javax.swing.JButton jbGrabar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JLabel jlCodigoBanco;
    private util.JTextFieldFijo jtffDescripcion;
    private util.JTextFieldNumeroFijo jtfnfCodigoBanco;
    // End of variables declaration//GEN-END:variables
}