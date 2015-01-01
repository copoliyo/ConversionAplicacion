package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CpCalle {
	private String empresa;
	private int codigo;
	private String calle;
	private String impresion;

	public CpCalle(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		calle = "";
		impresion = "";
	}

	public CpCalle(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("CPCALL_CODIGO");
				calle = rs.getString("CPCALL_CALLE".trim());
				impresion = rs.getString("CPCALL_IMPRESION").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpCalle!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("CPCALL_CODIGO");
			calle = rs.getString("CPCALL_CALLE").trim();
			impresion = rs.getString("CPCALL_IMPRESION").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpCalle!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CPCALL " +
						   "(EMPRESA, " +
						   "CPCALL_CODIGO, " +
						   "CPCALL_CALLE, " +
						   "CPCALL_IMPRESION) " +						   
				           "VALUES (?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CPCALL_CODIGO = ?, " +
						   "CPCALL_CALLE = ?, " +
						   "CPCALL_IMPRESION = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(calle, 30));
			ps.setString(i++, Cadena.left(impresion, 30));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(calle, 30));
			ps.setString(i++, Cadena.left(impresion, 30));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CpCalle!!!");
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

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getImpresion() {
		return impresion;
	}

	public void setImpresion(String impresion) {
		this.impresion = impresion;
	}
}
