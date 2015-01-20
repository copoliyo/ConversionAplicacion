package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class ArticuloEan {
	private String empresa;
	private String articulo;
	private String ean;

	public ArticuloEan(){
		empresa = DatosComunes.eEmpresa;
		articulo = "";
		ean = "";		
	}

	public ArticuloEan(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				articulo = rs.getString("ARTEAN_ARTICULO").trim();
				ean = rs.getString("ARTEAN_EAN").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ArticuloEan!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			articulo = rs.getString("ARTEAN_ARTICULO").trim();
			ean = rs.getString("ARTEAN_EAN").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ArticuloEan!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ARTEAN " +
						   "(EMPRESA, " +
						   "ARTEAN_ARTICULO, " +
						   "ARTEAN_EAN) " +						   
				           "VALUES (?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ARTEAN_ARTICULO = ?, " +
						   "ARTEAN_EAN = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setString(i++, Cadena.left(ean, 13));
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setString(i++, Cadena.left(ean, 13));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ArticuloEan!!!");
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

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

}
