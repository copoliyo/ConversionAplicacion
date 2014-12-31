package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Apariencia;
import util.BaseDatos;
import util.Cadena;


public class IndiceBancos {
	private String empresa;
	private int banco;
	private String sucursal;
	private String descripcion;
	private int provincia;

	public IndiceBancos(){
		empresa = DatosComunes.eEmpresa;
		banco = 0;
		sucursal = "";
		descripcion = "";
		provincia = 0;
	}

	public IndiceBancos(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				banco = rs.getInt("BCOIND_BANCO");
				sucursal = rs.getString("BCOIND_SUCURSAL").trim();
				descripcion = rs.getString("BCOIND_DESCRIPCION").trim();
				provincia = rs.getInt("BCOIND_PROVINCIA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de IndiceBancos!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}		
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			banco = rs.getInt("BCOIND_BANCO");
			sucursal = rs.getString("BCOIND_SUCURSAL").trim();
			descripcion = rs.getString("BCOIND_DESCRIPCION").trim();
			provincia = rs.getInt("BCOIND_PROVINCIA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de IndiceBancos!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}		
	}

	// Con este método leemos una banco o sucursal pasando tan sólo una consulta SQL
	public void read(String strSql){

		ResultSet rsSql = null;
		MysqlConnect m = null;

		m = MysqlConnect.getDbCon();

		if(BaseDatos.countRows(strSql) != 0){
			try {
				rsSql = m.query(strSql);				
				// Como ya tenemos el ResultSet se lo pasamos al mérodo 'read(ResultSet rs)'.
				if(rsSql.next()){
					this.read(rsSql);
					// Cerramos para evitar gastar memoria
					rsSql.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(DatosComunes.enDebug)
					e.printStackTrace();
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de Indice de Bancos");				
			}
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO BCOIND " +
						   "(EMPRESA, " +
						   "BCOIND_BANCO, " +
						   "BCOIND_SUCURSAL, " +
						   "BCOIND_DESCRIPCION, " +
						   "BCOIND_PROVINCIA) " +						   
				           "VALUES (?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "BCOIND_BANCO = ?, " +
						   "BCOIND_SUCURSAL = ?, " +
						   "BCOIND_DESCRIPCION = ?, " +
						   "BCOIND_PROVINCIA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, banco);
			ps.setString(i++, Cadena.left(sucursal, 4));
			ps.setString(i++, Cadena.left(descripcion, 36));
			ps.setInt(i++, provincia);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, banco);
			ps.setString(i++, Cadena.left(sucursal, 4));
			ps.setString(i++, Cadena.left(descripcion, 36));
			ps.setInt(i++, provincia);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de IndiceBancos!!!");
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

	public int getBanco() {
		return banco;
	}

	public void setBanco(int banco) {
		this.banco = banco;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getProvincia() {
		return provincia;
	}

	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}
}
