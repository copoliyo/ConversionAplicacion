package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CaracteristicaFamilia {
	private String empresa;
	private int gama;
	private int familia;
	private int orden;
	private String descripcion;
	private int tipoDato;
	private int tipoEtiqueta;
	private int tipoPantalla;
	private int altoEmbalado;
	private int anchoEmbalado;
	private int fondoEmbalado;
	private int pesoEmbalado;
	private int tipoTratamiento;
	private int tipoEspacio;
	private int unidadesPack;

	public CaracteristicaFamilia(){
		empresa = DatosComunes.eEmpresa;
		gama = 0;
		familia = 0;
		orden = 0;
		descripcion = "";
		tipoDato = 0;
		tipoEtiqueta = 0;
		tipoPantalla = 0;
		altoEmbalado = 0;
		anchoEmbalado = 0;
		fondoEmbalado = 0;
		pesoEmbalado = 0;
		tipoTratamiento = 0;
		tipoEspacio = 0;
		unidadesPack = 0;
	}

	public CaracteristicaFamilia(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				gama = rs.getInt("CARTCF_GAMA");
				familia = rs.getInt("CARTCF_FAMILIA");
				orden = rs.getInt("CARTCF_ORDEN");
				descripcion = rs.getString("CARTCF_DESCRIP").trim();
				tipoDato = rs.getInt("CARTCF_ALF_NUM_SINO");
				tipoEtiqueta = rs.getInt("CARTCF_TIPO_ETIQ");
				tipoPantalla = rs.getInt("CARTCF_TIPO_PANT");
				altoEmbalado = rs.getInt("CARTCF_ALTO_EMB");
				anchoEmbalado = rs.getInt("CARTCF_ANCHO_EMB");
				fondoEmbalado = rs.getInt("CARTCF_FONDO_EMB");
				pesoEmbalado = rs.getInt("CARTCF_PESO_EMB");
				tipoTratamiento = rs.getInt("CARTCF_TIPO_TRATAM");
				tipoEspacio = rs.getInt("CARTCF_TIPO_ESPACIO");
				unidadesPack = rs.getInt("CARTCF_UNID_PACK");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CaracteristicaFamilia!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			gama = rs.getInt("CARTCF_GAMA");
			familia = rs.getInt("CARTCF_FAMILIA");
			orden = rs.getInt("CARTCF_ORDEN");
			descripcion = rs.getString("CARTCF_DESCRIP").trim();
			tipoDato = rs.getInt("CARTCF_ALF_NUM_SINO");
			tipoEtiqueta = rs.getInt("CARTCF_TIPO_ETIQ");
			tipoPantalla = rs.getInt("CARTCF_TIPO_PANT");
			altoEmbalado = rs.getInt("CARTCF_ALTO_EMB");
			anchoEmbalado = rs.getInt("CARTCF_ANCHO_EMB");
			fondoEmbalado = rs.getInt("CARTCF_FONDO_EMB");
			pesoEmbalado = rs.getInt("CARTCF_PESO_EMB");
			tipoTratamiento = rs.getInt("CARTCF_TIPO_TRATAM");
			tipoEspacio = rs.getInt("CARTCF_TIPO_ESPACIO");
			unidadesPack = rs.getInt("CARTCF_UNID_PACK");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CaracteristicaFamilia!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CARTCF " +
						   "(EMPRESA, " +
						   "CARTCF_GAMA, " +
						   "CARTCF_FAMILIA, " +
						   "CARTCF_ORDEN, " +
						   "CARTCF_DESCRIP, " +
						   "CARTCF_ALF_NUM_SINO, " +
						   "CARTCF_TIPO_ETIQ, " +
						   "CARTCF_TIPO_PANT, " +
						   "CARTCF_ALTO_EMB, " +
						   "CARTCF_ANCHO_EMB, " +
						   "CARTCF_FONDO_EMB, " +
						   "CARTCF_PESO_EMB, " +
						   "CARTCF_TIPO_TRATAM, " +
						   "CARTCF_TIPO_ESPACIO, " +
						   "CARTCF_UNID_PACK) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CARTCF_GAMA = ?, " +
						   "CARTCF_FAMILIA = ?, " +
						   "CARTCF_ORDEN = ?, " +
						   "CARTCF_DESCRIP = ?, " +
						   "CARTCF_ALF_NUM_SINO = ?, " +
						   "CARTCF_TIPO_ETIQ = ?, " +
						   "CARTCF_TIPO_PANT = ?, " +
						   "CARTCF_ALTO_EMB = ?, " +
						   "CARTCF_ANCHO_EMB = ?, " +
						   "CARTCF_FONDO_EMB = ?, " +
						   "CARTCF_PESO_EMB = ?, " +
						   "CARTCF_TIPO_TRATAM = ?, " +
						   "CARTCF_TIPO_ESPACIO = ?, " +
						   "CARTCF_UNID_PACK = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(descripcion, 25));
			ps.setInt(i++, tipoDato);
			ps.setInt(i++, tipoEtiqueta);
			ps.setInt(i++, tipoPantalla);
			ps.setInt(i++, altoEmbalado);
			ps.setInt(i++, anchoEmbalado);
			ps.setInt(i++, fondoEmbalado);
			ps.setInt(i++, pesoEmbalado);
			ps.setInt(i++, tipoTratamiento);
			ps.setInt(i++, tipoEspacio);
			ps.setInt(i++, unidadesPack);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(descripcion, 25));
			ps.setInt(i++, tipoDato);
			ps.setInt(i++, tipoEtiqueta);
			ps.setInt(i++, tipoPantalla);
			ps.setInt(i++, altoEmbalado);
			ps.setInt(i++, anchoEmbalado);
			ps.setInt(i++, fondoEmbalado);
			ps.setInt(i++, pesoEmbalado);
			ps.setInt(i++, tipoTratamiento);
			ps.setInt(i++, tipoEspacio);
			ps.setInt(i++, unidadesPack);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CaracteristicaFamilia!!!");
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

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(int tipoDato) {
		this.tipoDato = tipoDato;
	}

	public int getTipoEtiqueta() {
		return tipoEtiqueta;
	}

	public void setTipoEtiqueta(int tipoEtiqueta) {
		this.tipoEtiqueta = tipoEtiqueta;
	}

	public int getTipoPantalla() {
		return tipoPantalla;
	}

	public void setTipoPantalla(int tipoPantalla) {
		this.tipoPantalla = tipoPantalla;
	}

	public int getAltoEmbalado() {
		return altoEmbalado;
	}

	public void setAltoEmbalado(int altoEmbalado) {
		this.altoEmbalado = altoEmbalado;
	}

	public int getAnchoEmbalado() {
		return anchoEmbalado;
	}

	public void setAnchoEmbalado(int anchoEmbalado) {
		this.anchoEmbalado = anchoEmbalado;
	}

	public int getFondoEmbalado() {
		return fondoEmbalado;
	}

	public void setFondoEmbalado(int fondoEmbalado) {
		this.fondoEmbalado = fondoEmbalado;
	}

	public int getPesoEmbalado() {
		return pesoEmbalado;
	}

	public void setPesoEmbalado(int pesoEmbalado) {
		this.pesoEmbalado = pesoEmbalado;
	}

	public int getTipoTratamiento() {
		return tipoTratamiento;
	}

	public void setTipoTratamiento(int tipoTratamiento) {
		this.tipoTratamiento = tipoTratamiento;
	}

	public int getTipoEspacio() {
		return tipoEspacio;
	}

	public void setTipoEspacio(int tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}

	public int getUnidadesPack() {
		return unidadesPack;
	}

	public void setUnidadesPack(int unidadesPack) {
		this.unidadesPack = unidadesPack;
	}
}
