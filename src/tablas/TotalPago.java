package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class TotalPago {
	private String empresa;
	private int factura;
	private int fecha;
	private int caja;
	private int tipoPago;
	private int linea;
	private String concepto;
	private double importePago;
	
	public TotalPago(){
		empresa = DatosComunes.eEmpresa;
		factura = 0;
		fecha = 0;
		caja = 0;
		tipoPago = 0;
		linea = 0;
		concepto = "";
		importePago = 0.0;
	}
	
	public TotalPago(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				factura = rs.getInt("TOTPAG_FACTURA");
				fecha = rs.getInt("TOTPAG_FECHA");
				caja = rs.getInt("TOTPAG_CAJA");
				tipoPago = rs.getInt("TOTPAG_TIPAGO");
				linea = rs.getInt("TOTPAG_LINEA");
				concepto = rs.getString("TOTPAG_CONCEPTO").trim();
				importePago = rs.getDouble("TOTPAG_IMPORTE_PAGO");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de TotalPago!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void read(ResultSet rs){
		try{				
				empresa = rs.getString("EMPRESA").trim();
				factura = rs.getInt("TOTPAG_FACTURA");
				fecha = rs.getInt("TOTPAG_FECHA");
				caja = rs.getInt("TOTPAG_CAJA");
				tipoPago = rs.getInt("TOTPAG_TIPAGO");
				linea = rs.getInt("TOTPAG_LINEA");
				concepto = rs.getString("TOTPAG_CONCEPTO").trim();
				importePago = rs.getDouble("TOTPAG_IMPORTE_PAGO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de TotalPago!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO TOTPAG " +
						   "(EMPRESA, " +
						   "TOTPAG_FACTURA, " +
						   "TOTPAG_FECHA, " +
						   "TOTPAG_CAJA, " +
						   "TOTPAG_TIPAGO, " +
						   "TOTPAG_LINEA, " +
						   "TOTPAG_CONCEPTO, " +
						   "TOTPAG_IMPORTE_PAGO) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "TOTPAG_FACTURA = ?, " +
						   "TOTPAG_FECHA = ?, " +
						   "TOTPAG_CAJA = ?, " +
						   "TOTPAG_TIPAGO = ?, " +
						   "TOTPAG_LINEA = ?, " +
						   "TOTPAG_CONCEPTO = ?, " +
						   "TOTPAG_IMPORTE_PAGO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, factura);
			ps.setInt(i++, fecha);
			ps.setInt(i++, caja);
			ps.setInt(i++, tipoPago);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setDouble(i++, importePago);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, factura);
			ps.setInt(i++, fecha);
			ps.setInt(i++, caja);
			ps.setInt(i++, tipoPago);
			ps.setInt(i++, linea);
			ps.setString(i++, Cadena.left(concepto, 30));
			ps.setDouble(i++, importePago);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de TotalPago!!!");
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

	public int getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
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

	public double getImportePago() {
		return importePago;
	}

	public void setImportePago(double importePago) {
		this.importePago = importePago;
	}
}
