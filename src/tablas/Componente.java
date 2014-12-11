package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class Componente {
	private String empresa;
	private String producto;
	private String componente;
	private double unidades;

	public Componente(){
		empresa = DatosComunes.eEmpresa;
		producto = "";
		componente = "";
		unidades = 0.0;
	}

	public Componente(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				producto = rs.getString("COMPON_PRODUCTO").trim();
				componente = rs.getString("COMPON_COMPONENTE").trim();
				unidades = rs.getDouble("COMPON_UNIDADES");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Componente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			producto = rs.getString("COMPON_PRODUCTO").trim();
			componente = rs.getString("COMPON_COMPONENTE").trim();
			unidades = rs.getDouble("COMPON_UNIDADES");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Componente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO COMPON " +
						   "(EMPRESA, " +
						   "COMPON_PRODUCTO, " +
						   "COMPON_COMPONENTE, " +
						   "COMPON_UNIDADES) " +						   
				           "VALUES (?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "COMPON_PRODUCTO = ?, " +
						   "COMPON_COMPONENTE = ?, " +
						   "COMPON_UNIDADES = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(producto, 13));
			ps.setString(i++, Cadena.left(componente, 13));
			ps.setDouble(i++, unidades);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(producto, 13));
			ps.setString(i++, Cadena.left(componente, 13));
			ps.setDouble(i++, unidades);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Componente!!!");
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

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public double getUnidades() {
		return unidades;
	}

	public void setUnidades(double unidades) {
		this.unidades = unidades;
	}
}
