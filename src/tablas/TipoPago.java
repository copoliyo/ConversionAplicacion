package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class TipoPago {
	private String empresa;
	private int codigo;
	private String descripcion;
	private int activo;
	private String cuentaProveedorAcreedor;
	private String cuentaCaja;
	private double importeDia;

	public TipoPago(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		descripcion = "";
		activo = 0;
		cuentaProveedorAcreedor = "";
		cuentaCaja = "";
		importeDia = 0.0;
	}

	public TipoPago(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("TIPAGO_CODIGO");
				descripcion = rs.getString("TIPAGO_DESCRIP").trim();
				activo = rs.getInt("TIPAGO_ACTIVO");
				cuentaProveedorAcreedor = rs.getString("TIPAGO_CTA_PROAC").trim();
				cuentaCaja = rs.getString("TIPAGO_CTA_CAJA").trim();
				importeDia = rs.getDouble("TIPAGO_IMPORTE_DIA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de TipoPago!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("TIPAGO_CODIGO");
			descripcion = rs.getString("TIPAGO_DESCRIP").trim();
			activo = rs.getInt("TIPAGO_ACTIVO");
			cuentaProveedorAcreedor = rs.getString("TIPAGO_CTA_PROAC").trim();
			cuentaCaja = rs.getString("TIPAGO_CTA_CAJA").trim();
			importeDia = rs.getDouble("TIPAGO_IMPORTE_DIA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de TipoPago!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO TIPAGO " +
						   "(EMPRESA, " +
						   "TIPAGO_CODIGO, " +
						   "TIPAGO_DESCRIP, " +
						   "TIPAGO_ACTIVO, " +
						   "TIPAGO_CTA_PROAC, " +
						   "TIPAGO_CTA_CAJA, " +
						   "TIPAGO_IMPORTE_DIA) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "TIPAGO_CODIGO = ?, " +
						   "TIPAGO_DESCRIP = ?, " +
						   "TIPAGO_ACTIVO = ?, " +
						   "TIPAGO_CTA_PROAC = ?, " +
						   "TIPAGO_CTA_CAJA = ?, " +
						   "TIPAGO_IMPORTE_DIA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(descripcion, 20));
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(cuentaProveedorAcreedor, 9));
			ps.setString(i++, Cadena.left(cuentaCaja, 9));
			ps.setDouble(i++, importeDia);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(descripcion, 20));
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(cuentaProveedorAcreedor, 9));
			ps.setString(i++, Cadena.left(cuentaCaja, 9));
			ps.setDouble(i++, importeDia);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de TipoPago!!!");
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getCuentaProveedorAcreedor() {
		return cuentaProveedorAcreedor;
	}

	public void setCuentaProveedorAcreedor(String cuentaProveedorAcreedor) {
		this.cuentaProveedorAcreedor = cuentaProveedorAcreedor;
	}

	public String getCuentaCaja() {
		return cuentaCaja;
	}

	public void setCuentaCaja(String cuentaCaja) {
		this.cuentaCaja = cuentaCaja;
	}

	public double getImporteDia() {
		return importeDia;
	}

	public void setImporteDia(double importeDia) {
		this.importeDia = importeDia;
	}
}
