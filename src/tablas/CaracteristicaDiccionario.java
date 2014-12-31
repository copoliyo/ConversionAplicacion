package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CaracteristicaDiccionario {
	private String empresa;
	private String idioma;
	private int gama;
	private int familia;
	private int orden;
	private String palabra;
	private String descripcion;

	public CaracteristicaDiccionario(){
		empresa = DatosComunes.eEmpresa;
		idioma = "";
		gama = 0;
		familia = 0;
		orden = 0;
		palabra = "";
		descripcion = "";		
	}

	public CaracteristicaDiccionario(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				idioma = rs.getString("CARTCD_IDIOMA").trim();
				gama = rs.getInt("CARTCD_GAMA");
				familia = rs.getInt("CARTCD_FAMILIA");
				orden = rs.getInt("CARTCD_ORDEN");
				palabra = rs.getString("CARTCD_FILL_PALBR").trim();
				descripcion = rs.getString("CARTCD_DESCRIP").trim();	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CaracteristicaDiccionario!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			idioma = rs.getString("CARTCD_IDIOMA").trim();
			gama = rs.getInt("CARTCD_GAMA");
			familia = rs.getInt("CARTCD_FAMILIA");
			orden = rs.getInt("CARTCD_ORDEN");
			palabra = rs.getString("CARTCD_FILL_PALBR").trim();
			descripcion = rs.getString("CARTCD_DESCRIP").trim();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CaracteristicaDiccionario!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CARTCD " +
						   "(EMPRESA, " +
						   "CARTCD_IDIOMA, " +
						   "CARTCD_GAMA, " +
						   "CARTCD_FAMILIA, " +
						   "CARTCD_ORDEN, " +
						   "CARTCD_FILL_PALBR, " +
						   "CARTCD_DESCRIP) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CARTCD_IDIOMA = ?, " +
						   "CARTCD_GAMA = ?, " +
						   "CARTCD_FAMILIA = ?, " +
						   "CARTCD_ORDEN = ?, " +
						   "CARTCD_FILL_PALBR = ?, " +
						   "CARTCD_DESCRIP = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(idioma, 2));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(palabra, 20));
			ps.setString(i++, Cadena.left(descripcion, 25));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(idioma, 2));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(palabra, 20));
			ps.setString(i++, Cadena.left(descripcion, 25));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CaracteristicaDiccionario!!!");
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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getGama() {
		return gama;
	}

	public void setGama(int gama) {
		this.gama = gama;
	}

	public int getFamilia() {
		return familia;
	}

	public void setFamilia(int familia) {
		this.familia = familia;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
