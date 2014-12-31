package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CaracteristicaTecnica {
	private String empresa;
	private String ean;
	private int tipoTabla;
	private int orden;
	private String valor;

	public CaracteristicaTecnica(){
		empresa = DatosComunes.eEmpresa;
		ean = "";
		tipoTabla = 0;
		orden = 0;
		valor = "";
	}

	public CaracteristicaTecnica(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				ean = rs.getString("CARTCA_EAN").trim();
				tipoTabla = rs.getInt("CARTCA_TIPO_TABLA");
				orden = rs.getInt("CARTCA_ORDEN");
				valor = rs.getString("CARTCA_VALOR").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CAracteristicaTecnica!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			ean = rs.getString("CARTCA_EAN").trim();
			tipoTabla = rs.getInt("CARTCA_TIPO_TABLA");
			orden = rs.getInt("CARTCA_ORDEN");
			valor = rs.getString("CARTCA_VALOR").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CAracteristicaTecnica!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CARTCA " +
						   "(EMPRESA, " +
						   "CARTCA_EAN, " +
						   "CARTCA_TIPO_TABLA, " +
						   "CARTCA_ORDEN, " +
						   "CARTCA_VALOR) " +						   
				           "VALUES (?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CARTCA_EAN = ?, " +
						   "CARTCA_TIPO_TABLA = ?, " +
						   "CARTCA_ORDEN = ?, " +
						   "CARTCA_VALOR = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(ean, 13));
			ps.setInt(i++, tipoTabla);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(valor, 13));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(ean, 13));
			ps.setInt(i++, tipoTabla);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(valor, 13));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CaracteristicaTecnica!!!");
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

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public int getTipoTabla() {
		return tipoTabla;
	}

	public void setTipoTabla(int tipoTabla) {
		this.tipoTabla = tipoTabla;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
