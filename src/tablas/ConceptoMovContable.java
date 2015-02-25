package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import util.Apariencia;
import util.BaseDatos;

import util.Cadena;

public class ConceptoMovContable {

    private String empresa;
    private long fechaAsientoApunte;
    private String concepto;

    public ConceptoMovContable() {
        empresa = DatosComunes.eEmpresa;
        fechaAsientoApunte = (long) 0;
        concepto = "";
    }

    public ConceptoMovContable(ResultSet rs) {
        try {
            if (rs.next() == true) {
                empresa = rs.getString("EMPRESA").trim();
                fechaAsientoApunte = rs.getLong("MOCCEP_FECH_ASTO_APT");
                concepto = rs.getString("MOCCEP_CONCEPTO").trim();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error en lectura fichero de ConceptoMovContable!!!");
            if (DatosComunes.enDebug) {
                e.printStackTrace();
            }
        }
    }

    public void read(ResultSet rs) {
        try {
            empresa = rs.getString("EMPRESA").trim();
            fechaAsientoApunte = rs.getLong("MOCCEP_FECH_ASTO_APT");
            concepto = rs.getString("MOCCEP_CONCEPTO").trim();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error en lectura fichero de ConceptoMovContable!!!");
            if (DatosComunes.enDebug) {
                e.printStackTrace();
            }
        }
    }

    public boolean read(int fecha, int asiento, int apunte) {
        boolean lecturaOk = true;

        String claveFechaAsientoApunte = String.valueOf(fecha)
                + String.format("%05d", asiento)
                + String.format("%05d", apunte);

        String strSql = "SELECT * FROM MOCCEP WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND MOCCEP_FECH_ASTO_APT = '" + claveFechaAsientoApunte + "' LIMIT 1";

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
                lecturaOk = false;
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Movimientos Contables");
            }
        } else {
            lecturaOk = false;
        }

        rsSql = null;
        m = null;

        return lecturaOk;
    }

    public void write() {
        PreparedStatement ps = null;

        String sqlInsert = "INSERT INTO MOCCEP "
                + "(EMPRESA, "
                + "MOCCEP_FECH_ASTO_APT, "
                + "MOCCEP_CONCEPTO) "
                + "VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "EMPRESA = ?, "
                + "MOCCEP_FECH_ASTO_APT = ?, "
                + "MOCCEP_CONCEPTO = ? ";

        try {
            ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
            int i = 1;
            // Insert
            ps.setString(i++, Cadena.left(empresa, 2));
            ps.setLong(i++, fechaAsientoApunte);
            ps.setString(i++, Cadena.left(concepto, 30));

            // Update
            ps.setString(i++, Cadena.left(empresa, 2));
            ps.setLong(i++, fechaAsientoApunte);
            ps.setString(i++, Cadena.left(concepto, 30));

            ps.execute();

        } catch (SQLException e) {
            if (DatosComunes.enDebug) {
                JOptionPane.showMessageDialog(null,
                        "Error en escritura fichero de ConceptoMovContable!!!");
                e.printStackTrace();
            }
        }
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public long getFechaAsientoApunte() {
        return fechaAsientoApunte;
    }

    public void setFechaAsientoApunte(long fechaAsientoApunte) {
        this.fechaAsientoApunte = fechaAsientoApunte;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
