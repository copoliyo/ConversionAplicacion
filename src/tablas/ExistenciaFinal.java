package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class ExistenciaFinal {
	private String empresa;
	private int cuentaA�oMes;
	private double existencias;

	public ExistenciaFinal(){
		empresa = DatosComunes.eEmpresa;
		cuentaA�oMes = 0;
		existencias = 0.0;
	}

	public ExistenciaFinal(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cuentaA�oMes = rs.getInt("EXTFIN_CTA_ANYMES");
				existencias = rs.getDouble("EXTFIN_EXISTENCIAS");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ExistenciaFinal!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{
			empresa = rs.getString("EMPRESA").trim();
			cuentaA�oMes = rs.getInt("EXTFIN_CTA_ANYMES");
			existencias = rs.getDouble("EXTFIN_EXISTENCIAS");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ExistenciaFinal!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO EXTFIN " +
						   "(EMPRESA, " +
						   "EXTFIN_CTA_ANYMES, " +
						   "EXTFIN_EXISTENCIAS) " +						   
				           "VALUES (?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "EXTFIN_CTA_ANYMES = ?, " +
						   "EXTFIN_EXISTENCIAS = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cuentaA�oMes);
			ps.setDouble(i++, existencias);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cuentaA�oMes);
			ps.setDouble(i++, existencias);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ExistenciaFinal!!!");
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

	public int getCuentaA�oMes() {
		return cuentaA�oMes;
	}

	public void setCuentaA�oMes(int cuentaA�oMes) {
		this.cuentaA�oMes = cuentaA�oMes;
	}

	public double getExistencias() {
		return existencias;
	}

	public void setExistencias(double existencias) {
		this.existencias = existencias;
	}
}
