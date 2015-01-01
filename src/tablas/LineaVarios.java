package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class LineaVarios {
	private String empresa;
	private int fecha;
	private int linea;
	private String articulo;
	private String descripcion;
	private double cantidad;
	private double precio;

	public LineaVarios(){
		empresa = DatosComunes.eEmpresa;
		fecha = 0;
		linea = 0;
		articulo = "";
		descripcion = "";
		cantidad = 0.0;
		precio = 0.0;		
	}

	public LineaVarios(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				fecha = rs.getInt("LINVAR_FECHA");
				linea = rs.getInt("LINVAR_LINEA");
				articulo = rs.getString("LINVAR_ARTCLO").trim();
				descripcion = rs.getString("LINVAR_DESCRI").trim();
				cantidad = rs.getDouble("LINVAR_CANTID");
				precio = rs.getDouble("LINVAR_PRECIO");		
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de LineaVarios!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			fecha = rs.getInt("LINVAR_FECHA");
			linea = rs.getInt("LINVAR_LINEA");
			articulo = rs.getString("LINVAR_ARTCLO").trim();
			descripcion = rs.getString("LINVAR_DESCRI").trim();
			cantidad = rs.getDouble("LINVAR_CANTID");
			precio = rs.getDouble("LINVAR_PRECIO");		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de LineaVarios!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO LINVAR " +
						   "(EMPRESA, " +
						   "LINVAR_FECHA, " +
						   "LINVAR_LINEA, " +
						   "LINVAR_ARTCLO, " +
						   "LINVAR_DESCRI, " +
						   "LINVAR_CANTID, " +
						   "LINVAR_PRECIO) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "LINVAR_FECHA = ?, " +
						   "LINVAR_LINEA = ?, " +
						   "LINVAR_ARTCLO = ?, " +
						   "LINVAR_DESCRI = ?, " +
						   "LINVAR_CANTID = ?, " +
						   "LINVAR_PRECIO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setString(i++, Cadena.left(descripcion, 30));
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precio);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setString(i++, Cadena.left(descripcion, 30));
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precio);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de LineaVarios!!!");
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

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
