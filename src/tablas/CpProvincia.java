package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CpProvincia {
	private String empresa;
	private int codigo;
	private String provincia;
	private int kilometrosCuadrados;
	private int habitantes;

	public CpProvincia(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		provincia = "";
		kilometrosCuadrados = 0;
		habitantes = 0;
	}

	public CpProvincia(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("CPPROV_CODIGO");
				provincia = rs.getString("CPPROV_PROVINCIA").trim();
				kilometrosCuadrados = rs.getInt("CPPROV_KM2");
				habitantes = rs.getInt("CPPROV_HABITANTES");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpProvincia!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("CPPROV_CODIGO");
			provincia = rs.getString("CPPROV_PROVINCIA").trim();
			kilometrosCuadrados = rs.getInt("CPPROV_KM2");
			habitantes = rs.getInt("CPPROV_HABITANTES");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpProvincia!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CPPROV " +
						   "(EMPRESA, " +
						   "CPPROV_CODIGO, " +
						   "CPPROV_PROVINCIA, " +
						   "CPPROV_KM2, " +
						   "CPPROV_HABITANTES) " +						   
				           "VALUES (?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CPPROV_CODIGO = ?, " +
						   "CPPROV_PROVINCIA = ?, " +
						   "CPPROV_KM2 = ?, " +
						   "CPPROV_HABITANTES = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(provincia, 20));
			ps.setInt(i++, kilometrosCuadrados);
			ps.setInt(i++, habitantes);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(provincia, 20));
			ps.setInt(i++, kilometrosCuadrados);
			ps.setInt(i++, habitantes);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CpProvincia!!!");
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getKilometrosCuadrados() {
		return kilometrosCuadrados;
	}

	public void setKilometrosCuadrados(int kilometrosCuadrados) {
		this.kilometrosCuadrados = kilometrosCuadrados;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}
}
