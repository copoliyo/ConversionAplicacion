package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import util.Cadena;



public class Representante {
	private String empresa;
	private int representante;
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
	private String observaciones;
	private double comision;
	private int tipoComision;
	private double retencionIrpf;
	private int activo;
	private String firmaElectronica;
	private int permitePedidosWeb;

	public Representante(){
		empresa = DatosComunes.eEmpresa;
		representante = 0;
		centro = 0;
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
		observaciones = "";
		comision = 0.0;
		tipoComision = 0;
		retencionIrpf = 0.0;
		activo = 0;
		firmaElectronica = "";
		permitePedidosWeb = 0;
	}

	public Representante(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				representante = rs.getInt("REPRES_REPRE");
				centro = rs.getInt("REPRES_CENTRO");
				apellidosRazonSocial = rs.getString("REPRES_APELRAZON").trim();
				nombre = rs.getString("REPRES_NOMBRE").trim();
				direccionAtencion = rs.getString("REPRES_DIRATT").trim();
				direccion = rs.getString("REPRES_DIR").trim();
				poblacion = rs.getString("REPRES_POB").trim();
				pais = rs.getInt("REPRES_PAIS");
				provincia = rs.getInt("REPRES_PROV");
				codigoPostal = rs.getInt("REPRES_CP");
				contacto = rs.getString("REPRES_CONTACTO").trim();
				telefono = rs.getString("REPRES_TLFNO").trim();
				fax = rs.getString("REPRES_FAX").trim();
				nif = rs.getString("REPRES_NIF").trim();
				email = rs.getString("REPRES_EMAIL").trim();
				observaciones = rs.getString("REPRES_OBSERV").trim();
				comision = rs.getDouble("REPRES_COMISION");
				tipoComision = rs.getInt("REPRES_TIPOCOM");
				retencionIrpf = rs.getDouble("REPRES_RETIRPF");
				activo = rs.getInt("REPRES_ACTIVO");
				firmaElectronica = rs.getString("REPRES_FIRMA_E").trim();
				permitePedidosWeb = rs.getInt("REPRES_PERMITE_PEDIDOS_WEB");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Representante!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			representante = rs.getInt("REPRES_REPRE");
			centro = rs.getInt("REPRES_CENTRO");
			apellidosRazonSocial = rs.getString("REPRES_APELRAZON").trim();
			nombre = rs.getString("REPRES_NOMBRE").trim();
			direccionAtencion = rs.getString("REPRES_DIRATT").trim();
			direccion = rs.getString("REPRES_DIR").trim();
			poblacion = rs.getString("REPRES_POB").trim();
			pais = rs.getInt("REPRES_PAIS");
			provincia = rs.getInt("REPRES_PROV");
			codigoPostal = rs.getInt("REPRES_CP");
			contacto = rs.getString("REPRES_CONTACTO").trim();
			telefono = rs.getString("REPRES_TLFNO").trim();
			fax = rs.getString("REPRES_FAX").trim();
			nif = rs.getString("REPRES_NIF").trim();
			email = rs.getString("REPRES_EMAIL").trim();
			observaciones = rs.getString("REPRES_OBSERV").trim();
			comision = rs.getDouble("REPRES_COMISION");
			tipoComision = rs.getInt("REPRES_TIPOCOM");
			retencionIrpf = rs.getDouble("REPRES_RETIRPF");
			activo = rs.getInt("REPRES_ACTIVO");
			firmaElectronica = rs.getString("REPRES_FIRMA_E").trim();
			permitePedidosWeb = rs.getInt("REPRES_PERMITE_PEDIDOS_WEB");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Representante!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public boolean write(){
		boolean escrituraCorrecta = true;
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO REPRES " +
						   "(EMPRESA, " +
						   "REPRES_REPRE, " +
						   "REPRES_CENTRO, " +
						   "REPRES_APELRAZON, " +
						   "REPRES_NOMBRE, " +
						   "REPRES_DIRATT, " +
						   "REPRES_DIR, " +
						   "REPRES_POB, " +
						   "REPRES_PAIS, " +
						   "REPRES_PROV, " +
						   "REPRES_CP, " +
						   "REPRES_CONTACTO, " +
						   "REPRES_TLFNO, " +
						   "REPRES_FAX, " +
						   "REPRES_NIF, " +
						   "REPRES_EMAIL, " +
						   "REPRES_OBSERV, " +
						   "REPRES_COMISION, " +
						   "REPRES_TIPOCOM, " +
						   "REPRES_RETIRPF, " +
						   "REPRES_ACTIVO, " +
						   "REPRES_FIRMA_E, " +
						   "REPRES_PERMITE_PEDIDOS_WEB) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "REPRES_REPRE = ?, " +
						   "REPRES_CENTRO = ?, " +
						   "REPRES_APELRAZON = ?, " +
						   "REPRES_NOMBRE = ?, " +
						   "REPRES_DIRATT = ?, " +
						   "REPRES_DIR = ?, " +
						   "REPRES_POB = ?, " +
						   "REPRES_PAIS = ?, " +
						   "REPRES_PROV = ?, " +
						   "REPRES_CP = ?, " +
						   "REPRES_CONTACTO = ?, " +
						   "REPRES_TLFNO = ?, " +
						   "REPRES_FAX = ?, " +
						   "REPRES_NIF = ?, " +
						   "REPRES_EMAIL = ?, " +
						   "REPRES_OBSERV = ?, " +
						   "REPRES_COMISION = ?, " +
						   "REPRES_TIPOCOM = ?, " +
						   "REPRES_RETIRPF = ?, " +
						   "REPRES_ACTIVO = ?, " +
						   "REPRES_FIRMA_E = ?, " +
						   "REPRES_PERMITE_PEDIDOS_WEB = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, representante);
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
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setDouble(i++, comision);
			ps.setInt(i++, tipoComision);
			ps.setDouble(i++, retencionIrpf);
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(firmaElectronica, 8));
			ps.setInt(i++, permitePedidosWeb);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, representante);
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
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setDouble(i++, comision);
			ps.setInt(i++, tipoComision);
			ps.setDouble(i++, retencionIrpf);
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(firmaElectronica, 8));
			ps.setInt(i++, permitePedidosWeb);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				escrituraCorrecta = false;
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Representante!!!");
				e.printStackTrace();
			}
		}
		
		return escrituraCorrecta;
	}
	
	
	// Devolvemos el número de registros borrados, si hay problemas, devolvemos -1 
	public int delete(){
		int registrosBorrados = 0;
		
		Statement ps = null;
		   
		String sqlDelete = "DELETE FROM REPRES WHERE " + 
							"EMPRESA = '" + this.empresa + "' AND " +				           
							"REPRES_REPRE = " + this.representante + " AND " +
							"REPRES_CENTRO = " + this.centro;

		try {
			ps = MysqlConnect.db.conn.createStatement();
						
			registrosBorrados = ps.executeUpdate(sqlDelete);

		} catch (SQLException e) {
			registrosBorrados = -1;
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
						"Error en borrado fichero de Representantes!!!");
				e.printStackTrace();
			}
		}				
		
		return registrosBorrados;			
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public int getTipoComision() {
		return tipoComision;
	}

	public void setTipoComision(int tipoComision) {
		this.tipoComision = tipoComision;
	}

	public double getRetencionIrpf() {
		return retencionIrpf;
	}

	public void setRetencionIrpf(double retencionIrpf) {
		this.retencionIrpf = retencionIrpf;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getFirmaElectronica() {
		return firmaElectronica;
	}

	public void setFirmaElectronica(String firmaElectronica) {
		this.firmaElectronica = firmaElectronica;
	}

	public int getPermitePedidosWeb() {
		return permitePedidosWeb;
	}

	public void setPermitePedidosWeb(int permitePedidosWeb) {
		this.permitePedidosWeb = permitePedidosWeb;
	}
}
