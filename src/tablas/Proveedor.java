package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;




public class Proveedor {
	private String empresa;
	private String proveedor;
	private int centro;
	private String apellidosRazonSocial;
	private String nombre;
	private String direccionAtencion;
	private String direccion;
	private String poblacion;
	private int pais;
	private int provincia;
	private int codigoPostal;
	private String contacto;
	private String telefono;
	private String fax;
	private String nif;
	private String email;
	private String somosSuCliente;
	private int fechaUltimaFactura;
	private int direccion2;
	private double descuentoComercial;
	private int tipoDescuento;
	private double descuentoProntoPago;
	private double rappel;
	private int tipoRappel;
	private int tipoPrac;
	private int tipoFacturacion;
	private int nivelConflicto;
	private int numeroBanco;
	private int numeroSucursal;
	private int digitosControl;
	private long cuenta;
	private int numeroEfectos;
	private int intervalo;
	private int primerVencimiento;
	private int seNegocia;
	private int diaPago[];
	private String observaciones;
	private int activo;
	private int emisionPagares;
	private int tipoIva;

	public Proveedor(){
		empresa = DatosComunes.eEmpresa;
		proveedor = "";
		centro = DatosComunes.centroCont;;
		apellidosRazonSocial = "";
		nombre = "";
		direccionAtencion = "";
		direccion = "";
		poblacion = "";
		pais = 0;
		provincia = 0;
		codigoPostal = 0;
		contacto = "";
		telefono = "";
		fax = "";
		nif = "";
		email = "";
		somosSuCliente = "";
		fechaUltimaFactura = 0;
		direccion2 = 0;
		descuentoComercial = 0.0;
		tipoDescuento = 0;
		descuentoProntoPago = 0.0;
		rappel = 0.0;
		tipoRappel = 0;
		tipoPrac = 0;
		tipoFacturacion = 0;
		nivelConflicto = 0;
		numeroBanco = 0;
		numeroSucursal = 0;
		digitosControl = 0;
		cuenta = 0;
		numeroEfectos = 0;
		intervalo = 0;
		primerVencimiento = 0;
		seNegocia = 0;
		diaPago = new int[3];
		for(int i = 0;i < 3; i++)
			diaPago[i] = 0;
		observaciones = "";
		activo = 0;
		emisionPagares = 0;
		tipoIva = 0;
	}

	public Proveedor(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				proveedor = rs.getString("PROVAC_PROVACRE").trim();
				centro = rs.getInt("PROVAC_CENTRO");
				apellidosRazonSocial = rs.getString("PROVAC_APELRAZON").trim();
				nombre = rs.getString("PROVAC_NOMBRE").trim();
				direccionAtencion = rs.getString("PROVAC_DIRATT").trim();
				direccion = rs.getString("PROVAC_DIR").trim();
				poblacion = rs.getString("PROVAC_POB").trim();
				pais = rs.getInt("PROVAC_PAIS");
				provincia = rs.getInt("PROVAC_PROV");
				codigoPostal = rs.getInt("PROVAC_CP");
				contacto = rs.getString("PROVAC_CONTACTO").trim();
				telefono = rs.getString("PROVAC_TLFNO").trim();
				fax = rs.getString("PROVAC_FAX").trim();
				nif = rs.getString("PROVAC_NIF").trim();
				email = rs.getString("PROVAC_EMAIL").trim();
				somosSuCliente = rs.getString("PROVAC_SOMOS_SU_CLTE").trim();
				fechaUltimaFactura = rs.getInt("PROVAC_FEC_ULT_FRA");
				direccion2 = rs.getInt("PROVAC_DIR2");
				descuentoComercial = rs.getDouble("PROVAC_DTO_CIAL");
				tipoDescuento = rs.getInt("PROVAC_TIPO_DTO");
				descuentoProntoPago = rs.getDouble("PROVAC_DTO_PP");
				rappel = rs.getDouble("PROVAC_RAPPEL");
				tipoRappel = rs.getInt("PROVAC_TIPO_RAPPEL");
				tipoPrac = rs.getInt("PROVAC_TIPO_PRAC");
				tipoFacturacion = rs.getInt("PROVAC_TIPO_FACTURAC");
				nivelConflicto = rs.getInt("PROVAC_NIVEL_CONFLICTO");
				numeroBanco = rs.getInt("PROVAC_NRO_BCO");
				numeroSucursal = rs.getInt("PROVAC_NRO_SUC");
				digitosControl = rs.getInt("PROVAC_DC");
				cuenta = rs.getLong("PROVAC_CTA");
				numeroEfectos = rs.getInt("PROVAC_NRO_EFECTOS");
				intervalo = rs.getInt("PROVAC_INTERVALO");
				primerVencimiento = rs.getInt("PROVAC_PRIMER_VTO");
				seNegocia = rs.getInt("PROVAC_SE_NEGOCIA");

				diaPago = new int[3];
				diaPago[0] = rs.getInt("PROVAC_DIA_PAGO1");
				diaPago[1] = rs.getInt("PROVAC_DIA_PAGO2");
				diaPago[2] = rs.getInt("PROVAC_DIA_PAGO3");

				observaciones = rs.getString("PROVAC_OBSERV").trim();
				activo = rs.getInt("PROVAC_ACTIVO");
				emisionPagares = rs.getInt("PROVAC_EMISION_PAGARES");
				tipoIva = rs.getInt("PROVAC_TIPO_IVA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Proveedor!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			proveedor = rs.getString("PROVAC_PROVACRE").trim();
			centro = rs.getInt("PROVAC_CENTRO");
			apellidosRazonSocial = rs.getString("PROVAC_APELRAZON").trim();
			nombre = rs.getString("PROVAC_NOMBRE").trim();
			direccionAtencion = rs.getString("PROVAC_DIRATT").trim();
			direccion = rs.getString("PROVAC_DIR").trim();
			poblacion = rs.getString("PROVAC_POB").trim();
			pais = rs.getInt("PROVAC_PAIS");
			provincia = rs.getInt("PROVAC_PROV");
			codigoPostal = rs.getInt("PROVAC_CP");
			contacto = rs.getString("PROVAC_CONTACTO").trim();
			telefono = rs.getString("PROVAC_TLFNO").trim();
			fax = rs.getString("PROVAC_FAX").trim();
			nif = rs.getString("PROVAC_NIF").trim();
			email = rs.getString("PROVAC_EMAIL").trim();
			somosSuCliente = rs.getString("PROVAC_SOMOS_SU_CLTE").trim();
			fechaUltimaFactura = rs.getInt("PROVAC_FEC_ULT_FRA");
			direccion2 = rs.getInt("PROVAC_DIR2");
			descuentoComercial = rs.getDouble("PROVAC_DTO_CIAL");
			tipoDescuento = rs.getInt("PROVAC_TIPO_DTO");
			descuentoProntoPago = rs.getDouble("PROVAC_DTO_PP");
			rappel = rs.getDouble("PROVAC_RAPPEL");
			tipoRappel = rs.getInt("PROVAC_TIPO_RAPPEL");
			tipoPrac = rs.getInt("PROVAC_TIPO_PRAC");
			tipoFacturacion = rs.getInt("PROVAC_TIPO_FACTURAC");
			nivelConflicto = rs.getInt("PROVAC_NIVEL_CONFLICTO");
			numeroBanco = rs.getInt("PROVAC_NRO_BCO");
			numeroSucursal = rs.getInt("PROVAC_NRO_SUC");
			digitosControl = rs.getInt("PROVAC_DC");
			cuenta = rs.getLong("PROVAC_CTA");
			numeroEfectos = rs.getInt("PROVAC_NRO_EFECTOS");
			intervalo = rs.getInt("PROVAC_INTERVALO");
			primerVencimiento = rs.getInt("PROVAC_PRIMER_VTO");
			seNegocia = rs.getInt("PROVAC_SE_NEGOCIA");

			diaPago = new int[3];
			diaPago[0] = rs.getInt("PROVAC_DIA_PAGO1");
			diaPago[1] = rs.getInt("PROVAC_DIA_PAGO2");
			diaPago[2] = rs.getInt("PROVAC_DIA_PAGO3");

			observaciones = rs.getString("PROVAC_OBSERV").trim();
			activo = rs.getInt("PROVAC_ACTIVO");
			emisionPagares = rs.getInt("PROVAC_EMISION_PAGARES");
			tipoIva = rs.getInt("PROVAC_TIPO_IVA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Proveedor!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	// Devolvemos 'true' si no hay errores, 'false' si hay alguna excepción.
	public boolean write(){
		boolean escrituraCorrecta = true;
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO PROVAC " +
						   "(EMPRESA, " +
						   "PROVAC_PROVACRE, " +
						   "PROVAC_CENTRO, " +
						   "PROVAC_APELRAZON, " +
						   "PROVAC_NOMBRE, " +
						   "PROVAC_DIRATT, " +
						   "PROVAC_DIR, " +
						   "PROVAC_POB, " +
						   "PROVAC_PAIS, " +
						   "PROVAC_PROV, " +
						   "PROVAC_CP, " +
						   "PROVAC_CONTACTO, " +
						   "PROVAC_TLFNO, " +
						   "PROVAC_FAX, " +
						   "PROVAC_NIF, " +
						   "PROVAC_EMAIL, " +
						   "PROVAC_SOMOS_SU_CLTE, " +
						   "PROVAC_FEC_ULT_FRA, " +
						   "PROVAC_DIR2, " +
						   "PROVAC_DTO_CIAL, " +
						   "PROVAC_TIPO_DTO, " +
						   "PROVAC_DTO_PP, " +
						   "PROVAC_RAPPEL, " +
						   "PROVAC_TIPO_RAPPEL, " +
						   "PROVAC_TIPO_PRAC, " +
						   "PROVAC_TIPO_FACTURAC, " +
						   "PROVAC_NIVEL_CONFLICTO, " +
						   "PROVAC_NRO_BCO, " +
						   "PROVAC_NRO_SUC, " +
						   "PROVAC_DC, " +
						   "PROVAC_CTA, " +
						   "PROVAC_NRO_EFECTOS, " +
						   "PROVAC_INTERVALO, " +
						   "PROVAC_PRIMER_VTO, " +
						   "PROVAC_SE_NEGOCIA, " +
						   "PROVAC_DIA_PAGO1, " +
						   "PROVAC_DIA_PAGO2, " +
						   "PROVAC_DIA_PAGO3, " +
						   "PROVAC_OBSERV, " +
						   "PROVAC_ACTIVO, " +
						   "PROVAC_EMISION_PAGARES, " +
						   "PROVAC_TIPO_IVA) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "PROVAC_PROVACRE = ?, " +
						   "PROVAC_CENTRO = ?, " +
						   "PROVAC_APELRAZON = ?, " +
						   "PROVAC_NOMBRE = ?, " +
						   "PROVAC_DIRATT = ?, " +
						   "PROVAC_DIR = ?, " +
						   "PROVAC_POB = ?, " +
						   "PROVAC_PAIS = ?, " +
						   "PROVAC_PROV = ?, " +
						   "PROVAC_CP = ?, " +
						   "PROVAC_CONTACTO = ?, " +
						   "PROVAC_TLFNO = ?, " +
						   "PROVAC_FAX = ?, " +
						   "PROVAC_NIF = ?, " +
						   "PROVAC_EMAIL = ?, " +
						   "PROVAC_SOMOS_SU_CLTE = ?, " +
						   "PROVAC_FEC_ULT_FRA = ?, " +
						   "PROVAC_DIR2 = ?, " +
						   "PROVAC_DTO_CIAL = ?, " +
						   "PROVAC_TIPO_DTO = ?, " +
						   "PROVAC_DTO_PP = ?, " +
						   "PROVAC_RAPPEL = ?, " +
						   "PROVAC_TIPO_RAPPEL = ?, " +
						   "PROVAC_TIPO_PRAC = ?, " +
						   "PROVAC_TIPO_FACTURAC = ?, " +
						   "PROVAC_NIVEL_CONFLICTO = ?, " +
						   "PROVAC_NRO_BCO = ?, " +
						   "PROVAC_NRO_SUC = ?, " +
						   "PROVAC_DC = ?, " +
						   "PROVAC_CTA = ?, " +
						   "PROVAC_NRO_EFECTOS = ?, " +
						   "PROVAC_INTERVALO = ?, " +
						   "PROVAC_PRIMER_VTO = ?, " +
						   "PROVAC_SE_NEGOCIA = ?, " +
						   "PROVAC_DIA_PAGO1 = ?, " +
						   "PROVAC_DIA_PAGO2 = ?, " +
						   "PROVAC_DIA_PAGO3 = ?, " +
						   "PROVAC_OBSERV = ?, " +
						   "PROVAC_ACTIVO = ?, " +
						   "PROVAC_EMISION_PAGARES = ?, " +
						   "PROVAC_TIPO_IVA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(proveedor, 9));
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazonSocial, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 20));
			ps.setInt(i++, pais);
			ps.setInt(i++, provincia);
			ps.setInt(i++, codigoPostal);
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(email, 60));
			ps.setString(i++, Cadena.left(somosSuCliente, 16));
			ps.setInt(i++, fechaUltimaFactura);
			ps.setInt(i++, direccion2);
			ps.setDouble(i++, descuentoComercial);
			ps.setInt(i++, tipoDescuento);
			ps.setDouble(i++, descuentoProntoPago);
			ps.setDouble(i++, rappel);
			ps.setInt(i++, tipoRappel);
			ps.setInt(i++, tipoPrac);
			ps.setInt(i++, tipoFacturacion);
			ps.setInt(i++, nivelConflicto);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitosControl);
			ps.setLong(i++, cuenta);
			ps.setInt(i++, numeroEfectos);
			ps.setInt(i++, intervalo);
			ps.setInt(i++, primerVencimiento);
			ps.setInt(i++, seNegocia);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, diaPago[j]);
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setInt(i++, activo);
			ps.setInt(i++, emisionPagares);
			ps.setInt(i++, tipoIva);
						
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(proveedor, 9));
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazonSocial, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 20));
			ps.setInt(i++, pais);
			ps.setInt(i++, provincia);
			ps.setInt(i++, codigoPostal);
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(email, 60));
			ps.setString(i++, Cadena.left(somosSuCliente, 16));
			ps.setInt(i++, fechaUltimaFactura);
			ps.setInt(i++, direccion2);
			ps.setDouble(i++, descuentoComercial);
			ps.setInt(i++, tipoDescuento);
			ps.setDouble(i++, descuentoProntoPago);
			ps.setDouble(i++, rappel);
			ps.setInt(i++, tipoRappel);
			ps.setInt(i++, tipoPrac);
			ps.setInt(i++, tipoFacturacion);
			ps.setInt(i++, nivelConflicto);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitosControl);
			ps.setLong(i++, cuenta);
			ps.setInt(i++, numeroEfectos);
			ps.setInt(i++, intervalo);
			ps.setInt(i++, primerVencimiento);
			ps.setInt(i++, seNegocia);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, diaPago[j]);
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setInt(i++, activo);
			ps.setInt(i++, emisionPagares);
			ps.setInt(i++, tipoIva);
			
			ps.execute();
			
		} catch (SQLException e) {
			escrituraCorrecta = false;
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Proveedor!!!");
				e.printStackTrace();
			}
		}
		
		return escrituraCorrecta;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public String getApellidosRazonSocial() {
		return apellidosRazonSocial;
	}

	public void setApellidosRazonSocial(String apellidosRazonSocial) {
		this.apellidosRazonSocial = apellidosRazonSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccionAtencion() {
		return direccionAtencion;
	}

	public void setDireccionAtencion(String direccionAtencion) {
		this.direccionAtencion = direccionAtencion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public int getProvincia() {
		return provincia;
	}

	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSomosSuCliente() {
		return somosSuCliente;
	}

	public void setSomosSuCliente(String somosSuCliente) {
		this.somosSuCliente = somosSuCliente;
	}

	public int getFechaUltimaFactura() {
		return fechaUltimaFactura;
	}

	public void setFechaUltimaFactura(int fechaUltimaFactura) {
		this.fechaUltimaFactura = fechaUltimaFactura;
	}

	public int getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(int direccion2) {
		this.direccion2 = direccion2;
	}

	public double getDescuentoComercial() {
		return descuentoComercial;
	}

	public void setDescuentoComercial(double descuentoComercial) {
		this.descuentoComercial = descuentoComercial;
	}

	public int getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(int tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public double getDescuentoProntoPago() {
		return descuentoProntoPago;
	}

	public void setDescuentoProntoPago(double descuentoProntoPago) {
		this.descuentoProntoPago = descuentoProntoPago;
	}

	public double getRappel() {
		return rappel;
	}

	public void setRappel(double rappel) {
		this.rappel = rappel;
	}

	public int getTipoRappel() {
		return tipoRappel;
	}

	public void setTipoRappel(int tipoRappel) {
		this.tipoRappel = tipoRappel;
	}

	public int getTipoPrac() {
		return tipoPrac;
	}

	public void setTipoPrac(int tipoPrac) {
		this.tipoPrac = tipoPrac;
	}

	public int getTipoFacturacion() {
		return tipoFacturacion;
	}

	public void setTipoFacturacion(int tipoFacturacion) {
		this.tipoFacturacion = tipoFacturacion;
	}

	public int getNivelConflicto() {
		return nivelConflicto;
	}

	public void setNivelConflicto(int nivelConflicto) {
		this.nivelConflicto = nivelConflicto;
	}

	public int getNumeroBanco() {
		return numeroBanco;
	}

	public void setNumeroBanco(int numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	public int getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(int numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public int getDigitosControl() {
		return digitosControl;
	}

	public void setDigitosControl(int digitosControl) {
		this.digitosControl = digitosControl;
	}

	public long getCuenta() {
		return cuenta;
	}

	public void setCuenta(long cuenta) {
		this.cuenta = cuenta;
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

	public int[] getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(int[] diaPago) {
		this.diaPago = diaPago;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getEmisionPagares() {
		return emisionPagares;
	}

	public void setEmisionPagares(int emisionPagares) {
		this.emisionPagares = emisionPagares;
	}

	public int getTipoIva() {
		return tipoIva;
	}

	public void setTipoIva(int tipoIva) {
		this.tipoIva = tipoIva;
	}
}
