/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indices;

import general.DatosComunes;
import general.MysqlConnect;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import tablas.ClaveContable;
import util.Apariencia;
import util.BaseDatos;

/**
 *
 * @author Txus
 */
public class IndiceClavesConceptos extends util.EscapeDialog {

    
    /**
     * Creates new form IndiceClavesConceptos
     */
    public IndiceClavesConceptos(util.EscapeDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializaTabla();
    }

    public enum Columna {

        NUMERO(0),
        DESCRIPCION(1),
        IVA(2),
        PREVISION(3),
        ACUMULADO(4);

        private int value;

        private Columna(int value) {
            this.value = value;
        }
    }
    
    // Definiciones de componentes de pantalla
        
    private String strSql = "";      
    private ClaveContable claveContable;        
    
    ResultSet rs = null;
    MysqlConnect m = null;
   
    private DefaultTableCellRenderer dtcrDebe, dtcrHaber;
    //private TableCellRenderer tcr;
    JTable jtClavesDebe, jtClavesHaber;
    JScrollPane spClavesDebe, spClavesHaber;
    
    DefaultTableModel modeloTablaDebe = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Para que no podamos editar los datos de la tabla
            return false;
        }
    };
    
    DefaultTableModel modeloTablaHaber = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Para que no podamos editar los datos de la tabla
            return false;
        }
    };
    
    private void inicializaTabla(){
        claveContable = new ClaveContable();
        m = MysqlConnect.getDbCon();
        
        // La tabla contendra estas columnas
        modeloTablaDebe.addColumn("Id");
        modeloTablaDebe.addColumn("Descripción");
        modeloTablaDebe.addColumn("Iva");
        modeloTablaDebe.addColumn("Prev.");
        modeloTablaDebe.addColumn("Acumula");     
        
        modeloTablaHaber.addColumn("Id");
        modeloTablaHaber.addColumn("Descripción");
        modeloTablaHaber.addColumn("Iva");
        modeloTablaHaber.addColumn("Prev.");
        modeloTablaHaber.addColumn("Acumula");   
        
        jtClavesDebe = new JTable(modeloTablaDebe);
        jtClavesHaber = new JTable(modeloTablaHaber);
        
        jtClavesDebe.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
        jtClavesHaber.setFont(Apariencia.cambiaFuente(Font.PLAIN, 13));
        
        TableColumn columna = new TableColumn();
        // Establecemos el ancho
        jtClavesDebe.getColumn("Id").setMaxWidth(30);
        jtClavesDebe.getColumn("Descripción").setMaxWidth(120);
        jtClavesDebe.getColumn("Iva").setMaxWidth(30);
        jtClavesDebe.getColumn("Prev.").setMaxWidth(50);
        jtClavesDebe.getColumn("Acumula").setMaxWidth(180);
        jtClavesDebe.setRowHeight(20);
        
        jtClavesHaber.getColumn("Id").setMaxWidth(30);
        jtClavesHaber.getColumn("Descripción").setMaxWidth(120);
        jtClavesHaber.getColumn("Iva").setMaxWidth(30);
        jtClavesHaber.getColumn("Prev.").setMaxWidth(50);
        jtClavesHaber.getColumn("Acumula").setMaxWidth(180);
        jtClavesHaber.setRowHeight(20);

        // Hacemos que las columnas se alineen a la IZQUIERDA
        dtcrDebe = new DefaultTableCellRenderer();
        dtcrDebe.setHorizontalAlignment(SwingConstants.LEFT);
        jtClavesDebe.getColumn("Id").setCellRenderer(dtcrDebe);
        jtClavesDebe.getColumn("Descripción").setCellRenderer(dtcrDebe);
        jtClavesDebe.getColumn("Iva").setCellRenderer(dtcrDebe);
        jtClavesDebe.getColumn("Prev.").setCellRenderer(dtcrDebe);
        jtClavesDebe.getColumn("Acumula").setCellRenderer(dtcrDebe);
        
        dtcrHaber = new DefaultTableCellRenderer();
        dtcrHaber.setHorizontalAlignment(SwingConstants.LEFT);
        
        jtClavesHaber.getColumn("Id").setCellRenderer(dtcrHaber);
        jtClavesHaber.getColumn("Descripción").setCellRenderer(dtcrHaber);
        jtClavesHaber.getColumn("Iva").setCellRenderer(dtcrHaber);
        jtClavesHaber.getColumn("Prev.").setCellRenderer(dtcrHaber);
        jtClavesHaber.getColumn("Acumula").setCellRenderer(dtcrHaber);
        
        // Creamos un JscrollPane y le agregamos la JTable
        spClavesDebe = new JScrollPane(jtClavesDebe);
        spClavesHaber = new JScrollPane(jtClavesHaber);
        // Si quisieramos barra horizontal, descomentar la linea siguiente
        spClavesDebe.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spClavesHaber.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // Agregamos el JScrollPane al contenedor
        spClavesDebe.setBounds(10, 20, 380, 460);
        spClavesDebe.setFont(Apariencia.cambiaFuente());
        
        spClavesHaber.setBounds(400, 20, 380, 460);
        spClavesHaber.setFont(Apariencia.cambiaFuente());
        
        jtClavesDebe.addMouseListener(new TablaListener());
        jtClavesHaber.addMouseListener(new TablaListener());
        
        this.add(spClavesDebe);	
        this.add(spClavesHaber);
        
        // Primero cargamos las de Debe
        cargaClavesYConceptos(0);
        // Después cargamos las de Haber
        cargaClavesYConceptos(1);
        
        // Con esto conseguimos que sei pulsamos la tecla ESCAPE, se devuelva un 0
        claveContable = new ClaveContable();
                
    }
    
    private void cargaClavesYConceptos(int debeHaber) {
        // debeHaber = 0 => Carga claves de Debe
        // debeHaber = 1 => Carga claves de Haber
        
        int numeroDeFilas = 0;
        int clave;

        Object fila[] = {"", "", "", "", ""};

        if (m.conn == null) {
            getClaveContable();
        }

        strSql = "";

        if (debeHaber == 0) {
            strSql = "SELECT * FROM CLCEPC WHERE EMPRESA = '" + DatosComunes.eEmpresa
                    + "' AND CLCEPC_KEY < 50";
        } else {
            strSql = "SELECT * FROM CLCEPC WHERE EMPRESA = '" + DatosComunes.eEmpresa
                    + "' AND CLCEPC_KEY > 50";
        }

        numeroDeFilas = BaseDatos.countRows(strSql);
        if (numeroDeFilas > 0) {
            try {
                limpiarTabla(debeHaber);                
                rs = (ResultSet) m.query(strSql);

                // Recorremos el recodSet para ir rellenando la tabla de marcas
                while (rs.next() == true) {
                    claveContable.read(rs);
                    
                    clave = claveContable.getClave();
                    fila[Columna.NUMERO.value] = clave;
                    fila[Columna.DESCRIPCION.value] = claveContable.getDescripcion();
                    
                    if(claveContable.getActualizaIva() == 0)
                        // Cuando en "No", no lo sacamos
                        fila[Columna.IVA.value] = "";
                    else
                        fila[Columna.IVA.value] = "Si";
                    
                    // Debe
                    if (clave < 50) {
                        switch (claveContable.getActualizaPrevisiones()) {
                            case 0:
                                // Cuando en "No", no lo sacamos
                                fila[Columna.PREVISION.value] = "";
                                break;
                            case 1:
                                fila[Columna.PREVISION.value] = "Pago en Menos";
                                break;
                            case 2:
                                fila[Columna.PREVISION.value] = "Cobro";
                                break;
                        }

                        switch (claveContable.getActualizaAcumulados()) {
                            case 0:
                                // Cuando en "No", no lo sacamos
                                fila[Columna.ACUMULADO.value] = "";
                                break;
                            case 1:
                                fila[Columna.ACUMULADO.value] = "Facturado Cliente";
                                break;
                            case 2:
                                fila[Columna.ACUMULADO.value] = "Remesado Banco";
                                break;
                            case 3:
                                fila[Columna.ACUMULADO.value] = "Retenc.IRPF Repres";
                                break;
                            case 4:
                                fila[Columna.ACUMULADO.value] = "Impagado Clientes";
                                break;
                            case 5:
                                fila[Columna.ACUMULADO.value] = "Fact+Rappel Cliente";
                                break;
                        }
                    } else {
                        //Haber
                        switch (claveContable.getActualizaPrevisiones()) {
                            case 0:
                                // Cuando en "No", no lo sacamos
                                fila[Columna.PREVISION.value] = "";
                                break;
                            case 1:
                                fila[Columna.PREVISION.value] = "Pago";
                                break;
                            case 2:
                                fila[Columna.PREVISION.value] = "Cobro en menos";
                                break;
                        }

                        switch (claveContable.getActualizaAcumulados()) {
                            // Cuando en "No", no lo sacamos
                            case 0:
                                fila[Columna.ACUMULADO.value] = "";
                                break;
                            case 1:
                                fila[Columna.ACUMULADO.value] = "Facturado Proveedor";
                                break;
                            case 2:
                                fila[Columna.ACUMULADO.value] = "Comision Represent.";
                                break;
                            case 3:
                                fila[Columna.ACUMULADO.value] = "Negociado Bancos";
                                break;
                            case 4:
                                fila[Columna.ACUMULADO.value] = "Gtos.Devol.Banco";
                                break;
                            case 5:
                                fila[Columna.ACUMULADO.value] = "Fact+Rappel Provee";
                                break;
                        }
                    }
                    
                    if (debeHaber == 0) {
                        modeloTablaDebe.addRow(fila);
                    } else {
                        modeloTablaHaber.addRow(fila);
                    }   
                        
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
    
    public void limpiarTabla(int debeHaber) {
        // Vaciamos las tablas
        
        if(debeHaber == 0)
            modeloTablaDebe.setRowCount(0);
        else
            modeloTablaHaber.setRowCount(0);
    }
    
    public ClaveContable getClaveContable() {
        try {
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.dispose();
        return claveContable;
    }

    public void muestra() {
        this.setVisible(true);
    }
    
    class TablaListener implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            claveContable = null;
            dispose();
        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
            // Si hacemos doble click fijamos el valor de la marca y salimos
            if (arg0.getClickCount() == 2) {
                JTable target = (JTable) arg0.getSource();
                int row = target.getSelectedRow();
                int clave = (Integer) target.getValueAt(row, 0);
                claveContable.read(clave);
                dispose();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    class SalirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            claveContable = null;
            dispose();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Indice de Claves");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
