package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class ServicioTecnico {
	private String empresa;
	private String marcas;
	private int numeroSat;
	private int centro;
	private String apellidosRazon;
	private String nombre;
	private String direccionAtencion;
	private String direccion;
	private String poblacion;
	private String contacto;
	private String telefono;
	private String fax;
	private String nif;
	private String observaciones;
	private int reparacionesAño;
	private double importeAño;
	private int activo;

	public ServicioTecnico(){
		empresa = DatosComunes.eEmpresa;
		marcas = "";
		numeroSat = 0;
		centro = 0;
		apellidosRazon = "";
		nombre = "";
		direccionAtencion = "";
		direccion = "";
		poblacion = "";
		contacto = "";
		telefono = "";
		fax = "";
		nif = "";
		observaciones = "";
		reparacionesAño = 0;
		importeAño = 0.0;
		activo = 0;
	}

	public ServicioTecnico(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				marcas = rs.getString("SATSAT_MARCAS").trim();
				numeroSat = rs.getInt("SATSAT_NRO_SAT");
				centro = rs.getInt("SATSAT_CENTRO");
				apellidosRazon = rs.getString("SATSAT_APELRAZON").trim();
				nombre = rs.getString("SATSAT_NOMBRE").trim();
				direccionAtencion = rs.getString("SATSAT_DIRATT").trim();
				direccion = rs.getString("SATSAT_DIR").trim();
				poblacion = rs.getString("SATSAT_POB").trim();
				contacto = rs.getString("SATSAT_CONTACTO").trim();
				telefono = rs.getString("SATSAT_TLFNO").trim();
				fax = rs.getString("SATSAT_FAX").trim();
				nif = rs.getString("SATSAT_NIF").trim();
				observaciones = rs.getString("SATSAT_OBSERVAC").trim();
				reparacionesAño = rs.getInt("SATSAT_REPARAC_ANY");
				importeAño = rs.getDouble("SATSAT_IMP_ANY");
				activo = rs.getInt("SATSAT_ACTIVO");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ServicioTecnico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			marcas = rs.getString("SATSAT_MARCAS").trim();
			numeroSat = rs.getInt("SATSAT_NRO_SAT");
			centro = rs.getInt("SATSAT_CENTRO");
			apellidosRazon = rs.getString("SATSAT_APELRAZON").trim();
			nombre = rs.getString("SATSAT_NOMBRE").trim();
			direccionAtencion = rs.getString("SATSAT_DIRATT").trim();
			direccion = rs.getString("SATSAT_DIR").trim();
			poblacion = rs.getString("SATSAT_POB").trim();
			contacto = rs.getString("SATSAT_CONTACTO").trim();
			telefono = rs.getString("SATSAT_TLFNO").trim();
			fax = rs.getString("SATSAT_FAX").trim();
			nif = rs.getString("SATSAT_NIF").trim();
			observaciones = rs.getString("SATSAT_OBSERVAC").trim();
			reparacionesAño = rs.getInt("SATSAT_REPARAC_ANY");
			importeAño = rs.getDouble("SATSAT_IMP_ANY");
			activo = rs.getInt("SATSAT_ACTIVO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ServicioTecnico!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO SATSAT " +
						   "(EMPRESA, " +
						   "SATSAT_MARCAS, " +
						   "SATSAT_NRO_SAT, " +
						   "SATSAT_CENTRO, " +
						   "SATSAT_APELRAZON, " +
						   "SATSAT_NOMBRE, " +
						   "SATSAT_DIRATT, " +
						   "SATSAT_DIR, " +
						   "SATSAT_POB, " +
						   "SATSAT_CONTACTO, " +
						   "SATSAT_TLFNO, " +
						   "SATSAT_FAX, " +
						   "SATSAT_NIF, " +
						   "SATSAT_OBSERVAC, " +
						   "SATSAT_REPARAC_ANY, " +
						   "SATSAT_IMP_ANY, " +
						   "SATSAT_ACTIVO) " +			   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "SATSAT_MARCAS = ?, " +
						   "SATSAT_NRO_SAT = ?, " +
						   "SATSAT_CENTRO = ?, " +
						   "SATSAT_APELRAZON = ?, " +
						   "SATSAT_NOMBRE = ?, " +
						   "SATSAT_DIRATT = ?, " +
						   "SATSAT_DIR = ?, " +
						   "SATSAT_POB = ?, " +
						   "SATSAT_CONTACTO = ?, " +
						   "SATSAT_TLFNO = ?, " +
						   "SATSAT_FAX = ?, " +
						   "SATSAT_NIF = ?, " +
						   "SATSAT_OBSERVAC = ?, " +
						   "SATSAT_REPARAC_ANY = ?, " +
						   "SATSAT_IMP_ANY = ?, " +
						   "SATSAT_ACTIVO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(marcas, 25));
			ps.setInt(i++, numeroSat);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazon, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setInt(i++, reparacionesAño);
			ps.setDouble(i++, importeAño);
			ps.setInt(i++, activo);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(marcas, 25));
			ps.setInt(i++, numeroSat);
			ps.setInt(i++, centro);
			ps.setString(i++, Cadena.left(apellidosRazon, 30));
			ps.setString(i++, Cadena.left(nombre, 16));
			ps.setString(i++, Cadena.left(direccionAtencion, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			ps.setString(i++, Cadena.left(contacto, 30));
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setString(i++, Cadena.left(fax, 16));
			ps.setString(i++, Cadena.left(nif, 16));
			ps.setString(i++, Cadena.left(observaciones, 30));
			ps.setInt(i++, reparacionesAño);
			ps.setDouble(i++, importeAño);
			ps.setInt(i++, activo);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ServicioTecnico!!!");
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

	public String getMarcas() {
		return marcas;
	}

	public void setMarcas(String marcas) {
		this.marcas = marcas;
	}

	public int getNumeroSat() {
		return numeroSat;
	}

	public void setNumeroSat(int numeroSat) {
		this.numeroSat = numeroSat;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public String getApellidosRazon() {
		return apellidosRazon;
	}

	public void setApellidosRazon(String apellidosRazon) {
		this.apellidosRazon = apellidosRazon;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getReparacionesAño() {
		return reparacionesAño;
	}

	public void setReparacionesAño(int reparacionesAño) {
		this.reparacionesAño = reparacionesAño;
	}

	public double getImporteAño() {
		return importeAño;
	}

	public void setImporteAño(double importeAño) {
		this.importeAño = importeAño;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
}
