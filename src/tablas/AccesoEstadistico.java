package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class AccesoEstadistico {
	private String empresa;
	private String programa;
	private String usuario;
	private int vecesLlamada;
	private int intentosFallo;
	private int segundosConexion;
	private int ultimaFecha;
	
	public AccesoEstadistico(){
		empresa = DatosComunes.eEmpresa;
		programa = "";
		usuario = "";
		vecesLlamada = intentosFallo = segundosConexion = ultimaFecha = 0;		
	}
	
	public AccesoEstadistico(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				programa = rs.getString("ACCEST_PROGRAMA").trim();
				usuario = rs.getString("ACCEST_USUARIO").trim();
				vecesLlamada = rs.getInt("ACCEST_VECES_LLAMADA");
				intentosFallo = rs.getInt("ACCEST_INTENTOS_FALLO");
				segundosConexion = rs.getInt("ACCEST_SEGUNDOS_CONEX");
				ultimaFecha = rs.getInt("ACCEST_ULT_FECHA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de AccesoEstadistico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void read(ResultSet rs){
		try{						
			empresa = rs.getString("EMPRESA").trim();
			programa = rs.getString("ACCEST_PROGRAMA").trim();
			usuario = rs.getString("ACCEST_USUARIO").trim();
			vecesLlamada = rs.getInt("ACCEST_VECES_LLAMADA");
			intentosFallo = rs.getInt("ACCEST_INTENTOS_FALLO");
			segundosConexion = rs.getInt("ACCEST_SEGUNDOS_CONEX");
			ultimaFecha = rs.getInt("ACCEST_ULT_FECHA");			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de AccesoEstadistico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ACCEST (" +
				           "EMPRESA, " +
				           "ACCEST_PROGRAMA, " +
				           "ACCEST_USUARIO, " +
				           "ACCEST_VECES_LLAMADA, " +
				           "ACCEST_INTENTOS_FALLO, " +
				           "ACCEST_SEGUNDOS_CONEX, " +
				           "ACCEST_ULT_FECHA) " +
				           "VALUES (?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ACCEST_PROGRAMA = ?, " +
				           "ACCEST_USUARIO = ?, " +
				           "ACCEST_VECES_LLAMADA = ?, " +
				           "ACCEST_INTENTOS_FALLO = ?, " +
				           "ACCEST_SEGUNDOS_CONEX = ?, " +
				           "ACCEST_ULT_FECHA = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setString(2, Cadena.left(programa, 8));
			ps.setString(3, Cadena.left(usuario, 8));
			ps.setInt(4, vecesLlamada);
			ps.setInt(5, intentosFallo);
			ps.setInt(6, segundosConexion);
			ps.setInt(7, ultimaFecha);
			// Update
			ps.setString(8, Cadena.left(empresa, 2));
			ps.setString(9, Cadena.left(programa, 8));
			ps.setString(10, Cadena.left(usuario, 8));
			ps.setInt(11, vecesLlamada);
			ps.setInt(12, intentosFallo);
			ps.setInt(13, segundosConexion);
			ps.setInt(14, ultimaFecha);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de AccesoEstadistico!!!");
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

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getVecesLlamada() {
		return vecesLlamada;
	}

	public void setVecesLlamada(int vecesLlamada) {
		this.vecesLlamada = vecesLlamada;
	}

	public int getIntentosFallo() {
		return intentosFallo;
	}

	public void setIntentosFallo(int intentosFallo) {
		this.intentosFallo = intentosFallo;
	}

	public int getSegundosConexion() {
		return segundosConexion;
	}

	public void setSegundosConexion(int segundosConexion) {
		this.segundosConexion = segundosConexion;
	}

	public int getUltimaFecha() {
		return ultimaFecha;
	}

	public void setUltimaFecha(int ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}

}
