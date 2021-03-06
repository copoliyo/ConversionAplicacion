/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import general.DatosComunes;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import procesos.EntradaAsientosContables;

/**
 *
 * @author Txus
 */
public class TestEntradaAsientos extends javax.swing.JFrame {

    /**
     * Creates new form TestEntradaAsientos
     */
    public TestEntradaAsientos() {
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

        jbTestEntradaAsientos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbTestEntradaAsientos.setText("Test Entrada Asientos");
        jbTestEntradaAsientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTestEntradaAsientosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jbTestEntradaAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jbTestEntradaAsientos)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbTestEntradaAsientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTestEntradaAsientosActionPerformed
        EntradaAsientosContables eac = new EntradaAsientosContables(new javax.swing.JFrame(), true);
        if(eac != null){
            eac.dispose();
            eac = null;
        }
    }//GEN-LAST:event_jbTestEntradaAsientosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new DatosComunes("AL");
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                //if ("Windows Classic".equals(info.getName())) {    
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
                        UIDefaults defaults = lookAndFeel.getDefaults();
                        defaults.put("ScrollBar.minimumThumbSize", new Dimension(30, 30));
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
                EntradaAsientosContables eac = new EntradaAsientosContables(new javax.swing.JFrame(), true);
                if (eac != null) {
                    eac.dispose();
                    eac = null;
                }
                System.exit(0);
                //new TestEntradaAsientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbTestEntradaAsientos;
    // End of variables declaration//GEN-END:variables
}
