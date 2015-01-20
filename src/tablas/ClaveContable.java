package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;
import util.Apariencia;
import util.BaseDatos;

/**
 *
 * @author Txus
 */
public class ClaveContable {

    private String empresa;
    private int clave;
    private String descripcion;
    private int actualizaIva;
    private int actualizaPrevisiones;
    private int actualizaAcumulados;

    /**
     *
     */
    public ClaveContable() {
        empresa = DatosComunes.eEmpresa;
        clave = 0;
        descripcion = "";
        actualizaIva = 0;
        actualizaPrevisiones = 0;
        actualizaAcumulados = 0;
    }

    /**
     *
     * @param rs
     */
    public ClaveContable(ResultSet rs) {
        try {
            if (rs.next() == true) {
                empresa = rs.getString("EMPRESA").trim();
                clave = rs.getInt("CLCEPC_KEY");
                descripcion = rs.getString("CLCEPC_DESCRIP").trim();
                actualizaIva = rs.getInt("CLCEPC_ACT_IVA");
                actualizaPrevisiones = rs.getInt("CLCEPC_ACT_PREV");
                actualizaAcumulados = rs.getInt("CLCEPC_ACT_ACUM");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error en lectura fichero de ClaveContable!!!");
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
            clave = rs.getInt("CLCEPC_KEY");
            descripcion = rs.getString("CLCEPC_DESCRIP").trim();
            actualizaIva = rs.getInt("CLCEPC_ACT_IVA");
            actualizaPrevisiones = rs.getInt("CLCEPC_ACT_PREV");
            actualizaAcumulados = rs.getInt("CLCEPC_ACT_ACUM");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error en lectura fichero de ClaveContable!!!");
            if (DatosComunes.enDebug) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param clave
     * @return <code>true</code> si se ha leido la Clave Contable Correctamente
     * @return <code>false</code> si NO se ha leido la Clave Contable Correctamente
     */
    public boolean read(int clave) {
        boolean lecturaOk = false;

        String strSql = "SELECT * FROM CLCEPC WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND CLCEPC_KEY = " + clave
                + " LIMIT 1";

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        if (BaseDatos.countRows(strSql) != 0) {
            try {
                rsSql = m.query(strSql);
                // Como ya tenemos el ResultSet se lo pasamos al mñrodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    this.read(rsSql);
                    lecturaOk = true;
                    // Cerramos para evitar gastar memoria
                    rsSql.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Claves Contables");
            }
        }

        return lecturaOk;
    }

    /**
     *
     */
    public void write() {
        PreparedStatement ps = null;

        String sqlInsert = "INSERT INTO CLCEPC "
                + "(EMPRESA, "
                + "CLCEPC_KEY, "
                + "CLCEPC_DESCRIP, "
                + "CLCEPC_ACT_IVA, "
                + "CLCEPC_ACT_PREV, "
                + "CLCEPC_ACT_ACUM) "
                + "VALUES (?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "EMPRESA = ?, "
                + "CLCEPC_KEY = ?, "
                + "CLCEPC_DESCRIP = ?, "
                + "CLCEPC_ACT_IVA = ?, "
                + "CLCEPC_ACT_PREV = ?, "
                + "CLCEPC_ACT_ACUM = ? ";

        try {
            ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
            int i = 1;
            // Insert
            ps.setString(i++, Cadena.left(empresa, 2));
            ps.setInt(i++, clave);
            ps.setString(i++, Cadena.left(descripcion, 14));
            ps.setInt(i++, actualizaIva);
            ps.setInt(i++, actualizaPrevisiones);
            ps.setInt(i++, actualizaAcumulados);

            // Update
            ps.setString(i++, Cadena.left(empresa, 2));
            ps.setInt(i++, clave);
            ps.setString(i++, Cadena.left(descripcion, 14));
            ps.setInt(i++, actualizaIva);
            ps.setInt(i++, actualizaPrevisiones);
            ps.setInt(i++, actualizaAcumulados);

            ps.execute();

        } catch (SQLException e) {
            if (DatosComunes.enDebug) {
                JOptionPane.showMessageDialog(null,
                        "Error en escritura fichero de ClaveContable!!!");
                e.printStackTrace();
            }
        }
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
    public int getClave() {
        return clave;
    }

    /**
     *
     * @param clave
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public int getActualizaIva() {
        return actualizaIva;
    }

    /**
     *
     * @param actualizaIva
     */
    public void setActualizaIva(int actualizaIva) {
        this.actualizaIva = actualizaIva;
    }

    /**
     *
     * @return
     */
    public int getActualizaPrevisiones() {
        return actualizaPrevisiones;
    }

    /**
     *
     * @param actualizaPrevisiones
     */
    public void setActualizaPrevisiones(int actualizaPrevisiones) {
        this.actualizaPrevisiones = actualizaPrevisiones;
    }

    /**
     *
     * @return
     */
    public int getActualizaAcumulados() {
        return actualizaAcumulados;
    }

    /**
     *
     * @param actualizaAcumulados
     */
    public void setActualizaAcumulados(int actualizaAcumulados) {
        this.actualizaAcumulados = actualizaAcumulados;
    }

}
