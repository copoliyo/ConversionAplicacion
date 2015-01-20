package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class MovimientoArticulo {
	private String empresa;
	private String marca;
	private int gama;
	private int familia;
	private String articulo;
	private int cuenta;
	private int fecha;
	private int apunte;
	private int enviado;
	private int fechaProveedor;
	private int apunteProveedor;
	private int centro;
	private int representante;
	private int tipoArticulo;
	private int clave;
	private int documento;
	private double cantidad;
	private double stock;
	private double precio;
	private double stockCentro;
	private double precioCentro;
	private double precioVenta;
	private int descuento;
	private int repartidor;
	private int usuarioPedido;
	private int factura;
	private String tty;
	private int usuario;
	private int dayTime;
	private int documentoContable;
	private int acumulaVentasDia;

	public MovimientoArticulo(){
		empresa = DatosComunes.eEmpresa;
		marca = "";
		gama = 0;
		familia = 0;
		articulo = "";
		cuenta = 0;
		fecha = 0;
		apunte = 0;
		enviado = 0;
		fechaProveedor = 0;
		apunteProveedor = 0;
		centro = 0;
		representante = 0;
		tipoArticulo = 0;
		clave = 0;
		documento = 0;
		cantidad = 0.0;
		stock = 0.0;
		precio = 0.0;
		stockCentro = 0.0;
		precioCentro = 0.0;
		precioVenta = 0.0;
		descuento = 0;
		repartidor = 0;
		usuarioPedido = 0;
		factura = 0;
		tty = "";
		usuario = 0;
		dayTime = 0;
		documentoContable = 0;
		acumulaVentasDia = 0;
	}

	public MovimientoArticulo(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				marca = rs.getString("MOVART_MARCA").trim();
				gama = rs.getInt("MOVART_GAMA");
				familia = rs.getInt("MOVART_FAMILIA");
				articulo = rs.getString("MOVART_ARTICULO").trim();
				cuenta = rs.getInt("MOVART_CUENTA");
				fecha = rs.getInt("MOVART_FECHA");
				apunte = rs.getInt("MOVART_APUNTE");
				enviado = rs.getInt("MOVART_ENVIADO");
				fechaProveedor = rs.getInt("MOVART_FECHA_PROV");
				apunteProveedor = rs.getInt("MOVART_APUNTE_PROV");
				centro = rs.getInt("MOVART_CENTRO");
				representante = rs.getInt("MOVART_REPRE");
				tipoArticulo = rs.getInt("MOVART_TIPO_ARTICULO");
				clave = rs.getInt("MOVART_CLAVE");
				documento = rs.getInt("MOVART_DOCUMENTO");
				cantidad = rs.getDouble("MOVART_CANTIDAD");
				stock = rs.getDouble("MOVART_STOCK");
				precio = rs.getDouble("MOVART_PRECIO");
				stockCentro = rs.getDouble("MOVART_STOCK_CENTRO");
				precioCentro = rs.getDouble("MOVART_PRECIO_CENTRO");
				precioVenta = rs.getDouble("MOVART_PRECIO_VENTA");
				descuento = rs.getInt("MOVART_DTO");
				repartidor = rs.getInt("MOVART_REPARTIDOR");
				usuarioPedido = rs.getInt("MOVART_USU_PEDIDO");
				factura = rs.getInt("MOVART_FACTURA");
				tty = rs.getString("MOVART_TTY").trim();
				usuario = rs.getInt("MOVART_USUARIO");
				dayTime = rs.getInt("MOVART_DAY_TIME");
				documentoContable = rs.getInt("MOVART_DOC_CONTABLE");
				acumulaVentasDia = rs.getInt("MOVART_AC_VTASDIA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de MovimientoArticulo!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			marca = rs.getString("MOVART_MARCA").trim();
			gama = rs.getInt("MOVART_GAMA");
			familia = rs.getInt("MOVART_FAMILIA");
			articulo = rs.getString("MOVART_ARTICULO").trim();
			cuenta = rs.getInt("MOVART_CUENTA");
			fecha = rs.getInt("MOVART_FECHA");
			apunte = rs.getInt("MOVART_APUNTE");
			enviado = rs.getInt("MOVART_ENVIADO");
			fechaProveedor = rs.getInt("MOVART_FECHA_PROV");
			apunteProveedor = rs.getInt("MOVART_APUNTE_PROV");
			centro = rs.getInt("MOVART_CENTRO");
			representante = rs.getInt("MOVART_REPRE");
			tipoArticulo = rs.getInt("MOVART_TIPO_ARTICULO");
			clave = rs.getInt("MOVART_CLAVE");
			documento = rs.getInt("MOVART_DOCUMENTO");
			cantidad = rs.getDouble("MOVART_CANTIDAD");
			stock = rs.getDouble("MOVART_STOCK");
			precio = rs.getDouble("MOVART_PRECIO");
			stockCentro = rs.getDouble("MOVART_STOCK_CENTRO");
			precioCentro = rs.getDouble("MOVART_PRECIO_CENTRO");
			precioVenta = rs.getDouble("MOVART_PRECIO_VENTA");
			descuento = rs.getInt("MOVART_DTO");
			repartidor = rs.getInt("MOVART_REPARTIDOR");
			usuarioPedido = rs.getInt("MOVART_USU_PEDIDO");
			factura = rs.getInt("MOVART_FACTURA");
			tty = rs.getString("MOVART_TTY").trim();
			usuario = rs.getInt("MOVART_USUARIO");
			dayTime = rs.getInt("MOVART_DAY_TIME");
			documentoContable = rs.getInt("MOVART_DOC_CONTABLE");
			acumulaVentasDia = rs.getInt("MOVART_AC_VTASDIA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de MovimientoArticulo!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO MOVART " +
						   "(EMPRESA, " +
						   "MOVART_MARCA, " +
						   "MOVART_GAMA, " +
						   "MOVART_FAMILIA, " +
						   "MOVART_ARTICULO, " +
						   "MOVART_CUENTA, " +
						   "MOVART_FECHA, " +
						   "MOVART_APUNTE, " +
						   "MOVART_ENVIADO, " +
						   "MOVART_FECHA_PROV, " +
						   "MOVART_APUNTE_PROV, " +
						   "MOVART_CENTRO, " +
						   "MOVART_REPRE, " +
						   "MOVART_TIPO_ARTICULO, " +
						   "MOVART_CLAVE, " +
						   "MOVART_DOCUMENTO, " +
						   "MOVART_CANTIDAD, " +
						   "MOVART_STOCK, " +
						   "MOVART_PRECIO, " +
						   "MOVART_STOCK_CENTRO, " +
						   "MOVART_PRECIO_CENTRO, " +
						   "MOVART_PRECIO_VENTA, " +
						   "MOVART_DTO, " +
						   "MOVART_REPARTIDOR, " +
						   "MOVART_USU_PEDIDO, " +
						   "MOVART_FACTURA, " +
						   "MOVART_TTY, " +
						   "MOVART_USUARIO, " +
						   "MOVART_DAY_TIME, " +
						   "MOVART_DOC_CONTABLE, " +
						   "MOVART_AC_VTASDIA) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "MOVART_MARCA = ?, " +
						   "MOVART_GAMA = ?, " +
						   "MOVART_FAMILIA = ?, " +
						   "MOVART_ARTICULO = ?, " +
						   "MOVART_CUENTA = ?, " +
						   "MOVART_FECHA = ?, " +
						   "MOVART_APUNTE = ?, " +
						   "MOVART_ENVIADO = ?, " +
						   "MOVART_FECHA_PROV = ?, " +
						   "MOVART_APUNTE_PROV = ?, " +
						   "MOVART_CENTRO = ?, " +
						   "MOVART_REPRE = ?, " +
						   "MOVART_TIPO_ARTICULO = ?, " +
						   "MOVART_CLAVE = ?, " +
						   "MOVART_DOCUMENTO = ?, " +
						   "MOVART_CANTIDAD = ?, " +
						   "MOVART_STOCK = ?, " +
						   "MOVART_PRECIO = ?, " +
						   "MOVART_STOCK_CENTRO = ?, " +
						   "MOVART_PRECIO_CENTRO = ?, " +
						   "MOVART_PRECIO_VENTA = ?, " +
						   "MOVART_DTO = ?, " +
						   "MOVART_REPARTIDOR = ?, " +
						   "MOVART_USU_PEDIDO = ?, " +
						   "MOVART_FACTURA = ?, " +
						   "MOVART_TTY = ?, " +
						   "MOVART_USUARIO = ?, " +
						   "MOVART_DAY_TIME = ?, " +
						   "MOVART_DOC_CONTABLE = ?, " +
						   "MOVART_AC_VTASDIA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(marca, 3));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setInt(i++, cuenta);
			ps.setInt(i++, fecha);
			ps.setInt(i++, apunte);
			ps.setInt(i++, enviado);
			ps.setInt(i++, fechaProveedor);
			ps.setInt(i++, apunteProveedor);
			ps.setInt(i++, centro);
			ps.setInt(i++, representante);
			ps.setInt(i++, tipoArticulo);
			ps.setInt(i++, clave);
			ps.setInt(i++, documento);
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, stock);
			ps.setDouble(i++, precio);
			ps.setDouble(i++, stockCentro);
			ps.setDouble(i++, precioCentro);
			ps.setDouble(i++, precioVenta);
			ps.setInt(i++, descuento);
			ps.setInt(i++, repartidor);
			ps.setInt(i++, usuarioPedido);
			ps.setInt(i++, factura);
			ps.setString(i++, Cadena.left(tty, 5));
			ps.setInt(i++, usuario);
			ps.setInt(i++, dayTime);
			ps.setInt(i++, documentoContable);
			ps.setInt(i++, acumulaVentasDia);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(marca, 3));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setInt(i++, cuenta);
			ps.setInt(i++, fecha);
			ps.setInt(i++, apunte);
			ps.setInt(i++, enviado);
			ps.setInt(i++, fechaProveedor);
			ps.setInt(i++, apunteProveedor);
			ps.setInt(i++, centro);
			ps.setInt(i++, representante);
			ps.setInt(i++, tipoArticulo);
			ps.setInt(i++, clave);
			ps.setInt(i++, documento);
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, stock);
			ps.setDouble(i++, precio);
			ps.setDouble(i++, stockCentro);
			ps.setDouble(i++, precioCentro);
			ps.setDouble(i++, precioVenta);
			ps.setInt(i++, descuento);
			ps.setInt(i++, repartidor);
			ps.setInt(i++, usuarioPedido);
			ps.setInt(i++, factura);
			ps.setString(i++, Cadena.left(tty, 5));
			ps.setInt(i++, usuario);
			ps.setInt(i++, dayTime);
			ps.setInt(i++, documentoContable);
			ps.setInt(i++, acumulaVentasDia);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de MovimientoArticulo!!!");
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getGama() {
		return gama;
	}

	public void setGama(int gama) {
		this.gama = gama;
	}

	public int getFamilia() {
		return familia;
	}

	public void setFamilia(int familia) {
		this.familia = familia;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getApunte() {
		return apunte;
	}

	public void setApunte(int apunte) {
		this.apunte = apunte;
	}

	public int getEnviado() {
		return enviado;
	}

	public void setEnviado(int enviado) {
		this.enviado = enviado;
	}

	public int getFechaProveedor() {
		return fechaProveedor;
	}

	public void setFechaProveedor(int fechaProveedor) {
		this.fechaProveedor = fechaProveedor;
	}

	public int getApunteProveedor() {
		return apunteProveedor;
	}

	public void setApunteProveedor(int apunteProveedor) {
		this.apunteProveedor = apunteProveedor;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
	}

	public int getTipoArticulo() {
		return tipoArticulo;
	}

	public void setTipoArticulo(int tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getStockCentro() {
		return stockCentro;
	}

	public void setStockCentro(double stockCentro) {
		this.stockCentro = stockCentro;
	}

	public double getPrecioCentro() {
		return precioCentro;
	}

	public void setPrecioCentro(double precioCentro) {
		this.precioCentro = precioCentro;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public int getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(int repartidor) {
		this.repartidor = repartidor;
	}

	public int getUsuarioPedido() {
		return usuarioPedido;
	}

	public void setUsuarioPedido(int usuarioPedido) {
		this.usuarioPedido = usuarioPedido;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
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

	public int getDocumentoContable() {
		return documentoContable;
	}

	public void setDocumentoContable(int documentoContable) {
		this.documentoContable = documentoContable;
	}

	public int getAcumulaVentasDia() {
		return acumulaVentasDia;
	}

	public void setAcumulaVentasDia(int acumulaVentasDia) {
		this.acumulaVentasDia = acumulaVentasDia;
	}
}
