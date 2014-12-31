package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class EstadisticaContable {
	private String empresa;
	private int fichero;
	private String clave;
	private int año;
	private int mes;
	private double acumulado[];

	public EstadisticaContable(){
		empresa = DatosComunes.eEmpresa;
		fichero = 0;
		clave = "";
		año = 0;
		mes = 0;
		acumulado = new double[6];
		for(int i = 0; i < 6; i++)
			acumulado[i] = 0.0;
	}

	public EstadisticaContable(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				fichero = rs.getInt("CACEST_FICHERO");
				clave = rs.getString("CACEST_CLAVE").trim();
				año = rs.getInt("CACEST_ANY");
				mes = rs.getInt("CACEST_MES");				
				acumulado[0] = rs.getDouble("CACEST_ACUMULADO_1");
				acumulado[1] = rs.getDouble("CACEST_ACUMULADO_2");
				acumulado[2] = rs.getDouble("CACEST_ACUMULADO_3");
				acumulado[3] = rs.getDouble("CACEST_ACUMULADO_4");
				acumulado[4] = rs.getDouble("CACEST_ACUMULADO_5");
				acumulado[5] = rs.getDouble("CACEST_ACUMULADO_6");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de EstadisticaContable!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			fichero = rs.getInt("CACEST_FICHERO");
			clave = rs.getString("CACEST_CLAVE").trim();
			año = rs.getInt("CACEST_ANY");
			mes = rs.getInt("CACEST_MES");				
			acumulado[0] = rs.getDouble("CACEST_ACUMULADO_1");
			acumulado[1] = rs.getDouble("CACEST_ACUMULADO_2");
			acumulado[2] = rs.getDouble("CACEST_ACUMULADO_3");
			acumulado[3] = rs.getDouble("CACEST_ACUMULADO_4");
			acumulado[4] = rs.getDouble("CACEST_ACUMULADO_5");
			acumulado[5] = rs.getDouble("CACEST_ACUMULADO_6");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de EstadisticaContable!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CACEST " +
						   "(EMPRESA, " +
						   "CACEST_FICHERO, " +
						   "CACEST_CLAVE, " +
						   "CACEST_ANY, " +
						   "CACEST_MES, " +
						   "CACEST_ACUMULADO_1, " +
						   "CACEST_ACUMULADO_2, " +
						   "CACEST_ACUMULADO_3, " +
						   "CACEST_ACUMULADO_4, " +
						   "CACEST_ACUMULADO_5, " +
						   "CACEST_ACUMULADO_6) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CACEST_FICHERO = ?, " +
						   "CACEST_CLAVE = ?, " +
						   "CACEST_ANY = ?, " +
						   "CACEST_MES = ?, " +
						   "CACEST_ACUMULADO_1 = ?, " +
						   "CACEST_ACUMULADO_2 = ?, " +
						   "CACEST_ACUMULADO_3 = ?, " +
						   "CACEST_ACUMULADO_4 = ?, " +
						   "CACEST_ACUMULADO_5 = ?, " +
						   "CACEST_ACUMULADO_6 = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fichero);
			ps.setString(i++, Cadena.left(clave, 13));
			ps.setInt(i++, año);
			ps.setInt(i++, mes);
			for(int j = 0; j < 6; j++)
				ps.setDouble(i++, acumulado[j]);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fichero);
			ps.setString(i++, Cadena.left(clave, 13));
			ps.setInt(i++, año);
			ps.setInt(i++, mes);
			for(int j = 0; j < 6; j++)
				ps.setDouble(i++, acumulado[j]);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de EstadisticaContable!!!");
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

	public int getFichero() {
		return fichero;
	}

	public void setFichero(int fichero) {
		this.fichero = fichero;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public double[] getAcumulado() {
		return acumulado;
	}
	
	public double getAcumulado(int i) {
		return acumulado[i];
	}

	public void setAcumulado(double[] acumulado) {
		this.acumulado = acumulado;
	}
	
	public void setAcumulado(double acumulado, int i) {
		this.acumulado[i] = acumulado;
	}
}
