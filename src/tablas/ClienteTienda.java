package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class ClienteTienda {
	private String empresa;
	private int cliente;
	private int centro;
	private String apellidosRazonSoc;
	private String nombre;
	private String observaciones;
	private String direccionAtencion;
	private String direccionClites;
	private int pais;
	private int codigoPoblacion;
	private int ordenCodigoPoblacion;
	private int calle;
	private int portal;
	private String escaleraBloqueKm;
	private int piso;
	private String puerta;
	private int codigoPostal;
	private String contacto;
	private String telefono;
	private String telefono2;
	private String nif;
	private String email;
	private String nickAlias;
	private String password;
	private int fechaNaciomiento;
	private String somosSuProveedorNro;
	private int fechaUltimafactura;
	private double riesgoTotal;
	private double limiteCredito;
	private double valorAlbaranesPendienteFact;
	private double abonosPendientes;
	private int zona;
	private int tipoIva;
	private int representante;
	private int direccion2;
	private double descuentoComercial;
	private double descuentoProntoPago;
	private int copias;
	private int tipoCliente;
	private int tipoFacturacion;
	private int nivelConflicto;
	private int tipoTarifa;
	private int envioTarifa;
	private int ruta;
	private int numeroBanco;
	private int numeroSucursal;
	private int digitosControl;
	private long cuenta;
	private int bancoNegociacion;
	private int numeroEfectos;
	private int intervalo;
	private int primerVencimiento;
	private int seNegocia;
	private int diaPago1;
	private int diaPago2;
	private int diaPago3;
	private int activo;
	private String sexo;
	private int noMailing;

	public ClienteTienda(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		centro = 0;
		apellidosRazonSoc = "";
		nombre = "";
		observaciones = "";
		direccionAtencion = "";
		direccionClites = "";
		pais = 0;
		codigoPoblacion = 0;
		ordenCodigoPoblacion = 0;
		calle = 0;
		portal = 0;
		escaleraBloqueKm = "";
		piso = 0;
		puerta = "";
		codigoPostal = 0;
		contacto = "";
		telefono = "";
		telefono2 = "";
		nif = "";
		email = "";
		nickAlias = "";
		password = "";
		fechaNaciomiento = 0;
		somosSuProveedorNro = "";
		fechaUltimafactura = 0;
		riesgoTotal = 0.0;
		limiteCredito = 0.0;
		valorAlbaranesPendienteFact = 0.0;
		abonosPendientes = 0.0;
		zona = 0;
		tipoIva = 0;
		representante = 0;
		direccion2 = 0;
		descuentoComercial = 0.0;
		descuentoProntoPago = 0.0;
		copias = 0;
		tipoCliente = 0;
		tipoFacturacion = 0;
		nivelConflicto = 0;
		tipoTarifa = 0;
		envioTarifa = 0;
		ruta = 0;
		numeroBanco = 0;
		numeroSucursal = 0;
		digitosControl = 0;
		cuenta = 0;
		bancoNegociacion = 0;
		numeroEfectos = 0;
		intervalo = 0;
		primerVencimiento = 0;
		seNegocia = 0;
		diaPago1 = 0;
		diaPago2 = 0;
		diaPago3 = 0;
		activo = 0;
		sexo = "";
		noMailing = 0;
	}

	public ClienteTienda(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("CLIENT_CLIENTE");
				centro = rs.getInt("CLIENT_CENTRO");
				apellidosRazonSoc = rs.getString("CLIENT_APELRAZON").trim();
				nombre = rs.getString("CLIENT_NOMBRE").trim();
				observaciones = rs.getString("CLIENT_OBSERVAC").trim();
				direccionAtencion = rs.getString("CLIENT_DIRATT").trim();
				direccionClites = rs.getString("CLIENT_DIRclites").trim();
				pais = rs.getInt("CLIENT_PAIS");
				codigoPoblacion = rs.getInt("CLIENT_CODPOB");
				ordenCodigoPoblacion = rs.getInt("CLIENT_ORDENCPB");
				calle = rs.getInt("CLIENT_CALLE");
				portal = rs.getInt("CLIENT_PORTAL");
				escaleraBloqueKm = rs.getString("CLIENT_ESCALBLOQKM").trim();
				piso = rs.getInt("CLIENT_PISO");
				puerta = rs.getString("CLIENT_PUERTA").trim();
				codigoPostal = rs.getInt("CLIENT_CP");
				contacto = rs.getString("CLIENT_CONTACTO").trim();
				telefono = rs.getString("CLIENT_TLFNO").trim();
				telefono2 = rs.getString("CLIENT_TLFNO2").trim();
				nif = rs.getString("CLIENT_NIF").trim();
				email = rs.getString("CLIENT_EMAIL").trim();
				nickAlias = rs.getString("CLIENT_NIK_ALIAS").trim();
				password = rs.getString("CLIENT_PASSWORD").trim();
				fechaNaciomiento = rs.getInt("CLIENT_FEC_NACIMIENTO");
				somosSuProveedorNro = rs.getString("CLIENT_SOMOS_SU_PROV").trim();
				fechaUltimafactura = rs.getInt("CLIENT_FEC_ULT_FRA");
				riesgoTotal = rs.getDouble("CLIENT_RIES_TOT");
				limiteCredito = rs.getDouble("CLIENT_LIM_CRE");
				valorAlbaranesPendienteFact = rs.getDouble("CLIENT_VAL_ALB_PDT_FAC");
				abonosPendientes = rs.getDouble("CLIENT_ABONOS_PENDTES");
				zona = rs.getInt("CLIENT_ZONA");
				tipoIva = rs.getInt("CLIENT_TIPO_IVA");
				representante = rs.getInt("CLIENT_REPRE");
				direccion2 = rs.getInt("CLIENT_DIR2");
				descuentoComercial = rs.getDouble("CLIENT_DTO_CIAL");
				descuentoProntoPago = rs.getDouble("CLIENT_DTO_PP");
				copias = rs.getInt("CLIENT_COPIAS");
				tipoCliente = rs.getInt("CLIENT_TIPO_CLTE");
				tipoFacturacion = rs.getInt("CLIENT_TIPO_FACTURAC");
				nivelConflicto = rs.getInt("CLIENT_NIVEL_CONFLICTO");
				tipoTarifa = rs.getInt("CLIENT_TIPO_TARIFA");
				envioTarifa = rs.getInt("CLIENT_ENVIO_TARIFA");
				ruta = rs.getInt("CLIENT_RUTA");
				numeroBanco = rs.getInt("CLIENT_NRO_BCO");
				numeroSucursal = rs.getInt("CLIENT_NRO_SUC");
				digitosControl = rs.getInt("CLIENT_DC");
				cuenta = rs.getLong("CLIENT_CTA");
				bancoNegociacion = rs.getInt("CLIENT_BANCO_NEGOCIACION");
				numeroEfectos = rs.getInt("CLIENT_NRO_EFECTOS");
				intervalo = rs.getInt("CLIENT_INTERVALO");
				primerVencimiento = rs.getInt("CLIENT_PRIMER_VTO");
				seNegocia = rs.getInt("CLIENT_SE_NEGOCIA");
				diaPago1 = rs.getInt("CLIENT_DIA_PAGO1");
				diaPago2 = rs.getInt("CLIENT_DIA_PAGO2");
				diaPago3 = rs.getInt("CLIENT_DIA_PAGO3");
				activo = rs.getInt("CLIENT_ACTIVO");
				sexo = rs.getString("CLIENT_SEXO").trim();
				noMailing = rs.getInt("CLIENT_NO_MAILING");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ClienteTienda!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{
			empresa = rs.getString("EMPRESA").trim();
			cliente = rs.getInt("CLIENT_CLIENTE");
			centro = rs.getInt("CLIENT_CENTRO");
			apellidosRazonSoc = rs.getString("CLIENT_APELRAZON").trim();
			nombre = rs.getString("CLIENT_NOMBRE").trim();
			observaciones = rs.getString("CLIENT_OBSERVAC").trim();
			direccionAtencion = rs.getString("CLIENT_DIRATT").trim();
			direccionClites = rs.getString("CLIENT_DIRclites").trim();
			pais = rs.getInt("CLIENT_PAIS");
			codigoPoblacion = rs.getInt("CLIENT_CODPOB");
			ordenCodigoPoblacion = rs.getInt("CLIENT_ORDENCPB");
			calle = rs.getInt("CLIENT_CALLE");
			portal = rs.getInt("CLIENT_PORTAL");
			escaleraBloqueKm = rs.getString("CLIENT_ESCALBLOQKM").trim();
			piso = rs.getInt("CLIENT_PISO");
			puerta = rs.getString("CLIENT_PUERTA").trim();
			codigoPostal = rs.getInt("CLIENT_CP");
			contacto = rs.getString("CLIENT_CONTACTO").trim();
			telefono = rs.getString("CLIENT_TLFNO").trim();
			telefono2 = rs.getString("CLIENT_TLFNO2").trim();
			nif = rs.getString("CLIENT_NIF").trim();
			email = rs.getString("CLIENT_EMAIL").trim();
			nickAlias = rs.getString("CLIENT_NIK_ALIAS").trim();
			password = rs.getString("CLIENT_PASSWORD").trim();
			fechaNaciomiento = rs.getInt("CLIENT_FEC_NACIMIENTO");
			somosSuProveedorNro = rs.getString("CLIENT_SOMOS_SU_PROV").trim();
			fechaUltimafactura = rs.getInt("CLIENT_FEC_ULT_FRA");
			riesgoTotal = rs.getDouble("CLIENT_RIES_TOT");
			limiteCredito = rs.getDouble("CLIENT_LIM_CRE");
			valorAlbaranesPendienteFact = rs.getDouble("CLIENT_VAL_ALB_PDT_FAC");
			abonosPendientes = rs.getDouble("CLIENT_ABONOS_PENDTES");
			zona = rs.getInt("CLIENT_ZONA");
			tipoIva = rs.getInt("CLIENT_TIPO_IVA");
			representante = rs.getInt("CLIENT_REPRE");
			direccion2 = rs.getInt("CLIENT_DIR2");
			descuentoComercial = rs.getDouble("CLIENT_DTO_CIAL");
			descuentoProntoPago = rs.getDouble("CLIENT_DTO_PP");
			copias = rs.getInt("CLIENT_COPIAS");
			tipoCliente = rs.getInt("CLIENT_TIPO_CLTE");
			tipoFacturacion = rs.getInt("CLIENT_TIPO_FACTURAC");
			nivelConflicto = rs.getInt("CLIENT_NIVEL_CONFLICTO");
			tipoTarifa = rs.getInt("CLIENT_TIPO_TARIFA");
			envioTarifa = rs.getInt("CLIENT_ENVIO_TARIFA");
			ruta = rs.getInt("CLIENT_RUTA");
			numeroBanco = rs.getInt("CLIENT_NRO_BCO");
			numeroSucursal = rs.getInt("CLIENT_NRO_SUC");
			digitosControl = rs.getInt("CLIENT_DC");
			cuenta = rs.getLong("CLIENT_CTA");
			bancoNegociacion = rs.getInt("CLIENT_BANCO_NEGOCIACION");
			numeroEfectos = rs.getInt("CLIENT_NRO_EFECTOS");
			intervalo = rs.getInt("CLIENT_INTERVALO");
			primerVencimiento = rs.getInt("CLIENT_PRIMER_VTO");
			seNegocia = rs.getInt("CLIENT_SE_NEGOCIA");
			diaPago1 = rs.getInt("CLIENT_DIA_PAGO1");
			diaPago2 = rs.getInt("CLIENT_DIA_PAGO2");
			diaPago3 = rs.getInt("CLIENT_DIA_PAGO3");
			activo = rs.getInt("CLIENT_ACTIVO");
			sexo = rs.getString("CLIENT_SEXO").trim();
			noMailing = rs.getInt("CLIENT_NO_MAILING");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ClienteTienda!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CLIENT " +
						   "(EMPRESA, " +
						   "CLIENT_CLIENTE, " +
						   "CLIENT_CENTRO, " +
						   "CLIENT_APELRAZON, " +
						   "CLIENT_NOMBRE, " +
						   "CLIENT_OBSERVAC, " +
						   "CLIENT_DIRATT, " +
						   "CLIENT_DIRclites, " +
						   "CLIENT_PAIS, " +
						   "CLIENT_CODPOB, " +
						   "CLIENT_ORDENCPB, " +
						   "CLIENT_CALLE, " +
						   "CLIENT_PORTAL, " +
						   "CLIENT_ESCALBLOQKM, " +
						   "CLIENT_PISO, " +
						   "CLIENT_PUERTA, " +
						   "CLIENT_CP, " +
						   "CLIENT_CONTACTO, " +
						   "CLIENT_TLFNO, " +
						   "CLIENT_TLFNO2, " +
						   "CLIENT_NIF, " +
						   "CLIENT_EMAIL, " +
						   "CLIENT_NIK_ALIAS, " +
						   "CLIENT_PASSWORD, " +
						   "CLIENT_FEC_NACIMIENTO, " +
						   "CLIENT_SOMOS_SU_PROV, " +
						   "CLIENT_FEC_ULT_FRA, " +
						   "CLIENT_RIES_TOT, " +
						   "CLIENT_LIM_CRE, " +
						   "CLIENT_VAL_ALB_PDT_FAC, " +
						   "CLIENT_ABONOS_PENDTES, " +
						   "CLIENT_ZONA, " +
						   "CLIENT_TIPO_IVA, " +
						   "CLIENT_REPRE, " +
						   "CLIENT_DIR2, " +
						   "CLIENT_DTO_CIAL, " +
						   "CLIENT_DTO_PP, " +
						   "CLIENT_COPIAS, " +
						   "CLIENT_TIPO_CLTE, " +
						   "CLIENT_TIPO_FACTURAC, " +
						   "CLIENT_NIVEL_CONFLICTO, " +
						   "CLIENT_TIPO_TARIFA, " +
						   "CLIENT_ENVIO_TARIFA, " +
						   "CLIENT_RUTA, " +
						   "CLIENT_NRO_BCO, " +
						   "CLIENT_NRO_SUC, " +
						   "CLIENT_DC, " +
						   "CLIENT_CTA, " +
						   "CLIENT_BANCO_NEGOCIACION, " +
						   "CLIENT_NRO_EFECTOS, " +
						   "CLIENT_INTERVALO, " +
						   "CLIENT_PRIMER_VTO, " +
						   "CLIENT_SE_NEGOCIA, " +
						   "CLIENT_DIA_PAGO1, " +
						   "CLIENT_DIA_PAGO2, " +
						   "CLIENT_DIA_PAGO3, " +
						   "CLIENT_ACTIVO, " +
						   "CLIENT_SEXO, " +
						   "CLIENT_NO_MAILING) " +		   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?) " +				                   
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CLIENT_CLIENTE = ?, " +
						   "CLIENT_CENTRO = ?, " +
						   "CLIENT_APELRAZON = ?, " +
						   "CLIENT_NOMBRE = ?, " +
						   "CLIENT_OBSERVAC = ?, " +
						   "CLIENT_DIRATT = ?, " +
						   "CLIENT_DIRclites = ?, " +
						   "CLIENT_PAIS = ?, " +
						   "CLIENT_CODPOB = ?, " +
						   "CLIENT_ORDENCPB = ?, " +
						   "CLIENT_CALLE = ?, " +
						   "CLIENT_PORTAL = ?, " +
						   "CLIENT_ESCALBLOQKM = ?, " +
						   "CLIENT_PISO = ?, " +
						   "CLIENT_PUERTA = ?, " +
						   "CLIENT_CP = ?, " +
						   "CLIENT_CONTACTO = ?, " +
						   "CLIENT_TLFNO = ?, " +
						   "CLIENT_TLFNO2 = ?, " +
						   "CLIENT_NIF = ?, " +
						   "CLIENT_EMAIL = ?, " +
						   "CLIENT_NIK_ALIAS = ?, " +
						   "CLIENT_PASSWORD = ?, " +
						   "CLIENT_FEC_NACIMIENTO = ?, " +
						   "CLIENT_SOMOS_SU_PROV = ?, " +
						   "CLIENT_FEC_ULT_FRA = ?, " +
						   "CLIENT_RIES_TOT = ?, " +
						   "CLIENT_LIM_CRE = ?, " +
						   "CLIENT_VAL_ALB_PDT_FAC = ?, " +
						   "CLIENT_ABONOS_PENDTES = ?, " +
						   "CLIENT_ZONA = ?, " +
						   "CLIENT_TIPO_IVA = ?, " +
						   "CLIENT_REPRE = ?, " +
						   "CLIENT_DIR2 = ?, " +
						   "CLIENT_DTO_CIAL = ?, " +
						   "CLIENT_DTO_PP = ?, " +
						   "CLIENT_COPIAS = ?, " +
						   "CLIENT_TIPO_CLTE = ?, " +
						   "CLIENT_TIPO_FACTURAC = ?, " +
						   "CLIENT_NIVEL_CONFLICTO = ?, " +
						   "CLIENT_TIPO_TARIFA = ?, " +
						   "CLIENT_ENVIO_TARIFA = ?, " +
						   "CLIENT_RUTA = ?, " +
						   "CLIENT_NRO_BCO = ?, " +
						   "CLIENT_NRO_SUC = ?, " +
						   "CLIENT_DC = ?, " +
						   "CLIENT_CTA = ?, " +
						   "CLIENT_BANCO_NEGOCIACION = ?, " +
						   "CLIENT_NRO_EFECTOS = ?, " +
						   "CLIENT_INTERVALO = ?, " +
						   "CLIENT_PRIMER_VTO = ?, " +
						   "CLIENT_SE_NEGOCIA = ?, " +
						   "CLIENT_DIA_PAGO1 = ?, " +
						   "CLIENT_DIA_PAGO2 = ?, " +
						   "CLIENT_DIA_PAGO3 = ?, " +
						   "CLIENT_ACTIVO = ?, " +
						   "CLIENT_SEXO = ?, " +
						   "CLIENT_NO_MAILING = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazonSoc, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccionClites, 30));
			ps.setInt(i++, pais);
			ps.setInt(i++, codigoPoblacion);
			ps.setInt(i++, ordenCodigoPoblacion);
			ps.setInt(i++, calle);
			ps.setInt(i++, portal);
			ps.setString(i++, Cadena.left(escaleraBloqueKm, 20));
			ps.setInt(i++, piso);
			ps.setString(i++, Cadena.left(puerta, 3));
			ps.setInt(i++, codigoPostal);
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(telefono2, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(email, 50));
			ps.setString(i++, Cadena.left(nickAlias, 16));
			ps.setString(i++, Cadena.left(password, 16));
			ps.setInt(i++, fechaNaciomiento);
			ps.setString(i++, Cadena.left(somosSuProveedorNro, 16));
			ps.setInt(i++, fechaUltimafactura);
			ps.setDouble(i++, riesgoTotal);
			ps.setDouble(i++, limiteCredito);
			ps.setDouble(i++, valorAlbaranesPendienteFact);
			ps.setDouble(i++, abonosPendientes);
			ps.setInt(i++, zona);
			ps.setInt(i++, tipoIva);
			ps.setInt(i++, representante);
			ps.setInt(i++, direccion2);
			ps.setDouble(i++, descuentoComercial);
			ps.setDouble(i++, descuentoProntoPago);
			ps.setInt(i++, copias);
			ps.setInt(i++, tipoCliente);
			ps.setInt(i++, tipoFacturacion);
			ps.setInt(i++, nivelConflicto);
			ps.setInt(i++, tipoTarifa);
			ps.setInt(i++, envioTarifa);
			ps.setInt(i++, ruta);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitosControl);
			ps.setLong(i++, cuenta);
			ps.setInt(i++, bancoNegociacion);
			ps.setInt(i++, numeroEfectos);
			ps.setInt(i++, intervalo);
			ps.setInt(i++, primerVencimiento);
			ps.setInt(i++, seNegocia);
			ps.setInt(i++, diaPago1);
			ps.setInt(i++, diaPago2);
			ps.setInt(i++, diaPago3);
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(sexo, 1));
			ps.setInt(i++, noMailing);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazonSoc, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccionClites, 30));
			ps.setInt(i++, pais);
			ps.setInt(i++, codigoPoblacion);
			ps.setInt(i++, ordenCodigoPoblacion);
			ps.setInt(i++, calle);
			ps.setInt(i++, portal);
			ps.setString(i++, Cadena.left(escaleraBloqueKm, 20));
			ps.setInt(i++, piso);
			ps.setString(i++, Cadena.left(puerta, 3));
			ps.setInt(i++, codigoPostal);
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(telefono2, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(email, 50));
			ps.setString(i++, Cadena.left(nickAlias, 16));
			ps.setString(i++, Cadena.left(password, 16));
			ps.setInt(i++, fechaNaciomiento);
			ps.setString(i++, Cadena.left(somosSuProveedorNro, 16));
			ps.setInt(i++, fechaUltimafactura);
			ps.setDouble(i++, riesgoTotal);
			ps.setDouble(i++, limiteCredito);
			ps.setDouble(i++, valorAlbaranesPendienteFact);
			ps.setDouble(i++, abonosPendientes);
			ps.setInt(i++, zona);
			ps.setInt(i++, tipoIva);
			ps.setInt(i++, representante);
			ps.setInt(i++, direccion2);
			ps.setDouble(i++, descuentoComercial);
			ps.setDouble(i++, descuentoProntoPago);
			ps.setInt(i++, copias);
			ps.setInt(i++, tipoCliente);
			ps.setInt(i++, tipoFacturacion);
			ps.setInt(i++, nivelConflicto);
			ps.setInt(i++, tipoTarifa);
			ps.setInt(i++, envioTarifa);
			ps.setInt(i++, ruta);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitosControl);
			ps.setLong(i++, cuenta);
			ps.setInt(i++, bancoNegociacion);
			ps.setInt(i++, numeroEfectos);
			ps.setInt(i++, intervalo);
			ps.setInt(i++, primerVencimiento);
			ps.setInt(i++, seNegocia);
			ps.setInt(i++, diaPago1);
			ps.setInt(i++, diaPago2);
			ps.setInt(i++, diaPago3);
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(sexo, 1));
			ps.setInt(i++, noMailing);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ClienteTienda!!!");
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

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public String getApellidosRazonSoc() {
		return apellidosRazonSoc;
	}

	public void setApellidosRazonSoc(String apellidosRazonSoc) {
		this.apellidosRazonSoc = apellidosRazonSoc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDireccionAtencion() {
		return direccionAtencion;
	}

	public void setDireccionAtencion(String direccionAtencion) {
		this.direccionAtencion = direccionAtencion;
	}

	public String getDireccionClites() {
		return direccionClites;
	}

	public void setDireccionClites(String direccionClites) {
		this.direccionClites = direccionClites;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public int getCodigoPoblacion() {
		return codigoPoblacion;
	}

	public void setCodigoPoblacion(int codigoPoblacion) {
		this.codigoPoblacion = codigoPoblacion;
	}

	public int getOrdenCodigoPoblacion() {
		return ordenCodigoPoblacion;
	}

	public void setOrdenCodigoPoblacion(int ordenCodigoPoblacion) {
		this.ordenCodigoPoblacion = ordenCodigoPoblacion;
	}

	public int getCalle() {
		return calle;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public int getPortal() {
		return portal;
	}

	public void setPortal(int portal) {
		this.portal = portal;
	}

	public String getEscaleraBloqueKm() {
		return escaleraBloqueKm;
	}

	public void setEscaleraBloqueKm(String escaleraBloqueKm) {
		this.escaleraBloqueKm = escaleraBloqueKm;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
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

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
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

	public String getNickAlias() {
		return nickAlias;
	}

	public void setNickAlias(String nickAlias) {
		this.nickAlias = nickAlias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFechaNaciomiento() {
		return fechaNaciomiento;
	}

	public void setFechaNaciomiento(int fechaNaciomiento) {
		this.fechaNaciomiento = fechaNaciomiento;
	}

	public String getSomosSuProveedorNro() {
		return somosSuProveedorNro;
	}

	public void setSomosSuProveedorNro(String somosSuProveedorNro) {
		this.somosSuProveedorNro = somosSuProveedorNro;
	}

	public int getFechaUltimafactura() {
		return fechaUltimafactura;
	}

	public void setFechaUltimafactura(int fechaUltimafactura) {
		this.fechaUltimafactura = fechaUltimafactura;
	}

	public double getRiesgoTotal() {
		return riesgoTotal;
	}

	public void setRiesgoTotal(double riesgoTotal) {
		this.riesgoTotal = riesgoTotal;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public double getValorAlbaranesPendienteFact() {
		return valorAlbaranesPendienteFact;
	}

	public void setValorAlbaranesPendienteFact(double valorAlbaranesPendienteFact) {
		this.valorAlbaranesPendienteFact = valorAlbaranesPendienteFact;
	}

	public double getAbonosPendientes() {
		return abonosPendientes;
	}

	public void setAbonosPendientes(double abonosPendientes) {
		this.abonosPendientes = abonosPendientes;
	}

	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public int getTipoIva() {
		return tipoIva;
	}

	public void setTipoIva(int tipoIva) {
		this.tipoIva = tipoIva;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
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

	public double getDescuentoProntoPago() {
		return descuentoProntoPago;
	}

	public void setDescuentoProntoPago(double descuentoProntoPago) {
		this.descuentoProntoPago = descuentoProntoPago;
	}

	public int getCopias() {
		return copias;
	}

	public void setCopias(int copias) {
		this.copias = copias;
	}

	public int getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
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

	public int getTipoTarifa() {
		return tipoTarifa;
	}

	public void setTipoTarifa(int tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	public int getEnvioTarifa() {
		return envioTarifa;
	}

	public void setEnvioTarifa(int envioTarifa) {
		this.envioTarifa = envioTarifa;
	}

	public int getRuta() {
		return ruta;
	}

	public void setRuta(int ruta) {
		this.ruta = ruta;
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

	public int getBancoNegociacion() {
		return bancoNegociacion;
	}

	public void setBancoNegociacion(int bancoNegociacion) {
		this.bancoNegociacion = bancoNegociacion;
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

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getNoMailing() {
		return noMailing;
	}

	public void setNoMailing(int noMailing) {
		this.noMailing = noMailing;
	}
}
