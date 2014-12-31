package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class Ticket {
	private String empresa;
	private int cliente;
	private int factura;
	private int fechaFactura;
	private int fecha;
	private int centro;
	private int año;
	private int ticket;
	private int linea;
	private int vendedor;
	private int caja;
	private String articuloConcepto;
	private String concepto;
	private int iva;
	private String cuentaVentas;
	private double cantidad;
	private double precio;
	private int descuento1;
	private int descuento2;
	private double importe;
	private String usuario;
	private int dayTime;
	private String relleno;
	private int facturaACliente;
	private int tipoLinea;
	private int representante;
	private int seReparte;
	private int pedidoAAlmacen;

	public Ticket(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		factura = 0;
		fechaFactura = 0;
		fecha = 0;
		centro = 0;
		año = 0;
		ticket = 0;
		linea = 0;
		vendedor = 0;
		caja = 0;
		articuloConcepto = "";
		concepto = "";
		iva = 0;
		cuentaVentas = "";
		cantidad = 0.0;
		precio = 0.0;
		descuento1 = 0;
		descuento2 = 0;
		importe = 0.0;
		usuario = "";
		dayTime = 0;
		relleno = "";
		facturaACliente = 0;
		tipoLinea = 0;
		representante = 0;
		seReparte = 0;
		pedidoAAlmacen = 0;
	}

	public Ticket(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("TICKET_CLIENTE");
				factura = rs.getInt("TICKET_FACTURA");
				fechaFactura = rs.getInt("TICKET_FECHA_FRA");
				fecha = rs.getInt("TICKET_FECHA");
				centro = rs.getInt("TICKET_CENTRO");
				año = rs.getInt("TICKET_ANY");
				ticket = rs.getInt("TICKET_TICKET");
				linea = rs.getInt("TICKET_LINEA");
				vendedor = rs.getInt("TICKET_VENDEDOR");
				caja = rs.getInt("TICKET_CAJA");
				articuloConcepto = rs.getString("TICKET_ARTFCP").trim();
				concepto = rs.getString("TICKET_CONCEPTO").trim();
				iva = rs.getInt("TICKET_IVA");
				cuentaVentas = rs.getString("TICKET_CTA_VTAS").trim();
				cantidad = rs.getDouble("TICKET_CANTIDAD");
				precio = rs.getDouble("TICKET_PRECIO");
				descuento1 = rs.getInt("TICKET_POR_DTO1");
				descuento2 = rs.getInt("TICKET_POR_DTO2");
				importe = rs.getDouble("TICKET_IMPORTE");
				usuario = rs.getString("TICKET_USUARIO").trim();
				dayTime = rs.getInt("TICKET_DAY_TIME");
				relleno = rs.getString("TICKET_RELLENO").trim();
				facturaACliente = rs.getInt("TICKET_FRA_A_CLI");
				tipoLinea = rs.getInt("TICKET_TIPO_LINEA");
				representante = rs.getInt("TICKET_REPRES");
				seReparte = rs.getInt("TICKET_SE_REPARTE");
				pedidoAAlmacen = rs.getInt("TICKET_PEDIDO_A_ALMACEN");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Ticket!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			cliente = rs.getInt("TICKET_CLIENTE");
			factura = rs.getInt("TICKET_FACTURA");
			fechaFactura = rs.getInt("TICKET_FECHA_FRA");
			fecha = rs.getInt("TICKET_FECHA");
			centro = rs.getInt("TICKET_CENTRO");
			año = rs.getInt("TICKET_ANY");
			ticket = rs.getInt("TICKET_TICKET");
			linea = rs.getInt("TICKET_LINEA");
			vendedor = rs.getInt("TICKET_VENDEDOR");
			caja = rs.getInt("TICKET_CAJA");
			articuloConcepto = rs.getString("TICKET_ARTFCP").trim();
			concepto = rs.getString("TICKET_CONCEPTO").trim();
			iva = rs.getInt("TICKET_IVA");
			cuentaVentas = rs.getString("TICKET_CTA_VTAS").trim();
			cantidad = rs.getDouble("TICKET_CANTIDAD");
			precio = rs.getDouble("TICKET_PRECIO");
			descuento1 = rs.getInt("TICKET_POR_DTO1");
			descuento2 = rs.getInt("TICKET_POR_DTO2");
			importe = rs.getDouble("TICKET_IMPORTE");
			usuario = rs.getString("TICKET_USUARIO").trim();
			dayTime = rs.getInt("TICKET_DAY_TIME");
			relleno = rs.getString("TICKET_RELLENO".trim());
			facturaACliente = rs.getInt("TICKET_FRA_A_CLI");
			tipoLinea = rs.getInt("TICKET_TIPO_LINEA");
			representante = rs.getInt("TICKET_REPRES");
			seReparte = rs.getInt("TICKET_SE_REPARTE");
			pedidoAAlmacen = rs.getInt("TICKET_PEDIDO_A_ALMACEN");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Ticket!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO TICKET " +
						   "(EMPRESA, " +
						   "TICKET_CLIENTE, " +
						   "TICKET_FACTURA, " +
						   "TICKET_FECHA_FRA, " +
						   "TICKET_FECHA, " +
						   "TICKET_CENTRO, " +
						   "TICKET_ANY, " +
						   "TICKET_TICKET, " +
						   "TICKET_LINEA, " +
						   "TICKET_VENDEDOR, " +
						   "TICKET_CAJA, " +
						   "TICKET_ARTFCP, " +
						   "TICKET_CONCEPTO, " +
						   "TICKET_IVA, " +
						   "TICKET_CTA_VTAS, " +
						   "TICKET_CANTIDAD, " +
						   "TICKET_PRECIO, " +
						   "TICKET_POR_DTO1, " +
						   "TICKET_POR_DTO2, " +
						   "TICKET_IMPORTE, " +
						   "TICKET_USUARIO, " +
						   "TICKET_DAY_TIME, " +
						   "TICKET_RELLENO, " +
						   "TICKET_FRA_A_CLI, " +
						   "TICKET_TIPO_LINEA, " +
						   "TICKET_REPRES, " +
						   "TICKET_SE_REPARTE, " +
						   "TICKET_PEDIDO_A_ALMACEN) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "TICKET_CLIENTE = ?, " +
						   "TICKET_FACTURA = ?, " +
						   "TICKET_FECHA_FRA = ?, " +
						   "TICKET_FECHA = ?, " +
						   "TICKET_CENTRO = ?, " +
						   "TICKET_ANY = ?, " +
						   "TICKET_TICKET = ?, " +
						   "TICKET_LINEA = ?, " +
						   "TICKET_VENDEDOR = ?, " +
						   "TICKET_CAJA = ?, " +
						   "TICKET_ARTFCP = ?, " +
						   "TICKET_CONCEPTO = ?, " +
						   "TICKET_IVA = ?, " +
						   "TICKET_CTA_VTAS = ?, " +
						   "TICKET_CANTIDAD = ?, " +
						   "TICKET_PRECIO = ?, " +
						   "TICKET_POR_DTO1 = ?, " +
						   "TICKET_POR_DTO2 = ?, " +
						   "TICKET_IMPORTE = ?, " +
						   "TICKET_USUARIO = ?, " +
						   "TICKET_DAY_TIME = ?, " +
						   "TICKET_RELLENO = ?, " +
						   "TICKET_FRA_A_CLI = ?, " +
						   "TICKET_TIPO_LINEA = ?, " +
						   "TICKET_REPRES = ?, " +
						   "TICKET_SE_REPARTE = ?, " +
						   "TICKET_PEDIDO_A_ALMACEN = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, factura);
			ps.setInt(i++, fechaFactura);
			ps.setInt(i++, fecha);
			ps.setInt(i++, centro);
			ps.setInt(i++, año);
			ps.setInt(i++, ticket);
			ps.setInt(i++, linea);
			ps.setInt(i++, vendedor);
			ps.setInt(i++, caja);
			ps.setString(i++, Cadena.left(articuloConcepto, 13));
			ps.setString(i++, Cadena.left(concepto, 60));
			ps.setInt(i++, iva);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precio);
			ps.setInt(i++, descuento1);
			ps.setInt(i++, descuento2);
			ps.setDouble(i++, importe);
			ps.setString(i++, Cadena.left(usuario, 8));
			ps.setInt(i++, dayTime);
			ps.setString(i++, Cadena.left(relleno, 1));
			ps.setInt(i++, facturaACliente);
			ps.setInt(i++, tipoLinea);
			ps.setInt(i++, representante);
			ps.setInt(i++, seReparte);
			ps.setInt(i++, pedidoAAlmacen);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, factura);
			ps.setInt(i++, fechaFactura);
			ps.setInt(i++, fecha);
			ps.setInt(i++, centro);
			ps.setInt(i++, año);
			ps.setInt(i++, ticket);
			ps.setInt(i++, linea);
			ps.setInt(i++, vendedor);
			ps.setInt(i++, caja);
			ps.setString(i++, Cadena.left(articuloConcepto, 13));
			ps.setString(i++, Cadena.left(concepto, 60));
			ps.setInt(i++, iva);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precio);
			ps.setInt(i++, descuento1);
			ps.setInt(i++, descuento2);
			ps.setDouble(i++, importe);
			ps.setString(i++, Cadena.left(usuario, 8));
			ps.setInt(i++, dayTime);
			ps.setString(i++, Cadena.left(relleno, 1));
			ps.setInt(i++, facturaACliente);
			ps.setInt(i++, tipoLinea);
			ps.setInt(i++, representante);
			ps.setInt(i++, seReparte);
			ps.setInt(i++, pedidoAAlmacen);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Ticket!!!");
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

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public int getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(int fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
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

	public String getArticuloConcepto() {
		return articuloConcepto;
	}

	public void setArticuloConcepto(String articuloConcepto) {
		this.articuloConcepto = articuloConcepto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public String getCuentaVentas() {
		return cuentaVentas;
	}

	public void setCuentaVentas(String cuentaVentas) {
		this.cuentaVentas = cuentaVentas;
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

	public int getDescuento1() {
		return descuento1;
	}

	public void setDescuento1(int descuento1) {
		this.descuento1 = descuento1;
	}

	public int getDescuento2() {
		return descuento2;
	}

	public void setDescuento2(int descuento2) {
		this.descuento2 = descuento2;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
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

	public String getRelleno() {
		return relleno;
	}

	public void setRelleno(String relleno) {
		this.relleno = relleno;
	}

	public int getFacturaACliente() {
		return facturaACliente;
	}

	public void setFacturaACliente(int facturaACliente) {
		this.facturaACliente = facturaACliente;
	}

	public int getTipoLinea() {
		return tipoLinea;
	}

	public void setTipoLinea(int tipoLinea) {
		this.tipoLinea = tipoLinea;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
	}

	public int getSeReparte() {
		return seReparte;
	}

	public void setSeReparte(int seReparte) {
		this.seReparte = seReparte;
	}

	public int getPedidoAAlmacen() {
		return pedidoAAlmacen;
	}

	public void setPedidoAAlmacen(int pedidoAAlmacen) {
		this.pedidoAAlmacen = pedidoAAlmacen;
	}
}
