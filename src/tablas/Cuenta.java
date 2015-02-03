package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import util.Apariencia;
import util.BaseDatos;
import util.Cadena;

/* @author Jesus Marcos Gonzalez
 *  
 */

/**
 *
 * @author Txus
 */

public class Cuenta {

    private String empresa;
    private String grado;
    private String cuenta;
    private int centro;
    private String titulo;
    private int activo;
    private int extenOtroFichero;
    private double saldo;
    private double saldoUltimaDepuracion;

    /**
     *
     */
    public Cuenta() {
        empresa = DatosComunes.eEmpresa;
        grado = "";
        cuenta = "";
        centro = DatosComunes.centroCont;
        titulo = "";
        activo = 0;
        extenOtroFichero = 0;
        saldo = 0.0;
        saldoUltimaDepuracion = 0.0;
    }

    /**
     *
     * @param rs
     */
    public Cuenta(ResultSet rs) {
        try {
            if (rs.next() == true) {
                empresa = rs.getString("EMPRESA").trim();
                grado = rs.getString("CONTAB_GRADO").trim();
                cuenta = rs.getString("CONTAB_CUENTA").trim();
                centro = rs.getInt("CONTAB_CENTRO");
                titulo = rs.getString("CONTAB_TITULO").trim();
                activo = rs.getInt("CONTAB_ACTIVO");
                extenOtroFichero = rs.getInt("CONTAB_EXTENS_OTRO_FICHERO");
                saldo = rs.getDouble("CONTAB_SALDO");
                saldoUltimaDepuracion = rs.getDouble("CONTAB_SALDO_ULTDEPMOV");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error en lectura fichero de Cuenta!!!");
            if (DatosComunes.enDebug) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param rs
     */
    public void read(ResultSet rs) {
        try {
            empresa = rs.getString("EMPRESA").trim();
            grado = rs.getString("CONTAB_GRADO").trim();
            cuenta = rs.getString("CONTAB_CUENTA").trim();
            centro = rs.getInt("CONTAB_CENTRO");
            titulo = rs.getString("CONTAB_TITULO").trim();
            activo = rs.getInt("CONTAB_ACTIVO");
            extenOtroFichero = rs.getInt("CONTAB_EXTENS_OTRO_FICHERO");
            saldo = rs.getDouble("CONTAB_SALDO");
            saldoUltimaDepuracion = rs.getDouble("CONTAB_SALDO_ULTDEPMOV");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error en lectura fichero de Cuenta!!!");
            if (DatosComunes.enDebug) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Con este método leemos una cuenta pasando tan sólo una consulta SQL
     * @param strSql
     * @return el numero de registros leidos
     */
    public int read(String strSql) {
        
        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        int registrosLeidos = BaseDatos.countRows(strSql);

        if (registrosLeidos != 0) {
            try {
                rsSql = m.query(strSql);
                // Como ya tenemos el ResultSet se lo pasamos al mñrodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    this.read(rsSql);
                    // Cerramos para evitar gastar memoria
                    rsSql.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Cuentas Contables");
            }
        }

        return registrosLeidos;
    }

	// Leemos una cuenta pasando la cuenta y el centro
    // Si la hemos leñdo bien, devolvemos TRUE, en caso de problemas FALSE

    /**
     *
     * @param strCuenta
     * @param centro
     * @return
     */
        public boolean read(String strCuenta, int centro) {
        boolean lecturaOk = true;

        String strSqlCuenta = "SELECT * FROM CONTAB WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND CONTAB_CENTRO = " + centro
                + " AND CONTAB_CUENTA = '" + strCuenta.trim() + "' LIMIT 1";

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al mñrodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    this.read(rsSql);
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
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Cuentas Contables");
            }            
        }else{
            lecturaOk = false;
        }

        return lecturaOk;
    }

    /**
     *
     * @param strCuenta
     * @param centro
     * @return Devuelve TRUE si existe la cuenta.
     */
    public boolean existeCuenta(String strCuenta, int centro) {
        boolean existe = false;
        String strSqlCuenta = "SELECT * FROM CONTAB WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND CONTAB_CENTRO = " + centro
                + " AND CONTAB_CUENTA = '" + strCuenta.trim() + "' LIMIT 1";

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            existe = true;
        }

        return existe;
    }

    // Mñtodo estñtico para saber si una cuenta tiene saldo para evitar borrarla por equivocacion

    /**
     *
     * @param strCuenta
     * @param centro
     * @return
     */
        public static boolean cuentaConSaldo(String strCuenta, int centro) {

        double dSaldo = 0.0;

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        Cuenta cuentaSaldo = new Cuenta();

        boolean tieneSaldo = false;
        String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND CONTAB_CENTRO = " + centro
                + " AND CONTAB_CUENTA = '" + strCuenta.trim() + "' LIMIT 1";

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al mñtodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    cuentaSaldo.read(rsSql);
                    dSaldo = cuentaSaldo.getSaldo();
                }
                // Cerramos para evitar gastar memoria
                rsSql.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block				
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Cuentas Contables");
            }

            if (dSaldo != 0.0) {
                tieneSaldo = true;
            }
        }

        return tieneSaldo;
    }

    // Como se va a utilizar muchñsimo, vamos a crear un mñtodo sñlo para consultar el saldo

    /**
     *
     * @param strCuenta
     * @param centro
     * @return
     */
        public double getSaldoCuenta(String strCuenta, int centro) {

        double dSaldo = 0.0;

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND CONTAB_CENTRO = " + centro
                + " AND CONTAB_CUENTA = '" + strCuenta.trim() + "' LIMIT 1";

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al mñtodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    this.read(rsSql);
                    dSaldo = this.getSaldo();
                }
                // Cerramos para evitar gastar memoria
                rsSql.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block				
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Cuentas Contables");
            }
        }
        return dSaldo;
    }

    /**
     *
     * @param strCuenta String - Cuenta contable     
     * @param mes Int - Hasta Mes 
     * @return any Int - Hasta Año
     */            
    public double getSaldoCuentaMesAny(String strCuenta, int mes, int any){
        double dSaldo = 0.0, dDebe = 0.0, dHaber = 0.0;
        String strHastaMes, strHastaAny, strClaveDebeHaberHasta, strClaveDebeHaberDesde;
        String strEspacios = "        ";

        strHastaMes = Cadena.enteroCerosIzquierda(mes, 2);
        strHastaAny = String.valueOf(any);
        
        ResultSet rsSql = null;
        MysqlConnect m = null;
        
        m = MysqlConnect.getDbCon();

        // 43000..........    -> Cuenta (9) + Año(4) + Mes(2) en este caso espacios (Año y Mes) -> 6 espacios
        strClaveDebeHaberDesde = strCuenta + strEspacios.substring(0, 9 - strCuenta.trim().length()) + "      ";
        
        strClaveDebeHaberHasta = strCuenta;
       
        if(strCuenta.length() < 9)
            strClaveDebeHaberHasta += strEspacios.substring(0, 9 - strCuenta.trim().length());
                                 
        strClaveDebeHaberHasta += String.valueOf(any) + Cadena.enteroCerosIzquierda(mes, 2);                        
        // No lo hacemos así....
        String strSqlCuenta = "SELECT SUM(DEBHAB_DEBE), SUM(DEBHAB_HABER) FROM DEBHAB WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' AND "                
                + "DEBHAB_CTA_ANYMES <= '" + strClaveDebeHaberHasta + "' AND "
                + "DEBHAB_CTA_ANYMES >= '" + strClaveDebeHaberDesde + "'";
        // Lo hacemos así
        String strSqlCuentaCuentaRegistros = "SELECT * FROM DEBHAB WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' AND "                
                + "DEBHAB_CTA_ANYMES <= '" + strClaveDebeHaberHasta + "' AND "
                + "DEBHAB_CTA_ANYMES >= '" + strClaveDebeHaberDesde + "'";
        
        if (BaseDatos.countRows(strSqlCuentaCuentaRegistros) > 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al mñtodo 'read(ResultSet rs)'.
                if (rsSql.next()) {                    
                    dSaldo = rsSql.getDouble(1) - rsSql.getDouble(2);
                    dSaldo = Double.valueOf(Cadena.redondear2Decimales(dSaldo));
                }
                // Cerramos para evitar gastar memoria
                rsSql.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block				
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(4, "<center>Error en lectura de Saldo<br>en el fichero de Debe/Haber<center>");
            }
        }
        
        return dSaldo;
    }

    /**
     *
     * @param strCuenta Código de cuenta 
     * @param centro
     * @param fechaAsientoApunte
     * @return
     */
    public double getSaldoCuentaEnFecha(String strCuenta, int centro, String fechaAsientoApunte) {

        double dSaldo = 0.0, dDebe = 0.0, dHaber = 0.0;

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        String strClaveFechaAsientoApunte = fechaAsientoApunte;

		// Primero averiguamos el DEBE hasta la fecha
        String strSqlCuenta = "SELECT SUM(MOVCON_IMPORTE) FROM MOVCON WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND MOVCON_CENTRO = " + centro
                + " AND MOVCON_CUENTA = '" + strCuenta.trim() + "' "
                + " AND MOVCON_FECH_ASTO_APT <= '" + fechaAsientoApunte + "' "
                + " AND MOVCON_CLAVE < 50";

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al mñtodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    dDebe = rsSql.getDouble(1);
                }
                // Cerramos para evitar gastar memoria
                rsSql.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block				
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Cuentas Contables");
            }
        }

        // Ahora el HABER
        strSqlCuenta = "SELECT SUM(MOVCON_IMPORTE) FROM MOVCON WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND MOVCON_CENTRO = " + centro
                + " AND MOVCON_CUENTA = '" + strCuenta.trim() + "' "
                + " AND MOVCON_FECH_ASTO_APT < '" + strClaveFechaAsientoApunte + "' "
                + " AND MOVCON_CLAVE > 49";

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al mñtodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    dHaber = rsSql.getDouble(1);
                }
                // Cerramos para evitar gastar memoria
                rsSql.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block				
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Cuentas Contables");
            }
        }

        dSaldo = dDebe - dHaber;

        return dSaldo;
    }

    // Devolvemos 'true' si no hay errores, 'false' si hay alguna excepciñn.

    /**
     *
     * @return
     */
        public boolean write() {
        boolean escrituraCorrecta = true;
        boolean falloCuentasSuperiores = false;
        PreparedStatement ps = null;

        String sqlInsert = "INSERT INTO CONTAB (EMPRESA, "
                + "CONTAB_GRADO, "
                + "CONTAB_CUENTA, "
                + "CONTAB_CENTRO, "
                + "CONTAB_TITULO, "
                + "CONTAB_ACTIVO, "
                + "CONTAB_EXTENS_OTRO_FICHERO, "
                + "CONTAB_SALDO, "
                + "CONTAB_SALDO_ULTDEPMOV)" + ""
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "EMPRESA = ?, "
                + "CONTAB_GRADO = ?, "
                + "CONTAB_CUENTA = ?, "
                + "CONTAB_CENTRO = ?, "
                + "CONTAB_TITULO = ?, "
                + "CONTAB_ACTIVO = ?, "
                + "CONTAB_EXTENS_OTRO_FICHERO = ?, "
                + "CONTAB_SALDO = ?, "
                + "CONTAB_SALDO_ULTDEPMOV = ?";

        if (this.grado == "2" && this.grado == "3") {
            if (!existenCuentasSuperiores(this.cuenta)) {
                falloCuentasSuperiores = true;
                escrituraCorrecta = false;
            }
        }

        if (!falloCuentasSuperiores) {
            try {
                ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
                int i = 1;
                // Insert
                ps.setString(i++, Cadena.left(empresa, 2));
                ps.setString(i++, Cadena.left(grado, 1));
                ps.setString(i++, Cadena.left(cuenta, 9));
                ps.setInt(i++, centro);
                ps.setString(i++, Cadena.left(titulo, 30));
                ps.setInt(i++, activo);
                ps.setInt(i++, extenOtroFichero);
                ps.setDouble(i++, saldo);
                ps.setDouble(i++, saldoUltimaDepuracion);
                // Update
                ps.setString(i++, Cadena.left(empresa, 2));
                ps.setString(i++, Cadena.left(grado, 1));
                ps.setString(i++, Cadena.left(cuenta, 9));
                ps.setInt(i++, centro);
                ps.setString(i++, Cadena.left(titulo, 30));
                ps.setInt(i++, activo);
                ps.setInt(i++, extenOtroFichero);
                ps.setDouble(i++, saldo);
                ps.setDouble(i++, saldoUltimaDepuracion);

                ps.execute();

            } catch (SQLException e) {
                escrituraCorrecta = false;
                if (DatosComunes.enDebug) {
                    JOptionPane.showMessageDialog(null,
                            "Error en escritura fichero de Cuenta!!!");
                    e.printStackTrace();
                }
            }
        }
        return escrituraCorrecta;
    }

	// Borramos una CUENTA de un CENTRO concreto.
    // Devolvemos el nñmero de registros borrados o -1 si hay error

    /**
     *
     * @param strCuenta
     * @param centro
     * @return
     */
    public int delete(String strCuenta, int centro) {
        int registrosBorrados = 0;

        Statement ps = null;

        String sqlDelete = "DELETE FROM CONTAB WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                + "CONTAB_CENTRO = " + centro + " AND "
                + "CONTAB_CUENTA = '" + strCuenta + "'";

        try {
            ps = MysqlConnect.db.conn.createStatement();

            registrosBorrados = ps.executeUpdate(sqlDelete);

        } catch (SQLException e) {
            registrosBorrados = -1;
            if (DatosComunes.enDebug) {
                JOptionPane.showMessageDialog(null,
                        "Error en borrado fichero de Cuenta!!!");
                e.printStackTrace();
            }
        }

        return registrosBorrados;
    }

    /**
     *
     * @param cuenta
     * @return
     */
    public boolean existenCuentasSuperiores(String cuenta) {
        boolean cuentasSuperioresOk = true;
        int grado = 0;
        String cuentaSuperiorPrimerGrado = "";
        String cuentaSuperiorSegundoGrado = "";
        String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND CONTAB_CENTRO = " + DatosComunes.centroCont
                + " AND CONTAB_CUENTA = '";
        String strExisteCuenta = "";

        if (cuenta.length() > 5) {
            cuentaSuperiorSegundoGrado = cuenta.substring(0, 5);
            cuentaSuperiorPrimerGrado = cuenta.substring(0, 3);
        }

        if (cuenta.length() > 3 && cuenta.length() <= 5) {
            cuentaSuperiorPrimerGrado = cuenta.substring(0, 3);
        }

        if (cuentaSuperiorSegundoGrado.length() > 0) {
            strExisteCuenta = strSqlCuenta + cuentaSuperiorSegundoGrado + "' AND CONTAB_GRADO = 2";
            if (BaseDatos.countRows(strExisteCuenta) == 0) {
                cuentasSuperioresOk = false;
                JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>"
                        + "No existe cuenta superior!!!: " + cuentaSuperiorSegundoGrado
                        + "</strong></font></html>");
            }
        }

        if (cuentaSuperiorPrimerGrado.length() > 0) {
            strExisteCuenta = strSqlCuenta + cuentaSuperiorPrimerGrado + "' AND CONTAB_GRADO = 1";
            if (BaseDatos.countRows(strExisteCuenta) == 0) {
                cuentasSuperioresOk = false;
                JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>"
                        + "No existe cuenta superior!!!: " + cuentaSuperiorPrimerGrado
                        + "</strong></font></html>");
            }
        }

        return cuentasSuperioresOk;
    }

    /**
     *
     * @return
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     *
     * @param empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     *
     * @return
     */
    public String getGrado() {
        return grado;
    }

    /**
     *
     * @param grado
     */
    public void setGrado(String grado) {
        this.grado = grado;
    }

    /**
     *
     * @return
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     *
     * @param cuenta
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     *
     * @return
     */
    public int getCentro() {
        return centro;
    }

    /**
     *
     * @param centro
     */
    public void setCentro(int centro) {
        this.centro = centro;
    }

    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return
     */
    public int getActivo() {
        return activo;
    }

    /**
     *
     * @param activo
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }

    /**
     *
     * @return
     */
    public int getExtenOtroFichero() {
        return extenOtroFichero;
    }

    /**
     *
     * @param extenOtroFichero
     */
    public void setExtenOtroFichero(int extenOtroFichero) {
        this.extenOtroFichero = extenOtroFichero;
    }

    /**
     *
     * @return
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     *
     * @param saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     *
     * @return
     */
    public double getSaldoUltimaDepuracion() {
        return saldoUltimaDepuracion;
    }

    /**
     *
     * @param saldoUltimaDepuracion
     */
    public void setSaldoUltimaDepuracion(double saldoUltimaDepuracion) {
        this.saldoUltimaDepuracion = saldoUltimaDepuracion;
    }
}
