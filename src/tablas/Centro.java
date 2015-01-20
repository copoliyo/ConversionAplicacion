package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class Centro {
	private String empresa;
	private int codigo;
	private String keyAlfa;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String responsable;
	private int tipoCentro;
	private String telefono;
	private int empleados;
	private int metrosCuadrados;
	private String ip;
	private int enInventario;
	private int representanteCoor;
	private int seVeEnTiendaPropia;
	private double saldoFijoDeCaja;
	private int grupo;
	private int activo;
	private int tipTipoCentro;
	private int coeficienteLineales;
	private int coeficienteComercio;
	private int representanteResponsable;
	private int centroServicio;
	
	public Centro(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		keyAlfa = "";
		nombre = "";
		direccion = "";
		poblacion = "";
		responsable = "";
		tipoCentro = 0;
		telefono = "";
		empleados = metrosCuadrados = 0;
		ip = "";
		enInventario = representanteCoor = seVeEnTiendaPropia = 0;
	    saldoFijoDeCaja = 0.0;
		grupo = activo = tipTipoCentro = coeficienteLineales = coeficienteComercio =
		representanteResponsable = centroServicio = 0;		
	}
	
	public Centro(ResultSet rs){
		try{
			if(rs.next() == true){
				
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("ACCCEN_CODIGO");
				keyAlfa = rs.getString("ACCCEN_KEY_ALFA").trim();
				nombre = rs.getString("ACCCEN_NOMBRE").trim();
				direccion = rs.getString("ACCCEN_DIR").trim();
				poblacion = rs.getString("ACCCEN_POB").trim();
				responsable = rs.getString("ACCCEN_RESPONS").trim();
				tipoCentro = rs.getInt("ACCCEN_TIPO_CENTRO");
				telefono = rs.getString("ACCCEN_TLFNO").trim();
				empleados = rs.getInt("ACCCEN_EMPLEADOS");
				metrosCuadrados = rs.getInt("ACCCEN_M2");
				ip = rs.getString("ACCCEN_IP").trim();
				enInventario = rs.getInt("ACCCEN_EN_INVENTARIO");
				representanteCoor = rs.getInt("ACCCEN_REPRES_COORD");
				seVeEnTiendaPropia = rs.getInt("ACCCEN_SVE_EN_TIPRO");
				saldoFijoDeCaja = rs.getDouble("ACCCEN_SDO_FIJO_CAJA");
				grupo = rs.getInt("ACCCEN_GRUPO");
				activo = rs.getInt("ACCCEN_ACTIVO");
				tipTipoCentro = rs.getInt("ACCCEN_TIP_TIPO_CENTRO");
				coeficienteLineales = rs.getInt("ACCCEN_COEFIC_LINEALES");
				coeficienteComercio = rs.getInt("ACCCEN_COEFIC_COMERCIO");
				representanteResponsable = rs.getInt("ACCCEN_REPRES_RESP");
				centroServicio = rs.getInt("ACCCEN_CENTRO_SERV");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Centro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
		
	}
	
	public void read(ResultSet rs){
		try{		
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("ACCCEN_CODIGO");
			keyAlfa = rs.getString("ACCCEN_KEY_ALFA").trim();
			nombre = rs.getString("ACCCEN_NOMBRE").trim();
			direccion = rs.getString("ACCCEN_DIR").trim();
			poblacion = rs.getString("ACCCEN_POB").trim();
			responsable = rs.getString("ACCCEN_RESPONS").trim();
			tipoCentro = rs.getInt("ACCCEN_TIPO_CENTRO");
			telefono = rs.getString("ACCCEN_TLFNO").trim();
			empleados = rs.getInt("ACCCEN_EMPLEADOS");
			metrosCuadrados = rs.getInt("ACCCEN_M2");
			ip = rs.getString("ACCCEN_IP").trim();
			enInventario = rs.getInt("ACCCEN_EN_INVENTARIO");
			representanteCoor = rs.getInt("ACCCEN_REPRES_COORD");
			seVeEnTiendaPropia = rs.getInt("ACCCEN_SVE_EN_TIPRO");
			saldoFijoDeCaja = rs.getDouble("ACCCEN_SDO_FIJO_CAJA");
			grupo = rs.getInt("ACCCEN_GRUPO");
			activo = rs.getInt("ACCCEN_ACTIVO");
			tipTipoCentro = rs.getInt("ACCCEN_TIP_TIPO_CENTRO");
			coeficienteLineales = rs.getInt("ACCCEN_COEFIC_LINEALES");
			coeficienteComercio = rs.getInt("ACCCEN_COEFIC_COMERCIO");
			representanteResponsable = rs.getInt("ACCCEN_REPRES_RESP");
			centroServicio = rs.getInt("ACCCEN_CENTRO_SERV");			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Centro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
		
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ACCCEN (" +
						   "EMPRESA, " +
						   "ACCCEN_CODIGO, " +
						   "ACCCEN_KEY_ALFA, " +
						   "ACCCEN_NOMBRE, " +
						   "ACCCEN_DIR, " +
						   "ACCCEN_POB, " +
						   "ACCCEN_RESPONS, " +
						   "ACCCEN_TIPO_CENTRO, " +
						   "ACCCEN_TLFNO, " +
						   "ACCCEN_EMPLEADOS, " +
						   "ACCCEN_M2, " +
						   "ACCCEN_IP, " +
						   "ACCCEN_EN_INVENTARIO, " +
						   "ACCCEN_REPRES_COORD, " +
						   "ACCCEN_SVE_EN_TIPRO, " +
						   "ACCCEN_SDO_FIJO_CAJA, " +
						   "ACCCEN_GRUPO, " +
						   "ACCCEN_ACTIVO, " +
						   "ACCCEN_TIP_TIPO_CENTRO, " +
						   "ACCCEN_COEFIC_LINEALES, " +
						   "ACCCEN_COEFIC_COMERCIO, " +
						   "ACCCEN_REPRES_RESP, " +						   
						   "ACCCEN_CENTRO_SERV) " +
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
						   "ACCCEN_CODIGO = ?, " +
						   "ACCCEN_KEY_ALFA = ?, " +
						   "ACCCEN_NOMBRE = ?, " +
						   "ACCCEN_DIR = ?, " +
						   "ACCCEN_POB = ?, " +
						   "ACCCEN_RESPONS = ?, " +
						   "ACCCEN_TIPO_CENTRO = ?, " +
						   "ACCCEN_TLFNO = ?, " +
						   "ACCCEN_EMPLEADOS = ?, " +
						   "ACCCEN_M2 = ?, " +
						   "ACCCEN_IP = ?, " +
						   "ACCCEN_EN_INVENTARIO = ?, " +
						   "ACCCEN_REPRES_COORD = ?, " +
						   "ACCCEN_SVE_EN_TIPRO = ?, " +
						   "ACCCEN_SDO_FIJO_CAJA = ?, " +
						   "ACCCEN_GRUPO = ?, " +
						   "ACCCEN_ACTIVO = ?, " +
						   "ACCCEN_TIP_TIPO_CENTRO = ?, " +
						   "ACCCEN_COEFIC_LINEALES = ?, " +
						   "ACCCEN_COEFIC_COMERCIO = ?, " +
						   "ACCCEN_REPRES_RESP = ?, " +						   
						   "ACCCEN_CENTRO_SERV = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(keyAlfa, 2));
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			ps.setString(i++, Cadena.left(responsable, 30));
			ps.setInt(i++, tipoCentro);
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setInt(i++, empleados);
			ps.setInt(i++, metrosCuadrados);
			ps.setString(i++, Cadena.left(ip, 15));
			ps.setInt(i++, enInventario);
			ps.setInt(i++, representanteCoor);
			ps.setInt(i++, seVeEnTiendaPropia);
			ps.setDouble(i++, saldoFijoDeCaja);
			ps.setInt(i++, grupo);
			ps.setInt(i++, activo);
			ps.setInt(i++, tipTipoCentro);
			ps.setInt(i++, coeficienteLineales);
			ps.setInt(i++, coeficienteComercio);
			ps.setInt(i++, representanteResponsable);
			ps.setInt(i++, centroServicio);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(keyAlfa, 2));
			ps.setString(i++, Cadena.left(nombre, 30));
			ps.setString(i++, Cadena.left(direccion, 30));
			ps.setString(i++, Cadena.left(poblacion, 30));
			ps.setString(i++, Cadena.left(responsable, 30));
			ps.setInt(i++, tipoCentro);
			ps.setString(i++, Cadena.left(telefono, 16));
			ps.setInt(i++, empleados);
			ps.setInt(i++, metrosCuadrados);
			ps.setString(i++, Cadena.left(ip, 15));
			ps.setInt(i++, enInventario);
			ps.setInt(i++, representanteCoor);
			ps.setInt(i++, seVeEnTiendaPropia);
			ps.setDouble(i++, saldoFijoDeCaja);
			ps.setInt(i++, grupo);
			ps.setInt(i++, activo);
			ps.setInt(i++, tipTipoCentro);
			ps.setInt(i++, coeficienteLineales);
			ps.setInt(i++, coeficienteComercio);
			ps.setInt(i++, representanteResponsable);
			ps.setInt(i++, centroServicio);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Centro!!!");
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getKeyAlfa() {
		return keyAlfa;
	}

	public void setKeyAlfa(String keyAlfa) {
		this.keyAlfa = keyAlfa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public int getTipoCentro() {
		return tipoCentro;
	}

	public void setTipoCentro(int tipoCentro) {
		this.tipoCentro = tipoCentro;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getEmpleados() {
		return empleados;
	}

	public void setEmpleados(int empleados) {
		this.empleados = empleados;
	}

	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getEnInventario() {
		return enInventario;
	}

	public void setEnInventario(int enInventario) {
		this.enInventario = enInventario;
	}

	public int getRepresentanteCoor() {
		return representanteCoor;
	}

	public void setRepresentanteCoor(int representanteCoor) {
		this.representanteCoor = representanteCoor;
	}

	public int getSeVeEnTiendaPropia() {
		return seVeEnTiendaPropia;
	}

	public void setSeVeEnTiendaPropia(int seVeEnTiendaPropia) {
		this.seVeEnTiendaPropia = seVeEnTiendaPropia;
	}

	public double getSaldoFijoDeCaja() {
		return saldoFijoDeCaja;
	}

	public void setSaldoFijoDeCaja(double saldoFijoDeCaja) {
		this.saldoFijoDeCaja = saldoFijoDeCaja;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getTipTipoCentro() {
		return tipTipoCentro;
	}

	public void setTipTipoCentro(int tipTipoCentro) {
		this.tipTipoCentro = tipTipoCentro;
	}

	public int getCoeficienteLineales() {
		return coeficienteLineales;
	}

	public void setCoeficienteLineales(int coeficienteLineales) {
		this.coeficienteLineales = coeficienteLineales;
	}

	public int getCoeficienteComercio() {
		return coeficienteComercio;
	}

	public void setCoeficienteComercio(int coeficienteComercio) {
		this.coeficienteComercio = coeficienteComercio;
	}

	public int getRepresentanteResponsable() {
		return representanteResponsable;
	}

	public void setRepresentanteResponsable(int representanteResponsable) {
		this.representanteResponsable = representanteResponsable;
	}

	public int getCentroServicio() {
		return centroServicio;
	}

	public void setCentroServicio(int centroServicio) {
		this.centroServicio = centroServicio;
	}
}
