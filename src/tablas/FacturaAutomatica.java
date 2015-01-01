package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class FacturaAutomatica {
	private String empresa;
	private int cliente;
	private int linea;
	private int momentoFacturacion;
	private String faccep;
	private String concepto;
	private int cantidadImporte;
	private double cantidad;
	private double precioImporte;
	private int iva;
	private int recargoEquivalencia;
	private String cuentaVentas;

	public FacturaAutomatica(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		linea = 0;
		momentoFacturacion = 0;
		faccep = "";
		concepto = "";
		cantidadImporte = 0;
		cantidad = 0.0;
		precioImporte = 0.0;
		iva = 0;
		recargoEquivalencia = 0;
		cuentaVentas = "";
	}

	public FacturaAutomatica(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("FACAUT_CLIENTE");
				linea = rs.getInt("FACAUT_LINEA");
				momentoFacturacion = rs.getInt("FACAUT_MOMENTO_FACT");
				faccep = rs.getString("FACAUT_FACCEP").trim();
				concepto = rs.getString("FACAUT_CONCEPTO").trim();
				cantidadImporte = rs.getInt("FACAUT_CANIMPORC");
				cantidad = rs.getDouble("FACAUT_CANTIDAD");
				precioImporte = rs.getDouble("FACAUT_PREIMPORC");
				iva = rs.getInt("FACAUT_IVA");
				recargoEquivalencia = rs.getInt("FACAUT_REQU");
				cuentaVentas = rs.getString("FACAUT_CTA_VTAS").trim();	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FacturaAutomatica!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			cliente = rs.getInt("FACAUT_CLIENTE");
			linea = rs.getInt("FACAUT_LINEA");
			momentoFacturacion = rs.getInt("FACAUT_MOMENTO_FACT");
			faccep = rs.getString("FACAUT_FACCEP").trim();
			concepto = rs.getString("FACAUT_CONCEPTO").trim();
			cantidadImporte = rs.getInt("FACAUT_CANIMPORC");
			cantidad = rs.getDouble("FACAUT_CANTIDAD");
			precioImporte = rs.getDouble("FACAUT_PREIMPORC");
			iva = rs.getInt("FACAUT_IVA");
			recargoEquivalencia = rs.getInt("FACAUT_REQU");
			cuentaVentas = rs.getString("FACAUT_CTA_VTAS").trim();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FacturaAutomatica!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO FACAUT " +
						   "(EMPRESA, " +
						   "FACAUT_CLIENTE, " +
						   "FACAUT_LINEA, " +
						   "FACAUT_MOMENTO_FACT, " +
						   "FACAUT_FACCEP, " +
						   "FACAUT_CONCEPTO, " +
						   "FACAUT_CANIMPORC, " +
						   "FACAUT_CANTIDAD, " +
						   "FACAUT_PREIMPORC, " +
						   "FACAUT_IVA, " +
						   "FACAUT_REQU, " +
						   "FACAUT_CTA_VTAS) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "FACAUT_CLIENTE = ?, " +
						   "FACAUT_LINEA = ?, " +
						   "FACAUT_MOMENTO_FACT = ?, " +
						   "FACAUT_FACCEP = ?, " +
						   "FACAUT_CONCEPTO = ?, " +
						   "FACAUT_CANIMPORC = ?, " +
						   "FACAUT_CANTIDAD = ?, " +
						   "FACAUT_PREIMPORC = ?, " +
						   "FACAUT_IVA = ?, " +
						   "FACAUT_REQU = ?, " +
						   "FACAUT_CTA_VTAS = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, linea);
			ps.setInt(i++, momentoFacturacion);
			ps.setString(i++, Cadena.left(faccep, 12));
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setInt(i++, cantidadImporte);
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precioImporte);
			ps.setInt(i++, iva);
			ps.setInt(i++, recargoEquivalencia);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, linea);
			ps.setInt(i++, momentoFacturacion);
			ps.setString(i++, Cadena.left(faccep, 12));
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setInt(i++, cantidadImporte);
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precioImporte);
			ps.setInt(i++, iva);
			ps.setInt(i++, recargoEquivalencia);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de FacturaAutomatica!!!");
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

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public int getMomentoFacturacion() {
		return momentoFacturacion;
	}

	public void setMomentoFacturacion(int momentoFacturacion) {
		this.momentoFacturacion = momentoFacturacion;
	}

	public String getFaccep() {
		return faccep;
	}

	public void setFaccep(String faccep) {
		this.faccep = faccep;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public int getCantidadImporte() {
		return cantidadImporte;
	}

	public void setCantidadImporte(int cantidadImporte) {
		this.cantidadImporte = cantidadImporte;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
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
}
