package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class SegundaDireccion {
	private String empresa;
	private String cuenta;
	private int codigoSegundaDireccion;
	private String nombre;
	private String direccion;
	private String poblacion;
	
	public SegundaDireccion(){
		empresa = DatosComunes.eEmpresa;
		cuenta = "";
		codigoSegundaDireccion = 0;
		nombre = "";
		direccion = "";
		poblacion = "";
	}
	
	public SegundaDireccion(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cuenta = rs.getString("2DADIR_CUENTA").trim();
				codigoSegundaDireccion = rs.getInt("2DADIR_COD_DIR2");
				nombre = rs.getString("2DADIR_NOM").trim();
				direccion = rs.getString("2DADIR_DIR").trim();
				poblacion = rs.getString("2DADIR_POB").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de SegundaDireccion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void read(ResultSet rs){
		try{						
			empresa = rs.getString("EMPRESA").trim();
			cuenta = rs.getString("2DADIR_CUENTA").trim();
			codigoSegundaDireccion = rs.getInt("2DADIR_COD_DIR2");
			nombre = rs.getString("2DADIR_NOM").trim();
			direccion = rs.getString("2DADIR_DIR").trim();
			poblacion = rs.getString("2DADIR_POB").trim();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de SegundaDireccion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO 2DADIR (" +
				           "EMPRESA" +
				           "2DADIR_CUENTA" +
				           "2DADIR_COD_DIR2" +
				           "2DADIR_NOM" +
				           "2DADIR_DIR" +
				           "2DADIR_POB)" + 
				           "VALUES (?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?" +
				           "2DADIR_CUENTA = ?" +
				           "2DADIR_COD_DIR2 = ?" +
				           "2DADIR_NOM = ?" +
				           "2DADIR_DIR = ?" +
				           "2DADIR_POB = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setInt(i++, codigoSegundaDireccion);
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setInt(i++, codigoSegundaDireccion);
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de SegundaDireccion!!!");
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

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public int getCodigoSegundaDireccion() {
		return codigoSegundaDireccion;
	}

	public void setCodigoSegundaDireccion(int codigoSegundaDireccion) {
		this.codigoSegundaDireccion = codigoSegundaDireccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
}
