package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class HistorialAcceso {
	private String empresa;
	private String programa;
	private String usuario;
	private int fecha;
	private int llamada;
	private int hora;
	private int horaFin;
	private int fallo;
	private String tty;
	private int centroContable;
	private int centroGestion;
	
	public HistorialAcceso(){
		empresa = DatosComunes.eEmpresa;
		programa = DatosComunes.programa;
		usuario = DatosComunes.usuario;
		fecha = DatosComunes.fechaLlamada;
		llamada = 0;
		hora = 0;
		horaFin = 0;
		fallo = 0;
		tty = DatosComunes.tty;
		centroContable = DatosComunes.centroCont;
		centroGestion = DatosComunes.centroGest;		
	}
	
	public HistorialAcceso(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				programa = rs.getString("ACCHST_PROGRAMA").trim();
				usuario = rs.getString("ACCHST_USUARIO").trim();
				fecha = rs.getInt("ACCHST_FECHA");
				llamada = rs.getInt("ACCHST_LLAMADA");
				hora = rs.getInt("ACCHST_HORA");
				horaFin = rs.getInt("ACCHST_HORA_FIN");
				fallo = rs.getInt("ACCHST_FALLO");
				tty = rs.getString("ACCHST_TTY").trim();
				centroContable = rs.getInt("ACCHST_CENTRO_CONT");
				centroGestion = rs.getInt("ACCHST_CENTRO_GEST");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de HistorialAcceso!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void read(ResultSet rs){
		try{						
			empresa = rs.getString("EMPRESA").trim();
			programa = rs.getString("ACCHST_PROGRAMA").trim();
			usuario = rs.getString("ACCHST_USUARIO").trim();
			fecha = rs.getInt("ACCHST_FECHA");
			llamada = rs.getInt("ACCHST_LLAMADA");
			hora = rs.getInt("ACCHST_HORA");
			horaFin = rs.getInt("ACCHST_HORA_FIN");
			fallo = rs.getInt("ACCHST_FALLO");
			tty = rs.getString("ACCHST_TTY").trim();
			centroContable = rs.getInt("ACCHST_CENTRO_CONT");
			centroGestion = rs.getInt("ACCHST_CENTRO_GEST");			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de HistorialAcceso!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ACCHST " +
						   "(EMPRESA, " +
						   "ACCHST_PROGRAMA, " +
						   "ACCHST_USUARIO, " +
						   "ACCHST_FECHA, " +
						   "ACCHST_LLAMADA, " +
						   "ACCHST_HORA, " +
						   "ACCHST_HORA_FIN, " +
						   "ACCHST_FALLO, " +
						   "ACCHST_TTY, " +
						   "ACCHST_CENTRO_CONT, " +
						   "ACCHST_CENTRO_GEST) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ACCHST_PROGRAMA = ?, " +
						   "ACCHST_USUARIO = ?, " +
						   "ACCHST_FECHA = ?, " +
						   "ACCHST_LLAMADA = ?, " +
						   "ACCHST_HORA = ?, " +
						   "ACCHST_HORA_FIN = ?, " +
						   "ACCHST_FALLO = ?, " +
						   "ACCHST_TTY = ?, " +
						   "ACCHST_CENTRO_CONT = ?, " +
						   "ACCHST_CENTRO_GEST = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setString(2, Cadena.left(programa, 8));
			ps.setString(3, Cadena.left(usuario, 8));
			ps.setInt(4, fecha);
			ps.setInt(5, llamada);
			ps.setInt(6, hora);
			ps.setInt(7, horaFin);
			ps.setInt(8, fallo);
			ps.setString(9, Cadena.left(tty, 5));
			ps.setInt(10, centroContable);
			ps.setInt(11, centroGestion);
			// Update
			ps.setString(12, Cadena.left(empresa, 2));
			ps.setString(13, Cadena.left(programa, 8));
			ps.setString(14, Cadena.left(usuario, 8));
			ps.setInt(15, fecha);
			ps.setInt(16, llamada);
			ps.setInt(17, hora);
			ps.setInt(18, horaFin);
			ps.setInt(19, fallo);
			ps.setString(20, Cadena.left(tty, 5));
			ps.setInt(21, centroContable);
			ps.setInt(22, centroGestion);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de HistorialAcceso!!!");
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

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getLlamada() {
		return llamada;
	}

	public void setLlamada(int llamada) {
		this.llamada = llamada;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public int getFallo() {
		return fallo;
	}

	public void setFallo(int fallo) {
		this.fallo = fallo;
	}

	public String getTty() {
		return tty;
	}

	public void setTty(String tty) {
		this.tty = tty;
	}

	public int getCentroContable() {
		return centroContable;
	}

	public void setCentroContable(int centroContable) {
		this.centroContable = centroContable;
	}

	public int getCentroGestion() {
		return centroGestion;
	}

	public void setCentroGestion(int centroGestion) {
		this.centroGestion = centroGestion;
	}
}
