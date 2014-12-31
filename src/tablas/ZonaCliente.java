package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class ZonaCliente {
	private String empresa;
	private int zona;
	private int centro;
	private String nombre;
	private int representante;

	public ZonaCliente(){
		empresa = DatosComunes.eEmpresa;
		zona = 0;
		centro = 0;
		nombre = "";
		representante = 0;
	}

	public ZonaCliente(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				zona = rs.getInt("ZONCLI_ZONA");
				centro = rs.getInt("ZONCLI_CENTRO");
				nombre = rs.getString("ZONCLI_NOMBRE").trim();
				representante = rs.getInt("ZONCLI_REPRE");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ZonaCliente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			zona = rs.getInt("ZONCLI_ZONA");
			centro = rs.getInt("ZONCLI_CENTRO");
			nombre = rs.getString("ZONCLI_NOMBRE").trim();
			representante = rs.getInt("ZONCLI_REPRE");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ZonaCliente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ZONCLI " +
						   "(EMPRESA, " +
						   "ZONCLI_ZONA, " +
						   "ZONCLI_CENTRO, " +
						   "ZONCLI_NOMBRE, " +
						   "ZONCLI_REPRE) " +						   
				           "VALUES (?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ZONCLI_ZONA = ?, " +
						   "ZONCLI_CENTRO = ?, " +
						   "ZONCLI_NOMBRE = ?, " +
						   "ZONCLI_REPRE = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, zona);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setInt(i++, representante);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, zona);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setInt(i++, representante);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ZonaCliente!!!");
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

	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
	}
}
