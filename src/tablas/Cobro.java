package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class Cobro {
	private String empresa;
	private int cliente;
	private int fecha;
	private int ticket;
	private int numeroPago;
	private int vendedor;
	private int caja;
	private double importeTicket;
	private double importeCobro;
	private String previsionCobro;
	private int fechaCobro;
	private int formaCobro;
	private String usuario;
	private int dayTime;
	private int representante;

	public Cobro(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		fecha = 0;
		ticket = 0;
		numeroPago = 0;
		vendedor = 0;
		caja = 0;
		importeTicket = 0.0;
		importeCobro = 0.0;
		previsionCobro = "";
		fechaCobro = 0;
		formaCobro = 0;
		usuario = DatosComunes.usuario;
		dayTime = 0;
		representante = 0;
	}

	public Cobro(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("COBROS_CLIENTE");
				fecha = rs.getInt("COBROS_FECHA");
				ticket = rs.getInt("COBROS_TICKET");
				numeroPago = rs.getInt("COBROS_NRO_PAGO");
				vendedor = rs.getInt("COBROS_VENDEDOR");
				caja = rs.getInt("COBROS_CAJA");
				importeTicket = rs.getDouble("COBROS_IMPORTE_TICKET");
				importeCobro = rs.getDouble("COBROS_IMPORTE_COBRO");
				previsionCobro = rs.getString("COBROS_PREVIS_COBRO").trim();
				fechaCobro = rs.getInt("COBROS_FECHA_COBRO");
				formaCobro = rs.getInt("COBROS_FORCOB");
				usuario = rs.getString("COBROS_USUARIO").trim();
				dayTime = rs.getInt("COBROS_DAY_TIME");
				representante = rs.getInt("COBROS_REPRES");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Cobro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			cliente = rs.getInt("COBROS_CLIENTE");
			fecha = rs.getInt("COBROS_FECHA");
			ticket = rs.getInt("COBROS_TICKET");
			numeroPago = rs.getInt("COBROS_NRO_PAGO");
			vendedor = rs.getInt("COBROS_VENDEDOR");
			caja = rs.getInt("COBROS_CAJA");
			importeTicket = rs.getDouble("COBROS_IMPORTE_TICKET");
			importeCobro = rs.getDouble("COBROS_IMPORTE_COBRO");
			previsionCobro = rs.getString("COBROS_PREVIS_COBRO").trim();
			fechaCobro = rs.getInt("COBROS_FECHA_COBRO");
			formaCobro = rs.getInt("COBROS_FORCOB");
			usuario = rs.getString("COBROS_USUARIO").trim();
			dayTime = rs.getInt("COBROS_DAY_TIME");
			representante = rs.getInt("COBROS_REPRES");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Cobro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO COBROS " +
						   "(EMPRESA, " +
						   "COBROS_CLIENTE, " +
						   "COBROS_FECHA, " +
						   "COBROS_TICKET, " +
						   "COBROS_NRO_PAGO, " +
						   "COBROS_VENDEDOR, " +
						   "COBROS_CAJA, " +
						   "COBROS_IMPORTE_TICKET, " +
						   "COBROS_IMPORTE_COBRO, " +
						   "COBROS_PREVIS_COBRO, " +
						   "COBROS_FECHA_COBRO, " +
						   "COBROS_FORCOB, " +
						   "COBROS_USUARIO, " +
						   "COBROS_DAY_TIME, " +
						   "COBROS_REPRES) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "COBROS_CLIENTE = ?, " +
						   "COBROS_FECHA = ?, " +
						   "COBROS_TICKET = ?, " +
						   "COBROS_NRO_PAGO = ?, " +
						   "COBROS_VENDEDOR = ?, " +
						   "COBROS_CAJA = ?, " +
						   "COBROS_IMPORTE_TICKET = ?, " +
						   "COBROS_IMPORTE_COBRO = ?, " +
						   "COBROS_PREVIS_COBRO = ?, " +
						   "COBROS_FECHA_COBRO = ?, " +
						   "COBROS_FORCOB = ?, " +
						   "COBROS_USUARIO = ?, " +
						   "COBROS_DAY_TIME = ?, " +
						   "COBROS_REPRES = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, fecha);
			ps.setInt(i++, ticket);
			ps.setInt(i++, numeroPago);
			ps.setInt(i++, vendedor);
			ps.setInt(i++, caja);
			ps.setDouble(i++, importeTicket);
			ps.setDouble(i++, importeCobro);
			ps.setString(i++, Cadena.left(previsionCobro, 25));
			ps.setInt(i++, fechaCobro);
			ps.setInt(i++, formaCobro);
			ps.setString(i++, Cadena.left(usuario, 8));
			ps.setInt(i++, dayTime);
			ps.setInt(i++, representante);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, fecha);
			ps.setInt(i++, ticket);
			ps.setInt(i++, numeroPago);
			ps.setInt(i++, vendedor);
			ps.setInt(i++, caja);
			ps.setDouble(i++, importeTicket);
			ps.setDouble(i++, importeCobro);
			ps.setString(i++, Cadena.left(previsionCobro, 25));
			ps.setInt(i++, fechaCobro);
			ps.setInt(i++, formaCobro);
			ps.setString(i++, Cadena.left(usuario, 8));
			ps.setInt(i++, dayTime);
			ps.setInt(i++, representante);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Cobro!!!");
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

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(int numeroPago) {
		this.numeroPago = numeroPago;
	}

	public int getVendedor() {
		return vendedor;
	}

	public void setVendedor(int vendedor) {
		this.vendedor = vendedor;
	}

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}

	public double getImporteTicket() {
		return importeTicket;
	}

	public void setImporteTicket(double importeTicket) {
		this.importeTicket = importeTicket;
	}

	public double getImporteCobro() {
		return importeCobro;
	}

	public void setImporteCobro(double importeCobro) {
		this.importeCobro = importeCobro;
	}

	public String getPrevisionCobro() {
		return previsionCobro;
	}

	public void setPrevisionCobro(String previsionCobro) {
		this.previsionCobro = previsionCobro;
	}

	public int getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(int fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public int getFormaCobro() {
		return formaCobro;
	}

	public void setFormaCobro(int formaCobro) {
		this.formaCobro = formaCobro;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getDayTime() {
		return dayTime;
	}

	public void setDayTime(int dayTime) {
		this.dayTime = dayTime;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
	}
}
