package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class ClaveContable {
	private String empresa;
	private int clave;
	private String descripcion;
	private int actualizaIva;
	private int actualizaPrevisiones;
	private int actualizaAcumulados;

	public ClaveContable(){
		empresa = DatosComunes.eEmpresa;
		clave = 0;
		descripcion = "";
		actualizaIva = 0;
		actualizaPrevisiones = 0;
		actualizaAcumulados = 0;
	}

	public ClaveContable(ResultSet rs){
		try{
			if(rs.next() == true){				
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
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			clave = rs.getInt("CLCEPC_KEY");
			descripcion = rs.getString("CLCEPC_DESCRIP").trim();
			actualizaIva = rs.getInt("CLCEPC_ACT_IVA");
			actualizaPrevisiones = rs.getInt("CLCEPC_ACT_PREV");
			actualizaAcumulados = rs.getInt("CLCEPC_ACT_ACUM");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ClaveContable!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CLCEPC " +
						   "(EMPRESA, " +
						   "CLCEPC_KEY, " +
						   "CLCEPC_DESCRIP, " +
						   "CLCEPC_ACT_IVA, " +
						   "CLCEPC_ACT_PREV, " +
						   "CLCEPC_ACT_ACUM) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CLCEPC_KEY = ?, " +
						   "CLCEPC_DESCRIP = ?, " +
						   "CLCEPC_ACT_IVA = ?, " +
						   "CLCEPC_ACT_PREV = ?, " +
						   "CLCEPC_ACT_ACUM = ? ";
		
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
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ClaveContable!!!");
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

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getActualizaIva() {
		return actualizaIva;
	}

	public void setActualizaIva(int actualizaIva) {
		this.actualizaIva = actualizaIva;
	}

	public int getActualizaPrevisiones() {
		return actualizaPrevisiones;
	}

	public void setActualizaPrevisiones(int actualizaPrevisiones) {
		this.actualizaPrevisiones = actualizaPrevisiones;
	}

	public int getActualizaAcumulados() {
		return actualizaAcumulados;
	}

	public void setActualizaAcumulados(int actualizaAcumulados) {
		this.actualizaAcumulados = actualizaAcumulados;
	}

}
