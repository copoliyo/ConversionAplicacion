package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import util.Apariencia;
import util.BaseDatos;

import util.Cadena;

/**
 *
 * @author Txus
 */
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

    /**
     *
     */
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

    /**
     *
     * @param rs
     */
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

    /**
     *
     * @param rs
     */
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
        
        // Con este método leemos una cuenta pasando tan sólo una consulta SQL

    /**
     *
     * @param strSql
     */
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
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de Efecto a Cobrar");				
			}
		}
	}

    /**
     *
     */
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
	
        // Borramos un EFECTO  de un CENTRO concreto, en una FECHA_DE_VENCIMIENTO y con un NUMERO_DE_EFECTO.
	// Devolvemos el número de registros borrados o -1 si hay error

    /**
     *
     * @param centro
     * @param fechaVencimiento
     * @param numeroEfecto
     * @return Número de registros borrados
     */
    	public int delete(int centro, int fechaVencimiento, int numeroEfecto){
		int registrosBorrados = 0;
		
		Statement ps = null;
   
		String sqlDelete = "DELETE FROM EFECOB WHERE " + 
							"EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
							"EFECOB_CENTRO = " + centro + " AND " +
							"EFECOB_VENCIM = " + fechaVencimiento + " AND " +
							"EFECOB_EFECTO = " + numeroEfecto;

		try {
			ps = MysqlConnect.db.conn.createStatement();			
			
			registrosBorrados = ps.executeUpdate(sqlDelete);

		} catch (SQLException e) {
			registrosBorrados = -1;
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
						"Error en borrado fichero de Efectos a Cobrar!!!");
				e.printStackTrace();
			}
		}		
		
		return registrosBorrados;
	}
        
    /**
     *
     * @return
     */
    public String getEmpresa() {
		return empresa;
	}

    /**
     *
     * @param empresa
     */
    public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

    /**
     *
     * @return
     */
    public int getBanco() {
		return banco;
	}

    /**
     *
     * @param banco
     */
    public void setBanco(int banco) {
		this.banco = banco;
	}

    /**
     *
     * @return
     */
    public int getFechaCobro() {
		return fechaCobro;
	}

    /**
     *
     * @param fechaCobro
     */
    public void setFechaCobro(int fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

    /**
     *
     * @return
     */
    public int getVencimiento() {
		return vencimiento;
	}

    /**
     *
     * @param vencimiento
     */
    public void setVencimiento(int vencimiento) {
		this.vencimiento = vencimiento;
	}

    /**
     *
     * @return
     */
    public int getEfecto() {
		return efecto;
	}

    /**
     *
     * @param efecto
     */
    public void setEfecto(int efecto) {
		this.efecto = efecto;
	}

    /**
     *
     * @return
     */
    public int getCentro() {
		return centro;
	}

    /**
     *
     * @param centro
     */
    public void setCentro(int centro) {
		this.centro = centro;
	}

    /**
     *
     * @return
     */
    public int getSituacion() {
		return situacion;
	}

    /**
     *
     * @param situacion
     */
    public void setSituacion(int situacion) {
		this.situacion = situacion;
	}

    /**
     *
     * @return
     */
    public int getFechaRemesa() {
		return fechaRemesa;
	}

    /**
     *
     * @param fechaRemesa
     */
    public void setFechaRemesa(int fechaRemesa) {
		this.fechaRemesa = fechaRemesa;
	}

    /**
     *
     * @return
     */
    public int getRemesa() {
		return remesa;
	}

    /**
     *
     * @param remesa
     */
    public void setRemesa(int remesa) {
		this.remesa = remesa;
	}

    /**
     *
     * @return
     */
    public int getFactura() {
		return factura;
	}

    /**
     *
     * @param factura
     */
    public void setFactura(int factura) {
		this.factura = factura;
	}

    /**
     *
     * @return
     */
    public String getCuenta() {
		return cuenta;
	}

    /**
     *
     * @param cuenta
     */
    public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

    /**
     *
     * @return
     */
    public long getFechaAsientoApunte() {
		return fechaAsientoApunte;
	}

    /**
     *
     * @param fechaAsientoApunte
     */
    public void setFechaAsientoApunte(long fechaAsientoApunte) {
		this.fechaAsientoApunte = fechaAsientoApunte;
	}

    /**
     *
     * @return
     */
    public int getFechaEfecto() {
		return fechaEfecto;
	}

    /**
     *
     * @param fechaEfecto
     */
    public void setFechaEfecto(int fechaEfecto) {
		this.fechaEfecto = fechaEfecto;
	}

    /**
     *
     * @return
     */
    public double getImporte() {
		return importe;
	}

    /**
     *
     * @param importe
     */
    public void setImporte(double importe) {
		this.importe = importe;
	}

    /**
     *
     * @return
     */
    public int getVencimientoDv() {
		return vencimientoDv;
	}

    /**
     *
     * @param vencimientoDv
     */
    public void setVencimientoDv(int vencimientoDv) {
		this.vencimientoDv = vencimientoDv;
	}

    /**
     *
     * @return
     */
    public int getNumeroBanco() {
		return numeroBanco;
	}

    /**
     *
     * @param numeroBanco
     */
    public void setNumeroBanco(int numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

    /**
     *
     * @return
     */
    public int getNumeroSucursal() {
		return numeroSucursal;
	}

    /**
     *
     * @param numeroSucursal
     */
    public void setNumeroSucursal(int numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

    /**
     *
     * @return
     */
    public int getDigitosControl() {
		return digitosControl;
	}

    /**
     *
     * @param digitosControl
     */
    public void setDigitosControl(int digitosControl) {
		this.digitosControl = digitosControl;
	}

    /**
     *
     * @return
     */
    public long getCuentaBancaria() {
		return cuentaBancaria;
	}

    /**
     *
     * @param cuentaBancaria
     */
    public void setCuentaBancaria(long cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

    /**
     *
     * @return
     */
    public double getTimbres() {
		return timbres;
	}

    /**
     *
     * @param timbres
     */
    public void setTimbres(double timbres) {
		this.timbres = timbres;
	}

    /**
     *
     * @return
     */
    public int getFechaFactura() {
		return fechaFactura;
	}

    /**
     *
     * @param fechaFactura
     */
    public void setFechaFactura(int fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

    /**
     *
     * @return
     */
    public int getRepresentante() {
		return representante;
	}

    /**
     *
     * @param representante
     */
    public void setRepresentante(int representante) {
		this.representante = representante;
	}

    /**
     *
     * @return
     */
    public int getReciboImpreso() {
		return reciboImpreso;
	}

    /**
     *
     * @param reciboImpreso
     */
    public void setReciboImpreso(int reciboImpreso) {
		this.reciboImpreso = reciboImpreso;
	}

    /**
     *
     * @return
     */
    public int getOrigen() {
		return origen;
	}

    /**
     *
     * @param origen
     */
    public void setOrigen(int origen) {
		this.origen = origen;
	}
}
