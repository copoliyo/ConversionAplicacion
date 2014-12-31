package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;



public class FacturaRecibida {
	private String empresa;
	private String proveedorAcreedor;
	private int fecha;
	private int orden;
	private int centro;
	private long fechaAsientoApunte;
	private int factura;
	private double base[];
	private double iva;
	private double recargoEquivalencia;
	private double total;

	public FacturaRecibida(){
		empresa = DatosComunes.eEmpresa;
		proveedorAcreedor = "";
		fecha = 0;
		orden = 0;
		centro = 0;
		fechaAsientoApunte = (long)0;
		factura = 0;
		base = new double[4];
		for(int i = 0; i < 4; i++)
			base[i] = 0.0;
		iva = 0.0;
		recargoEquivalencia = 0.0;
		total = 0.0;
	}

	public FacturaRecibida(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				proveedorAcreedor = rs.getString("FACREC_PROVACRE").trim();
				fecha = rs.getInt("FACREC_FECHA");
				orden = rs.getInt("FACREC_ORDEN");
				centro = rs.getInt("FACREC_CENTRO");
				fechaAsientoApunte = rs.getLong("FACREC_FECH_ASTO_APT");
				factura = rs.getInt("FACREC_FACTURA");
				base = new double[4];
				base[0] = rs.getDouble("FACREC_BASES_1");
				base[1] = rs.getDouble("FACREC_BASES_2");
				base[2] = rs.getDouble("FACREC_BASES_3");
				base[3] = rs.getDouble("FACREC_BASES_4");
				iva = rs.getDouble("FACREC_IVA");
				recargoEquivalencia = rs.getDouble("FACREC_RECEQ");
				total = rs.getDouble("FACREC_TOTAL");	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FacturaRecibida!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			proveedorAcreedor = rs.getString("FACREC_PROVACRE").trim();
			fecha = rs.getInt("FACREC_FECHA");
			orden = rs.getInt("FACREC_ORDEN");
			centro = rs.getInt("FACREC_CENTRO");
			fechaAsientoApunte = rs.getLong("FACREC_FECH_ASTO_APT");
			factura = rs.getInt("FACREC_FACTURA");
			base[0] = rs.getDouble("FACREC_BASES_1");
			base[1] = rs.getDouble("FACREC_BASES_2");
			base[2] = rs.getDouble("FACREC_BASES_3");
			base[3] = rs.getDouble("FACREC_BASES_4");
			iva = rs.getDouble("FACREC_IVA");
			recargoEquivalencia = rs.getDouble("FACREC_RECEQ");
			total = rs.getDouble("FACREC_TOTAL");	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FacturaRecibida!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO FACREC " +
						   "(EMPRESA, " +
						   "FACREC_PROVACRE, " +
						   "FACREC_FECHA, " +
						   "FACREC_ORDEN, " +
						   "FACREC_CENTRO, " +
						   "FACREC_FECH_ASTO_APT, " +
						   "FACREC_FACTURA, " +
						   "FACREC_BASES_1, " +
						   "FACREC_BASES_2, " +
						   "FACREC_BASES_3, " +
						   "FACREC_BASES_4, " +
						   "FACREC_IVA, " +
						   "FACREC_RECEQ, " +
						   "FACREC_TOTAL) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "FACREC_PROVACRE = ?, " +
						   "FACREC_FECHA = ?, " +
						   "FACREC_ORDEN = ?, " +
						   "FACREC_CENTRO = ?, " +
						   "FACREC_FECH_ASTO_APT = ?, " +
						   "FACREC_FACTURA = ?, " +
						   "FACREC_BASES_1 = ?, " +
						   "FACREC_BASES_2 = ?, " +
						   "FACREC_BASES_3 = ?, " +
						   "FACREC_BASES_4 = ?, " +
						   "FACREC_IVA = ?, " +
						   "FACREC_RECEQ = ?, " +
						   "FACREC_TOTAL = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(proveedorAcreedor, 9));
			ps.setInt(i++, fecha);
			ps.setInt(i++, orden);
			ps.setInt(i++, centro);
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, factura);
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, base[j]);
			ps.setDouble(i++, iva);
			ps.setDouble(i++, recargoEquivalencia);
			ps.setDouble(i++, total);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(proveedorAcreedor, 9));
			ps.setInt(i++, fecha);
			ps.setInt(i++, orden);
			ps.setInt(i++, centro);
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, factura);
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, base[j]);
			ps.setDouble(i++, iva);
			ps.setDouble(i++, recargoEquivalencia);
			ps.setDouble(i++, total);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de FacturaRecibida!!!");
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

	public String getProveedorAcreedor() {
		return proveedorAcreedor;
	}

	public void setProveedorAcreedor(String proveedorAcreedor) {
		this.proveedorAcreedor = proveedorAcreedor;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public long getFechaAsientoApunte() {
		return fechaAsientoApunte;
	}

	public void setFechaAsientoApunte(long fechaAsientoApunte) {
		this.fechaAsientoApunte = fechaAsientoApunte;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public double getBase(int i) {
		return base[i];
	}
	
	public double[] getBase() {
		return base;
	}

	public void setBase(int indice, double valor) {
		this.base[indice] = valor;
	}

	public void setBase(double[] base) {
		this.base = base;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getRecargoEquivalencia() {
		return recargoEquivalencia;
	}

	public void setRecargoEquivalencia(double recargoEquivalencia) {
		this.recargoEquivalencia = recargoEquivalencia;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
