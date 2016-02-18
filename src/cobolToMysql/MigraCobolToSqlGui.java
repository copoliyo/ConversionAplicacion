/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobolToMysql;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.DefaultListModel;

/**
 *
 * @author Txus
 */
public class MigraCobolToSqlGui extends javax.swing.JFrame {

    /**
     * Creates new form MigraCobolToSqlGui
     */
    DefaultListModel modeloEmpresasDisponibles = new DefaultListModel();
    DefaultListModel modeloFicherosPosibles = new DefaultListModel();
    DefaultListModel modeloFicherosAConvertir = new DefaultListModel();
    
    public MigraCobolToSqlGui() {
        initComponents();
        jtffDirectorioFicherosDatos.setText("C:\\DENAORIGINAL");
        jtffDirectorioFicherosXfXs.setText("C:\\CONVERSION");
        cargaFicherosEnModeloFicherosPosibles(); 
        cargaEmpresasDisponibles();
    }
    
    private void cargaFicherosEnModeloFicherosPosibles(){
        String strFicherosPosibles[] = {"CPCALL", "CPPOST", "CPPAIS", "CPPOBL", "CPPROV", 
            "2DADIR", "ABNSTK", "ACCCEN", "ACCESO", "ACCEST", "ACCHST", "ACCPRG", "ACCUSU", "AGENDA", "ALBPRV",
            "ARTCEN", "ARTCLO", "ARTEAN", "ARTEQV", "ASTAUC", "ASTAUT", "BANCOS", "BCOIND", "CACEST", "CALVAR",
            "CARTCA", "CARTCD", "CARTCF", "CEMAGF", "CEPTOS", "CLCEPC", "CLIENT", "CLITES", "COBROS", "COMPON",
            "CONTAB", "CPZONA", "DEBHAB", "EFECOB", "EFEPAG", "EXTFIN", "FACAUT", "FACCEP", "FACEMI", "FACLIN",
            "FACREC", "FORCOB", "GESCEP", "GESCPN", "INDCAR", "INDCEN", "INDINF", "LINVAR", "MGFGST", "MOACEP",
            "MOCCEP", "MOVART", "MOVCON", "MOVIMP", "NOTASF", "OFERTA", "PARTIE", "PROVAC", "REPRES", "RUTCLI",
            "SATREP", "SATSAT", "TICKET", "TICKOT", "TIPAGO", "TIPCLI", "TITCOB", "TOTCOB", "TOTPAG", "ZONCLI",
            "SISTEM"};
        
        for(int i = 0; i < strFicherosPosibles.length; i++)
            modeloFicherosPosibles.addElement(strFicherosPosibles[i]);
        
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jFileChooser1 = new javax.swing.JFileChooser();
        jlDirectorioFicherosDatos = new javax.swing.JLabel();
        jlDirectorioFicherosXfXs = new javax.swing.JLabel();
        jtffDirectorioFicherosDatos = new util.JTextFieldFijo(255);
        jtffDirectorioFicherosXfXs = new util.JTextFieldFijo(255);
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFicherosPosibles = new javax.swing.JList<>();
        jlFicherosPosibles = new javax.swing.JLabel();
        jlFicherosQueSeVanAConvertir = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListFicherosAConvertir = new javax.swing.JList<>(modeloFicherosAConvertir);
        jlEmpresasDisponibles = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListEmpresasDisponibles = new javax.swing.JList<>(modeloEmpresasDisponibles);
        jlEmpresaAConvertir = new javax.swing.JLabel();
        jtffEmpresaAConvertir = new util.JTextFieldFijo(2);
        jbPasarTodos = new javax.swing.JButton();
        jbQuitarTodos = new javax.swing.JButton();
        jbPasarUno = new javax.swing.JButton();
        jbQuitarUno = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jbIniciarConversion = new javax.swing.JButton();
        jbFileChooserFicDatos = new javax.swing.JButton();
        jbFileChooserFicherosXfXs = new javax.swing.JButton();
        jlSchema = new javax.swing.JLabel();
        jtfSchema = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlDirectorioFicherosDatos.setText("Directorio Ficheros de Datos");

        jlDirectorioFicherosXfXs.setText("Directorio Ficheros XF/XS");

        jtffDirectorioFicherosDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtffDirectorioFicherosDatosActionPerformed(evt);
            }
        });

        jListFicherosPosibles.setModel(modeloFicherosPosibles);
        jListFicherosPosibles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListFicherosPosibles);

        jlFicherosPosibles.setText("Ficheros que se pueden convertir");

        jlFicherosQueSeVanAConvertir.setText("Ficheros que se van a convertir");

        jListFicherosAConvertir.setModel(modeloFicherosAConvertir);
        jListFicherosAConvertir.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListFicherosAConvertir);

        jlEmpresasDisponibles.setText("Empresas Disponibles");

        jListEmpresasDisponibles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jListEmpresasDisponibles, org.jdesktop.beansbinding.ELProperty.create("${selectedValue}"), jListEmpresasDisponibles, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jListEmpresasDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListEmpresasDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListEmpresasDisponibles);

        jlEmpresaAConvertir.setText("Empresa a Convertir:");

        jbPasarTodos.setText("Pasar Todos ->");
        jbPasarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPasarTodosActionPerformed(evt);
            }
        });

        jbQuitarTodos.setText("Quitar Todos <-");
        jbQuitarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuitarTodosActionPerformed(evt);
            }
        });

        jbPasarUno.setText("Pasar Uno ->");
        jbPasarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPasarUnoActionPerformed(evt);
            }
        });

        jbQuitarUno.setText("Quitar Uno <-");
        jbQuitarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuitarUnoActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbIniciarConversion.setText("Iniciar Conversi�n");

        jbFileChooserFicDatos.setText("...");

        jbFileChooserFicherosXfXs.setText("...");

        jlSchema.setText("Schema:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDirectorioFicherosDatos)
                            .addComponent(jlDirectorioFicherosXfXs))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbFileChooserFicDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbFileChooserFicherosXfXs, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtffDirectorioFicherosXfXs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtffDirectorioFicherosDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jbIniciarConversion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlSchema)
                                                .addGap(26, 26, 26)
                                                .addComponent(jtfSchema))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jlEmpresaAConvertir)
                                                .addGap(18, 18, 18)
                                                .addComponent(jtffEmpresaAConvertir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 116, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlEmpresasDisponibles)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlFicherosPosibles)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jbQuitarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbPasarUno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbPasarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbQuitarUno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSalir))
                            .addComponent(jlFicherosQueSeVanAConvertir))
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDirectorioFicherosDatos)
                    .addComponent(jtffDirectorioFicherosDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbFileChooserFicDatos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDirectorioFicherosXfXs)
                    .addComponent(jtffDirectorioFicherosXfXs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbFileChooserFicherosXfXs))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jlEmpresasDisponibles))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlFicherosPosibles)
                            .addComponent(jlFicherosQueSeVanAConvertir))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlEmpresaAConvertir)
                                    .addComponent(jtffEmpresaAConvertir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlSchema)
                                    .addComponent(jtfSchema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jbPasarTodos, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jbQuitarTodos)
                        .addGap(18, 18, 18)
                        .addComponent(jbPasarUno)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 13, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jbIniciarConversion)
                                    .addComponent(jbSalir)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbQuitarUno)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtffDirectorioFicherosDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtffDirectorioFicherosDatosActionPerformed
        //String rutaFicherosCobol = jtffDirectorioFicherosDatos.getText().trim();
        
        cargaEmpresasDisponibles();                
    }//GEN-LAST:event_jtffDirectorioFicherosDatosActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jListEmpresasDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListEmpresasDisponiblesMouseClicked
        jtffEmpresaAConvertir.setText(modeloEmpresasDisponibles.get(jListEmpresasDisponibles.getSelectedIndex()).toString());        
    }//GEN-LAST:event_jListEmpresasDisponiblesMouseClicked

    private void jbPasarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPasarTodosActionPerformed
        
        modeloFicherosAConvertir.clear();
        
        for(int i = 0; i < modeloFicherosPosibles.size(); i++){
            modeloFicherosAConvertir.addElement(modeloFicherosPosibles.get(i).toString());
        }                
    }//GEN-LAST:event_jbPasarTodosActionPerformed

    private void jbQuitarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuitarTodosActionPerformed
        
        modeloFicherosAConvertir.clear();
    }//GEN-LAST:event_jbQuitarTodosActionPerformed

    private void jbPasarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPasarUnoActionPerformed
        
        String strFicheroAPasar = modeloFicherosPosibles.get(jListFicherosPosibles.getSelectedIndex()).toString();
        
        if(!modeloFicherosAConvertir.contains(strFicheroAPasar))
            modeloFicherosAConvertir.addElement(strFicheroAPasar);
    }//GEN-LAST:event_jbPasarUnoActionPerformed

    private void jbQuitarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuitarUnoActionPerformed
        
        int indiceSeleccionado = jListFicherosAConvertir.getSelectedIndex();
        if(indiceSeleccionado != -1)
            modeloFicherosAConvertir.removeElementAt(indiceSeleccionado);
    }//GEN-LAST:event_jbQuitarUnoActionPerformed

    private void cargaEmpresasDisponibles(){
        String rutaFicherosCobol = jtffDirectorioFicherosDatos.getText().trim();
    
        String nombreFichero = "";
        if (rutaFicherosCobol.length() > 0) {
            File dir = new File(rutaFicherosCobol);
            if (dir.exists()) {
                File[] ficheros = dir.listFiles();

                if (ficheros == null) {
                    System.out.println("No hay ficheros en el directorio especificado");
                } else {
                    modeloEmpresasDisponibles.clear();
                    for (int x = 0; x < ficheros.length; x++) {
                        nombreFichero = ficheros[x].toString().toUpperCase();
                        if (nombreFichero.endsWith("SISTEM")) {
                            //System.out.println(ficheros[x]);                            
                            modeloEmpresasDisponibles.addElement(nombreFichero.subSequence(nombreFichero.lastIndexOf("\\") + 1, nombreFichero.lastIndexOf("\\") + 3));
                        }
                    }
                }
            } else {
                modeloEmpresasDisponibles.clear();
                modeloEmpresasDisponibles.addElement("Directorio Inv�lido!!!");
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MigraCobolToSqlGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MigraCobolToSqlGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MigraCobolToSqlGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MigraCobolToSqlGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MigraCobolToSqlGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JList<String> jListEmpresasDisponibles;
    private javax.swing.JList<String> jListFicherosAConvertir;
    private javax.swing.JList<String> jListFicherosPosibles;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbFileChooserFicDatos;
    private javax.swing.JButton jbFileChooserFicherosXfXs;
    private javax.swing.JButton jbIniciarConversion;
    private javax.swing.JButton jbPasarTodos;
    private javax.swing.JButton jbPasarUno;
    private javax.swing.JButton jbQuitarTodos;
    private javax.swing.JButton jbQuitarUno;
    private javax.swing.JButton jbSalir;
    private javax.swing.JLabel jlDirectorioFicherosDatos;
    private javax.swing.JLabel jlDirectorioFicherosXfXs;
    private javax.swing.JLabel jlEmpresaAConvertir;
    private javax.swing.JLabel jlEmpresasDisponibles;
    private javax.swing.JLabel jlFicherosPosibles;
    private javax.swing.JLabel jlFicherosQueSeVanAConvertir;
    private javax.swing.JLabel jlSchema;
    private javax.swing.JTextField jtfSchema;
    private util.JTextFieldFijo jtffDirectorioFicherosDatos;
    private util.JTextFieldFijo jtffDirectorioFicherosXfXs;
    private util.JTextFieldFijo jtffEmpresaAConvertir;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}