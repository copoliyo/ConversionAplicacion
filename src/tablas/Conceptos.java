package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class Conceptos {
	private String empresa;
	private String fichero;
	private String keyFichero;
	private String concepto;
	private double num1;
	private double num2;
	private double num3;

	public Conceptos(){
		empresa = DatosComunes.eEmpresa;
		fichero = "";
		keyFichero = "";
		concepto = "";
		num1 = 0;
		num2 = 0;
		num3 = 0;
	}

	public Conceptos(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				fichero = rs.getString("CEPTOS_FICHERO").trim();
				keyFichero = rs.getString("CEPTOS_KEY_FICHERO").trim();
				concepto = rs.getString("CEPTOS_CONCEPTO").trim();
				num1 = rs.getInt("CEPTOS_NUM1");
				num2 = rs.getInt("CEPTOS_NUM2");
				num3 = rs.getInt("CEPTOS_NUM3");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Conceptos!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			fichero = rs.getString("CEPTOS_FICHERO").trim();
			keyFichero = rs.getString("CEPTOS_KEY_FICHERO").trim();
			concepto = rs.getString("CEPTOS_CONCEPTO").trim();
			num1 = rs.getInt("CEPTOS_NUM1");
			num2 = rs.getInt("CEPTOS_NUM2");
			num3 = rs.getInt("CEPTOS_NUM3");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Conceptos!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CEPTOS " +
						   "(EMPRESA, " +
						   "CEPTOS_FICHERO, " +
						   "CEPTOS_KEY_FICHERO, " +
						   "CEPTOS_CONCEPTO, " +
						   "CEPTOS_NUM1, " +
						   "CEPTOS_NUM2, " +
						   "CEPTOS_NUM3) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CEPTOS_FICHERO = ?, " +
						   "CEPTOS_KEY_FICHERO = ?, " +
						   "CEPTOS_CONCEPTO = ?, " +
						   "CEPTOS_NUM1 = ?, " +
						   "CEPTOS_NUM2 = ?, " +
						   "CEPTOS_NUM3 = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(fichero, 8));
			ps.setString(i++, Cadena.left(keyFichero, 30));
			ps.setString(i++, Cadena.left(concepto, 255));
			ps.setDouble(i++, num1);
			ps.setDouble(i++, num2);
			ps.setDouble(i++, num3);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(fichero, 8));
			ps.setString(i++, Cadena.left(keyFichero, 30));
			ps.setString(i++, Cadena.left(concepto, 255));
			ps.setDouble(i++, num1);
			ps.setDouble(i++, num2);
			ps.setDouble(i++, num3);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Conceptos!!!");
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

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public String getKeyFichero() {
		return keyFichero;
	}

	public void setKeyFichero(String keyFichero) {
		this.keyFichero = keyFichero;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getNum1() {
		return num1;
	}

	public void setNum1(double num1) {
		this.num1 = num1;
	}

	public double getNum2() {
		return num2;
	}

	public void setNum2(double num2) {
		this.num2 = num2;
	}

	public double getNum3() {
		return num3;
	}

	public void setNum3(double num3) {
		this.num3 = num3;
	}

}
