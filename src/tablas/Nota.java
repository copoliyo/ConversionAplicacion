package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;



public class Nota {
	private String empresa;
	private String fichero;
	private String clave;
	private String nota;

	public Nota(){
		empresa = DatosComunes.eEmpresa;
		fichero = "";
		clave = "";
		nota = "";
	}

	public Nota(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				fichero = rs.getString("NOTASF_FICHERO").trim();
				clave  = rs.getString("NOTASF_CLAVE").trim();
				nota = rs.getString("NOTASF_NOTA").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Nota!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			fichero = rs.getString("NOTASF_FICHERO").trim();
			clave  = rs.getString("NOTASF_CLAVE").trim();
			nota = rs.getString("NOTASF_NOTA").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Nota!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO NOTASF " +
						   "(EMPRESA, " +
						   "NOTASF_FICHERO, " +
						   "NOTASF_CLAVE, " +
						   "NOTASF_NOTA) " +						   
				           "VALUES (?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "NOTASF_FICHERO = ?, " +
						   "NOTASF_CLAVE = ?, " +
						   "NOTASF_NOTA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(fichero, 6));
			ps.setString(i++, Cadena.left(clave, 16));
			ps.setString(i++, Cadena.left(nota, 3600));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(fichero, 6));
			ps.setString(i++, Cadena.left(clave, 16));
			ps.setString(i++, Cadena.left(nota, 3600));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Nota!!!");
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
}
