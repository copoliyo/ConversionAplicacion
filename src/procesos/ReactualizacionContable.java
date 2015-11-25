/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import general.DatosComunes;
import general.MysqlConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tablas.Cuenta;
import tablas.DebeHaber;
import tablas.MovimientoContable;
import util.Apariencia;
import util.BaseDatos;
import util.Fecha;
import util.JOptionPaneConTimeOut;

/**
 *
 * @author Txus
 */
public class ReactualizacionContable {
    int mes, anio, apunte;
    public static ResultSet rs = null;
    public MysqlConnect m = null;
    Fecha fechaInicio;
    int anioHasta, mesHasta;
    
    public ReactualizacionContable(){
        mes = 1;
        anio = 1980;
        apunte = 1;
        anioHasta = mesHasta = 0;
        // Si existe Fecha Ultima Depuracion Movimientos Contables, utilizamos esa
        // Si no, empezamos siempre desde 01/01/1980        
        if(DatosComunes.fecUltDepmoc != 0)
            fechaInicio = new Fecha(String.valueOf(DatosComunes.fecUltDepmoc));
        else
            fechaInicio = new Fecha("20050101");
        
        m = MysqlConnect.getDbCon();
        
        PonerContabSaldoUltDepmov();
        PonerGradosCuentasCorrectos();
        BorraAcumuladosDebeHaber();
        recalculaDebHabDesdeMovcon();
    }
    
    private void PonerContabSaldoUltDepmov(){
        
        
        String strSqlUpdate = "UPDATE CONTAB SET CONTAB_SALDO = CONTAB_SALDO_ULTDEPMOV";
        
        System.out.println("Procedemos a poner el Salde de Ultima depuración de moviemoentos en el Saldo");
        System.out.println("SQL: '" + strSqlUpdate + "'");        
        
        int registrosAfectados = 0;
        
        try {
            if (m.insert(strSqlUpdate) > 0) // JOptionPane.showMessageDialog(null, "Grabación correcta.");
            {
                System.out.println("Actualizción de Saldo Ultima Depuración correcta.");               
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el Saldo de Ultima Depuración.");
            e.printStackTrace();
        }                        
    }
    
    private void PonerGradosCuentasCorrectos(){
        String strSql = "SELECT * FROM CONTAB";
        int longitudCuenta = 0;
        int grado = 1;
        
        Cuenta cuenta = new Cuenta();
        int numeroDeFilas = BaseDatos.countRows(strSql);
        if (numeroDeFilas > 0) {
            try {

                rs = m.query(strSql);

                // Recorremos el recodSet para ir rellenando la tabla de rutas
                while(rs.next() == true) {
                    cuenta.read(rs);                                        
                    
                    longitudCuenta = cuenta.getCuenta().trim().length();
                    if(longitudCuenta <= 3)
                        grado = 1;
                    else
                        if(longitudCuenta == 4 || longitudCuenta == 5)
                            grado = 2;
                        else
                            grado = 3;                        

                    cuenta.setGrado(String.valueOf(grado));
                    cuenta.write();
                    
                    System.out.println("Cuenta: '" + cuenta.getCuenta() + "'" + "    Grado: " + cuenta.getGrado());                    
                }
            } catch (SQLException e1) {
                System.out.println("Fallo al leer cuenta.");
                e1.printStackTrace();
            }
        }
        
        rs = null;
    }
    
    private void BorraAcumuladosDebeHaber(){
        
        DebeHaber dh = new DebeHaber();
        
        int anioDh = 0;
        int mesDh = 0;
        
        String strSql = "SELECT * FROM DEBHAB";
        int numeroDeFilas = BaseDatos.countRows(strSql);
        if (numeroDeFilas > 0) {
            try {

                rs = m.query(strSql);

                // Recorremos el recodSet
                while(rs.next() == true) {
                    dh.read(rs);                                        
                    
                    System.out.println("'" + dh.getCuentaAñoMes() + "'");
                    anioDh = Integer.valueOf(dh.getCuentaAñoMes().substring(9, 13));
                    mesDh  = Integer.valueOf(dh.getCuentaAñoMes().substring(13, 14));
                    System.out.print("DH Clave: '" + dh.getCuentaAñoMes() + " Año: " + anioDh + "    Mes: " + mesDh);     
                    if(anioDh < fechaInicio.getAnio()){
                        System.out.println("   NO BORRAR");                        
                    }else{
                        if(anioDh == fechaInicio.getAnio() && mesDh < fechaInicio.getMes()){
                            System.out.println("   NO BORRAR");                        
                        }else{
                            System.out.println("   BORRAR");
                            dh.delete(dh.getCuentaAñoMes());
                        }
                    }
                }
            } catch (SQLException e1) {
                System.out.println("Fallo al leer DebeHaber.");
                e1.printStackTrace();
            }
        }
    }
    
    private void recalculaDebHabDesdeMovcon(){
        
        int i = 0;
        String strCuenta, claveMovconFechaAstoApunte;
        int anioMov, mesMov, diaMov, asientoMov;
        int numeroMovimientosLeidos = 0;
        double movDebe, movHaber;
        String strSqlMov, strSqlDebHab;
        MovimientoContable mc = new MovimientoContable();
        DebeHaber dh = new DebeHaber();
        ResultSet rsDebHab = null;
        anioMov = mesMov = 0;
        
        
        
        do{
            strSqlMov = "SELECT * FROM MOVCON LIMIT 100 OFFSET " + String.valueOf(i);
            
            //numeroMovimientosLeidos = BaseDatos.countRows(strSqlMov);
            try {
                m.conn.close();
                while(!m.conn.isClosed()){
                    i = i;
                }
                         
                m.closeCon();
                m = null;
                m = MysqlConnect.getDbCon();
                //while(m.conn.isClosed()){
                //    i=i;
                //}
               
                //System.out.println("i: " + i);
                rs = m.query(strSqlMov); 
                rs.last(); 
                numeroMovimientosLeidos = rs.getRow(); 
                rs.beforeFirst(); // esto te lo deja como al principio 
                //System.out.println("Registros leidos: " + numeroMovimientosLeidos);
                // Recorremos el recodSet para ir rellenando la tabla de marcas
                while (rs.next() == true) {
                    mc.read(rs);
                    strCuenta = mc.getCuenta();
                    claveMovconFechaAstoApunte = String.valueOf(mc.getFechaAsientoApunte());
                    anioMov = Integer.valueOf(claveMovconFechaAstoApunte.substring(0, 4));
                    mesMov = Integer.valueOf(claveMovconFechaAstoApunte.substring(4, 6));
                    diaMov = Integer.valueOf(claveMovconFechaAstoApunte.substring(6, 8));
                    asientoMov = Integer.valueOf(claveMovconFechaAstoApunte.substring(8, 13));
                    // Por los asientos de Regularizacion (13) y de Cierre (14)
                    if(mesMov == 12 && diaMov == 31){
                        if(asientoMov == 99998)
                            mesMov = 13;
                        if(asientoMov == 99999)
                            mesMov = 14;
                    }
                    if(mc.getClave() < 50){
                        movDebe = mc.getImporte();
                        movHaber = 0.0;
                    }else{
                        movDebe = 0.0;
                        movHaber = mc.getImporte();
                    }
                    // Comprobamos que no empiece a procesar de fechas anteriores
                    if (anioMov > fechaInicio.getAnio() || (anioMov == fechaInicio.getAnio() && mesMov >= fechaInicio.getMes())){
                        strSqlDebHab = "SELECT * FROM DEBHAB WHERE EMPRESA =  '" + DatosComunes.eEmpresa + "' AND "
                                + "DEBHAB_CTA_ANYMES = '" + String.format("%1$-9s", strCuenta) + 
                                String.format("%04d%02d", anioMov, mesMov) + "'";
                        rsDebHab = m.query(strSqlDebHab);
                        if (rsDebHab.next()){                            
                            dh.read(rsDebHab);
                            dh.setDebe(dh.getDebe() + movDebe);
                            dh.setHaber(dh.getHaber() + movHaber);
                            dh.write();
                            //System.out.println("Clave DEBHAB existente: " + dh.getCuentaAñoMes() + "  Debe: " + dh.getDebe() + "  Haber: " + dh.getHaber());                        
                        }else{
                            dh.setEmpresa(DatosComunes.eEmpresa);
                            dh.setCuentaAñoMes(String.format("%1$-9s", strCuenta) + 
                                String.format("%04d%02d", anioMov, mesMov));
                            dh.setDebe(movDebe);
                            dh.setHaber(movHaber);
                            dh.write();
                            //System.out.println("Clave DEBHAB nueva: " + dh.getCuentaAñoMes() + "  Debe: " + dh.getDebe() + "  Haber: " + dh.getHaber());
                        }
                        //rsDebHab.close();                        
                        //rsDebHab = null;
                        // Ahora hay que hacer las cuentas superiores
                        // Segundo grado
                        strCuenta = strCuenta.substring(0, 5);
                        strSqlDebHab = "SELECT * FROM DEBHAB WHERE EMPRESA =  '" + DatosComunes.eEmpresa + "' AND "
                                + "DEBHAB_CTA_ANYMES = '" + String.format("%1$-9s", strCuenta) + 
                                String.format("%04d%02d", anioMov, mesMov) + "'";
                        rsDebHab = m.query(strSqlDebHab);
                        if (rsDebHab.next()){                            
                            dh.read(rsDebHab);
                            dh.setDebe(dh.getDebe() + movDebe);
                            dh.setHaber(dh.getHaber() + movHaber);
                            dh.write();
                            //System.out.println("Clave DEBHAB existente: " + dh.getCuentaAñoMes() + "  Debe: " + dh.getDebe() + "  Haber: " + dh.getHaber());                        
                        }else{
                            dh.setEmpresa(DatosComunes.eEmpresa);
                            dh.setCuentaAñoMes(String.format("%1$-9s", strCuenta) + 
                                String.format("%04d%02d", anioMov, mesMov));
                            dh.setDebe(movDebe);
                            dh.setHaber(movHaber);
                            dh.write();
                            //System.out.println("Clave DEBHAB nueva: " + dh.getCuentaAñoMes() + "  Debe: " + dh.getDebe() + "  Haber: " + dh.getHaber());
                        }
                        //rsDebHab.close();                        
                        //rsDebHab = null;
                        // Primer Grado
                        strCuenta = strCuenta.substring(0, 3);
                        strSqlDebHab = "SELECT * FROM DEBHAB WHERE EMPRESA =  '" + DatosComunes.eEmpresa + "' AND "
                                + "DEBHAB_CTA_ANYMES = '" + String.format("%1$-9s", strCuenta) + 
                                String.format("%04d%02d", anioMov, mesMov) + "'";
                        rsDebHab = m.query(strSqlDebHab);
                        if (rsDebHab.next()){                            
                            dh.read(rsDebHab);
                            dh.setDebe(dh.getDebe() + movDebe);
                            dh.setHaber(dh.getHaber() + movHaber);
                            dh.write();
                            //System.out.println("Clave DEBHAB existente: " + dh.getCuentaAñoMes() + "  Debe: " + dh.getDebe() + "  Haber: " + dh.getHaber());                        
                        }else{
                            dh.setEmpresa(DatosComunes.eEmpresa);
                            dh.setCuentaAñoMes(String.format("%1$-9s", strCuenta) + 
                                String.format("%04d%02d", anioMov, mesMov));
                            dh.setDebe(movDebe);
                            dh.setHaber(movHaber);
                            dh.write();
                            //System.out.println("Clave DEBHAB nueva: " + dh.getCuentaAñoMes() + "  Debe: " + dh.getDebe() + "  Haber: " + dh.getHaber());
                        }
                        rsDebHab.close();                        
                        rsDebHab = null;
                    }
                    //System.out.println("Movimiento: " + mc.getFechaAsientoApunte());
                
                }
                rs.close();
                rs = null;
            } catch (SQLException ex) {
                Logger.getLogger(ReactualizacionContable.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Año: " + anioMov + "  Mes: " + mesMov);
            i = i + 100;
        } while (numeroMovimientosLeidos > 0);
        
    }
    
    
    private void oldRecalculaDebHabDesdeMovcon(){
        // Con esta sentencia obtendremos el último apunte contable
        String strSql = "SELECT MAX(MOVCON_FECH_ASTO_APT) FROM MOVCON";
        String claveFechaAsientoApunte;
        int diaApunte;
        
        // Primero averiguamos el año y el mes del ultimo movimiento contable
        int numeroDeFilas = BaseDatos.countRows(strSql);
        if(numeroDeFilas == 1){
            try {
                rs = m.query(strSql);
                rs.next();
                claveFechaAsientoApunte = String.valueOf(rs.getLong(1));
                anioHasta = Integer.valueOf(claveFechaAsientoApunte.substring(0, 4));
                mesHasta = Integer.valueOf(claveFechaAsientoApunte.substring(4, 6));
                diaApunte = Integer.valueOf(claveFechaAsientoApunte.substring(6, 8));
                System.out.println("Clave : " + claveFechaAsientoApunte + " Año: " + anioHasta + "  Mes: " + mesHasta + "  Día: " + diaApunte);
                rs.close();
            } catch (SQLException e) {
                                
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                System.out.println("Error al leer el último movimiento contable.");
            }
        }else{
            System.out.println("Fallo al leer el último registro de Movimientos Contables");
        }
        
        // Vamos a ir totalizando mes a mes los movimientos contables en el DebHab correspondiente
        anio = fechaInicio.getAnio();
        mes = fechaInicio.getMes();
        
        // Vamos a recorrer todas las cuentas una a una y por cada una de ellas sumaremos 
        // los movimientos contables mes a mes para el DEBE y el HABER, si los dos son 0 ->
        // no hay que grabar nada en el DEBHAB
        ResultSet rsCuentas = null;
        ResultSet rsMovimiento = null;
        Cuenta cuenta = new Cuenta();
        String strSqlLeeCuentas = "SELECT * FROM CONTAB";
        String strSqlLeeMoviento = "";
        double debe, haber;
        
        numeroDeFilas = BaseDatos.countRows(strSqlLeeCuentas);
        if (numeroDeFilas > 0) {
            try {
                rsCuentas = m.query(strSqlLeeCuentas);
                // Recorremos cada cuenta
                while (rsCuentas.next() == true) {
                    
                    cuenta.read(rsCuentas);

                    System.out.println("Cuenta: " + cuenta.getCuenta());
                    // Recorremos cada mes para esa cuenta
                    while (anio <= anioHasta) {
                        if ((anio == anioHasta && mes <= mesHasta) || anio < anioHasta) {
                            debe = haber = 0.0;
                            // DEBE cuentas de primer y segundo grado
                            if (cuenta.getGrado().equalsIgnoreCase("1") || cuenta.getGrado().equalsIgnoreCase("2")) {
                                strSqlLeeMoviento = "SELECT SUM(MOVCON_IMPORTE) FROM MOVCON WHERE "
                                        + "MOVCON_CUENTA LIKE '" + cuenta.getCuenta() + "%' AND "
                                        + "MOVCON_CLAVE < 50 AND "
                                        + "MOVCON_FECH_ASTO_APT LIKE '" + String.format("%04d%02d", anio, mes) + "%'";
                            }else{
                                strSqlLeeMoviento = "SELECT SUM(MOVCON_IMPORTE) FROM MOVCON WHERE "
                                        + "MOVCON_CUENTA LIKE '" + cuenta.getCuenta() + "' AND "
                                        + "MOVCON_CLAVE < 50 AND "
                                        + "MOVCON_FECH_ASTO_APT LIKE '" + String.format("%04d%02d", anio, mes) + "%'";
                            }
                                
                            rsMovimiento = m.query(strSqlLeeMoviento);
                            // Si tiene movimientos con esas condiciones
                            if(rsMovimiento.next()){
                                debe = rsMovimiento.getDouble(1);
                            }else{
                                // No tiene movimientos con esas condiciones
                                debe = 0.0;
                            }
                            
                            
                            // HABER cuentas de primer y segundo grado
                            if (cuenta.getGrado().equalsIgnoreCase("1") || cuenta.getGrado().equalsIgnoreCase("2")) {
                                strSqlLeeMoviento = "SELECT SUM(MOVCON_IMPORTE) FROM MOVCON WHERE "
                                        + "MOVCON_CUENTA LIKE '" + cuenta.getCuenta() + "%' AND "
                                        + "MOVCON_CLAVE > 49 AND "
                                        + "MOVCON_FECH_ASTO_APT LIKE '" + String.format("%04d%02d", anio, mes) + "%'";
                            }else{
                                strSqlLeeMoviento = "SELECT SUM(MOVCON_IMPORTE) FROM MOVCON WHERE "
                                        + "MOVCON_CUENTA LIKE '" + cuenta.getCuenta() + "' AND "
                                        + "MOVCON_CLAVE > 49 AND "
                                        + "MOVCON_FECH_ASTO_APT LIKE '" + String.format("%04d%02d", anio, mes) + "%'";
                            }
                                
                            rsMovimiento = m.query(strSqlLeeMoviento);
                            // Si tiene movimientos con esas condiciones
                            if(rsMovimiento.next()){
                                haber = rsMovimiento.getDouble(1);
                            }else{
                                // No tiene movimientos con esas condiciones
                                haber = 0.0;
                            }
                            
                            System.out.println("Cuenta: " + cuenta.getCuenta() + " Año: " + anio + "  Mes: " + mes + "  Debe: " + debe + "  Haber: " + haber);
                        }

                        mes++;
                        if (mes > 14) {
                            anio++;
                            mes = 1;
                        }
                    }
                }

            } catch (SQLException e) {

                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                System.out.println("Error al leer el último movimiento contable.");
            }
        } else {
            System.out.println("Fallo al leer el último registro de Movimientos Contables");
        }                                 
    }
    
    
}

