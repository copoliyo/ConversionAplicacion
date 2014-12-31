package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class TotalCobro {
	private String empresa;
	private int factura;
	private String aB;
	private int fecha;
	private int caja;
	private int cliente;
	private int formaCobro;
	private int linea;
	private String concepto;
	private double importeCobros;

	public TotalCobro(){
		empresa = DatosComunes.eEmpresa;
		factura = 0;
		aB = "A";
		fecha = 0;
		caja = 0;
		cliente = 0;
		formaCobro = 0;
		linea = 0;
		concepto = "";
		importeCobros = 0.0;
	}

	public TotalCobro(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				factura = rs.getInt("TOTCOB_FACTURA");
				aB = rs.getString("TOTCOB_AB").trim();
				fecha = rs.getInt("TOTCOB_FECHA");
				caja = rs.getInt("TOTCOB_CAJA");
				cliente = rs.getInt("TOTCOB_CLIENTE");
				formaCobro = rs.getInt("TOTCOB_FORCOB");
				linea = rs.getInt("TOTCOB_LINEA");
				concepto = rs.getString("TOTCOB_CONCEPTO").trim();
				importeCobros = rs.getDouble("TOTCOB_IMPORTE_COBROS");	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de TotalCobro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			factura = rs.getInt("TOTCOB_FACTURA");
			aB = rs.getString("TOTCOB_AB").trim();
			fecha = rs.getInt("TOTCOB_FECHA");
			caja = rs.getInt("TOTCOB_CAJA");
			cliente = rs.getInt("TOTCOB_CLIENTE");
			formaCobro = rs.getInt("TOTCOB_FORCOB");
			linea = rs.getInt("TOTCOB_LINEA");
			concepto = rs.getString("TOTCOB_CONCEPTO").trim();
			importeCobros = rs.getDouble("TOTCOB_IMPORTE_COBROS");	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de TotalCobro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO TOTCOB " +
						   "(EMPRESA, " +
						   "TOTCOB_FACTURA, " +
						   "TOTCOB_AB, " +
						   "TOTCOB_FECHA, " +
						   "TOTCOB_CAJA, " +
						   "TOTCOB_CLIENTE, " +
						   "TOTCOB_FORCOB, " +
						   "TOTCOB_LINEA, " +
						   "TOTCOB_CONCEPTO, " +
						   "TOTCOB_IMPORTE_COBROS) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "TOTCOB_FACTURA = ?, " +
						   "TOTCOB_AB = ?, " +
						   "TOTCOB_FECHA = ?, " +
						   "TOTCOB_CAJA = ?, " +
						   "TOTCOB_CLIENTE = ?, " +
						   "TOTCOB_FORCOB = ?, " +
						   "TOTCOB_LINEA = ?, " +
						   "TOTCOB_CONCEPTO = ?, " +
						   "TOTCOB_IMPORTE_COBROS = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, factura);
			ps.setString(i++, Cadena.left(aB, 1));
			ps.setInt(i++, fecha);
			ps.setInt(i++, caja);
			ps.setInt(i++, cliente);
			ps.setInt(i++, formaCobro);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setDouble(i++, importeCobros);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, factura);
			ps.setString(i++, Cadena.left(aB, 1));
			ps.setInt(i++, fecha);
			ps.setInt(i++, caja);
			ps.setInt(i++, cliente);
			ps.setInt(i++, formaCobro);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setDouble(i++, importeCobros);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de TotalCobro!!!");
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

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public String getaB() {
		return aB;
	}

	public void setaB(String aB) {
		this.aB = aB;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public int getFormaCobro() {
		return formaCobro;
	}

	public void setFormaCobro(int formaCobro) {
		this.formaCobro = formaCobro;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getImporteCobros() {
		return importeCobros;
	}

	public void setImporteCobros(double importeCobros) {
		this.importeCobros = importeCobros;
	}
}
