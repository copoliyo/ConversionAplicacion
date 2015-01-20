package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class Banco {
	private String empresa;
	private int banco;
	private int centro;
	private int numeroBanco;
	private int numeroSucursal;
	private int digitoControl;
	private long cuenta;
	private String contacto;
	private String telefono;
	private String fax;
	private String email;
	private String cedente;
	private double concedido;
	private double totalRiesgo;
	private int diasLiberacion;
	private int diasAvisoImpagado;
	private double negociadoAño;
	private int efectos;
	private int remesas;
	private double gastosNegociacion;
	private double impagados;
	private int numeroImpagados;
	private double gastosImpagados;
	private String sufijo;
	private String nombreCsb19;
	private String otroOrdenante;
	private String numeroCliente;
	private String codigoInePlazemis;

	public Banco(){
		empresa = DatosComunes.eEmpresa; 
		banco = 0;
		centro = DatosComunes.centroCont;
		numeroBanco = 0;
		numeroSucursal = 0;
		digitoControl = 0;
		cuenta = 0L;
		contacto = "";
		telefono = "";
		fax = "";
		email = "";
		cedente = "";
		concedido = 0.0;
		totalRiesgo = 0.0;
		diasLiberacion = 0;
		diasAvisoImpagado = 0;
		negociadoAño = 0.0;
		efectos = 0;
		remesas = 0;
		gastosNegociacion = 0.0;
		impagados = 0.0;
		numeroImpagados = 0;
		gastosImpagados = 0.0;
		sufijo = "";
		nombreCsb19 = "";
		otroOrdenante = "";
		numeroCliente = "";
		codigoInePlazemis = "";
	}

	public Banco(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				banco = rs.getInt("BANCOS_BANCO");
				centro = rs.getInt("BANCOS_CENTRO");
				numeroBanco = rs.getInt("BANCOS_NRO_BCO");
				numeroSucursal = rs.getInt("BANCOS_NRO_SUC");
				digitoControl = rs.getInt("BANCOS_DC");
				cuenta = rs.getLong("BANCOS_CTA");
				contacto = rs.getString("BANCOS_CONTACTO").trim();
				telefono = rs.getString("BANCOS_TLFNO").trim();
				fax = rs.getString("BANCOS_FAX").trim();
				email = rs.getString("BANCOS_EMAIL").trim();
				cedente = rs.getString("BANCOS_CEDENTE").trim();
				concedido = rs.getDouble("BANCOS_CONCEDIDO");
				totalRiesgo = rs.getDouble("BANCOS_TOT_RIESGO");
				diasLiberacion = rs.getInt("BANCOS_DIAS_LIBERACION");
				diasAvisoImpagado = rs.getInt("BANCOS_DIAS_AVISOIMPAG");
				negociadoAño = rs.getDouble("BANCOS_NEGOCIADO_ANY");
				efectos = rs.getInt("BANCOS_EFECTOS");
				remesas = rs.getInt("BANCOS_REMESAS");
				gastosNegociacion = rs.getDouble("BANCOS_GASTOS_NEGOC");
				impagados = rs.getDouble("BANCOS_IMPAGADOS");
				numeroImpagados = rs.getInt("BANCOS_NRO_IMPAGADOS");
				gastosImpagados = rs.getDouble("BANCOS_GASTOS_IMPAGADOS");
				sufijo = rs.getString("BANCOS_SUFIJO");
				nombreCsb19 = rs.getString("BANCOS_NOMBRE_CSB19").trim();
				otroOrdenante = rs.getString("BANCOS_OTRO_ORDENANTE").trim();
				numeroCliente = rs.getString("BANCOS_NRO_CLIENTE").trim();
				codigoInePlazemis = rs.getString("BANCOS_COD_INE_PLAZEMIS").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Banco!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			banco = rs.getInt("BANCOS_BANCO");
			centro = rs.getInt("BANCOS_CENTRO");
			numeroBanco = rs.getInt("BANCOS_NRO_BCO");
			numeroSucursal = rs.getInt("BANCOS_NRO_SUC");
			digitoControl = rs.getInt("BANCOS_DC");
			cuenta = rs.getLong("BANCOS_CTA");
			contacto = rs.getString("BANCOS_CONTACTO").trim();
			telefono = rs.getString("BANCOS_TLFNO").trim();
			fax = rs.getString("BANCOS_FAX").trim();
			email = rs.getString("BANCOS_EMAIL").trim();
			cedente = rs.getString("BANCOS_CEDENTE").trim();
			concedido = rs.getDouble("BANCOS_CONCEDIDO");
			totalRiesgo = rs.getDouble("BANCOS_TOT_RIESGO");
			diasLiberacion = rs.getInt("BANCOS_DIAS_LIBERACION");
			diasAvisoImpagado = rs.getInt("BANCOS_DIAS_AVISOIMPAG");
			negociadoAño = rs.getDouble("BANCOS_NEGOCIADO_ANY");
			efectos = rs.getInt("BANCOS_EFECTOS");
			remesas = rs.getInt("BANCOS_REMESAS");
			gastosNegociacion = rs.getDouble("BANCOS_GASTOS_NEGOC");
			impagados = rs.getDouble("BANCOS_IMPAGADOS");
			numeroImpagados = rs.getInt("BANCOS_NRO_IMPAGADOS");
			gastosImpagados = rs.getDouble("BANCOS_GASTOS_IMPAGADOS");
			sufijo = rs.getString("BANCOS_SUFIJO").trim();
			nombreCsb19 = rs.getString("BANCOS_NOMBRE_CSB19").trim();
			otroOrdenante = rs.getString("BANCOS_OTRO_ORDENANTE").trim();
			numeroCliente = rs.getString("BANCOS_NRO_CLIENTE").trim();
			codigoInePlazemis = rs.getString("BANCOS_COD_INE_PLAZEMIS").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Banco!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public boolean write(){
		
		boolean escrituraCorrecta = true;
		
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO BANCOS " +
		   				   "(EMPRESA, " +
		   				   "BANCOS_BANCO, " +
		   				   "BANCOS_CENTRO, " +
		   				   "BANCOS_NRO_BCO, " +
		   				   "BANCOS_NRO_SUC, " +
		   				   "BANCOS_DC, " +
		   				   "BANCOS_CTA, " +
		   				   "BANCOS_CONTACTO, " +
		   				   "BANCOS_TLFNO, " +
		   				   "BANCOS_FAX, " +
		   				   "BANCOS_EMAIL, " +
		   				   "BANCOS_CEDENTE, " +
		   				   "BANCOS_CONCEDIDO, " +
		   				   "BANCOS_TOT_RIESGO, " +
		   				   "BANCOS_DIAS_LIBERACION, " +
		   				   "BANCOS_DIAS_AVISOIMPAG, " +
		   				   "BANCOS_NEGOCIADO_ANY, " +
		   				   "BANCOS_EFECTOS, " +
		   				   "BANCOS_REMESAS, " +
		   				   "BANCOS_GASTOS_NEGOC, " +
		   				   "BANCOS_IMPAGADOS, " +
		   				   "BANCOS_NRO_IMPAGADOS, " +
		   				   "BANCOS_GASTOS_IMPAGADOS, " +
		   				   "BANCOS_SUFIJO, " +
		   				   "BANCOS_NOMBRE_CSB19, " +
		   				   "BANCOS_OTRO_ORDENANTE, " +
		   				   "BANCOS_NRO_CLIENTE, " +
		   				   "BANCOS_COD_INE_PLAZEMIS) " +						   
		   				   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
		   				           "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
		   				           "?, ?, ?, ?, ?, ?, ?, ?) " +
		   				   "ON DUPLICATE KEY UPDATE " +
		   				   "EMPRESA = ?, " +
		   				   "BANCOS_BANCO = ?, " +
		   				   "BANCOS_CENTRO = ?, " +
		   				   "BANCOS_NRO_BCO = ?, " +
		   				   "BANCOS_NRO_SUC = ?, " +
		   				   "BANCOS_DC = ?, " +
		   				   "BANCOS_CTA = ?, " +
		   				   "BANCOS_CONTACTO = ?, " +
		   				   "BANCOS_TLFNO = ?, " +
		   				   "BANCOS_FAX = ?, " +
		   				   "BANCOS_EMAIL = ?, " +
		   				   "BANCOS_CEDENTE = ?, " +
		   				   "BANCOS_CONCEDIDO = ?, " +
		   				   "BANCOS_TOT_RIESGO = ?, " +
		   				   "BANCOS_DIAS_LIBERACION = ?, " +
		   				   "BANCOS_DIAS_AVISOIMPAG = ?, " +
		   				   "BANCOS_NEGOCIADO_ANY = ?, " +
		   				   "BANCOS_EFECTOS = ?, " +
		   				   "BANCOS_REMESAS = ?, " +
		   				   "BANCOS_GASTOS_NEGOC = ?, " +
		   				   "BANCOS_IMPAGADOS = ?, " +
		   				   "BANCOS_NRO_IMPAGADOS = ?, " +
		   				   "BANCOS_GASTOS_IMPAGADOS = ?, " +
		   				   "BANCOS_SUFIJO = ?, " +
		   				   "BANCOS_NOMBRE_CSB19 = ?, " +
		   				   "BANCOS_OTRO_ORDENANTE = ?, " +
		   				   "BANCOS_NRO_CLIENTE = ?, " +
		   				   "BANCOS_COD_INE_PLAZEMIS = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, banco);
			ps.setInt(i++, centro);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitoControl);
			ps.setLong(i++, cuenta);
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(email, 60));
			ps.setString(i++, Cadena.left(cedente, 15));
			ps.setDouble(i++, concedido);
			ps.setDouble(i++, totalRiesgo);
			ps.setInt(i++, diasLiberacion);
			ps.setInt(i++, diasAvisoImpagado);
			ps.setDouble(i++, negociadoAño);
			ps.setInt(i++, efectos);
			ps.setInt(i++, remesas);
			ps.setDouble(i++, gastosNegociacion);
			ps.setDouble(i++, impagados);
			ps.setInt(i++, numeroImpagados);
			ps.setDouble(i++, gastosImpagados);
			ps.setString(i++, Cadena.left(sufijo, 3));
			ps.setString(i++, Cadena.left(nombreCsb19, 15));
			ps.setString(i++, Cadena.left(otroOrdenante, 30));
			ps.setString(i++, Cadena.left(numeroCliente, 15));
			ps.setString(i++, Cadena.left(codigoInePlazemis, 9));
					
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, banco);
			ps.setInt(i++, centro);
			ps.setInt(i++, numeroBanco);
			ps.setInt(i++, numeroSucursal);
			ps.setInt(i++, digitoControl);
			ps.setLong(i++, cuenta);
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(email, 60));
			ps.setString(i++, Cadena.left(cedente, 15));
			ps.setDouble(i++, concedido);
			ps.setDouble(i++, totalRiesgo);
			ps.setInt(i++, diasLiberacion);
			ps.setInt(i++, diasAvisoImpagado);
			ps.setDouble(i++, negociadoAño);
			ps.setInt(i++, efectos);
			ps.setInt(i++, remesas);
			ps.setDouble(i++, gastosNegociacion);
			ps.setDouble(i++, impagados);
			ps.setInt(i++, numeroImpagados);
			ps.setDouble(i++, gastosImpagados);
			ps.setString(i++, Cadena.left(sufijo, 3));
			ps.setString(i++, Cadena.left(nombreCsb19, 15));
			ps.setString(i++, Cadena.left(otroOrdenante, 30));
			ps.setString(i++, Cadena.left(numeroCliente, 15));
			ps.setString(i++, Cadena.left(codigoInePlazemis, 9));
			
			ps.execute();
			
		} catch (SQLException e) {
			escrituraCorrecta = false;
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Banco!!!");
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

	public int getBanco() {
		return banco;
	}

	public void setBanco(int banco) {
		this.banco = banco;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
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

	public int getDigitoControl() {
		return digitoControl;
	}

	public void setDigitoControl(int digitoControl) {
		this.digitoControl = digitoControl;
	}

	public long getCuenta() {
		return cuenta;
	}

	public void setCuenta(long cuenta) {
		this.cuenta = cuenta;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCedente() {
		return cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public double getConcedido() {
		return concedido;
	}

	public void setConcedido(double concedido) {
		this.concedido = concedido;
	}

	public double getTotalRiesgo() {
		return totalRiesgo;
	}

	public void setTotalRiesgo(double totalRiesgo) {
		this.totalRiesgo = totalRiesgo;
	}

	public int getDiasLiberacion() {
		return diasLiberacion;
	}

	public void setDiasLiberacion(int diasLiberacion) {
		this.diasLiberacion = diasLiberacion;
	}

	public int getDiasAvisoImpagado() {
		return diasAvisoImpagado;
	}

	public void setDiasAvisoImpagado(int diasAvisoImpagado) {
		this.diasAvisoImpagado = diasAvisoImpagado;
	}

	public double getNegociadoAño() {
		return negociadoAño;
	}

	public void setNegociadoAño(double negociadoAño) {
		this.negociadoAño = negociadoAño;
	}

	public int getEfectos() {
		return efectos;
	}

	public void setEfectos(int efectos) {
		this.efectos = efectos;
	}

	public int getRemesas() {
		return remesas;
	}

	public void setRemesas(int remesas) {
		this.remesas = remesas;
	}

	public double getGastosNegociacion() {
		return gastosNegociacion;
	}

	public void setGastosNegociacion(double gastosNegociacion) {
		this.gastosNegociacion = gastosNegociacion;
	}

	public double getImpagados() {
		return impagados;
	}

	public void setImpagados(double impagados) {
		this.impagados = impagados;
	}

	public int getNumeroImpagados() {
		return numeroImpagados;
	}

	public void setNumeroImpagados(int numeroImpagados) {
		this.numeroImpagados = numeroImpagados;
	}

	public double getGastosImpagados() {
		return gastosImpagados;
	}

	public void setGastosImpagados(double gastosImpagados) {
		this.gastosImpagados = gastosImpagados;
	}

	public String getSufijo() {
		return sufijo;
	}

	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	public String getNombreCsb19() {
		return nombreCsb19;
	}

	public void setNombreCsb19(String nombreCsb19) {
		this.nombreCsb19 = nombreCsb19;
	}

	public String getOtroOrdenante() {
		return otroOrdenante;
	}

	public void setOtroOrdenante(String otroOrdenante) {
		this.otroOrdenante = otroOrdenante;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getCodigoInePlazemis() {
		return codigoInePlazemis;
	}

	public void setCodigoInePlazemis(String codigoInePlazemis) {
		this.codigoInePlazemis = codigoInePlazemis;
	}
}
