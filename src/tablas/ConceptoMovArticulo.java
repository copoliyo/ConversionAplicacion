package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class ConceptoMovArticulo {
	private String empresa;
	private int fecha;
	private int apunte;
	private String concepto;

	public ConceptoMovArticulo(){
		empresa = DatosComunes.eEmpresa;
		fecha = 0;
		apunte = 0;
		concepto = "";
	}

	public ConceptoMovArticulo(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				fecha = rs.getInt("MOACEP_FECHA");
				apunte = rs.getInt("MOACEP_APUNTE");
				concepto = rs.getString("MOACEP_CONCEPTO").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ConceptoMovArticulo!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			fecha = rs.getInt("MOACEP_FECHA");
			apunte = rs.getInt("MOACEP_APUNTE");
			concepto = rs.getString("MOACEP_CONCEPTO").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ConceptoMovArticulo!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO MOACEP " +
						   "(EMPRESA, " +
						   "MOACEP_FECHA, " +
						   "MOACEP_APUNTE) " +						   
				           "VALUES (?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "MOACEP_FECHA = ?, " +
						   "MOACEP_APUNTE = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, apunte);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, apunte);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ConceptoMovArticulo!!!");
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

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getApunte() {
		return apunte;
	}

	public void setApunte(int apunte) {
		this.apunte = apunte;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
}
