package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import util.Apariencia;
import util.BaseDatos;
import util.Cadena;

import java.sql.ResultSet;


public class EfectoPagar {
	private String empresa;
	private String cuenta;
	private int fechaPago;
	private int vencimiento;
	private int efecto;
	private int centro;
	private int banco;
	private long fechaAsientoApunte;
	private int documentoOrigen;
	private int fechaFactura;
	private double importe;
	private int fechaPagare;
	private int bancoPagare;
	private int pagare;

	public EfectoPagar(){
		empresa = DatosComunes.eEmpresa;
		cuenta = "";
		fechaPago = 0;
		vencimiento = 0;
		efecto = 0;
		centro = 0;
		banco = 0;
		fechaAsientoApunte = 0L;
		documentoOrigen = 0;
		fechaFactura = 0;
		importe = 0.0;
		fechaPagare = 0;
		bancoPagare = 0;
		pagare = 0;
	}

	public EfectoPagar(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cuenta = rs.getString("EFEPAG_CUENTA").trim();
				fechaPago = rs.getInt("EFEPAG_FECHA_PAGO");
				vencimiento = rs.getInt("EFEPAG_VENCIM");
				efecto = rs.getInt("EFEPAG_EFECTO");
				centro = rs.getInt("EFEPAG_CENTRO");
				banco = rs.getInt("EFEPAG_BCO");
				fechaAsientoApunte = rs.getLong("EFEPAG_FECH_ASTO_APT");
				documentoOrigen = rs.getInt("EFEPAG_DOC_ORIGEN");
				fechaFactura = rs.getInt("EFEPAG_FECHA_FRA");
				importe = rs.getDouble("EFEPAG_IMPORTE");
				fechaPagare = rs.getInt("EFEPAG_FECHA_PAGARE");
				bancoPagare = rs.getInt("EFEPAG_BANCO_PAGARE");
				pagare = rs.getInt("EFEPAG_PAGARE");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de EfectoPagar!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			cuenta = rs.getString("EFEPAG_CUENTA").trim();
			fechaPago = rs.getInt("EFEPAG_FECHA_PAGO");
			vencimiento = rs.getInt("EFEPAG_VENCIM");
			efecto = rs.getInt("EFEPAG_EFECTO");
			centro = rs.getInt("EFEPAG_CENTRO");
			banco = rs.getInt("EFEPAG_BCO");
			fechaAsientoApunte = rs.getLong("EFEPAG_FECH_ASTO_APT");
			documentoOrigen = rs.getInt("EFEPAG_DOC_ORIGEN");
			fechaFactura = rs.getInt("EFEPAG_FECHA_FRA");
			importe = rs.getDouble("EFEPAG_IMPORTE");
			fechaPagare = rs.getInt("EFEPAG_FECHA_PAGARE");
			bancoPagare = rs.getInt("EFEPAG_BANCO_PAGARE");
			pagare = rs.getInt("EFEPAG_PAGARE");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de EfectoPagar!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	// Con este método leemos una cuenta pasando tan sólo una consulta SQL
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
				Apariencia.mensajeInformativo(5, "Error en lectura fichero de Efecto a Pagar");				
			}
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO EFEPAG " +
						   "(EMPRESA, " +
						   "EFEPAG_CUENTA, " +
						   "EFEPAG_FECHA_PAGO, " +
						   "EFEPAG_VENCIM, " +
						   "EFEPAG_EFECTO, " +
						   "EFEPAG_CENTRO, " +
						   "EFEPAG_BCO, " +
						   "EFEPAG_FECH_ASTO_APT, " +
						   "EFEPAG_DOC_ORIGEN, " +
						   "EFEPAG_FECHA_FRA, " +
						   "EFEPAG_IMPORTE, " +
						   "EFEPAG_FECHA_PAGARE, " +
						   "EFEPAG_BANCO_PAGARE, " +
						   "EFEPAG_PAGARE) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "EFEPAG_CUENTA = ?, " +
						   "EFEPAG_FECHA_PAGO = ?, " +
						   "EFEPAG_VENCIM = ?, " +
						   "EFEPAG_EFECTO = ?, " +
						   "EFEPAG_CENTRO = ?, " +
						   "EFEPAG_BCO = ?, " +
						   "EFEPAG_FECH_ASTO_APT = ?, " +
						   "EFEPAG_DOC_ORIGEN = ?, " +
						   "EFEPAG_FECHA_FRA = ?, " +
						   "EFEPAG_IMPORTE = ?, " +
						   "EFEPAG_FECHA_PAGARE = ?, " +
						   "EFEPAG_BANCO_PAGARE = ?, " +
						   "EFEPAG_PAGARE = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setInt(i++, fechaPago);
			ps.setInt(i++, vencimiento);
			ps.setInt(i++, efecto);
			ps.setInt(i++, centro);
			ps.setInt(i++, banco);
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, documentoOrigen);
			ps.setInt(i++, fechaFactura);
			ps.setDouble(i++, importe);
			ps.setInt(i++, fechaPagare);
			ps.setInt(i++, bancoPagare);
			ps.setInt(i++, pagare);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setInt(i++, fechaPago);
			ps.setInt(i++, vencimiento);
			ps.setInt(i++, efecto);
			ps.setInt(i++, centro);
			ps.setInt(i++, banco);
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, documentoOrigen);
			ps.setInt(i++, fechaFactura);
			ps.setDouble(i++, importe);
			ps.setInt(i++, fechaPagare);
			ps.setInt(i++, bancoPagare);
			ps.setInt(i++, pagare);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de EfectoPagar!!!");
				e.printStackTrace();
			}
		}
	}
	
	// Borramos un EFECTO  de un CENTRO concreto, en una FECHA_DE_VENCIMIENTO y con un NUMERO_DE_EFECTO.
	// Devolvemos el número de registros borrados o -1 si hay error
	public static int delete(int centro, int fechaVencimiento, int numeroEfecto){
		int registrosBorrados = 0;
		
		Statement ps = null;
   
		String sqlDelete = "DELETE FROM EFEPAG WHERE " + 
							"EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
							"EFEPAG_CENTRO = " + centro + " AND " +
							"EFEPAG_VENCIM = " + fechaVencimiento + " AND " +
							"EFEPAG_EFECTO = " + numeroEfecto;

		try {
			ps = MysqlConnect.db.conn.createStatement();			
			
			registrosBorrados = ps.executeUpdate(sqlDelete);

		} catch (SQLException e) {
			registrosBorrados = -1;
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
						"Error en borrado fichero de Efectos a Pagar!!!");
				e.printStackTrace();
			}
		}		
		
		return registrosBorrados;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public int getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(int fechaPago) {
		this.fechaPago = fechaPago;
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

	public int getBanco() {
		return banco;
	}

	public void setBanco(int banco) {
		this.banco = banco;
	}

	public long getFechaAsientoApunte() {
		return fechaAsientoApunte;
	}

	public void setFechaAsientoApunte(long fechaAsientoApunte) {
		this.fechaAsientoApunte = fechaAsientoApunte;
	}

	public int getDocumentoOrigen() {
		return documentoOrigen;
	}

	public void setDocumentoOrigen(int documentoOrigen) {
		this.documentoOrigen = documentoOrigen;
	}

	public int getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(int fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getFechaPagare() {
		return fechaPagare;
	}

	public void setFechaPagare(int fechaPagare) {
		this.fechaPagare = fechaPagare;
	}

	public int getBancoPagare() {
		return bancoPagare;
	}

	public void setBancoPagare(int bancoPagare) {
		this.bancoPagare = bancoPagare;
	}

	public int getPagare() {
		return pagare;
	}

	public void setPagare(int pagare) {
		this.pagare = pagare;
	}
}
