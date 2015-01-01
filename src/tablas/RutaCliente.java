package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class RutaCliente {
	private String empresa;
	private int ruta;
	private String nombre;
	
	public RutaCliente(){
		empresa = DatosComunes.eEmpresa;
		ruta = 0;
		nombre =  "";		
	}
	
	public RutaCliente(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				ruta = rs.getInt("RUTCLI_RUTA");
				nombre =  rs.getString("RUTCLI_NOMBRE");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de RutaCliente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			ruta = rs.getInt("RUTCLI_RUTA");
			nombre =  rs.getString("RUTCLI_NOMBRE");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de RutaCliente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO RUTCLI " +
						   "(EMPRESA, " +
						   "RUTCLI_RUTA, " +
						   "RUTCLI_NOMBRE) " +						   
				           "VALUES (?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "RUTCLI_RUTA = ?, " +						   
						   "RUTCLI_NOMBRE = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, ruta);
			ps.setString(i++, Cadena.left(nombre, 30));			
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, ruta);
			ps.setString(i++, Cadena.left(nombre, 30));	
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de RutaCliente!!!");
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

	public int getRuta() {
		return ruta;
	}

	public void setRuta(int ruta) {
		this.ruta = ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
