/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import general.DatosComunes;
import general.MysqlConnect;
import indices.IndicePrevisionesCobro;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.EscapeDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import tablas.Cuenta;
import tablas.EfectoCobrar;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.Fecha;

/**
 *
 * @author Txus
 */
public class DepuracionRiesgosContables extends util.EscapeDialog {
    
    public enum Columna {

        FECHA_VENCIMIENTO(0),
        EFECTO(1),
        IMPORTE(2),
        FECHA_FACTURA(3),
        FACTURA(4),
        CUENTA(5);

        private int value;

        private Columna(int value) {
            this.value = value;
        }
    }
    
    // Definiciones de componentes de pantalla
    
    private static EfectoCobrar efectoCobrar = new EfectoCobrar();

    private boolean borrarCobrados = false;
    private String strSql = "";
    
    int fechaFiltro = 0;
    
    ResultSet rs = null;
    MysqlConnect m = null;

    // Consulta SQL    
    String strCuenta = "";
    private int fechaTope;
    private DefaultTableCellRenderer tcr;
    private TableCellRenderer tcr2;
    JTable jtEfectosCobrar;
    JScrollPane spEfectosCobrar;
    
    DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Para que no podamos editar los datos de la tabla
            return false;
        }
    };

    /**
     * Creates new form DepuracionRiesgosContables
     */
    public DepuracionRiesgosContables(util.EscapeDialog parent, boolean modal, int hastaFecha) {
        super(parent, modal);
        this.fechaTope = hastaFecha;
        initComponents();
        inicializaTabla();
        jtffHastaFecha.setText(Fecha.fechaAcadena(hastaFecha));
                
    }
    
    private void inicializaTabla(){
        
        
        // La tabla contendra estas columnas
        modeloTabla.addColumn("Vencimiento");
        modeloTabla.addColumn("Efecto");
        modeloTabla.addColumn("Importe");
        modeloTabla.addColumn("Fecha Factura");
        modeloTabla.addColumn("Factura");
        modeloTabla.addColumn("Cuenta");
        
        jtEfectosCobrar = new JTable(modeloTabla);
        jtEfectosCobrar.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
        
        TableColumn columna = new TableColumn();
        // Establecemos el ancho
        jtEfectosCobrar.getColumn("Vencimiento").setMaxWidth(80);
        jtEfectosCobrar.getColumn("Efecto").setMaxWidth(60);
        jtEfectosCobrar.getColumn("Importe").setMaxWidth(120);
        jtEfectosCobrar.getColumn("Fecha Factura").setMaxWidth(80);
        jtEfectosCobrar.getColumn("Factura").setMaxWidth(80);
        jtEfectosCobrar.getColumn("Cuenta").setMaxWidth(260);
        jtEfectosCobrar.setRowHeight(20);

        // Hacemos que las columnas se alineen a la DERECHA
        tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jtEfectosCobrar.getColumn("Vencimiento").setCellRenderer(tcr);
        jtEfectosCobrar.getColumn("Efecto").setCellRenderer(tcr);
        jtEfectosCobrar.getColumn("Fecha Factura").setCellRenderer(tcr);
        jtEfectosCobrar.getColumn("Factura").setCellRenderer(tcr);

		// Hacemos que la comluna del saldo se alinee a la derecha y
        // que salga en rojo si es negativa.
        tcr2 = new TableCellRenderer();
        jtEfectosCobrar.getColumn("Importe").setCellRenderer(tcr2);        
        
        // Creamos un JscrollPane y le agregamos la JTable
        spEfectosCobrar = new JScrollPane(jtEfectosCobrar);
        // Si quisieramos barra horizontal, descomentar la linea siguiente
        spEfectosCobrar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // Agregamos el JScrollPane al contenedor
        spEfectosCobrar.setBounds(10, 80, 700, 480);
        spEfectosCobrar.setFont(Apariencia.cambiaFuente());
        this.add(spEfectosCobrar);	
        
        cargaPrevisionesCobro();
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

    private void borrarTabla(){
		// Vaciamos la tabla
		//int a = modeloTabla.getRowCount() - 1;

		//for (int i = a; i >= 0; i--)
		//	modeloTabla.removeRow(i);
		modeloTabla.setRowCount(0);
	}
    
    private void cargaPrevisionesCobro() {

        borrarTabla();
        
	// Primero cargamos TODAS las previsiones de Cobro.
        // La fecha sólo nos sirve para poner el foco en la primera
        // a partir de la fecha de vencimiento		
        String strHastaFecha = String.valueOf(Cadena.cadenaAfecha(jtffHastaFecha.getText()));
        
        
        if(strHastaFecha.equalsIgnoreCase("0"))
            fechaFiltro = fechaTope;
        else
            fechaFiltro = Cadena.cadenaAfecha(jtffHastaFecha.getText());

        if (fechaFiltro == 0) 
            fechaFiltro = Cadena.cadenaAfecha(jtffHastaFecha.getText());
        
            

        Object fila[] = {"", "", "", "", "", "", ""};

		// Necesitamos saber las que no tienen fecha de Cobro, esto es, que están pendientes
        // OJO a la clausula ORDER!!!!!A
        strSql = "SELECT * FROM EFECOB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND ";

        /* El indice del programa original pasa del CENTRO????
         if(DatosComunes.centroCont != 0)
         strSql = strSql + " EFECOB_CENTRO = " + DatosComunes.centroCont + " AND ";
         else
         strSql = strSql + " AND ";
         */
        if (strCuenta.length() > 0) {
            strSql += "EFECOB_CUENTA = '" + strCuenta + "' AND ";
        }

        strSql+= "EFECOB_VENCIM <= " + fechaFiltro + " AND ";
        
        if(borrarCobrados)
            strSql += "EFECOB_SITUACION = 5 AND EFECOB_IMPORTE > 0 AND EFECOB_VENCIM > 0 ";
        else
            strSql += "EFECOB_SITUACION = 3 ";
              
        
        strSql += "ORDER BY EFECOB_VENCIM, EFECOB_EFECTO, EFECOB_CUENTA, EFECOB_FECHA_COBRO ASC";

        //Apariencia.mensajeInformativo(4, strSql);
        Cuenta cuenta = new Cuenta();
        //EfectoCobrar efectoCobrar = new EfectoCobrar();
        
		// Utilizados para leer sólo una vez y ver si hay que mostrarlos o
        // si son 0, mejor no sacar nada.
        double dAcumulado = 0.0, importe = 0.0;
        int fechaVencimiento = 0, efecto = 0, fechaFactura = 0, factura = 0;
        String cuentaEfectoCobrar = "";
        String tituloCuentaEfectoCobrar = "";
        String strFechaVencimiento = "";

        m = MysqlConnect.getDbCon();
        
        
        int numeroRegistros = BaseDatos.countRows(strSql);
        int numeroFilas = 0;
        if (numeroRegistros != 0) {
            try {
                
                rs = m.query(strSql);

                while (rs.next() || rs.isLast()) {

                    efectoCobrar.read(rs);

                    strFechaVencimiento = Cadena.fechaAcadena(efectoCobrar.getVencimiento());
                    fechaVencimiento = Cadena.cadenaAfecha(strFechaVencimiento);
                    efecto = efectoCobrar.getEfecto();
                    importe = efectoCobrar.getImporte();
                    fechaFactura = efectoCobrar.getFechaFactura();
                    factura = efectoCobrar.getFactura();
                    cuentaEfectoCobrar = efectoCobrar.getCuenta();

                    // Obtenemos el título de la cuenta
                    String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' "
                            + "AND CONTAB_CENTRO = " + DatosComunes.centroCont + " "
                            + "AND CONTAB_CUENTA = '" + cuentaEfectoCobrar + "'";

                    if (cuenta.read(strSqlCuenta) == 0) {
                        tituloCuentaEfectoCobrar = "Inexistente!!!";
                    } else {
                        tituloCuentaEfectoCobrar = cuenta.getTitulo();
                    }

                    fila[Columna.FECHA_VENCIMIENTO.value] = strFechaVencimiento;
                    if (efecto == 0) {
                        fila[Columna.EFECTO.value] = "";
                    } else {
                        fila[Columna.EFECTO.value] = efecto;
                    }

                    if (importe == 0.0) {
                        fila[Columna.IMPORTE.value] = "";
                    } else {
                        fila[Columna.IMPORTE.value] = Cadena.formatoConComaDecimal(importe);
                    }

                    if (fechaFactura == 0) {
                        fila[Columna.FECHA_FACTURA.value] = "";
                    } else {
                        fila[Columna.FECHA_FACTURA.value] = Cadena.fechaAcadena(fechaFactura);
                    }

                    if (factura == 0) {
                        fila[Columna.FACTURA.value] = "";
                    } else {
                        fila[Columna.FACTURA.value] = factura;
                    }
 

                    if (tituloCuentaEfectoCobrar.length() == 0) {
                        fila[Columna.CUENTA.value] = "";
                    } else {
                        String situacion = "";
                        switch (efectoCobrar.getSituacion()) {
                            case 0:
                                situacion = "Rec ";
                                break;
                            case 2:
                                situacion = "lib ";
                                break;
                            case 3:
                                situacion = "Rem ";
                                break;
                            case 4:
                                situacion = "Acp ";
                                break;
                            case 5:
                                situacion = "Cob ";
                                break;
                            default:
                                situacion = "    ";
                                break;
                        }
                        fila[Columna.CUENTA.value] = situacion + tituloCuentaEfectoCobrar;
                    }

                    if (fechaFiltro == 0) {
                        modeloTabla.addRow(fila);
                        numeroFilas++;
                    } else {
                        if (fechaVencimiento <= fechaFiltro) {
                            modeloTabla.addRow(fila);
                            numeroFilas++;
                        }
                    }
                }
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de DebeHaber<BR>Consulta Acumulados Contables");
            }

            /*
            if (fechaFiltro != 0) {
                numeroFilas = 0;
            } else {
                numeroFilas--;
            }
            */
            numeroFilas--;
            // Vamos al final de la trabla
            jtEfectosCobrar.clearSelection();
            jtEfectosCobrar.setRowSelectionInterval(numeroFilas, numeroFilas);
            Rectangle rect = jtEfectosCobrar.getCellRect(numeroFilas, 0, true);
            jtEfectosCobrar.scrollRectToVisible(rect);
            jtEfectosCobrar.clearSelection();
            jtEfectosCobrar.setRowSelectionInterval(numeroFilas, numeroFilas);
            DefaultTableModel modelo = (DefaultTableModel) jtEfectosCobrar.getModel();
            modelo.fireTableDataChanged();          
        }

    }

    private void salir() {
        this.dispose();        
    }   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoDepuracion = new javax.swing.ButtonGroup();
        jlHastaFecha = new javax.swing.JLabel();
        jtffHastaFecha = new util.JTextFieldFecha();
        jtbCobradosComoRemesados = new javax.swing.JRadioButton();
        jrbBorrarCobrados = new javax.swing.JRadioButton();
        jbBajaAntiguos = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jbProceso = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Depuración Riesgo");

        jlHastaFecha.setText("Hasta Fecha");

        jtffHastaFecha.setText("00.00.00");
        jtffHastaFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtffHastaFechaActionPerformed(evt);
            }
        });

        bgTipoDepuracion.add(jtbCobradosComoRemesados);
        jtbCobradosComoRemesados.setSelected(true);
        jtbCobradosComoRemesados.setText("Poner los REMESADOS como COBRADOS");
        jtbCobradosComoRemesados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbCobradosComoRemesadosActionPerformed(evt);
            }
        });

        bgTipoDepuracion.add(jrbBorrarCobrados);
        jrbBorrarCobrados.setText("Borrar efectos COBRADOS");
        jrbBorrarCobrados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbBorrarCobradosActionPerformed(evt);
            }
        });

        jbBajaAntiguos.setText("Baja Antiguos");

        jbProceso.setText("Proceso");
        jbProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProcesoActionPerformed(evt);
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
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlHastaFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtffHastaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrbBorrarCobrados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtbCobradosComoRemesados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSalir)
                        .addGap(45, 45, 45)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbProceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbBajaAntiguos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlHastaFecha)
                        .addComponent(jtffHastaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtbCobradosComoRemesados)
                            .addComponent(jbBajaAntiguos)
                            .addComponent(jbSalir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbBorrarCobrados)
                            .addComponent(jbProceso))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(510, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        
        salir();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jtbCobradosComoRemesadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbCobradosComoRemesadosActionPerformed
        borrarCobrados = false;
        Apariencia.mensajeInformativo(5, 
            "Al pulsar el botón de 'Proceso',<br> los efectos REMESADOS se pondrán<br> con el estatus de COBRADOS.");
        cargaPrevisionesCobro();
    }//GEN-LAST:event_jtbCobradosComoRemesadosActionPerformed

    private void jrbBorrarCobradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbBorrarCobradosActionPerformed
       borrarCobrados = true;
       Apariencia.mensajeInformativo(5, 
            "Al pulsar el botón de 'Proceso',<br> los efectos COBRADOS se borrarán<br> hasta la fecha indicada inclusive.");
       cargaPrevisionesCobro();
    }//GEN-LAST:event_jrbBorrarCobradosActionPerformed

    private void jtffHastaFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtffHastaFechaActionPerformed
        
        cargaPrevisionesCobro();
    }//GEN-LAST:event_jtffHastaFechaActionPerformed

    private void jbProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProcesoActionPerformed

        m = MysqlConnect.getDbCon();
        int numeroRegistros, opcion;
        Object[] opciones = {"Si", "No"};

        numeroRegistros = BaseDatos.countRows(strSql);

        if (borrarCobrados) {
            if (numeroRegistros > 0) {
                // Damos la oportunidad de no borrar

                opcion = JOptionPane.showOptionDialog(this,
                        "<html><font size='4'><strong><center>"
                        + "Se borrarán " + String.valueOf(numeroRegistros) + " Efectos a Cobrar.<br>"
                        + "¿Desea continuar?<br>"
                        + "</center></strong></font></html>",
                        "Borrar Efectos a Cobrar",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, // Sin Icono personalizado.
                        opciones, // Título de los botonoes
                        opciones[1]); // Botón por defecto.           
                // Si = 0
                if (opcion == 0) {
                    // Creamos la SQL de borrado.
                    String strSqlDelete = strSql.replaceFirst("SELECT *", "DELETE  ");
                    Apariencia.mensajeInformativo(3, strSqlDelete);
                }
            } else {
                Apariencia.mensajeInformativo(4, "<html><font size='4'><strong><center>"
                        + "Lo siento, pero no hay efectos COBRADO para borrar."
                        + "</center></strong></font></html>");
            }
        } else {
            //if (numeroRegistros > 0) {
            if(true){
                // Damos la oportunidad de no poner com COBRADOS
                opcion = JOptionPane.showOptionDialog(this,
                        "<html><font size='4'><strong><center>"
                        + "Se pondrán como COBRADOS " + String.valueOf(numeroRegistros) + " Efectos a Cobrar,<br>"
                        + "que ahora figuran como REMESADOS.<br>"
                        + "¿Desea continuar?<br>"
                        + "</center></strong></font></html>",
                        "Borrar Efectos a Cobrar",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, // Sin Icono personalizado.
                        opciones, // Título de los botonoes
                        opciones[1]); // Botón por defecto.     
                // Si = 0
                if (opcion == 0) {
                    // Creamos la SQL de UPDATE
                    String strSqlUpdate = "UPDATE EFECOB SET EFECOB_SITUACION = 5, EFECOB_BANCO = 0, EFECOB_REMESA = 0, EFECOB_FECHA_REMESA = 0 "
                            + "WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " 
                            + "EFECOB_VENCIM <= " + fechaFiltro + " AND "
                            + "EFECOB_SITUACION = 3";
                    Apariencia.mensajeInformativo(3, strSqlUpdate);
                }
            } else {
                Apariencia.mensajeInformativo(4, "<html><font size='4'><strong><center>" +
                        "Lo siento, pero no hay efectos REMESADOS para poner como COBRADOS." +
                        "</center></strong></font></html>");
            }

        }
    }//GEN-LAST:event_jbProcesoActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoDepuracion;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbBajaAntiguos;
    private javax.swing.JButton jbProceso;
    private javax.swing.JButton jbSalir;
    private javax.swing.JLabel jlHastaFecha;
    private javax.swing.JRadioButton jrbBorrarCobrados;
    private javax.swing.JRadioButton jtbCobradosComoRemesados;
    private util.JTextFieldFecha jtffHastaFecha;
    // End of variables declaration//GEN-END:variables
}
