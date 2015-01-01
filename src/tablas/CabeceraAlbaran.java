package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CabeceraAlbaran {
	private String empresa;
	private int centro;
	private int año;
	private int albaran;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String nif;	

	public CabeceraAlbaran(){
		empresa = DatosComunes.eEmpresa;
		centro = DatosComunes.centroGest;
		año = 0;
		albaran = 0;
		nombre = "";
		direccion = "";
		poblacion = "";
		nif = "";	
	}

	public CabeceraAlbaran(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				centro = rs.getInt("CALVAR_CENTRO");
				año = rs.getInt("CALVAR_ANY");
				albaran = rs.getInt("CALVAR_ALBARAN");
				nombre = rs.getString("CALVAR_NOMBRE").trim();
				direccion = rs.getString("CALVAR_DIR").trim();
				poblacion = rs.getString("CALVAR_POB").trim();
				nif = rs.getString("CALVAR_NIF").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CabeceraAlbaran!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			centro = rs.getInt("CALVAR_CENTRO");
			año = rs.getInt("CALVAR_ANY");
			albaran = rs.getInt("CALVAR_ALBARAN");
			nombre = rs.getString("CALVAR_NOMBRE").trim();
			direccion = rs.getString("CALVAR_DIR").trim();
			poblacion = rs.getString("CALVAR_POB").trim();
			nif = rs.getString("CALVAR_NIF").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CabeceraAlbaran!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CALVAR " +
						   "(EMPRESA, " +
						   "CALVAR_CENTRO, " +
						   "CALVAR_ANY, " +
						   "CALVAR_ALBARAN, " +
						   "CALVAR_NOMBRE, " +
						   "CALVAR_DIR, " +
						   "CALVAR_POB, " +
						   "CALVAR_NIF) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CALVAR_CENTRO = ?, " +
						   "CALVAR_ANY = ?, " +
						   "CALVAR_ALBARAN = ?," +
						   "CALVAR_NOMBRE = ?, " +
						   "CALVAR_DIR = ?, " +
						   "CALVAR_POB = ?, " +
						   "CALVAR_NIF = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, centro);
			ps.setInt(i++, año);
			ps.setInt(i++, albaran);
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			ps.setString(i++, Cadena.left(nif, 16));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, centro);
			ps.setInt(i++, año);
			ps.setInt(i++, albaran);
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			ps.setString(i++, Cadena.left(nif, 16));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CabeceraAlbaran!!!");
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

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getAlbaran() {
		return albaran;
	}

	public void setAlbaran(int albaran) {
		this.albaran = albaran;
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

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
}
