package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;




public class ClienteContable {
	private String empresa;
	private int cliente;
	private int centro;
	private String apellidosRazonSocial;
	private String nombre;
	private String segundoNombre;
	private String direccionAtencion;
	private String direccion;
	private String poblacion;
	private int pais;
	private int provincia;
	private int codigoPostal;
	private String fillCp;
	private String contacto;
	private String telefono;
	private String fax;
	private String nif;
	private String email;
	private String nickAlias;
	private String password;
	private int fechaNacimiento;
	private String somosSuProveedorNro;
	private int fechaUltimaFactura;
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
	private int facturaPorEmail;
	
	public ClienteContable(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		centro = 0;
		apellidosRazonSocial = "";
		nombre = "";
		segundoNombre = "";
		direccionAtencion = "";
		direccion = "";
		poblacion = "";
		pais = 0;
		provincia = 0;
		codigoPostal = 0;
		fillCp = "";
		contacto = "";
		telefono = "";
		fax = "";
		nif = "";
		email = "";
		nickAlias = "";
		password = "";
		fechaNacimiento = 0;
		somosSuProveedorNro = "";
		fechaUltimaFactura = 0;
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
		facturaPorEmail = 0;
	}
	
	public ClienteContable(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("CLITES_CLIENTE");
				centro = rs.getInt("CLITES_CENTRO");
				apellidosRazonSocial = rs.getString("CLITES_APELRAZON").trim();
				nombre = rs.getString("CLITES_NOMBRE").trim();
				segundoNombre = rs.getString("CLITES_2DO_NOMBR").trim();
				direccionAtencion = rs.getString("CLITES_DIRATT").trim();
				direccion = rs.getString("CLITES_DIR").trim();
				poblacion = rs.getString("CLITES_POB").trim();
				pais = rs.getInt("CLITES_PAIS");
				provincia = rs.getInt("CLITES_PROV");
				codigoPostal = rs.getInt("CLITES_CP");
				fillCp = rs.getString("CLITES_FILLCP").trim();
				contacto = rs.getString("CLITES_CONTACTO").trim();
				telefono = rs.getString("CLITES_TLFNO").trim();
				fax = rs.getString("CLITES_FAX").trim();
				nif = rs.getString("CLITES_NIF").trim();
				email = rs.getString("CLITES_EMAIL").trim();
				nickAlias = rs.getString("CLITES_NIK_ALIAS").trim();
				password = rs.getString("CLITES_PASSWORD").trim();
				fechaNacimiento = rs.getInt("CLITES_FEC_NACIMIENTO");
				somosSuProveedorNro = rs.getString("CLITES_SOMOS_SU_PROV").trim();
				fechaUltimaFactura = rs.getInt("CLITES_FEC_ULT_FRA");
				riesgoTotal = rs.getDouble("CLITES_RIES_TOT");
				limiteCredito = rs.getDouble("CLITES_LIM_CRE");
				valorAlbaranesPendienteFact = rs.getDouble("CLITES_VAL_ALB_PDT_FAC");
				abonosPendientes = rs.getDouble("CLITES_ABONOS_PENDTES");
				zona = rs.getInt("CLITES_ZONA");
				tipoIva = rs.getInt("CLITES_TIPO_IVA");
				representante = rs.getInt("CLITES_REPRE");
				direccion2 = rs.getInt("CLITES_DIR2");
				descuentoComercial = rs.getDouble("CLITES_DTO_CIAL");
				descuentoProntoPago = rs.getDouble("CLITES_DTO_PP");
				copias = rs.getInt("CLITES_COPIAS");
				tipoCliente = rs.getInt("CLITES_TIPO_CLTE");
				tipoFacturacion = rs.getInt("CLITES_TIPO_FACTURAC");
				nivelConflicto = rs.getInt("CLITES_NIVEL_CONFLICTO");
				tipoTarifa = rs.getInt("CLITES_TIPO_TARIFA");
				envioTarifa = rs.getInt("CLITES_ENVIO_TARIFA");
				ruta = rs.getInt("CLITES_RUTA");
				numeroBanco = rs.getInt("CLITES_NRO_BCO");
				numeroSucursal = rs.getInt("CLITES_NRO_SUC");
				digitosControl = rs.getInt("CLITES_DC");
				cuenta = rs.getLong("CLITES_CTA");
				bancoNegociacion = rs.getInt("CLITES_BANCO_NEGOCIACION");
				numeroEfectos = rs.getInt("CLITES_NRO_EFECTOS");
				intervalo = rs.getInt("CLITES_INTERVALO");
				primerVencimiento = rs.getInt("CLITES_PRIMER_VTO");
				seNegocia = rs.getInt("CLITES_SE_NEGOCIA");
				diaPago1 = rs.getInt("CLITES_DIA_PAGO1");
				diaPago2 = rs.getInt("CLITES_DIA_PAGO2");
				diaPago3 = rs.getInt("CLITES_DIA_PAGO3");
				activo = rs.getInt("CLITES_ACTIVO");
				sexo = rs.getString("CLITES_SEXO");
				noMailing = rs.getInt("CLITES_NO_MAILING");
				facturaPorEmail = rs.getInt("CLITES_FRA_POR_EMAIL");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de ClienteContable!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("CLITES_CLIENTE");
				centro = rs.getInt("CLITES_CENTRO");
				apellidosRazonSocial = rs.getString("CLITES_APELRAZON").trim();
				nombre = rs.getString("CLITES_NOMBRE").trim();
				segundoNombre = rs.getString("CLITES_2DO_NOMBR").trim();
				direccionAtencion = rs.getString("CLITES_DIRATT").trim();
				direccion = rs.getString("CLITES_DIR").trim();
				poblacion = rs.getString("CLITES_POB").trim();
				pais = rs.getInt("CLITES_PAIS");
				provincia = rs.getInt("CLITES_PROV");
				codigoPostal = rs.getInt("CLITES_CP");
				fillCp = rs.getString("CLITES_FILLCP").trim();
				contacto = rs.getString("CLITES_CONTACTO").trim();
				telefono = rs.getString("CLITES_TLFNO").trim();
				fax = rs.getString("CLITES_FAX").trim();
				nif = rs.getString("CLITES_NIF").trim();
				email = rs.getString("CLITES_EMAIL").trim();
				nickAlias = rs.getString("CLITES_NIK_ALIAS").trim();
				password = rs.getString("CLITES_PASSWORD").trim();
				fechaNacimiento = rs.getInt("CLITES_FEC_NACIMIENTO");
				somosSuProveedorNro = rs.getString("CLITES_SOMOS_SU_PROV").trim();
				fechaUltimaFactura = rs.getInt("CLITES_FEC_ULT_FRA");
				riesgoTotal = rs.getDouble("CLITES_RIES_TOT");
				limiteCredito = rs.getDouble("CLITES_LIM_CRE");
				valorAlbaranesPendienteFact = rs.getDouble("CLITES_VAL_ALB_PDT_FAC");
				abonosPendientes = rs.getDouble("CLITES_ABONOS_PENDTES");
				zona = rs.getInt("CLITES_ZONA");
				tipoIva = rs.getInt("CLITES_TIPO_IVA");
				representante = rs.getInt("CLITES_REPRE");
				direccion2 = rs.getInt("CLITES_DIR2");
				descuentoComercial = rs.getDouble("CLITES_DTO_CIAL");
				descuentoProntoPago = rs.getDouble("CLITES_DTO_PP");
				copias = rs.getInt("CLITES_COPIAS");
				tipoCliente = rs.getInt("CLITES_TIPO_CLTE");
				tipoFacturacion = rs.getInt("CLITES_TIPO_FACTURAC");
				nivelConflicto = rs.getInt("CLITES_NIVEL_CONFLICTO");
				tipoTarifa = rs.getInt("CLITES_TIPO_TARIFA");
				envioTarifa = rs.getInt("CLITES_ENVIO_TARIFA");
				ruta = rs.getInt("CLITES_RUTA");
				numeroBanco = rs.getInt("CLITES_NRO_BCO");
				numeroSucursal = rs.getInt("CLITES_NRO_SUC");
				digitosControl = rs.getInt("CLITES_DC");
				cuenta = rs.getLong("CLITES_CTA");
				bancoNegociacion = rs.getInt("CLITES_BANCO_NEGOCIACION");
				numeroEfectos = rs.getInt("CLITES_NRO_EFECTOS");
				intervalo = rs.getInt("CLITES_INTERVALO");
				primerVencimiento = rs.getInt("CLITES_PRIMER_VTO");
				seNegocia = rs.getInt("CLITES_SE_NEGOCIA");
				diaPago1 = rs.getInt("CLITES_DIA_PAGO1");
				diaPago2 = rs.getInt("CLITES_DIA_PAGO2");
				diaPago3 = rs.getInt("CLITES_DIA_PAGO3");
				activo = rs.getInt("CLITES_ACTIVO");
				sexo = rs.getString("CLITES_SEXO").trim();
				noMailing = rs.getInt("CLITES_NO_MAILING");
				facturaPorEmail = rs.getInt("CLITES_FRA_POR_EMAIL");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de ClienteContable!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public boolean write(){
		boolean escrituraCorrecta = true;
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CLITES " +
						   "(EMPRESA, " +
						   "CLITES_CLIENTE, " +
						   "CLITES_CENTRO, " +
						   "CLITES_APELRAZON, " +
						   "CLITES_NOMBRE, " +
						   "CLITES_2DO_NOMBR, " +
						   "CLITES_DIRATT, " +
						   "CLITES_DIR, " +
						   "CLITES_POB, " +
						   "CLITES_PAIS, " +
						   "CLITES_PROV, " +
						   "CLITES_CP, " +
						   "CLITES_FILLCP, " +
						   "CLITES_CONTACTO, " +
						   "CLITES_TLFNO, " +
						   "CLITES_FAX, " +
						   "CLITES_NIF, " +
						   "CLITES_EMAIL, " +
						   "CLITES_NIK_ALIAS, " +
						   "CLITES_PASSWORD, " +
						   "CLITES_FEC_NACIMIENTO, " +
						   "CLITES_SOMOS_SU_PROV, " +
						   "CLITES_FEC_ULT_FRA, " +
						   "CLITES_RIES_TOT, " +
						   "CLITES_LIM_CRE, " +
						   "CLITES_VAL_ALB_PDT_FAC, " +
						   "CLITES_ABONOS_PENDTES, " +
						   "CLITES_ZONA, " +
						   "CLITES_TIPO_IVA, " +
						   "CLITES_REPRE, " +
						   "CLITES_DIR2, " +
						   "CLITES_DTO_CIAL, " +
						   "CLITES_DTO_PP, " +
						   "CLITES_COPIAS, " +
						   "CLITES_TIPO_CLTE, " +
						   "CLITES_TIPO_FACTURAC, " +
						   "CLITES_NIVEL_CONFLICTO, " +
						   "CLITES_TIPO_TARIFA, " +
						   "CLITES_ENVIO_TARIFA, " +
						   "CLITES_RUTA, " +
						   "CLITES_NRO_BCO, " +
						   "CLITES_NRO_SUC, " +
						   "CLITES_DC, " +
						   "CLITES_CTA, " +
						   "CLITES_BANCO_NEGOCIACION, " +
						   "CLITES_NRO_EFECTOS, " +
						   "CLITES_INTERVALO, " +
						   "CLITES_PRIMER_VTO, " +
						   "CLITES_SE_NEGOCIA, " +
						   "CLITES_DIA_PAGO1, " +
						   "CLITES_DIA_PAGO2, " +
						   "CLITES_DIA_PAGO3, " +
						   "CLITES_ACTIVO, " +
						   "CLITES_SEXO, " +
						   "CLITES_NO_MAILING, " +						 
						   "CLITES_FRA_POR_EMAIL) " +						   
						   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
		                           "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
		                           "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
		                           "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
		                           "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
		                           "?, ?, ?, ?, ?, ?) " +		
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CLITES_CLIENTE = ?, " +
						   "CLITES_CENTRO = ?, " +
						   "CLITES_APELRAZON = ?, " +
						   "CLITES_NOMBRE = ?, " +
						   "CLITES_2DO_NOMBR = ?, " +
						   "CLITES_DIRATT = ?, " +
						   "CLITES_DIR = ?, " +
						   "CLITES_POB = ?, " +
						   "CLITES_PAIS = ?, " +
						   "CLITES_PROV = ?, " +
						   "CLITES_CP = ?, " +
						   "CLITES_FILLCP = ?, " +
						   "CLITES_CONTACTO = ?, " +
						   "CLITES_TLFNO = ?, " +
						   "CLITES_FAX = ?, " +
						   "CLITES_NIF = ?, " +
						   "CLITES_EMAIL = ?, " +
						   "CLITES_NIK_ALIAS = ?, " +
						   "CLITES_PASSWORD = ?, " +
						   "CLITES_FEC_NACIMIENTO = ?, " +
						   "CLITES_SOMOS_SU_PROV = ?, " +
						   "CLITES_FEC_ULT_FRA = ?, " +
						   "CLITES_RIES_TOT = ?, " +
						   "CLITES_LIM_CRE = ?, " +
						   "CLITES_VAL_ALB_PDT_FAC = ?, " +
						   "CLITES_ABONOS_PENDTES = ?, " +
						   "CLITES_ZONA = ?, " +
						   "CLITES_TIPO_IVA = ?, " +
						   "CLITES_REPRE = ?, " +
						   "CLITES_DIR2 = ?, " +
						   "CLITES_DTO_CIAL = ?, " +
						   "CLITES_DTO_PP = ?, " +
						   "CLITES_COPIAS = ?, " +
						   "CLITES_TIPO_CLTE = ?, " +
						   "CLITES_TIPO_FACTURAC = ?, " +
						   "CLITES_NIVEL_CONFLICTO = ?, " +
						   "CLITES_TIPO_TARIFA = ?, " +
						   "CLITES_ENVIO_TARIFA = ?, " +
						   "CLITES_RUTA = ?, " +
						   "CLITES_NRO_BCO = ?, " +
						   "CLITES_NRO_SUC = ?, " +
						   "CLITES_DC = ?, " +
						   "CLITES_CTA = ?, " +
						   "CLITES_BANCO_NEGOCIACION = ?, " +
						   "CLITES_NRO_EFECTOS = ?, " +
						   "CLITES_INTERVALO = ?," +
						   "CLITES_PRIMER_VTO = ?, " +
						   "CLITES_SE_NEGOCIA = ?, " +
						   "CLITES_DIA_PAGO1 = ?, " +
						   "CLITES_DIA_PAGO2 = ?, " +
						   "CLITES_DIA_PAGO3 = ?, " +
						   "CLITES_ACTIVO = ?, " +
						   "CLITES_SEXO = ?, " +
						   "CLITES_NO_MAILING = ?, " +						 
						   "CLITES_FRA_POR_EMAIL = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazonSocial, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(segundoNombre, 30));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 20));
			ps.setInt(i++, pais);
			ps.setInt(i++, provincia);
			ps.setInt(i++, codigoPostal);
			ps.setString(i++, Cadena.left(fillCp, 22));
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(email, 50));
			ps.setString(i++, Cadena.left(nickAlias, 16));
			ps.setString(i++, Cadena.left(password, 16));
			ps.setInt(i++, fechaNacimiento);
			ps.setString(i++, Cadena.left(somosSuProveedorNro, 16));
			ps.setInt(i++, fechaUltimaFactura);
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
			ps.setInt(i++, facturaPorEmail);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, cliente);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazonSocial, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(segundoNombre, 30));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 20));
			ps.setInt(i++, pais);
			ps.setInt(i++, provincia);
			ps.setInt(i++, codigoPostal);
			ps.setString(i++, Cadena.left(fillCp, 22));
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(email, 50));
			ps.setString(i++, Cadena.left(nickAlias, 16));
			ps.setString(i++, Cadena.left(password, 16));
			ps.setInt(i++, fechaNacimiento);
			ps.setString(i++, Cadena.left(somosSuProveedorNro, 16));
			ps.setInt(i++, fechaUltimaFactura);
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
			ps.setInt(i++, facturaPorEmail);
			
			ps.execute();
			
		} catch (SQLException e) {
			escrituraCorrecta = false;
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ClienteContable!!!");
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

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
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

	public String getFillCp() {
		return fillCp;
	}

	public void setFillCp(String fillCp) {
		this.fillCp = fillCp;
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

	public int getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(int fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSomosSuProveedorNro() {
		return somosSuProveedorNro;
	}

	public void setSomosSuProveedorNro(String somosSuProveedorNro) {
		this.somosSuProveedorNro = somosSuProveedorNro;
	}

	public int getFechaUltimaFactura() {
		return fechaUltimaFactura;
	}

	public void setFechaUltimaFactura(int fechaUltimaFactura) {
		this.fechaUltimaFactura = fechaUltimaFactura;
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

	public int getFacturaPorEmail() {
		return facturaPorEmail;
	}

	public void setFacturaPorEmail(int facturaPorEmail) {
		this.facturaPorEmail = facturaPorEmail;
	}
}
