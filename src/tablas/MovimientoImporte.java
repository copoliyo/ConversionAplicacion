package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class MovimientoImporte {
	private String empresa;
	private int fecha;
	private int apunte;
	private double importe;
	private double importeCentro;
	private int porcentajeDescuento2;
	private double importeVenta;
	private double beneficio;
	private double beneficioCentro;
	private double importeAbono;

	public MovimientoImporte(){
		empresa = DatosComunes.eEmpresa;
		fecha = 0;
		apunte = 0;
		importe = 0.0;
		importeCentro = 0.0;
		porcentajeDescuento2 = 0;
		importeVenta = 0.0;
		beneficio = 0.0;
		beneficioCentro = 0.0;
		importeAbono = 0.0;
	}

	public MovimientoImporte(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				fecha = rs.getInt("");
				apunte = rs.getInt("");
				importe = rs.getDouble("");
				importeCentro = rs.getDouble("");
				porcentajeDescuento2 = rs.getInt("");
				importeVenta = rs.getDouble("");
				beneficio = rs.getDouble("");
				beneficioCentro = rs.getDouble("");
				importeAbono = rs.getDouble("");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de MovimientoImporte!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			fecha = rs.getInt("MOVIMP_FECHA");
			apunte = rs.getInt("MOVIMP_APUNTE");
			importe = rs.getDouble("MOVIMP_IMPORTE");
			importeCentro = rs.getDouble("MOVIMP_IMPORTE_CENTRO");
			porcentajeDescuento2 = rs.getInt("MOVIMP_POR_DTO2");
			importeVenta = rs.getDouble("MOVIMP_IMPORTE_VENTA");
			beneficio = rs.getDouble("MOVIMP_BENEFICIO");
			beneficioCentro = rs.getDouble("MOVIMP_BENEFICIO_CENTRO");
			importeAbono = rs.getDouble("MOVIMP_IMPORTE_ABONO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de MovimientoImporte!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO MOVIMP " +
						   "(EMPRESA, " +
						   "MOVIMP_FECHA, " +
						   "MOVIMP_APUNTE, " +
						   "MOVIMP_IMPORTE, " +
						   "MOVIMP_IMPORTE_CENTRO, " +
						   "MOVIMP_POR_DTO2, " +
						   "MOVIMP_IMPORTE_VENTA, " +
						   "MOVIMP_BENEFICIO, " +
						   "MOVIMP_BENEFICIO_CENTRO, " +
						   "MOVIMP_IMPORTE_ABONO) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "MOVIMP_FECHA = ?, " +
						   "MOVIMP_APUNTE = ?, " +
						   "MOVIMP_IMPORTE = ?, " +
						   "MOVIMP_IMPORTE_CENTRO = ?, " +
						   "MOVIMP_POR_DTO2 = ?, " +
						   "MOVIMP_IMPORTE_VENTA = ?, " +
						   "MOVIMP_BENEFICIO = ?, " +
						   "MOVIMP_BENEFICIO_CENTRO = ?, " +
						   "MOVIMP_IMPORTE_ABONO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, apunte);
			ps.setDouble(i++, importe);
			ps.setDouble(i++, importeCentro);
			ps.setInt(i++, porcentajeDescuento2);
			ps.setDouble(i++, importeVenta);
			ps.setDouble(i++, beneficio);
			ps.setDouble(i++, beneficioCentro);
			ps.setDouble(i++, importeAbono);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, apunte);
			ps.setDouble(i++, importe);
			ps.setDouble(i++, importeCentro);
			ps.setInt(i++, porcentajeDescuento2);
			ps.setDouble(i++, importeVenta);
			ps.setDouble(i++, beneficio);
			ps.setDouble(i++, beneficioCentro);
			ps.setDouble(i++, importeAbono);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de MovimientoImporte!!!");
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

	public int getApunte() {
		return apunte;
	}

	public void setApunte(int apunte) {
		this.apunte = apunte;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getImporteCentro() {
		return importeCentro;
	}

	public void setImporteCentro(double importeCentro) {
		this.importeCentro = importeCentro;
	}

	public int getPorcentajeDescuento2() {
		return porcentajeDescuento2;
	}

	public void setPorcentajeDescuento2(int porcentajeDescuento2) {
		this.porcentajeDescuento2 = porcentajeDescuento2;
	}

	public double getImporteVenta() {
		return importeVenta;
	}

	public void setImporteVenta(double importeVenta) {
		this.importeVenta = importeVenta;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	public double getBeneficioCentro() {
		return beneficioCentro;
	}

	public void setBeneficioCentro(double beneficioCentro) {
		this.beneficioCentro = beneficioCentro;
	}

	public double getImporteAbono() {
		return importeAbono;
	}

	public void setImporteAbono(double importeAbono) {
		this.importeAbono = importeAbono;
	}
}	
