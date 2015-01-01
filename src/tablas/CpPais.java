package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CpPais {
	private String empresa;
	private int codigo;
	private String keyOnu;
	private String keyOnuAbc;
	private String keyIso;
	private String keyTelefono;
	private String keyEan;
	private String keyInternet;
	private String keyAladi;
	private String pais;
	private int kilometrosCuadrados;
	private int habitantes;
	private String siglaMoneda;
	private double cambioEuro;
	private double cambioDolar;

	public CpPais(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		keyOnu = "";
		keyOnuAbc = "";
		keyIso = "";
		keyTelefono = "";
		keyEan = "";
		keyInternet = "";
		keyAladi = "";
		pais = "";
		kilometrosCuadrados = 0;
		habitantes = 0;
		siglaMoneda = "";
		cambioEuro = 0.0;
		cambioDolar = 0.0;
	}

	public CpPais(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("CPPAIS_CODIGO");
				keyOnu = rs.getString("CPPAIS_KEY_ONU").trim();
				keyOnuAbc = rs.getString("CPPAIS_KEY_ONU_ABC").trim();
				keyIso = rs.getString("CPPAIS_KEY_ISO").trim();
				keyTelefono = rs.getString("CPPAIS_KEY_TLF").trim();
				keyEan = rs.getString("CPPAIS_KEY_EAN").trim();
				keyInternet = rs.getString("CPPAIS_KEY_INTERNET").trim();
				keyAladi = rs.getString("CPPAIS_KEY_ALADI").trim();
				pais = rs.getString("CPPAIS_PAIS").trim();
				kilometrosCuadrados = rs.getInt("CPPAIS_KM2");
				habitantes = rs.getInt("CPPAIS_HABITANTES");
				siglaMoneda = rs.getString("CPPAIS_SIGLA_MONEDA").trim();
				cambioEuro = rs.getDouble("CPPAIS_CAMBIO_EURO");
				cambioDolar = rs.getDouble("CPPAIS_CAMBIO_DOLAR");	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpPais!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("CPPAIS_CODIGO");
			keyOnu = rs.getString("CPPAIS_KEY_ONU").trim();
			keyOnuAbc = rs.getString("CPPAIS_KEY_ONU_ABC").trim();
			keyIso = rs.getString("CPPAIS_KEY_ISO").trim();
			keyTelefono = rs.getString("CPPAIS_KEY_TLF").trim();
			keyEan = rs.getString("CPPAIS_KEY_EAN").trim();
			keyInternet = rs.getString("CPPAIS_KEY_INTERNET").trim();
			keyAladi = rs.getString("CPPAIS_KEY_ALADI").trim();
			pais = rs.getString("CPPAIS_PAIS").trim();
			kilometrosCuadrados = rs.getInt("CPPAIS_KM2");
			habitantes = rs.getInt("CPPAIS_HABITANTES");
			siglaMoneda = rs.getString("CPPAIS_SIGLA_MONEDA").trim();
			cambioEuro = rs.getDouble("CPPAIS_CAMBIO_EURO");
			cambioDolar = rs.getDouble("CPPAIS_CAMBIO_DOLAR");	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpPais!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ARTEAN " +
						   "(EMPRESA, " +
						   "CPPAIS_CODIGO, " +
						   "CPPAIS_KEY_ONU, " +
						   "CPPAIS_KEY_ONU_ABC, " +
						   "CPPAIS_KEY_ISO, " +
						   "CPPAIS_KEY_TLF, " +
						   "CPPAIS_KEY_EAN, " +
						   "CPPAIS_KEY_INTERNET, " +
						   "CPPAIS_KEY_ALADI, " +
						   "CPPAIS_PAIS, " +
						   "CPPAIS_KM2, " +
						   "CPPAIS_HABITANTES, " +
						   "CPPAIS_SIGLA_MONEDA, " +
						   "CPPAIS_CAMBIO_EURO, " +
						   "CPPAIS_CAMBIO_DOLAR) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CPPAIS_CODIGO = ?, " +
						   "CPPAIS_KEY_ONU = ?, " +
						   "CPPAIS_KEY_ONU_ABC = ?, " +
						   "CPPAIS_KEY_ISO = ?, " +
						   "CPPAIS_KEY_TLF = ?, " +
						   "CPPAIS_KEY_EAN = ?, " +
						   "CPPAIS_KEY_INTERNET = ?, " +
						   "CPPAIS_KEY_ALADI = ?, " +
						   "CPPAIS_PAIS = ?, " +
						   "CPPAIS_KM2 = ?, " +
						   "CPPAIS_HABITANTES = ?, " +
						   "CPPAIS_SIGLA_MONEDA = ?, " +
						   "CPPAIS_CAMBIO_EURO = ?, " +
						   "CPPAIS_CAMBIO_DOLAR ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(keyOnu, 3));
			ps.setString(i++, Cadena.left(keyOnuAbc, 3));
			ps.setString(i++, Cadena.left(keyIso, 3));
			ps.setString(i++, Cadena.left(keyTelefono, 3));
			ps.setString(i++, Cadena.left(keyEan, 3));
			ps.setString(i++, Cadena.left(keyInternet, 3));
			ps.setString(i++, Cadena.left(keyAladi, 3));
			ps.setString(i++, Cadena.left(pais, 20));
			ps.setInt(i++, kilometrosCuadrados);
			ps.setInt(i++, habitantes);
			ps.setString(i++, Cadena.left(siglaMoneda, 3));
			ps.setDouble(i++, cambioEuro);
			ps.setDouble(i++, cambioDolar);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(keyOnu, 3));
			ps.setString(i++, Cadena.left(keyOnuAbc, 3));
			ps.setString(i++, Cadena.left(keyIso, 3));
			ps.setString(i++, Cadena.left(keyTelefono, 3));
			ps.setString(i++, Cadena.left(keyEan, 3));
			ps.setString(i++, Cadena.left(keyInternet, 3));
			ps.setString(i++, Cadena.left(keyAladi, 3));
			ps.setString(i++, Cadena.left(pais, 20));
			ps.setInt(i++, kilometrosCuadrados);
			ps.setInt(i++, habitantes);
			ps.setString(i++, Cadena.left(siglaMoneda, 3));
			ps.setDouble(i++, cambioEuro);
			ps.setDouble(i++, cambioDolar);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CpPais!!!");
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

	public String getKeyOnu() {
		return keyOnu;
	}

	public void setKeyOnu(String keyOnu) {
		this.keyOnu = keyOnu;
	}

	public String getKeyOnuAbc() {
		return keyOnuAbc;
	}

	public void setKeyOnuAbc(String keyOnuAbc) {
		this.keyOnuAbc = keyOnuAbc;
	}

	public String getKeyIso() {
		return keyIso;
	}

	public void setKeyIso(String keyIso) {
		this.keyIso = keyIso;
	}

	public String getKeyTelefono() {
		return keyTelefono;
	}

	public void setKeyTelefono(String keyTelefono) {
		this.keyTelefono = keyTelefono;
	}

	public String getKeyEan() {
		return keyEan;
	}

	public void setKeyEan(String keyEan) {
		this.keyEan = keyEan;
	}

	public String getKeyInternet() {
		return keyInternet;
	}

	public void setKeyInternet(String keyInternet) {
		this.keyInternet = keyInternet;
	}

	public String getKeyAladi() {
		return keyAladi;
	}

	public void setKeyAladi(String keyAladi) {
		this.keyAladi = keyAladi;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getKilometrosCuadrados() {
		return kilometrosCuadrados;
	}

	public void setKilometrosCuadrados(int kilometrosCuadrados) {
		this.kilometrosCuadrados = kilometrosCuadrados;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public String getSiglaMoneda() {
		return siglaMoneda;
	}

	public void setSiglaMoneda(String siglaMoneda) {
		this.siglaMoneda = siglaMoneda;
	}

	public double getCambioEuro() {
		return cambioEuro;
	}

	public void setCambioEuro(double cambioEuro) {
		this.cambioEuro = cambioEuro;
	}

	public double getCambioDolar() {
		return cambioDolar;
	}

	public void setCambioDolar(double cambioDolar) {
		this.cambioDolar = cambioDolar;
	}
}
