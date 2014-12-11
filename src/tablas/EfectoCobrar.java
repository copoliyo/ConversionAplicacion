package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;




public class EfectoCobrar {
	private String empresa;
	private int banco;
	private int fechaCobro;
	private int vencimiento;
	private int efecto;
	private int centro;
	private int situacion;
	private int fechaRemesa;
	private int remesa;
	private int factura;
	private String cuenta;
	private long fechaAsientoApunte;
	private int fechaEfecto;
	private double importe;
	private int vencimientoDv;
	private int numeroBanco;
	private int numeroSucursal;
	private int digitosControl;
	private long cuentaBancaria;
	private double timbres;
	private int fechaFactura;
	private int representante;
	private int reciboImpreso;
	private int origen;

	public EfectoCobrar(){
		empresa = DatosComunes.eEmpresa;		
		banco = 0;
		fechaCobro = 0;
		vencimiento = 0;
		efecto = 0;
		centro = 0;
		situacion = 0;
		fechaRemesa = 0;
		remesa = 0;
		factura = 0;
		cuenta = "";
		fechaAsientoApunte = 0L;
		fechaEfecto = 0;
		importe = 0.0;
		vencimientoDv = 0;
		numeroBanco = 0;
		numeroSucursal = 0;
		digitosControl = 0;
		cuentaBancaria = 0;
		timbres = 0.0;
		fechaFactura = 0;
		representante = 0;
		reciboImpreso = 0;
		origen = 0;
	}

	public EfectoCobrar(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				banco = rs.getInt("EFECOB_BANCO");
				fechaCobro = rs.getInt("EFECOB_FECHA_COBRO");
				vencimiento = rs.getInt("EFECOB_VENCIM");
				efecto = rs.getInt("EFECOB_EFECTO");
				centro = rs.getInt("EFECOB_CENTRO");
				situacion = rs.getInt("EFECOB_SITUACION");
				fechaRemesa = rs.getInt("EFECOB_FECHA_REMESA");
				remesa = rs.getInt("EFECOB_REMESA");
				factura = rs.getInt("EFECOB_FACTURA");
				cuenta = rs.getString("EFECOB_CUENTA").trim();
				fechaAsientoApunte = rs.getLong("EFECOB_FECH_ASTO_APT");
				fechaEfecto = rs.getInt("EFECOB_FECHA_EFE");
				importe = rs.getDouble("EFECOB_IMPORTE");
				vencimientoDv = rs.getInt("EFECOB_VTO_DV");
				numeroBanco = rs.getInt("EFECOB_NRO_BCO");
				numeroSucursal = rs.getInt("EFECOB_NRO_SUC");
				digitosControl = rs.getInt("EFECOB_DC");
				cuentaBancaria = rs.getLong("EFECOB_CTA");
				timbres = rs.getDouble("EFECOB_TIMBRES");
				fechaFactura = rs.getInt("EFECOB_FECHA_FRA");
				representante = rs.getInt("EFECOB_REPRE");
				reciboImpreso = rs.getInt("EFECOB_RECIBO_IMPRESO");
				origen = rs.getInt("EFECOB_ORIGEN");	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de EfectoCobrar!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			banco = rs.getInt("EFECOB_BANCO");
			fechaCobro = rs.getInt("EFECOB_FECHA_COBRO");
			vencimiento = rs.getInt("EFECOB_VENCIM");
			efecto = rs.getInt("EFECOB_EFECTO");
			centro = rs.getInt("EFECOB_CENTRO");
			situacion = rs.getInt("EFECOB_SITUACION");
			fechaRemesa = rs.getInt("EFECOB_FECHA_REMESA");
			remesa = rs.getInt("EFECOB_REMESA");
			factura = rs.getInt("EFECOB_FACTURA");
			cuenta = rs.getString("EFECOB_CUENTA").trim();
			fechaAsientoApunte = rs.getLong("EFECOB_FECH_ASTO_APT");
			fechaEfecto = rs.getInt("EFECOB_FECHA_EFE");
			importe = rs.getDouble("EFECOB_IMPORTE");
			vencimientoDv = rs.getInt("EFECOB_VTO_DV");
			numeroBanco = rs.getInt("EFECOB_NRO_BCO");
			numeroSucursal = rs.getInt("EFECOB_NRO_SUC");
			digitosControl = rs.getInt("EFECOB_DC");
			cuentaBancaria = rs.getLong("EFECOB_CTA");
			timbres = rs.getDouble("EFECOB_TIMBRES");
			fechaFactura = rs.getInt("EFECOB_FECHA_FRA");
			representante = rs.getInt("EFECOB_REPRE");
			reciboImpreso = rs.getInt("EFECOB_RECIBO_IMPRESO");
			origen = rs.getInt("EFECOB_ORIGEN");	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de EfectoCobrar!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO EFECOB " +
						   "(EMPRESA, " +
						   "EFECOB_BANCO, " +
						   "EFECOB_FECHA_COBRO, " +
						   "EFECOB_VENCIM, " +
						   "EFECOB_EFECTO, " +
						   "EFECOB_CENTRO, " +
						   "EFECOB_SITUACION, " +
						   "EFECOB_FECHA_REMESA, " +
						   "EFECOB_REMESA, " +
						   "EFECOB_FACTURA, " +
						   "EFECOB_CUENTA, " +
						   "EFECOB_FECH_ASTO_APT, " +
						   "EFECOB_FECHA_EFE, " +
						   "EFECOB_IMPORTE, " +
						   "EFECOB_VTO_DV, " +
						   "EFECOB_NRO_BCO, " +
						   "EFECOB_NRO_SUC, " +
						   "EFECOB_DC, " +
						   "EFECOB_CTA, " +
						   "EFECOB_TIMBRES, " +
						   "EFECOB_FECHA_FRA, " +
						   "EFECOB_REPRE, " +
						   "EFECOB_RECIBO_IMPRESO, " +
						   "EFECOB_ORIGEN) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "EFECOB_BANCO = ?, " +
						   "EFECOB_FECHA_COBRO = ?, " +
						   "EFECOB_VENCIM = ?, " +
						   "EFECOB_EFECTO = ?, " +
						   "EFECOB_CENTRO = ?, " +
						   "EFECOB_SITUACION = ?, " +
						   "EFECOB_FECHA_REMESA = ?, " +
						   "EFECOB_REMESA = ?, " +
						   "EFECOB_FACTURA = ?, " +
						   "EFECOB_CUENTA = ?, " +
						   "EFECOB_FECH_ASTO_APT = ?, " +
						   "EFECOB_FECHA_EFE = ?, " +
						   "EFECOB_IMPORTE = ?, " +
						   "EFECOB_VTO_DV = ?, " +
						   "EFECOB_NRO_BCO = ?, " +
						   "EFECOB_NRO_SUC = ?, " +
						   "EFECOB_DC = ?, " +
						   "EFECOB_CTA = ?, " +
						   "EFECOB_TIMBRES = ?, " +
						   "EFECOB_FECHA_FRA = ?, " +
						   "EFECOB_REPRE = ?, " +
						   "EFECOB_RECIBO_IMPRESO = ?, " +
						   "EFECOB_ORIGEN = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, banco);
			ps.setInt(i++, fechaCobro);
			ps.setInt(i++, vencimiento);
			ps.setInt(i++, efecto);
			ps.setInt(i++, centro);
			ps.setInt(i++, situacion);
			ps.setInt(i++ ,fechaRemesa);
			ps.setInt(i++, remesa);
			ps.setInt(i++, factura);
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, fechaEfecto);
			ps.setDouble(i++, importe);
			ps.setInt(i++, vencimientoDv);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitosControl);
			ps.setLong(i++, cuentaBancaria);
			ps.setDouble(i++, timbres);
			ps.setInt(i++, fechaFactura);
			ps.setInt(i++, representante);
			ps.setInt(i++, reciboImpreso);
			ps.setInt(i++, origen);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, banco);
			ps.setInt(i++, fechaCobro);
			ps.setInt(i++, vencimiento);
			ps.setInt(i++, efecto);
			ps.setInt(i++, centro);
			ps.setInt(i++, situacion);
			ps.setInt(i++ ,fechaRemesa);
			ps.setInt(i++, remesa);
			ps.setInt(i++, factura);
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, fechaEfecto);
			ps.setDouble(i++, importe);
			ps.setInt(i++, vencimientoDv);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitosControl);
			ps.setLong(i++, cuentaBancaria);
			ps.setDouble(i++, timbres);
			ps.setInt(i++, fechaFactura);
			ps.setInt(i++, representante);
			ps.setInt(i++, reciboImpreso);
			ps.setInt(i++, origen);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de EfectoCobrar!!!");
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

	public int getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(int fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public int getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(int vencimiento) {
		this.vencimiento = vencimiento;
	}

	public int getEfecto() {
		return efecto;
	}

	public void setEfecto(int efecto) {
		this.efecto = efecto;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getSituacion() {
		return situacion;
	}

	public void setSituacion(int situacion) {
		this.situacion = situacion;
	}

	public int getFechaRemesa() {
		return fechaRemesa;
	}

	public void setFechaRemesa(int fechaRemesa) {
		this.fechaRemesa = fechaRemesa;
	}

	public int getRemesa() {
		return remesa;
	}

	public void setRemesa(int remesa) {
		this.remesa = remesa;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public long getFechaAsientoApunte() {
		return fechaAsientoApunte;
	}

	public void setFechaAsientoApunte(long fechaAsientoApunte) {
		this.fechaAsientoApunte = fechaAsientoApunte;
	}

	public int getFechaEfecto() {
		return fechaEfecto;
	}

	public void setFechaEfecto(int fechaEfecto) {
		this.fechaEfecto = fechaEfecto;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getVencimientoDv() {
		return vencimientoDv;
	}

	public void setVencimientoDv(int vencimientoDv) {
		this.vencimientoDv = vencimientoDv;
	}

	public int getNumeroBanco() {
		return numeroBanco;
	}

	public void setNumeroBanco(int numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	public int getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(int numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public int getDigitosControl() {
		return digitosControl;
	}

	public void setDigitosControl(int digitosControl) {
		this.digitosControl = digitosControl;
	}

	public long getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(long cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public double getTimbres() {
		return timbres;
	}

	public void setTimbres(double timbres) {
		this.timbres = timbres;
	}

	public int getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(int fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
	}

	public int getReciboImpreso() {
		return reciboImpreso;
	}

	public void setReciboImpreso(int reciboImpreso) {
		this.reciboImpreso = reciboImpreso;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}
}
