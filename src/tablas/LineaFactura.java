package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class LineaFactura {
	private String empresa;
	private int cliente;
	private int factura;
	private int fechaFactura;
	private int centro;
	private int año;
	private int albaran;
	private int linea;
	private int fecha;
	private int clienteContado;
	private int okExpedicion;
	private int tipoLinea;
	private String marca;
	private int gama;
	private int familia;
	private String artFcp;
	private double cantidad;
	private double precio;
	private int porcentajeDescuento1;
	private int porcentajeDescuento2;
	private double importe;
	private double porcentajeComision;
	private double comision;
	private int seFactura;
	private double descuentoProntoPago;
	private double recargoAplazamiento;
	private String cuentaVentas;
	private int iva;
	private int recargoEquivalencia;
	private int numeroEfectos;
	private int intervalo;
	private int primerVencimiento;
	private int seNegocia;
	private int diaDePago[];
	private int repres;
	private int lineaRegalo;
	private String tipoAlbaran;

	public LineaFactura(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		factura = 0;
		fechaFactura = 0;
		centro = 0;
		año = 0;
		albaran = 0;
		linea = 0;
		fecha = 0;
		clienteContado = 0;
		okExpedicion = 0;
		tipoLinea = 0;
		marca = "";
		gama = 0;
		familia = 0;
		artFcp = "";
		cantidad = 0.0;
		precio = 0.0;
		porcentajeDescuento1 = 0;
		porcentajeDescuento2 = 0;
		importe = 0.0;
		porcentajeComision = 0.0;
		comision = 0.0;
		seFactura = 0;
		descuentoProntoPago = 0.0;
		recargoAplazamiento = 0.0;
		cuentaVentas = "";
		iva = 0;
		recargoEquivalencia = 0;
		numeroEfectos = 0;
		intervalo = 0;
		primerVencimiento = 0;
		seNegocia = 0;

		diaDePago = new  int[3];
		for(int i = 0; i < 3; i++)
			diaDePago[i] = 0;

		repres = 0;
		lineaRegalo = 0;
		tipoAlbaran = "";
	}

	public LineaFactura(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("FACLIN_CLIENTE");
				factura = rs.getInt("FACLIN_FACTURA");
				fechaFactura = rs.getInt("FACLIN_FECHA_FRA");
				centro = rs.getInt("FACLIN_CENTRO");
				año = rs.getInt("FACLIN_ANY");
				albaran = rs.getInt("FACLIN_ALBARAN");
				linea = rs.getInt("FACLIN_LINEA");
				fecha = rs.getInt("FACLIN_FECHA");
				clienteContado = rs.getInt("FACLIN_CLICONT");
				okExpedicion = rs.getInt("FACLIN_OK_EXPED");
				tipoLinea = rs.getInt("FACLIN_TIPO_LINEA");
				marca = rs.getString("FACLIN_MARCA").trim();
				gama = rs.getInt("FACLIN_GAMA");
				familia = rs.getInt("FACLIN_FAMILIA");
				artFcp = rs.getString("FACLIN_ARTFCP").trim();
				cantidad = rs.getDouble("FACLIN_CANTIDAD");
				precio = rs.getDouble("FACLIN_PRECIO");
				porcentajeDescuento1 = rs.getInt("FACLIN_POR_DTO1");
				porcentajeDescuento2 = rs.getInt("FACLIN_POR_DTO2");
				importe = rs.getDouble("FACLIN_IMPORTE");
				porcentajeComision = rs.getDouble("FACLIN_POR_COMIS");
				comision = rs.getDouble("FACLIN_COMISION");
				seFactura = rs.getInt("FACLIN_SE_FRA");
				descuentoProntoPago = rs.getDouble("FACLIN_DTO_PP");
				recargoAplazamiento = rs.getDouble("FACLIN_RCAPLZ");
				cuentaVentas = rs.getString("FACLIN_CTA_VTAS").trim();
				iva = rs.getInt("FACLIN_IVA");
				recargoEquivalencia = rs.getInt("FACLIN_REQU");
				numeroEfectos = rs.getInt("FACLIN_NRO_EFECTOS");
				intervalo = rs.getInt("FACLIN_INTERVALO");
				primerVencimiento = rs.getInt("FACLIN_PRIMER_VTO");
				seNegocia = rs.getInt("FACLIN_SE_NEGOCIA");				
				diaDePago = new  int[3];
				diaDePago[0] = rs.getInt("FACLIN_DIA_PAGO1");
				diaDePago[1] = rs.getInt("FACLIN_DIA_PAGO2");
				diaDePago[2] = rs.getInt("FACLIN_DIA_PAGO3");				
				repres = rs.getInt("FACLIN_REPRES");
				lineaRegalo = rs.getInt("FACLIN_LIN_REGALO");
				tipoAlbaran = rs.getString("FACLIN_TIPO_ALBARAN");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de LineaFactura!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			cliente = rs.getInt("FACLIN_CLIENTE");
			factura = rs.getInt("FACLIN_FACTURA");
			fechaFactura = rs.getInt("FACLIN_FECHA_FRA");
			centro = rs.getInt("FACLIN_CENTRO");
			año = rs.getInt("FACLIN_ANY");
			albaran = rs.getInt("FACLIN_ALBARAN");
			linea = rs.getInt("FACLIN_LINEA");
			fecha = rs.getInt("FACLIN_FECHA");
			clienteContado = rs.getInt("FACLIN_CLICONT");
			okExpedicion = rs.getInt("FACLIN_OK_EXPED");
			tipoLinea = rs.getInt("FACLIN_TIPO_LINEA");
			marca = rs.getString("FACLIN_MARCA").trim();
			gama = rs.getInt("FACLIN_GAMA");
			familia = rs.getInt("FACLIN_FAMILIA");
			artFcp = rs.getString("FACLIN_ARTFCP").trim();
			cantidad = rs.getDouble("FACLIN_CANTIDAD");
			precio = rs.getDouble("FACLIN_PRECIO");
			porcentajeDescuento1 = rs.getInt("FACLIN_POR_DTO1");
			porcentajeDescuento2 = rs.getInt("FACLIN_POR_DTO2");
			importe = rs.getDouble("FACLIN_IMPORTE");
			porcentajeComision = rs.getDouble("FACLIN_POR_COMIS");
			comision = rs.getDouble("FACLIN_COMISION");
			seFactura = rs.getInt("FACLIN_SE_FRA");
			descuentoProntoPago = rs.getDouble("FACLIN_DTO_PP");
			recargoAplazamiento = rs.getDouble("FACLIN_RCAPLZ");
			cuentaVentas = rs.getString("FACLIN_CTA_VTAS").trim();
			iva = rs.getInt("FACLIN_IVA");
			recargoEquivalencia = rs.getInt("FACLIN_REQU");
			numeroEfectos = rs.getInt("FACLIN_NRO_EFECTOS");
			intervalo = rs.getInt("FACLIN_INTERVALO");
			primerVencimiento = rs.getInt("FACLIN_PRIMER_VTO");
			seNegocia = rs.getInt("FACLIN_SE_NEGOCIA");				
			diaDePago[0] = rs.getInt("FACLIN_DIA_PAGO1");
			diaDePago[1] = rs.getInt("FACLIN_DIA_PAGO2");
			diaDePago[2] = rs.getInt("FACLIN_DIA_PAGO3");				
			repres = rs.getInt("FACLIN_REPRES");
			lineaRegalo = rs.getInt("FACLIN_LIN_REGALO");
			tipoAlbaran = rs.getString("FACLIN_TIPO_ALBARAN");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de LineaFactura!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO FACLIN " +
						   "(EMPRESA, " +
						   "FACLIN_CLIENTE, " +
						   "FACLIN_FACTURA, " +
						   "FACLIN_FECHA_FRA, " +
						   "FACLIN_CENTRO, " +
						   "FACLIN_ANY, " +
						   "FACLIN_ALBARAN, " +
						   "FACLIN_LINEA, " +
						   "FACLIN_FECHA, " +
						   "FACLIN_CLICONT, " +
						   "FACLIN_OK_EXPED, " +
						   "FACLIN_TIPO_LINEA, " +
						   "FACLIN_MARCA, " +
						   "FACLIN_GAMA, " +
						   "FACLIN_FAMILIA, " +
						   "FACLIN_ARTFCP, " +
						   "FACLIN_CANTIDAD, " +
						   "FACLIN_PRECIO, " +
						   "FACLIN_POR_DTO1, " +
						   "FACLIN_POR_DTO2, " +
						   "FACLIN_IMPORTE, " +
						   "FACLIN_POR_COMIS, " +
						   "FACLIN_COMISION, " +
						   "FACLIN_SE_FRA, " +
						   "FACLIN_DTO_PP, " +
						   "FACLIN_RCAPLZ, " +
						   "FACLIN_CTA_VTAS, " +
						   "FACLIN_IVA, " +
						   "FACLIN_REQU, " +
						   "FACLIN_NRO_EFECTOS, " +
						   "FACLIN_INTERVALO, " +
						   "FACLIN_PRIMER_VTO, " +
						   "FACLIN_SE_NEGOCIA, " +
						   "FACLIN_DIA_PAGO1, " +
						   "FACLIN_DIA_PAGO2, " +
						   "FACLIN_DIA_PAGO3, " +
						   "FACLIN_REPRES, " +
						   "FACLIN_LIN_REGALO, " +
						   "FACLIN_TIPO_ALBARAN) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "FACLIN_CLIENTE = ?, " +
						   "FACLIN_FACTURA = ?, " +
						   "FACLIN_FECHA_FRA = ?, " +
						   "FACLIN_CENTRO = ?, " +
						   "FACLIN_ANY = ?, " +
						   "FACLIN_ALBARAN = ?, " +
						   "FACLIN_LINEA = ?, " +
						   "FACLIN_FECHA = ?, " +
						   "FACLIN_CLICONT = ?, " +
						   "FACLIN_OK_EXPED = ?, " +
						   "FACLIN_TIPO_LINEA = ?, " +
						   "FACLIN_MARCA = ?, " +
						   "FACLIN_GAMA = ?, " +
						   "FACLIN_FAMILIA = ?, " +
						   "FACLIN_ARTFCP = ?, " +
						   "FACLIN_CANTIDAD = ?, " +
						   "FACLIN_PRECIO = ?, " +
						   "FACLIN_POR_DTO1 = ?, " +
						   "FACLIN_POR_DTO2 = ?, " +
						   "FACLIN_IMPORTE = ?, " +
						   "FACLIN_POR_COMIS = ?, " +
						   "FACLIN_COMISION = ?, " +
						   "FACLIN_SE_FRA = ?, " +
						   "FACLIN_DTO_PP = ?, " +
						   "FACLIN_RCAPLZ = ?, " +
						   "FACLIN_CTA_VTAS = ?, " +
						   "FACLIN_IVA = ?, " +
						   "FACLIN_REQU = ?, " +
						   "FACLIN_NRO_EFECTOS = ?, " +
						   "FACLIN_INTERVALO = ?, " +
						   "FACLIN_PRIMER_VTO = ?, " +
						   "FACLIN_SE_NEGOCIA = ?, " +
						   "FACLIN_DIA_PAGO1 = ?, " +
						   "FACLIN_DIA_PAGO2 = ?, " +
						   "FACLIN_DIA_PAGO3 = ?, " +
						   "FACLIN_REPRES = ?, " +
						   "FACLIN_LIN_REGALO = ?, " +
						   "FACLIN_TIPO_ALBARAN = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, factura);
			ps.setInt(i++, fechaFactura);
			ps.setInt(i++, centro);
			ps.setInt(i++, año);
			ps.setInt(i++, albaran);
			ps.setInt(i++, linea);
			ps.setInt(i++, fecha);
			ps.setInt(i++, clienteContado);
			ps.setInt(i++, okExpedicion);
			ps.setInt(i++, tipoLinea);
			ps.setString(i++, Cadena.left(marca, 3));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setString(i++, Cadena.left(artFcp, 13));
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precio);
			ps.setInt(i++, porcentajeDescuento1);
			ps.setInt(i++, porcentajeDescuento2);
			ps.setDouble(i++, importe);
			ps.setDouble(i++, porcentajeComision);
			ps.setDouble(i++, comision);
			ps.setInt(i++, seFactura);
			ps.setDouble(i++, descuentoProntoPago);
			ps.setDouble(i++, recargoAplazamiento);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			ps.setInt(i++, iva);
			ps.setInt(i++, recargoEquivalencia);
			ps.setInt(i++, numeroEfectos);
			ps.setInt(i++, intervalo);
			ps.setInt(i++, primerVencimiento);
			ps.setInt(i++, seNegocia);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, diaDePago[j]);
			ps.setInt(i++, repres);
			ps.setInt(i++, lineaRegalo);
			ps.setString(i++, Cadena.left(tipoAlbaran, 2));			
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, factura);
			ps.setInt(i++, fechaFactura);
			ps.setInt(i++, centro);
			ps.setInt(i++, año);
			ps.setInt(i++, albaran);
			ps.setInt(i++, linea);
			ps.setInt(i++, fecha);
			ps.setInt(i++, clienteContado);
			ps.setInt(i++, okExpedicion);
			ps.setInt(i++, tipoLinea);
			ps.setString(i++, Cadena.left(marca, 3));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setString(i++, Cadena.left(artFcp, 13));
			ps.setDouble(i++, cantidad);
			ps.setDouble(i++, precio);
			ps.setInt(i++, porcentajeDescuento1);
			ps.setInt(i++, porcentajeDescuento2);
			ps.setDouble(i++, importe);
			ps.setDouble(i++, porcentajeComision);
			ps.setDouble(i++, comision);
			ps.setInt(i++, seFactura);
			ps.setDouble(i++, descuentoProntoPago);
			ps.setDouble(i++, recargoAplazamiento);
			ps.setString(i++, Cadena.left(cuentaVentas, 9));
			ps.setInt(i++, iva);
			ps.setInt(i++, recargoEquivalencia);
			ps.setInt(i++, numeroEfectos);
			ps.setInt(i++, intervalo);
			ps.setInt(i++, primerVencimiento);
			ps.setInt(i++, seNegocia);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, diaDePago[j]);
			ps.setInt(i++, repres);
			ps.setInt(i++, lineaRegalo);
			ps.setString(i++, Cadena.left(tipoAlbaran, 2));			
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de LineaFactura!!!");
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

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getClienteContado() {
		return clienteContado;
	}

	public void setClienteContado(int clienteContado) {
		this.clienteContado = clienteContado;
	}

	public int getOkExpedicion() {
		return okExpedicion;
	}

	public void setOkExpedicion(int okExpedicion) {
		this.okExpedicion = okExpedicion;
	}

	public int getTipoLinea() {
		return tipoLinea;
	}

	public void setTipoLinea(int tipoLinea) {
		this.tipoLinea = tipoLinea;
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

	public String getArtFcp() {
		return artFcp;
	}

	public void setArtFcp(String artFcp) {
		this.artFcp = artFcp;
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

	public int getPorcentajeDescuento1() {
		return porcentajeDescuento1;
	}

	public void setPorcentajeDescuento1(int porcentajeDescuento1) {
		this.porcentajeDescuento1 = porcentajeDescuento1;
	}

	public int getPorcentajeDescuento2() {
		return porcentajeDescuento2;
	}

	public void setPorcentajeDescuento2(int porcentajeDescuento2) {
		this.porcentajeDescuento2 = porcentajeDescuento2;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public int getSeFactura() {
		return seFactura;
	}

	public void setSeFactura(int seFactura) {
		this.seFactura = seFactura;
	}

	public double getDescuentoProntoPago() {
		return descuentoProntoPago;
	}

	public void setDescuentoProntoPago(double descuentoProntoPago) {
		this.descuentoProntoPago = descuentoProntoPago;
	}

	public double getRecargoAplazamiento() {
		return recargoAplazamiento;
	}

	public void setRecargoAplazamiento(double recargoAplazamiento) {
		this.recargoAplazamiento = recargoAplazamiento;
	}

	public String getCuentaVentas() {
		return cuentaVentas;
	}

	public void setCuentaVentas(String cuentaVentas) {
		this.cuentaVentas = cuentaVentas;
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

	public int getDiaDePago(int i) {
		return diaDePago[i];
	}
	
	public int[] getDiaDePago() {
		return diaDePago;
	}

	public void setDiaDePago(int indice, int valor) {
		this.diaDePago[indice] = valor;
	}

	public void setDiaDePago(int[] diaDePago) {
		this.diaDePago = diaDePago;
	}

	public int getRepres() {
		return repres;
	}

	public void setRepres(int repres) {
		this.repres = repres;
	}

	public int getLineaRegalo() {
		return lineaRegalo;
	}

	public void setLineaRegalo(int lineaRegalo) {
		this.lineaRegalo = lineaRegalo;
	}

	public String getTipoAlbaran() {
		return tipoAlbaran;
	}

	public void setTipoAlbaran(String tipoAlbaran) {
		this.tipoAlbaran = tipoAlbaran;
	}
}
