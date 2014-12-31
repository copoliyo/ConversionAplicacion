package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class Agenda {
	private String empresa;
	private String fichero;
	private String clave;
	private int fecha;
	private int horaInicio;
	private int horaFinal;
	private String nota;
	private int aviso;
	private int antelacion;
	private int hecho;
	private String refereciaAlfabetica;
	private int referenciaNumerica;
	private String titulo;
	private int haVenido;

	public Agenda(){
		empresa = DatosComunes.eEmpresa;
		fichero = "";
		clave = "";
		fecha = 0;
		horaInicio = 0;
		horaFinal = 0;
		nota = "";
		aviso = 0;
		antelacion = 0;
		hecho = 0;
		refereciaAlfabetica = "";
		referenciaNumerica = 0;
		titulo = "";
		haVenido = 0;
	}

	public Agenda(ResultSet rs){
		try{
			if(rs.next() == true){

				empresa = rs.getString("EMPRESA").trim();
				fichero = rs.getString("AGENDA_FICHERO").trim();
				clave = rs.getString("AGENDA_CLAVE").trim();
				fecha = rs.getInt("AGENDA_FECHA");
				horaInicio = rs.getInt("AGENDA_HORA_INICIO");
				horaFinal = rs.getInt("AGENDA_HORA_FINAL");
				nota = rs.getString("AGENDA_NOTA").trim();
				aviso = rs.getInt("AGENDA_AVISO");
				antelacion = rs.getInt("AGENDA_ANTELACION");
				hecho = rs.getInt("AGENDA_HECHO");
				refereciaAlfabetica = rs.getString("AGENDA_REF_ALF").trim();
				referenciaNumerica = rs.getInt("AGENDA_REF_NUM");
				titulo = rs.getString("AGENDA_TITULO").trim();
				haVenido = rs.getInt("AGENDA_HA_VENIDO");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Agenda!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{
			empresa = rs.getString("EMPRESA").trim();
			fichero = rs.getString("AGENDA_FICHERO").trim();
			clave = rs.getString("AGENDA_CLAVE").trim();
			fecha = rs.getInt("AGENDA_FECHA");
			horaInicio = rs.getInt("AGENDA_HORA_INICIO");
			horaFinal = rs.getInt("AGENDA_HORA_FINAL");
			nota = rs.getString("AGENDA_NOTA");
			aviso = rs.getInt("AGENDA_AVISO");
			antelacion = rs.getInt("AGENDA_ANTELACION");
			hecho = rs.getInt("AGENDA_HECHO");
			refereciaAlfabetica = rs.getString("AGENDA_REF_ALF").trim();
			referenciaNumerica = rs.getInt("AGENDA_REF_NUM");
			titulo = rs.getString("AGENDA_TITULO").trim();
			haVenido = rs.getInt("AGENDA_HA_VENIDO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Agenda!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO AGENDA " +
						   "(EMPRESA, " +
						   "AGENDA_FICHERO, " +
						   "AGENDA_CLAVE, " +
						   "AGENDA_FECHA, " +
						   "AGENDA_HORA_INICIO, " +
						   "AGENDA_HORA_FINAL, " +
						   "AGENDA_NOTA, " +
						   "AGENDA_AVISO, " +
						   "AGENDA_ANTELACION, " +
						   "AGENDA_HECHO, " +
						   "AGENDA_REF_ALF, " +
						   "AGENDA_REF_NUM, " +
						   "AGENDA_TITULO, " +
						   "AGENDA_HA_VENIDO) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "AGENDA_FICHERO = ?, " +
						   "AGENDA_CLAVE = ?, " +
						   "AGENDA_FECHA = ?, " +
						   "AGENDA_HORA_INICIO = ?, " +
						   "AGENDA_HORA_FINAL = ?, " +
						   "AGENDA_NOTA = ?, " +
						   "AGENDA_AVISO = ?, " +
						   "AGENDA_ANTELACION = ?, " +
						   "AGENDA_HECHO = ?, " +
						   "AGENDA_REF_ALF = ?, " +
						   "AGENDA_REF_NUM = ?, " +
						   "AGENDA_TITULO = ?, " +
						   "AGENDA_HA_VENIDO = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setString(2, Cadena.left(fichero, 8));
			ps.setString(3, Cadena.left(clave, 30));
			ps.setInt(4, fecha);
			ps.setInt(5, horaInicio);
			ps.setInt(6, horaFinal);
			ps.setString(7, Cadena.left(nota, 1024));
			ps.setInt(8, aviso);
			ps.setInt(9, antelacion);
			ps.setInt(10, hecho);
			ps.setString(11, Cadena.left(refereciaAlfabetica, 20));
			ps.setInt(12, referenciaNumerica);
			ps.setString(13, Cadena.left(titulo, 16));
			ps.setInt(14, haVenido);
			// Update
			ps.setString(15, Cadena.left(empresa, 2));
			ps.setString(16, Cadena.left(fichero, 8));
			ps.setString(17, Cadena.left(clave, 30));
			ps.setInt(18, fecha);
			ps.setInt(19, horaInicio);
			ps.setInt(20, horaFinal);
			ps.setString(21, Cadena.left(nota, 1024));
			ps.setInt(22, aviso);
			ps.setInt(23, antelacion);
			ps.setInt(24, hecho);
			ps.setString(25, Cadena.left(refereciaAlfabetica, 20));
			ps.setInt(26, referenciaNumerica);
			ps.setString(27, Cadena.left(titulo, 16));
			ps.setInt(28, haVenido);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Agenda!!!");
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

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(int horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public int getAviso() {
		return aviso;
	}

	public void setAviso(int aviso) {
		this.aviso = aviso;
	}

	public int getAntelacion() {
		return antelacion;
	}

	public void setAntelacion(int antelacion) {
		this.antelacion = antelacion;
	}

	public int getHecho() {
		return hecho;
	}

	public void setHecho(int hecho) {
		this.hecho = hecho;
	}

	public String getRefereciaAlfabetica() {
		return refereciaAlfabetica;
	}

	public void setRefereciaAlfabetica(String refereciaAlfabetica) {
		this.refereciaAlfabetica = refereciaAlfabetica;
	}

	public int getReferenciaNumerica() {
		return referenciaNumerica;
	}

	public void setReferenciaNumerica(int referenciaNumerica) {
		this.referenciaNumerica = referenciaNumerica;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getHaVenido() {
		return haVenido;
	}

	public void setHaVenido(int haVenido) {
		this.haVenido = haVenido;
	}
}
