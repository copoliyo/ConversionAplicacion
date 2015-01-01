package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class FacturaConcepto {
	private String empresa;
	private String codigo;
	private int cantidadImporteNada;
	private double precioImporte;
	private int iva;
	private int recargoEquivalencia;
	private String cuentaVentas;
	private int activa;
	private int actualizaVentasDia;

	public FacturaConcepto(){
		empresa = DatosComunes.eEmpresa;
		codigo = "";
		cantidadImporteNada = 0;
		precioImporte = 0.0;
		iva = 0;
		recargoEquivalencia = 0;
		cuentaVentas = "";
		activa = 0;
		actualizaVentasDia = 0;
	}

	public FacturaConcepto(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getString("FACCEP_CODIGO").trim();
				cantidadImporteNada = rs.getInt("FACCEP_CANIMPNADA");
				precioImporte = rs.getDouble("FACCEP_PRECIMP");
				iva = rs.getInt("FACCEP_IVA");
				recargoEquivalencia = rs.getInt("FACCEP_REQU");
				cuentaVentas = rs.getString("FACCEP_CTA_VTAS").trim();
				activa = rs.getInt("FACCEP_ACTIVO");
				actualizaVentasDia = rs.getInt("FACCEP_AC_VTASDIA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FacturaConcepto!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getString("FACCEP_CODIGO").trim();
			cantidadImporteNada = rs.getInt("FACCEP_CANIMPNADA");
			precioImporte = rs.getDouble("FACCEP_PRECIMP");
			iva = rs.getInt("FACCEP_IVA");
			recargoEquivalencia = rs.getInt("FACCEP_REQU");
			cuentaVentas = rs.getString("FACCEP_CTA_VTAS").trim();
			activa = rs.getInt("FACCEP_ACTIVO");
			actualizaVentasDia = rs.getInt("FACCEP_AC_VTASDIA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FacturaConcepto!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO FACCEP " +
						   "(EMPRESA, " +
						   "FACCEP_CODIGO, " +
						   "FACCEP_CANIMPNADA, " +
						   "FACCEP_PRECIMP, " +
						   "FACCEP_IVA, " +
						   "FACCEP_REQU, " +
						   "FACCEP_CTA_VTAS, " +
						   "FACCEP_ACTIVO, " +
						   "FACCEP_AC_VTASDIA) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "FACCEP_CODIGO = ?, " +
						   "FACCEP_CANIMPNADA = ?, " +
						   "FACCEP_PRECIMP = ?, " +
						   "FACCEP_IVA = ?, " +
						   "FACCEP_REQU = ?, " +
						   "FACCEP_CTA_VTAS = ?, " +
						   "FACCEP_ACTIVO = ?, " +
						   "FACCEP_AC_VTASDIA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(codigo, 12));
			ps.setInt(i++, cantidadImporteNada);
			ps.setDouble(i++, precioImporte);
			ps.setInt(i++, iva);
			ps.setInt(i++, recargoEquivalencia);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			ps.setInt(i++, activa);
			ps.setInt(i++, actualizaVentasDia);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(codigo, 12));
			ps.setInt(i++, cantidadImporteNada);
			ps.setDouble(i++, precioImporte);
			ps.setInt(i++, iva);
			ps.setInt(i++, recargoEquivalencia);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			ps.setInt(i++, activa);
			ps.setInt(i++, actualizaVentasDia);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de FacturaConcepto!!!");
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCantidadImporteNada() {
		return cantidadImporteNada;
	}

	public void setCantidadImporteNada(int cantidadImporteNada) {
		this.cantidadImporteNada = cantidadImporteNada;
	}

	public double getPrecioImporte() {
		return precioImporte;
	}

	public void setPrecioImporte(double precioImporte) {
		this.precioImporte = precioImporte;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public int getRecargoEquivalencia() {
		return recargoEquivalencia;
	}

	public void setRecargoEquivalencia(int recargoEquivalencia) {
		this.recargoEquivalencia = recargoEquivalencia;
	}

	public String getCuentaVentas() {
		return cuentaVentas;
	}

	public void setCuentaVentas(String cuentaVentas) {
		this.cuentaVentas = cuentaVentas;
	}

	public int getActiva() {
		return activa;
	}

	public void setActiva(int activa) {
		this.activa = activa;
	}

	public int getActualizaVentasDia() {
		return actualizaVentasDia;
	}

	public void setActualizaVentasDia(int actualizaVentasDia) {
		this.actualizaVentasDia = actualizaVentasDia;
	}
}
