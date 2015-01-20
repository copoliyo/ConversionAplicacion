package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class CpPoblacion {
	private String empresa;
	private int pais;
	private int codigo;
	private int orden;
	private String poblacion;
	private int conCallejero;
	private int printProvincia;
	private double distanciaCentral;
	private double distanciaCapitalProv;
	private String otraPoblacion[];
	private double distanciaOtraPoblacion[];
	private String idioma;
	private String otroIdioma;
	private int habitanes;
	private int habitantesArea;

	public CpPoblacion(){
		empresa = DatosComunes.eEmpresa;
		pais = 0;
		codigo = 0;
		orden = 0;
		poblacion = "";
		conCallejero = 0;
		printProvincia = 0;
		distanciaCentral = 0.0;
		distanciaCapitalProv = 0.0;

		otraPoblacion = new String[5];
		for(int i = 0; i < 5; i++)
			otraPoblacion[i] = "";

		distanciaOtraPoblacion = new double[5];
		for(int i = 0; i < 5; i++)
			distanciaOtraPoblacion[i] = 0.0;

		idioma = "";
		otroIdioma = "";
		habitanes = 0;
		habitantesArea = 0;
	}

	public CpPoblacion(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				pais = rs.getInt("CPPOBL_PAIS");
				codigo = rs.getInt("CPPOBL_CODPOB");
				orden = rs.getInt("CPPOBL_ORDENCPB");
				poblacion = rs.getString("CPPOBL_POBLACION").trim();
				conCallejero = rs.getInt("CPPOBL_CON_CALLEJERO");
				printProvincia = rs.getInt("CPPOBL_PRINT_PROV");
				distanciaCentral = rs.getDouble("CPPOBL_DIST_CENTRAL");
				distanciaCapitalProv = rs.getDouble("CPPOBL_DIST_CAP_PROV");

				otraPoblacion[0] = rs.getString("CPPOBL_OTR_POBL_1").trim();
				otraPoblacion[1] = rs.getString("CPPOBL_OTR_POBL_2").trim();
				otraPoblacion[2] = rs.getString("CPPOBL_OTR_POBL_3").trim();
				otraPoblacion[3] = rs.getString("CPPOBL_OTR_POBL_4").trim();
				otraPoblacion[4] = rs.getString("CPPOBL_OTR_POBL_5").trim();

				distanciaOtraPoblacion[0] = rs.getDouble("CPPOBL_DIST_OTR_POBL_1");
				distanciaOtraPoblacion[1] = rs.getDouble("CPPOBL_DIST_OTR_POBL_2");
				distanciaOtraPoblacion[2] = rs.getDouble("CPPOBL_DIST_OTR_POBL_3");
				distanciaOtraPoblacion[3] = rs.getDouble("CPPOBL_DIST_OTR_POBL_4");
				distanciaOtraPoblacion[4] = rs.getDouble("CPPOBL_DIST_OTR_POBL_5");

				idioma = rs.getString("CPPOBL_IDIOMA").trim();
				otroIdioma = rs.getString("CPPOBL_OTRO_IDIOMA").trim();
				habitanes = rs.getInt("CPPOBL_HABITANTES");
				habitantesArea = rs.getInt("CPPOBL_HABIT_AREA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpPoblacion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			pais = rs.getInt("CPPOBL_PAIS");
			codigo = rs.getInt("CPPOBL_CODPOB");
			orden = rs.getInt("CPPOBL_ORDENCPB");
			poblacion = rs.getString("CPPOBL_POBLACION").trim();
			conCallejero = rs.getInt("CPPOBL_CON_CALLEJERO");
			printProvincia = rs.getInt("CPPOBL_PRINT_PROV");
			distanciaCentral = rs.getDouble("CPPOBL_DIST_CENTRAL");
			distanciaCapitalProv = rs.getDouble("CPPOBL_DIST_CAP_PROV");

			otraPoblacion[0] = rs.getString("CPPOBL_OTR_POBL_1").trim();
			otraPoblacion[1] = rs.getString("CPPOBL_OTR_POBL_2").trim();
			otraPoblacion[2] = rs.getString("CPPOBL_OTR_POBL_3").trim();
			otraPoblacion[3] = rs.getString("CPPOBL_OTR_POBL_4").trim();
			otraPoblacion[4] = rs.getString("CPPOBL_OTR_POBL_5").trim();

			distanciaOtraPoblacion[0] = rs.getDouble("CPPOBL_DIST_OTR_POBL_1");
			distanciaOtraPoblacion[1] = rs.getDouble("CPPOBL_DIST_OTR_POBL_2");
			distanciaOtraPoblacion[2] = rs.getDouble("CPPOBL_DIST_OTR_POBL_3");
			distanciaOtraPoblacion[3] = rs.getDouble("CPPOBL_DIST_OTR_POBL_4");
			distanciaOtraPoblacion[4] = rs.getDouble("CPPOBL_DIST_OTR_POBL_5");

			idioma = rs.getString("CPPOBL_IDIOMA").trim();
			otroIdioma = rs.getString("CPPOBL_OTRO_IDIOMA").trim();
			habitanes = rs.getInt("CPPOBL_HABITANTES");
			habitantesArea = rs.getInt("CPPOBL_HABIT_AREA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpPoblacion!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CPPOBL " +
						   "(EMPRESA, " +
						   "CPPOBL_PAIS, " +
						   "CPPOBL_CODPOB, " +
						   "CPPOBL_ORDENCPB, " +
						   "CPPOBL_POBLACION, " +
						   "CPPOBL_CON_CALLEJERO, " +
						   "CPPOBL_PRINT_PROV, " +
						   "CPPOBL_DIST_CENTRAL, " +
						   "CPPOBL_DIST_CAP_PROV, " +
						   "CPPOBL_OTR_POBL_1, " +
						   "CPPOBL_OTR_POBL_2, " +
						   "CPPOBL_OTR_POBL_3, " +
						   "CPPOBL_OTR_POBL_4, " +
						   "CPPOBL_OTR_POBL_5, " +
						   "CPPOBL_DIST_OTR_POBL_1, " +
						   "CPPOBL_DIST_OTR_POBL_2, " +
						   "CPPOBL_DIST_OTR_POBL_3, " +
						   "CPPOBL_DIST_OTR_POBL_4, " +
						   "CPPOBL_DIST_OTR_POBL_5, " +
						   "CPPOBL_IDIOMA, " +
						   "CPPOBL_OTRO_IDIOMA, " +
						   "CPPOBL_HABITANTES, " +
						   "CPPOBL_HABIT_AREA) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CPPOBL_PAIS = ?, " +
						   "CPPOBL_CODPOB = ?, " +
						   "CPPOBL_ORDENCPB = ?, " +
						   "CPPOBL_POBLACION = ?, " +
						   "CPPOBL_CON_CALLEJERO = ?, " +
						   "CPPOBL_PRINT_PROV = ?, " +
						   "CPPOBL_DIST_CENTRAL = ?, " +
						   "CPPOBL_DIST_CAP_PROV = ?, " +
						   "CPPOBL_OTR_POBL_1 = ?, " +
						   "CPPOBL_OTR_POBL_2 = ?, " +
						   "CPPOBL_OTR_POBL_3 = ?, " +
						   "CPPOBL_OTR_POBL_4 = ?, " +
						   "CPPOBL_OTR_POBL_5 = ?, " +
						   "CPPOBL_DIST_OTR_POBL_1 = ?, " +
						   "CPPOBL_DIST_OTR_POBL_2 = ?, " +
						   "CPPOBL_DIST_OTR_POBL_3 = ?, " +
						   "CPPOBL_DIST_OTR_POBL_4 = ?, " +
						   "CPPOBL_DIST_OTR_POBL_5 = ?, " +
						   "CPPOBL_IDIOMA = ?, " +
						   "CPPOBL_OTRO_IDIOMA = ?, " +
						   "CPPOBL_HABITANTES = ?, " +
						   "CPPOBL_HABIT_AREA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, pais);
			ps.setInt(i++, codigo);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(poblacion, 22));
			ps.setInt(i++, conCallejero);
			ps.setInt(i++, printProvincia);
			ps.setDouble(i++, distanciaCentral);
			ps.setDouble(i++, distanciaCapitalProv);
			for(int j = 0; j < 5; j++)
				ps.setString(i++, Cadena.left(otraPoblacion[j],	25));
			for(int j = 0; j < 5; j++)
				ps.setDouble(i++, distanciaOtraPoblacion[j]);
			ps.setString(i++, Cadena.left(idioma, 22));
			ps.setString(i++, Cadena.left(otroIdioma, 22));
			ps.setInt(i++, habitanes);
			ps.setInt(i++, habitantesArea);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, pais);
			ps.setInt(i++, codigo);
			ps.setInt(i++, orden);
			ps.setString(i++, Cadena.left(poblacion, 22));
			ps.setInt(i++, conCallejero);
			ps.setInt(i++, printProvincia);
			ps.setDouble(i++, distanciaCentral);
			ps.setDouble(i++, distanciaCapitalProv);
			for(int j = 0; j < 5; j++)
				ps.setString(i++, Cadena.left(otraPoblacion[j],	25));
			for(int j = 0; j < 5; j++)
				ps.setDouble(i++, distanciaOtraPoblacion[j]);
			ps.setString(i++, Cadena.left(idioma, 22));
			ps.setString(i++, Cadena.left(otroIdioma, 22));
			ps.setInt(i++, habitanes);
			ps.setInt(i++, habitantesArea);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CpPoblacion!!!");
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

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getConCallejero() {
		return conCallejero;
	}

	public void setConCallejero(int conCallejero) {
		this.conCallejero = conCallejero;
	}

	public int getPrintProvincia() {
		return printProvincia;
	}

	public void setPrintProvincia(int printProvincia) {
		this.printProvincia = printProvincia;
	}

	public double getDistanciaCentral() {
		return distanciaCentral;
	}

	public void setDistanciaCentral(double distanciaCentral) {
		this.distanciaCentral = distanciaCentral;
	}

	public double getDistanciaCapitalProv() {
		return distanciaCapitalProv;
	}

	public void setDistanciaCapitalProv(double distanciaCapitalProv) {
		this.distanciaCapitalProv = distanciaCapitalProv;
	}

	public String[] getOtraPoblacion() {
		return otraPoblacion;
	}

	public void setOtraPoblacion(String[] otraPoblacion) {
		this.otraPoblacion = otraPoblacion;
	}

	public double[] getDistanciaOtraPoblacion() {
		return distanciaOtraPoblacion;
	}

	public void setDistanciaOtraPoblacion(double[] distanciaOtraPoblacion) {
		this.distanciaOtraPoblacion = distanciaOtraPoblacion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getOtroIdioma() {
		return otroIdioma;
	}

	public void setOtroIdioma(String otroIdioma) {
		this.otroIdioma = otroIdioma;
	}

	public int getHabitanes() {
		return habitanes;
	}

	public void setHabitanes(int habitanes) {
		this.habitanes = habitanes;
	}

	public int getHabitantesArea() {
		return habitantesArea;
	}

	public void setHabitantesArea(int habitantesArea) {
		this.habitantesArea = habitantesArea;
	}
}	
