package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class CpCodPostal {
	private String empresa;
	private int codigoPostal;
	private int pais;
	private int codigoPoblacion;
	private int ordenCodigoPoblacion;
	private int calle;
	private int deImpar[];
	private int dePar[];
	private int aImpar[];
	private int aPar[];

	public CpCodPostal(){
		empresa = DatosComunes.eEmpresa;
		codigoPostal = 0;
		pais = 0;
		codigoPoblacion = 0;
		ordenCodigoPoblacion = 0;
		calle = 0;
		deImpar = new int[4];		
		dePar = new int[4];
		aImpar = new int[4];
		aPar = new int[4];
		for(int i = 0; i < 4; i++){
			deImpar[i] = 0;
			dePar[i] = 0;
			aImpar[i] = 0;
			aPar[i] = 0;
		}
	}

	public CpCodPostal(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				codigoPostal = rs.getInt("CPPOST_CP");
				pais = rs.getInt("CPPOST_PAIS");
				codigoPoblacion = rs.getInt("CPPOST_CODPOB");
				ordenCodigoPoblacion = rs.getInt("CPPOST_ORDENCPB");
				calle = rs.getInt("CPPOST_CALLE");

				// Creamos los arrays
				deImpar = new int[4];		
				dePar = new int[4];
				aImpar = new int[4];
				aPar = new int[4];

				// Los rellenamos
				deImpar[0] = rs.getInt("CPPOST_D_IMPAR_1");
				deImpar[1] = rs.getInt("CPPOST_D_IMPAR_2");
				deImpar[2] = rs.getInt("CPPOST_D_IMPAR_3");
				deImpar[3] = rs.getInt("CPPOST_D_IMPAR_4");

				dePar[0] = rs.getInt("CPPOST_D_PAR_1");
				dePar[1] = rs.getInt("CPPOST_D_PAR_2");
				dePar[2] = rs.getInt("CPPOST_D_PAR_3");
				dePar[3] = rs.getInt("CPPOST_D_PAR_4");

				aImpar[0] = rs.getInt("CPPOST_A_IMPAR_1");
				aImpar[1] = rs.getInt("CPPOST_A_IMPAR_2");
				aImpar[2] = rs.getInt("CPPOST_A_IMPAR_3");
				aImpar[3] = rs.getInt("CPPOST_A_IMPAR_4");

				aPar[0] = rs.getInt("CPPOST_A_PAR_1");
				aPar[1] = rs.getInt("CPPOST_A_PAR_2");
				aPar[2] = rs.getInt("CPPOST_A_PAR_3");
				aPar[3] = rs.getInt("CPPOST_A_PAR_4");
			}	

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpCodPostal!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			codigoPostal = rs.getInt("CPPOST_CP");
			pais = rs.getInt("CPPOST_PAIS");
			codigoPoblacion = rs.getInt("CPPOST_CODPOB");
			ordenCodigoPoblacion = rs.getInt("CPPOST_ORDENCPB");
			calle = rs.getInt("CPPOST_CALLE");

			// Los rellenamos
			deImpar[0] = rs.getInt("CPPOST_D_IMPAR_1");
			deImpar[1] = rs.getInt("CPPOST_D_IMPAR_2");
			deImpar[2] = rs.getInt("CPPOST_D_IMPAR_3");
			deImpar[3] = rs.getInt("CPPOST_D_IMPAR_4");

			dePar[0] = rs.getInt("CPPOST_D_PAR_1");
			dePar[1] = rs.getInt("CPPOST_D_PAR_2");
			dePar[2] = rs.getInt("CPPOST_D_PAR_3");
			dePar[3] = rs.getInt("CPPOST_D_PAR_4");

			aImpar[0] = rs.getInt("CPPOST_A_IMPAR_1");
			aImpar[1] = rs.getInt("CPPOST_A_IMPAR_2");
			aImpar[2] = rs.getInt("CPPOST_A_IMPAR_3");
			aImpar[3] = rs.getInt("CPPOST_A_IMPAR_4");

			aPar[0] = rs.getInt("CPPOST_A_PAR_1");
			aPar[1] = rs.getInt("CPPOST_A_PAR_2");
			aPar[2] = rs.getInt("CPPOST_A_PAR_3");
			aPar[3] = rs.getInt("CPPOST_A_PAR_4");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de CpCodPostal!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO CPPOST " +
						   "(EMPRESA, " +
						   "CPPOST_CP, " +
						   "CPPOST_PAIS, " +
						   "CPPOST_CODPOB, " +
						   "CPPOST_ORDENCPB, " +
						   "CPPOST_CALLE, " +
						   "CPPOST_D_IMPAR_1, " +
						   "CPPOST_D_IMPAR_2, " +
						   "CPPOST_D_IMPAR_3, " +
						   "CPPOST_D_IMPAR_4, " +
						   "CPPOST_D_PAR_1, " +
						   "CPPOST_D_PAR_2, " +
						   "CPPOST_D_PAR_3, " +
						   "CPPOST_D_PAR_4, " +
						   "CPPOST_A_IMPAR_1, " +
						   "CPPOST_A_IMPAR_2, " +
						   "CPPOST_A_IMPAR_3, " +
						   "CPPOST_A_IMPAR_4, " +
						   "CPPOST_A_PAR_1, " +
						   "CPPOST_A_PAR_2, " +
						   "CPPOST_A_PAR_3, " +
						   "CPPOST_A_PAR_4) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "CPPOST_CP = ?, " +
						   "CPPOST_PAIS = ?, " +
						   "CPPOST_CODPOB = ?, " +
						   "CPPOST_ORDENCPB = ?, " +
						   "CPPOST_CALLE = ?, " +
						   "CPPOST_D_IMPAR_1 = ?, " +
						   "CPPOST_D_IMPAR_2 = ?, " +
						   "CPPOST_D_IMPAR_3 = ?, " +
						   "CPPOST_D_IMPAR_4 = ?, " +
						   "CPPOST_D_PAR_1 = ?, " +
						   "CPPOST_D_PAR_2 = ?, " +
						   "CPPOST_D_PAR_3 = ?, " +
						   "CPPOST_D_PAR_4 = ?, " +
						   "CPPOST_A_IMPAR_1 = ?, " +
						   "CPPOST_A_IMPAR_2 = ?, " +
						   "CPPOST_A_IMPAR_3 = ?, " +
						   "CPPOST_A_IMPAR_4 = ?, " +
						   "CPPOST_A_PAR_1 = ?, " +
						   "CPPOST_A_PAR_2 = ?, " +
						   "CPPOST_A_PAR_3 = ?, " +
						   "CPPOST_A_PAR_4 = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigoPostal);
			ps.setInt(i++, pais);
			ps.setInt(i++, codigoPoblacion);
			ps.setInt(i++, ordenCodigoPoblacion);
			ps.setInt(i++, calle);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, deImpar[j]);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, dePar[j]);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, aImpar[j]);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, aPar[j]);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigoPostal);
			ps.setInt(i++, pais);
			ps.setInt(i++, codigoPoblacion);
			ps.setInt(i++, ordenCodigoPoblacion);
			ps.setInt(i++, calle);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, deImpar[j]);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, dePar[j]);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, aImpar[j]);
			for(int j = 0; j < 4; j++)
				ps.setInt(i++, aPar[j]);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de CpCodPostal!!!");
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

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public int getCodigoPoblacion() {
		return codigoPoblacion;
	}

	public void setCodigoPoblacion(int codigoPoblacion) {
		this.codigoPoblacion = codigoPoblacion;
	}

	public int getOrdenCodigoPoblacion() {
		return ordenCodigoPoblacion;
	}

	public void setOrdenCodigoPoblacion(int ordenCodigoPoblacion) {
		this.ordenCodigoPoblacion = ordenCodigoPoblacion;
	}

	public int getCalle() {
		return calle;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public int[] getDeImpar() {
		return deImpar;
	}

	public void setDeImpar(int indice, int valor) {
		this.deImpar[indice] = valor;
	}
	
	public void setDeImpar(int[] deImpar) {
		this.deImpar = deImpar;
	}

	public int getDePar(int i) {
		return dePar[i];
	}

	public int[] getDePar() {
		return dePar;
	}

	public void setDePar(int indice, int valor) {
		this.dePar[indice] = valor;
	}

	public void setDePar(int[] dePar) {
		this.dePar = dePar;
	}

	public int getaImpar(int i) {
		return aImpar[i];
	}

	public int[] getaImpar() {
		return aImpar;
	}

	public void setaImpar(int indice, int valor) {
		this.aImpar[indice] = valor;
	}
	
	public void setaImpar(int[] aImpar) {
		this.aImpar = aImpar;
	}

	public int getaPar(int i) {
		return aPar[i];
	}

	public int[] getaPar() {
		return aPar;
	}

	public void setaPar(int indice, int valor) {
		this.aPar[indice] = valor;
	}
	
	public void setaPar(int[] aPar) {
		this.aPar = aPar;
	}
}
