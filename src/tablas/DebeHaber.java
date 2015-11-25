package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import util.Cadena;



public class DebeHaber {
	private String empresa;
	private String cuentaAñoMes;
	private double debe;
	private double haber;

	public DebeHaber(){
		empresa = DatosComunes.eEmpresa;
		cuentaAñoMes = "";
		debe = 0.0;
		haber = 0.0;
	}

	public DebeHaber(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cuentaAñoMes = rs.getString("DEBHAB_CTA_ANYMES").trim();
				debe = rs.getDouble("DEBHAB_DEBE");
				haber = rs.getDouble("DEBHAB_HABER");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de DebeHaber!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA".trim());
			cuentaAñoMes = rs.getString("DEBHAB_CTA_ANYMES").trim();
			debe = rs.getDouble("DEBHAB_DEBE");
			haber = rs.getDouble("DEBHAB_HABER"); 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de DebeHaber!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}                                       
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO DEBHAB " +
						   "(EMPRESA, " +
						   "DEBHAB_CTA_ANYMES, " +
						   "DEBHAB_DEBE, " +
						   "DEBHAB_HABER) " +
				           "VALUES (?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "DEBHAB_CTA_ANYMES = ?, " +
						   "DEBHAB_DEBE = ?, " +
						   "DEBHAB_HABER = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuentaAñoMes, 15));
			ps.setDouble(i++, debe);
			ps.setDouble(i++, haber);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuentaAñoMes, 15));
			ps.setDouble(i++, debe);
			ps.setDouble(i++, haber);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de DebeHaber!!!");
				e.printStackTrace();
			}
		}
	}
	
        public int delete(String strClave) {
            int registrosBorrados = 0;

            Statement ps = null;

            String sqlDelete = "DELETE FROM DEBHAB WHERE "
                    + "EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                    + "DEBHAB_CTA_ANYMES = '" + strClave + "'";                    

            try {
                ps = MysqlConnect.db.conn.createStatement();

                registrosBorrados = ps.executeUpdate(sqlDelete);

            } catch (SQLException e) {
                registrosBorrados = -1;
                if (DatosComunes.enDebug) {
                    JOptionPane.showMessageDialog(null,
                            "Error en borrado fichero de Debehaber!!!");
                    e.printStackTrace();
                }
            }

            return registrosBorrados;
        }
        
        
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCuentaAñoMes() {
		return cuentaAñoMes;
	}

	public void setCuentaAñoMes(String cuentaAñoMes) {
		this.cuentaAñoMes = cuentaAñoMes;
	}

	public double getDebe() {
		return debe;
	}

	public void setDebe(double debe) {
		this.debe = debe;
	}

	public double getHaber() {
		return haber;
	}

	public void setHaber(double haber) {
		this.haber = haber;
	}
}
