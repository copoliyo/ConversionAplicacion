package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class AsientoAutomatico {
	private String empresa;
	private int codigo;
	private String descripcion;

	public AsientoAutomatico(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		descripcion = "";
	}

	public AsientoAutomatico(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("ASTAUC_CODIGO");
				descripcion = rs.getString("ASTAUC_DESCRIP").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de AsientoAutomatico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("ASTAUC_CODIGO");
			descripcion = rs.getString("ASTAUC_DESCRIP").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de AsientoAutomatico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ASTAUC " +
						   "(EMPRESA, " +
						   "ASTAUC_CODIGO, " +
						   "ASTAUC_DESCRIP) " +						   
				           "VALUES (?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ASTAUC_CODIGO = ?, " +
						   "ASTAUC_DESCRIP = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(descripcion, 13));
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(descripcion, 13));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de AsientoAutomatico!!!");
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
