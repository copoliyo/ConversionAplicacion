package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class AlbaranProveedor {
	private String empresa;
	private int proAcreedor;
	private int albaran;
	private int linea;
	private int centro;
	private int pedido;
	private int fecha;
	private String articulo;
	private String concepto;
	private double cantidad;
	private double precio;
	private int porcentajeDescuento;
	private double importe;
	private String observaciones;
	private int numeroEfectos;
	private int intervalo;
	private int primerVencimiento;
	private int seNegocia;
	private int diaPago1;
	private int diaPago2;
	private int diaPago3;
	private int origenDiferencia;
	
	public AlbaranProveedor(){
		empresa = DatosComunes.eEmpresa;
		proAcreedor = 0;
		albaran = 0;
		linea = 0;
		centro = DatosComunes.centroGest;
		pedido = 0;
		fecha = 0;
		articulo = "";
		concepto = "";
		cantidad = 0.0;
		precio = 0.0;
		porcentajeDescuento = 0;
		importe = 0.0;
		observaciones = "";
		numeroEfectos = 0;
		intervalo = 0;
		primerVencimiento = 0;
		seNegocia = 0;
		diaPago1 = 0;
		diaPago2 = 0;
		diaPago3 = 0;
		origenDiferencia = 0;
	}
	
	public AlbaranProveedor(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				proAcreedor = rs.getInt("ALBPRV_PROVAC");
				albaran = rs.getInt("ALBPRV_ALBARAN");
				linea = rs.getInt("ALBPRV_LINEA");
				centro = rs.getInt("ALBPRV_CENTRO");
				pedido = rs.getInt("ALBPRV_PEDIDO");
				fecha = rs.getInt("ALBPRV_FECHA");
				articulo = rs.getString("ALBPRV_ARTICULO").trim();
				concepto = rs.getString("ALBPRV_CONCEPTO").trim();
				cantidad = rs.getDouble("ALBPRV_CANTIDAD");
				precio = rs.getDouble("ALBPRV_PRECIO");
				porcentajeDescuento = rs.getInt("ALBPRV_POR_DTO");
				importe = rs.getDouble("ALBPRV_IMPORTE");
				observaciones = rs.getString("ALBPRV_OBSERVAC").trim();
				numeroEfectos = rs.getInt("ALBPRV_NRO_EFECTOS");
				intervalo = rs.getInt("ALBPRV_INTERVALO");
				primerVencimiento = rs.getInt("ALBPRV_PRIMER_VTO");
				seNegocia = rs.getInt("ALBPRV_SE_NEGOCIA");
				diaPago1 = rs.getInt("ALBPRV_DIA_PAGO1");
				diaPago2 = rs.getInt("ALBPRV_DIA_PAGO2");
				diaPago3 = rs.getInt("ALBPRV_DIA_PAGO3");
				origenDiferencia = rs.getInt("ALBPRV_ORIGEN_DIFERENCIA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de AlbaranProveedor!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
		
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			proAcreedor = rs.getInt("ALBPRV_PROVAC");
			albaran = rs.getInt("ALBPRV_ALBARAN");
			linea = rs.getInt("ALBPRV_LINEA");
			centro = rs.getInt("ALBPRV_CENTRO");
			pedido = rs.getInt("ALBPRV_PEDIDO");
			fecha = rs.getInt("ALBPRV_FECHA");
			articulo = rs.getString("ALBPRV_ARTICULO").trim();
			concepto = rs.getString("ALBPRV_CONCEPTO").trim();
			cantidad = rs.getDouble("ALBPRV_CANTIDAD");
			precio = rs.getDouble("ALBPRV_PRECIO");
			porcentajeDescuento = rs.getInt("ALBPRV_POR_DTO");
			importe = rs.getDouble("ALBPRV_IMPORTE");
			observaciones = rs.getString("ALBPRV_OBSERVAC").trim();
			numeroEfectos = rs.getInt("ALBPRV_NRO_EFECTOS");
			intervalo = rs.getInt("ALBPRV_INTERVALO");
			primerVencimiento = rs.getInt("ALBPRV_PRIMER_VTO");
			seNegocia = rs.getInt("ALBPRV_SE_NEGOCIA");
			diaPago1 = rs.getInt("ALBPRV_DIA_PAGO1");
			diaPago2 = rs.getInt("ALBPRV_DIA_PAGO2");
			diaPago3 = rs.getInt("ALBPRV_DIA_PAGO3");
			origenDiferencia = rs.getInt("ALBPRV_ORIGEN_DIFERENCIA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de AlbaranProveedor!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
		
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ALBPRV " +
						   "(EMPRESA, " +
						   "ALBPRV_PROVAC, " +
						   "ALBPRV_ALBARAN, " +
						   "ALBPRV_LINEA, " +
						   "ALBPRV_CENTRO, " +
						   "ALBPRV_PEDIDO, " +
						   "ALBPRV_FECHA, " +
						   "ALBPRV_ARTICULO, " +
						   "ALBPRV_CONCEPTO, " +
						   "ALBPRV_CANTIDAD, " +
						   "ALBPRV_PRECIO, " +
						   "ALBPRV_POR_DTO, " +
						   "ALBPRV_IMPORTE, " +
						   "ALBPRV_OBSERVAC, " +
						   "ALBPRV_NRO_EFECTOS, " +
						   "ALBPRV_INTERVALO, " +
						   "ALBPRV_PRIMER_VTO, " +
						   "ALBPRV_SE_NEGOCIA, " +
						   "ALBPRV_DIA_PAGO1, " +
						   "ALBPRV_DIA_PAGO2, " +
						   "ALBPRV_DIA_PAGO3, " +
						   "ALBPRV_ORIGEN_DIFERENCIA) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
						   "ALBPRV_PROVAC = ?, " +
						   "ALBPRV_ALBARAN = ?, " +
						   "ALBPRV_LINEA = ?, " +
						   "ALBPRV_CENTRO = ?, " +
						   "ALBPRV_PEDIDO = ?, " +
						   "ALBPRV_FECHA = ?, " +
						   "ALBPRV_ARTICULO = ?, " +
						   "ALBPRV_CONCEPTO = ?, " +
						   "ALBPRV_CANTIDAD = ?, " +
						   "ALBPRV_PRECIO = ?, " +
						   "ALBPRV_POR_DTO = ?, " +
						   "ALBPRV_IMPORTE = ?, " +
						   "ALBPRV_OBSERVAC = ?, " +
						   "ALBPRV_NRO_EFECTOS = ?, " +
						   "ALBPRV_INTERVALO = ?, " +
						   "ALBPRV_PRIMER_VTO = ?, " +
						   "ALBPRV_SE_NEGOCIA = ?, " +
						   "ALBPRV_DIA_PAGO1 = ?, " +
						   "ALBPRV_DIA_PAGO2 = ?, " +
						   "ALBPRV_DIA_PAGO3 = ?, " +
						   "ALBPRV_ORIGEN_DIFERENCIA = ?";						   
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setInt(2, proAcreedor);
			ps.setInt(3, albaran);
			ps.setInt(4, linea);
			ps.setInt(5, centro);
			ps.setInt(6, pedido);
			ps.setInt(7, fecha);
			ps.setString(8, Cadena.left(articulo, 13));
			ps.setString(9, Cadena.left(concepto, 30));
			ps.setDouble(10, cantidad);
			ps.setDouble(11, precio);
			ps.setInt(12, porcentajeDescuento);
			ps.setDouble(13, importe);
			ps.setString(14, Cadena.left(observaciones, 50));
			ps.setInt(15, numeroEfectos);
			ps.setInt(16, intervalo);
			ps.setInt(17, primerVencimiento);
			ps.setInt(18, seNegocia);
			ps.setInt(19, diaPago1);
			ps.setInt(20, diaPago2);
			ps.setInt(21, diaPago3);
			ps.setInt(22, origenDiferencia);
			// Update
			ps.setString(23, Cadena.left(empresa, 2));
			ps.setInt(24, proAcreedor);
			ps.setInt(25, albaran);
			ps.setInt(26, linea);
			ps.setInt(27, centro);
			ps.setInt(28, pedido);
			ps.setInt(29, fecha);
			ps.setString(30, Cadena.left(articulo, 13));
			ps.setString(31, Cadena.left(concepto, 30));
			ps.setDouble(32, cantidad);
			ps.setDouble(33, precio);
			ps.setInt(34, porcentajeDescuento);
			ps.setDouble(35, importe);
			ps.setString(36, Cadena.left(observaciones, 50));
			ps.setInt(37, numeroEfectos);
			ps.setInt(38, intervalo);
			ps.setInt(39, primerVencimiento);
			ps.setInt(40, seNegocia);
			ps.setInt(41, diaPago1);
			ps.setInt(42, diaPago2);
			ps.setInt(43, diaPago3);
			ps.setInt(44, origenDiferencia);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de AlbaranProveedor!!!");
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

	public int getProAcreedor() {
		return proAcreedor;
	}

	public void setProAcreedor(int proAcreedor) {
		this.proAcreedor = proAcreedor;
	}

	public int getAlbaran() {
		return albaran;
	}

	public void setAlbaran(int albaran) {
		this.albaran = albaran;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getNumeroEfectos() {
		return numeroEfectos;
	}

	public void setNumeroEfectos(int numeroEfectos) {
		this.numeroEfectos = numeroEfectos;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public int getPrimerVencimiento() {
		return primerVencimiento;
	}

	public void setPrimerVencimiento(int primerVencimiento) {
		this.primerVencimiento = primerVencimiento;
	}

	public int getSeNegocia() {
		return seNegocia;
	}

	public void setSeNegocia(int seNegocia) {
		this.seNegocia = seNegocia;
	}

	public int getDiaPago1() {
		return diaPago1;
	}

	public void setDiaPago1(int diaPago1) {
		this.diaPago1 = diaPago1;
	}

	public int getDiaPago2() {
		return diaPago2;
	}

	public void setDiaPago2(int diaPago2) {
		this.diaPago2 = diaPago2;
	}

	public int getDiaPago3() {
		return diaPago3;
	}

	public void setDiaPago3(int diaPago3) {
		this.diaPago3 = diaPago3;
	}

	public int getOrigenDiferencia() {
		return origenDiferencia;
	}

	public void setOrigenDiferencia(int origenDiferencia) {
		this.origenDiferencia = origenDiferencia;
	}

}
