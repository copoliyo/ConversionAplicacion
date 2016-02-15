/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;
///

import general.DatosComunes;
import general.MysqlConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
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
public class ReactualizacionContableOptimizado {
    int mes, anio, apunte;
    public static ResultSet rs = null;
    public MysqlConnect m = null;
    Fecha fechaInicio;
    int anioHasta, mesHasta;
    
    public ReactualizacionContableOptimizado(){
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
    
    class AuxDebeHaber{
        String cuentaAñoMes;
	double debe;
	double haber;
        
        AuxDebeHaber(){
            cuentaAñoMes = "";
            debe = 0.0;
            haber  = 0.0;
        }
        
        AuxDebeHaber(String cam, double saldoDebe, double saldoHaber){
            cuentaAñoMes = cam;
            debe = saldoDebe;
            haber  = saldoHaber;
        }
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
        anioMov = mesMov = 0;
        
        AuxDebeHaber auxDhMovcon = new AuxDebeHaber();
        AuxDebeHaber auxDh;
        int posicionEnMapa = 0;
        
        Map<String, AuxDebeHaber> mapaDh = new HashMap<String, AuxDebeHaber>();
        //Vector<AuxDebeHaber> vectorDH = new Vector<>();        
                
        do{
            strSqlMov = "SELECT * FROM MOVCON LIMIT 10000 OFFSET " + String.valueOf(i);
            
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
                    // Queremos la cuenta con espacios por detrás hasta 9 caracteres
                    if(strCuenta.length() < 9)
                        for(int j = strCuenta.length(); j < 9; j++)
                            strCuenta += " ";
                        
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
                       
                        auxDhMovcon.cuentaAñoMes = strCuenta + claveMovconFechaAstoApunte.substring(0, 4) + String.format("%02d", mesMov);
                        auxDhMovcon.debe = movDebe;
                        auxDhMovcon.haber = movHaber;
                                              
                        if((auxDh = mapaDh.get(auxDhMovcon.cuentaAñoMes)) == null){
                            auxDh = new AuxDebeHaber(auxDhMovcon.cuentaAñoMes, auxDhMovcon.debe, auxDhMovcon.haber);
                            mapaDh.put(auxDhMovcon.cuentaAñoMes, auxDh);
                        }else{                            
                            auxDh.debe += auxDhMovcon.debe;
                            auxDh.haber += auxDhMovcon.haber;
                            mapaDh.put(auxDhMovcon.cuentaAñoMes, auxDh);
                        }

                        // Ahora hay que hacer las cuentas superiores
                        // Segundo grado
                        strCuenta = strCuenta.substring(0, 5);
                        if(strCuenta.length() < 9)
                        for(int j = strCuenta.length(); j < 9; j++)
                            strCuenta += " ";
                        auxDhMovcon.cuentaAñoMes = strCuenta + claveMovconFechaAstoApunte.substring(0, 4) + String.format("%02d", mesMov);
                        auxDhMovcon.debe = movDebe;
                        auxDhMovcon.haber = movHaber;
                                              
                        if((auxDh = mapaDh.get(auxDhMovcon.cuentaAñoMes)) == null){
                            auxDh = new AuxDebeHaber(auxDhMovcon.cuentaAñoMes, auxDhMovcon.debe, auxDhMovcon.haber);
                            mapaDh.put(auxDhMovcon.cuentaAñoMes, auxDh);
                        }else{                            
                            auxDh.debe += auxDhMovcon.debe;
                            auxDh.haber += auxDhMovcon.haber;
                            mapaDh.put(auxDhMovcon.cuentaAñoMes, auxDh);
                        }   
                        
                        
                        //rsDebHab.close();                        
                        //rsDebHab = null;
                        // Primer Grado
                        strCuenta = strCuenta.substring(0, 3);
                        if(strCuenta.length() < 9)
                        for(int j = strCuenta.length(); j < 9; j++)
                            strCuenta += " ";
                        auxDhMovcon.cuentaAñoMes = strCuenta + claveMovconFechaAstoApunte.substring(0, 4) + String.format("%02d", mesMov);
                        auxDhMovcon.debe = movDebe;
                        auxDhMovcon.haber = movHaber;
                                              
                        if((auxDh = mapaDh.get(auxDhMovcon.cuentaAñoMes)) == null){
                            auxDh = new AuxDebeHaber(auxDhMovcon.cuentaAñoMes, auxDhMovcon.debe, auxDhMovcon.haber);
                            mapaDh.put(auxDhMovcon.cuentaAñoMes, auxDh);
                        }else{                            
                            auxDh.debe += auxDhMovcon.debe;
                            auxDh.haber += auxDhMovcon.haber;
                            mapaDh.put(auxDhMovcon.cuentaAñoMes, auxDh);
                        }
                        
                    }
                    //System.out.println("Movimiento: " + mc.getFechaAsientoApunte());
                
                }
                rs.close();
                rs = null;
            } catch (SQLException ex) {
                Logger.getLogger(ReactualizacionContableOptimizado.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Año: " + anioMov + "  Mes: " + mesMov + " Tamaño mapa: " + mapaDh.size() + " Registros MC: " + i);
            i = i + 10000;
        } while (numeroMovimientosLeidos > 0);
        
         System.out.println("Guardando valosres de DebHab en BD");
        // Recorremos el mapa
        // de paso lo grabamos en DEBHAB y actualizamos los saldos de las cuentas
        Cuenta cuenta = new Cuenta();
        DebeHaber debeHaber = new DebeHaber();
        
        for (Map.Entry<String, AuxDebeHaber> entry : mapaDh.entrySet()) {
            String key = entry.getKey();
            AuxDebeHaber value = entry.getValue();
            debeHaber.setEmpresa(DatosComunes.eEmpresa);
            debeHaber.setCuentaAñoMes(value.cuentaAñoMes);
            debeHaber.setDebe(value.debe);
            debeHaber.setHaber(value.haber);
            debeHaber.write();
            
            // Ahora ponemos el 
            
        
            //System.out.println(value.cuentaAñoMes + " " + value.debe + " " + value.haber);
        }
        
        // Intentamos recorrer el mapa por clave para grabar los saldos        
                        
        System.out.println("Reactualizando desde DebHab");
        Map mapOrdenado = new TreeMap(mapaDh);
        Set ref = mapOrdenado.keySet();
        Iterator it = ref.iterator();
        while (it.hasNext()) {
            auxDh = mapaDh.get((String)it.next());
            if(cuenta.existeCuenta(auxDh.cuentaAñoMes.substring(0,9).trim(), DatosComunes.centroCont)){
                cuenta.read(auxDh.cuentaAñoMes.substring(0,9).trim(), DatosComunes.centroCont);
                cuenta.setSaldo(cuenta.getSaldo() +  auxDh.debe - auxDh.haber);
                cuenta.write();            
                //System.out.println(cuenta.getCuenta() + " Saldo : " + cuenta.getSaldo());
            }else{
               System.out.println("No existe cuenta: " + auxDh.cuentaAñoMes.substring(0,9).trim() + " SE CREA!!!");
               cuenta.setActivo(1);
               cuenta.setCentro(DatosComunes.centroCont);
               cuenta.setCuenta(auxDh.cuentaAñoMes.substring(0,9).trim());
               cuenta.setEmpresa(DatosComunes.eEmpresa);
               cuenta.setExtenOtroFichero(0);
               if(cuenta.getCuenta().trim().length() == 3)
                    cuenta.setGrado("1");
               if(cuenta.getCuenta().trim().length() > 3 && cuenta.getCuenta().trim().length() < 8)
                    cuenta.setGrado("2");
               if(cuenta.getCuenta().trim().length() > 7)
                    cuenta.setGrado("3");
               cuenta.setSaldo(auxDh.debe - auxDh.haber);
               cuenta.setSaldoUltimaDepuracion(0.0);
               cuenta.setTitulo("Cuenta creada en React. Contable"); 
               cuenta.write();
            }
            
        }
        
        System.out.println("Tamaño del mapa: " + mapaDh.size());
        
        
        
        
        
    }
}

