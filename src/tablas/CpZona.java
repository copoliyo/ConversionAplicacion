package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class CpZona {
	private String empresa;
	private int pais;
	private int codigoPostal;
	private int zona;
	private int bloqueo;
	private int mañana;
	private int tarde;
	private int grupo;

	public CpZona(){
		empresa = DatosComunes.eEmpresa;
		pais = 0;
		codigoPostal = 0;
		zona = 0;
		bloqueo = 0;
		mañana = 0;
		tarde = 0;
		grupo = 0;
	}

	public CpZona(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				pais = rs.getInt("CPZONA_PAIS");
				codigoPostal = rs.getInt("CPZONA_CP");
				zona = rs.getInt("CPZONA_ZONA");
				bloqueo = rs.getInt("CPZONA_BLOQUEO");
				mañana = rs.getInt("CPZONA_MANANA");
				tarde = rs.getInt("CPZONA_TARDE");
				grupo = rs.getInt("CPZONA_GRUPO");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpZona!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			pais = rs.getInt("CPZONA_PAIS");
			codigoPostal = rs.getInt("CPZONA_CP");
			zona = rs.getInt("CPZONA_ZONA");
			bloqueo = rs.getInt("CPZONA_BLOQUEO");
			mañana = rs.getInt("CPZONA_MANANA");
			tarde = rs.getInt("CPZONA_TARDE");
			grupo = rs.getInt("CPZONA_GRUPO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpZona!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CPZONA " +
						   "(EMPRESA, " +
						   "CPZONA_PAIS, " +
						   "CPZONA_CP, " +
						   "CPZONA_ZONA, " +
						   "CPZONA_BLOQUEO, " +
						   "CPZONA_MANANA, " +
						   "CPZONA_TARDE, " +
						   "CPZONA_GRUPO) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CPZONA_PAIS = ?, " +
						   "CPZONA_CP = ?, " +
						   "CPZONA_ZONA = ?, " +
						   "CPZONA_BLOQUEO = ?, " +
						   "CPZONA_MANANA = ?, " +
						   "CPZONA_TARDE = ?, " +
						   "CPZONA_GRUPO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, pais);
			ps.setInt(i++, codigoPostal);
			ps.setInt(i++, zona);
			ps.setInt(i++, bloqueo);
			ps.setInt(i++, mañana);
			ps.setInt(i++, tarde);
			ps.setInt(i++, grupo);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, pais);
			ps.setInt(i++, codigoPostal);
			ps.setInt(i++, zona);
			ps.setInt(i++, bloqueo);
			ps.setInt(i++, mañana);
			ps.setInt(i++, tarde);
			ps.setInt(i++, grupo);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CpZona!!!");
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

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public int getBloqueo() {
		return bloqueo;
	}

	public void setBloqueo(int bloqueo) {
		this.bloqueo = bloqueo;
	}

	public int getMañana() {
		return mañana;
	}

	public void setMañana(int mañana) {
		this.mañana = mañana;
	}

	public int getTarde() {
		return tarde;
	}

	public void setTarde(int tarde) {
		this.tarde = tarde;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
}
