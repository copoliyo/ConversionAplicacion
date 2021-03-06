/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import general.DatosComunes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mantenimientos.MantenimientoCuentasContables;

/**
 *
 * @author Txus
 */
public class TestMantenimientoCuentasContables extends javax.swing.JFrame {

    MantenimientoCuentasContables mcc = null;
    /**
     * Creates new form TestMantenimientoCuentasContables
     */
    public TestMantenimientoCuentasContables() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbMantenimientoCuentas = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbMantenimientoCuentas.setText("Mantenimiento Cuentas Contables");
        jbMantenimientoCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMantenimientoCuentasActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(jbMantenimientoCuentas)
                .addGap(99, 99, 99))
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jbSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jbMantenimientoCuentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jbSalir)
                .addGap(82, 82, 82))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbMantenimientoCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMantenimientoCuentasActionPerformed
        
        if(mcc == null)
            mcc = new MantenimientoCuentasContables(new javax.swing.JFrame(), true);
        else
            mcc.setVisible(true);
    }//GEN-LAST:event_jbMantenimientoCuentasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       new DatosComunes("MV");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TestMantenimentoFacturasEmitidas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(TestMantenimentoFacturasEmitidas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(TestMantenimentoFacturasEmitidas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
            /*
             try {
             UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance" + "Sahara" + "LookAndFeel");
             } catch (ClassNotFoundException ex) {
             Logger.getLogger(TestMantenimientoPrevisionesCobro.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
             Logger.getLogger(TestMantenimientoPrevisionesCobro.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
             Logger.getLogger(TestMantenimientoPrevisionesCobro.class.getName()).log(Level.SEVERE, null, ex);
             }*/
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestMantenimientoCuentasContables().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbMantenimientoCuentas;
    private javax.swing.JButton jbSalir;
    // End of variables declaration//GEN-END:variables
}
