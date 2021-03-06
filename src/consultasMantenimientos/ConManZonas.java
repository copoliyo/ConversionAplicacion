/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultasMantenimientos;

import general.DatosComunes;
import general.MysqlConnect;
import indices.IndiceAcumuladosEstadisticos;
import indices.IndiceZonasCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tablas.Representante;
import tablas.ZonaCliente;
import util.Apariencia;
import util.BaseDatos;
import util.EscapeDialog;

/**
 *
 * @author Txus
 */
public class ConManZonas extends EscapeDialog {
    // Con esta variable definimos si estamos en una consulta (TRUE) o en
    // un mantenimiento (FALSE). Nos servir� para tener un s�lo programa
    // para algunas Consultas/Mantenimientos que pueden compartir las 
    // mismas pantallas.

    private static boolean consulta;
    private static boolean enCreacion = false;

    IndiceZonasCliente indiceZonasCliente = null;
    private static IndiceZonasCliente izc = null;

    public static ResultSet rs = null;
    public static MysqlConnect m = null;

    public ZonaCliente zona = new ZonaCliente();

    // Definiciones de componentes de pantalla
    public JFrame frameMenu = null;


    /**
     * Creates new form ConManZonas
     *
     * @param parent
     * @param modal
     */
    public ConManZonas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ConManZonas(boolean consultaOmantenimiento) {
        ConManZonas.consulta = consultaOmantenimiento;
        //this.consulta = false;
        m = MysqlConnect.getDbCon();
        initComponents();
        borrarPantalla();
        cargaInicial();
        this.setVisible(true);
    }

    public ConManZonas(JFrame parentFrame, boolean consultaOmantenimiento) {
        frameMenu = parentFrame;
        ConManZonas.consulta = consultaOmantenimiento;
        //this.consulta = false;
        m = MysqlConnect.getDbCon();
        initComponents();
        borrarPantalla();
        cargaInicial();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCodigo = new javax.swing.JLabel();
        jtfnfCodigo = new util.JTextFieldNumeroFijo(2);
        jbBuscar = new javax.swing.JButton();
        jlCentro = new javax.swing.JLabel();
        jtfnfCentro = new util.JTextFieldNumeroFijo(3);
        jlNombre = new javax.swing.JLabel();
        jtffNombre = new util.JTextFieldFijo(30);
        jbSalir = new javax.swing.JButton();
        jbPresupuesto = new javax.swing.JButton();
        jbEstadisticas = new javax.swing.JButton();
        jbBorrar = new javax.swing.JButton();
        jbGrabar = new javax.swing.JButton();
        jbAtras = new javax.swing.JButton();
        jbAdelante = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtfnfRepresentante = new util.JTextFieldNumeroFijo(4);
        jLabel2 = new javax.swing.JLabel();
        jlNombreRepresentante = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento Zonas Comerciales");

        jlCodigo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlCodigo.setText("C�digo");

        jtfnfCodigo.setColumns(2);
        jtfnfCodigo.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jtfnfCodigo.setName("jtfnfCodigo"); // NOI18N
        jtfnfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfnfCodigoActionPerformed(evt);
            }
        });

        jbBuscar.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.gif"))); // NOI18N
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jlCentro.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlCentro.setText("Centro");

        jtfnfCentro.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N

        jlNombre.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jlNombre.setText("Nombre");

        jtffNombre.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N

        jbSalir.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SALIR.gif"))); // NOI18N
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbPresupuesto.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbPresupuesto.setText("Presupuesto");

        jbEstadisticas.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbEstadisticas.setText("Estad�sticas");
        jbEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEstadisticasActionPerformed(evt);
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

        jbAtras.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Atras.gif"))); // NOI18N
        jbAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtrasActionPerformed(evt);
            }
        });

        jbAdelante.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jbAdelante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Adelante.gif"))); // NOI18N
        jbAdelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdelanteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel1.setText("Representante");

        jtfnfRepresentante.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jtfnfRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfnfRepresentanteActionPerformed(evt);
            }
        });

        jlNombreRepresentante.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlCodigo)
                            .addComponent(jlNombre))
                        .addGap(65, 65, 65)
                        .addComponent(jtfnfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlCentro)
                        .addGap(18, 18, 18)
                        .addComponent(jtfnfCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfnfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlNombreRepresentante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbBorrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbGrabar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jbAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jtffNombre))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlCodigo)
                        .addComponent(jtfnfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlCentro)
                        .addComponent(jtfnfCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNombre)
                    .addComponent(jtffNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jtfnfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jlNombreRepresentante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // Si pinchamos en el bot�n 'Salir', 'tiramos' la pantalla
        //frameMenu.setEnabled(true);
        //pantalla.dispose();
        salir();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        if (indiceZonasCliente == null) {
            indiceZonasCliente = new IndiceZonasCliente();
        } else {
            indiceZonasCliente.muestra();
        }
        jtfnfCodigo.setText(String.valueOf(indiceZonasCliente.getZonaCliente()));
        cargaDatos();
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdelanteActionPerformed
        // Si hay un espacio, entendemos c�digo 0
        if (jtfnfCodigo.getText().length() == 0) {
            jtfnfCodigo.setText("0");
        }

        String strSql = "SELECT * FROM ZONCLI WHERE EMPRESA = '"
                + DatosComunes.eEmpresa
                + "' AND ZONCLI_ZONA > " + jtfnfCodigo.getText();

        if (DatosComunes.centroCont != 0) {
            strSql += " AND ZONCLI_CENTRO = " + DatosComunes.centroCont + " LIMIT 1";
        }

        cargaDatos(strSql);

    }//GEN-LAST:event_jbAdelanteActionPerformed

    private void jbAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtrasActionPerformed
        // Si hay un espacio, entendemos c�digo 0
        if (jtfnfCodigo.getText().length() == 0) {
            jtfnfCodigo.setText("0");
        }

        String strSql = "SELECT * FROM ZONCLI WHERE EMPRESA = '"
                + DatosComunes.eEmpresa
                + "' AND ZONCLI_ZONA < " + jtfnfCodigo.getText();

        if (DatosComunes.centroCont != 0) {
            strSql += " AND ZONCLI_CENTRO = " + DatosComunes.centroCont
                    + " ORDER BY ZONCLI_ZONA DESC LIMIT 1";
        }

        cargaDatos(strSql);
    }//GEN-LAST:event_jbAtrasActionPerformed

    private void jbEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEstadisticasActionPerformed
        // Primer argumento Cuenta = "01"
        // Tercer argumento 5 porque son Zonas de Cliente
        new IndiceAcumuladosEstadisticos("01", jtffNombre.getText().trim(), 5);
    }//GEN-LAST:event_jbEstadisticasActionPerformed

    private void jbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarActionPerformed
        enCreacion = false;
        int registrosBorrados;
        boolean existeZona = false;
        int centro = DatosComunes.centroCont;

        if (centro == 0 && jtfnfCentro.getText().trim().length() == 0) {
            centro = 1;
        }
        if (centro == 0 && jtfnfCentro.getText().trim() == "0") {
            centro = 1;
        }
        if (centro == 0 && Integer.valueOf(jtfnfCentro.getText().trim()) > 0) {
            centro = Integer.valueOf(jtfnfCentro.getText().trim());
        }

        String strSql = "SELECT * FROM ZONCLI WHERE EMPRESA = '"
                + DatosComunes.eEmpresa
                + "' AND ZONCLI_ZONA = " + Integer.valueOf(jtfnfCodigo.getText().trim());

        if (DatosComunes.centroCont != 0) {
            strSql += " AND ZONCLI_CENTRO = " + centro;
        }

        // Comprobramos si existe la zona
        if (BaseDatos.countRows(strSql) > 0) {
            String sqlDelete = "DELETE FROM ZONCLI WHERE "
                    + "EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                    + "ZONCLI_ZONA = " + Integer.valueOf(jtfnfCodigo.getText().trim()) + " AND "
                    + "ZONCLI_CENTRO = " + centro;

            try {
                Statement ps = MysqlConnect.db.conn.createStatement();
             
                registrosBorrados = ps.executeUpdate(sqlDelete);
                
                if (registrosBorrados > 0){
                    JOptionPane.showMessageDialog(null,
                            "Zona Borrada!!!");
                    borrarPantalla();
                    cargaInicial();
                }
            } catch (SQLException e) {
                registrosBorrados = -1;
                if (DatosComunes.enDebug) {
                    JOptionPane.showMessageDialog(null,
                            "Error en borrado fichero de Zona!!!");
                    e.printStackTrace();
                }
            }
        } else {
            Apariencia.mensajeInformativo(5, "No existe la Zona.<BR>Revisarlo!!!");
        }
    }//GEN-LAST:event_jbBorrarActionPerformed

    private void jtfnfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnfCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfnfCodigoActionPerformed

    private void jbGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGrabarActionPerformed
        boolean zonaGrabada = true;

        int centro = DatosComunes.centroCont;

        if (centro == 0 && jtfnfCentro.getText().trim().length() == 0) {
            centro = 1;
        }
        if (centro == 0 && jtfnfCentro.getText().trim() == "0") {
            centro = 1;
        }
        if (centro == 0 && Integer.valueOf(jtfnfCentro.getText().trim()) > 0) {
            centro = Integer.valueOf(jtfnfCentro.getText().trim());
        }
        
        zona.setCentro(centro);
        zona.setEmpresa(DatosComunes.eEmpresa);
        zona.setNombre(jtffNombre.getText().trim());
        zona.setZona(Integer.valueOf(jtfnfCodigo.getText().trim()));
        zona.setRepresentante(Integer.valueOf(jtfnfRepresentante.getText().trim()));
        zona.write();
    }//GEN-LAST:event_jbGrabarActionPerformed

    private void jtfnfRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnfRepresentanteActionPerformed
        Representante representante = new Representante();
        if(representante.read(DatosComunes.eEmpresa, zona.getCentro(), Integer.valueOf(jtfnfRepresentante.getText().trim())) == null)
             Apariencia.mensajeInformativo(5, "No existe el Representante.<BR>Revisarlo!!!");        
        jlNombreRepresentante.setText(representante.getApellidosRazonSocial());
    }//GEN-LAST:event_jtfnfRepresentanteActionPerformed

    private void borrarPantalla() {

        if (consulta) {
            jtffNombre.setEnabled(false);
            jtfnfCentro.setEnabled(false);
            jtfnfRepresentante.setEnabled(false);
            jbBorrar.setVisible(false);
            jbGrabar.setVisible(false);
            jbEstadisticas.setVisible(true);
            jbPresupuesto.setVisible(false);

        } else {
            jtffNombre.setEnabled(true);
            jtfnfCentro.setEnabled(true);
            jtfnfRepresentante.setEnabled(true);
            jbBorrar.setVisible(true);
            jbGrabar.setVisible(true);
            jbEstadisticas.setVisible(true);
            jbPresupuesto.setVisible(true);
        }
        jtfnfCodigo.setText("0");
        jtffNombre.setText("");
        jtfnfCentro.setText(String.valueOf(DatosComunes.centroCont));
        jtfnfRepresentante.setText("");
    }

    private void cargaInicial() {
        // Carga inicial en el primer Proveedor
        if (jtfnfCodigo.getText().length() == 0) {
            jtfnfCodigo.setText("0");
        }

        String strSql = "SELECT * FROM ZONCLI WHERE EMPRESA = '"
                + DatosComunes.eEmpresa
                + "' AND ZONCLI_ZONA >= " + jtfnfCodigo.getText();

        if (DatosComunes.centroCont != 0) {
            strSql += " AND ZONCLI_CENTRO = " + DatosComunes.centroCont;
        }

        strSql += " LIMIT 1";

        cargaDatos(strSql);
    }

    private void cargaDatos() {
        String strSql = "SELECT * FROM ZONCLI WHERE EMPRESA = '"
                + DatosComunes.eEmpresa
                + "' AND ZONCLI_ZONA >= " + jtfnfCodigo.getText();

        if (DatosComunes.centroCont != 0) {
            strSql += " AND ZONCLI_CENTRO = " + DatosComunes.centroCont;
        }

        strSql += " LIMIT 1";

        cargaDatos(strSql);
    }

    private void cargaDatos(String strSql) {
        int numeroDeFilas = 0;

        numeroDeFilas = BaseDatos.countRows(strSql);
        if (numeroDeFilas > 0) {
            try {
                borrarPantalla();
                rs = m.query(strSql);

                // Recorremos el recodSet para ir rellenando la tabla de marcas
                if (rs.next() == true) {
                    zona.read(rs);

                    // Vamos a averiguar la descripci�n del Banco y de la Sucursal
                    //IndiceZonasCliente indiceZonas = new IndiceZonasCliente();
                    jtfnfCodigo.setText(String.valueOf(zona.getZona()));
                    jtfnfCentro.setText(String.valueOf(zona.getCentro()));
                    jtffNombre.setText(zona.getNombre());
                    jtfnfRepresentante.setText(String.valueOf(zona.getRepresentante()));
                                        
                    Representante representante = new Representante();
                    representante.read(DatosComunes.eEmpresa, zona.getCentro(), zona.getRepresentante());
                    jlNombreRepresentante.setText(representante.getApellidosRazonSocial());
                    

                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    private void salir() {
        this.dispose();
        frameMenu.setEnabled(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbAdelante;
    private javax.swing.JButton jbAtras;
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbEstadisticas;
    private javax.swing.JButton jbGrabar;
    private javax.swing.JButton jbPresupuesto;
    private javax.swing.JButton jbSalir;
    private javax.swing.JLabel jlCentro;
    private javax.swing.JLabel jlCodigo;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlNombreRepresentante;
    private javax.swing.JTextField jtffNombre;
    private javax.swing.JTextField jtfnfCentro;
    private javax.swing.JTextField jtfnfCodigo;
    private javax.swing.JTextField jtfnfRepresentante;
    // End of variables declaration//GEN-END:variables
}
