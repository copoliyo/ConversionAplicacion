package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class ApunteAsientoAutomatico {
	private String empresa;
	private int codigo;
	private int apunte;
	private String cuenta;
	private int clave;

	public ApunteAsientoAutomatico(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		apunte = 0;
		cuenta = "";
		clave = 0;
	}

	public ApunteAsientoAutomatico(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("ASTAUT_CODIGO");
				apunte = rs.getInt("ASTAUT_APTE");
				cuenta = rs.getString("ASTAUT_CUENTA").trim();
				clave = rs.getInt("ASTAUT_CLAVE");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ApunteAsientoAutomatico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("ASTAUT_CODIGO");
			apunte = rs.getInt("ASTAUT_APTE");
			cuenta = rs.getString("ASTAUT_CUENTA").trim();
			clave = rs.getInt("ASTAUT_CLAVE");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ApunteAsientoAutomatico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ASTAUT " +
						   "(EMPRESA, " +
						   "ASTAUT_CODIGO, " +
						   "ASTAUT_APTE, " +
						   "ASTAUT_CUENTA, " +
						   "ASTAUT_CLAVE) " +						   
				           "VALUES (?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ASTAUT_CODIGO = ?, " +
						   "ASTAUT_APTE = ?, " +
						   "ASTAUT_CUENTA = ?, " +
						   "ASTAUT_CLAVE = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setInt(i++, apunte);
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setInt(i++, clave);

			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setInt(i++, apunte);
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setInt(i++, clave);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ApunteAsientoAutomatico!!!");
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

	public int getApunte() {
		return apunte;
	}

	public void setApunte(int apunte) {
		this.apunte = apunte;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}
}
