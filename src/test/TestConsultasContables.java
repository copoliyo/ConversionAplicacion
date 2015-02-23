/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import consultas.ConsultaCuentas;
import consultas.ConsultaPlanContable;
import general.DatosComunes;
import indices.IndiceMovimientosContables;
import indices.IndiceMovimientosContablesPaginado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import procesos.EntradaAsientosContables;
/**
 *
 * @author Txus
 */
public class TestConsultasContables extends javax.swing.JFrame {

    ConsultaCuentas consultaCuentas = null;
    final ConsultaPlanContable consultaPlanContable = new ConsultaPlanContable(new JFrame(), true);
    /**
     * Creates new form TestConsultaCuentas
     */
    public TestConsultasContables() {
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

        jbTestConsultaCuentas = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jbConsultaPlanContable = new javax.swing.JButton();
        jbIndiceMovimientosContables = new javax.swing.JButton();
        jbIndiceMovContablesPaginado = new javax.swing.JButton();
        jbEntradaAsientosContables = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbTestConsultaCuentas.setText("Consulta de Cuentas");
        jbTestConsultaCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTestConsultaCuentasActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbConsultaPlanContable.setText("Consulta Plan Contable");
        jbConsultaPlanContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConsultaPlanContableActionPerformed(evt);
            }
        });

        jbIndiceMovimientosContables.setText("Indice Movimientos Contables");
        jbIndiceMovimientosContables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbIndiceMovimientosContablesActionPerformed(evt);
            }
        });

        jbIndiceMovContablesPaginado.setText("Indice Movimientos Contables Paginado");
        jbIndiceMovContablesPaginado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbIndiceMovContablesPaginadoActionPerformed(evt);
            }
        });

        jbEntradaAsientosContables.setText("Entrada Asientos Contables");
        jbEntradaAsientosContables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEntradaAsientosContablesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbConsultaPlanContable)
                            .addComponent(jbTestConsultaCuentas)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jbIndiceMovimientosContables))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jbIndiceMovContablesPaginado))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jbSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jbEntradaAsientosContables)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jbTestConsultaCuentas)
                .addGap(18, 18, 18)
                .addComponent(jbConsultaPlanContable)
                .addGap(18, 18, 18)
                .addComponent(jbIndiceMovimientosContables)
                .addGap(18, 18, 18)
                .addComponent(jbIndiceMovContablesPaginado)
                .addGap(18, 18, 18)
                .addComponent(jbEntradaAsientosContables)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jbSalir)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbTestConsultaCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTestConsultaCuentasActionPerformed

        if(consultaCuentas == null)
            consultaCuentas = new ConsultaCuentas(new javax.swing.JFrame(), true);
        else{
            consultaCuentas.setVisible(true);
        }
    }//GEN-LAST:event_jbTestConsultaCuentasActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
    
            System.exit(0);
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbConsultaPlanContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConsultaPlanContableActionPerformed
        consultaPlanContable.setVisible(true);
        /*
        if(consultaPlanContable == null){
            consultaPlanContable = new ConsultaPlanContable(new javax.swing.JFrame(), true);
            consultaPlanContable.setVisible(true);
        }else{
            consultaPlanContable.setVisible(true);
        } */       
    }//GEN-LAST:event_jbConsultaPlanContableActionPerformed

    private void jbIndiceMovimientosContablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIndiceMovimientosContablesActionPerformed
        
        IndiceMovimientosContables imc = new IndiceMovimientosContables();
        if(imc != null)
            imc = null;
        
        
    }//GEN-LAST:event_jbIndiceMovimientosContablesActionPerformed

    private void jbIndiceMovContablesPaginadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIndiceMovContablesPaginadoActionPerformed
        
        IndiceMovimientosContablesPaginado imcp = new IndiceMovimientosContablesPaginado("");
        if(imcp != null)
            imcp = null;
    }//GEN-LAST:event_jbIndiceMovContablesPaginadoActionPerformed

    private void jbEntradaAsientosContablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEntradaAsientosContablesActionPerformed
        EntradaAsientosContables eac = new EntradaAsientosContables(new javax.swing.JFrame(), true);
        if(eac != null){
            eac.dispose();
            eac = null;
        }
    }//GEN-LAST:event_jbEntradaAsientosContablesActionPerformed

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
                new TestConsultasContables().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbConsultaPlanContable;
    private javax.swing.JButton jbEntradaAsientosContables;
    private javax.swing.JButton jbIndiceMovContablesPaginado;
    private javax.swing.JButton jbIndiceMovimientosContables;
    private javax.swing.JButton jbSalir;
    private javax.swing.JButton jbTestConsultaCuentas;
    // End of variables declaration//GEN-END:variables
}
