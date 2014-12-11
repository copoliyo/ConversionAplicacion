package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;



public class FacturaEmitida {
	private String empresa;
	private int cliente;
	private int año;
	private String serie;
	private int factura;
	private int centro;
	private int fecha;
	private long fechaAsientoApunte;
	private String nombreCliente;
	private String nif;
	private double baseIva[];
	private double baseRecargoEquivalencia[];
	private double iva;
	private double recargoEquivalencia;
	private double total;
	
	public FacturaEmitida(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		año = 0;
		serie = "";
		factura = 0;
		centro = 0;
		fecha = 0;
		fechaAsientoApunte = 0L;
		nombreCliente = "";
		nif = "";
		
		baseIva = new double[3];
		baseRecargoEquivalencia = new double[3];
		for(int i = 0; i < 3; i++){
			baseIva[i] = 0.0;
			baseRecargoEquivalencia[i] = 0.0;
		}
					
		iva = 0.0;
		recargoEquivalencia = 0.0;
		total = 0.0;
	}
	
	public FacturaEmitida(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("FACEMI_CLIENTE");
				año = rs.getInt("FACEMI_ANY");
				serie = rs.getString("FACEMI_SERIE").trim();
				factura = rs.getInt("FACEMI_FACTURA");
				centro = rs.getInt("FACEMI_CENTRO");
				fecha = rs.getInt("FACEMI_FECHA");
				fechaAsientoApunte = rs.getLong("FACEMI_FECH_ASTO_APT");
				nombreCliente = rs.getString("FACEMI_NOMBRE_CLI").trim();
				nif = rs.getString("FACEMI_NIF").trim();	
				baseIva = new double[3];
				baseIva[0] = rs.getDouble("FACEMI_BASES_IVA_1");
				baseIva[1] = rs.getDouble("FACEMI_BASES_IVA_2");
				baseIva[2] = rs.getDouble("FACEMI_BASES_IVA_3");
				baseRecargoEquivalencia = new double[3];
				baseRecargoEquivalencia[0] = rs.getDouble("FACEMI_BASES_RE_1");
				baseRecargoEquivalencia[1] = rs.getDouble("FACEMI_BASES_RE_2");
				baseRecargoEquivalencia[2] = rs.getDouble("FACEMI_BASES_RE_3");											
				iva = rs.getDouble("FACEMI_IVA");
				recargoEquivalencia = rs.getDouble("FACEMI_RECEQ");
				total = rs.getDouble("FACEMI_TOTAL");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de FacturaEmitida!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void read(ResultSet rs){
		try{				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("FACEMI_CLIENTE");
				año = rs.getInt("FACEMI_ANY");
				serie = rs.getString("FACEMI_SERIE").trim();
				factura = rs.getInt("FACEMI_FACTURA");
				centro = rs.getInt("FACEMI_CENTRO");
				fecha = rs.getInt("FACEMI_FECHA");
				fechaAsientoApunte = rs.getLong("FACEMI_FECH_ASTO_APT");
				nombreCliente = rs.getString("FACEMI_NOMBRE_CLI").trim();
				nif = rs.getString("FACEMI_NIF").trim();	
				baseIva[0] = rs.getDouble("FACEMI_BASES_IVA_1");
				baseIva[1] = rs.getDouble("FACEMI_BASES_IVA_2");
				baseIva[2] = rs.getDouble("FACEMI_BASES_IVA_3");
				baseRecargoEquivalencia[0] = rs.getDouble("FACEMI_BASES_RE_1");
				baseRecargoEquivalencia[1] = rs.getDouble("FACEMI_BASES_RE_2");
				baseRecargoEquivalencia[2] = rs.getDouble("FACEMI_BASES_RE_3");											
				iva = rs.getDouble("FACEMI_IVA");
				recargoEquivalencia = rs.getDouble("FACEMI_RECEQ");
				total = rs.getDouble("FACEMI_TOTAL");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de FacturaEmitida!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO FACEMI " +
						   "(EMPRESA, " +
						   "FACEMI_CLIENTE, " +
						   "FACEMI_ANY, " +
						   "FACEMI_SERIE, " +
						   "FACEMI_FACTURA, " +
						   "FACEMI_CENTRO, " +
						   "FACEMI_FECHA, " +
						   "FACEMI_FECH_ASTO_APT, " +
						   "FACEMI_NOMBRE_CLI, " +
						   "FACEMI_NIF, " +
						   "FACEMI_BASES_IVA_1, " +
						   "FACEMI_BASES_IVA_2, " +
						   "FACEMI_BASES_IVA_3, " +
						   "FACEMI_BASES_RE_1, " +
						   "FACEMI_BASES_RE_2, " +
						   "FACEMI_BASES_RE_3, " +
						   "FACEMI_IVA, " +
						   "FACEMI_RECEQ, " +
						   "FACEMI_TOTAL) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "FACEMI_CLIENTE = ?, " +
						   "FACEMI_ANY = ?, " +
						   "FACEMI_SERIE = ?, " +
						   "FACEMI_FACTURA = ?, " +
						   "FACEMI_CENTRO = ?, " +
						   "FACEMI_FECHA = ?, " +
						   "FACEMI_FECH_ASTO_APT = ?, " +
						   "FACEMI_NOMBRE_CLI = ?, " +
						   "FACEMI_NIF = ?, " +
						   "FACEMI_BASES_IVA_1 = ?, " +
						   "FACEMI_BASES_IVA_2 = ?, " +
						   "FACEMI_BASES_IVA_3 = ?, " +
						   "FACEMI_BASES_RE_1 = ?, " +
						   "FACEMI_BASES_RE_2 = ?, " +
						   "FACEMI_BASES_RE_3 = ?, " +
						   "FACEMI_IVA = ?, " +
						   "FACEMI_RECEQ = ?, " +
						   "FACEMI_TOTAL = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, año);
			ps.setString(i++, Cadena.left(serie, 2));
			ps.setInt(i++, factura);
			ps.setInt(i++, centro);
			ps.setInt(i++, fecha);
			ps.setLong(i++, fechaAsientoApunte);
			ps.setString(i++, Cadena.left(nombreCliente, 30));
			ps.setString(i++, Cadena.left(nif, 16));
			for(int j = 0; j < 3; j++)
				ps.setDouble(i++, baseIva[j]);
			for(int j = 0; j < 3; j++)
				ps.setDouble(i++, baseRecargoEquivalencia[j]);
			ps.setDouble(i++, iva);
			ps.setDouble(i++, recargoEquivalencia);
			ps.setDouble(i++, total);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, año);
			ps.setString(i++, Cadena.left(serie, 2));
			ps.setInt(i++, factura);
			ps.setInt(i++, centro);
			ps.setInt(i++, fecha);
			ps.setLong(i++, fechaAsientoApunte);
			ps.setString(i++, Cadena.left(nombreCliente, 30));
			ps.setString(i++, Cadena.left(nif, 16));
			for(int j = 0; j < 3; j++)
				ps.setDouble(i++, baseIva[j]);
			for(int j = 0; j < 3; j++)
				ps.setDouble(i++, baseRecargoEquivalencia[j]);
			ps.setDouble(i++, iva);
			ps.setDouble(i++, recargoEquivalencia);
			ps.setDouble(i++, total);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de FacturaEmitida!!!");
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

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public long getFechaAsientoApunte() {
		return fechaAsientoApunte;
	}

	public void setFechaAsientoApunte(long fechaAsientoApunte) {
		this.fechaAsientoApunte = fechaAsientoApunte;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public double getBaseIva(int i) {
		return baseIva[i];
	}
	
	public double[] getBaseIva() {
		return baseIva;
	}

	public void setBaseIva(int indice, double valor) {
		this.baseIva[indice] = valor;
	}
	
	public void setBaseIva(double[] baseIva) {
		this.baseIva = baseIva;
	}

	public double getBaseRecargoEquivalencia(int i) {
		return baseRecargoEquivalencia[i];
	}

	public double[] getBaseRecargoEquivalencia() {
		return baseRecargoEquivalencia;
	}

	public void setBaseRecargoEquivalencia(int indice, double valor) {
		this.baseRecargoEquivalencia[indice] = valor;
	}

	public void setBaseRecargoEquivalencia(double[] baseRecargoEquivalencia) {
		this.baseRecargoEquivalencia = baseRecargoEquivalencia;
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
