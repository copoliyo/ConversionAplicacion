package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class ConceptoGestion {
	private String empresa;
	private String fichero;
	private String keyFichero;
	private String concepto;

	public ConceptoGestion(){
		empresa = DatosComunes.eEmpresa;
		fichero = "";
		keyFichero = "";
		concepto = "";
	}

	public ConceptoGestion(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				fichero = rs.getString("GESCEP_FICHERO").trim();
				keyFichero = rs.getString("GESCEP_KEY_FICHERO").trim();
				concepto = rs.getString("GESCEP_CONCEPTO").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ConceptoGestion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			fichero = rs.getString("GESCEP_FICHERO").trim();
			keyFichero = rs.getString("GESCEP_KEY_FICHERO").trim();
			concepto = rs.getString("GESCEP_CONCEPTO").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ConceptoGestion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO GESCEP " +
						   "(EMPRESA, " +
						   "GESCEP_FICHERO, " +
						   "GESCEP_KEY_FICHERO, " +
						   "GESCEP_CONCEPTO) " +						   
				           "VALUES (?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "GESCEP_FICHERO = ?, " +
						   "GESCEP_KEY_FICHERO = ?, " +
						   "GESCEP_CONCEPTO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(fichero, 8));
			ps.setString(i++, Cadena.left(keyFichero, 14));
			ps.setString(i++, Cadena.left(concepto, 50));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(fichero, 8));
			ps.setString(i++, Cadena.left(keyFichero, 14));
			ps.setString(i++, Cadena.left(concepto, 50));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ConceptoGestion!!!");
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

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public String getKeyFichero() {
		return keyFichero;
	}

	public void setKeyFichero(String keyFichero) {
		this.keyFichero = keyFichero;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
}
