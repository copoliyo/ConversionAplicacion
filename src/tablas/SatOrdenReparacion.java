package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class SatOrdenReparacion {
	public String empresa;
	public int fecha;
	public int numeroSat;
	public int orden;
	public int linea;
	public String articulo;
	public int centro;
	public int cliente;
	public int documento;
	public int fechaRecoger;
	public int fechaTerminacion;
	public int fechaEntregaACliente;
	public double importe;
	public String concepto;
	public String averia;
	public String tty;
	public int usuario;
	public int dayTime;

	public SatOrdenReparacion(){
		empresa = DatosComunes.eEmpresa;
		fecha = 0;
		numeroSat = 0;
		orden = 0;
		linea = 0;
		articulo = "";
		centro = 0;
		cliente = 0;
		documento = 0;
		fechaRecoger = 0;
		fechaTerminacion = 0;
		fechaEntregaACliente = 0;
		importe = 0.0;
		concepto = "";
		averia = "";
		tty = "";
		usuario = 0;
		dayTime = 0;
	}

	public SatOrdenReparacion(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				fecha = rs.getInt("SATREP_FECHA");
				numeroSat = rs.getInt("SATREP_NRO_SAT");
				orden = rs.getInt("SATREP_ORDEN");
				linea = rs.getInt("SATREP_LINEA");
				articulo = rs.getString("SATREP_ARTCLO").trim();
				centro = rs.getInt("SATREP_CENTRO");
				cliente = rs.getInt("SATREP_CLIENTE");
				documento = rs.getInt("SATREP_DOCUMENTO");
				fechaRecoger = rs.getInt("SATREP_FEC_RECOGER");
				fechaTerminacion = rs.getInt("SATREP_FEC_TERMINAC");
				fechaEntregaACliente = rs.getInt("SATREP_FEC_ENTREG_A_CLI");
				importe = rs.getDouble("SATREP_IMPORTE");
				concepto = rs.getString("SATREP_CONCEPTO").trim();
				averia = rs.getString("SATREP_AVERIA").trim();
				tty = rs.getString("SATREP_TTY").trim();
				usuario = rs.getInt("SATREP_USUARIO");
				dayTime = rs.getInt("SATREP_DAY_TIME");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de SatOrdenReparacion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			fecha = rs.getInt("SATREP_FECHA");
			numeroSat = rs.getInt("SATREP_NRO_SAT");
			orden = rs.getInt("SATREP_ORDEN");
			linea = rs.getInt("SATREP_LINEA");
			articulo = rs.getString("SATREP_ARTCLO").trim();
			centro = rs.getInt("SATREP_CENTRO");
			cliente = rs.getInt("SATREP_CLIENTE");
			documento = rs.getInt("SATREP_DOCUMENTO");
			fechaRecoger = rs.getInt("SATREP_FEC_RECOGER");
			fechaTerminacion = rs.getInt("SATREP_FEC_TERMINAC");
			fechaEntregaACliente = rs.getInt("SATREP_FEC_ENTREG_A_CLI");
			importe = rs.getDouble("SATREP_IMPORTE");
			concepto = rs.getString("SATREP_CONCEPTO").trim();
			averia = rs.getString("SATREP_AVERIA").trim();
			tty = rs.getString("SATREP_TTY").trim();
			usuario = rs.getInt("SATREP_USUARIO");
			dayTime = rs.getInt("SATREP_DAY_TIME");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de SatOrdenReparacion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO SATREP " +
						   "(EMPRESA, " +
						   "SATREP_FECHA, " +
						   "SATREP_NRO_SAT, " +
						   "SATREP_ORDEN, " +
						   "SATREP_LINEA, " +
						   "SATREP_ARTCLO, " +
						   "SATREP_CENTRO, " +
						   "SATREP_CLIENTE, " +
						   "SATREP_DOCUMENTO, " +
						   "SATREP_FEC_RECOGER, " +
						   "SATREP_FEC_TERMINAC, " +
						   "SATREP_FEC_ENTREG_A_CLI, " +
						   "SATREP_IMPORTE, " +
						   "SATREP_CONCEPTO, " +
						   "SATREP_AVERIA, " +
						   "SATREP_TTY, " +
						   "SATREP_USUARIO, " +
						   "SATREP_DAY_TIME) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "SATREP_FECHA = ?, " +
						   "SATREP_NRO_SAT = ?, " +
						   "SATREP_ORDEN = ?, " +
						   "SATREP_LINEA = ?, " +
						   "SATREP_ARTCLO = ?, " +
						   "SATREP_CENTRO = ?, " +
						   "SATREP_CLIENTE = ?, " +
						   "SATREP_DOCUMENTO = ?, " +
						   "SATREP_FEC_RECOGER = ?, " +
						   "SATREP_FEC_TERMINAC = ?, " +
						   "SATREP_FEC_ENTREG_A_CLI = ?, " +
						   "SATREP_IMPORTE = ?, " +
						   "SATREP_CONCEPTO = ?, " +
						   "SATREP_AVERIA = ?, " +
						   "SATREP_TTY = ?, " +
						   "SATREP_USUARIO = ?, " +
						   "SATREP_DAY_TIME = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, numeroSat);
			ps.setInt(i++, orden);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setInt(i++, centro);
			ps.setInt(i++, cliente);
			ps.setInt(i++, documento);
			ps.setInt(i++, fechaRecoger);
			ps.setInt(i++, fechaTerminacion);
			ps.setInt(i++, fechaEntregaACliente);
			ps.setDouble(i++, importe);
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setString(i++, Cadena.left(averia, 30));
			ps.setString(i++, Cadena.left(tty, 5));
			ps.setInt(i++, usuario);
			ps.setInt(i++, dayTime);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, fecha);
			ps.setInt(i++, numeroSat);
			ps.setInt(i++, orden);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setInt(i++, centro);
			ps.setInt(i++, cliente);
			ps.setInt(i++, documento);
			ps.setInt(i++, fechaRecoger);
			ps.setInt(i++, fechaTerminacion);
			ps.setInt(i++, fechaEntregaACliente);
			ps.setDouble(i++, importe);
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setString(i++, Cadena.left(averia, 30));
			ps.setString(i++, Cadena.left(tty, 5));
			ps.setInt(i++, usuario);
			ps.setInt(i++, dayTime);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de SatOrdenReparacion!!!");
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

	public int getNumeroSat() {
		return numeroSat;
	}

	public void setNumeroSat(int numeroSat) {
		this.numeroSat = numeroSat;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
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

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public int getFechaRecoger() {
		return fechaRecoger;
	}

	public void setFechaRecoger(int fechaRecoger) {
		this.fechaRecoger = fechaRecoger;
	}

	public int getFechaTerminacion() {
		return fechaTerminacion;
	}

	public void setFechaTerminacion(int fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	public int getFechaEntregaACliente() {
		return fechaEntregaACliente;
	}

	public void setFechaEntregaACliente(int fechaEntregaACliente) {
		this.fechaEntregaACliente = fechaEntregaACliente;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getAveria() {
		return averia;
	}

	public void setAveria(String averia) {
		this.averia = averia;
	}

	public String getTty() {
		return tty;
	}

	public void setTty(String tty) {
		this.tty = tty;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getDayTime() {
		return dayTime;
	}

	public void setDayTime(int dayTime) {
		this.dayTime = dayTime;
	}
}
