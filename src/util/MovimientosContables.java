/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import general.DatosComunes;
import general.MysqlConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import tablas.MovimientoContable;

/**
 *
 * @author Txus
 */
public class MovimientosContables {
    
    public enum Columna {

        FECHA_ASIENTO_APUNTE(1),
        CUENTA(2),
        DOCUMENTO(3),
        CLAVE(4), 
        CONCEPTO(5),
        IMPORTE(6);
        
        private int value;

        private Columna(int value) {
            this.value = value;
        }
    }
    
    
    public static int buscaPrimeroLibreEnDia(int centro, int fechaDia){
        int primeroLibre = -1;
        MovimientoContable movimientoContable = new MovimientoContable();
        
        String claveFechaAsientoApunte = String.valueOf(fechaDia)
                + String.format("%05d", 99999)
                + String.format("%05d", 99999);

        String strSqlCuenta = "SELECT * FROM MOVCON WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND MOVCON_CENTRO = " + centro
                + " AND MOVCON_FECH_ASTO_APT < '" + claveFechaAsientoApunte + "'"
                + " ORDER BY MOVCON_FECH_ASTO_APT DESC LIMIT 1";

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al m�rodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    movimientoContable.read(rsSql);
                    
                    claveFechaAsientoApunte = String.valueOf(movimientoContable.getFechaAsientoApunte());
                    
                    String strFechaAsiento = claveFechaAsientoApunte.substring(0, 8);
                    String strAsiento = claveFechaAsientoApunte.substring(8, 13);
                    String strApunte = claveFechaAsientoApunte.substring(13, 18);
                    
                    int fechaAsiento = Integer.valueOf(strFechaAsiento);
                    
                    // Si es la misma fecha, es primer asiento libre es el siguiente al �ltimo de ese dia
                    if(fechaDia == fechaAsiento){
                        primeroLibre = Integer.valueOf(strAsiento) + 1;
                    }else{
                    // Si la fecha es distinta, significa, que no hay asientos en ese d�a y 
                    // que el primero libre es el n�mero 1    
                        primeroLibre = 1;
                    }
                    // Cerramos para evitar gastar memoria
                    rsSql.close();
                }
            } catch (SQLException e) {
                                
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Movimientos Contables");
            }
        } 

        rsSql = null;
        m = null;
                
        return primeroLibre;
    }
    
    // Intentamos leer un apunde de un asiento, para ello pasamos como para�metros
    // el centro, la fecha y el asiento
    // Si lo hemos leido bien, devolvemos TRUE, en caso de problemas FALSE
    /**
     *
     * @param centro
     * @param fecha
     * @param asiento
     * @return lecturaOk
     */
    public static boolean existeMovimiento(int centro, int fecha, int asiento){
    
        boolean lecturaOk = true;
        MovimientoContable movimientoContable = new MovimientoContable();

        String claveFechaAsientoApunte = String.valueOf(fecha)
                + String.format("%05d", asiento)
                + String.format("%05d", 1);

        String strSqlCuenta = "SELECT * FROM MOVCON WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND MOVCON_CENTRO = " + centro
                + " AND MOVCON_FECH_ASTO_APT = '" + claveFechaAsientoApunte + "' LIMIT 1";

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al m�rodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    movimientoContable.read(rsSql);
                    lecturaOk = true;
                    // Cerramos para evitar gastar memoria
                    rsSql.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                lecturaOk = false;
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Movimientos Contables(ExisteMovimiento)");
            }
        } else {
            lecturaOk = false;
        }

        rsSql = null;
        m = null;
        
        return lecturaOk;
    }    
    
    public static Vector<LineaMovimientoContable> leeAsiento(int centro, int fechaAsiento, int asiento){
                
        Vector<LineaMovimientoContable> vectorLineaMovimientos = new Vector<>();
        boolean lecturaOk = true;
        
        
         String claveFechaAsiento = String.valueOf(fechaAsiento)
                + String.format("%05d", asiento)
                + "%"; // Para el LIKE %

        String strSql = "SELECT "
                        + "MOVCON_FECH_ASTO_APT, " 
                        + "MOVCON_CUENTA, " 
                        + "MOVCON_DOCUMENTO, " 
                        + "MOVCON_CLAVE, " 
                        + "MOCCEP_CONCEPTO, " 
                        + "MOVCON_IMPORTE "
                        + "FROM MOVCON, MOCCEP WHERE "
                        + "MOVCON.EMPRESA = '" + DatosComunes.eEmpresa + "' "
                        + "AND MOVCON_CENTRO = " + centro + " "
                        + "AND MOVCON_FECH_ASTO_APT LIKE '" + claveFechaAsiento + "' "
                        + "AND MOVCON_FECH_ASTO_APT = MOCCEP_FECH_ASTO_APT "
                        + "ORDER BY MOVCON_FECH_ASTO_APT ASC";
       
        ResultSet rs = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        vectorLineaMovimientos.clear();

        try {
            rs = m.query(strSql);

            while (rs.next() || rs.isLast()) {
                LineaMovimientoContable  lmc = new LineaMovimientoContable();
                                
                lmc.setFechaAsientoApunte(rs.getLong(Columna.FECHA_ASIENTO_APUNTE.value));
                lmc.setCuenta(rs.getString(Columna.CUENTA.value));
                lmc.setDocumento(rs.getInt(Columna.DOCUMENTO.value));
                lmc.setClave(rs.getInt(Columna.CLAVE.value));
                lmc.setConcepto(rs.getString(Columna.CONCEPTO.value));
                lmc.setImporte(rs.getDouble(Columna.IMPORTE.value));
                
                vectorLineaMovimientos.add(lmc);                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Apariencia.mensajeInformativo(5, "Error en lectura de la Linea del Movimiento contable.<BR>Movimiventos Contables");
        }

        return vectorLineaMovimientos;
    }
}
