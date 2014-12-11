package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class TipoCliente {
	private String empresa;
	private int tipo;
	private int centro;
	private String descripcion;
	private int representante;

	public TipoCliente(){
		empresa = DatosComunes.eEmpresa;
		tipo = 0;
		centro =  0;
		descripcion = "";
		representante = 0;
	}

	public TipoCliente(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				tipo = rs.getInt("TIPCLI_TIPO");
				centro =  rs.getInt("TIPCLI_CENTRO");
				descripcion = rs.getString("TIPCLI_DESCRIP").trim();
				representante = rs.getInt("TIPCLI_REPRE");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de TipoCliente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			tipo = rs.getInt("TIPCLI_TIPO");
			centro =  rs.getInt("TIPCLI_CENTRO");
			descripcion = rs.getString("TIPCLI_DESCRIP").trim();
			representante = rs.getInt("TIPCLI_REPRE");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de TipoCliente!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO TIPCLI " +
						   "(EMPRESA, " +
						   "TIPCLI_TIPO, " +
						   "TIPCLI_CENTRO, " +
						   "TIPCLI_DESCRIP, " +
						   "TIPCLI_REPRE) " +						   
				           "VALUES (?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "TIPCLI_TIPO = ?, " +
						   "TIPCLI_CENTRO = ?, " +
						   "TIPCLI_DESCRIP = ?, " +
						   "TIPCLI_REPRE = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, tipo);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(descripcion, 30));
			ps.setInt(i++, representante);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, tipo);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(descripcion, 30));
			ps.setInt(i++, representante);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de TipoCliente!!!");
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
	}
}
